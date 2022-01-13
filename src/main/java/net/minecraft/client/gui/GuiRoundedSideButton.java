package net.minecraft.client.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.FontUtils;

public class GuiRoundedSideButton extends GuiButton {
	
	private boolean quit;
	private int hoverInt;
	private fz.frazionz.gui.renderer.fonts.FontRenderer fontrenderer;
	private boolean isActive;
	private boolean isLeft;
	
	//default constructors
	public GuiRoundedSideButton(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText, boolean quit, fz.frazionz.gui.renderer.fonts.FontRenderer fontrenderer, int hoverInt, boolean isLeft, boolean isActive) {
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
	public void func_191745_a(Minecraft mc, int mouseX, int mouseY, float a)
    {
        if (this.visible)
        {
            
            if(this.hovered)
            	this.drawRoundedSideButton(this.xPosition-this.hoverInt, this.yPosition-this.hoverInt, this.xPosition + this.width+this.hoverInt, this.yPosition + this.height+this.hoverInt, 0xFFFFFFFF, 0xFFFFFFFF, this.isLeft);
            		
            //normal rectangle
            if(quit) 
            	this.drawRoundedSideButton(this.xPosition, this.yPosition, this.xPosition + this.width, this.yPosition + this.height, 0xFFCE0D00, 0xFFFF4C00, this.isLeft);
            else
                this.drawRoundedSideButton(this.xPosition, this.yPosition, this.xPosition + this.width, this.yPosition + this.height, this.isActive ? this.FIRST_GRADIENT_COLOR : 0xFF2A2A2A, this.isActive ? this.SECOND_GRADIENT_COLOR : 0xFF2A2A2A, this.isLeft);

            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            
            this.hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
            int i = this.getHoverState(this.hovered);
            
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            GlStateManager.blendFunc(770, 771);
            
            //default stuff from GuiButton.java
            this.mouseDragged(mc, mouseX, mouseY);
            
            fontrenderer.drawCenteredString(this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height -1) / 2, 0xFFFFFFFF);
        }
    }

}