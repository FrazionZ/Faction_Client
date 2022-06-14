package fz.frazionz.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;

public class GuiRoundedSideButton extends GuiButton {
	
	private boolean quit;
	private int hoverInt;
	private fz.frazionz.gui.renderer.fonts.FzFontRenderer fontrenderer;
	private boolean isActive;
	private boolean isLeft;
	
	//default constructors
	public GuiRoundedSideButton(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText, boolean quit, fz.frazionz.gui.renderer.fonts.FzFontRenderer fontrenderer, int hoverInt, boolean isLeft, boolean isActive) {
		super(buttonId, x, y, widthIn, heightIn, buttonText);
		this.quit = quit;
		this.fontrenderer = fontrenderer;
		this.hoverInt = hoverInt;
		this.isLeft = isLeft;
		this.isActive = isActive;
	}
	
	public GuiRoundedSideButton(int buttonId, int x, int y, String buttonText) {
		super(buttonId, x, y, buttonText);
	}
	
	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks)
    {
        if (this.visible)
        {
            
            if(this.hovered)
            	this.drawRoundedSideButton(this.x-this.hoverInt, this.y-this.hoverInt, this.x + this.width+this.hoverInt, this.y + this.height+this.hoverInt, 0xFFFFFFFF, 0xFFFFFFFF, this.isLeft);
            		
            //normal rectangle
            if(quit) 
            	this.drawRoundedSideButton(this.x, this.y, this.x + this.width, this.y + this.height, 0xFFCE0D00, 0xFFFF4C00, this.isLeft);
            else
                this.drawRoundedSideButton(this.x, this.y, this.x + this.width, this.y + this.height, this.isActive ? this.COLOR_2 : 0xFF2A2A2A, this.isActive ? this.COLOR_3 : 0xFF2A2A2A, this.isLeft);

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