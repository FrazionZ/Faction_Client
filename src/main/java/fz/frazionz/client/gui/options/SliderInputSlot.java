package fz.frazionz.client.gui.options;

import fz.frazionz.client.gui.list.slot.ButtonsSlotList;
import net.minecraft.client.gui.GuiButton;

import java.util.List;

public class SliderInputSlot extends ButtonsSlotList {

    private String label;
    private float sliderValue = 1.0F;
    public boolean dragging;
    private final float min;
    private final float max;


    public SliderInputSlot(String label, int x, int y, int width, int height) {
        super(x, y, width, height);
        this.label = label;
        min = 0.0F;
        max = 1.0F;
    }

    public SliderInputSlot(String label, int x, int y, int width, int height, List<GuiButton> buttonList, float min, float max) {
        super(x, y, width, height, buttonList);
        this.label = label;
        this.min = min;
        this.max = max;
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
}
