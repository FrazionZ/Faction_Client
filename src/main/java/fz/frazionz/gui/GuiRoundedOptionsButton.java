package fz.frazionz.gui;

import fz.frazionz.TTFFontRenderer;
import net.minecraft.client.settings.GameSettings;

public class GuiRoundedOptionsButton extends GuiRoundedButton
{
    private final GameSettings.Options enumOptions;

    public GuiRoundedOptionsButton(int buttonId, int x, int y, int widthIn, int heightIn, GameSettings.Options option, String buttonText, TTFFontRenderer fontrenderer, int hoverInt)
    {
        super(buttonId, x, y, widthIn, heightIn, buttonText, false, fontrenderer, 1);
        this.enumOptions = option;
    }
    
    public GuiRoundedOptionsButton(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText, TTFFontRenderer fontrenderer, int hoverInt)
    {
        super(buttonId, x, y, widthIn, heightIn, buttonText, false, fontrenderer, 1);
        this.enumOptions = 	(GameSettings.Options)null;
    }
    

    public GameSettings.Options returnEnumOptions()
    {
        return this.enumOptions;
    }
}
