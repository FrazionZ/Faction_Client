package net.minecraft.client.gui;

import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;

public class GuiScreenOptionsSounds extends GuiScreen
{
    private final GuiScreen parent;

    /** Reference to the GameSettings object. */
    private final GameSettings game_settings_4;
    protected String title = "Options";
    private String offDisplayString;

    public GuiScreenOptionsSounds(GuiScreen parentIn, GameSettings settingsIn)
    {
        this.parent = parentIn;
        this.game_settings_4 = settingsIn;
    }

    /**
     * Adds the buttons (and other controls) to the screen in question. Called when the GUI is displayed and when the
     * window resizes, the buttonList is cleared beforehand.
     */
    public void initGui()
    {
        this.title = I18n.format("options.sounds.title");
        this.offDisplayString = I18n.format("options.off");
    	
    	if(this.mc.gameSettings.frazionz_ui) {
        	int width = this.width/5;
        	int height = this.height / 19;
        	int h = height + height/3;
        	int x = this.width/16;
        	int y = (this.height / 22);
        	
        	if(height != this.mc.fontrenderer.getSize()) {
        		this.mc.fontrenderer = new fz.frazionz.gui.renderer.fonts.FontRenderer(this.mc.FONT_LOCATION, height);
        	}
        	if((int) (height*1.5) != this.mc.fontrendererTitle.getSize()) {
        		this.mc.fontrendererTitle = new fz.frazionz.gui.renderer.fonts.FontRenderer(this.mc.FONT_LOCATION, (float) (height*1.5));
        	}
        	
            int i = 2;
            this.buttonList.add(new GuiScreenOptionsSounds.RoundedButton(SoundCategory.MASTER.ordinal(), this.width / 2 - width - (width / 4), y + height + h*2, width*2 + (width / 4)*2 , height, this.mc.fontrenderer, SoundCategory.MASTER, 0.0F, 1.0F));

            for (SoundCategory soundcategory : SoundCategory.values())
            {
                if (soundcategory != SoundCategory.MASTER)
                {
                    this.buttonList.add(new GuiScreenOptionsSounds.RoundedButton(soundcategory.ordinal(), i%2 == 0 ? this.width / 2 - width - (width / 4) : this.width / 2 + (width / 4), y + height + (int)((2+i/2)*h), width, height, this.mc.fontrenderer, soundcategory, 0.0F, 1.0F));
                    ++i;
                }
            }

            int j = this.width / 2 - 75;
            int k = this.height / 6 - 12;
            ++i;
            this.buttonList.add(new GuiRoundedOptionsButton(201, this.width / 2 - (width / 2),y + height + (int)(8*h), width, height, GameSettings.Options.SHOW_SUBTITLES, this.game_settings_4.getKeyBinding(GameSettings.Options.SHOW_SUBTITLES), this.mc.fontrenderer, 1));
            this.buttonList.add(new GuiRoundedButton(200, this.width / 2 - (width / 2), y + height + (int)(11*h), width, height, I18n.format("gui.done"), false, this.mc.fontrenderer, 1));
        
    	}
    	else {
            int i = 0;
            this.buttonList.add(new GuiScreenOptionsSounds.Button(SoundCategory.MASTER.ordinal(), this.width / 2 - 155 + i % 2 * 160, this.height / 6 - 12 + 24 * (i >> 1), SoundCategory.MASTER, true));
            i = i + 2;

            for (SoundCategory soundcategory : SoundCategory.values())
            {
                if (soundcategory != SoundCategory.MASTER)
                {
                    this.buttonList.add(new GuiScreenOptionsSounds.Button(soundcategory.ordinal(), this.width / 2 - 155 + i % 2 * 160, this.height / 6 - 12 + 24 * (i >> 1), soundcategory, false));
                    ++i;
                }
            }

            int j = this.width / 2 - 75;
            int k = this.height / 6 - 12;
            ++i;
            this.buttonList.add(new GuiOptionButton(201, j, k + 24 * (i >> 1), GameSettings.Options.SHOW_SUBTITLES, this.game_settings_4.getKeyBinding(GameSettings.Options.SHOW_SUBTITLES)));
            this.buttonList.add(new GuiButton(200, this.width / 2 - 100, this.height / 6 + 168, I18n.format("gui.done")));
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
    protected void actionPerformed(GuiButton button, int mouseButton) throws IOException
    {
        if (button.enabled)
        {
            if (button.id == 200)
            {
                this.mc.gameSettings.saveOptions();
                this.mc.displayGuiScreen(this.parent);
            }
            else if (button.id == 201)
            {
                this.mc.gameSettings.setOptionValue(GameSettings.Options.SHOW_SUBTITLES, 1);
                button.displayString = this.mc.gameSettings.getKeyBinding(GameSettings.Options.SHOW_SUBTITLES);
                this.mc.gameSettings.saveOptions();
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
        	//this.fontrenderer.setSize(height);
        	int ok = this.width/26;
        	int y = this.height / 22;
        
            this.drawRect(this.width/8, this.height/8, this.width - this.width/8, this.height - this.height/20, this.BACKGROUND_COLOR);
            this.drawGradientRect(this.width/8 - ok, y, this.width - this.width/8 + ok, y + height, this.FIRST_GRADIENT_COLOR, this.SECOND_GRADIENT_COLOR);
            this.mc.fontrendererTitle.drawCenteredString(this.title, this.width / 2, y + (height-1)/2, 0xFFFFFFFF);
    	}
    	else {
            this.drawCenteredString(this.fontRendererObj, this.title, this.width / 2, 15, 16777215);
    	}
        
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    protected String getDisplayString(SoundCategory category)
    {
        float f = this.game_settings_4.getSoundLevel(category);
        return f == 0.0F ? this.offDisplayString : (int)(f * 100.0F) + "%";
    }

    class RoundedButton extends GuiButton
    {
        private final SoundCategory category;
        private final String categoryName;
        private final float minValue;
        private final float maxValue;
        public boolean dragging;
        private float sliderValue = 1.0F;
    	private fz.frazionz.gui.renderer.fonts.FontRenderer fontrenderer;

        /*public Button(int p_i46744_2_, int x, int y, SoundCategory categoryIn, boolean master)
        {
            super(p_i46744_2_, x, y, master ? 310 : 150, 20, "");
            this.category = categoryIn;
            this.categoryName = I18n.format("soundCategory." + categoryIn.getName());
            this.displayString = this.categoryName + ": " + GuiScreenOptionsSounds.this.getDisplayString(categoryIn);
            this.volume = GuiScreenOptionsSounds.this.game_settings_4.getSoundLevel(categoryIn);
        }*/
        
        public RoundedButton(int buttonId, int x, int y, int widthIn, int heightIn, fz.frazionz.gui.renderer.fonts.FontRenderer fontrenderer, SoundCategory categoryIn, float minValueIn, float maxValue) {
    		super(buttonId, x, y, widthIn, heightIn, "");
    		this.fontrenderer = fontrenderer;
    		
    		this.sliderValue = GuiScreenOptionsSounds.this.game_settings_4.getSoundLevel(categoryIn);
            this.minValue = minValueIn;
            this.maxValue = maxValue;

            this.category = categoryIn;
            this.categoryName = I18n.format("soundCategory." + categoryIn.getName());
            this.displayString = this.categoryName + ": " + GuiScreenOptionsSounds.this.getDisplayString(categoryIn);
            this.sliderValue = GuiScreenOptionsSounds.this.game_settings_4.getSoundLevel(categoryIn);
    	}

        protected int getHoverState(boolean mouseOver)
        {
            return 0;
        }
        
    	public void func_191745_a(Minecraft mc, int mouseX, int mouseY, float a)
        {
            if (this.visible)
            {                   		
                //normal rectangle
                this.drawRoundedButton(this.xPosition, this.yPosition, this.xPosition + this.width, this.yPosition + this.height, 0xFF101010);
                
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                
                this.hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
                int i = this.getHoverState(this.hovered);
                
                GlStateManager.enableBlend();
                GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
                GlStateManager.blendFunc(770, 771);
                
                //default stuff from GuiButton.java
                this.mouseDragged(mc, mouseX, mouseY);
                
                this.fontrenderer.drawCenteredString(this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height -1) / 2, 0xFFFFFFFF);
            }
        }

        protected void mouseDragged(Minecraft mc, int mouseX, int mouseY)
        {
            if (this.visible)
            {
                if (this.dragging)
                {
                    this.sliderValue = (float)(mouseX - (this.xPosition + 4)) / (float)(this.width - 8);
                    this.sliderValue = MathHelper.clamp(this.sliderValue, 0.0F, 1.0F);
                    mc.gameSettings.setSoundLevel(this.category, this.sliderValue);
                    mc.gameSettings.saveOptions();
                    this.displayString = this.categoryName + ": " + GuiScreenOptionsSounds.this.getDisplayString(this.category);
                }

                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                this.drawRoundedSliderButton(this.xPosition + (int)(this.sliderValue * (float)(this.width - this.width / 18)), this.yPosition, this.xPosition + (int)(this.sliderValue * (float)(this.width - this.width / 18)) + this.width / 18, this.yPosition + this.height, 0xFFFEA801);
            }
        }

        public boolean mousePressed(Minecraft mc, int mouseX, int mouseY)
        {
            if (super.mousePressed(mc, mouseX, mouseY))
            {
                this.sliderValue = (float)(mouseX - (this.xPosition + 4)) / (float)(this.width - 8);
                this.sliderValue = MathHelper.clamp(this.sliderValue, 0.0F, 1.0F);
                mc.gameSettings.setSoundLevel(this.category, this.sliderValue);
                mc.gameSettings.saveOptions();
                this.displayString = this.categoryName + ": " + GuiScreenOptionsSounds.this.getDisplayString(this.category);
                this.dragging = true;
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
            if (this.dragging)
            {
                GuiScreenOptionsSounds.this.mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
            }

            this.dragging = false;
        }
    }
    
    class Button extends GuiButton
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
            return 0;
        }

        protected void mouseDragged(Minecraft mc, int mouseX, int mouseY)
        {
            if (this.visible)
            {
                if (this.pressed)
                {
                    this.volume = (float)(mouseX - (this.xPosition + 4)) / (float)(this.width - 8);
                    this.volume = MathHelper.clamp(this.volume, 0.0F, 1.0F);
                    mc.gameSettings.setSoundLevel(this.category, this.volume);
                    mc.gameSettings.saveOptions();
                    this.displayString = this.categoryName + ": " + GuiScreenOptionsSounds.this.getDisplayString(this.category);
                }

                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                this.drawTexturedModalRect(this.xPosition + (int)(this.volume * (float)(this.width - 8)), this.yPosition, 0, 66, 4, 20);
                this.drawTexturedModalRect(this.xPosition + (int)(this.volume * (float)(this.width - 8)) + 4, this.yPosition, 196, 66, 4, 20);
            }
        }

        public boolean mousePressed(Minecraft mc, int mouseX, int mouseY)
        {
            if (super.mousePressed(mc, mouseX, mouseY))
            {
                this.volume = (float)(mouseX - (this.xPosition + 4)) / (float)(this.width - 8);
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
