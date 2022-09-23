package net.minecraft.client.gui;

import java.io.IOException;

import fz.frazionz.client.gui.GuiFzBaseScreen;
import fz.frazionz.client.gui.GuiMacro;
import fz.frazionz.client.gui.buttons.GuiFzButton;
import fz.frazionz.client.gui.buttons.GuiFzChoiceButton;
import fz.frazionz.client.gui.buttons.GuiFzOptionButton;
import fz.frazionz.client.gui.buttons.GuiFzSlider;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.EnumDifficulty;

public class GuiOptions extends GuiFzBaseScreen
{
    private static final GameSettings.Options[] SCREEN_OPTIONS = new GameSettings.Options[] {GameSettings.Options.FOV};

    /** Reference to the GameSettings object. */
    private final GameSettings settings;
    private GuiFzChoiceButton difficultyButton;
    private GuiLockIconButton lockButton;

    public GuiOptions(GuiScreen lastScreen, GameSettings gameSettings)
    {
    	super(lastScreen);
        this.settings = gameSettings;
    }

    /**
     * Adds the buttons (and other controls) to the screen in question. Called when the GUI is displayed and when the
     * window resizes, the buttonList is cleared beforehand.
     */
    public void initGui()
    {
        this.title = I18n.format("options.title");
        super.initGui();
    }
    
    @Override
    protected void addAllButtons()
    {
        for (GameSettings.Options gamesettings$options : SCREEN_OPTIONS)
        {
            if (gamesettings$options.isFloat())
            {
                this.buttonList.add(new GuiFzSlider(gamesettings$options.getOrdinal(), this.width / 2 - 155, this.height/2 - 82, gamesettings$options));
            }
            else
            {
            	GuiFzOptionButton guioptionbutton = new GuiFzOptionButton(gamesettings$options.getOrdinal(), this.width / 2 - 155, this.height/2 - 82, gamesettings$options, this.settings.getKeyBinding(gamesettings$options), "");
                this.buttonList.add(guioptionbutton);
            }
        }
        
        if (this.mc.world != null)
        {
            EnumDifficulty enumdifficulty = this.mc.world.getDifficulty();
            this.difficultyButton = new GuiFzChoiceButton(108, this.width / 2 + 5, this.height / 2 - 82, 150, 20, I18n.format("options.difficulty") + ":", I18n.format(enumdifficulty.getTranslationKey()));
            this.buttonList.add(this.difficultyButton);

            if (this.mc.isSingleplayer() && !this.mc.world.getWorldInfo().isHardcoreModeEnabled())
            {
                //this.difficultyButton.setWidth(this.difficultyButton.getButtonWidth() - 20);
                this.lockButton = new GuiLockIconButton(109, this.difficultyButton.x + this.difficultyButton.getButtonWidth(), this.difficultyButton.y);
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
    	
        this.buttonList.add(new GuiFzButton(110, this.width / 2 - 155, this.height / 2 - 58, 150, 20, I18n.format("options.skinCustomisation")));
        this.buttonList.add(new GuiFzButton(106, this.width / 2 + 5, this.height / 2 - 58, 150, 20, I18n.format("options.sounds")));
        this.buttonList.add(new GuiFzButton(101, this.width / 2 - 155, this.height / 2 - 34, 150, 20, I18n.format("options.video")));
        this.buttonList.add(new GuiFzButton(100, this.width / 2 + 5, this.height / 2 - 34, 150, 20, I18n.format("options.controls")));
        this.buttonList.add(new GuiFzButton(102, this.width / 2 - 155, this.height / 2 - 10, 150, 20, I18n.format("options.language")));
        this.buttonList.add(new GuiFzButton(103, this.width / 2 + 5, this.height / 2 - 10, 150, 20, I18n.format("options.chat.title")));
        this.buttonList.add(new GuiFzButton(105, this.width / 2 - 155, this.height / 2 + 14, 150, 20, I18n.format("options.resourcepack")));
        this.buttonList.add(new GuiFzButton(104, this.width / 2 + 5, this.height / 2 + 14, 150, 20, I18n.format("options.snooper.view")));
        this.buttonList.add(new GuiFzButton(203, this.width / 2 - 100, this.height / 2 + 38, "Macro"));
        this.buttonList.add(new GuiFzButton(200, this.width / 2 - 100, this.height / 2 + 86, I18n.format("gui.done")));
    
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
    protected void actionPerformed(GuiButton button, int keyCode) throws IOException
    {
        if (button.enabled)
        {
            if (button.id < 100 && button instanceof GuiOptionButton)
            {
                GameSettings.Options gamesettings$options = ((GuiOptionButton)button).getOption();
                this.settings.setOptionValue(gamesettings$options, 1);
                button.displayString = this.settings.getKeyBinding(GameSettings.Options.byOrdinal(button.id));
            }

            if (button.id == 108)
            {
                this.mc.world.getWorldInfo().setDifficulty(EnumDifficulty.byId(this.mc.world.getDifficulty().getId() + 1));
                this.difficultyButton.setInfo(I18n.format(this.mc.world.getDifficulty().getTranslationKey()));
            }

            if (button.id == 109)
            {
                this.mc.displayGuiScreen(new GuiYesNo(this, (new TextComponentTranslation("difficulty.lock.title", new Object[0])).getFormattedText(), (new TextComponentTranslation("difficulty.lock.question", new Object[] {new TextComponentTranslation(this.mc.world.getWorldInfo().getDifficulty().getTranslationKey(), new Object[0])})).getFormattedText(), 109));
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
                //this.mc.displayGuiScreen(new GuiClassement(this, 1));
            }
        }
    }
}
