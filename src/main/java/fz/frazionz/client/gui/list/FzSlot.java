package fz.frazionz.client.gui.list;

public interface FzSlot {

    int getSlotHeight();

    int getSlotWidth();

    int getSlotX();

    int getSlotY();

    void setSlotX(int x);

    void setSlotY(int y);

    void setSlotWidth(int width);

    void drawSlot(int mouseX, int mouseY, float partialTicks);

}
