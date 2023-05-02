package fz.frazionz.client.gui.buttons;

import fz.frazionz.FzClient;
import fz.frazionz.client.gui.utils.RoundedGradientShaderRenderer;
import fz.frazionz.client.gui.utils.RoundedShaderRenderer;
import net.minecraft.client.Minecraft;

public class GuiHoverButton extends GuiFzButton {
    protected int hoveredValue = 0;

    public GuiHoverButton(int buttonId, int x, int y, String displayString)
    {
        super(buttonId, x, y, 200, 20, displayString);
    }
    public GuiHoverButton(int buttonId, int x, int y, int width, int height, String displayString)
    {
        super(buttonId, x, y, width, height, displayString);
    }

    @Override
    protected int textColor() {
        return 0xFF311903;
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
            RoundedGradientShaderRenderer.getInstance().drawRoundRect(this.x, this.y, this.width, this.height, 2, GRADIENT_BUTTON_1, GRADIENT_BUTTON_2);
            if(drawString())
                FzClient.getInstance().getTTFFontRenderers().get(16).drawCenteredString(this.displayString, this.x + (this.width/2), this.y + (this.height / 2), textColor());
        }
    }


}
