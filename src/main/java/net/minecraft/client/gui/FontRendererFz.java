package net.minecraft.client.gui;

import java.awt.Color;
import java.awt.Font;
import java.util.Locale;

import static org.lwjgl.opengl.GL11.*;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer.EnumChatVisibility;
import net.minecraft.util.ResourceLocation;
import optifine.Config;
import optifine.CustomColors;

public class FontRendererFz extends FontRenderer {

	private UnicodeFont font;
	public FontRendererFz(String fontName, int size) {
		super(Minecraft.getMinecraft().gameSettings, new ResourceLocation("textures/fontfz/ascii.png"), Minecraft.getMinecraft().getTextureManager(), Minecraft.getMinecraft().isUnicode());

		(font = new UnicodeFont(new Font(fontName, Font.PLAIN, size))).addAsciiGlyphs();
		font.getEffects().add(new ColorEffect(Color.WHITE));
		
		try {
			font.loadGlyphs();
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		FONT_HEIGHT = font.getHeight("azertyuiopqsdfghjklmwxcvbnAZERTYUIOPQSDFGHJKLMWXCVBNÉ0123456789") / 2;
		
	}
	
	private int renderString(String text, float x, float y, int color) {
		if(text == null) return 0;
		
		glPushMatrix();
		glScaled(0.5, 0.5, 0.5);
		x *= 2;
		y = y * 2 - 4.0F;
		boolean blend = glIsEnabled(GL_BLEND), lighting = glIsEnabled(GL_LIGHTING), texture2d = glIsEnabled(GL_TEXTURE_2D), alpha = glIsEnabled(GL_ALPHA_TEST);
		glEnable(GL_BLEND);
		glDisable(GL_LIGHTING);
		glDisable(GL_TEXTURE_2D);
		glDisable(GL_ALPHA_TEST);
				
		float red = RenderUtils.RGBA(color)[0];
		float green = RenderUtils.RGBA(color)[1];
		float blue = RenderUtils.RGBA(color)[2];
		
		for (int i = 0; i < text.length(); ++i)
        {
            char c0 = text.charAt(i);

            if (c0 == 167 && i + 1 < text.length())
            {
                int l = "0123456789abcdefklmnor".indexOf(String.valueOf(text.charAt(i + 1)).toLowerCase(Locale.ROOT).charAt(0));

                if (l < 16)
                {

                    if (l < 0 || l > 15)
                    {
                        l = 15;
                    }

                    int i1 = this.colorCode[l];

                    if (Config.isCustomColors())
                    {
                        i1 = CustomColors.getTextColor(l, i1);
                    }
                    
                    red = RenderUtils.RGBA(i1)[0];
                    green = RenderUtils.RGBA(i1)[1];
                    blue = RenderUtils.RGBA(i1)[2];
                    
                }
                ++i;
            } else {
            	font.drawString(x, y, Character.toString(c0), new org.newdawn.slick.Color(red, green, blue, RenderUtils.RGBA(color)[3]));
            	x += this.getCharWidth(c0);
            }
        }
		
		if(!blend) {
			glDisable(GL_BLEND);
		}
		if(lighting) {
			glEnable(GL_LIGHTING);
		}
		if(texture2d) {
			glEnable(GL_TEXTURE_2D);
		}
		if(alpha) {
			glEnable(GL_ALPHA_TEST);
		}
		
		GlStateManager.bindTexture(0);
		GlStateManager.color(0, 0, 0);
		
		glPopMatrix();
		
		return (int) x;
		
	}
	
	@Override
	public int drawString(String text, int x, int y, int color) {
		return renderString(text, x, y, color);
	}
	
	@Override
	public int drawStringWithShadow(String text, float x, float y, int color) {
		renderString(text, x + 1.0F, y + 1.0F, Color.BLACK.getRGB());
		return renderString(text, x, y, color);
	}
	
	@Override
	public int getCharWidth(char character) {
		return font.getWidth(Character.toString(character));
	}
	
	@Override
	public int getStringWidth(String text) {
		return font.getWidth(text) / 2;
	}
	

}
