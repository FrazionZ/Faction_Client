package fz.frazionz.client.gui.buttons;

import fz.frazionz.FzClient;
import fz.frazionz.client.gui.utils.RoundedGradientShaderRenderer;
import fz.frazionz.client.gui.utils.RoundedShaderRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.math.MathHelper;

public class GuiFzOptionSlider extends GuiFzButton
{
    private float sliderValue = 1.0F;
    public boolean dragging;
    private final float min;
    private final float max;
    protected final GameSettings.Options options;
    protected int hoveredValue = 0;
    protected String stringValue = "";


    public GuiFzOptionSlider(int buttonId, int x, int y, GameSettings.Options optionIn)
    {
        this(buttonId, x, y, optionIn, 0.0F, 1.0F);
    }

    public GuiFzOptionSlider(int buttonId, int x, int y, GameSettings.Options optionIn, float min, float max)
    {
        super(buttonId, x, y, 150, 34, "");
        this.options = optionIn;
        this.min = min;
        this.max = max;
        Minecraft minecraft = Minecraft.getMinecraft();
        this.sliderValue = optionIn.normalizeValue(minecraft.gameSettings.getOptionFloatValue(optionIn));
        this.displayString = I18n.format(this.options.getTranslation());
        this.stringValue = minecraft.gameSettings.getKeyBinding(this.options);
    }

    private String getDisplayString() {
        return I18n.format(this.options.getTranslation());
    }

    private String getStringValue() {
        return Minecraft.getMinecraft().gameSettings.getKeyBinding(this.options);
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
    @Override
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
                this.displayString = getDisplayString();
                this.stringValue = getStringValue();
            }

            RoundedGradientShaderRenderer.getInstance().drawRoundRect(this.x, this.y+17, (this.sliderValue * (float)(this.width - 6)) + 3, 6, 3.0f, GRADIENT_BUTTON_1, GRADIENT_BUTTON_2);
            RoundedGradientShaderRenderer.getInstance().drawRoundRect(this.x + (int)(this.sliderValue * (float)(this.width - 12)), this.y - 3+17, 12, 12, 6.0f, GRADIENT_BUTTON_1, GRADIENT_BUTTON_2);
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
            this.displayString = getDisplayString();
            this.stringValue = getStringValue();
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

    @Override
    protected int textColor() {
        return 0xFFFFFFFF;
    }

    /**
     * Gets the value of the slider.
     * @return A value that will under normal circumstances be between the slider's {@link #min} and {@link #max}
     * values, unless it was manually set out of that range.
     */
    public float getSliderValue()
    {
        return this.min + (this.max - this.min) * this.sliderValue;
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        if (this.visible)
        {
            this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
            if(this.hovered && hoveredValue < 3)
                hoveredValue += 1;
            else if(!this.hovered && hoveredValue > 0)
                hoveredValue -= 1;
            //if(hoveredValue > 0)
                //RoundedShaderRenderer.getInstance().drawRoundRect(this.x - hoveredValue + 1, this.y - hoveredValue + 1, this.width+2*hoveredValue - 2, this.height + 2*hoveredValue - 2, 3.5f, hoverColor());

            RoundedShaderRenderer.getInstance().drawRoundRect(this.x, this.y+17, this.width, 6, 3.0f, BLACK_2);
            //RoundedGradientShaderRenderer.getInstance().drawRoundRect(this.x, this.y, this.width, this.height, 2, GRADIENT_BUTTON_1_INACTIVE, GRADIENT_BUTTON_2_INACTIVE);

            this.mouseDragged(mc, mouseX, mouseY);

            if(drawString()) {
                FzClient.getInstance().getTTFFontRenderers().get(16).drawCenteredStringVertically(this.displayString, this.x, this.y+7, textColor());
                RoundedShaderRenderer.getInstance().drawRoundRect(this.x + this.width-50, this.y, 50, 14, 2.0f, BLACK_2);
                FzClient.getInstance().getTTFFontRenderers().get(16).drawCenteredString(this.stringValue, this.x + this.width-25, this.y+7, textColor());
            }
        }
    }
}
