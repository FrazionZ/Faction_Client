package fz.frazionz.gui.buttons;

import java.awt.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;

public class GuiFzButton extends GuiButton
{   
	
	private int fade;
	
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
            
            int i = 27 + this.getHoverState(this.hovered) * 30;
            this.drawModalRectWithCustomSizedTexture(this.x, this.y, 0, i, this.width/2, this.height/2, 512.0F, 512.0F);
            this.drawModalRectWithCustomSizedTexture(this.x, this.y + this.height/2, 0, i+(30-this.height/2), this.width/2, this.height/2, 512.0F, 512.0F);
            this.drawModalRectWithCustomSizedTexture(this.x + this.width / 2, this.y, 200-this.width/2, i, this.width/2, this.height/2, 512.0F, 512.0F);
            this.drawModalRectWithCustomSizedTexture(this.x + this.width / 2, this.y + this.height/2, 200-this.width/2, i+(30-this.height/2), this.width/2, this.height/2, 512.0F, 512.0F);
            
            this.mouseDragged(mc, mouseX, mouseY);

            mc.fzFontRenderers.get(16).drawCenteredString(displayString, this.x + this.width / 2, this.y + this.height/2, 0xFFFFFFFF);
        }
    }
}
