package fz.frazionz.client.gui.list.slot;

public interface FzSlot {

    int getSlotHeight();

    int getSlotWidth();

    int getSlotX();

    int getSlotY();

    void setSlotX(int x);

    void setSlotY(int y);

    void setSlotWidth(int width);

    void setSlotHeight(int height);

    /**
     * Draw slot
     * @param mouseX
     * @param mouseY
     * @param partialTicks
     */
    void drawSlot(int mouseX, int mouseY, float partialTicks);

    void mousePressed(int mouseX, int mouseY, int mouseButton);

    void mouseReleased(int mouseX, int mouseY, int mouseButton);

    /**
     * Called when slot position is updated by List
     */
    void updateSlotPosition();

    /**
     * Return true if mouse is hovered over slot
     * @param mouseX
     * @param mouseY
     * @return true if mouse is hovered over slot
     */
    default boolean hovered(int mouseX, int mouseY) {
        return mouseX >= getSlotX() && mouseY >= getSlotY() && mouseX < getSlotX() + getSlotWidth() && mouseY < getSlotY() + getSlotHeight();
    }

}
