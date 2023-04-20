package fz.frazionz.mods.toggle_sprint;

import fz.frazionz.mods.mod_hud.ScreenPosition;
import fz.frazionz.mods.ModDraggable;

public class ToggleSprintMod extends ModDraggable
{
    public ToggleSprintMod() {
        super("toggle_sprint");
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
