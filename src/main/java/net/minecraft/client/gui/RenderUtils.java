package net.minecraft.client.gui;

public class RenderUtils {

	public static float[] RGBA(int color) {
		
		if((color & -67108864) == 0) {
			color |= -16777216;
		}
		
		float red = (color >> 16 & 255) / 255.0F;
		float blue = (color >> 8 & 255) / 255.0F;
		float green = (color & 255) / 255.0F;
		float alpha = (color >> 24 & 255) / 255.0F;
		
		return new float[] {red, green, blue, alpha};
	}
	
}
