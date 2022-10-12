package fz.frazionz.client.gui.buttons;

import fz.frazionz.FzClient;
import fz.frazionz.client.gui.utils.RoundedShaderRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

public class GuiFzButton extends GuiButton
{
    private int backgroundColor = BLACK_3;
    private int textColor = 0xFFFFFFFF;
    private int hoverColor = 0xFFFFFFFF;

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
                FzClient.getInstance().getTTFFontRenderers().get(16).drawCenteredStringVertically(this.displayString, this.x + (this.width/2), this.y + (this.height / 2), textColor());
        }
    }

    protected boolean drawString() {
        return true;
    }

    protected int buttonColor() {
        return backgroundColor;
    }

    protected int hoverColor() {
        return hoverColor;
    }

    protected int textColor() {
        return textColor;
    }

    public GuiFzButton setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    public GuiFzButton setHoverColor(int hoverColor) {
        this.hoverColor = hoverColor;
        return this;
    }

    public GuiFzButton setTextColor(int textColor) {
        this.textColor = textColor;
        return this;
    }
}

