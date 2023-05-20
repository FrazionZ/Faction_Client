package fz.frazionz.client.gui.list;

import fz.frazionz.FzClient;
import net.minecraft.client.gui.Gui;

public class FzSlotExample implements FzSlot {

    private int x;
    private int y;
    private int width = 150;
    private int number;

    public FzSlotExample(int number) {
        this.number = number;
    }

    @Override
    public int getSlotHeight() {
        return 30;
    }

    @Override
    public int getSlotWidth() {
        return width;
    }

    @Override
    public int getSlotX() {
        return x;
    }

    @Override
    public int getSlotY() {
        return y;
    }

    @Override
    public void setSlotX(int x) {
        this.x = x;
    }

    @Override
    public void setSlotY(int y) {
        this.y = y;
    }

    @Override
    public void setSlotWidth(int width) {
        this.width = width;
    }

    @Override
    public void drawSlot(int mouseX, int mouseY, float partialTicks) {
        Gui.drawRect(x, y, x + getSlotWidth(), y + getSlotHeight(), 0xFFFF0000);
        FzClient.getInstance().getTTFFontRenderers().get(24).drawCenteredString("Slot " + number, x + getSlotWidth() / 2, y + getSlotHeight() / 2 - 5, 0xFFFFFFFF);
    }
}
