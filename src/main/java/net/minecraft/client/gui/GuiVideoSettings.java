package net.minecraft.client.gui;

import fz.frazionz.client.gui.GuiFzBaseScreen;
import fz.frazionz.client.gui.buttons.GuiFzOptionButton;
import fz.frazionz.client.gui.buttons.GuiFzOptionSlider;
import fz.frazionz.client.gui.buttons.GuiHoverButton;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.src.Config;
import net.optifine.Lang;
import net.optifine.gui.GuiAnimationSettingsOF;
import net.optifine.gui.GuiDetailSettingsOF;
import net.optifine.gui.GuiOtherSettingsOF;
import net.optifine.gui.GuiPerformanceSettingsOF;
import net.optifine.gui.GuiQualitySettingsOF;
import net.optifine.gui.TooltipManager;
import net.optifine.gui.TooltipProviderOptions;
import net.optifine.shaders.gui.GuiShaders;

public class GuiVideoSettings extends GuiFzBaseScreen
{
    private GameSettings guiGameSettings;
    private static GameSettings.Options[] videoOptions = new GameSettings.Options[] {GameSettings.Options.GRAPHICS, GameSettings.Options.RENDER_DISTANCE, GameSettings.Options.AMBIENT_OCCLUSION, GameSettings.Options.FRAMERATE_LIMIT, GameSettings.Options.AO_LEVEL, GameSettings.Options.VIEW_BOBBING, GameSettings.Options.GUI_SCALE, GameSettings.Options.USE_VBO, GameSettings.Options.GAMMA, GameSettings.Options.ATTACK_INDICATOR, GameSettings.Options.DYNAMIC_LIGHTS, GameSettings.Options.DYNAMIC_FOV};
    private TooltipManager tooltipManager = new TooltipManager(this, new TooltipProviderOptions());

    public GuiVideoSettings(GuiScreen parentScreenIn, GameSettings gameSettingsIn)
    {
        super(parentScreenIn);
        this.guiGameSettings = gameSettingsIn;
    }

    /**
     * Adds the buttons (and other controls) to the screen in question. Called when the GUI is displayed and when the
     * window resizes, the buttonList is cleared beforehand.
     */
    public void initGui()
    {
    	super.initGui();
        this.title = I18n.format("options.videoTitle");
        
        this.buttonList.clear();
        for (int i = 0; i < videoOptions.length; ++i)
        {
            GameSettings.Options gamesettings$options = videoOptions[i];

            if (gamesettings$options != null)
            {
                int j = this.width / 2 - 155 + i % 2 * 160;
                int k = this.height/2 + 21 * (i / 2) - 100;

                if (gamesettings$options.isFloat())
                {
                	this.buttonList.add(new GuiFzOptionSlider(gamesettings$options.getOrdinal(), j, k, gamesettings$options));
                }
                else
                {
                	this.buttonList.add(new GuiFzOptionButton(gamesettings$options.getOrdinal(), j, k, gamesettings$options, I18n.format(gamesettings$options.getTranslation()), this.guiGameSettings.getKeyBinding(gamesettings$options)));
                }
            }
        }

        addAllButtons();
    }

    
    protected void addAllButtons()
    {
        int l = this.height/2 + 21 * (videoOptions.length / 2) - 100;
        int i1 = 0;
        i1 = this.width / 2 - 155 + 0;
        this.buttonList.add(new GuiHoverButton(231, i1, l, 150, 20, Lang.get("of.options.shaders")));
        i1 = this.width / 2 - 155 + 160;
        this.buttonList.add(new GuiHoverButton(202, i1, l, 150, 20, Lang.get("of.options.quality")));
        l = l + 21;
        i1 = this.width / 2 - 155 + 0;
        this.buttonList.add(new GuiHoverButton(201, i1, l, 150, 20, Lang.get("of.options.details")));
        i1 = this.width / 2 - 155 + 160;
        this.buttonList.add(new GuiHoverButton(212, i1, l, 150, 20, Lang.get("of.options.performance")));
        l = l + 21;
        i1 = this.width / 2 - 155 + 0;
        this.buttonList.add(new GuiHoverButton(211, i1, l, 150, 20, Lang.get("of.options.animations")));
        i1 = this.width / 2 - 155 + 160;
        this.buttonList.add(new GuiHoverButton(222, i1, l, 150, 20, Lang.get("of.options.other")));
        l = l + 21;
        this.buttonList.add(new GuiHoverButton(200, this.width / 2 - 100, this.height /2 + 94, I18n.format("gui.done")));
    }

    /**
     * Called by the controls from the buttonList when activated. (Mouse pressed for buttons)
     */
    protected void actionPerformed(GuiButton button, int keyCode)
    {
        if (button.enabled)
        {
            int i = this.guiGameSettings.guiScale;

            if(button.id < 200 && button instanceof GuiFzOptionButton) {
                this.guiGameSettings.setOptionValue(((GuiFzOptionButton)button).getOption(), keyCode == 0 ? 1 : -1);
                ((GuiFzOptionButton)button).setInfo(this.guiGameSettings.getKeyBinding(GameSettings.Options.byOrdinal(button.id)));
            }

            if (button.id == 200)
            {
                this.mc.gameSettings.saveOptions();
                this.mc.displayGuiScreen(this.lastScreen);
            }

            if (this.guiGameSettings.guiScale != i)
            {
                ScaledResolution scaledresolution = new ScaledResolution(this.mc);
                int j = scaledresolution.getScaledWidth();
                int k = scaledresolution.getScaledHeight();
                this.setWorldAndResolution(this.mc, j, k);
            }

            if (button.id == 201)
            {
                this.mc.gameSettings.saveOptions();
                GuiDetailSettingsOF guidetailsettingsof = new GuiDetailSettingsOF(this, this.guiGameSettings);
                this.mc.displayGuiScreen(guidetailsettingsof);
            }

            if (button.id == 202)
            {
                this.mc.gameSettings.saveOptions();
                GuiQualitySettingsOF guiqualitysettingsof = new GuiQualitySettingsOF(this, this.guiGameSettings);
                this.mc.displayGuiScreen(guiqualitysettingsof);
            }

            if (button.id == 211)
            {
                this.mc.gameSettings.saveOptions();
                GuiAnimationSettingsOF guianimationsettingsof = new GuiAnimationSettingsOF(this, this.guiGameSettings);
                this.mc.displayGuiScreen(guianimationsettingsof);
            }

            if (button.id == 212)
            {
                this.mc.gameSettings.saveOptions();
                GuiPerformanceSettingsOF guiperformancesettingsof = new GuiPerformanceSettingsOF(this, this.guiGameSettings);
                this.mc.displayGuiScreen(guiperformancesettingsof);
            }

            if (button.id == 222)
            {
                this.mc.gameSettings.saveOptions();
                GuiOtherSettingsOF guiothersettingsof = new GuiOtherSettingsOF(this, this.guiGameSettings);
                this.mc.displayGuiScreen(guiothersettingsof);
            }

            if (button.id == 231)
            {
                if (Config.isAntialiasing() || Config.isAntialiasingConfigured())
                {
                    Config.showGuiMessage(Lang.get("of.message.shaders.aa1"), Lang.get("of.message.shaders.aa2"));
                    return;
                }

                if (Config.isAnisotropicFiltering())
                {
                    Config.showGuiMessage(Lang.get("of.message.shaders.af1"), Lang.get("of.message.shaders.af2"));
                    return;
                }

                if (Config.isFastRender())
                {
                    Config.showGuiMessage(Lang.get("of.message.shaders.fr1"), Lang.get("of.message.shaders.fr2"));
                    return;
                }

                if (Config.getGameSettings().anaglyph)
                {
                    Config.showGuiMessage(Lang.get("of.message.shaders.an1"), Lang.get("of.message.shaders.an2"));
                    return;
                }

                this.mc.gameSettings.saveOptions();
                GuiShaders guishaders = new GuiShaders(this, this.guiGameSettings);
                this.mc.displayGuiScreen(guishaders);
            }
        }
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
    	super.drawScreen(mouseX, mouseY, partialTicks);
        String s = "OptiFine HD G5 Ultra";
        this.drawString(this.fontRenderer, s, 2, this.height - 10, 8421504);
        
        this.tooltipManager.drawTooltips(mouseX, mouseY, this.buttonList);
    }

    public static int getButtonWidth(GuiButton button)
    {
        return button.width;
    }

    public static int getButtonHeight(GuiButton button)
    {
        return button.height;
    }

    public static String getGuiChatText(GuiChat p_getGuiChatText_0_)
    {
        return p_getGuiChatText_0_.inputField.getText();
    }
}
