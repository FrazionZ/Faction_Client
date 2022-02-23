package net.minecraft.client.gui;

import java.io.IOException;
import java.net.IDN;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.lwjgl.input.Keyboard;

import com.google.common.base.Predicate;

import fz.frazionz.data.FzUserData;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.ResourceLocation;
import optifine.TooltipManager;

public class GuiMacro extends GuiScreen
{
    private List<GuiTextField> macroFields = new ArrayList<GuiTextField>();;
    
    private String title;
    private final GuiScreen lastScreen;
    
    private static final ResourceLocation gui_macro = new ResourceLocation("textures/gui/frazionz/gui_macro.png");

    protected int xSize = 186;
    protected int ySize = 210;

    private GuiScreen parentGuiScreen;

    public GuiMacro(GuiScreen lastScreen)
    {
        this.lastScreen = lastScreen;
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
        	
            this.macroFields.add(new GuiRoundedTextField(0, this.width / 2 - 110, y + height + (int)(3.5*h), 220, height, "1.   /", this.fontRendererObj));
            this.macroFields.add(new GuiRoundedTextField(0, this.width / 2 - 110, y + height + (int)(4.7*h), 220, height, "2.   /", this.fontRendererObj));
            this.macroFields.add(new GuiRoundedTextField(0, this.width / 2 - 110, y + height + (int)(5.9*h), 220, height, "3.   /", this.fontRendererObj));
            this.macroFields.add(new GuiRoundedTextField(0, this.width / 2 - 110, y + height + (int)(7.1*h), 220, height, "4.   /", this.fontRendererObj));
            
            this.buttonList.add(new GuiRoundedButton(200, this.width / 2 - (width / 2), y + height + (int)(11*h), width, height, I18n.format("gui.done"), false, this.mc.fontrenderer, 1));

    	}
    	else {
            int x = (this.width - this.xSize) / 2;
            int z = (this.height - this.ySize) / 2;
            this.macroFields.add(new GuiTextField(0, this.fontRendererObj, x + 16, z + 56, 162, 24));
            this.macroFields.add(new GuiTextField(0, this.fontRendererObj, x + 16, z + 86, 162, 24));
            this.macroFields.add(new GuiTextField(0, this.fontRendererObj, x + 16, z + 116, 162, 24));
            this.macroFields.add(new GuiTextField(0, this.fontRendererObj, x + 16, z + 146, 162, 24));
            
            this.buttonList.add(new GuiButton(200, this.width / 2 - 100, this.height/2 + 126, I18n.format("gui.done")));
    	}
        
        for(int i = 0; i < this.macroFields.size(); i++) {
        	GuiTextField macro = this.macroFields.get(i);
        	if(!this.mc.gameSettings.frazionz_ui)
        		macro.setEnableBackgroundDrawing(false);
            macro.setMaxStringLength(64);
            macro.setText(FzUserData.getInstance().getMacro(i));
        }
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
    		macroField.textboxKeyTyped(typedChar, keyCode, true);
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
        this.drawDefaultBackground();
        if(this.mc.gameSettings.frazionz_ui) {
        	
        	int height = (int)((this.height / 14) * 1.5);
        	int ok = this.width/26;
        	int y = this.height / 22;
        	
            this.drawRect(this.width/8, this.height/8, this.width - this.width/8, this.height - this.height/20, this.BACKGROUND_COLOR);
            this.drawGradientRect(this.width/8 - ok, y, this.width - this.width/8 + ok, y + height, this.FIRST_GRADIENT_COLOR, this.SECOND_GRADIENT_COLOR);
            
        	for(GuiTextField macroField : this.macroFields) {
        		macroField.drawTextBox();
        	}
            this.mc.fontrendererTitle.drawCenteredString(this.title, this.width / 2, y + (height-1)/2, 0xFFFFFFFF);
        }
        else {
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            this.mc.getTextureManager().bindTexture(gui_macro);
            int x = (this.width - this.xSize) / 2;
            int z = (this.height - this.ySize) / 2;
            this.drawTexturedModalRect(x, z, 0, 0, this.xSize, this.ySize);
            
        	for(GuiTextField macroField : this.macroFields) {
        		macroField.drawTextBox();
        	}
        }
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
