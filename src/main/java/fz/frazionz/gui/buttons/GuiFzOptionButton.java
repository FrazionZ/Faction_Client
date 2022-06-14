package fz.frazionz.gui.buttons;

import net.minecraft.client.settings.GameSettings;

public class GuiFzOptionButton extends GuiFzChoiceButton
{
    private final GameSettings.Options enumOptions;

    public GuiFzOptionButton(int buttonId, int x, int y, String buttonText, String info)
    {
        this(buttonId, x, y, 150, 20, (GameSettings.Options)null, buttonText, info);
    }
    
    public GuiFzOptionButton(int buttonId, int x, int y, int width, int height, String buttonText, String info)
    {
        this(buttonId, x, y, width, height, (GameSettings.Options)null, buttonText, info);
    }
    
    public GuiFzOptionButton(int buttonId, int x, int y, GameSettings.Options option, String buttonText, String info)
    {
        this(buttonId, x, y, 150, 20, option, buttonText, info);
    }
        
    public GuiFzOptionButton(int buttonId, int x, int y, int width, int height, GameSettings.Options option, String buttonText, String info)
    {
        super(buttonId, x, y, width, height, buttonText, info);
        this.enumOptions = option;
    }

    public GameSettings.Options getOption()
    {
        return this.enumOptions;
    }
}
