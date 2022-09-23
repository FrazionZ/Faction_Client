package fz.frazionz.client.gui.buttons;

import fz.frazionz.Client;
import fz.frazionz.client.gui.utils.RoundedShaderRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;

public class GuiFzButton extends GuiButton
{   
	
	protected int hoveredValue;
	
    public GuiFzButton(int buttonId, int x, int y, String buttonText)
    {
        super(buttonId, x, y, 200, 20, buttonText);
    }

    public GuiFzButton(int buttonId, int x, int y, int width, int height, String buttonText)
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
    
    protected int getBackgroundColor() {
    	return BLACK_1;
    }
    
    protected boolean hasHover() {
    	return true;
    }
    
    /**
     * Draws this button to the screen.
     */
    public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks)
    {
        if (this.visible)
        {
            mc.getTextureManager().bindTexture(INTERFACE_BACKGROUND_2);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;

            if(this.hasHover()) {
                if(this.hovered && hoveredValue < 3)
                	hoveredValue += 1;
                else if(!this.hovered && hoveredValue > 0)
                	hoveredValue -= 1;
                
            	RoundedShaderRenderer.getInstance().drawRoundRect(this.x - hoveredValue + 1, this.y - hoveredValue + 1, this.width+2*hoveredValue - 2, this.height + 2*hoveredValue - 2, 3.5f, 0xFFFFFF);
            }
            RoundedShaderRenderer.getInstance().drawRoundRect(this.x, this.y, this.width, this.height, 2, getBackgroundColor());
            //this.drawModalRectWithCustomSizedTexture(this.x, this.y, 0, i, this.width/2, this.height/2, 512.0F, 512.0F);
            //this.drawModalRectWithCustomSizedTexture(this.x, this.y + this.height/2, 0, i+(30-this.height/2), this.width/2, this.height/2, 512.0F, 512.0F);
            //this.drawModalRectWithCustomSizedTexture(this.x + this.width / 2, this.y, 200-this.width/2, i, this.width/2, this.height/2, 512.0F, 512.0F);
            //this.drawModalRectWithCustomSizedTexture(this.x + this.width / 2, this.y + this.height/2, 200-this.width/2, i+(30-this.height/2), this.width/2, this.height/2, 512.0F, 512.0F);
            
            this.mouseDragged(mc, mouseX, mouseY);

            Client.getInstance().getTTFFontRenderers().get(16).drawCenteredString(displayString, this.x + this.width / 2, this.y + this.height/2, 0xFFFFFFFF);
        }
    }
}
