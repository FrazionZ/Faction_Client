package fz.frazionz.gui.toasts;

import fz.frazionz.packets.server.SPacketToast;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.toasts.GuiToast;
import net.minecraft.client.gui.toasts.IToast;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class SuccessToast implements IToast {
    private final SPacketToast.Icon icon;

    private final String title;
    private final String subtitle;

    private static final ResourceLocation logoFZ = new ResourceLocation("textures/gui/title/background/fz_logo_flat_white.png");
    private static final ResourceLocation toastFZ = new ResourceLocation("textures/gui/frazionz/toasts.png");

    private long field_193662_f;
    private boolean hasPlayedSound;

    public static int BACKGROUND_COLOR = -15198184;
    public static int FIRST_GRADIENT_COLOR = -34304;
    public static int SECOND_GRADIENT_COLOR = -88063;

    public SuccessToast(SPacketToast.Icon icon, ITextComponent title, ITextComponent subtitle) {
        this.icon = icon;
        this.title = title.getUnformattedText();
        this.subtitle = subtitle.getUnformattedText();
    }

    public IToast.Visibility draw(GuiToast toastGui, long delta) {
        if (this.hasPlayedSound) {
            this.hasPlayedSound = false;
        }
        Minecraft mc = toastGui.getMinecraft();
        mc.getTextureManager().bindTexture(toastFZ);
        GlStateManager.color(1.0F, 1.0F, 1.0F);
        toastGui.drawTexturedModalRect(0, 0, 0, 32, 160, 32);
        if (this.icon != null) {
            mc.getTextureManager().bindTexture(toastFZ);
            GlStateManager.color(1.0F, 1.0F, 1.0F);
            toastGui.drawTexturedModalRect(this.icon.getxToast(), this.icon.getyToast(), this.icon.getxTexture(), this.icon.getyTexture(), this.icon.getW(), this.icon.getH());
        } else {
        	mc.getTextureManager().bindTexture(logoFZ);
            GuiToast.drawModalRectWithCustomSizedTexture(132, 5, 0.0F, 0.0F, 24, 24, 24.0F, 24.0F);
        }
        String titleSuccess = "Succès Obtenu !";

        String keySuccess = "";
        for(KeyBinding keyBinding : Minecraft.getMinecraft().gameSettings.keyBindings)
            if(keyBinding.getKeyDescription().equalsIgnoreCase("key.advancements"))
                keySuccess = GameSettings.getKeyDisplayString(keyBinding.getKeyCode());
        String touchToShowSuccess = "Appuyez sur ("+keySuccess+") pour afficher";
        mc.fontRenderer.drawScaleString(touchToShowSuccess, 4, 26, 0.7D, Color.WHITE);
        mc.fontRenderer.drawScaleString(titleSuccess, 6.0F, 4.0F, 1.2D, Color.ORANGE);
        if (delta < 2000L) {
        	mc.fontRenderer.drawScaleString(this.title, 6.0F, 14.0F, 0.8D, Color.WHITE);
        } else {
        	mc.fontRenderer.drawScaleString(this.subtitle, 6.0F, 14.0F, 0.8D, Color.WHITE);
        }
        return (delta >= 5000L) ? IToast.Visibility.HIDE : IToast.Visibility.SHOW;
    }
}
