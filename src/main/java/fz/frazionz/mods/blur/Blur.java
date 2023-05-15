package fz.frazionz.mods.blur;

import com.google.common.base.Throwables;
import fz.frazionz.event.EventHandler;
import fz.frazionz.event.impl.GuiCloseEvent;
import fz.frazionz.event.impl.GuiOpenEvent;
import fz.frazionz.event.impl.RenderTickEvent;
import fz.frazionz.mods.Mod;
import fz.frazionz.mods.mod_hud.ScreenPosition;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.resources.SimpleReloadableResourceManager;
import net.minecraft.client.shader.Shader;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.client.shader.ShaderUniform;
import net.minecraft.resources.ResourceLocation;
import net.optifine.util.ArrayUtils;
import org.json.JSONObject;

import java.util.List;
public class Blur extends Mod {

    public static Blur instance;

    private String[] blurExclusions;
    private long start;
    private int fadeTime;

    public int radius; // Store default so we don't trigger an extra reload
    private int colorFirst;
    private int colorSecond;


    @SuppressWarnings("unchecked")
    public Blur() {
        super("blur");
        instance = this;
    }

    /*@EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);

        // Add our dummy resourcepack
        ((SimpleReloadableResourceManager)Minecraft.getMinecraft().getResourceManager()).registerReloadListener(dummyPack);

        config = new Configuration(new File(event.getModConfigurationDirectory(), "blur.cfg"));
        saveConfig();
    }*/

    /*@SubscribeEvent
    public void onConfigChanged(OnConfigChangedEvent event) {
        if (event.modID.equals(MODID)) {
            saveConfig();
        }
    }*/

    @EventHandler
    public void onGuiChange(GuiOpenEvent event) {
        if (Minecraft.getMinecraft().world != null) {
            EntityRenderer er = Minecraft.getMinecraft().entityRenderer;
            boolean excluded = event.getGui() == null || ArrayUtils.contains(blurExclusions, event.getGui().getClass().getName());
            if (!er.isShaderActive() && !excluded) {
                er.loadShader(new ResourceLocation( "shaders/post/fade_in_blur.json"));
                start = System.currentTimeMillis();
            } else if (er.isShaderActive() && excluded) {
                er.stopUseShader();
            }
        }
    }

    @EventHandler
    public void onGuiClose(GuiCloseEvent event) {
        EntityRenderer er = Minecraft.getMinecraft().entityRenderer;
        boolean excluded = event.getGui() == null || ArrayUtils.contains(blurExclusions, event.getGui().getClass().getName());
        if (er.isShaderActive() && !excluded)
            Minecraft.getMinecraft().entityRenderer.stopUseShader();
    }

    private float getProgress() {
        return Math.min((System.currentTimeMillis() - start) / (float) fadeTime, 1);
    }

    @EventHandler
    public void onRenderTick(RenderTickEvent event) {
        if (event.getPhase() == RenderTickEvent.Phase.END && Minecraft.getMinecraft().currentScreen != null && Minecraft.getMinecraft().entityRenderer.isShaderActive()) {
            ShaderGroup sg = Minecraft.getMinecraft().entityRenderer.getShaderGroup();
            try {
                List<Shader> shaders = sg.getListShaders();
                for (Shader s : shaders) {
                    ShaderUniform su1 = s.getShaderManager().getShaderUniform("Radius");
                    if (su1 != null) {
                        su1.set(radius);
                    }
                    ShaderUniform su = s.getShaderManager().getShaderUniform("Progress");
                    if (su != null) {
                        su.set(getProgress());
                    }
                }
            } catch (IllegalArgumentException e) {
                Throwables.propagate(e);
            }
        }
    }

    public static int getBackgroundColor(boolean second) {
        int color = second ? instance.colorSecond : instance.colorFirst;
        int a = color >>> 24;
        int r = (color >> 16) & 0xFF;
        int b = (color >> 8) & 0xFF;
        int g = color & 0xFF;
        float prog = instance.getProgress();
        a *= prog;
        r *= prog;
        g *= prog;
        b *= prog;
        return a << 24 | r << 16 | b << 8 | g;
    }

    public void loadConfig(JSONObject json) {
        super.loadConfig(json);
        if(json.has("fadeTime") && json.has("fadeTime"))
            fadeTime = json.getInt("fadeTime");
        else
            fadeTime = 200;

        if(json.has("radius"))
            radius = json.getInt("radius");
        else
            radius = 12;

        if(json.has("colorFirst"))
            colorFirst = json.getInt("colorFirst");
        else
            colorFirst = Integer.parseUnsignedInt("75000000");

        if(json.has("colorSecond"))
            colorSecond = json.getInt("colorSecond");
        else
            colorSecond = Integer.parseUnsignedInt("75000000");

        if(json.has("blurExclusions")) {
            blurExclusions = new String[json.getJSONArray("blurExclusions").length()];
            for(int i = 0; i < json.getJSONArray("blurExclusions").length(); i++)
                blurExclusions[i] = json.getJSONArray("blurExclusions").getString(i);
        }
        else
            blurExclusions = new String[] {
                    GuiChat.class.getName(),
            };
    }

    @Override
    public JSONObject getJson() {
        JSONObject json = super.getJson();

        json.put("fadeTime", fadeTime);
        json.put("radius", radius);
        json.put("colorFirst", colorFirst);
        json.put("colorSecond", colorSecond);
        json.put("blurExclusions", blurExclusions);

        return json;
    }
}