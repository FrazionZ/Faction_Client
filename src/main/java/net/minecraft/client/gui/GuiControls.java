package net.minecraft.client.gui;

import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;

public class GuiControls extends GuiScreen
{
    private static final GameSettings.Options[] OPTIONS_ARR = new GameSettings.Options[] {GameSettings.Options.INVERT_MOUSE, GameSettings.Options.SENSITIVITY, GameSettings.Options.TOUCHSCREEN, GameSettings.Options.AUTO_JUMP};

    /**
     * A reference to the screen object that created this. Used for navigating between screens.
     */
    private final GuiScreen parentScreen;
    protected String screenTitle = "Controls";

    /** Reference to the GameSettings object. */
    private final GameSettings options;

    /** The ID of the button that has been pressed. */
    public KeyBinding buttonId;
    public long time;
    private GuiKeyBindingList keyBindingList;
    private GuiButton buttonReset;
    private GuiRoundedButton roundedButtonReset;

    public GuiControls(GuiScreen screen, GameSettings settings)
    {
        this.parentScreen = screen;
        this.options = settings;
    }

    /**
     * Adds the buttons (and other controls) to the screen in question. Called when the GUI is displayed and when the
     * window resizes, the buttonList is cleared beforehand.
     */
    public void initGui()
    {
        this.screenTitle = I18n.format("controls.title");
    	
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
        	
    		this.keyBindingList = new GuiKeyBindingList(this, this.mc, this.width/2, this.width - this.width/8, y + height + (int)(1.2*h), y + height + (int)(10.4*h), 20, true);
    		
            this.buttonList.add(new GuiRoundedButton(200, this.width / 2 + (width / 4), y + height + (int)(11*h), width, height, I18n.format("gui.done"), false, this.mc.fontrenderer, 1));	
    		this.roundedButtonReset = this.addButton(new GuiRoundedButton(201, this.width / 2 - width - (width / 4), y + height + (int)(11*h), width, height, I18n.format("controls.resetAll"), false , this.mc.fontrenderer, 1));
    	
            int i = 0;

            for (GameSettings.Options gamesettings$options : OPTIONS_ARR)
            {
                if (gamesettings$options.getEnumFloat()) {
                    this.buttonList.add(new GuiRoundedSlider(gamesettings$options.returnEnumOrdinal(),this.width/8 + (this.width/2 - this.width/8)/2 - width/2, y + height + (int)((3+i)*h), width, height, "", this.mc.fontrenderer, 1, gamesettings$options, 0.0F, 1.0F));
                }
                else {
                    this.buttonList.add(new GuiRoundedOptionsButton(gamesettings$options.returnEnumOrdinal(), this.width/8 + (this.width/2 - this.width/8 )/2 - width/2, y + height + (int)((3+i)*h), width, height, gamesettings$options, this.options.getKeyBinding(gamesettings$options), this.mc.fontrenderer, 1));
                }

                ++i;
            }
    	}
    	else {
            this.keyBindingList = new GuiKeyBindingList(this, this.mc);
            this.buttonList.add(new GuiButton(200, this.width / 2 - 155 + 160, this.height - 29, 150, 20, I18n.format("gui.done")));
            this.buttonReset = this.addButton(new GuiButton(201, this.width / 2 - 155, this.height - 29, 150, 20, I18n.format("controls.resetAll")));
            
            int i = 0;

            for (GameSettings.Options gamesettings$options : OPTIONS_ARR)
            {
                if (gamesettings$options.getEnumFloat())
                {
                    this.buttonList.add(new GuiOptionSlider(gamesettings$options.returnEnumOrdinal(), this.width / 2 - 155 + i % 2 * 160, 18 + 24 * (i >> 1), gamesettings$options));
                }
                else
                {
                    this.buttonList.add(new GuiOptionButton(gamesettings$options.returnEnumOrdinal(), this.width / 2 - 155 + i % 2 * 160, 18 + 24 * (i >> 1), gamesettings$options, this.options.getKeyBinding(gamesettings$options)));
                }

                ++i;
            }
    	}
    }

    /**
     * Handles mouse input.
     */
    public void handleMouseInput() throws IOException
    {
        super.handleMouseInput();
        this.keyBindingList.handleMouseInput();
    }

    /**
     * Called by the controls from the buttonList when activated. (Mouse pressed for buttons)
     */
    protected void actionPerformed(GuiButton button, int mouseButton) throws IOException
    {
        if (button.id == 200)
        {
            this.mc.displayGuiScreen(this.parentScreen);
        }
        else if (button.id == 201)
        {
            for (KeyBinding keybinding : this.mc.gameSettings.keyBindings)
            {
                keybinding.setKeyCode(keybinding.getKeyCodeDefault());
            }

            KeyBinding.resetKeyBindingArrayAndHash();
        }
        else if (button.id < 100 && button instanceof GuiOptionButton)
        {
            this.options.setOptionValue(((GuiOptionButton)button).returnEnumOptions(), 1);
            button.displayString = this.options.getKeyBinding(GameSettings.Options.getEnumOptions(button.id));
        }
        else if (button.id < 100 && button instanceof GuiRoundedOptionsButton)
        {
            this.options.setOptionValue(((GuiRoundedOptionsButton)button).returnEnumOptions(), 1);
            button.displayString = this.options.getKeyBinding(GameSettings.Options.getEnumOptions(button.id));
        }
    }

    /**
     * Called when the mouse is clicked. Args : mouseX, mouseY, clickedButton
     */
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
    {
        if (this.buttonId != null)
        {
            this.options.setOptionKeyBinding(this.buttonId, -100 + mouseButton);
            this.buttonId = null;
            KeyBinding.resetKeyBindingArrayAndHash();
        }
        else if (mouseButton != 0 || !this.keyBindingList.mouseClicked(mouseX, mouseY, mouseButton))
        {
            super.mouseClicked(mouseX, mouseY, mouseButton);
        }
    }

    /**
     * Called when a mouse button is released.
     */
    protected void mouseReleased(int mouseX, int mouseY, int state)
    {
        if (state != 0 || !this.keyBindingList.mouseReleased(mouseX, mouseY, state))
        {
            super.mouseReleased(mouseX, mouseY, state);
        }
    }

    /**
     * Fired when a key is typed (except F11 which toggles full screen). This is the equivalent of
     * KeyListener.keyTyped(KeyEvent e). Args : character (character on the key), keyCode (lwjgl Keyboard key code)
     */
    protected void keyTyped(char typedChar, int keyCode) throws IOException
    {
        if (this.buttonId != null)
        {
            if (keyCode == 1)
            {
                this.options.setOptionKeyBinding(this.buttonId, 0);
            }
            else if (keyCode != 0)
            {
                this.options.setOptionKeyBinding(this.buttonId, keyCode);
            }
            else if (typedChar > 0)
            {
                this.options.setOptionKeyBinding(this.buttonId, typedChar + 256);
            }

            this.buttonId = null;
            this.time = Minecraft.getSystemTime();
            KeyBinding.resetKeyBindingArrayAndHash();
        }
        else
        {
            super.keyTyped(typedChar, keyCode);
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
        	int basicHeight = this.height / 19;
        	int h = basicHeight + basicHeight/3;
          	int y = (this.height / 22);
        	
            this.drawRect(this.width/8, this.height/8, this.width - this.width/8, this.height - this.height/20, this.BACKGROUND_COLOR);
            this.keyBindingList.drawScreen(mouseX, mouseY, partialTicks);
        	this.drawRect(this.width/8, this.height/8, this.width - this.width/8, y + basicHeight + (int)(1.2*h), this.BACKGROUND_COLOR);
        	this.drawRect(this.width/8, y + basicHeight + (int)(10.4*h), this.width - this.width/8, this.height - this.height/20, this.BACKGROUND_COLOR);
            this.drawGradientRect(this.width/8 - ok, y, this.width - this.width/8 + ok, y + height, this.FIRST_GRADIENT_COLOR, this.SECOND_GRADIENT_COLOR);
            this.mc.fontrendererTitle.drawCenteredString(this.screenTitle, this.width / 2, y + (height-1)/2, 0xFFFFFFFF);
        
            boolean flag = false;

            for (KeyBinding keybinding : this.options.keyBindings)
            {
                if (keybinding.getKeyCode() != keybinding.getKeyCodeDefault())
                {
                    flag = true;
                    break;
                }
            }

            this.roundedButtonReset.enabled = flag;
        }
        else {
            this.keyBindingList.drawScreen(mouseX, mouseY, partialTicks);
            this.drawCenteredString(this.fontRendererObj, this.screenTitle, this.width / 2, 8, 16777215);
            boolean flag = false;

            for (KeyBinding keybinding : this.options.keyBindings)
            {
                if (keybinding.getKeyCode() != keybinding.getKeyCodeDefault())
                {
                    flag = true;
                    break;
                }
            }

            this.buttonReset.enabled = flag;
        }
        
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
