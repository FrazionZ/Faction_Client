package net.minecraft.client.gui;

import java.io.IOException;

import fz.frazionz.gui.GuiFzBaseScreen;
import fz.frazionz.gui.buttons.GuiFzButton;
import fz.frazionz.gui.buttons.GuiFzChoiceButton;
import fz.frazionz.gui.buttons.GuiFzOptionButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;

public class GuiScreenOptionsSounds extends GuiFzBaseScreen
{
    /** Reference to the GameSettings object. */
    private final GameSettings game_settings_4;
    private String offDisplayString;

    public GuiScreenOptionsSounds(GuiScreen parentIn, GameSettings settingsIn)
    {
        super(parentIn);
        this.game_settings_4 = settingsIn;
    }

    /**
     * Adds the buttons (and other controls) to the screen in question. Called when the GUI is displayed and when the
     * window resizes, the buttonList is cleared beforehand.
     */
    public void initGui()
    {
    	super.initGui();
        this.title = I18n.format("options.sounds.title");
        this.title = I18n.format("options.sounds.title");
        this.offDisplayString = I18n.format("options.off");
    	
        int i = 0;
	    this.buttonList.add(new GuiScreenOptionsSounds.Button(SoundCategory.MASTER.ordinal(), this.width/2 - 155 + i % 2 * 160, this.height/2 - 82 + 24 * (i >> 1), SoundCategory.MASTER, true));
	    i = i + 2;
	
	    for (SoundCategory soundcategory : SoundCategory.values())
	    {
	        if (soundcategory != SoundCategory.MASTER)
	        {
	            this.buttonList.add(new GuiScreenOptionsSounds.Button(soundcategory.ordinal(), this.width/2 - 155 + i % 2 * 160, this.height/2- 82 + 24 * (i >> 1), soundcategory, false));
	            ++i;
	        }
	    }
	
	    int j = this.width / 2 - 75;
	    int k = this.height/2 - 82;
	    ++i;
	    this.buttonList.add(new GuiFzOptionButton(201, j, k + 24 * (i >> 1), GameSettings.Options.SHOW_SUBTITLES, I18n.format(GameSettings.Options.SHOW_SUBTITLES.getTranslation()), this.game_settings_4.getKeyBinding(GameSettings.Options.SHOW_SUBTITLES)));
	    this.buttonList.add(new GuiFzButton(200, this.width/2 - 100, this.height/2 + 86, I18n.format("gui.done")));
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
            if (button.id == 200)
            {
                this.mc.gameSettings.saveOptions();
                this.mc.displayGuiScreen(this.lastScreen);
            }
            else if (button.id == 201)
            {
                this.mc.gameSettings.setOptionValue(GameSettings.Options.SHOW_SUBTITLES, 1);
                ((GuiFzOptionButton)button).setInfo(this.mc.gameSettings.getKeyBinding(GameSettings.Options.SHOW_SUBTITLES));
                this.mc.gameSettings.saveOptions();
            }
        }
    }

    protected String getDisplayString(SoundCategory category)
    {
        float f = this.game_settings_4.getSoundLevel(category);
        return f == 0.0F ? "Off" : (int)(f * 100.0F) + "%";
    }

    class Button extends GuiFzButton
    {
        private final SoundCategory category;
        private final String categoryName;
        public float volume = 1.0F;
        public boolean pressed;

        public Button(int buttonId, int x, int y, SoundCategory categoryIn, boolean master)
        {
            super(buttonId, x, y, master ? 310 : 150, 20, "");
            this.category = categoryIn;
            this.categoryName = I18n.format("soundCategory." + categoryIn.getName());
            this.displayString = this.categoryName + ": " + GuiScreenOptionsSounds.this.getDisplayString(categoryIn);
            this.volume = GuiScreenOptionsSounds.this.game_settings_4.getSoundLevel(categoryIn);
        }

        protected int getHoverState(boolean mouseOver)
        {
            return 1;
        }

        protected void mouseDragged(Minecraft mc, int mouseX, int mouseY)
        {
            if (this.visible)
            {
                if (this.pressed)
                {
                    this.volume = (float)(mouseX - (this.x + 4)) / (float)(this.width - 8);
                    this.volume = MathHelper.clamp(this.volume, 0.0F, 1.0F);
                    mc.gameSettings.setSoundLevel(this.category, this.volume);
                    mc.gameSettings.saveOptions();
                    this.displayString = this.categoryName + ": " + GuiScreenOptionsSounds.this.getDisplayString(this.category);
                }

                mc.getTextureManager().bindTexture(INTERFACE_BACKGROUND_2);
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                this.drawModalRectWithCustomSizedTexture(this.x + (int)(volume * (float)(width-8)), this.y, 200+(this.hovered?8:0), 117, 8, height/2, 512.0F, 512.0F);
                this.drawModalRectWithCustomSizedTexture(this.x + (int)(volume * (float)(width-8)), this.y + height/2, 200+(this.hovered?8:0), 117 + (30-height/2), 8, height/2, 512.0F, 512.0F);
            }
        }

        public boolean mousePressed(Minecraft mc, int mouseX, int mouseY)
        {
            if (super.mousePressed(mc, mouseX, mouseY))
            {
                this.volume = (float)(mouseX - (this.x + 4)) / (float)(this.width - 8);
                this.volume = MathHelper.clamp(this.volume, 0.0F, 1.0F);
                mc.gameSettings.setSoundLevel(this.category, this.volume);
                mc.gameSettings.saveOptions();
                this.displayString = this.categoryName + ": " + GuiScreenOptionsSounds.this.getDisplayString(this.category);
                this.pressed = true;
                return true;
            }
            else
            {
                return false;
            }
        }

        public void playPressSound(SoundHandler soundHandlerIn)
        {
        }

        public void mouseReleased(int mouseX, int mouseY)
        {
            if (this.pressed)
            {
                GuiScreenOptionsSounds.this.mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
            }

            this.pressed = false;
        }
    }
}
