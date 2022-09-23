package fz.frazionz.client.gui.list;

import fz.frazionz.client.gui.buttons.GuiItemstackButton;
import net.minecraft.item.ItemStack;

public class DoubleListItemEntry extends DoubleListEntry {
    public DoubleListItemEntry(int id, String name, ItemStack stack)
    {
        this.id = id;
        this.buttonType = new GuiItemstackButton(0, stack, name, 0, 0, 298, 36);
    }

}
