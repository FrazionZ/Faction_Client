package net.minecraft.client.gui;

import java.io.IOException;
import net.minecraft.client.gui.chat.NarratorChatListener;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;

public class ScreenChatOptions extends GuiScreen
{
    private static final GameSettings.Options[] CHAT_OPTIONS = new GameSettings.Options[] {GameSettings.Options.CHAT_VISIBILITY, GameSettings.Options.CHAT_COLOR, GameSettings.Options.CHAT_LINKS, GameSettings.Options.CHAT_OPACITY, GameSettings.Options.CHAT_LINKS_PROMPT, GameSettings.Options.CHAT_SCALE, GameSettings.Options.CHAT_HEIGHT_FOCUSED, GameSettings.Options.CHAT_HEIGHT_UNFOCUSED, GameSettings.Options.CHAT_WIDTH, GameSettings.Options.REDUCED_DEBUG_INFO, GameSettings.Options.NARRATOR};
    private final GuiScreen parentScreen;
    private final GameSettings game_settings;
    private String chatTitle;
    private GuiOptionButton field_193025_i;
    private GuiRoundedOptionsButton roundedOptionsButton;

    public ScreenChatOptions(GuiScreen parentScreenIn, GameSettings gameSettingsIn)
    {
        this.parentScreen = parentScreenIn;
        this.game_settings = gameSettingsIn;
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
    	
      	if(this.mc.gameSettings.frazionz_ui) {
        	if(height != this.mc.fontrenderer.getSize()) {
        		this.mc.fontrenderer = new fz.frazionz.gui.renderer.fonts.FontRenderer(this.mc.FONT_LOCATION, height);
        	}
        	
        	if((int) (height*1.5) != this.mc.fontrendererTitle.getSize()) {
        		this.mc.fontrendererTitle = new fz.frazionz.gui.renderer.fonts.FontRenderer(this.mc.FONT_LOCATION, (float) (height*1.5));
        	}
      	}
      	
        this.chatTitle = I18n.format("options.chat.title");
        int i = 0;

        for (GameSettings.Options gamesettings$options : CHAT_OPTIONS)
        {
            if (gamesettings$options.getEnumFloat())
            {
            	if(this.mc.gameSettings.frazionz_ui)
            		this.buttonList.add(new GuiRoundedSlider(gamesettings$options.returnEnumOrdinal(),  i%2 == 0 ? this.width / 2 - width - (width / 4) : this.width / 2 + (width / 4), y + height + (int)((2+i/2)*h), width, height , "" , this.mc.fontrenderer, 1, gamesettings$options, 0.0F, 1.0F));
            	else
            		this.buttonList.add(new GuiOptionSlider(gamesettings$options.returnEnumOrdinal(), this.width / 2 - 155 + i % 2 * 160, this.height / 6 + 24 * (i >> 1), gamesettings$options));
            }
            else
            {
            	if(this.mc.gameSettings.frazionz_ui) {
            		GuiRoundedOptionsButton guioptionbutton = new GuiRoundedOptionsButton(gamesettings$options.returnEnumOrdinal(), i%2 == 0 ? this.width / 2 - width - (width / 4) : this.width / 2 + (width / 4), y + height + (int)((2+i/2)*h), width, height, gamesettings$options, this.game_settings.getKeyBinding(gamesettings$options), this.mc.fontrenderer, 1);
                    this.buttonList.add(guioptionbutton);

                    if (gamesettings$options == GameSettings.Options.NARRATOR)
                    {
                        this.roundedOptionsButton = guioptionbutton;
                        guioptionbutton.enabled = NarratorChatListener.field_193643_a.func_193640_a();
                    }
            	}
                else {
                	GuiOptionButton guioptionbutton = new GuiOptionButton(gamesettings$options.returnEnumOrdinal(), this.width / 2 - 155 + i % 2 * 160, this.height / 6 + 24 * (i >> 1), gamesettings$options, this.game_settings.getKeyBinding(gamesettings$options));
                    this.buttonList.add(guioptionbutton);

                    if (gamesettings$options == GameSettings.Options.NARRATOR)
                    {
                        this.field_193025_i = guioptionbutton;
                        guioptionbutton.enabled = NarratorChatListener.field_193643_a.func_193640_a();
                    }
                }

            }

            ++i;
        }

        if(this.mc.gameSettings.frazionz_ui)
            this.buttonList.add(new GuiRoundedButton(200, this.width / 2 - (width / 2), y + height + (int)(11*h), width, height, I18n.format("gui.done"), false, this.mc.fontrenderer, 1));
        else
        	this.buttonList.add(new GuiButton(200, this.width / 2 - 100, this.height / 6 + 144, I18n.format("gui.done")));
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
            if (button.id < 100 && (button instanceof GuiOptionButton || button instanceof GuiRoundedOptionsButton))
            {
            	if(button instanceof GuiRoundedOptionsButton) {
                    this.game_settings.setOptionValue(((GuiRoundedOptionsButton)button).returnEnumOptions(), 1);
                    button.displayString = this.game_settings.getKeyBinding(GameSettings.Options.getEnumOptions(button.id));
            	}
            	else {
                    this.game_settings.setOptionValue(((GuiOptionButton)button).returnEnumOptions(), 1);
                    button.displayString = this.game_settings.getKeyBinding(GameSettings.Options.getEnumOptions(button.id));
            	}
            }

            if (button.id == 200)
            {
                this.mc.gameSettings.saveOptions();
                this.mc.displayGuiScreen(this.parentScreen);
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
    	int h = height + height/3;
    	
        this.drawDefaultBackground();
        
        if(this.mc.gameSettings.frazionz_ui) {
            this.drawRect(this.width/8, this.height/8, this.width - this.width/8, this.height - this.height/20, this.BACKGROUND_COLOR);
            this.drawGradientRect(this.width/8 - ok, y, this.width - this.width/8 + ok, y + height, this.FIRST_GRADIENT_COLOR, this.SECOND_GRADIENT_COLOR);
            this.mc.fontrendererTitle.drawCenteredString(this.chatTitle, this.width / 2, y + (height-1)/2, 0xFFFFFFFF); // DRAW Gui Tittle
        }
        else {
            this.drawCenteredString(this.fontRendererObj, this.chatTitle, this.width / 2, 20, 16777215);
        }
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    public void func_193024_a()
    {
    	if(this.mc.gameSettings.frazionz_ui) {
            this.roundedOptionsButton.displayString = this.game_settings.getKeyBinding(GameSettings.Options.getEnumOptions(this.roundedOptionsButton.id));
    	}
    	else {
            this.field_193025_i.displayString = this.game_settings.getKeyBinding(GameSettings.Options.getEnumOptions(this.field_193025_i.id));
    	}
    }
}
