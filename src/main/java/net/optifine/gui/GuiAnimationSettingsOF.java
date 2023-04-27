package net.optifine.gui;

import fz.frazionz.client.gui.GuiFzBaseScreen;
import fz.frazionz.client.gui.buttons.GuiFzOptionButton;
import fz.frazionz.client.gui.buttons.GuiFzOptionSlider;
import fz.frazionz.client.gui.buttons.GuiHoverButton;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.optifine.Lang;

public class GuiAnimationSettingsOF extends GuiFzBaseScreen
{
    private GameSettings settings;
    private static GameSettings.Options[] enumOptions = new GameSettings.Options[] {GameSettings.Options.ANIMATED_WATER, GameSettings.Options.ANIMATED_LAVA, GameSettings.Options.ANIMATED_FIRE, GameSettings.Options.ANIMATED_PORTAL, GameSettings.Options.ANIMATED_REDSTONE, GameSettings.Options.ANIMATED_EXPLOSION, GameSettings.Options.ANIMATED_FLAME, GameSettings.Options.ANIMATED_SMOKE, GameSettings.Options.VOID_PARTICLES, GameSettings.Options.WATER_PARTICLES, GameSettings.Options.RAIN_SPLASH, GameSettings.Options.PORTAL_PARTICLES, GameSettings.Options.POTION_PARTICLES, GameSettings.Options.DRIPPING_WATER_LAVA, GameSettings.Options.ANIMATED_TERRAIN, GameSettings.Options.ANIMATED_TEXTURES, GameSettings.Options.FIREWORK_PARTICLES, GameSettings.Options.PARTICLES};

    public GuiAnimationSettingsOF(GuiScreen lastScreen, GameSettings gamesettings)
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
        this.title = I18n.format("of.options.animationsTitle");
        this.buttonList.clear();


	        for (int i = 0; i < enumOptions.length; ++i)
	        {
	            GameSettings.Options gamesettings$options = enumOptions[i];
	            int j = this.width/2 - 155 + i % 2 * 160;
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
	
	        this.buttonList.add(new GuiHoverButton(210, this.width / 2 - 155, this.height/2 + 94, 70, 20, Lang.get("of.options.animation.allOn")));
	        this.buttonList.add(new GuiHoverButton(211, this.width / 2 - 155 + 80, this.height/2 + 94, 70, 20, Lang.get("of.options.animation.allOff")));
	        this.buttonList.add(new GuiHoverButton(200, this.width / 2 + 5, this.height/2 + 94, 150, 20, I18n.format("gui.done")));
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

            if (guibutton.id == 210)
            {
                this.mc.gameSettings.setAllAnimations(true);
            }

            if (guibutton.id == 211)
            {
                this.mc.gameSettings.setAllAnimations(false);
            }

            ScaledResolution scaledresolution = new ScaledResolution(this.mc);
            this.setWorldAndResolution(this.mc, scaledresolution.getScaledWidth(), scaledresolution.getScaledHeight());
        }
    }

}
