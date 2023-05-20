package fz.frazionz.client.gui.list;

public class FzCategory implements FzSlot {

    private final String name;
    private final FzSlot[] slots;

    public FzCategory(String name, FzSlot... slots) {
        this.name = name;
        this.slots = slots;
    }

    public String getName() {
        return name;
    }

    public FzSlot[] getSlots() {
        return slots;
    }

    @Override
    public int getSlotHeight() {
        return 0;
    }

    @Override
    public int getSlotWidth() {
        return 0;
    }

    @Override
    public int getSlotX() {
        return 0;
    }

    @Override
    public int getSlotY() {
        return 0;
    }

    @Override
    public void setSlotX(int x) {

    }

    @Override
    public void setSlotY(int y) {

    }

    @Override
    public void setSlotWidth(int width) {

    }

    @Override
    public void drawSlot(int mouseX, int mouseY, float partialTicks) {

    }
}