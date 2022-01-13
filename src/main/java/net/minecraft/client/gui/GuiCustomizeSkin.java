package net.minecraft.client.gui;

import java.io.IOException;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.player.EnumPlayerModelParts;

public class GuiCustomizeSkin extends GuiScreen
{
    /** The parent GUI for this GUI */
    private final GuiScreen parentScreen;

    /** The title of the GUI. */
    private String title;

    public GuiCustomizeSkin(GuiScreen parentScreenIn)
    {
        this.parentScreen = parentScreenIn;
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
      	
        int i = 0;
        this.title = I18n.format("options.skinCustomisation.title");

        for (EnumPlayerModelParts enumplayermodelparts : EnumPlayerModelParts.values())
        {
        	if(this.mc.gameSettings.frazionz_ui)
        		this.buttonList.add(new GuiCustomizeSkin.RoundedButtonPart(enumplayermodelparts.getPartId(), i%2 == 0 ? this.width / 2 - width - (width / 4) : this.width / 2 + (width / 4), y + height + (int)((2+i/2)*h), width, height, enumplayermodelparts, false, this.mc.fontrenderer, 1));
        	else
        		this.buttonList.add(new GuiCustomizeSkin.ButtonPart(enumplayermodelparts.getPartId(), this.width / 2 - 155 + i % 2 * 160, this.height / 6 + 24 * (i >> 1), 150, 20, enumplayermodelparts));
            ++i;
        }
        if(this.mc.gameSettings.frazionz_ui)
        	this.buttonList.add(new GuiRoundedOptionsButton(199, i%2 == 0 ? this.width / 2 - width - (width / 4) : this.width / 2 + (width / 4), y + height + (int)((2+i/2)*h), width, height, GameSettings.Options.MAIN_HAND, this.mc.gameSettings.getKeyBinding(GameSettings.Options.MAIN_HAND), this.mc.fontrenderer, 1));
        else
        	this.buttonList.add(new GuiOptionButton(199, this.width / 2 - 155 + i % 2 * 160, this.height / 6 + 24 * (i >> 1), GameSettings.Options.MAIN_HAND, this.mc.gameSettings.getKeyBinding(GameSettings.Options.MAIN_HAND)));
        ++i;

        if (i % 2 == 1)
        {
            ++i;
        }

        if(this.mc.gameSettings.frazionz_ui)
            this.buttonList.add(new GuiRoundedButton(200, this.width / 2 - (width / 2), y + height + (int)(11*h), width, height, I18n.format("gui.done"), false, this.mc.fontrenderer, 1));
        else
        	this.buttonList.add(new GuiButton(200, this.width / 2 - 100, this.height / 6 + 24 * (i >> 1), I18n.format("gui.done")));
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
            if (button.id == 200)
            {
                this.mc.gameSettings.saveOptions();
                this.mc.displayGuiScreen(this.parentScreen);
            }
            else if (button.id == 199)
            {
                this.mc.gameSettings.setOptionValue(GameSettings.Options.MAIN_HAND, 1);
                button.displayString = this.mc.gameSettings.getKeyBinding(GameSettings.Options.MAIN_HAND);
                this.mc.gameSettings.sendSettingsToServer();
            }
            else if (button instanceof GuiCustomizeSkin.ButtonPart)
            {
                EnumPlayerModelParts enumplayermodelparts = ((GuiCustomizeSkin.ButtonPart)button).playerModelParts;
                this.mc.gameSettings.switchModelPartEnabled(enumplayermodelparts);
                button.displayString = this.getMessage(enumplayermodelparts);
            }
            else if (button instanceof GuiCustomizeSkin.RoundedButtonPart)
            {
                EnumPlayerModelParts enumplayermodelparts = ((GuiCustomizeSkin.RoundedButtonPart)button).playerModelParts;
                this.mc.gameSettings.switchModelPartEnabled(enumplayermodelparts);
                button.displayString = this.getMessage(enumplayermodelparts);
            }
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
        	int y = this.height / 22;
        	int h = height + height/3;
        	
            this.drawRect(this.width/8, this.height/8, this.width - this.width/8, this.height - this.height/20, this.BACKGROUND_COLOR);
            this.drawGradientRect(this.width/8 - ok, y, this.width - this.width/8 + ok, y + height, this.FIRST_GRADIENT_COLOR, this.SECOND_GRADIENT_COLOR);
            
            this.mc.fontrendererTitle.drawCenteredString(this.title, this.width / 2, y + (height-1)/2, 0xFFFFFFFF); // DRAW Gui Tittle
        }
        else {
            this.drawCenteredString(this.fontRendererObj, this.title, this.width / 2, 20, 16777215);
        }
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    private String getMessage(EnumPlayerModelParts playerModelParts)
    {
        String s;

        if (this.mc.gameSettings.getModelParts().contains(playerModelParts))
        {
            s = I18n.format("options.on");
        }
        else
        {
            s = I18n.format("options.off");
        }

        return playerModelParts.getName().getFormattedText() + ": " + s;
    }

    class ButtonPart extends GuiButton
    {
        private final EnumPlayerModelParts playerModelParts;

        private ButtonPart(int p_i45514_2_, int p_i45514_3_, int p_i45514_4_, int p_i45514_5_, int p_i45514_6_, EnumPlayerModelParts playerModelParts)
        {
            super(p_i45514_2_, p_i45514_3_, p_i45514_4_, p_i45514_5_, p_i45514_6_, GuiCustomizeSkin.this.getMessage(playerModelParts));
            this.playerModelParts = playerModelParts;
        }
    }
    
    class RoundedButtonPart extends GuiRoundedButton
    {
        private final EnumPlayerModelParts playerModelParts;

        private RoundedButtonPart(int buttonId, int x, int y, int widthIn, int heightIn, EnumPlayerModelParts playerModelParts, boolean quit, fz.frazionz.gui.renderer.fonts.FontRenderer fontrenderer, int hoverInt) {
        	super(buttonId, x, y, widthIn, heightIn, GuiCustomizeSkin.this.getMessage(playerModelParts), quit, fontrenderer, hoverInt);
        	this.playerModelParts = playerModelParts;
        }
    }
}
