package fz.frazionz.client.gui.buttons;

import fz.frazionz.FzClient;
import fz.frazionz.client.gui.utils.RoundedGradientShaderRenderer;
import fz.frazionz.client.gui.utils.RoundedShaderRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;

public class GuiFzChoiceButton extends GuiHoverButton
{
    private String info;

    public GuiFzChoiceButton(int buttonId, int x, int y, String buttonText, String choiceInfo)
    {
        super(buttonId, x, y, 200, 24, buttonText);
        this.info = choiceInfo;
    }

    public GuiFzChoiceButton(int buttonId, int x, int y, int width, int height, String buttonText, String choiceInfo)
    {
        super(buttonId, x, y, width, height, buttonText);
        this.info = choiceInfo;
    }
    /**
     * Draws this button to the screen.
     */
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

            RoundedShaderRenderer.getInstance().drawRoundRect(this.x, this.y, width, this.height, 2.0f, BLACK_2);
            RoundedGradientShaderRenderer.getInstance().drawRoundRect(this.x, this.y, 3*(width/5), this.height, 2.0f, GRADIENT_BUTTON_1, GRADIENT_BUTTON_2);

            this.mouseDragged(mc, mouseX, mouseY);

            FzClient.getInstance().getTTFFontRenderers().get(16).drawCenteredString(displayString, x + this.width/2 - (width/5), y + height/2, 0xFF311903);
            FzClient.getInstance().getTTFFontRenderers().get(16).drawCenteredString(info, x + 4*(width/5), y + height/2, 0xFFFFFFFF);
        }
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
