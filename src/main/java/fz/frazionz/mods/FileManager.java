package fz.frazionz.mods;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.google.common.base.Splitter;
import com.google.gson.Gson;

import fz.frazionz.gui.hud.ScreenPosition;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.nbt.NBTTagCompound;
import net.optifine.reflect.Reflector;

public class FileManager {

	public static final Splitter COLON_SPLITTER = Splitter.on(':');
	
	private static Gson gson = new Gson();
	
	private static File ROOT_DIR = new File("hud");
	public static File MODS_DIR = new File(ROOT_DIR, "mods");
	
	public static void init() {
		
		if(!ROOT_DIR.exists()) {
			
			ROOT_DIR.mkdir();
			
		}
		
		if(!MODS_DIR.exists()) {
			
			MODS_DIR.mkdir();
			
		}		
				
	}
	
	public static Gson getGson() {
		
		return gson;
		
	}
	
	public static File getModsDirectory() {
		return MODS_DIR;
	}
	
	public static boolean writeJsonToFile(File file, Object obj) {
		
			try {
				if(!file.exists()) {
					file.createNewFile();
				}
				
				FileOutputStream outputStream = new FileOutputStream(file);
				outputStream.write(gson.toJson(obj).getBytes());
				outputStream.flush();
				outputStream.close();			
				return true;
			}
			catch (IOException e) {
				e.printStackTrace();
				return false;
			}
						
	}
	
	public static <T extends Object> T readFromJson(File file, Class<T> c) {
		
		try {
			
			FileInputStream fileInputStream = new FileInputStream(file);
			InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			
			StringBuilder builder = new StringBuilder();
			
			String line;
			
			while((line = bufferedReader.readLine()) != null) {
				
				builder.append(line);
				
			}
			
			bufferedReader.close();
			inputStreamReader.close();
			fileInputStream.close();
			
			return gson.fromJson(builder.toString(), c);
			
		}
		catch(IOException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public static void saveModEnable(File file, boolean enable)
    {
        if (Reflector.FMLClientHandler.exists())
        {
            Object object = Reflector.call(Reflector.FMLClientHandler_instance);

            if (object != null && Reflector.callBoolean(object, Reflector.FMLClientHandler_isLoading))
            {
                return;
            }
        }

        PrintWriter printwriter = null;

        try
        {
            printwriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8));
            printwriter.println("enable:" + enable);
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        finally
        {
            IOUtils.closeQuietly((Writer)printwriter);
        }
    }
	
	public static boolean loadModEnable(File file)
    {
		
        try
        {
            if (!file.exists())
            {
                return false;
            }
            

            List<String> list = IOUtils.readLines(new FileInputStream(file), StandardCharsets.UTF_8);
            NBTTagCompound nbttagcompound = new NBTTagCompound();

            for (String s : list)
            {
                try
                {
                    Iterator<String> iterator = COLON_SPLITTER.omitEmptyStrings().limit(2).split(s).iterator();
                    nbttagcompound.setString(iterator.next(), iterator.next());
                }
                catch (Exception var12)
                {
                    var12.printStackTrace();
                }
            }


            for (String s1 : nbttagcompound.getKeySet())
            {
                String s2 = nbttagcompound.getString(s1);

                try
                {

                    if ("enable".equals(s1))
                    {
                        if("true".equals(s2)) {
                        	return true;
                        }
                        else {
                        	return false;
                        }
                    }
                }
                catch (Exception exception1)
                {
                    exception1.printStackTrace();
                }
            }

            KeyBinding.resetKeyBindingArrayAndHash();
        }
        catch (Exception exception1)
        {
            exception1.printStackTrace();
        }
		return false;
    }
	
	
	public static void saveModOptions(File file, ScreenPosition pos)
    {
        if (Reflector.FMLClientHandler.exists())
        {
            Object object = Reflector.call(Reflector.FMLClientHandler_instance);

            if (object != null && Reflector.callBoolean(object, Reflector.FMLClientHandler_isLoading))
            {
                return;
            }
        }

        PrintWriter printwriter = null;

        try
        {
            printwriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8));
            printwriter.println("x:" + pos.getAbsoluteX());
            printwriter.println("y:" + pos.getAbsoluteY());
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        finally
        {
            IOUtils.closeQuietly((Writer)printwriter);
        }
    }
	
	
	public static ScreenPosition loadModOptions(File file)
    {
		ScreenPosition screenPos = new ScreenPosition(1,1);
		
        try
        {
            if (!file.exists())
            {
                return screenPos;
            }
            

            List<String> list = IOUtils.readLines(new FileInputStream(file), StandardCharsets.UTF_8);
            NBTTagCompound nbttagcompound = new NBTTagCompound();

            for (String s : list)
            {
                try
                {
                    Iterator<String> iterator = COLON_SPLITTER.omitEmptyStrings().limit(2).split(s).iterator();
                    nbttagcompound.setString(iterator.next(), iterator.next());
                }
                catch (Exception var12)
                {
                    var12.printStackTrace();
                }
            }


            for (String s1 : nbttagcompound.getKeySet())
            {
                String s2 = nbttagcompound.getString(s1);

                try
                {

                    if ("x".equals(s1))
                    {
                        screenPos.setX(Integer.parseInt(s2));
                    }
                    if ("y".equals(s1))
                    {
                    	screenPos.setY(Integer.parseInt(s2));
                    }
                }
                catch (Exception exception1)
                {
                    exception1.printStackTrace();
                }
            }

            KeyBinding.resetKeyBindingArrayAndHash();
        }
        catch (Exception exception1)
        {
            exception1.printStackTrace();
        }
        
        return screenPos;
    }
	
	
}
