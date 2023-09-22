package fz.frazionz.client.gui.list.slot;

import fz.frazionz.FzClient;
import fz.frazionz.client.gui.utils.RoundedGradientShaderRenderer;
import fz.frazionz.client.gui.utils.RoundedShaderRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.math.MathHelper;

public class SliderInputSlot extends SimpleSlot {

    protected float sliderValue = 1.0F;
    protected boolean dragging;
    protected final float min;
    protected final float max;

    protected String label;
    protected String stringValue;


    public SliderInputSlot(String label, int x, int y, int width) {
        this(label, x, y, width, 0.0F, 1.0F);
    }

    public SliderInputSlot(String label, int x, int y, int width, float min, float max) {
        super(x, y, width, 68);
        this.min = min;
        this.max = max;;
        Minecraft minecraft = Minecraft.getMinecraft();
        this.sliderValue = 0.0f;
        this.label = label;
        this.stringValue = "";
    }

    public float getMax() {
        return max;
    }

    public float getMin() {
        return min;
    }

    public String getLabel() {
        return label;
    }

    public String getStringValue() {
        return "";
    }

    /**
     * Fired when the mouse button is dragged. Equivalent of MouseListener.mouseDragged(MouseEvent e).
     */
    protected void mouseDragged(Minecraft mc, int mouseX, int mouseY)
    {
            if (this.dragging)
            {
                this.sliderValue = (float)(mouseX - (this.x + 4)) / (float)(this.width - 8);
                this.sliderValue = MathHelper.clamp(this.sliderValue, 0.0F, 1.0F);
                this.onDrag();
                this.label = getLabel();
                this.stringValue = getStringValue();
            }

        RoundedShaderRenderer.getInstance().drawRoundRect(this.x, this.y+height-24, this.width, 16, 7.5f, Gui.BLACK_2);

            RoundedGradientShaderRenderer.getInstance().drawRoundRect(this.x, this.y+height-24, (this.sliderValue * (float)(this.width-28) + 14), 16, 7.5f, Gui.GRADIENT_BUTTON_1, Gui.GRADIENT_BUTTON_2);
            RoundedGradientShaderRenderer.getInstance().drawRoundRect(this.x + (int)(this.sliderValue * (float)(this.width - 28)), this.y+height-30, 28, 28, 13.0f, Gui.GRADIENT_BUTTON_1, Gui.GRADIENT_BUTTON_2);
    }

    protected void onDrag() {

    }

    @Override
    public void mousePressed(int mouseX, int mouseY, int mouseButton) {
        this.sliderValue = (float)(mouseX - (this.x + 4)) / (float)(this.width - 8);
        this.sliderValue = MathHelper.clamp(this.sliderValue, 0.0F, 1.0F);

        this.label = getLabel();
        this.stringValue = getStringValue();
        this.dragging = true;
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int mouseButton) {
        this.dragging = false;
    }

    @Override
    public void drawSlot(int mouseX, int mouseY, float partialTicks) {
        RoundedShaderRenderer.getInstance().drawRoundRect(this.x, this.y+height-24, this.width, 16, 7.5f, Gui.BLACK_2);

        this.mouseDragged(mc, mouseX, mouseY);
        FzClient.getInstance().getTTFFontRenderers().get(18).drawCenteredStringVertically(this.label, this.x, this.y+15, 0xFFFFFFFF);
        RoundedShaderRenderer.getInstance().drawRoundRect(this.x + this.width-100, this.y, 100, 30, 3.5f, Gui.BLACK_2);
        FzClient.getInstance().getTTFFontRenderers().get(16).drawCenteredString(this.stringValue, this.x + this.width-50, this.y+15, 0xFFFFFFFF);
    }
}
