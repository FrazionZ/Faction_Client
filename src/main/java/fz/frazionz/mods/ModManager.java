package fz.frazionz.mods;

import fz.frazionz.utils.JsonHelper;
import net.minecraft.client.Minecraft;
import org.json.JSONObject;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class ModManager {

    private static final ModManager INSTANCE = new ModManager();
    private Set<Mod> mods = new HashSet<Mod>();
    private final File modsConfigFile = new File(Minecraft.getMinecraft().gameDir + File.separator + "mods" + File.separator + "mods.json");

    public static ModManager getInstance() {
        return INSTANCE;
    }

    public void onEnable() {
        loadModsConfig();
    }

    public void onDisable() {
        saveModsConfig();
    }

    private void loadModsConfig() {
        if(!modsConfigFile.exists()) {
            try {
                modsConfigFile.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        JSONObject modsConfig = JsonHelper.getJsonObject(modsConfigFile);
        if (modsConfig == null) {
            modsConfig = new JSONObject();
        }
        System.out.println(modsConfig.toString());
    }

    private void saveModsConfig() {
        if(!modsConfigFile.exists()) {
            try {
                modsConfigFile.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean registerMod(Mod mod) {
        mods.add(mod);
        return true;
    }

    public boolean unregisterMod(Mod mod) {
        mods.remove(mod);
        return true;
    }

    public Set<Mod> getMods() {
        return mods;
    }
}
