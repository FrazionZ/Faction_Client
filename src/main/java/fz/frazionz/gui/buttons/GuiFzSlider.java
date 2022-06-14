package fz.frazionz.gui.buttons;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.math.MathHelper;

public class GuiFzSlider extends GuiFzButton
{
    protected float sliderValue;
    public boolean dragging;
    protected final GameSettings.Options options;
    private final float minValue;
    protected final float maxValue;

    public GuiFzSlider(int buttonId, int x, int y, GameSettings.Options optionIn)
    {
        this(buttonId, x, y, optionIn, 0.0F, 1.0F);
    }

    public GuiFzSlider(int buttonId, int x, int y, GameSettings.Options optionIn, float minValueIn, float maxValue)
    {
        super(buttonId, x, y, 150, 20, "");
        this.sliderValue = 1.0F;
        this.options = optionIn;
        this.minValue = minValueIn;
        this.maxValue = maxValue;
        Minecraft minecraft = Minecraft.getMinecraft();
        this.sliderValue = optionIn.normalizeValue(minecraft.gameSettings.getOptionFloatValue(optionIn));
        this.displayString = I18n.format(this.options.getTranslation()) + ": " + minecraft.gameSettings.getKeyBinding(this.options);
    }

    /**
     * Returns 0 if the button is disabled, 1 if the mouse is NOT hovering over this button and 2 if it IS hovering over
     * this button.
     */
    protected int getHoverState(boolean mouseOver)
    {
        return 1;
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
                this.displayString = I18n.format(this.options.getTranslation()) + ": " + mc.gameSettings.getKeyBinding(this.options);
            }

            mc.getTextureManager().bindTexture(INTERFACE_BACKGROUND_2);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            this.drawModalRectWithCustomSizedTexture(this.x + (int)(sliderValue * (float)(width-8)), this.y, 200+(this.hovered?8:0), 117, 8, height/2, 512.0F, 512.0F);
            this.drawModalRectWithCustomSizedTexture(this.x + (int)(sliderValue * (float)(width-8)), this.y + height/2, 200+(this.hovered?8:0), 117 + (30-height/2), 8, height/2, 512.0F, 512.0F);
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
            this.displayString = I18n.format(this.options.getTranslation()) + ": " + mc.gameSettings.getKeyBinding(this.options);
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
