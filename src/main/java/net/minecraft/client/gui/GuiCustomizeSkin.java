package net.minecraft.client.gui;

import java.io.IOException;

import fz.frazionz.client.gui.GuiFzBaseScreen;
import fz.frazionz.client.gui.buttons.GuiFzChoiceButton;
import fz.frazionz.client.gui.buttons.GuiFzOptionButton;
import fz.frazionz.client.gui.buttons.GuiHoverButton;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.player.EnumPlayerModelParts;
import net.optifine.gui.GuiScreenCapeOF;

public class GuiCustomizeSkin extends GuiFzBaseScreen
{
    public GuiCustomizeSkin(GuiScreen lastScreen)
    {
    	super(lastScreen);
    }

    /**
     * Adds the buttons (and other controls) to the screen in question. Called when the GUI is displayed and when the
     * window resizes, the buttonList is cleared beforehand.
     */
    public void initGui()
    {
    	super.initGui();
        int i = 0;
        this.title = I18n.format("options.skinCustomisation.title");

        for (EnumPlayerModelParts enumplayermodelparts : EnumPlayerModelParts.values())
        {
        	this.buttonList.add(new GuiCustomizeSkin.ButtonPart(enumplayermodelparts.getPartId(), this.width/2 - 155 + i % 2 * 160, this.height/2 - 82 + 24 * (i >> 1), 150, 20, enumplayermodelparts));
            ++i;
        }

        this.buttonList.add(new GuiFzOptionButton(199, this.width/2 - 155 + i % 2 * 160, this.height/2 - 82 + 24 * (i >> 1), GameSettings.Options.MAIN_HAND, I18n.format(GameSettings.Options.MAIN_HAND.getTranslation()), this.mc.gameSettings.getKeyBinding(GameSettings.Options.MAIN_HAND)));
        ++i;

        if (i % 2 == 1)
        {
            ++i;
        }

        this.buttonList.add(new GuiHoverButton(200, this.width / 2 - 100, this.height / 2 + 86, I18n.format("gui.done")));
        //this.buttonList.add(new GuiFzButton(210, this.width/2 - 100, this.height / 6 + 24 * (i >> 1), I18n.format("of.options.skinCustomisation.ofCape")));
        i = i + 2;   
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
            if (button.id == 210)
            {
                this.mc.displayGuiScreen(new GuiScreenCapeOF(this));
            }

            if (button.id == 200)
            {
                this.mc.gameSettings.saveOptions();
                this.mc.displayGuiScreen(this.lastScreen);
            }
            else if (button.id == 199)
            {
                this.mc.gameSettings.setOptionValue(GameSettings.Options.MAIN_HAND, 1);
                ((GuiFzOptionButton)button).setInfo(this.mc.gameSettings.getKeyBinding(GameSettings.Options.MAIN_HAND));
                this.mc.gameSettings.sendSettingsToServer();
            }
            else if (button instanceof GuiCustomizeSkin.ButtonPart)
            {
                EnumPlayerModelParts enumplayermodelparts = ((GuiCustomizeSkin.ButtonPart)button).playerModelParts;
                this.mc.gameSettings.switchModelPartEnabled(enumplayermodelparts);
                ((GuiCustomizeSkin.ButtonPart) button).setInfo(this.getMessage(enumplayermodelparts));
            }
        }
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

        return s;
    }
    
    class ButtonPart extends GuiFzChoiceButton
    {
        private final EnumPlayerModelParts playerModelParts;

        private ButtonPart(int buttonId, int x, int y, int width, int height, EnumPlayerModelParts playerModelParts)
        {
            super(buttonId, x, y, width, height, playerModelParts.getName().getFormattedText(), GuiCustomizeSkin.this.getMessage(playerModelParts));
            this.playerModelParts = playerModelParts;
        }
    }
}
