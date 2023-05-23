package fz.frazionz;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.resources.ResourceLocation;

public class TTFFontRenderer {
    private int size, scaleFactor;
    private Font font;
    private UnicodeFont unicodeFont;

    public TTFFontRenderer(ResourceLocation loc, int size) {
        try {
            InputStream is = Minecraft.getMinecraft().getResourceManager().getResource(loc).getInputStream();
            this.font = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        /*ScaledResolution res = new ScaledResolution(Minecraft.getMinecraft());
        this.scaleFactor = res.getScaleFactor();*/
        this.size = size;
        this.unicodeFont = new UnicodeFont(this.font.deriveFont((float) size));
        try {
            this.unicodeFont.addAsciiGlyphs();
            this.unicodeFont.getEffects().add(new ColorEffect(Color.white));
            this.unicodeFont.loadGlyphs();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public void drawCenteredStringVertically(String text, int x, int y, int color) {
        drawString(text, x, y - getHeight() / 2, color);
    }

    public void drawString(String text, float x, float y, int color) {
        /*ScaledResolution res = new ScaledResolution(Minecraft.getMinecraft());
        if (res.getScaleFactor() != scaleFactor) {
            this.scaleFactor = res.getScaleFactor();
            this.unicodeFont = new UnicodeFont(this.font.deriveFont((float) size));

            try {
                this.unicodeFont.addAsciiGlyphs();
                this.unicodeFont.getEffects().add(new ColorEffect(Color.white));
                this.unicodeFont.loadGlyphs();
            } catch (SlickException e) {
                e.printStackTrace();
            }
        }*/
        org.newdawn.slick.Color c = new org.newdawn.slick.Color(color);
        GlStateManager.pushMatrix();
        GlStateManager.scale(1D, 1D, 1D);
        GlStateManager.color(c.getRed() / 255F, c.getGreen() / 255F, c.getBlue() / 255F, c.getAlpha() / 255F);

        GlStateManager.disableLighting();
        GL11.glEnable(GL11.GL_BLEND);
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        unicodeFont.drawString(x, y, text, c);

        GlStateManager.color(1, 1, 1, 1);
        GlStateManager.bindTexture(0);
        GL11.glEnable(GL11.GL_TEXTURE_2D);

        GlStateManager.popMatrix();
    }

    public void drawStringWithShadow(String text, int x, int y, int color) {
        drawString(text, x + 1F, y + 1F, Color.black.getRGB());
        drawString(text, x, y, color);
    }

    public int getWidth(String text) {
        return unicodeFont.getWidth(text);
    }

    public int getHeight() {
        return unicodeFont.getLineHeight();
    }

    public int getHeight(String text) {
        return unicodeFont.getHeight(text) / 2;
    }

    public void drawCenteredString(String text, int x, int y, int color) {
        drawString(text, x - getWidth(text) / 2, y - getHeight() / 2, color);
    }

    public void drawCenteredStringHorizontally(String text, int x, int y, int color) {
        drawString(text, x - getWidth(text) / 2, y, color);
    }

    public void drawCenteredStringWithShadow(String text, int x, int y, int color) {
        drawStringWithShadow(text, x - getWidth(text) / 2, y - getHeight() / 2, color);
    }

    public String trimStringToWidth(String text, int width) {
        return this.trimStringToWidth(text, width, false);
    }

    public String trimStringToWidth(String text, int width, boolean reverse) {
        StringBuilder stringbuilder = new StringBuilder();
        float f = 0.0F;
        int i = reverse ? text.length() - 1 : 0;
        int j = reverse ? -1 : 1;
        boolean flag = false;
        boolean flag1 = false;

        for (int k = i; k >= 0 && k < text.length() && f < (float) width; k += j) {
            char c0 = text.charAt(k);
            float f1 = this.getWidth(String.valueOf(c0));

            if (flag) {
                flag = false;

                if (c0 != 'l' && c0 != 'L') {
                    if (c0 == 'r' || c0 == 'R') {
                        flag1 = false;
                    }
                } else {
                    flag1 = true;
                }
            } else if (f1 < 0.0F) {
                flag = true;
            } else {
                f += f1;

                if (flag1) {
                    ++f;
                }
            }

            if (f > (float) width) {
                break;
            }

            if (reverse) {
                stringbuilder.insert(0, c0);
            } else {
                stringbuilder.append(c0);
            }
        }

        return stringbuilder.toString();
    }

    public void drawStringWithGradient(String text, int x, int y, int startColor, int endColor) {
        // Get the width of the text
        int width = getWidth(text);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ZERO);

        GL14.glBlendFuncSeparate(GL11.GL_ZERO, GL11.GL_ONE, GL11.GL_SRC_COLOR, GL11.GL_ZERO);
        // Draw Black rectangle
        GlStateManager.pushMatrix();
        GL11.glShadeModel(GL11.GL_SMOOTH);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glBegin(GL11.GL_QUADS);
        GlStateManager.color(0.0f, 0.0f, 0.0f, 1.0f);
        GL11.glVertex2f(x, y);
        GL11.glVertex2f(x, y + getHeight());
        GL11.glVertex2f(x + width, y + getHeight());
        GL11.glVertex2f(x + width, y);
        GL11.glEnd();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glShadeModel(GL11.GL_FLAT);

        // Draw Text
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        drawString(text, x, y, 0xFFFFFFFF);
        GlStateManager.popMatrix();

        GlStateManager.blendFunc(GL11.GL_DST_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        // Create a gradient texture
        Gui.drawGradientRect(x, y, width, getHeight(), startColor, endColor, true);

        // Reset OpenGL state
        GlStateManager.disableBlend();
    }
}