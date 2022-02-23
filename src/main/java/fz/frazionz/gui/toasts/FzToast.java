package fz.frazionz.gui.toasts;

import fz.frazionz.packets.server.SPacketToast;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.toasts.GuiToast;
import net.minecraft.client.gui.toasts.IToast;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class FzToast implements IToast {
    private final SPacketToast.Type typeToast;

    private final SPacketToast.Icon icon;

    private String title;

    private String subtitle;

    private boolean field_193663_g;

    public static int FIRST_GRADIENT_COLOR = -34304;

    public static int SECOND_GRADIENT_COLOR = -88063;

    private static final ResourceLocation logoFZ = new ResourceLocation("textures/gui/title/background/fz_logo_flat_white.png");

    private static final ResourceLocation toastFZ = new ResourceLocation("textures/gui/frazionz/toasts.png");

    private boolean alreadyPlaySound = false;

    public FzToast(SPacketToast.Type typeToast, SPacketToast.Icon icon, ITextComponent p_i47488_2_, ITextComponent p_i47488_3_) {
        this.typeToast = typeToast;
        this.icon = icon;
        this.title = p_i47488_2_.getUnformattedText();
        this.subtitle = p_i47488_3_.getUnformattedText();
    }

    public IToast.Visibility func_193653_a(GuiToast p_193653_1_, long p_193653_2_) {
        if (this.field_193663_g) {
            this.field_193663_g = false;
        }
        p_193653_1_.func_192989_b().getTextureManager().bindTexture(toastFZ);
        GlStateManager.color(1.0F, 1.0F, 1.0F);
        p_193653_1_.drawTexturedModalRect(0, 0, 0, 0, 160, 32);
        p_193653_1_.func_192989_b().getTextureManager().bindTexture(logoFZ);
        GuiToast.drawModalRectWithCustomSizedTexture(this.icon.getxToast(), this.icon.getyToast(), 0.0F, 0.0F, this.icon.getW(), this.icon.getH(), this.icon.getW(), this.icon.getH());
        if (this.typeToast == SPacketToast.Type.SUCCESS) {
            String titleSuccess = I18n.format("Succd!", new Object[0]);
            p_193653_1_.func_192989_b();
            Minecraft.fontRendererObj.drawScaleString(titleSuccess, 50.0F, 8.0F, 1.2000000476837158D, Color.ORANGE);
            if (p_193653_2_ < 2000L) {
                p_193653_1_.func_192989_b();
                Minecraft.fontRendererObj.drawScaleString(this.title, 50.0F, 20.0F, 0.8999999761581421D, Color.WHITE);
            } else {
                p_193653_1_.func_192989_b();
                Minecraft.fontRendererObj.drawScaleString(this.subtitle, 50.0F, 20.0F, 0.8999999761581421D, Color.WHITE);
            }
            if (!this.alreadyPlaySound && p_193653_2_ > 0L) {
                this.alreadyPlaySound = true;
                p_193653_1_.func_192989_b().getSoundHandler().playSound((ISound)PositionedSoundRecord.func_194007_a(SoundEvents.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F));
            }
        } else if (this.typeToast == SPacketToast.Type.NORMAL) {
            p_193653_1_.func_192989_b();
            Minecraft.fontRendererObj.drawScaleString(this.title, 40.0F, 8.0F, 1.2000000476837158D, Color.ORANGE);
            p_193653_1_.func_192989_b();
            Minecraft.fontRendererObj.drawScaleString(this.subtitle, 40.0F, 20.0F, 0.8999999761581421D, Color.WHITE);
        }
        return (p_193653_2_ >= 5000L) ? IToast.Visibility.HIDE : IToast.Visibility.SHOW;
    }
}
