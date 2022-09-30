package net.minecraft.client.gui;

import java.io.IOException;

import fz.frazionz.client.gui.GuiFzBaseScreen;
import fz.frazionz.client.gui.GuiKeyBinds;
import fz.frazionz.client.gui.buttons.GuiFzOptionButton;
import fz.frazionz.client.gui.buttons.GuiFzSlider;
import fz.frazionz.client.gui.buttons.GuiHoverButton;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.optifine.Lang;

public class GuiControls extends GuiFzBaseScreen
{
    private static final GameSettings.Options[] OPTIONS_ARR = new GameSettings.Options[] {GameSettings.Options.INVERT_MOUSE, GameSettings.Options.SENSITIVITY, GameSettings.Options.TOUCHSCREEN, GameSettings.Options.AUTO_JUMP};

    /** Reference to the GameSettings object. */
    private final GameSettings options;

    public GuiControls(GuiScreen screen, GameSettings settings)
    {
    	super(screen);
        this.options = settings;
    }

    /**
     * Adds the buttons (and other controls) to the screen in question. Called when the GUI is displayed and when the
     * window resizes, the buttonList is cleared beforehand.
     */
    public void initGui()
    {
    	this.title = I18n.format("controls.title");
    	
        int i = 0;
        for (GameSettings.Options gamesettings$options : OPTIONS_ARR)
        {
            if (gamesettings$options != null)
            {
                int j = this.width / 2 - 155 + i % 2 * 160;
                int k = this.height/2 + 24 * (i / 2) - 82;

                if (gamesettings$options.isFloat())
                {
                	this.buttonList.add(new GuiFzSlider(gamesettings$options.getOrdinal(), j, k, gamesettings$options));
                }
                else
                {
                	this.buttonList.add(new GuiFzOptionButton(gamesettings$options.getOrdinal(), j, k, gamesettings$options, I18n.format(gamesettings$options.getTranslation()), this.options.getKeyBinding(gamesettings$options)));
                }
            }

            ++i;
        }
        
        this.buttonList.add(new GuiHoverButton(201, this.width / 2 - 155, this.height / 2 - 34, 150, 20, Lang.get("options.controls")));
        
        this.buttonList.add(new GuiHoverButton(200, this.width / 2 - 100, this.height / 2 + 86, I18n.format("gui.done")));
        
        super.initGui();
    	
    }

    /**
     * Handles mouse input.
     */
    public void handleMouseInput() throws IOException
    {
        super.handleMouseInput();
    }

    /**
     * Called by the controls from the buttonList when activated. (Mouse pressed for buttons)
     */
    protected void actionPerformed(GuiButton button, int keyCode) throws IOException
    {
        if (button.id == 200)
        {
            this.mc.displayGuiScreen(this.lastScreen);
        }
        else if (button.id == 201)
        {
        	this.mc.displayGuiScreen(new GuiKeyBinds(this, options));
        }
        else if (button.id < 100 && button instanceof GuiFzOptionButton)
        {
        	this.options.setOptionValue(((GuiFzOptionButton)button).getOption(), keyCode == 0 ? 1 : -1);
            ((GuiFzOptionButton)button).setInfo(this.options.getKeyBinding(GameSettings.Options.byOrdinal(button.id)));
        }
    }

}
