package fz.frazionz.mods.impl;

import fz.frazionz.gui.hud.ScreenPosition;
import fz.frazionz.mods.ModDraggable;


public class ModHelloWorld extends ModDraggable {

	
	String text = "TU PEUX ME BOUGER";
	
	@Override
	public int getWidth() {
		return font.getStringWidth(text);
	}

	@Override
	public int getHeight() {
		return font.FONT_HEIGHT;
	}

	@Override
	public void render(ScreenPosition pos) {
		//font.drawString("TU PEUX ME BOUGER", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, -1);
		font.drawString(text, pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, -1);
	}
	
	@Override
	public void renderDummy(ScreenPosition pos) {
		//font.drawString("TU PEUX ME BOUGER", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, 0xFF00FF00);
		font.drawString(text, pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, 0xFF00FF00);
	}

}
