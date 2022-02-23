package fz.frazionz.gui.toasts;

import fz.frazionz.packets.server.SPacketToast;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.toasts.GuiToast;
import net.minecraft.client.gui.toasts.IToast;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class SuccessToast implements IToast {
    private final SPacketToast.Icon icon;

    private final String title;

    private final String subtitle;

    private static final ResourceLocation logoFZ = new ResourceLocation("textures/gui/title/background/fz_logo_flat_white.png");

    private static final ResourceLocation toastFZ = new ResourceLocation("textures/gui/frazionz/toasts.png");

    private long field_193662_f;

    private boolean field_193663_g;

    public static int BACKGROUND_COLOR = -15198184;

    public static int FIRST_GRADIENT_COLOR = -34304;

    public static int SECOND_GRADIENT_COLOR = -88063;

    public SuccessToast(SPacketToast.Icon icon, ITextComponent title, ITextComponent subtitle) {
        this.icon = icon;
        this.title = title.getUnformattedText();
        this.subtitle = subtitle.getUnformattedText();
    }

    public IToast.Visibility func_193653_a(GuiToast fzGuiToast, long p_193653_2_) {
        if (this.field_193663_g) {
            this.field_193662_f = p_193653_2_;
            this.field_193663_g = false;
        }
        fzGuiToast.func_192989_b().getTextureManager().bindTexture(toastFZ);
        GlStateManager.color(1.0F, 1.0F, 1.0F);
        fzGuiToast.drawTexturedModalRect(0, 0, 0, 32, 160, 32);
        if (this.icon != null) {
            fzGuiToast.func_192989_b().getTextureManager().bindTexture(toastFZ);
            GlStateManager.color(1.0F, 1.0F, 1.0F);
            fzGuiToast.drawTexturedModalRect(this.icon.getxToast(), this.icon.getyToast(), this.icon.getxTexture(), this.icon.getyTexture(), this.icon.getW(), this.icon.getH());
        } else {
            fzGuiToast.func_192989_b().getTextureManager().bindTexture(logoFZ);
            GuiToast.drawModalRectWithCustomSizedTexture(132, 5, 0.0F, 0.0F, 24, 24, 24.0F, 24.0F);
        }
        String titleSuccess = "Succès Obtenu !";

        String keySuccess = "";
        for(KeyBinding keyBinding : Minecraft.getMinecraft().gameSettings.keyBindings)
            if(keyBinding.getKeyDescription().equalsIgnoreCase("key.advancements"))
                keySuccess = GameSettings.getKeyDisplayString(keyBinding.getKeyCode());
        String touchToShowSuccess = "Appuyez sur ("+keySuccess+") pour afficher";
        Minecraft.fontRendererObj.drawScaleString(touchToShowSuccess, 4, 26, 0.7D, Color.WHITE);
        Minecraft.fontRendererObj.drawScaleString(titleSuccess, 6.0F, 4.0F, 1.2D, Color.ORANGE);
        if (p_193653_2_ < 2000L) {
            fzGuiToast.func_192989_b();
            Minecraft.fontRendererObj.drawScaleString(this.title, 6.0F, 14.0F, 0.8D, Color.WHITE);
        } else {
            fzGuiToast.func_192989_b();
            Minecraft.fontRendererObj.drawScaleString(this.subtitle, 6.0F, 14.0F, 0.8D, Color.WHITE);
        }
        return (p_193653_2_ >= 5000L) ? IToast.Visibility.HIDE : IToast.Visibility.SHOW;
    }
}
