package fz.frazionz.client.gui.list.slot.keybinds;

import fz.frazionz.FzClient;
import fz.frazionz.client.gui.list.slot.SimpleSlot;

public class CategorySlot extends SimpleSlot  {

    protected String name;

    public CategorySlot(String name) {
        this(name, 0, 0, 100, 52);
    }

    public CategorySlot(String name, int x, int y, int width, int height) {
        super(x, y, width, height);
        this.name = name;
    }

    @Override
    public void drawSlot(int mouseX, int mouseY, float partialTicks) {
        FzClient.getInstance().getTTFFontRenderers().get(24).drawCenteredStringHorizontally(name, x+width/2, y+24, 0xFFFFFFFF);
    }


}
