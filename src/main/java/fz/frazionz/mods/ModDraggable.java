package fz.frazionz.mods;

import fz.frazionz.mods.mod_hud.IRenderer;
import fz.frazionz.mods.mod_hud.ScreenPosition;
import net.minecraft.client.Minecraft;
import org.json.JSONObject;

public abstract class ModDraggable extends Mod implements IRenderer
{
    protected ScreenPosition pos;

    public ModDraggable(String name)
    {
        super(name);
    }

    @Override
    public ScreenPosition load()
    {
        return pos;
    }

    @Override
    public void save(ScreenPosition pos)
    {
        this.pos = pos;
    }

    public final int getLineOffset(ScreenPosition pos, int lineNum)
    {
        return pos.getAbsoluteX() + getLineOffset(lineNum);
    }

    private int getLineOffset(int lineNum)
    {
        return (Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT + 3) * lineNum;
    }

    public ScreenPosition getPos() {
        return pos;
    }

    public void setPos(ScreenPosition pos) {
        this.pos = pos;
    }

    public void loadConfig(JSONObject json) {
        super.loadConfig(json);
        if(json.has("posX") && json.has("posY")) {
            pos = ScreenPosition.fromAbsolute(json.getInt("posX"), json.getInt("posY"));
        }
        else {
            pos = ScreenPosition.fromAbsolute(0, 0);
        }
    }

    @Override
    public int getDummyHeight() {
        return getHeight();
    }

    @Override
    public int getDummyWidth() {
        return getWidth();
    }

    @Override
    public JSONObject getJson() {
        JSONObject json = super.getJson();
        json.put("posX", pos.getAbsoluteX());
        json.put("posY", pos.getAbsoluteY());
        return json;
    }
}
