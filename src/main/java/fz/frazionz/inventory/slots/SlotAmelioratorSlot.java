package fz.frazionz.inventory.slots;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotAmelioratorSlot extends Slot
{
    public SlotAmelioratorSlot(IInventory inventoryIn, int slotIndex, int xPosition, int yPosition)
    {
        super(inventoryIn, slotIndex, xPosition, yPosition);
    }

    public int getItemStackLimit(ItemStack stack)
    {
        return 1;
    }
}
