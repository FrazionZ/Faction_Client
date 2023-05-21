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

    void onClick(int mouseX, int mouseY, int mouseButton);

    default boolean hovered(int mouseX, int mouseY) {
        return mouseX >= getSlotX() && mouseY >= getSlotY() && mouseX < getSlotX() + getSlotWidth() && mouseY < getSlotY() + getSlotHeight();
    }

}
