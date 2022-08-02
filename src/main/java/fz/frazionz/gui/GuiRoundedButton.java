package fz.frazionz.gui;

import fz.frazionz.TTFFontRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;

public class GuiRoundedButton extends GuiButton {
	
	private boolean quit;
	private int hoverInt;
	private TTFFontRenderer fontrenderer;
	
	//default constructors
	public GuiRoundedButton(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText, boolean quit, TTFFontRenderer fontrenderer, int hoverInt) {
		super(buttonId, x, y, widthIn, heightIn, buttonText);
		this.quit = quit;
		this.fontrenderer = fontrenderer;
		this.hoverInt = hoverInt;
	}
	
	public GuiRoundedButton(int buttonId, int x, int y, String buttonText) {
		super(buttonId, x, y, buttonText);
	}
	
	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks)
    {
        if (this.visible)
        {
            
            if(this.hovered)
            	this.drawGradientRoundedButton(this.x-this.hoverInt, this.y-this.hoverInt, this.x + this.width+this.hoverInt, this.y + this.height+this.hoverInt, 0xFFFFFFFF, 0xFFFFFFFF);
            		
            //normal rectangle
            if(quit) 
            	this.drawRoundedButton(this.x, this.y, this.x + this.width, this.y + this.height, 0xFFCE0D00);
            else
                this.drawRoundedButton(this.x, this.y, this.x + this.width, this.y + this.height, this.COLOR_2);

            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            
            this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
            int i = this.getHoverState(this.hovered);
            
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            GlStateManager.blendFunc(770, 771);
            
            //default stuff from GuiButton.java
            this.mouseDragged(mc, mouseX, mouseY);
            
            fontrenderer.drawCenteredString(this.displayString, this.x + this.width / 2, this.y + (this.height -1) / 2, 0xFFFFFFFF);
        }
    }

}