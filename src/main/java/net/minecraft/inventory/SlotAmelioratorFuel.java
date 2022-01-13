package net.minecraft.inventory;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityAmeliorator;

public class SlotAmelioratorFuel extends Slot
{
    public SlotAmelioratorFuel(IInventory inventoryIn, int slotIndex, int xPosition, int yPosition)
    {
        super(inventoryIn, slotIndex, xPosition, yPosition);
    }

    /**
     * Check if the stack is allowed to be placed in this slot, used for armor slots as well as furnace fuel.
     */
    public boolean isItemValid(ItemStack stack)
    {
        return TileEntityAmeliorator.isItemFuel(stack);
    }

    public int getItemStackLimit(ItemStack stack)
    {
        return super.getItemStackLimit(stack);
    }
}
