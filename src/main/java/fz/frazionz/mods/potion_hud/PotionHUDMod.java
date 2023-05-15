package fz.frazionz.mods.potion_hud;

import fz.frazionz.mods.mod_hud.ScreenPosition;
import fz.frazionz.mods.ModDraggable;

public class PotionHUDMod extends ModDraggable
{
    public PotionHUDMod() {
        super("potion_hud");
    }

    @Override
    public int getWidth() {
        return 120;
    }

    @Override
    public int getHeight() {
        return 120;
    }

    @Override
    public void render(ScreenPosition pos) {

    }
}
