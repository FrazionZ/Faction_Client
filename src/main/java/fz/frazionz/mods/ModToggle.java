package fz.frazionz.mods;

import java.io.File;

import fz.frazionz.gui.hud.ScreenPosition;

public class ModToggle {
	public static File getFolder(String mod) {
		File file = new File(FileManager.MODS_DIR, mod);
		file.mkdirs();
		return file;
	}

	public static void saveIsEnabledFileTxt(String mod, Boolean b) {
		FileManager.saveModEnable(new File(getFolder(mod), "enabled.txt"), b);
	}
	
	public static void saveIsEnabledToFile(String mod, Boolean b) {
		FileManager.writeJsonToFile(new File(getFolder(mod), "enabled.json"), b);
	}
	
	public static Boolean loadEnabledFromFile(String mod) {
		Boolean b = FileManager.readFromJson(new File(getFolder(mod), "enabled.json"), Boolean.class);
		
		if (b == null) {
			b = false;
			saveIsEnabledToFile(mod, b);
		}
		return b;
	}
	
	public static Boolean loadEnabledFileTxt(String mod) {
		Boolean b = FileManager.loadModEnable(new File(getFolder(mod), "enabled.json"));

		return b;
	}
}