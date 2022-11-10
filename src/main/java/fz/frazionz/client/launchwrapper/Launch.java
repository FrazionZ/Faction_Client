package fz.frazionz.client.launchwrapper;

import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import joptsimple.OptionSpec;
import org.apache.logging.log4j.Level;

public class Launch
{
    private static final String DEFAULT_TWEAK = "net.minecraft.launchwrapper.VanillaTweaker";
    public static File minecraftHome;
    public static File assetsDir;
    public static Map<String, Object> blackboard;
    public static LaunchClassLoader classLoader;

    public static void main(final String[] args) {
        new Launch().launch(args);
    }

    private Launch() {
        if (this.getClass().getClassLoader() instanceof URLClassLoader) {
            final URLClassLoader ucl = (URLClassLoader)this.getClass().getClassLoader();
            Launch.classLoader = new LaunchClassLoader(ucl.getURLs());
        }
        else {
            Launch.classLoader = new LaunchClassLoader(this.getURLs());
        }
        Launch.blackboard = new HashMap<String, Object>();
        Thread.currentThread().setContextClassLoader(Launch.classLoader);
    }

    private URL[] getURLs() {
        final String cp = System.getProperty("java.class.path");
        String[] elements = cp.split(File.pathSeparator);
        if (elements.length == 0) {
            elements = new String[] { "" };
        }
        final URL[] urls = new URL[elements.length];
        for (int i = 0; i < elements.length; ++i) {
            try {
                final URL url = new File(elements[i]).toURI().toURL();
                urls[i] = url;
            }
            catch (MalformedURLException ex) {}
        }
        return urls;
    }

    private void launch(final String[] args) {
        final OptionParser parser = new OptionParser();
        parser.allowsUnrecognizedOptions();
        final OptionSpec<String> profileOption = (OptionSpec<String>)parser.accepts("version", "The version we launched with").withRequiredArg();
        final OptionSpec<File> gameDirOption = (OptionSpec<File>)parser.accepts("gameDir", "Alternative game directory").withRequiredArg().ofType((Class)File.class);
        final OptionSpec<File> assetsDirOption = (OptionSpec<File>)parser.accepts("assetsDir", "Assets directory").withRequiredArg().ofType((Class)File.class);
        final OptionSpec<String> tweakClassOption = (OptionSpec<String>)parser.accepts("tweakClass", "Tweak class(es) to load").withRequiredArg().defaultsTo("net.minecraft.launchwrapper.VanillaTweaker", new String[0]);
        final OptionSpec<String> nonOption = (OptionSpec<String>)parser.nonOptions();
        final OptionSet options = parser.parse(args);
        Launch.minecraftHome = (File)options.valueOf((OptionSpec)gameDirOption);
        Launch.assetsDir = (File)options.valueOf((OptionSpec)assetsDirOption);
        final String profileName = (String)options.valueOf((OptionSpec)profileOption);
        final List<String> tweakClassNames = new ArrayList<String>(options.valuesOf((OptionSpec)tweakClassOption));
        final List<String> argumentList = new ArrayList<String>();
        Launch.blackboard.put("TweakClasses", tweakClassNames);
        Launch.blackboard.put("ArgumentList", argumentList);
        final Set<String> allTweakerNames = new HashSet<String>();
        final List<ITweaker> allTweakers = new ArrayList<ITweaker>();
        try {
            final List<ITweaker> tweakers = new ArrayList<ITweaker>(tweakClassNames.size() + 1);
            Launch.blackboard.put("Tweaks", tweakers);
            ITweaker primaryTweaker = null;
            do {
                final Iterator<String> it = tweakClassNames.iterator();
                while (it.hasNext()) {
                    final String tweakName = it.next();
                    if (allTweakerNames.contains(tweakName)) {
                        LogWrapper.log(Level.WARN, "Tweak class name %s has already been visited -- skipping", tweakName);
                        it.remove();
                    }
                    else {
                        allTweakerNames.add(tweakName);
                        LogWrapper.log(Level.INFO, "Loading tweak class name %s", tweakName);
                        Launch.classLoader.addClassLoaderExclusion(tweakName.substring(0, tweakName.lastIndexOf(46)));
                        final ITweaker tweaker = (ITweaker)Class.forName(tweakName, true, Launch.classLoader).newInstance();
                        tweakers.add(tweaker);
                        it.remove();
                        if (primaryTweaker != null) {
                            continue;
                        }
                        LogWrapper.log(Level.INFO, "Using primary tweak class name %s", tweakName);
                        primaryTweaker = tweaker;
                    }
                }
                final Iterator<ITweaker> it2 = tweakers.iterator();
                while (it2.hasNext()) {
                    final ITweaker tweaker2 = it2.next();
                    LogWrapper.log(Level.INFO, "Calling tweak class %s", tweaker2.getClass().getName());
                    tweaker2.acceptOptions(options.valuesOf((OptionSpec)nonOption), Launch.minecraftHome, Launch.assetsDir, profileName);
                    tweaker2.injectIntoClassLoader(Launch.classLoader);
                    allTweakers.add(tweaker2);
                    it2.remove();
                }
            } while (!tweakClassNames.isEmpty());
            final Iterator<ITweaker> iterator = allTweakers.iterator();
            while (iterator.hasNext()) {
                final ITweaker tweaker2 = iterator.next();
                argumentList.addAll(Arrays.asList(tweaker2.getLaunchArguments()));
            }
            final String launchTarget = primaryTweaker.getLaunchTarget();
            final Class<?> clazz = Class.forName(launchTarget, false, Launch.classLoader);
            final Method mainMethod = clazz.getMethod("main", String[].class);
            LogWrapper.info("Launching wrapped minecraft {%s}", launchTarget);
            mainMethod.invoke(null, argumentList.toArray(new String[argumentList.size()]));
        }
        catch (Exception e) {
            LogWrapper.log(Level.ERROR, e, "Unable to launch", new Object[0]);
            System.exit(1);
        }
    }
}
