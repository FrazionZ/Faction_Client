package fz.frazionz.gui;

import java.io.IOException;

import fz.frazionz.gui.buttons.GuiFzButton;
import fz.frazionz.gui.renderer.fonts.FzFontRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiKeyBindingList;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;

public class GuiKeyBinds extends GuiScreen
{
    private static final GameSettings.Options[] OPTIONS_ARR = new GameSettings.Options[] {GameSettings.Options.INVERT_MOUSE, GameSettings.Options.SENSITIVITY, GameSettings.Options.TOUCHSCREEN, GameSettings.Options.AUTO_JUMP};

    /**
     * A reference to the screen object that created this. Used for navigating between screens.
     */
    private final GuiScreen parentScreen;
    protected String title = "Key Binds";

    /** Reference to the GameSettings object. */
    private final GameSettings options;

    /** The ID of the button that has been pressed. */
    public KeyBinding buttonId;
    public long time;
    private GuiKeyBindingList keyBindingList;
    private GuiButton buttonReset;

    public GuiKeyBinds(GuiScreen screen, GameSettings settings)
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
    	this.title = I18n.format("controls.title");
    	
	    this.keyBindingList = new GuiKeyBindingList(this, this.mc, 0, width, height/8, height-height/8, 20);
	    this.buttonList.add(new GuiFzButton(200, this.width / 2 - 155 + 160, this.height - this.height/16-10, 150, 20, I18n.format("gui.done")));
	    this.buttonReset = this.addButton(new GuiFzButton(201, this.width / 2 - 155, this.height - this.height/16-10, 150, 20, I18n.format("controls.resetAll")));
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
    protected void actionPerformed(GuiButton button, int keyCode) throws IOException
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

    @Override
    public void drawDefaultBackground() {
    
    	this.drawRect(0, 0, width, height, BLACK_3);
    	
    }
    
    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        
        this.keyBindingList.drawScreen(mouseX, mouseY, partialTicks);
	    boolean flag = false;
	
	    for (KeyBinding keybinding : this.options.keyBindings)
	    {
	        if (keybinding.getKeyCode() != keybinding.getKeyCodeDefault())
	        {
	            flag = true;
	            break;
	        }
	    }
	    
        this.drawRect(0, 0, this.width, this.height/8, this.BLACK_4);
	    this.drawRect(0, this.height - this.height/8, this.width, this.height, this.BLACK_4);
	    
        FzFontRenderer titleRenderer = this.mc.fzFontRenderers.get(24);
        int titleSize = titleRenderer.getStringWidth(this.title);
        titleRenderer.drawCenteredString(this.title, this.width / 2, this.height/16, 0xFFFFFFFF);
	
	    this.buttonReset.enabled = flag;
	    
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
