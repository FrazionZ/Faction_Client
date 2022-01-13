package net.minecraft.client.gui;

import java.io.IOException;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import optifine.Config;
import optifine.GuiAnimationSettingsOF;
import optifine.GuiDetailSettingsOF;
import optifine.GuiOtherSettingsOF;
import optifine.GuiPerformanceSettingsOF;
import optifine.GuiQualitySettingsOF;
import optifine.GuiScreenOF;
import optifine.Lang;
import optifine.TooltipManager;
import shadersmod.client.GuiShaders;

public class GuiVideoSettings extends GuiScreenOF
{
    private GuiScreen parentGuiScreen;
    protected String screenTitle = "Video Settings";
    private GameSettings guiGameSettings;
    private static GameSettings.Options[] videoOptions = new GameSettings.Options[] {GameSettings.Options.GRAPHICS, GameSettings.Options.RENDER_DISTANCE, GameSettings.Options.AMBIENT_OCCLUSION, GameSettings.Options.FRAMERATE_LIMIT, GameSettings.Options.AO_LEVEL, GameSettings.Options.VIEW_BOBBING, GameSettings.Options.GUI_SCALE, GameSettings.Options.USE_VBO, GameSettings.Options.GAMMA, GameSettings.Options.ATTACK_INDICATOR, GameSettings.Options.DYNAMIC_LIGHTS, GameSettings.Options.DYNAMIC_FOV};
    private static final String __OBFID = "CL_00000718";
    private TooltipManager tooltipManager = new TooltipManager(this);

    public GuiVideoSettings(GuiScreen parentScreenIn, GameSettings gameSettingsIn)
    {
        this.parentGuiScreen = parentScreenIn;
        this.guiGameSettings = gameSettingsIn;
    }

    /**
     * Adds the buttons (and other controls) to the screen in question. Called when the GUI is displayed and when the
     * window resizes, the buttonList is cleared beforehand.
     */
    public void initGui()
    {
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
    	
        this.screenTitle = I18n.format("options.videoTitle");
        this.buttonList.clear();

        for (int i = 0; i < videoOptions.length; ++i)
        {
            GameSettings.Options gamesettings$options = videoOptions[i];

            if (gamesettings$options != null)
            {
                int j = this.width / 2 - 155 + i % 2 * 160;
                int k = this.height / 6 + 21 * (i / 2) - 12;
                
                if (gamesettings$options.getEnumFloat())
                {
                	if(this.mc.gameSettings.frazionz_ui)
                		this.buttonList.add(new GuiRoundedSlider(gamesettings$options.returnEnumOrdinal(), i%2 == 0 ? this.width / 2 - width - (width / 4) : this.width / 2 + (width / 4), y + height + (int)((2+i/2)*h), width, height , "" , this.mc.fontrenderer, 1, gamesettings$options, 0.0F, 1.0F));
                	else
                		this.buttonList.add(new GuiOptionSlider(gamesettings$options.returnEnumOrdinal(), j, k, gamesettings$options));
                }
                else
                {
                	if(this.mc.gameSettings.frazionz_ui)
                		this.buttonList.add(new GuiRoundedOptionsButton(gamesettings$options.returnEnumOrdinal(), i%2 == 0 ? this.width / 2 - width - (width / 4) : this.width / 2 + (width / 4), y + height + (int)((2+i/2)*h), width, height, gamesettings$options, this.guiGameSettings.getKeyBinding(gamesettings$options), this.mc.fontrenderer, 1));
                	else
                		this.buttonList.add(new GuiOptionButton(gamesettings$options.returnEnumOrdinal(), j, k, gamesettings$options, this.guiGameSettings.getKeyBinding(gamesettings$options)));
                }
            }
        }
        
        addAllButtons(width, height, h, y);
    }

    
    private void addAllButtons(int width, int height, int h, int y)
    {
    	if(this.mc.gameSettings.frazionz_ui) {
            this.buttonList.add(new GuiRoundedOptionsButton(231, this.width / 2 - width - (width / 4), y + height + (int)(8*h), width, height, Lang.get("of.options.shaders"), this.mc.fontrenderer, 1));
            this.buttonList.add(new GuiRoundedOptionsButton(202, this.width / 2 + (width / 4), y + height + (int)(8*h), width, height, Lang.get("of.options.quality"), this.mc.fontrenderer, 1));
            this.buttonList.add(new GuiRoundedOptionsButton(201, this.width / 2 - width - (width / 4), y + height + (int)(9*h), width, height, Lang.get("of.options.details"), this.mc.fontrenderer, 1));
            this.buttonList.add(new GuiRoundedOptionsButton(212, this.width / 2 + (width / 4), y + height + (int)(9*h), width, height, Lang.get("of.options.performance"), this.mc.fontrenderer, 1));
            this.buttonList.add(new GuiRoundedOptionsButton(211, this.width / 2 - width - (width / 4), y + height + (int)(10*h), width, height, Lang.get("of.options.animations"), this.mc.fontrenderer, 1));
            this.buttonList.add(new GuiRoundedOptionsButton(222, this.width / 2 + (width / 4), y + height + (int)(10*h), width, height, Lang.get("of.options.other"), this.mc.fontrenderer, 1));

            this.buttonList.add(new GuiRoundedButton(200, this.width / 2 - (width / 2), y + height + (int)(11*h), width, height, I18n.format("gui.done"), false, this.mc.fontrenderer, 1));
    	}
    	else {
            int l = this.height / 6 + 21 * (videoOptions.length / 2) - 12;
            int i1 = 0;
            i1 = this.width / 2 - 155 + 0;
            this.buttonList.add(new GuiOptionButton(231, i1, l, Lang.get("of.options.shaders")));
            i1 = this.width / 2 - 155 + 160;
            this.buttonList.add(new GuiOptionButton(202, i1, l, Lang.get("of.options.quality")));
            l = l + 21;
            i1 = this.width / 2 - 155 + 0;
            this.buttonList.add(new GuiOptionButton(201, i1, l, Lang.get("of.options.details")));
            i1 = this.width / 2 - 155 + 160;
            this.buttonList.add(new GuiOptionButton(212, i1, l, Lang.get("of.options.performance")));
            l = l + 21;
            i1 = this.width / 2 - 155 + 0;
            this.buttonList.add(new GuiOptionButton(211, i1, l, Lang.get("of.options.animations")));
            i1 = this.width / 2 - 155 + 160;
            this.buttonList.add(new GuiOptionButton(222, i1, l, Lang.get("of.options.other")));
            l = l + 21;
            this.buttonList.add(new GuiButton(200, this.width / 2 - 100, this.height / 6 + 168 + 11, I18n.format("gui.done")));
    	}
    }
    
    /**
     * Called by the controls from the buttonList when activated. (Mouse pressed for buttons)
     */
    protected void actionPerformed(GuiButton p_actionPerformed_1_, int mouseButton)
    {
    	System.out.println("MouseButton: " + mouseButton);
    	
        if (p_actionPerformed_1_.enabled)
        {
        	int p_actionPerformed_2_ = mouseButton == 0 ? 1 : -1;
        	
            int i = this.guiGameSettings.guiScale;
            
            if (p_actionPerformed_1_.id < 200 && ( p_actionPerformed_1_ instanceof GuiOptionButton || p_actionPerformed_1_ instanceof GuiRoundedOptionsButton) )
            {
            	if(p_actionPerformed_1_ instanceof GuiRoundedOptionsButton) {
                    this.guiGameSettings.setOptionValue(((GuiRoundedOptionsButton)p_actionPerformed_1_).returnEnumOptions(), p_actionPerformed_2_);
            	}
            	else {
                    this.guiGameSettings.setOptionValue(((GuiOptionButton)p_actionPerformed_1_).returnEnumOptions(), p_actionPerformed_2_);
            	}
                p_actionPerformed_1_.displayString = this.guiGameSettings.getKeyBinding(GameSettings.Options.getEnumOptions(p_actionPerformed_1_.id));
            }

            if (p_actionPerformed_1_.id == 200)
            {
                this.mc.gameSettings.saveOptions();
                this.mc.displayGuiScreen(this.parentGuiScreen);
            }

            if (this.guiGameSettings.guiScale != i)
            {
                ScaledResolution scaledresolution = new ScaledResolution(this.mc);
                int j = scaledresolution.getScaledWidth();
                int k = scaledresolution.getScaledHeight();
                this.setWorldAndResolution(this.mc, j, k);
            }

            if (p_actionPerformed_1_.id == 201)
            {
                this.mc.gameSettings.saveOptions();
                GuiDetailSettingsOF guidetailsettingsof = new GuiDetailSettingsOF(this, this.guiGameSettings);
                this.mc.displayGuiScreen(guidetailsettingsof);
            }

            if (p_actionPerformed_1_.id == 202)
            {
                this.mc.gameSettings.saveOptions();
                GuiQualitySettingsOF guiqualitysettingsof = new GuiQualitySettingsOF(this, this.guiGameSettings);
                this.mc.displayGuiScreen(guiqualitysettingsof);
            }

            if (p_actionPerformed_1_.id == 211)
            {
                this.mc.gameSettings.saveOptions();
                GuiAnimationSettingsOF guianimationsettingsof = new GuiAnimationSettingsOF(this, this.guiGameSettings);
                this.mc.displayGuiScreen(guianimationsettingsof);
            }

            if (p_actionPerformed_1_.id == 212)
            {
                this.mc.gameSettings.saveOptions();
                GuiPerformanceSettingsOF guiperformancesettingsof = new GuiPerformanceSettingsOF(this, this.guiGameSettings);
                this.mc.displayGuiScreen(guiperformancesettingsof);
            }

            if (p_actionPerformed_1_.id == 222)
            {
                this.mc.gameSettings.saveOptions();
                GuiOtherSettingsOF guiothersettingsof = new GuiOtherSettingsOF(this, this.guiGameSettings);
                this.mc.displayGuiScreen(guiothersettingsof);
            }

            if (p_actionPerformed_1_.id == 231)
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
        this.drawDefaultBackground();
        String s = "OptiFine HD C6 Ultra";
    	if(this.mc.gameSettings.frazionz_ui)
    	{
        	int height = (int)((this.height / 14) * 1.5);
        	int ok = this.width/26;
        	int y = this.height / 22;
        	int h = height + height/3;
            
            this.drawRect(this.width/8, this.height/8, this.width - this.width/8, this.height - this.height/20, this.BACKGROUND_COLOR);
            this.drawGradientRect(this.width/8 - ok, y, this.width - this.width/8 + ok, y + height, this.FIRST_GRADIENT_COLOR, this.SECOND_GRADIENT_COLOR);
            this.mc.fontrenderer.drawString(s, 2, this.height - (this.height / 28), 8421504); // DRAW "OptiFine HD C6 Ultra"
            this.mc.fontrendererTitle.drawCenteredString(this.screenTitle, this.width / 2, y + (height-1)/2, 0xFFFFFFFF); // DRAW Gui Tittle
    	}
    	else {
    		this.drawCenteredString(this.fontRendererObj, this.screenTitle, this.width / 2, 15, 16777215);
    		this.drawString(this.fontRendererObj, s, 2, this.height - 10, 8421504);
    	}
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.tooltipManager.drawTooltips(mouseX, mouseY, this.buttonList);
    }

    public static int getButtonWidth(GuiButton p_getButtonWidth_0_)
    {
        return p_getButtonWidth_0_.width;
    }

    public static int getButtonHeight(GuiButton p_getButtonHeight_0_)
    {
        return p_getButtonHeight_0_.height;
    }

    public static void drawGradientRect(GuiScreen p_drawGradientRect_0_, int p_drawGradientRect_1_, int p_drawGradientRect_2_, int p_drawGradientRect_3_, int p_drawGradientRect_4_, int p_drawGradientRect_5_, int p_drawGradientRect_6_)
    {
        p_drawGradientRect_0_.drawGradientRect(p_drawGradientRect_1_, p_drawGradientRect_2_, p_drawGradientRect_3_, p_drawGradientRect_4_, p_drawGradientRect_5_, p_drawGradientRect_6_);
    }
}
