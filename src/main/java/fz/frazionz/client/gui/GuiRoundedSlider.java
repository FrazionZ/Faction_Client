package fz.frazionz.client.gui;

import fz.frazionz.TTFFontRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.math.MathHelper;

public class GuiRoundedSlider extends GuiButton
{
    private float sliderValue;
    public boolean dragging;
    private final GameSettings.Options options;
    private final float minValue;
    private final float maxValue;
    
	private TTFFontRenderer fontrenderer;
    
    public GuiRoundedSlider(int buttonId, int x, int y, GameSettings.Options optionIn)
    {
        this(buttonId, x, y, optionIn, 0.0F, 1.0F);
    }
    
    public GuiRoundedSlider(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText, TTFFontRenderer fontrenderer, int hoverInt, GameSettings.Options optionIn, float minValueIn, float maxValue) {
		super(buttonId, x, y, widthIn, heightIn, "");
		this.fontrenderer = fontrenderer;
		
        this.sliderValue = 1.0F;
        this.options = optionIn;
        this.minValue = minValueIn;
        this.maxValue = maxValue;
        Minecraft minecraft = Minecraft.getMinecraft();
        this.sliderValue = optionIn.normalizeValue(minecraft.gameSettings.getOptionFloatValue(optionIn));
        this.displayString = minecraft.gameSettings.getKeyBinding(optionIn);
	}

    public GuiRoundedSlider(int buttonId, int x, int y, GameSettings.Options optionIn, float minValueIn, float maxValue)
    {
        super(buttonId, x, y, 150, 20, "");
        this.sliderValue = 1.0F;
        this.options = optionIn;
        this.minValue = minValueIn;
        this.maxValue = maxValue;
        Minecraft minecraft = Minecraft.getMinecraft();
        this.sliderValue = optionIn.normalizeValue(minecraft.gameSettings.getOptionFloatValue(optionIn));
        this.displayString = minecraft.gameSettings.getKeyBinding(optionIn);
    }

    /**
     * Returns 0 if the button is disabled, 1 if the mouse is NOT hovering over this button and 2 if it IS hovering over
     * this button.
     */
    protected int getHoverState(boolean mouseOver)
    {
        return 0;
    }
    
	public void drawButton(Minecraft mc, int mouseX, int mouseY, float a)
    {
        if (this.visible)
        {                   		
            //normal rectangle
            this.drawRoundedButton(this.x, this.y, this.x + this.width, this.y + this.height, 0xFF101010);
            
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            
            this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
            int i = this.getHoverState(this.hovered);
            
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            GlStateManager.blendFunc(770, 771);
            
            //default stuff from GuiButton.java
            this.mouseDragged(mc, mouseX, mouseY);
            
            this.fontrenderer.drawCenteredString(this.displayString, this.x + this.width / 2, this.y + (this.height -1) / 2, 0xFFFFFFFF);
        }
    }

    /**
     * Fired when the mouse button is dragged. Equivalent of MouseListener.mouseDragged(MouseEvent e).
     */
    protected void mouseDragged(Minecraft mc, int mouseX, int mouseY)
    {
        if (this.visible)
        {
            if (this.dragging)
            {
                this.sliderValue = (float)(mouseX - (this.x + 4)) / (float)(this.width - 8);
                this.sliderValue = MathHelper.clamp(this.sliderValue, 0.0F, 1.0F);
                float f = this.options.denormalizeValue(this.sliderValue);
                mc.gameSettings.setOptionFloatValue(this.options, f);
                this.sliderValue = this.options.normalizeValue(f);
                this.displayString = mc.gameSettings.getKeyBinding(this.options);
            }

            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            this.drawRoundedSliderButton(this.x + (int)(this.sliderValue * (float)(this.width - this.width / 18)), this.y, this.x + (int)(this.sliderValue * (float)(this.width - this.width / 18)) + this.width / 18, this.y + this.height, this.COLOR_2);
            
        }
    }

    /**
     * Returns true if the mouse has been pressed on this control. Equivalent of MouseListener.mousePressed(MouseEvent
     * e).
     */
    public boolean mousePressed(Minecraft mc, int mouseX, int mouseY)
    {
        if (super.mousePressed(mc, mouseX, mouseY))
        {
            this.sliderValue = (float)(mouseX - (this.x + 4)) / (float)(this.width - 8);
            this.sliderValue = MathHelper.clamp(this.sliderValue, 0.0F, 1.0F);
            mc.gameSettings.setOptionFloatValue(this.options, this.options.denormalizeValue(this.sliderValue));
            this.displayString = mc.gameSettings.getKeyBinding(this.options);
            this.dragging = true;
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Fired when the mouse button is released. Equivalent of MouseListener.mouseReleased(MouseEvent e).
     */
    public void mouseReleased(int mouseX, int mouseY)
    {
        this.dragging = false;
    }
}
