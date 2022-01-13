package optifine;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiOptionButton;
import net.minecraft.client.gui.GuiRoundedButton;
import net.minecraft.client.gui.GuiRoundedOptionsButton;
import net.minecraft.client.gui.GuiRoundedSlider;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;

public class GuiPerformanceSettingsOF extends GuiScreen
{
    private GuiScreen prevScreen;
    protected String title;
    private GameSettings settings;
    private static GameSettings.Options[] enumOptions = new GameSettings.Options[] {GameSettings.Options.SMOOTH_FPS, GameSettings.Options.SMOOTH_WORLD, GameSettings.Options.FAST_RENDER, GameSettings.Options.FAST_MATH, GameSettings.Options.CHUNK_UPDATES, GameSettings.Options.CHUNK_UPDATES_DYNAMIC, GameSettings.Options.LAZY_CHUNK_LOADING};
    private TooltipManager tooltipManager = new TooltipManager(this);

    public GuiPerformanceSettingsOF(GuiScreen p_i52_1_, GameSettings p_i52_2_)
    {
        this.prevScreen = p_i52_1_;
        this.settings = p_i52_2_;
    }

    /**
     * Adds the buttons (and other controls) to the screen in question. Called when the GUI is displayed and when the
     * window resizes, the buttonList is cleared beforehand.
     */
    public void initGui()
    {
        this.title = I18n.format("of.options.performanceTitle");
        this.buttonList.clear();

        if(this.mc.gameSettings.frazionz_ui) {
        	int width = this.width/5;
        	int height = this.height / 19;
        	int h = height + height/3;
          	int y = (this.height / 22);
          	
        	if(height != this.mc.fontrenderer.getSize()) {
        		this.mc.fontrenderer = new fz.frazionz.gui.renderer.fonts.FontRenderer(this.mc.FONT_LOCATION, height);
        	}
        	
        	if((int) (height*1.5) != this.mc.fontrendererTitle.getSize()) {
        		this.mc.fontrendererTitle = new fz.frazionz.gui.renderer.fonts.FontRenderer(this.mc.FONT_LOCATION, (float) (height*1.5));
        	}
        	
            for (int i = 0; i < enumOptions.length; ++i)
            {
                GameSettings.Options gamesettings$options = enumOptions[i];
                int j = this.width / 2 - 155 + i % 2 * 160;
                int k = this.height / 6 + 21 * (i / 2) - 12;

                if (!gamesettings$options.getEnumFloat())
                {
                    this.buttonList.add(new GuiRoundedOptionsButton(gamesettings$options.returnEnumOrdinal(), i%2 == 0 ? this.width / 2 - width - (width / 4) : this.width / 2 + (width / 4), y + height + (int)((2+i/2)*h), width, height, gamesettings$options, this.settings.getKeyBinding(gamesettings$options), this.mc.fontrenderer, 1));
                }
                else
                {
                    this.buttonList.add(new GuiRoundedSlider(gamesettings$options.returnEnumOrdinal(), i%2 == 0 ? this.width / 2 - width - (width / 4) : this.width / 2 + (width / 4), y + height + (int)((2+i/2)*h), width, height , "" , this.mc.fontrenderer, 1, gamesettings$options, 0.0F, 1.0F));
                }
            }
            
            this.buttonList.add(new GuiRoundedButton(200, this.width / 2 - (width / 2), y + height + (int)(11*h), width, height, I18n.format("gui.done"), false, this.mc.fontrenderer, 1));    
        }
        
        else {
            for (int i = 0; i < enumOptions.length; ++i)
            {
                GameSettings.Options gamesettings$options = enumOptions[i];
                int j = this.width / 2 - 155 + i % 2 * 160;
                int k = this.height / 6 + 21 * (i / 2) - 12;

                if (!gamesettings$options.getEnumFloat())
                {
                    this.buttonList.add(new GuiOptionButtonOF(gamesettings$options.returnEnumOrdinal(), j, k, gamesettings$options, this.settings.getKeyBinding(gamesettings$options)));
                }
                else
                {
                    this.buttonList.add(new GuiOptionSliderOF(gamesettings$options.returnEnumOrdinal(), j, k, gamesettings$options));
                }
            }

            this.buttonList.add(new GuiButton(200, this.width / 2 - 100, this.height / 6 + 168 + 11, I18n.format("gui.done")));
        }
    }

    /**
     * Called by the controls from the buttonList when activated. (Mouse pressed for buttons)
     */
    protected void actionPerformed(GuiButton button, int mouseButton)
    {
        if (button.enabled)
        {
            if (button.id < 200)
            {
            	if(button instanceof GuiOptionButton) {
                    this.settings.setOptionValue(((GuiOptionButton)button).returnEnumOptions(), 1);
                    button.displayString = this.settings.getKeyBinding(GameSettings.Options.getEnumOptions(button.id));
            	}
            	else if(button instanceof GuiRoundedOptionsButton) {
                    this.settings.setOptionValue(((GuiRoundedOptionsButton)button).returnEnumOptions(), 1);
                    button.displayString = this.settings.getKeyBinding(GameSettings.Options.getEnumOptions(button.id));
            	}
            }

            if (button.id == 200)
            {
                this.mc.gameSettings.saveOptions();
                this.mc.displayGuiScreen(this.prevScreen);
            }
        }
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        if(this.mc.gameSettings.frazionz_ui) {
        	int height = (int)((this.height / 14) * 1.5);
        	int ok = this.width/26;
        	int y = this.height / 22;
        	int h = height + height/3;
            
            this.drawRect(this.width/8, this.height/8, this.width - this.width/8, this.height - this.height/20, this.BACKGROUND_COLOR);
            this.drawGradientRect(this.width/8 - ok, y, this.width - this.width/8 + ok, y + height, this.FIRST_GRADIENT_COLOR, this.SECOND_GRADIENT_COLOR);
            this.mc.fontrendererTitle.drawCenteredString(this.title, this.width / 2, y + (height-1)/2, 0xFFFFFFFF); // DRAW Gui Tittle
        }
        else {
            this.drawCenteredString(this.fontRendererObj, this.title, this.width / 2, 15, 16777215);
        }
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.tooltipManager.drawTooltips(mouseX, mouseY, this.buttonList);
    }
}
