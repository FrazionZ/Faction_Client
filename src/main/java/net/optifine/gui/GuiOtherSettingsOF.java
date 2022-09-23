package net.optifine.gui;

import fz.frazionz.client.gui.GuiFzBaseScreen;
import fz.frazionz.client.gui.GuiRoundedOptionsButton;
import fz.frazionz.client.gui.buttons.GuiFzButton;
import fz.frazionz.client.gui.buttons.GuiFzOptionButton;
import fz.frazionz.client.gui.buttons.GuiFzSlider;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;

public class GuiOtherSettingsOF extends GuiFzBaseScreen implements GuiYesNoCallback
{
    private GameSettings settings;
    private static GameSettings.Options[] enumOptions = new GameSettings.Options[] {GameSettings.Options.LAGOMETER, GameSettings.Options.PROFILER, GameSettings.Options.SHOW_FPS, GameSettings.Options.ADVANCED_TOOLTIPS, GameSettings.Options.WEATHER, GameSettings.Options.TIME, GameSettings.Options.USE_FULLSCREEN, GameSettings.Options.FULLSCREEN_MODE, GameSettings.Options.ANAGLYPH, GameSettings.Options.AUTOSAVE_TICKS, GameSettings.Options.SCREENSHOT_SIZE, GameSettings.Options.SHOW_GL_ERRORS};
    private TooltipManager tooltipManager = new TooltipManager(this, new TooltipProviderOptions());
    
    public GuiOtherSettingsOF(GuiScreen lastScreen, GameSettings gamesettings)
    {
        super(lastScreen);
        this.settings = gamesettings;
    }

    /**
     * Adds the buttons (and other controls) to the screen in question. Called when the GUI is displayed and when the
     * window resizes, the buttonList is cleared beforehand.
     */
    public void initGui()
    {
    	super.initGui();
        this.title = I18n.format("of.options.otherTitle");
        this.buttonList.clear();

        for (int i = 0; i < enumOptions.length; ++i)
	    {
	        GameSettings.Options gamesettings$options = enumOptions[i];
	        int j = this.width / 2 - 155 + i % 2 * 160;
	        int k = this.height/2 + 21 * (i / 2) - 100;
	
	        if (!gamesettings$options.isFloat())
	        {
	            this.buttonList.add(new GuiFzOptionButton(gamesettings$options.getOrdinal(), j, k, gamesettings$options, I18n.format(gamesettings$options.getTranslation()),this.settings.getKeyBinding(gamesettings$options)));
	        }
	        else
	        {
	            this.buttonList.add(new GuiFzSlider(gamesettings$options.getOrdinal(), j, k, gamesettings$options));
	        }
	    }
	
	    this.buttonList.add(new GuiFzButton(210, this.width/2 - 100, this.height/2 + 94 - 44, I18n.format("of.options.other.reset")));
	    this.buttonList.add(new GuiFzButton(200, this.width/2 - 100, this.height/2 + 94, I18n.format("gui.done")));
    }

    /**
     * Called by the controls from the buttonList when activated. (Mouse pressed for buttons)
     */
    protected void actionPerformed(GuiButton guibutton, int keyCode)
    {
        if (guibutton.enabled)
        {
            if (guibutton.id < 200)
            {
            	if(guibutton instanceof GuiFzOptionButton) {
                    this.settings.setOptionValue(((GuiFzOptionButton)guibutton).getOption(), 1);
                    ((GuiFzOptionButton)guibutton).setInfo(this.settings.getKeyBinding(GameSettings.Options.byOrdinal(guibutton.id)));
            	}
            }

            if (guibutton.id == 200)
            {
                this.mc.gameSettings.saveOptions();
                this.mc.refreshFontRenderer();
                this.mc.displayGuiScreen(this.lastScreen);
            }

            if (guibutton.id == 210)
            {
                this.mc.gameSettings.saveOptions();
                GuiYesNo guiyesno = new GuiYesNo(this, I18n.format("of.message.other.reset"), "", 9999);
                this.mc.displayGuiScreen(guiyesno);
            }
        }
    }

    public void confirmClicked(boolean flag, int i)
    {
        if (flag)
        {
            this.mc.gameSettings.resetSettings();
        }

        this.mc.displayGuiScreen(this);
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int x, int y, float f)
    {
    	super.drawScreen(x, y, f);
        this.tooltipManager.drawTooltips(x, y, this.buttonList);
    }
}
