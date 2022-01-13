package net.minecraft.inventory;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityAmeliorator;

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
