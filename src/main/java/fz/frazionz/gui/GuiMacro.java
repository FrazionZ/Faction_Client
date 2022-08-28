package fz.frazionz.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;

import fz.frazionz.gui.buttons.GuiFzButton;
import fz.frazionz.utils.data.FzUserData;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;

public class GuiMacro extends GuiFzBaseScreen
{
    private List<GuiTextField> macroFields = new ArrayList<GuiTextField>();;

    private GuiScreen parentGuiScreen;

    public GuiMacro(GuiScreen lastScreen)
    {
    	super(lastScreen);
    }

    /**
     * Called from the main game loop to update the screen.
     */
    public void updateScreen()
    {
    	for(GuiTextField macro : this.macroFields) {
    		macro.updateCursorCounter();
    	}
    }

    /**
     * Adds the buttons (and other controls) to the screen in question. Called when the GUI is displayed and when the
     * window resizes, the buttonList is cleared beforehand.
     */
    public void initGui()
    {
    	this.macroFields.clear();
    	this.title = "Macro";

        int x = (this.width - this.xSize) / 2;
        int z = (this.height - this.ySize) / 2;
        this.macroFields.add(new GuiFzTextField(0, width/2 - 100, height/2 - 80, 200, 30, "1. /", this.fontRenderer));
        this.macroFields.add(new GuiFzTextField(0, width/2 - 100, height/2 - 46, 200, 30, "2. /", this.fontRenderer));
        this.macroFields.add(new GuiFzTextField(0, width/2 - 100, height/2 - 12, 200, 30, "3. /", this.fontRenderer));
        this.macroFields.add(new GuiFzTextField(0, width/2 - 100, height/2 + 22, 200, 30, "4. /", this.fontRenderer));
            
        this.buttonList.add(new GuiFzButton(200, this.width / 2 - 100, this.height / 2 + 86, I18n.format("gui.done")));
    	        
        for(int i = 0; i < this.macroFields.size(); i++) {
        	GuiTextField macro = this.macroFields.get(i);
            macro.setMaxStringLength(64);
            macro.setText(FzUserData.getInstance().getMacro(i));
        }
        
        super.initGui();
    }

    /**
     * Called when the screen is unloaded. Used to disable keyboard repeat events
     */
    public void onGuiClosed()
    {
        Keyboard.enableRepeatEvents(false);
    }
    
    private void setMacro() {
        for(int i = 0; i < this.macroFields.size(); i++) {
        	FzUserData.getInstance().setMacro(i, this.macroFields.get(i).getText());
        }
    }

    /**
     * Called by the controls from the buttonList when activated. (Mouse pressed for buttons)
     */
    protected void actionPerformed(GuiButton button, int mouseButton) throws IOException
    {
        switch (button.id)
        {
            case 200:
            	FzUserData.getInstance().setMacro(0, this.macroFields.get(0).getText());
            	FzUserData.getInstance().setMacro(1, this.macroFields.get(1).getText());
            	FzUserData.getInstance().setMacro(2, this.macroFields.get(2).getText());
            	FzUserData.getInstance().setMacro(3, this.macroFields.get(3).getText());
                this.mc.gameSettings.saveOptions();
                this.mc.displayGuiScreen(this.lastScreen);
        }
    }

    /**
     * Fired when a key is typed (except F11 which toggles full screen). This is the equivalent of
     * KeyListener.keyTyped(KeyEvent e). Args : character (character on the key), keyCode (lwjgl Keyboard key code)
     */
    protected void keyTyped(char typedChar, int keyCode) throws IOException
    {
    	for(GuiTextField macroField : this.macroFields) {
    		macroField.textboxKeyTyped(typedChar, keyCode);
    	}

        if (keyCode == 1)
        {
            this.mc.displayGuiScreen((GuiScreen)null);

            if (this.mc.currentScreen == null)
            {
                this.mc.setIngameFocus();
                this.setMacro();
            }
        }
    }

    /**
     * Called when the mouse is clicked. Args : mouseX, mouseY, clickedButton
     */
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
    {
        super.mouseClicked(mouseX, mouseY, mouseButton);
    	for(GuiTextField macroField : this.macroFields) {
    		macroField.mouseClicked(mouseX, mouseY, mouseButton);
    	}
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
    	super.drawScreen(mouseX, mouseY, partialTicks);
    	
        for(GuiTextField macroField : this.macroFields) {
        	macroField.drawTextBox();
        }
    }
}
