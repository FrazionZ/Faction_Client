package fz.frazionz.gui.buttons;

import java.awt.Color;

import fz.frazionz.Client;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;

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
    public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks)
    {
        if (this.visible)
        {
            mc.getTextureManager().bindTexture(INTERFACE_BACKGROUND_2);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            
            if(this.hovered) {
                this.drawModalRectWithCustomSizedTexture(this.x, this.y, 0, 27, this.width/2, this.height/2, 512.0F, 512.0F);
                this.drawModalRectWithCustomSizedTexture(this.x, this.y + this.height/2, 0, 27+(30-this.height/2), this.width/2, this.height/2, 512.0F, 512.0F);
                this.drawModalRectWithCustomSizedTexture(this.x + this.width / 2, this.y, 200-this.width/2, 27, this.width/2, this.height/2, 512.0F, 512.0F);
                this.drawModalRectWithCustomSizedTexture(this.x + this.width / 2, this.y + this.height/2, 200-this.width/2, 27+(30-this.height/2), this.width/2, this.height/2, 512.0F, 512.0F);
                
            }
            this.mouseDragged(mc, mouseX, mouseY);

            Client.getInstance().getTTFFontRenderers().get(20).drawCenteredString(displayString, this.x + this.width / 2, this.y + this.height/2, 0xFFFFFFFF);
        }
    }
}
