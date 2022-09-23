package fz.frazionz.client.gui.list;

import fz.frazionz.client.gui.buttons.GuiHoverButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiListExtended;

public class DoubleListEntry implements GuiListExtended.IGuiListEntry {

    protected GuiButton buttonType;
    protected int id;

    public DoubleListEntry(int id, String name)
    {
        this.id = id;
        this.buttonType = new GuiHoverButton(0, name, 0, 0, 298, 36);
    }

    public int getId() {
        return id;
    }

    public DoubleListEntry() {}

    public void drawEntry(int slotIndex, int x, int y, int listWidth, int slotHeight, int mouseX, int mouseY, boolean isSelected, float partialTicks)
    {
        this.buttonType.x = x - 3;
        this.buttonType.y = y;
        this.buttonType.drawButton(Minecraft.getMinecraft(), mouseX, mouseY, partialTicks);
    }

    public boolean mousePressed(int slotIndex, int mouseX, int mouseY, int mouseEvent, int relativeX, int relativeY)
    {
        if(this.buttonType.mousePressed(Minecraft.getMinecraft(), mouseX, mouseY)) {
            return true;
        }
        return false;
    }

    public void mouseReleased(int slotIndex, int x, int y, int mouseEvent, int relativeX, int relativeY)
    {
        this.buttonType.mouseReleased(x, y);
    }

    public void updatePosition(int slotIndex, int x, int y, float partialTicks) {
    }

    public GuiButton getShopTypeButton() {
        return buttonType;
    }

}
