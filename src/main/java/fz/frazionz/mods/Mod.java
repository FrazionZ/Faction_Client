package fz.frazionz.mods;

import fz.frazionz.FzClient;
import fz.frazionz.event.EventManager;
import fz.frazionz.mods.impl.ModKeystrokes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

public class Mod {

	protected boolean isEnabled;
	
	protected static Minecraft mc;
	protected static FontRenderer font;
	protected static FzClient client;
	
	public Mod() {
		this.mc = Minecraft.getMinecraft();
		this.font = mc.fontRenderer;
		this.client = FzClient.getInstance();
		
		// ADD_MODS //
		
		if(this instanceof ModKeystrokes) {
			this.setEnabled(this.mc.gameSettings.keystrokesMod);
			System.out.println("[LOADING MODS] Keystrokes Mod");
		}
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
	
}
