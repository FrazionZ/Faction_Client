package fz.frazionz.mods;

import fz.frazionz.mods.mod_hud.HUDManager;
import fz.frazionz.mods.mod_hud.IRenderer;
import fz.frazionz.mods.armor_hud.ArmorHUDMod;
import fz.frazionz.mods.faction_hud.FactionHUDMod;
import fz.frazionz.mods.keystrokes.KeystrokesMod;
import fz.frazionz.mods.mod_hud.ScreenPosition;
import fz.frazionz.mods.potion_hud.PotionHUDMod;
import fz.frazionz.mods.toggle_sprint.ToggleSprintMod;
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

    private void createConfigIfNotExists() {
        if(!modsConfigFile.exists()) {
            try {
                modsConfigFile.getParentFile().mkdirs();
                modsConfigFile.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void loadModsConfig() {
        createConfigIfNotExists();
        JSONObject modsConfig = JsonHelper.getJsonObject(modsConfigFile);
        if (modsConfig == null) {
            modsConfig = new JSONObject();
        }

        registerMod(new ArmorHUDMod());
        registerMod(new FactionHUDMod());
        registerMod(new KeystrokesMod());
        registerMod(new PotionHUDMod());
        registerMod(new ToggleSprintMod());

        for (Mod mod : mods) {
            if (modsConfig.has(mod.getName())) {
                mod.loadConfig(modsConfig.getJSONObject(mod.getName()));
            }
            else {
                mod.loadConfig(new JSONObject());
            }
        }
    }

    public void saveModsConfig() {
        createConfigIfNotExists();

        JSONObject modsConfig = new JSONObject();
        for (Mod mod : mods) {
            modsConfig.put(mod.getName(), mod.getJson());
        }

        JsonHelper.saveJSONObject(modsConfigFile, modsConfig);
    }

    public boolean registerMod(Mod mod) {
        mods.add(mod);
        if(mod instanceof IRenderer)
            HUDManager.getInstance().register((IRenderer) mod);
        return true;
    }

    public boolean unregisterMod(Mod mod) {
        mods.remove(mod);
        if(mod instanceof IRenderer)
            HUDManager.getInstance().unregister((IRenderer) mod);
        return true;
    }

    public Set<Mod> getMods() {
        return mods;
    }
}
