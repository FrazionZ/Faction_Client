package fz.frazionz.mods.armor_hud;

import fz.frazionz.mods.mod_hud.ScreenPosition;
import fz.frazionz.mods.ModDraggable;

public class ArmorHUDMod extends ModDraggable
{
    public ArmorHUDMod() {
        super("armor_hud");
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
