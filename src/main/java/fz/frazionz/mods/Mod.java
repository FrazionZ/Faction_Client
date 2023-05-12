package fz.frazionz.mods;

import fz.frazionz.FzClient;
import fz.frazionz.client.gui.utils.RoundedShaderRenderer;
import fz.frazionz.event.EventManager;
import fz.frazionz.utils.StringUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.resources.ResourceLocation;
import org.json.JSONObject;
import org.lwjgl.Sys;

public class Mod {

	protected String name;
	protected boolean isEnabled;
	protected FontRenderer font;
	protected ResourceLocation icon;
	protected static Minecraft mc = Minecraft.getMinecraft();
	
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

	public void setIcon(ResourceLocation icon) {
		this.icon = icon;
	}

	public void drawBackground(int width, int height) {
		RoundedShaderRenderer.getInstance().drawRoundRect(mc.displayWidth/2-width/2, mc.displayHeight/2-height/2, width, height, 10, Gui.BLACK_4);
	}

	protected int getPadding() {
		return 12;
	}

	public void drawConfigScreen(int width, int height) {
		this.drawBackground(width, height);
		FzClient.getInstance().getTTFFontRenderers().get(20).drawString(StringUtils.capitalize(name), getPadding(), getPadding(), 0xFFFFFFFF);
	}
}
