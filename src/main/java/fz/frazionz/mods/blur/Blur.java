package fz.frazionz.mods.blur;

import com.google.common.base.Throwables;
import fz.frazionz.event.EventHandler;
import fz.frazionz.event.impl.GuiCloseEvent;
import fz.frazionz.event.impl.GuiOpenEvent;
import fz.frazionz.event.impl.RenderTickEvent;
import fz.frazionz.mods.Mod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.resources.SimpleReloadableResourceManager;
import net.minecraft.client.shader.Shader;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.client.shader.ShaderUniform;
import net.minecraft.resources.ResourceLocation;
import net.optifine.util.ArrayUtils;

import java.util.List;
public class Blur extends Mod {

    //@Instance
    public static Blur instance;

   // public Configuration config;

    private String[] blurExclusions = new String[] { GuiChat.class.getName() };
    private long start;
    private int fadeTime = 200;

    public int radius = 12; // Store default so we don't trigger an extra reload
    private int colorFirst = Integer.parseUnsignedInt("75000000");
    private int colorSecond = Integer.parseUnsignedInt("75000000");

    //@Nonnull
    public static ShaderResourcePack dummyPack = new ShaderResourcePack();

    @SuppressWarnings("unchecked")
    public Blur() {
        super("blur");
        instance = this;
        Minecraft.getMinecraft().getDefaultResourcePacks().add(dummyPack);
        ((SimpleReloadableResourceManager)Minecraft.getMinecraft().getResourceManager()).registerReloadListener(dummyPack);
        reloadPack();
    }

    private void reloadPack() {
        dummyPack.onResourceManagerReload(Minecraft.getMinecraft().getResourceManager());
        if (Minecraft.getMinecraft().world != null) {
            Minecraft.getMinecraft().entityRenderer.stopUseShader();
        }
    }

    /*@EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);

        // Add our dummy resourcepack
        ((SimpleReloadableResourceManager)Minecraft.getMinecraft().getResourceManager()).registerReloadListener(dummyPack);

        config = new Configuration(new File(event.getModConfigurationDirectory(), "blur.cfg"));
        saveConfig();
    }*/

    private void saveConfig() {

        /*blurExclusions = config.getStringList("guiExclusions", Configuration.CATEGORY_GENERAL, new String[] {
                GuiChat.class.getName(),
        }, "A list of classes to be excluded from the blur shader.");

        fadeTime = config.getInt("fadeTime", Configuration.CATEGORY_GENERAL, 200, 0, Integer.MAX_VALUE, "The time it takes for the blur to fade in, in ms.");

        int r = config.getInt("radius", Configuration.CATEGORY_GENERAL, 12, 1, 100, "The radius of the blur effect. This controls how \"strong\" the blur is.");
        if (r != radius) {
            radius = r;
            dummyPack.onResourceManagerReload(Minecraft.getMinecraft().getResourceManager());
            if (Minecraft.getMinecraft().theWorld != null) {
                Minecraft.getMinecraft().entityRenderer.stopUseShader();
            }
        }

        colorFirst = Integer.parseUnsignedInt(
                config.getString("gradientStartColor",  Configuration.CATEGORY_GENERAL, "75000000", "The start color of the background gradient. Given in ARGB hex."),
                16
        );

        colorSecond = Integer.parseUnsignedInt(
                config.getString("gradientEndColor",    Configuration.CATEGORY_GENERAL, "75000000", "The end color of the background gradient. Given in ARGB hex."),
                16
        );

        config.save();*/
    }

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
                er.loadShader(new ResourceLocation("shaders/post/fade_in_blur.json"));
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
                @SuppressWarnings("unchecked")
                List<Shader> shaders = sg.getListShaders();
                for (Shader s : shaders) {
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
}