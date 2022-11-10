package fz.frazionz.client.launchwrapper;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLConnection;
import java.security.CodeSigner;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import java.util.jar.Attributes.Name;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

public class LaunchClassLoader extends URLClassLoader
{
    public static final int BUFFER_SIZE = 4096;
    private List<URL> sources;
    private ClassLoader parent = this.getClass().getClassLoader();
    private List<IClassTransformer> transformers = new ArrayList<IClassTransformer>(2);
    private Map < String, Class<? >> cachedClasses = new ConcurrentHashMap < String, Class<? >> ();
    private Set<String> invalidClasses = new HashSet<String>(1000);
    private Set<String> classLoaderExceptions = new HashSet<String>();
    private Set<String> transformerExceptions = new HashSet<String>();
    private Map<String, byte[]> resourceCache = new ConcurrentHashMap<String, byte[]>(1000);
    private Set<String> negativeResourceCache = Collections.<String>newSetFromMap(new ConcurrentHashMap());
    private IClassNameTransformer renameTransformer;
    private final ThreadLocal<byte[]> loadBuffer = new ThreadLocal<byte[]>();
    private static final String[] RESERVED_NAMES = new String[] {"CON", "PRN", "AUX", "NUL", "COM1", "COM2", "COM3", "COM4", "COM5", "COM6", "COM7", "COM8", "COM9", "LPT1", "LPT2", "LPT3", "LPT4", "LPT5", "LPT6", "LPT7", "LPT8", "LPT9"};
    private static final boolean DEBUG = Boolean.parseBoolean(System.getProperty("legacy.debugClassLoading", "false"));
    private static final boolean DEBUG_FINER = DEBUG && Boolean.parseBoolean(System.getProperty("legacy.debugClassLoadingFiner", "false"));
    private static final boolean DEBUG_SAVE = DEBUG && Boolean.parseBoolean(System.getProperty("legacy.debugClassLoadingSave", "false"));
    private static File tempFolder = null;

    public LaunchClassLoader(URL[] p_i12_1_)
    {
        super(p_i12_1_, getParentClassLoader());
        this.sources = new ArrayList<URL>(Arrays.asList(p_i12_1_));
        this.addClassLoaderExclusion("java.");
        this.addClassLoaderExclusion("sun.");
        this.addClassLoaderExclusion("com.sun.");
        this.addClassLoaderExclusion("org.lwjgl.");
        this.addClassLoaderExclusion("org.apache.logging.");
        this.addClassLoaderExclusion("net.minecraft.launchwrapper.");
        this.addTransformerExclusion("javax.");
        this.addTransformerExclusion("argo.");
        this.addTransformerExclusion("org.objectweb.asm.");
        this.addTransformerExclusion("com.google.common.");
        this.addTransformerExclusion("org.bouncycastle.");
        this.addTransformerExclusion("net.minecraft.launchwrapper.injector.");

        if (DEBUG_SAVE)
        {
            int i = 1;

            for (tempFolder = new File(Launch.minecraftHome, "CLASSLOADER_TEMP"); tempFolder.exists() && i <= 10; tempFolder = new File(Launch.minecraftHome, "CLASSLOADER_TEMP" + i++))
            {
                ;
            }

            if (tempFolder.exists())
            {
                LogWrapper.info("DEBUG_SAVE enabled, but 10 temp directories already exist, clean them and try again.");
                tempFolder = null;
            }
            else
            {
                LogWrapper.info("DEBUG_SAVE Enabled, saving all classes to \"%s\"", tempFolder.getAbsolutePath().replace('\\', '/'));
                tempFolder.mkdirs();
            }
        }
    }

    public void registerTransformer(String p_registerTransformer_1_)
    {
        try
        {
            IClassTransformer iclasstransformer = (IClassTransformer)this.loadClass(p_registerTransformer_1_).newInstance();
            this.transformers.add(iclasstransformer);

            if (iclasstransformer instanceof IClassNameTransformer && this.renameTransformer == null)
            {
                this.renameTransformer = (IClassNameTransformer)iclasstransformer;
            }
        }
        catch (Exception exception)
        {
            LogWrapper.log(Level.ERROR, exception, "A critical problem occurred registering the ASM transformer class %s", p_registerTransformer_1_);
        }
    }

    public Class<?> findClass(String p_findClass_1_) throws ClassNotFoundException
    {
        if (this.invalidClasses.contains(p_findClass_1_))
        {
            throw new ClassNotFoundException(p_findClass_1_);
        }
        else
        {
            for (String s : this.classLoaderExceptions)
            {
                if (p_findClass_1_.startsWith(s))
                {
                    return this.parent.loadClass(p_findClass_1_);
                }
            }

            if (this.cachedClasses.containsKey(p_findClass_1_))
            {
                return (Class)this.cachedClasses.get(p_findClass_1_);
            }
            else
            {
                for (String s4 : this.transformerExceptions)
                {
                    if (p_findClass_1_.startsWith(s4))
                    {
                        try
                        {
                            Class<?> oclass = super.findClass(p_findClass_1_);
                            this.cachedClasses.put(p_findClass_1_, oclass);
                            return oclass;
                        }
                        catch (ClassNotFoundException classnotfoundexception)
                        {
                            this.invalidClasses.add(p_findClass_1_);
                            throw classnotfoundexception;
                        }
                    }
                }

                try
                {
                    String s3 = this.transformName(p_findClass_1_);

                    if (this.cachedClasses.containsKey(s3))
                    {
                        return (Class)this.cachedClasses.get(s3);
                    }
                    else
                    {
                        String s5 = this.untransformName(p_findClass_1_);
                        int i = s5.lastIndexOf(46);
                        String s1 = i == -1 ? "" : s5.substring(0, i);
                        String s2 = s5.replace('.', '/').concat(".class");
                        URLConnection urlconnection = this.findCodeSourceConnectionFor(s2);
                        CodeSigner[] acodesigner = null;

                        if (i > -1 && !s5.startsWith("net.minecraft.") && !s5.startsWith("com.mojang.blaze3d."))
                        {
                            if (urlconnection instanceof JarURLConnection)
                            {
                                JarURLConnection jarurlconnection = (JarURLConnection)urlconnection;
                                JarFile jarfile = jarurlconnection.getJarFile();

                                if (jarfile != null && jarfile.getManifest() != null)
                                {
                                    Manifest manifest = jarfile.getManifest();
                                    JarEntry jarentry = jarfile.getJarEntry(s2);
                                    Package opackage = this.getPackage(s1);
                                    this.getClassBytes(s5);
                                    acodesigner = jarentry.getCodeSigners();

                                    if (opackage == null)
                                    {
                                        this.definePackage(s1, manifest, jarurlconnection.getJarFileURL());
                                    }
                                    else if (opackage.isSealed() && !opackage.isSealed(jarurlconnection.getJarFileURL()))
                                    {
                                        LogWrapper.severe("The jar file %s is trying to seal already secured path %s", jarfile.getName(), s1);
                                    }
                                    else if (this.isSealed(s1, manifest))
                                    {
                                        LogWrapper.severe("The jar file %s has a security seal for path %s, but that path is defined and not secure", jarfile.getName(), s1);
                                    }
                                }
                            }
                            else
                            {
                                Package opackage1 = this.getPackage(s1);

                                if (opackage1 == null)
                                {
                                    this.definePackage(s1, (String)null, (String)null, (String)null, (String)null, (String)null, (String)null, (URL)null);
                                }
                                else if (opackage1.isSealed())
                                {
                                    LogWrapper.severe("The URL %s is defining elements for sealed path %s", urlconnection.getURL(), s1);
                                }
                            }
                        }

                        byte[] abyte = this.runTransformers(s5, s3, this.getClassBytes(s5));

                        if (DEBUG_SAVE)
                        {
                            this.saveTransformedClass(abyte, s3);
                        }

                        CodeSource codesource = urlconnection == null ? null : new CodeSource(urlconnection.getURL(), acodesigner);
                        Class<?> oclass1 = this.defineClass(s3, abyte, 0, abyte.length, codesource);
                        this.cachedClasses.put(s3, oclass1);
                        return oclass1;
                    }
                }
                catch (Throwable throwable)
                {
                    this.invalidClasses.add(p_findClass_1_);

                    if (DEBUG)
                    {
                        LogWrapper.log(Level.TRACE, throwable, "Exception encountered attempting classloading of %s", p_findClass_1_);
                        LogManager.getLogger("LaunchWrapper").log(Level.ERROR, "Exception encountered attempting classloading of {}", throwable);
                    }

                    throw new ClassNotFoundException(p_findClass_1_, throwable);
                }
            }
        }
    }

    private void saveTransformedClass(byte[] p_saveTransformedClass_1_, String p_saveTransformedClass_2_)
    {
        if (tempFolder != null)
        {
            File file1 = new File(tempFolder, p_saveTransformedClass_2_.replace('.', File.separatorChar) + ".class");
            File file2 = file1.getParentFile();

            if (!file2.exists())
            {
                file2.mkdirs();
            }

            if (file1.exists())
            {
                file1.delete();
            }

            try
            {
                LogWrapper.fine("Saving transformed class \"%s\" to \"%s\"", p_saveTransformedClass_2_, file1.getAbsolutePath().replace('\\', '/'));
                OutputStream outputstream = new FileOutputStream(file1);
                outputstream.write(p_saveTransformedClass_1_);
                outputstream.close();
            }
            catch (IOException ioexception)
            {
                LogWrapper.log(Level.WARN, ioexception, "Could not save transformed class \"%s\"", p_saveTransformedClass_2_);
            }
        }
    }

    private String untransformName(String p_untransformName_1_)
    {
        return this.renameTransformer != null ? this.renameTransformer.unmapClassName(p_untransformName_1_) : p_untransformName_1_;
    }

    private String transformName(String p_transformName_1_)
    {
        return this.renameTransformer != null ? this.renameTransformer.remapClassName(p_transformName_1_) : p_transformName_1_;
    }

    private boolean isSealed(String p_isSealed_1_, Manifest p_isSealed_2_)
    {
        Attributes attributes = p_isSealed_2_.getAttributes(p_isSealed_1_);
        String s = null;

        if (attributes != null)
        {
            s = attributes.getValue(Name.SEALED);
        }

        if (s == null)
        {
            attributes = p_isSealed_2_.getMainAttributes();

            if (attributes != null)
            {
                s = attributes.getValue(Name.SEALED);
            }
        }

        return "true".equalsIgnoreCase(s);
    }

    private URLConnection findCodeSourceConnectionFor(String p_findCodeSourceConnectionFor_1_)
    {
        URL url = this.findResource(p_findCodeSourceConnectionFor_1_);

        if (url != null)
        {
            try
            {
                return url.openConnection();
            }
            catch (IOException ioexception)
            {
                throw new RuntimeException(ioexception);
            }
        }
        else
        {
            return null;
        }
    }

    private byte[] runTransformers(String p_runTransformers_1_, String p_runTransformers_2_, byte[] p_runTransformers_3_)
    {
        if (DEBUG_FINER)
        {
            LogWrapper.finest("Beginning transform of {%s (%s)} Start Length: %d", p_runTransformers_1_, p_runTransformers_2_, p_runTransformers_3_ == null ? 0 : p_runTransformers_3_.length);

            for (IClassTransformer iclasstransformer : this.transformers)
            {
                String s = iclasstransformer.getClass().getName();
                LogWrapper.finest("Before Transformer {%s (%s)} %s: %d", p_runTransformers_1_, p_runTransformers_2_, s, p_runTransformers_3_ == null ? 0 : p_runTransformers_3_.length);
                p_runTransformers_3_ = iclasstransformer.transform(p_runTransformers_1_, p_runTransformers_2_, p_runTransformers_3_);
                LogWrapper.finest("After  Transformer {%s (%s)} %s: %d", p_runTransformers_1_, p_runTransformers_2_, s, p_runTransformers_3_ == null ? 0 : p_runTransformers_3_.length);
            }

            LogWrapper.finest("Ending transform of {%s (%s)} Start Length: %d", p_runTransformers_1_, p_runTransformers_2_, p_runTransformers_3_ == null ? 0 : p_runTransformers_3_.length);
        }
        else
        {
            for (IClassTransformer iclasstransformer1 : this.transformers)
            {
                p_runTransformers_3_ = iclasstransformer1.transform(p_runTransformers_1_, p_runTransformers_2_, p_runTransformers_3_);
            }
        }

        return p_runTransformers_3_;
    }

    public void addURL(URL p_addURL_1_)
    {
        super.addURL(p_addURL_1_);
        this.sources.add(p_addURL_1_);
    }

    public List<URL> getSources()
    {
        return this.sources;
    }

    private byte[] readFully(InputStream p_readFully_1_)
    {
        try
        {
            byte[] abyte = this.getOrCreateBuffer();
            int j = 0;
            int i;

            while ((i = p_readFully_1_.read(abyte, j, abyte.length - j)) != -1)
            {
                j += i;

                if (j >= abyte.length - 1)
                {
                    byte[] abyte1 = new byte[abyte.length + 4096];
                    System.arraycopy(abyte, 0, abyte1, 0, abyte.length);
                    abyte = abyte1;
                }
            }

            byte[] abyte2 = new byte[j];
            System.arraycopy(abyte, 0, abyte2, 0, j);
            return abyte2;
        }
        catch (Throwable throwable)
        {
            LogWrapper.log(Level.WARN, throwable, "Problem loading class");
            return new byte[0];
        }
    }

    private byte[] getOrCreateBuffer()
    {
        byte[] abyte = this.loadBuffer.get();

        if (abyte == null)
        {
            this.loadBuffer.set(new byte[4096]);
            abyte = this.loadBuffer.get();
        }

        return abyte;
    }

    public List<IClassTransformer> getTransformers()
    {
        return Collections.<IClassTransformer>unmodifiableList(this.transformers);
    }

    public void addClassLoaderExclusion(String p_addClassLoaderExclusion_1_)
    {
        this.classLoaderExceptions.add(p_addClassLoaderExclusion_1_);
    }

    public void addTransformerExclusion(String p_addTransformerExclusion_1_)
    {
        this.transformerExceptions.add(p_addTransformerExclusion_1_);
    }

    public byte[] getClassBytes(String p_getClassBytes_1_) throws IOException
    {
        if (this.negativeResourceCache.contains(p_getClassBytes_1_))
        {
            return null;
        }
        else if (this.resourceCache.containsKey(p_getClassBytes_1_))
        {
            return this.resourceCache.get(p_getClassBytes_1_);
        }
        else
        {
            if (p_getClassBytes_1_.indexOf(46) == -1)
            {
                for (String s : RESERVED_NAMES)
                {
                    if (p_getClassBytes_1_.toUpperCase(Locale.ENGLISH).startsWith(s))
                    {
                        byte[] abyte = this.getClassBytes("_" + p_getClassBytes_1_);

                        if (abyte != null)
                        {
                            this.resourceCache.put(p_getClassBytes_1_, abyte);
                            return abyte;
                        }
                    }
                }
            }

            InputStream inputstream = null;
            Object object;

            try
            {
                String s1 = p_getClassBytes_1_.replace('.', '/').concat(".class");
                URL url = this.findResource(s1);

                if (url != null)
                {
                    inputstream = url.openStream();

                    if (DEBUG)
                    {
                        LogWrapper.finest("Loading class %s from resource %s", p_getClassBytes_1_, url.toString());
                    }

                    byte[] abyte1 = this.readFully(inputstream);
                    this.resourceCache.put(p_getClassBytes_1_, abyte1);
                    byte[] abyte2 = abyte1;
                    return abyte2;
                }

                if (DEBUG)
                {
                    LogWrapper.finest("Failed to find class resource %s", s1);
                }

                this.negativeResourceCache.add(p_getClassBytes_1_);
                object = null;
            }
            finally
            {
                closeSilently(inputstream);
            }

            return (byte[])object;
        }
    }

    private static void closeSilently(Closeable p_closeSilently_0_)
    {
        if (p_closeSilently_0_ != null)
        {
            try
            {
                p_closeSilently_0_.close();
            }
            catch (IOException var2)
            {
                ;
            }
        }
    }

    public void clearNegativeEntries(Set<String> p_clearNegativeEntries_1_)
    {
        this.negativeResourceCache.removeAll(p_clearNegativeEntries_1_);
    }

    private static ClassLoader getParentClassLoader()
    {
        if (!System.getProperty("java.version").startsWith("1."))
        {
            try
            {
                return (ClassLoader)ClassLoader.class.getDeclaredMethod("getPlatformClassLoader").invoke((Object)null);
            }
            catch (Throwable var1)
            {
                LogWrapper.warning("No platform classloader: " + System.getProperty("java.version"));
            }
        }

        return null;
    }
}
