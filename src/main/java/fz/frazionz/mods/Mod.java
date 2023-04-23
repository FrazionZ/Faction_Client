package fz.frazionz.mods;

import fz.frazionz.event.EventManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import org.json.JSONObject;
import org.lwjgl.Sys;

public class Mod {

	protected String name;
	protected boolean isEnabled;
	protected FontRenderer font;
	
	public Mod(String name) {
		this.name = name;
		this.isEnabled = false;
		this.font = Minecraft.getMinecraft().fontRenderer;
	}

	public void setEnabled(boolean isEnabled) {
		
		this.isEnabled = isEnabled;
		
		if(isEnabled)
		{
			EventManager.register(this);		
		}
		else
		{
			EventManager.unregister(this);
		}
		
	}
	
	public boolean isEnabled() {
		return isEnabled;
	}

	public String getName() {
		return name;
	}

	public void loadConfig(JSONObject json) {
		if(json.has("enabled")) {
			setEnabled(json.getBoolean("enabled"));
		}
		else {
			setEnabled(false);
		}
	}

	public JSONObject getJson() {
		JSONObject json = new JSONObject();
		json.put("name", name);
		json.put("enabled", isEnabled);
		return json;
	}
}
