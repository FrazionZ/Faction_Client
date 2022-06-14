package net.minecraft.client.gui;

import java.io.IOException;

import fz.frazionz.gui.GuiFzBaseScreen;
import fz.frazionz.gui.buttons.GuiFzButton;
import fz.frazionz.gui.buttons.GuiFzOptionButton;
import fz.frazionz.gui.buttons.GuiFzSlider;
import net.minecraft.client.gui.chat.NarratorChatListener;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;

public class ScreenChatOptions extends GuiFzBaseScreen
{
    private static final GameSettings.Options[] CHAT_OPTIONS = new GameSettings.Options[] {GameSettings.Options.CHAT_VISIBILITY, GameSettings.Options.CHAT_COLOR, GameSettings.Options.CHAT_LINKS, GameSettings.Options.CHAT_OPACITY, GameSettings.Options.CHAT_LINKS_PROMPT, GameSettings.Options.CHAT_SCALE, GameSettings.Options.CHAT_HEIGHT_FOCUSED, GameSettings.Options.CHAT_HEIGHT_UNFOCUSED, GameSettings.Options.CHAT_WIDTH, GameSettings.Options.REDUCED_DEBUG_INFO, GameSettings.Options.NARRATOR};
    private final GameSettings game_settings;
    private GuiFzOptionButton narratorButton;

    public ScreenChatOptions(GuiScreen parentScreenIn, GameSettings gameSettingsIn)
    {
        super(parentScreenIn);
        this.game_settings = gameSettingsIn;
    }

    /**
     * Adds the buttons (and other controls) to the screen in question. Called when the GUI is displayed and when the
     * window resizes, the buttonList is cleared beforehand.
     */
    public void initGui()
    {
    	super.initGui();
        this.title = I18n.format("options.chat.title");
        int i = 0;

        for (GameSettings.Options gamesettings$options : CHAT_OPTIONS)
        {
            if (gamesettings$options.isFloat())
            {
            	this.buttonList.add(new GuiFzSlider(gamesettings$options.getOrdinal(), this.width/2 - 155 + i % 2 * 160, this.height/2 - 82 + 24 * (i >> 1), gamesettings$options));
            }
            else
            {
            	GuiFzOptionButton guioptionbutton = new GuiFzOptionButton(gamesettings$options.getOrdinal(), this.width / 2 - 155 + i % 2 * 160, this.height/2 - 82 + 24 * (i >> 1), gamesettings$options, I18n.format(gamesettings$options.getTranslation()), this.game_settings.getKeyBinding(gamesettings$options));
	            this.buttonList.add(guioptionbutton);
	
	            if (gamesettings$options == GameSettings.Options.NARRATOR)
	            {
	                this.narratorButton = guioptionbutton;
	                guioptionbutton.enabled = NarratorChatListener.INSTANCE.isActive();
	            }
            }

            ++i;
        }

        this.buttonList.add(new GuiFzButton(200, this.width / 2 - 100, this.height/2 + 86, I18n.format("gui.done")));
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
            if (button.id < 100 && (button instanceof GuiFzOptionButton))
            {
            	GuiFzOptionButton fzButton = (GuiFzOptionButton) button;
            	this.game_settings.setOptionValue(fzButton.getOption(), 1);
            	fzButton.setInfo(this.game_settings.getKeyBinding(GameSettings.Options.byOrdinal(button.id)));
            }

            if (button.id == 200)
            {
                this.mc.gameSettings.saveOptions();
                this.mc.displayGuiScreen(this.lastScreen);
            }
        }
    }

    public void updateNarratorButton()
    {
    	this.narratorButton.setInfo(this.game_settings.getKeyBinding(GameSettings.Options.byOrdinal(this.narratorButton.id)));
    }
}
