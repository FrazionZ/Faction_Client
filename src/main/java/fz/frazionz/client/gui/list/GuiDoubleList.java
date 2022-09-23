package fz.frazionz.client.gui.list;

import fz.frazionz.client.gui.shop.GuiListFrazionZInterface;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.gui.GuiScreen;

public class GuiDoubleList extends GuiListFrazionZInterface
{

    public GuiDoubleList(GuiScreen lastScreen, Minecraft mcIn, int left, int right, int top, int bottom, int slotHeight)
    {
        super(lastScreen, mcIn, left, right, top, bottom, slotHeight);
    }

    public void setListEntry(DoubleListEntry[] entries) {
        this.listEntries = new GuiListExtended.IGuiListEntry[entries.length];
        int i = 0;
        for (DoubleListEntry entry : entries)
        {
            this.listEntries[i++] = entry;
        }
    }
}
