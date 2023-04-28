package fz.frazionz.client.gui.buttons;

import fz.frazionz.FzClient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;

import java.awt.*;

public class GuiMenuButton extends GuiHoverButton
{

    private int fade;

    public GuiMenuButton(int buttonId, int x, int y, String buttonText)
    {
        super(buttonId, x, y, 180, 28, buttonText);
    }

    public GuiMenuButton(int buttonId, int x, int y, int width, int height, String buttonText)
    {
        super(buttonId, x, y, width, height, buttonText);
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
        return 0xFF311903;
    }
}
