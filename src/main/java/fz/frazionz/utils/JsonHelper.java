package fz.frazionz.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.src.Config;

public class JsonHelper {
	
	public static JSONObject getJsonObject(ResourceLocation location) {
		try 
		{
			InputStream is = Config.getResourceStream(location);
	        String jsonTxt = IOUtils.toString(is, "UTF-8");
			return new JSONObject(jsonTxt);
		} 
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
