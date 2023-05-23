package fz.frazionz.client.gui.buttons;

import fz.frazionz.FzClient;
import fz.frazionz.client.gui.utils.RoundedGradientShaderRenderer;
import fz.frazionz.client.gui.utils.RoundedShaderRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;

import java.awt.*;

public class GuiMenuButton extends GuiHoverButton
{

    private int fade;
    private boolean active = true;

    private int color1 = Gui.GRADIENT_BUTTON_1;
    private int color2 = Gui.GRADIENT_BUTTON_2;
    private int textColor = 0xFF311903;

    public GuiMenuButton(int buttonId, int x, int y, String buttonText)
    {
        super(buttonId, x, y, 180, 28, buttonText);
    }

    public GuiMenuButton(int buttonId, int x, int y, int width, int height, String buttonText)
    {
        super(buttonId, x, y, width, height, buttonText);
    }

    public GuiMenuButton(int buttonId, int x, int y, int width, int height, String buttonText, int color1, int color2)
    {
        super(buttonId, x, y, width, height, buttonText);
        this.color1 = color1;
        this.color2 = color2;
    }

    public GuiMenuButton(int buttonId, int x, int y, int width, int height, String buttonText, int textColor, int color1, int color2)
    {
        super(buttonId, x, y, width, height, buttonText);
        this.textColor = textColor;
        this.color1 = color1;
        this.color2 = color2;
    }
    
    @Override
    protected int getHoverState(boolean mouseOver)
    {
        int i = 0;

        if (!this.enabled)
        {
            i = 1;
        }
        else if (mouseOver)
        {
            i = 2;
        }

        return i;
    }

    @Override
    protected int buttonColor() {
        return GRADIENT_BUTTON_1;
    }

    @Override
    protected int hoverColor() {
        return super.hoverColor();
    }

    @Override
    protected int textColor() {
        return textColor;
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks)
    {
        if (this.visible)
        {
            this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
            if(this.hovered && hoveredValue < 3)
                hoveredValue += 1;
            else if(!this.hovered && hoveredValue > 0)
                hoveredValue -= 1;
            if(hoveredValue > 0)
                RoundedShaderRenderer.getInstance().drawRoundRect(this.x - hoveredValue + 1, this.y - hoveredValue + 1, this.width+2*hoveredValue - 2, this.height + 2*hoveredValue - 2, 3.5f, hoverColor());
            if(active)
                RoundedGradientShaderRenderer.getInstance().drawRoundRect(this.x, this.y, this.width, this.height, 2, color1, color2);
            else
                RoundedGradientShaderRenderer.getInstance().drawRoundRect(this.x, this.y, this.width, this.height, 2, GRADIENT_BUTTON_1_INACTIVE, GRADIENT_BUTTON_2_INACTIVE);

            if(drawString())
                FzClient.getInstance().getTTFFontRenderers().get(18).drawCenteredString(this.displayString, this.x + (this.width/2), this.y + (this.height / 2), textColor());
        }
    }

    public GuiMenuButton active(boolean active) {
        this.active = active;
        return this;
    }
}
