package fz.frazionz.mods.faction_hud;

import fz.frazionz.mods.mod_hud.ScreenPosition;
import fz.frazionz.mods.ModDraggable;

public class FactionHUDMod extends ModDraggable
{
    public FactionHUDMod() {
        super("faction_hud");
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
