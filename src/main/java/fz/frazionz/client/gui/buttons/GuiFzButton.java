package fz.frazionz.client.gui.buttons;

import fz.frazionz.Client;
import fz.frazionz.client.gui.utils.RoundedShaderRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.init.SoundEvents;
import net.minecraft.resources.ResourceLocation;

public class GuiFzButton extends GuiButton
{
    public GuiFzButton(int buttonId, int x, int y, String buttonText)
    {
        this(buttonId, x, y, 200, 20, buttonText);
    }

    public GuiFzButton(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText)
    {
        super(buttonId, x, y, widthIn, heightIn, buttonText);
    }

    /**
     * Draws this button to the screen.
     */
    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks)
    {
        if (this.visible) {
            RoundedShaderRenderer.getInstance().drawRoundRect(this.x, this.y, this.width, this.height, 2, buttonColor());
            if(drawString())
                Client.getInstance().getTTFFontRenderers().get(16).drawCenteredStringVertically(this.displayString, this.x + (this.width/2), this.y + (this.height / 2), textColor());
        }
    }

    protected boolean drawString() {
        return true;
    }

    protected int buttonColor() {
        return BLACK_4;
    }

    protected int hoverColor() {
        return 0xFFFFFFFF;
    }

    protected int textColor() {
        return 0xFFFFFFFF;
    }
}

