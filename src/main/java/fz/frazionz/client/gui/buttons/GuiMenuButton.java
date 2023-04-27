package fz.frazionz.client.gui.buttons;

import fz.frazionz.FzClient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;

import java.awt.*;

public class GuiMenuButton extends GuiButton
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
    
    /**
     * Draws this button to the screen.
     */
    public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        if(this.visible) {
            this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
            Gui.drawRect(this.x, this.y, this.x + this.width, this.y + this.height, this.hovered ? 0xFFFFFFFF : 0x00000000);
            Gui.drawGradientRectLeftToRight(this.x+1, this.y+1, this.x + this.width-1, this.y + this.height-1, GRADIENT_BUTTON_1, GRADIENT_BUTTON_2);
            FzClient.getInstance().getTTFFontRenderers().get(20).drawCenteredString(displayString, this.x + this.width / 2, this.y + this.height/2, 0xFF311903);
            this.mouseDragged(mc, mouseX, mouseY);
        }
    }
}
