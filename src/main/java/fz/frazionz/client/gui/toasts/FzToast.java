package fz.frazionz.client.gui.toasts;

import java.awt.Color;

import fz.frazionz.packets.server.SPacketToast;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.toasts.GuiToast;
import net.minecraft.client.gui.toasts.IToast;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.SoundEvents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class FzToast implements IToast {
    private final SPacketToast.Type typeToast;

    private final SPacketToast.Icon icon;

    private String title;
    private String subtitle;
    private boolean hasPlayedSound;

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

    public IToast.Visibility draw(GuiToast toastGui, long delta) {
    	Minecraft mc = toastGui.getMinecraft();
        if (this.hasPlayedSound) {
            this.hasPlayedSound = false;
        }
        mc.getTextureManager().bindTexture(toastFZ);
        GlStateManager.color(1.0F, 1.0F, 1.0F);
        toastGui.drawTexturedModalRect(0, 0, 0, 0, 160, 32);
        mc.getTextureManager().bindTexture(logoFZ);
        GuiToast.drawModalRectWithCustomSizedTexture(this.icon.getxToast(), this.icon.getyToast(), 0.0F, 0.0F, this.icon.getW(), this.icon.getH(), this.icon.getW(), this.icon.getH());
        if (this.typeToast == SPacketToast.Type.SUCCESS) {
            String titleSuccess = I18n.format("Succd!", new Object[0]);
            mc.fontRenderer.drawScaleString(titleSuccess, 50.0F, 8.0F, 1.2000000476837158D, Color.ORANGE);
            if (delta < 2000L) {
            	mc.fontRenderer.drawScaleString(this.title, 50.0F, 20.0F, 0.8999999761581421D, Color.WHITE);
            } else {
            	mc.fontRenderer.drawScaleString(this.subtitle, 50.0F, 20.0F, 0.8999999761581421D, Color.WHITE);
            }
            if (!this.alreadyPlaySound && delta > 0L) {
                this.alreadyPlaySound = true;
                mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.getRecord(SoundEvents.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F));
            }
        } else if (this.typeToast == SPacketToast.Type.NORMAL) {
        	mc.fontRenderer.drawScaleString(this.title, 40.0F, 8.0F, 1.2000000476837158D, Color.ORANGE);
        	mc.fontRenderer.drawScaleString(this.subtitle, 40.0F, 20.0F, 0.8999999761581421D, Color.WHITE);
        }
        return (delta >= 5000L) ? IToast.Visibility.HIDE : IToast.Visibility.SHOW;
    }
}
