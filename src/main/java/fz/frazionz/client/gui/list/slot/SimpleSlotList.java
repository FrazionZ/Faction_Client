package fz.frazionz.client.gui.list.slot;

public class SimpleSlotList implements FzSlot {

    private int x;
    private int y;
    private int width;
    private int height;

    public SimpleSlotList(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public int getSlotHeight() {
        return height;
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
    public void setSlotHeight(int height) {
        this.height = height;
    }

    @Override
    public void drawSlot(int mouseX, int mouseY, float partialTicks) {

    }

    @Override
    public void onClick(int mouseX, int mouseY, int mouseButton) {

    }

    @Override
    public void updateSlotPosition() {

    }
}
