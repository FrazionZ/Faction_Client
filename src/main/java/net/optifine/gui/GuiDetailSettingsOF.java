package net.optifine.gui;

import fz.frazionz.client.gui.GuiFzBaseScreen;
import fz.frazionz.client.gui.buttons.GuiFzOptionButton;
import fz.frazionz.client.gui.buttons.GuiFzOptionSlider;
import fz.frazionz.client.gui.buttons.GuiHoverButton;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;

public class GuiDetailSettingsOF extends GuiFzBaseScreen
{
    private GameSettings settings;
    private static GameSettings.Options[] enumOptions = new GameSettings.Options[] {GameSettings.Options.CLOUDS, GameSettings.Options.CLOUD_HEIGHT, GameSettings.Options.TREES, GameSettings.Options.RAIN, GameSettings.Options.SKY, GameSettings.Options.STARS, GameSettings.Options.SUN_MOON, GameSettings.Options.SHOW_CAPES, GameSettings.Options.FOG_FANCY, GameSettings.Options.FOG_START, GameSettings.Options.TRANSLUCENT_BLOCKS, GameSettings.Options.HELD_ITEM_TOOLTIPS, GameSettings.Options.DROPPED_ITEMS, GameSettings.Options.ENTITY_SHADOWS, GameSettings.Options.VIGNETTE, GameSettings.Options.ALTERNATE_BLOCKS, GameSettings.Options.SWAMP_COLORS, GameSettings.Options.SMOOTH_BIOMES};
    private TooltipManager tooltipManager = new TooltipManager(this, new TooltipProviderOptions());

    public GuiDetailSettingsOF(GuiScreen lastScreen, GameSettings gamesettings)
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
        this.title = I18n.format("of.options.detailsTitle");
        this.buttonList.clear();

	        for (int i = 0; i < enumOptions.length; ++i)
	        {
	            GameSettings.Options gamesettings$options = enumOptions[i];
	            int j = this.width / 2 - 155 + i % 2 * 160;
	            int k = this.height/2 + 21 * (i / 2) - 100;
	
	            if (!gamesettings$options.isFloat())
	            {
	                this.buttonList.add(new GuiFzOptionButton(gamesettings$options.getOrdinal(), j, k, gamesettings$options, I18n.format(gamesettings$options.getTranslation()), this.settings.getKeyBinding(gamesettings$options)));
	            }
	            else
	            {
	                this.buttonList.add(new GuiFzOptionSlider(gamesettings$options.getOrdinal(), j, k, gamesettings$options));
	            }
	        }
	
	        this.buttonList.add(new GuiHoverButton(200, this.width / 2 - 100, this.height/2 + 94, I18n.format("gui.done")));
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
                this.mc.displayGuiScreen(this.lastScreen);
            }
        }
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
