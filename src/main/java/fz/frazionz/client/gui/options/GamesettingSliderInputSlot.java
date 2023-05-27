package fz.frazionz.client.gui.options;

import fz.frazionz.FzClient;
import fz.frazionz.client.gui.list.slot.SimpleSlotList;
import fz.frazionz.client.gui.utils.RoundedGradientShaderRenderer;
import fz.frazionz.client.gui.utils.RoundedShaderRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.math.MathHelper;

public class GamesettingSliderInputSlot extends SliderInputSlot {

    protected final GameSettings.Options options;


    public GamesettingSliderInputSlot(int x, int y, int width, GameSettings.Options optionIn) {
        this(x, y, width, optionIn, 0.0F, 1.0F);
    }

    public GamesettingSliderInputSlot(int x, int y, int width, GameSettings.Options optionIn, float min, float max) {
        super(I18n.format(optionIn.getTranslation()), x, y, width);
        this.options = optionIn;
        Minecraft minecraft = Minecraft.getMinecraft();
        this.sliderValue = optionIn.normalizeValue(minecraft.gameSettings.getOptionFloatValue(optionIn));
        this.stringValue = minecraft.gameSettings.getKeyBinding(this.options);
    }

    public float getMax() {
        return max;
    }

    public float getMin() {
        return min;
    }

    public String getLabel() {
        return I18n.format(this.options.getTranslation());
    }

    public String getStringValue() {
        return Minecraft.getMinecraft().gameSettings.getKeyBinding(this.options);
    }

    @Override
    protected void onDrag() {
        float f = this.options.denormalizeValue(this.sliderValue);
        mc.gameSettings.setOptionFloatValue(this.options, f);
        this.sliderValue = this.options.normalizeValue(f);
    }

    @Override
    public void mousePressed(int mouseX, int mouseY, int mouseButton) {
        this.sliderValue = (float)(mouseX - (this.x + 4)) / (float)(this.width - 8);
        this.sliderValue = MathHelper.clamp(this.sliderValue, 0.0F, 1.0F);

        mc.gameSettings.setOptionFloatValue(this.options, this.options.denormalizeValue(this.sliderValue));
        this.label = getLabel();
        this.stringValue = getStringValue();
        this.dragging = true;
    }
}
