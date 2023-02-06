package net.minecraft.client.gui;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;

import org.lwjgl.opengl.GL11;

import fz.frazionz.TTFFontRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.resources.ResourceLocation;

public class Gui
{
	
	public static final int BLACK_1 = 0xFF24262B;
	public static final int BLACK_2 = 0xFF1C1F23;
	public static final int BLACK_3 = 0xFF15171B;
	public static final int BLACK_4 = 0xFF0E1014;
	
	public static final int COLOR_1 = 0xFFF1B53F;
	public static final int COLOR_2 = 0xFFE48515;
	public static final int COLOR_3 = 0xFFDF5D14;
	
    public static final ResourceLocation OPTIONS_BACKGROUND = new ResourceLocation("textures/gui/options_background.png");
    public static final ResourceLocation FZ_OPTIONS_BACKGROUND = new ResourceLocation("textures/gui/title/background/fz_background.png");
    public static final ResourceLocation INTERFACE_BACKGROUND_1 = new ResourceLocation("textures/gui/frazionz/interface_background.png");
    public static final ResourceLocation INTERFACE_BACKGROUND_2 = new ResourceLocation("textures/gui/frazionz/interface_background_2.png");

    public static final ResourceLocation STAT_ICONS = new ResourceLocation("textures/gui/container/stats_icons.png");
    public static final ResourceLocation ICONS = new ResourceLocation("textures/gui/icons.png");
    protected static float zLevel;

    /**
     * Draws a thin horizontal line between two points.
     */
    protected void drawHorizontalLine(int startX, int endX, int y, int color)
    {
        if (endX < startX)
        {
            int i = startX;
            startX = endX;
            endX = i;
        }

        drawRect(startX, y, endX + 1, y + 1, color);
    }

    /**
     * Draw a 1 pixel wide vertical line. Args : x, y1, y2, color
     */
    protected void drawVerticalLine(int x, int startY, int endY, int color)
    {
        if (endY < startY)
        {
            int i = startY;
            startY = endY;
            endY = i;
        }

        drawRect(x, startY + 1, x + 1, endY, color);
    }

    /**
     * Draws a solid color rectangle with the specified coordinates and color.
     */
    public static void drawRect(int left, int top, int right, int bottom, int color)
    {
        if (left < right)
        {
            int i = left;
            left = right;
            right = i;
        }

        if (top < bottom)
        {
            int j = top;
            top = bottom;
            bottom = j;
        }

        float f3 = (float)(color >> 24 & 255) / 255.0F;
        float f = (float)(color >> 16 & 255) / 255.0F;
        float f1 = (float)(color >> 8 & 255) / 255.0F;
        float f2 = (float)(color & 255) / 255.0F;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.color(f, f1, f2, f3);
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION);
        bufferbuilder.pos((double)left, (double)bottom, 0.0D).endVertex();
        bufferbuilder.pos((double)right, (double)bottom, 0.0D).endVertex();
        bufferbuilder.pos((double)right, (double)top, 0.0D).endVertex();
        bufferbuilder.pos((double)left, (double)top, 0.0D).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }
    
    public static void drawGradientRoundedButton(int left, int top, int right, int bottom, int startColor, int endColor)
    {
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
    	int radiusF = bottom - top;
    	if(radiusF%2==1) {
    		radiusF+=1;
    		top-=1;
    	}
    	radiusF /= 2;
    	
    	int radius = (int) radiusF;
    	
        float f = (float)(startColor >> 24 & 255) / 255.0F;
        float f1 = (float)(startColor >> 16 & 255) / 255.0F;
        float f2 = (float)(startColor >> 8 & 255) / 255.0F;
        float f3 = (float)(startColor & 255) / 255.0F;
        float f4 = (float)(endColor >> 24 & 255) / 255.0F;
        float f5 = (float)(endColor >> 16 & 255) / 255.0F;
        float f6 = (float)(endColor >> 8 & 255) / 255.0F;
        float f7 = (float)(endColor & 255) / 255.0F;
    	
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
    	bufferbuilder.begin(6, DefaultVertexFormats.POSITION);
    	GlStateManager.color(f1, f2, f3, f);
        for (int i = (int) (0 / 360.0 * 100); i <= (int) (360 / 360.0 * 100); i++) {
            double angle = (Math.PI * 2 * i / 100) + Math.toRadians(180);
            bufferbuilder.pos((double)(left + Math.sin(angle) * radius), (double)(bottom-radius + Math.cos(angle) * radius), 0.0D).endVertex();
        }
        tessellator.draw();
        
        bufferbuilder.begin(6, DefaultVertexFormats.POSITION);
        GlStateManager.color(f5, f6, f7, f4);
        for (int i = (int) (0 / 360.0 * 100); i <= (int) (360 / 360.0 * 100); i++) {
            double angle = (Math.PI * 2 * i / 100) + Math.toRadians(180);
            bufferbuilder.pos((double)(right + Math.sin(angle) * radius), (double)(bottom-radius + Math.cos(angle) * radius), 0.0D).endVertex();
        }
        
        tessellator.draw();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    	
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel(7425);
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos((double)right, (double)top, (double)zLevel).color(f5, f6, f7, f4).endVertex();
        bufferbuilder.pos((double)left, (double)top, (double)zLevel).color(f1, f2, f3, f).endVertex();
        bufferbuilder.pos((double)left, (double)bottom, (double)zLevel).color(f1, f2, f3, f).endVertex();
        bufferbuilder.pos((double)right, (double)bottom, (double)zLevel).color(f5, f6, f7, f4).endVertex();
        tessellator.draw();
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableTexture2D();
    }
    
    public static void drawRoundedButton(int left, int top, int right, int bottom, int color)
    {
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
    	int radiusF = bottom - top;
    	if(radiusF%2==1) {
    		radiusF+=1;
    		top-=1;
    	}
    	radiusF /= 2;
    	
    	int radius = (int) radiusF;
    	
        float f = (float)(color >> 24 & 255) / 255.0F;
        float f1 = (float)(color >> 16 & 255) / 255.0F;
        float f2 = (float)(color >> 8 & 255) / 255.0F;
        float f3 = (float)(color & 255) / 255.0F;
    	
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
    	bufferbuilder.begin(6, DefaultVertexFormats.POSITION);
    	GlStateManager.color(f1, f2, f3, f);
        for (int i = (int) (0 / 360.0 * 100); i <= (int) (360 / 360.0 * 100); i++) {
            double angle = (Math.PI * 2 * i / 100) + Math.toRadians(180);
            bufferbuilder.pos((double)(left + Math.sin(angle) * radius), (double)(bottom-radius + Math.cos(angle) * radius), 0.0D).endVertex();
        }
        tessellator.draw();
        
        bufferbuilder.begin(6, DefaultVertexFormats.POSITION);
        GlStateManager.color(f1, f2, f3, f);
        for (int i = (int) (0 / 360.0 * 100); i <= (int) (360 / 360.0 * 100); i++) {
            double angle = (Math.PI * 2 * i / 100) + Math.toRadians(180);
            bufferbuilder.pos((double)(right + Math.sin(angle) * radius), (double)(bottom-radius + Math.cos(angle) * radius), 0.0D).endVertex();
        }
        
        tessellator.draw();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    	
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos((double)right, (double)top, (double)zLevel).color(f1, f2, f3, f).endVertex();
        bufferbuilder.pos((double)left, (double)top, (double)zLevel).color(f1, f2, f3, f).endVertex();
        bufferbuilder.pos((double)left, (double)bottom, (double)zLevel).color(f1, f2, f3, f).endVertex();
        bufferbuilder.pos((double)right, (double)bottom, (double)zLevel).color(f1, f2, f3, f).endVertex();
        tessellator.draw();
        GlStateManager.disableBlend();
        GlStateManager.enableTexture2D();
    }
    
    public static void drawRoundedSideButton(int left, int top, int right, int bottom, int startColor, int endColor, boolean isLeft)
    {
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
    	int radiusF = bottom - top;
    	if(radiusF%2==1) {
    		radiusF+=1;
    		top-=1;
    	}
    	radiusF /= 2;
    	
    	int radius = (int) radiusF;
    	
        float f = (float)(startColor >> 24 & 255) / 255.0F;
        float f1 = (float)(startColor >> 16 & 255) / 255.0F;
        float f2 = (float)(startColor >> 8 & 255) / 255.0F;
        float f3 = (float)(startColor & 255) / 255.0F;
        float f4 = (float)(endColor >> 24 & 255) / 255.0F;
        float f5 = (float)(endColor >> 16 & 255) / 255.0F;
        float f6 = (float)(endColor >> 8 & 255) / 255.0F;
        float f7 = (float)(endColor & 255) / 255.0F;
    	
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
    	if(isLeft) {
	        bufferbuilder.begin(6, DefaultVertexFormats.POSITION);
	        GlStateManager.color(f5, f6, f7, f4);
	        for (int i = (int) (0 / 360.0 * 100); i <= (int) (360 / 360.0 * 100); i++) {
	            double angle = (Math.PI * 2 * i / 100) + Math.toRadians(180);
	            bufferbuilder.pos((double)(right + Math.sin(angle) * radius), (double)(bottom-radius + Math.cos(angle) * radius), 0.0D).endVertex();
	        }
	        tessellator.draw();
    	}
    	else {
    		bufferbuilder.begin(6, DefaultVertexFormats.POSITION);
	    	GlStateManager.color(f1, f2, f3, f);
	        for (int i = (int) (0 / 360.0 * 100); i <= (int) (360 / 360.0 * 100); i++) {
	            double angle = (Math.PI * 2 * i / 100) + Math.toRadians(180);
	            bufferbuilder.pos((double)(left + Math.sin(angle) * radius), (double)(bottom-radius + Math.cos(angle) * radius), 0.0D).endVertex();
	        }
	        tessellator.draw();
    	}
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    	
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos((double)right, (double)top, (double)zLevel).color(f5, f6, f7, f4).endVertex();
        bufferbuilder.pos((double)left, (double)top, (double)zLevel).color(f1, f2, f3, f).endVertex();
        bufferbuilder.pos((double)left, (double)bottom, (double)zLevel).color(f1, f2, f3, f).endVertex();
        bufferbuilder.pos((double)right, (double)bottom, (double)zLevel).color(f5, f6, f7, f4).endVertex();
        tessellator.draw();
        GlStateManager.disableBlend();
        GlStateManager.enableTexture2D();
    }
    
    public static void drawRoundedRect(int left, int top, int right, int bottom, int color, int radius)
    {
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
    	if(radius%2==1) {
    		radius+=1;
    		top-=1;
    	}
    	
        float f = (float)(color >> 24 & 255) / 255.0F;
        float f1 = (float)(color >> 16 & 255) / 255.0F;
        float f2 = (float)(color >> 8 & 255) / 255.0F;
        float f3 = (float)(color & 255) / 255.0F;
    	
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
    	bufferbuilder.begin(6, DefaultVertexFormats.POSITION);
    	GlStateManager.color(f1, f2, f3, f);
        for (int i = (int) (0 / 360.0 * 100); i <= (int) (360 / 360.0 * 100); i++) {
            double angle = (Math.PI * 2 * i / 100) + Math.toRadians(180);
            bufferbuilder.pos((double)(left+radius + Math.sin(angle) * radius), (double)(top+radius + Math.cos(angle) * radius), 0.0D).endVertex();
        }
        tessellator.draw();
        bufferbuilder.begin(6, DefaultVertexFormats.POSITION);
        GlStateManager.color(f1, f2, f3, f);
        for (int i = (int) (0 / 360.0 * 100); i <= (int) (360 / 360.0 * 100); i++) {
            double angle = (Math.PI * 2 * i / 100) + Math.toRadians(180);
            bufferbuilder.pos((double)(left+radius + Math.sin(angle) * radius), (double)(bottom-radius + Math.cos(angle) * radius), 0.0D).endVertex();
        }
        tessellator.draw();
        bufferbuilder.begin(6, DefaultVertexFormats.POSITION);
        GlStateManager.color(f1, f2, f3, f);
        for (int i = (int) (0 / 360.0 * 100); i <= (int) (360 / 360.0 * 100); i++) {
            double angle = (Math.PI * 2 * i / 100) + Math.toRadians(180);
            bufferbuilder.pos((double)(right-radius + Math.sin(angle) * radius), (double)(bottom-radius + Math.cos(angle) * radius), 0.0D).endVertex();
        }
        tessellator.draw();
        bufferbuilder.begin(6, DefaultVertexFormats.POSITION);
        GlStateManager.color(f1, f2, f3, f);
        for (int i = (int) (0 / 360.0 * 100); i <= (int) (360 / 360.0 * 100); i++) {
            double angle = (Math.PI * 2 * i / 100) + Math.toRadians(180);
            bufferbuilder.pos((double)(right-radius + Math.sin(angle) * radius), (double)(top+radius + Math.cos(angle) * radius), 0.0D).endVertex();
        }
        tessellator.draw();
        
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    	
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos((double)right-radius, (double)top, (double)zLevel).color(f1, f2, f3, f).endVertex();
        bufferbuilder.pos((double)left+radius, (double)top, (double)zLevel).color(f1, f2, f3, f).endVertex();
        bufferbuilder.pos((double)left+radius, (double)bottom, (double)zLevel).color(f1, f2, f3, f).endVertex();
        bufferbuilder.pos((double)right-radius, (double)bottom, (double)zLevel).color(f1, f2, f3, f).endVertex();
        tessellator.draw();
        
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos((double)right, (double)top+radius, (double)zLevel).color(f1, f2, f3, f).endVertex();
        bufferbuilder.pos((double)left, (double)top+radius, (double)zLevel).color(f1, f2, f3, f).endVertex();
        bufferbuilder.pos((double)left, (double)bottom-radius, (double)zLevel).color(f1, f2, f3, f).endVertex();
        bufferbuilder.pos((double)right, (double)bottom-radius, (double)zLevel).color(f1, f2, f3, f).endVertex();
        tessellator.draw();
        GlStateManager.disableBlend();
        GlStateManager.enableTexture2D();
    }
    
    public static void drawRoundedRectWithContent(int left, int top, int width, int height, int color, int radius, TTFFontRenderer fontrenderer, String content)
    {
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
    	if(radius%2==1) {
    		radius+=1;
    		top-=1;
    	}
    	
    	int right = left+width;
    	int bottom = top+height;
    	
        float f = (float)(color >> 24 & 255) / 255.0F;
        float f1 = (float)(color >> 16 & 255) / 255.0F;
        float f2 = (float)(color >> 8 & 255) / 255.0F;
        float f3 = (float)(color & 255) / 255.0F;
    	
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
    	bufferbuilder.begin(6, DefaultVertexFormats.POSITION);
    	GlStateManager.color(f1, f2, f3, f);
        for (int i = (int) (0 / 360.0 * 100); i <= (int) (360 / 360.0 * 100); i++) {
            double angle = (Math.PI * 2 * i / 100) + Math.toRadians(180);
            bufferbuilder.pos((double)(left+radius + Math.sin(angle) * radius), (double)(top+radius + Math.cos(angle) * radius), 0.0D).endVertex();
        }
        tessellator.draw();
        bufferbuilder.begin(6, DefaultVertexFormats.POSITION);
        GlStateManager.color(f1, f2, f3, f);
        for (int i = (int) (0 / 360.0 * 100); i <= (int) (360 / 360.0 * 100); i++) {
            double angle = (Math.PI * 2 * i / 100) + Math.toRadians(180);
            bufferbuilder.pos((double)(left+radius + Math.sin(angle) * radius), (double)(bottom-radius + Math.cos(angle) * radius), 0.0D).endVertex();
        }
        tessellator.draw();
        bufferbuilder.begin(6, DefaultVertexFormats.POSITION);
        GlStateManager.color(f1, f2, f3, f);
        for (int i = (int) (0 / 360.0 * 100); i <= (int) (360 / 360.0 * 100); i++) {
            double angle = (Math.PI * 2 * i / 100) + Math.toRadians(180);
            bufferbuilder.pos((double)(right-radius + Math.sin(angle) * radius), (double)(bottom-radius + Math.cos(angle) * radius), 0.0D).endVertex();
        }
        tessellator.draw();
        bufferbuilder.begin(6, DefaultVertexFormats.POSITION);
        GlStateManager.color(f1, f2, f3, f);
        for (int i = (int) (0 / 360.0 * 100); i <= (int) (360 / 360.0 * 100); i++) {
            double angle = (Math.PI * 2 * i / 100) + Math.toRadians(180);
            bufferbuilder.pos((double)(right-radius + Math.sin(angle) * radius), (double)(top+radius + Math.cos(angle) * radius), 0.0D).endVertex();
        }
        tessellator.draw();
        
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    	
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos((double)right-radius, (double)top, (double)zLevel).color(f1, f2, f3, f).endVertex();
        bufferbuilder.pos((double)left+radius, (double)top, (double)zLevel).color(f1, f2, f3, f).endVertex();
        bufferbuilder.pos((double)left+radius, (double)bottom, (double)zLevel).color(f1, f2, f3, f).endVertex();
        bufferbuilder.pos((double)right-radius, (double)bottom, (double)zLevel).color(f1, f2, f3, f).endVertex();
        tessellator.draw();
        
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos((double)right, (double)top+radius, (double)zLevel).color(f1, f2, f3, f).endVertex();
        bufferbuilder.pos((double)left, (double)top+radius, (double)zLevel).color(f1, f2, f3, f).endVertex();
        bufferbuilder.pos((double)left, (double)bottom-radius, (double)zLevel).color(f1, f2, f3, f).endVertex();
        bufferbuilder.pos((double)right, (double)bottom-radius, (double)zLevel).color(f1, f2, f3, f).endVertex();
        tessellator.draw();
        GlStateManager.disableBlend();
        GlStateManager.enableTexture2D();
        
        fontrenderer.drawCenteredString(content, left + (right-left) / 2, top + (bottom-top -1) / 2, 0xFFFFFFFF);
    }
    
    public static void drawRoundedPlayerHead(int left, int top, int width, int height, int radius, String username)
    {    	
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
    	if(radius%2==1) {
    		radius+=1;
    		top-=1;
    	}
    	
    	int right = left+width;
    	int bottom = top+height;
    	
        float f = 1f;
        float f1 = 1f;
        float f2 = 1f;
        float f3 = 1f;
    	
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
    	bufferbuilder.begin(6, DefaultVertexFormats.POSITION);
    	GlStateManager.color(f1, f2, f3, f);
        for (int i = (int) (0 / 360.0 * 100); i <= (int) (360 / 360.0 * 100); i++) {
            double angle = (Math.PI * 2 * i / 100) + Math.toRadians(180);
            bufferbuilder.pos((double)(left+radius + Math.sin(angle) * radius), (double)(top+radius + Math.cos(angle) * radius), 0.0D).endVertex();
        }
        tessellator.draw();
        bufferbuilder.begin(6, DefaultVertexFormats.POSITION);
        GlStateManager.color(f1, f2, f3, f);
        for (int i = (int) (0 / 360.0 * 100); i <= (int) (360 / 360.0 * 100); i++) {
            double angle = (Math.PI * 2 * i / 100) + Math.toRadians(180);
            bufferbuilder.pos((double)(left+radius + Math.sin(angle) * radius), (double)(bottom-radius + Math.cos(angle) * radius), 0.0D).endVertex();
        }
        tessellator.draw();
        bufferbuilder.begin(6, DefaultVertexFormats.POSITION);
        GlStateManager.color(f1, f2, f3, f);
        for (int i = (int) (0 / 360.0 * 100); i <= (int) (360 / 360.0 * 100); i++) {
            double angle = (Math.PI * 2 * i / 100) + Math.toRadians(180);
            bufferbuilder.pos((double)(right-radius + Math.sin(angle) * radius), (double)(bottom-radius + Math.cos(angle) * radius), 0.0D).endVertex();
        }
        tessellator.draw();
        bufferbuilder.begin(6, DefaultVertexFormats.POSITION);
        GlStateManager.color(f1, f2, f3, f);
        for (int i = (int) (0 / 360.0 * 100); i <= (int) (360 / 360.0 * 100); i++) {
            double angle = (Math.PI * 2 * i / 100) + Math.toRadians(180);
            bufferbuilder.pos((double)(right-radius + Math.sin(angle) * radius), (double)(top+radius + Math.cos(angle) * radius), 0.0D).endVertex();
        }
        tessellator.draw();
        
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    	
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel(7425);
        
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos((double)right-radius, (double)top, (double)zLevel).color(f1, f2, f3, f).endVertex();
        bufferbuilder.pos((double)left+radius, (double)top, (double)zLevel).color(f1, f2, f3, f).endVertex();
        bufferbuilder.pos((double)left+radius, (double)bottom, (double)zLevel).color(f1, f2, f3, f).endVertex();
        bufferbuilder.pos((double)right-radius, (double)bottom, (double)zLevel).color(f1, f2, f3, f).endVertex();
        tessellator.draw();
        
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos((double)right, (double)top+radius, (double)zLevel).color(f1, f2, f3, f).endVertex();
        bufferbuilder.pos((double)left, (double)top+radius, (double)zLevel).color(f1, f2, f3, f).endVertex();
        bufferbuilder.pos((double)left, (double)bottom-radius, (double)zLevel).color(f1, f2, f3, f).endVertex();
        bufferbuilder.pos((double)right, (double)bottom-radius, (double)zLevel).color(f1, f2, f3, f).endVertex();
        tessellator.draw();
        
        
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
        
        GL11.glPushMatrix();
        
        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("frazionz/cache/skins/" + username));
        GL11.glScalef(width/width, height/width, 1f);
        bufferbuilder.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);
        bufferbuilder.pos((double)(left + 0), (double)(top + width), (double)zLevel).tex((double)((float)(32 + 0) * 0.00390625F), (double)((float)(32 + 32) * 0.00390625F)).color(1f, 1f, 1f, 1f).endVertex();
        bufferbuilder.pos((double)(left + width), (double)(top + width), (double)zLevel).tex((double)((float)(32 + 32) * 0.00390625F), (double)((float)(32 + 32) * 0.00390625F)).color(1f, 1f, 1f, 1f).endVertex();
        bufferbuilder.pos((double)(left + width), (double)(top + 0), (double)zLevel).tex((double)((float)(32 + 32) * 0.00390625F), (double)((float)(32 + 0) * 0.00390625F)).color(1f, 1f, 1f, 1f).endVertex();
        bufferbuilder.pos((double)(left + 0), (double)(top + 0), (double)zLevel).tex((double)((float)(32 + 0) * 0.00390625F), (double)((float)(32 + 0) * 0.00390625F)).color(1f, 1f, 1f, 1f).endVertex();
        tessellator.draw();
        
        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("frazionz/cache/skins/" + username));
        GL11.glScalef(width/width, height/width, 1f);
        GlStateManager.enableAlpha();
        bufferbuilder.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);
        bufferbuilder.pos((double)(left + 0), (double)(top + width), (double)zLevel).tex((double)((float)(160 + 0) * 0.00390625F), (double)((float)(32 + 32) * 0.00390625F)).endVertex();
        bufferbuilder.pos((double)(left + width), (double)(top + width), (double)zLevel).tex((double)((float)(160 + 32) * 0.00390625F), (double)((float)(32 + 32) * 0.00390625F)).endVertex();
        bufferbuilder.pos((double)(left + width), (double)(top + 0), (double)zLevel).tex((double)((float)(160 + 32) * 0.00390625F), (double)((float)(32 + 0) * 0.00390625F)).endVertex();
        bufferbuilder.pos((double)(left + 0), (double)(top + 0), (double)zLevel).tex((double)((float)(160 + 0) * 0.00390625F), (double)((float)(32 + 0) * 0.00390625F)).endVertex();
        tessellator.draw();
       
        GL11.glPopMatrix();
    }
    
    public static void drawRoundedSliderButton(int left, int top, int right, int bottom, int color)
    {
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
    	int radiusF = right - left;
    	if(radiusF%2==1) {
    		radiusF+=1;
    		right+=1;
    	}
    	radiusF /= 2;
    	
    	int radius = (int) radiusF;
    	
        float f = (float)(color >> 24 & 255) / 255.0F;
        float f1 = (float)(color >> 16 & 255) / 255.0F;
        float f2 = (float)(color >> 8 & 255) / 255.0F;
        float f3 = (float)(color & 255) / 255.0F;
    	
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
    	bufferbuilder.begin(6, DefaultVertexFormats.POSITION);
    	GlStateManager.color(f1, f2, f3, f);
        for (int i = (int) (0 / 360.0 * 100); i <= (int) (360 / 360.0 * 100); i++) {
            double angle = (Math.PI * 2 * i / 100) + Math.toRadians(180);
            bufferbuilder.pos((double)(right-radius + Math.sin(angle) * radius), (double)(top+radius + Math.cos(angle) * radius), 0.0D).endVertex();
        }
        tessellator.draw();
        
        bufferbuilder.begin(6, DefaultVertexFormats.POSITION);
        GlStateManager.color(f1, f2, f3, f);
        for (int i = (int) (0 / 360.0 * 100); i <= (int) (360 / 360.0 * 100); i++) {
            double angle = (Math.PI * 2 * i / 100) + Math.toRadians(180);
            bufferbuilder.pos((double)(right-radius + Math.sin(angle) * radius), (double)(bottom-radius + Math.cos(angle) * radius), 0.0D).endVertex();
        }
        
        tessellator.draw();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    	
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel(7425);
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos((double)right, (double)top+radius, (double)zLevel).color(f1, f2, f3, f).endVertex();
        bufferbuilder.pos((double)left, (double)top+radius, (double)zLevel).color(f1, f2, f3, f).endVertex();
        bufferbuilder.pos((double)left, (double)bottom-radius, (double)zLevel).color(f1, f2, f3, f).endVertex();
        bufferbuilder.pos((double)right, (double)bottom-radius, (double)zLevel).color(f1, f2, f3, f).endVertex();
        tessellator.draw();
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }
    
    public static void drawRekt(int left, int top, int right, int bottom, int color)
    {
        if (left < right)
        {
            int i = left;
            left = right;
            right = i;
        }

        if (top < bottom)
        {
            int j = top;
            top = bottom;
            bottom = j;
        }

        float f3 = (float)(color >> 24 & 255) / 255.0F;
        float f = (float)(color >> 16 & 255) / 255.0F;
        float f1 = (float)(color >> 8 & 255) / 255.0F;
        float f2 = (float)(color & 255) / 255.0F;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.color(f, f1, f2, f3);
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION);
        bufferbuilder.pos((double)left + 5, (double)bottom, 0.0D).endVertex();
        bufferbuilder.pos((double)right + 5, (double)bottom, 0.0D).endVertex();  
        bufferbuilder.pos((double)right - 5, (double)top, 0.0D).endVertex();
        bufferbuilder.pos((double)left - 5, (double)top, 0.0D).endVertex();

        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    /**
     * Draws a rectangle with a vertical gradient between the specified colors (ARGB format). Args : x1, y1, x2, y2,
     * topColor, bottomColor
     */
    protected static void drawGradientRect(int left, int top, int right, int bottom, int startColor, int endColor)
    {
        float f = (float)(startColor >> 24 & 255) / 255.0F;
        float f1 = (float)(startColor >> 16 & 255) / 255.0F;
        float f2 = (float)(startColor >> 8 & 255) / 255.0F;
        float f3 = (float)(startColor & 255) / 255.0F;
        float f4 = (float)(endColor >> 24 & 255) / 255.0F;
        float f5 = (float)(endColor >> 16 & 255) / 255.0F;
        float f6 = (float)(endColor >> 8 & 255) / 255.0F;
        float f7 = (float)(endColor & 255) / 255.0F;
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel(7425);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos((double)right, (double)top, (double)zLevel).color(f1, f2, f3, f).endVertex();
        bufferbuilder.pos((double)left, (double)top, (double)zLevel).color(f1, f2, f3, f).endVertex();
        bufferbuilder.pos((double)left, (double)bottom, (double)zLevel).color(f5, f6, f7, f4).endVertex();
        bufferbuilder.pos((double)right, (double)bottom, (double)zLevel).color(f5, f6, f7, f4).endVertex();
        tessellator.draw();
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }

    /**
     * Renders the specified text to the screen, center-aligned. Args : renderer, string, x, y, color
     */
    public void drawCenteredString(FontRenderer fontRendererIn, String text, int x, int y, int color)
    {
        fontRendererIn.drawStringWithShadow(text, (float)(x - fontRendererIn.getStringWidth(text) / 2), (float)y, color);
    }
    
    public void drawCenteredString(FontRenderer fontRendererIn, String text, int x, int y, int color, boolean shadow)
    {
        fontRendererIn.drawString(text, (float)(x - fontRendererIn.getStringWidth(text) / 2), (float)y, color);
    }

    /**
     * Renders the specified text to the screen. Args : renderer, string, x, y, color
     */
    public void drawString(FontRenderer fontRendererIn, String text, int x, int y, int color)
    {
        fontRendererIn.drawStringWithShadow(text, (float)x, (float)y, color);
    }

    /**
     * Draws a textured rectangle at the current z-value.
     */
    public void drawTexturedModalRect(int x, int y, int textureX, int textureY, int width, int height)
    {
        float f = 0.00390625F;
        float f1 = 0.00390625F;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.pos((double)(x + 0), (double)(y + height), (double)this.zLevel).tex((double)((float)(textureX + 0) * 0.00390625F), (double)((float)(textureY + height) * 0.00390625F)).endVertex();
        bufferbuilder.pos((double)(x + width), (double)(y + height), (double)this.zLevel).tex((double)((float)(textureX + width) * 0.00390625F), (double)((float)(textureY + height) * 0.00390625F)).endVertex();
        bufferbuilder.pos((double)(x + width), (double)(y + 0), (double)this.zLevel).tex((double)((float)(textureX + width) * 0.00390625F), (double)((float)(textureY + 0) * 0.00390625F)).endVertex();
        bufferbuilder.pos((double)(x + 0), (double)(y + 0), (double)this.zLevel).tex((double)((float)(textureX + 0) * 0.00390625F), (double)((float)(textureY + 0) * 0.00390625F)).endVertex();
        tessellator.draw();
    }
    
    public void drawCustomTexturedModalRect(int x, int y, int textureX, int textureY, int width, int height)
    {
        float f = 0.00781250F;
        float f1 = 0.00390625F;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.pos((double)(x + 0), (double)(y + height), (double)this.zLevel).tex((double)((float)(textureX + 0) * f), (double)((float)(textureY + height) * f)).endVertex();
        bufferbuilder.pos((double)(x + width), (double)(y + height), (double)this.zLevel).tex((double)((float)(textureX + width) * f), (double)((float)(textureY + height) * f)).endVertex();
        bufferbuilder.pos((double)(x + width), (double)(y + 0), (double)this.zLevel).tex((double)((float)(textureX + width) * f), (double)((float)(textureY + 0) * f)).endVertex();
        bufferbuilder.pos((double)(x + 0), (double)(y + 0), (double)this.zLevel).tex((double)((float)(textureX + 0) * f), (double)((float)(textureY + 0) * f)).endVertex();
        tessellator.draw();
    }

    /**
     * Draws a textured rectangle using the texture currently bound to the TextureManager
     */
    public void drawTexturedModalRect(float xCoord, float yCoord, int minU, int minV, int maxU, int maxV)
    {
        float f = 0.00390625F;
        float f1 = 0.00390625F;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.pos((double)(xCoord + 0.0F), (double)(yCoord + (float)maxV), (double)this.zLevel).tex((double)((float)(minU + 0) * 0.00390625F), (double)((float)(minV + maxV) * 0.00390625F)).endVertex();
        bufferbuilder.pos((double)(xCoord + (float)maxU), (double)(yCoord + (float)maxV), (double)this.zLevel).tex((double)((float)(minU + maxU) * 0.00390625F), (double)((float)(minV + maxV) * 0.00390625F)).endVertex();
        bufferbuilder.pos((double)(xCoord + (float)maxU), (double)(yCoord + 0.0F), (double)this.zLevel).tex((double)((float)(minU + maxU) * 0.00390625F), (double)((float)(minV + 0) * 0.00390625F)).endVertex();
        bufferbuilder.pos((double)(xCoord + 0.0F), (double)(yCoord + 0.0F), (double)this.zLevel).tex((double)((float)(minU + 0) * 0.00390625F), (double)((float)(minV + 0) * 0.00390625F)).endVertex();
        tessellator.draw();
    }

    /**
     * Draws a texture rectangle using the texture currently bound to the TextureManager
     */
    public void drawTexturedModalRect(int xCoord, int yCoord, TextureAtlasSprite textureSprite, int widthIn, int heightIn)
    {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.pos((double)(xCoord + 0), (double)(yCoord + heightIn), (double)this.zLevel).tex((double)textureSprite.getMinU(), (double)textureSprite.getMaxV()).endVertex();
        bufferbuilder.pos((double)(xCoord + widthIn), (double)(yCoord + heightIn), (double)this.zLevel).tex((double)textureSprite.getMaxU(), (double)textureSprite.getMaxV()).endVertex();
        bufferbuilder.pos((double)(xCoord + widthIn), (double)(yCoord + 0), (double)this.zLevel).tex((double)textureSprite.getMaxU(), (double)textureSprite.getMinV()).endVertex();
        bufferbuilder.pos((double)(xCoord + 0), (double)(yCoord + 0), (double)this.zLevel).tex((double)textureSprite.getMinU(), (double)textureSprite.getMinV()).endVertex();
        tessellator.draw();
    }

    /**
     * Draws a textured rectangle at z = 0. Args: x, y, u, v, width, height, textureWidth, textureHeight
     */
    public static void drawModalRectWithCustomSizedTexture(int x, int y, float u, float v, int width, int height, float textureWidth, float textureHeight)
    {
        float f = 1.0F / textureWidth;
        float f1 = 1.0F / textureHeight;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.pos((double)x, (double)(y + height), 0.0D).tex((double)(u * f), (double)((v + (float)height) * f1)).endVertex();
        bufferbuilder.pos((double)(x + width), (double)(y + height), 0.0D).tex((double)((u + (float)width) * f), (double)((v + (float)height) * f1)).endVertex();
        bufferbuilder.pos((double)(x + width), (double)y, 0.0D).tex((double)((u + (float)width) * f), (double)(v * f1)).endVertex();
        bufferbuilder.pos((double)x, (double)y, 0.0D).tex((double)(u * f), (double)(v * f1)).endVertex();
        tessellator.draw();
    }

    /**
     * Draws a scaled, textured, tiled modal rect at z = 0. This method isn't used anywhere in vanilla code.
     *  
     * @param u Texture U (or x) coordinate, in pixels
     * @param v Texture V (or y) coordinate, in pixels
     * @param uWidth Width of the rendered part of the texture, in pixels. Parts of the texture outside of it will wrap
     * around
     * @param vHeight Height of the rendered part of the texture, in pixels. Parts of the texture outside of it will
     * wrap around
     * @param tileWidth total width of the texture
     * @param tileHeight total height of the texture
     */
    public static void drawScaledCustomSizeModalRect(int x, int y, float u, float v, int uWidth, int vHeight, int width, int height, float tileWidth, float tileHeight)
    {
        float f = 1.0F / tileWidth;
        float f1 = 1.0F / tileHeight;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.pos((double)x, (double)(y + height), 0.0D).tex((double)(u * f), (double)((v + (float)vHeight) * f1)).endVertex();
        bufferbuilder.pos((double)(x + width), (double)(y + height), 0.0D).tex((double)((u + (float)uWidth) * f), (double)((v + (float)vHeight) * f1)).endVertex();
        bufferbuilder.pos((double)(x + width), (double)y, 0.0D).tex((double)((u + (float)uWidth) * f), (double)(v * f1)).endVertex();
        bufferbuilder.pos((double)x, (double)y, 0.0D).tex((double)(u * f), (double)(v * f1)).endVertex();
        tessellator.draw();
    }
    
	public static void drawFzCustomBackgroundSize(Minecraft mc, int x, int y, int widthIn, int heightIn) {
		ResourceLocation background = new ResourceLocation("textures/gui/frazionz/interface_background.png");
		mc.getTextureManager().bindTexture(background);
		// HG : 0 - 358
		// HD : 290 - 358
		// BG : 0 - 498
		// BD : 290 - 498
		widthIn -= 8;
		heightIn -= 8;
		
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.enableBlend();
        drawModalRectWithCustomSizedTexture(x, y, 0, 358, widthIn, heightIn, 512.0F, 512.0F);
        drawModalRectWithCustomSizedTexture(x + widthIn, y, 290, 358, 4, heightIn, 512.0F, 512.0F);
        drawModalRectWithCustomSizedTexture(x, y + heightIn, 0, 498, widthIn, 4, 512.0F, 512.0F);
        drawModalRectWithCustomSizedTexture(x + widthIn, y + heightIn, 290, 498, 4, 4, 512.0F, 512.0F);
	}

    public BufferedImage getDynamicTextureFromUrl(File file) throws IOException {
        InputStream in = new BufferedInputStream(new FileInputStream(file));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int n = 0;
        while (-1 != (n = in.read(buf))) {
            out.write(buf, 0, n);
        }
        out.close();
        in.close();
        byte[] byteArray = out.toByteArray();
        ByteArrayInputStream inByte = new ByteArrayInputStream(byteArray);
        BufferedImage read = ImageIO.read(inByte);
        return read;
    }

    public BufferedImage getDynamicTextureFromUrl(String url) throws IOException {
        InputStream in = new URL(url).openStream();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int n = 0;
        while (-1 != (n = in.read(buf))) {
            out.write(buf, 0, n);
        }
        out.close();
        in.close();
        byte[] byteArray = out.toByteArray();
        ByteArrayInputStream inByte = new ByteArrayInputStream(byteArray);
        BufferedImage read = ImageIO.read(inByte);
        return read;
    }
}
