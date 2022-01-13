package net.minecraft.client.gui;

import java.io.IOException;

import fz.frazionz.gui.GuiClassement;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.EnumDifficulty;

public class GuiOptions extends GuiScreen
{
    private static final GameSettings.Options[] SCREEN_OPTIONS = new GameSettings.Options[] {GameSettings.Options.FOV};
    private final GuiScreen lastScreen;

    /** Reference to the GameSettings object. */
    private final GameSettings settings;
    private GuiButton difficultyButton;
    private GuiLockIconButton lockButton;
    protected String title = "Options";

    public GuiOptions(GuiScreen p_i1046_1_, GameSettings p_i1046_2_)
    {
        this.lastScreen = p_i1046_1_;
        this.settings = p_i1046_2_;
    }

    /**
     * Adds the buttons (and other controls) to the screen in question. Called when the GUI is displayed and when the
     * window resizes, the buttonList is cleared beforehand.
     */
    public void initGui()
    {
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
            
            this.title = I18n.format("options.title");
            
            addAllButtons(width, height, h, y);
    	}
    	else {
    		int i = 0;
    		
            for (GameSettings.Options gamesettings$options : SCREEN_OPTIONS)
            {
                if (gamesettings$options.getEnumFloat())
                {
                    this.buttonList.add(new GuiOptionSlider(gamesettings$options.returnEnumOrdinal(), this.width / 2 - 155 + i % 2 * 160, this.height / 6 - 12 + 24 * (i >> 1), gamesettings$options));
                }
                else
                {
                    GuiOptionButton guioptionbutton = new GuiOptionButton(gamesettings$options.returnEnumOrdinal(), this.width / 2 - 155 + i % 2 * 160, this.height / 6 - 12 + 24 * (i >> 1), gamesettings$options, this.settings.getKeyBinding(gamesettings$options));
                    this.buttonList.add(guioptionbutton);
                }

                ++i;
            }

            if (this.mc.world != null)
            {
                EnumDifficulty enumdifficulty = this.mc.world.getDifficulty();
                this.difficultyButton = new GuiButton(108, this.width / 2 - 155 + i % 2 * 160, this.height / 6 - 12 + 24 * (i >> 1), 150, 20, this.getDifficultyText(enumdifficulty));
                this.buttonList.add(this.difficultyButton);

                if (this.mc.isSingleplayer() && !this.mc.world.getWorldInfo().isHardcoreModeEnabled())
                {
                    this.difficultyButton.setWidth(this.difficultyButton.getButtonWidth() - 20);
                    this.lockButton = new GuiLockIconButton(109, this.difficultyButton.xPosition + this.difficultyButton.getButtonWidth(), this.difficultyButton.yPosition);
                    this.buttonList.add(this.lockButton);
                    this.lockButton.setLocked(this.mc.world.getWorldInfo().isDifficultyLocked());
                    this.lockButton.enabled = !this.lockButton.isLocked();
                    this.difficultyButton.enabled = !this.lockButton.isLocked();
                }
                else
                {
                    this.difficultyButton.enabled = false;
                }
            }
            else
            {
                this.buttonList.add(new GuiOptionButton(GameSettings.Options.REALMS_NOTIFICATIONS.returnEnumOrdinal(), this.width / 2 - 155 + i % 2 * 160, this.height / 6 - 12 + 24 * (i >> 1), GameSettings.Options.REALMS_NOTIFICATIONS, this.settings.getKeyBinding(GameSettings.Options.REALMS_NOTIFICATIONS)));
            }

            this.buttonList.add(new GuiButton(110, this.width / 2 - 155, this.height / 6 + 48 - 6, 150, 20, I18n.format("options.skinCustomisation")));
            this.buttonList.add(new GuiButton(106, this.width / 2 + 5, this.height / 6 + 48 - 6, 150, 20, I18n.format("options.sounds")));
            this.buttonList.add(new GuiButton(101, this.width / 2 - 155, this.height / 6 + 72 - 6, 150, 20, I18n.format("options.video")));
            this.buttonList.add(new GuiButton(100, this.width / 2 + 5, this.height / 6 + 72 - 6, 150, 20, I18n.format("options.controls")));
            this.buttonList.add(new GuiButton(102, this.width / 2 - 155, this.height / 6 + 96 - 6, 150, 20, I18n.format("options.language")));
            this.buttonList.add(new GuiButton(103, this.width / 2 + 5, this.height / 6 + 96 - 6, 150, 20, I18n.format("options.chat.title")));
            this.buttonList.add(new GuiButton(105, this.width / 2 - 155, this.height / 6 + 120 - 6, 150, 20, I18n.format("options.resourcepack")));
            this.buttonList.add(new GuiButton(104, this.width / 2 + 5, this.height / 6 + 120 - 6, 150, 20, I18n.format("options.snooper.view")));
            
            this.buttonList.add(new GuiButton(203, this.width / 2 - 100, this.height / 6 + 138, "Macro"));
            
            this.buttonList.add(new GuiButton(200, this.width / 2 - 100, this.height / 6 + 168, I18n.format("gui.done")));
    	}
    }
    
    /**
     * Ajoute tout les boutons
     * @param width
     * @param height
     * @param h
     * @param y
     */
    private void addAllButtons(int width, int height, int h, int y)
    {
        int i = 0;

        for (GameSettings.Options gamesettings$options : SCREEN_OPTIONS)
        {
            if (gamesettings$options.getEnumFloat())
            {
                this.buttonList.add(new GuiRoundedSlider(gamesettings$options.returnEnumOrdinal(), this.width / 2 - width - (width / 4), y + height + h*2, width, height , "" , this.mc.fontrenderer, 1, gamesettings$options, 0.0F, 1.0F));
            }
            else
            {
                GuiOptionButton guioptionbutton = new GuiOptionButton(gamesettings$options.returnEnumOrdinal(), this.width / 2 - 155 + i % 2 * 160, this.height / 6 - 12 + 24 * (i >> 1), gamesettings$options, this.settings.getKeyBinding(gamesettings$options));
                this.buttonList.add(guioptionbutton);
            }

            ++i;
        }
        
        if (this.mc.world != null)
        {
            EnumDifficulty enumdifficulty = this.mc.world.getDifficulty();
            this.difficultyButton = new GuiRoundedButton(108, this.width / 2 + (width / 4), y + height + h*2, width, height, this.getDifficultyText(enumdifficulty), false, this.mc.fontrenderer, 1);
            this.buttonList.add(this.difficultyButton);

            if (this.mc.isSingleplayer() && !this.mc.world.getWorldInfo().isHardcoreModeEnabled())
            {
                //this.difficultyButton.setWidth(this.difficultyButton.getButtonWidth() - 20);
                this.lockButton = new GuiLockIconButton(109, this.difficultyButton.xPosition + this.difficultyButton.getButtonWidth(), this.difficultyButton.yPosition);
                //this.buttonList.add(this.lockButton);
                this.lockButton.setLocked(this.mc.world.getWorldInfo().isDifficultyLocked());
                this.lockButton.enabled = !this.lockButton.isLocked();
                this.difficultyButton.enabled = !this.lockButton.isLocked();
            }
            else
            {
                this.difficultyButton.enabled = false;
            }
        }	
    	
    	this.buttonList.add(new GuiRoundedButton(110, this.width / 2 - width - (width / 4), y + height + (int)(3*h), width, height, I18n.format("options.skinCustomisation"), false, this.mc.fontrenderer, 1));
    	this.buttonList.add(new GuiRoundedButton(106, this.width / 2 + (width / 4), y + height + (int)(3*h), width, height, I18n.format("options.sounds"), false, this.mc.fontrenderer, 1));
        this.buttonList.add(new GuiRoundedButton(101, this.width / 2 - width - (width / 4), y + height + (int)(4*h), width, height, I18n.format("options.video"), false, this.mc.fontrenderer, 1));
        this.buttonList.add(new GuiRoundedButton(100, this.width / 2 + (width / 4), y + height + (int)(4*h), width, height, I18n.format("options.controls"), false, this.mc.fontrenderer, 1));
        this.buttonList.add(new GuiRoundedButton(102, this.width / 2 - width - (width / 4), y + height + (int)(5*h), width, height, I18n.format("options.language"), false, this.mc.fontrenderer, 1));
        this.buttonList.add(new GuiRoundedButton(103, this.width / 2 + (width / 4), y + height + (int)(5*h), width, height, I18n.format("options.chat.title"), false, this.mc.fontrenderer, 1));
        this.buttonList.add(new GuiRoundedButton(105, this.width / 2 - width - (width / 4), y + height + (int)(6*h), width, height, I18n.format("options.resourcepack"), false, this.mc.fontrenderer, 1));
        this.buttonList.add(new GuiRoundedButton(104, this.width / 2 + (width / 4), y + height + (int)(6*h), width, height, I18n.format("options.snooper.view"), false, this.mc.fontrenderer, 1));
        
    	this.buttonList.add(new GuiRoundedButton(204, this.width / 2 - (width / 2), y + height + (int)(8*h), width, height, I18n.format("Classement"), false, this.mc.fontrenderer, 1));

    	this.buttonList.add(new GuiRoundedButton(203, this.width / 2 - (width / 2), y + height + (int)(7*h), width, height, I18n.format("Macro"), false, this.mc.fontrenderer, 1));
        this.buttonList.add(new GuiRoundedButton(200, this.width / 2 - (width / 2), y + height + (int)(11*h), width, height, I18n.format("gui.done"), false, this.mc.fontrenderer, 1));
    
    }

    public String getDifficultyText(EnumDifficulty p_175355_1_)
    {
        ITextComponent itextcomponent = new TextComponentString("");
        itextcomponent.appendSibling(new TextComponentTranslation("options.difficulty", new Object[0]));
        itextcomponent.appendText(": ");
        itextcomponent.appendSibling(new TextComponentTranslation(p_175355_1_.getDifficultyResourceKey(), new Object[0]));
        return itextcomponent.getFormattedText();
    }

    public void confirmClicked(boolean result, int id)
    {
        this.mc.displayGuiScreen(this);

        if (id == 109 && result && this.mc.world != null)
        {
            this.mc.world.getWorldInfo().setDifficultyLocked(true);
            this.lockButton.setLocked(true);
            this.lockButton.enabled = false;
            this.difficultyButton.enabled = false;
        }
    }

    /**
     * Fired when a key is typed (except F11 which toggles full screen). This is the equivalent of
     * KeyListener.keyTyped(KeyEvent e). Args : character (character on the key), keyCode (lwjgl Keyboard key code)
     */
    protected void keyTyped(char typedChar, int keyCode) throws IOException
    {
        if (keyCode == 1)
        {
            this.mc.gameSettings.saveOptions();
        }

        super.keyTyped(typedChar, keyCode);
    }

    /**
     * Called by the controls from the buttonList when activated. (Mouse pressed for buttons)
     */
    protected void actionPerformed(GuiButton button, int mouseButton) throws IOException
    {
        if (button.enabled)
        {
            if (button.id < 100 && button instanceof GuiOptionButton)
            {
                GameSettings.Options gamesettings$options = ((GuiOptionButton)button).returnEnumOptions();
                this.settings.setOptionValue(gamesettings$options, 1);
                button.displayString = this.settings.getKeyBinding(GameSettings.Options.getEnumOptions(button.id));
            }

            if (button.id == 108)
            {
                this.mc.world.getWorldInfo().setDifficulty(EnumDifficulty.getDifficultyEnum(this.mc.world.getDifficulty().getDifficultyId() + 1));
                this.difficultyButton.displayString = this.getDifficultyText(this.mc.world.getDifficulty());
            }

            if (button.id == 109)
            {
                this.mc.displayGuiScreen(new GuiYesNo(this, (new TextComponentTranslation("difficulty.lock.title", new Object[0])).getFormattedText(), (new TextComponentTranslation("difficulty.lock.question", new Object[] {new TextComponentTranslation(this.mc.world.getWorldInfo().getDifficulty().getDifficultyResourceKey(), new Object[0])})).getFormattedText(), 109));
            }

            if (button.id == 110)
            {
                this.mc.gameSettings.saveOptions();
                this.mc.displayGuiScreen(new GuiCustomizeSkin(this));
            }

            if (button.id == 101)
            {
                this.mc.gameSettings.saveOptions();
                this.mc.displayGuiScreen(new GuiVideoSettings(this, this.settings));
            }

            if (button.id == 100)
            {
                this.mc.gameSettings.saveOptions();
                this.mc.displayGuiScreen(new GuiControls(this, this.settings));
            }

            if (button.id == 102)
            {
                this.mc.gameSettings.saveOptions();
                this.mc.displayGuiScreen(new GuiLanguage(this, this.settings, this.mc.getLanguageManager()));
            }

            if (button.id == 103)
            {
                this.mc.gameSettings.saveOptions();
                this.mc.displayGuiScreen(new ScreenChatOptions(this, this.settings));
            }

            if (button.id == 104)
            {
                this.mc.gameSettings.saveOptions();
                this.mc.displayGuiScreen(new GuiSnooper(this, this.settings));
            }

            if (button.id == 200)
            {
                this.mc.gameSettings.saveOptions();
                this.mc.displayGuiScreen(this.lastScreen);
            }

            if (button.id == 105)
            {
                this.mc.gameSettings.saveOptions();
                this.mc.displayGuiScreen(new GuiScreenResourcePacks(this));
            }

            if (button.id == 106)
            {
                this.mc.gameSettings.saveOptions();
                this.mc.displayGuiScreen(new GuiScreenOptionsSounds(this, this.settings));
            }
            
            if (button.id == 203) {
            	this.mc.gameSettings.saveOptions();
                this.mc.displayGuiScreen(new GuiMacro(this));
            }
            if (button.id == 204) {
            	this.mc.gameSettings.saveOptions();
                this.mc.displayGuiScreen(new GuiClassement(this, 1));
            }
        }
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
    	int height = (int)((this.height / 14) * 1.5);
    	int ok = this.width/26;
    	int y = this.height / 22;
    	
    	this.drawDefaultBackground();
    	
    	if(this.mc.gameSettings.frazionz_ui){
            this.drawRect(this.width/8, this.height/8, this.width - this.width/8, this.height - this.height/20, this.BACKGROUND_COLOR);
            this.drawGradientRect(this.width/8 - ok, y, this.width - this.width/8 + ok, y + height, this.FIRST_GRADIENT_COLOR, this.SECOND_GRADIENT_COLOR);
            this.mc.fontrendererTitle.drawCenteredString(this.title, this.width / 2, y + (height-1)/2, 0xFFFFFFFF);
    	}
    	else
    		this.drawCenteredString(this.fontRendererObj, this.title, this.width / 2, 15, 16777215);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
