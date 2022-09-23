package fz.frazionz.client.gui.buttons;

import fz.frazionz.client.gui.utils.RoundedShaderRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

public class GuiHoverButton extends GuiButton {
    private int hoveredValue = 0;

    public GuiHoverButton(int buttonId, String displayString, int x, int y, int width, int height)
    {
        super(buttonId, x, y, width, height, displayString);
    }

    protected boolean drawString() {
        return true;
    }

    protected int buttonColor() {
        return 0x15171B;
    }

    protected int hoverColor() {
        return 0xFFFFFFFF;
    }

    protected int textColor() {
        return 0xFFFFFFFF;
    }

    public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks)
    {
        if (this.visible)
        {
        	this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
            if(this.hovered) {
                if(this.hovered && hoveredValue < 3)
                    hoveredValue += 1;
                else if(!this.hovered && hoveredValue > 0)
                    hoveredValue -= 1;

                RoundedShaderRenderer.getInstance().drawRoundRect(this.x - hoveredValue + 1, this.y - hoveredValue + 1, this.width+2*hoveredValue - 2, this.height + 2*hoveredValue - 2, 3.5f, hoverColor());
            }
            RoundedShaderRenderer.getInstance().drawRoundRect(this.x, this.y, this.width, this.height, 2, buttonColor());
            if(drawString())
                this.drawString(mc.fontRenderer, this.displayString, this.x + 8, this.y + (this.height / 2) - 3, textColor());
        }
    }
}
