package net.minecraft.client.gui;

import java.awt.Color;
import java.awt.Font;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.FontUtils;

public class GuiButtonAnimated extends GuiButton {

	public static FontUtils font = new FontUtils("Arial", Font.BOLD, 20);
	
	//default constructors
	public GuiButtonAnimated(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText) {
		super(buttonId, x, y, widthIn, heightIn, buttonText);
	}
	
	public GuiButtonAnimated(int buttonId, int x, int y, String buttonText) {
		super(buttonId, x, y, buttonText);
	}
	
	//this stores the height of the animated rectangle we are drawing
	int animatedWidth = 0;
	
	@Override
	public void func_191745_a(Minecraft mc, int mouseX, int mouseY, float a)
    {
        if (this.visible)
        {
            FontRenderer fontrenderer = mc.fontRendererObj;
            
            if(this.hovered) {
            	animatedWidth += 8;
            	if(animatedWidth > this.width) {
            		animatedWidth = width;
            	}
            }
            else {
            	animatedWidth -= 8;
            	if(animatedWidth < 0) {
            		animatedWidth = 0;
            	}
            }
              
            
            //normal rectangle
            this.drawRekt(this.xPosition, this.yPosition, this.xPosition + this.width, this.yPosition + this.height, 0xFFFFAA00);
            
            
            //animated one
            this.drawRekt(this.xPosition, this.yPosition, this.xPosition + this.animatedWidth, this.yPosition + this.height, 0xFFF29900);
            
            this.drawRekt(this.xPosition, this.yPosition, this.xPosition + 6, this.yPosition + this.height, 0xFFBC6100);
            this.drawRekt(this.xPosition + this.width - 6, this.yPosition, this.xPosition + this.width, this.yPosition + this.height, 0xFFBC6100);
            
            //0xFFBC6100
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            
            this.hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
            int i = this.getHoverState(this.hovered);
            
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            GlStateManager.blendFunc(770, 771);
            
            //default stuff from GuiButton.java
            this.mouseDragged(mc, mouseX, mouseY);
            int j = 14737632;

            if (!this.enabled)
            {
                j = 10526880;
            }
            else if (this.hovered)
            {
                j = 16777120;
            }
            
            this.drawCenteredString(fontrenderer, this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, 0xFFFFFFFF);
        }
    }

}