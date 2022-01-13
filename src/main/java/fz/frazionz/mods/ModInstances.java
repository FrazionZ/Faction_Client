package fz.frazionz.mods;

import fz.frazionz.gui.hud.HUDManager;
import fz.frazionz.mods.impl.ModKeystrokes;

public class ModInstances {
	
	
	private static ModKeystrokes modKeystrokes;
	private static ModToggle modToggle;
	
	
	/*public static void register(HUDManager api) {
		
		modKeystrokes = new ModKeystrokes();
		api.register(modKeystrokes);
		
	}*/
	
	
	public static void register(HUDManager api) {

		// ADD_MODS //
		
			modKeystrokes = new ModKeystrokes();
			api.register(modKeystrokes);
	}
	
	public static ModKeystrokes getModKeystrokes() {
		return modKeystrokes;
	}

}
