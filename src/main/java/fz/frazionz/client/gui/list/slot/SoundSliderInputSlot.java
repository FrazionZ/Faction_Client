package fz.frazionz.client.gui.list.slot;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;

public class SoundSliderInputSlot extends SliderInputSlot {

    protected final SoundCategory category;


    public SoundSliderInputSlot(int x, int y, int width, SoundCategory categoryIn) {
        this(x, y, width, categoryIn, 0.0F, 1.0F);
    }

    protected String getDisplayString(SoundCategory category)
    {
        float f = Minecraft.getMinecraft().gameSettings.getSoundLevel(category);
        return f == 0.0F ? "Off" : (int)(f * 100.0F) + "%";
    }

    public SoundSliderInputSlot(int x, int y, int width, SoundCategory categoryIn, float min, float max) {
        super(I18n.format("soundCategory." + categoryIn.getName()), x, y, width);
        this.category = categoryIn;
        this.sliderValue = Minecraft.getMinecraft().gameSettings.getSoundLevel(categoryIn);
        this.stringValue = getStringValue();
    }

    public float getMax() {
        return max;
    }

    public float getMin() {
        return min;
    }

    public String getLabel() {
        return I18n.format("soundCategory." + category.getName());
    }

    public String getStringValue() {
        float f = Minecraft.getMinecraft().gameSettings.getSoundLevel(category);
        return f == 0.0F ? "Off" : (int)(f * 100.0F) + "%";
    }

    @Override
    protected void onDrag() {
        mc.gameSettings.setSoundLevel(this.category, this.sliderValue);
        mc.gameSettings.saveOptions();
    }

    @Override
    public void mousePressed(int mouseX, int mouseY, int mouseButton) {
        this.sliderValue = (float)(mouseX - (this.x + 4)) / (float)(this.width - 8);
        this.sliderValue = MathHelper.clamp(this.sliderValue, 0.0F, 1.0F);

        mc.gameSettings.setSoundLevel(this.category, this.sliderValue);
        mc.gameSettings.saveOptions();
        this.label = getLabel();
        this.stringValue = getStringValue();
        this.dragging = true;
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int mouseButton) {
        super.mouseReleased(mouseX, mouseY, mouseButton);
    }
}
