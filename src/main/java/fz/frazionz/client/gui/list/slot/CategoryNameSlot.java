package fz.frazionz.client.gui.list.slot;

import fz.frazionz.FzClient;

public class CategoryNameSlot extends SimpleSlot {

    protected String name;

    public CategoryNameSlot(String name) {
        this(name, 0, 0, 100, 52);
    }

    public CategoryNameSlot(String name, int x, int y, int width, int height) {
        super(x, y, width, height);
        this.name = name;
    }

    @Override
    public void drawSlot(int mouseX, int mouseY, float partialTicks) {
        FzClient.getInstance().getTTFFontRenderers().get(24).drawString(name, x, y+24, 0xFFFFFFFF);
    }
}
