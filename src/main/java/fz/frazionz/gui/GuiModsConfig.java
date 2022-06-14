package fz.frazionz.gui;

import fz.frazionz.gui.hud.HUDConfigScreen;
import fz.frazionz.gui.hud.HUDManager;
import fz.frazionz.mods.ModInstances;
import fz.frazionz.mods.impl.ModKeystrokes;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiOptionButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.optifine.gui.GuiOptionButtonOF;
import net.optifine.gui.GuiOptionSliderOF;
import net.optifine.gui.TooltipManager;
import net.optifine.gui.TooltipProviderOptions;

public class GuiModsConfig extends GuiScreen implements GuiYesNoCallback
{
    private GuiScreen prevScreen;
    protected String title;
    private GameSettings settings;
    
    private ModKeystrokes keystrokes;
    
    private static GameSettings.Options[] enumOptions = new GameSettings.Options[] {GameSettings.Options.KEYSTROKES, GameSettings.Options.TOGGLESPRINT};
    private TooltipManager tooltipManager = new TooltipManager(this, new TooltipProviderOptions());
    
    private static HUDManager hudManager = new HUDManager();

    public GuiModsConfig(GuiScreen p_i51_1_, GameSettings p_i51_2_)
    {
        this.prevScreen = p_i51_1_;
        this.settings = p_i51_2_;
    }

    /**
     * Adds the buttons (and other controls) to the screen in question. Called when the GUI is displayed and when the
     * window resizes, the buttonList is cleared beforehand.
     */
    public void initGui()
    {
        this.title = I18n.format("of.options.otherTitle");
        this.buttonList.clear();

        for (int i = 0; i < enumOptions.length; ++i)
        {
            GameSettings.Options gamesettings$options = enumOptions[i];
            int j = this.width / 2 - 155 + i % 2 * 160;
            int k = this.height / 6 + 21 * (i / 2) - 12;

            if (!gamesettings$options.isFloat())
            {
                this.buttonList.add(new GuiOptionButtonOF(gamesettings$options.getOrdinal(), j, k, gamesettings$options, this.settings.getKeyBinding(gamesettings$options)));
            }
            else
            {
                this.buttonList.add(new GuiOptionSliderOF(gamesettings$options.getOrdinal(), j, k, gamesettings$options));
            }
        }

        this.buttonList.add(new GuiButton(210, this.width / 2 - 100, this.height / 6 + 168 + 11 - 44, I18n.format("D\u00E9placer Mods")));
        this.buttonList.add(new GuiButton(200, this.width / 2 - 100, this.height / 6 + 168 + 11, I18n.format("gui.done")));
    }

    /**
     * Called by the controls from the buttonList when activated. (Mouse pressed for buttons)
     */
    
    
    protected void actionPerformed(GuiButton button, int keyCode)
    {
        if (button.enabled)
        {
            if (button.id < 200 && button instanceof GuiOptionButton)
            {
            	this.mc.gameSettings.saveOptions();
                this.settings.setOptionValue(((GuiOptionButton)button).getOption(), keyCode == 0 ? 1 : -1);
                button.displayString = this.settings.getKeyBinding(GameSettings.Options.byOrdinal(button.id)); 
                
                // ADD_MODS //
                
                if(GameSettings.Options.byOrdinal(button.id) == GameSettings.Options.KEYSTROKES) {
                	ModKeystrokes keystrokes = ModInstances.getModKeystrokes();
                	/*HUDManager.getInstance().unregister(keystrokes);*/
                	if(keystrokes.isEnabled()) {
                		keystrokes.setEnabled(false);
                	}
                	else {
                    	keystrokes.setEnabled(true);
                	}
                	HUDManager.rerun();
                }
                
            }

            if (button.id == 200)
            {
                this.mc.gameSettings.saveOptions();
                this.mc.refreshFontRenderer();
                this.mc.displayGuiScreen(this.prevScreen);
            }
            
            if (button.id == 210)
            {
                this.mc.gameSettings.saveOptions();
                hudManager = HUDManager.getInstance();
            	this.mc.displayGuiScreen(new HUDConfigScreen(hudManager));
            }
        }
    }

    public void confirmClicked(boolean result, int id)
    {
        if (result)
        {
            this.mc.gameSettings.resetSettings();
        }

        this.mc.displayGuiScreen(this);
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRenderer, this.title, this.width / 2, 15, 16777215);
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.tooltipManager.drawTooltips(mouseX, mouseY, this.buttonList);
    }
}
