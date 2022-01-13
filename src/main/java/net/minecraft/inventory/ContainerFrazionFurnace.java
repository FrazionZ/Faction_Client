package net.minecraft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ZFurnaceRecipes;
import net.minecraft.tileentity.TileEntityFrazionFurnace;

public class ContainerFrazionFurnace extends Container
{
    private final IInventory tileFurnace;
    private int cookTime;
    private int totalCookTime;
    private int furnaceBurnTime;
    private int currentItemBurnTime;

    public ContainerFrazionFurnace(InventoryPlayer playerInventory, IInventory furnaceInventory)
    {
        this.tileFurnace = furnaceInventory;
        this.addSlotToContainer(new Slot(furnaceInventory, 0, 31, 34));
        this.addSlotToContainer(new Slot(furnaceInventory, 1, 49, 34));
        this.addSlotToContainer(new Slot(furnaceInventory, 2, 67, 34));
        this.addSlotToContainer(new Slot(furnaceInventory, 3, 31, 52));
        this.addSlotToContainer(new Slot(furnaceInventory, 4, 49, 52));
        this.addSlotToContainer(new Slot(furnaceInventory, 5, 67, 52));
        this.addSlotToContainer(new SlotFurnaceFuel(furnaceInventory, 6, 49, 77));
        this.addSlotToContainer(new SlotZFurnaceOutput(playerInventory.player, furnaceInventory, 7, 118, 45));
        this.addSlotToContainer(new SlotZFurnaceOutput(playerInventory.player, furnaceInventory, 8, 136, 45));
        this.addSlotToContainer(new SlotZFurnaceOutput(playerInventory.player, furnaceInventory, 9, 154, 45));
        this.addSlotToContainer(new SlotZFurnaceOutput(playerInventory.player, furnaceInventory, 10, 118, 63));
        this.addSlotToContainer(new SlotZFurnaceOutput(playerInventory.player, furnaceInventory, 11, 136, 63));
        this.addSlotToContainer(new SlotZFurnaceOutput(playerInventory.player, furnaceInventory, 12, 154, 63));

        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, 13 + j * 18, 106 + i * 18));
            }
        }

        for (int k = 0; k < 9; ++k)
        {
            this.addSlotToContainer(new Slot(playerInventory, k, 13 + k * 18, 164));
        }
    }

    public void addListener(IContainerListener listener)
    {
        super.addListener(listener);
        listener.sendAllWindowProperties(this, this.tileFurnace);
    }

    /**
     * Looks for changes made in the container, sends them to every listener.
     */
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int i = 0; i < this.listeners.size(); ++i)
        {
            IContainerListener icontainerlistener = this.listeners.get(i);

            if (this.cookTime != this.tileFurnace.getField(2))
            {
                icontainerlistener.sendProgressBarUpdate(this, 2, this.tileFurnace.getField(2));
            }

            if (this.furnaceBurnTime != this.tileFurnace.getField(0))
            {
                icontainerlistener.sendProgressBarUpdate(this, 0, this.tileFurnace.getField(0));
            }

            if (this.currentItemBurnTime != this.tileFurnace.getField(1))
            {
                icontainerlistener.sendProgressBarUpdate(this, 1, this.tileFurnace.getField(1));
            }

            if (this.totalCookTime != this.tileFurnace.getField(3))
            {
                icontainerlistener.sendProgressBarUpdate(this, 3, this.tileFurnace.getField(3));
            }
        }

        this.cookTime = this.tileFurnace.getField(2);
        this.furnaceBurnTime = this.tileFurnace.getField(0);
        this.currentItemBurnTime = this.tileFurnace.getField(1);
        this.totalCookTime = this.tileFurnace.getField(3);
    }

    public void updateProgressBar(int id, int data)
    {
        this.tileFurnace.setField(id, data);
    }

    /**
     * Determines whether supplied player can use this container
     */
    public boolean canInteractWith(EntityPlayer playerIn)
    {
        return this.tileFurnace.isUsableByPlayer(playerIn);
    }

    /**
     * Take a stack from the specified inventory slot.
     */
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
        ItemStack itemstack = ItemStack.field_190927_a;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index == 7 || index == 8 || index == 9 || index == 10 || index == 11 || index == 12)
            {
                if (!this.mergeItemStack(itemstack1, 13, 49, true))
                {
                    return ItemStack.field_190927_a;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (index != 2 && index != 0 && index != 1 && index != 3 && index != 4 && index != 5 && index != 6)
            {
                if (!ZFurnaceRecipes.instance().getSmeltingResult(itemstack1).func_190926_b())
                {
                    if (!this.mergeItemStack(itemstack1, 0, 6, false))
                    {
                        return ItemStack.field_190927_a;
                    }
                }
                else if (TileEntityFrazionFurnace.isItemFuel(itemstack1))
                {
                    if (!this.mergeItemStack(itemstack1, 6, 12, false))
                    {
                        return ItemStack.field_190927_a;
                    }
                }
                else if (index >=13 && index < 40)
                {
                    if (!this.mergeItemStack(itemstack1, 40, 49, false))
                    {
                        return ItemStack.field_190927_a;
                    }
                }
                else if (index >= 40 && index < 49 && !this.mergeItemStack(itemstack1, 13, 40, false))
                {
                    return ItemStack.field_190927_a;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 13, 49, false))
            {
                return ItemStack.field_190927_a;
            }

            if (itemstack1.func_190926_b())
            {
                slot.putStack(ItemStack.field_190927_a);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.func_190916_E() == itemstack.func_190916_E())
            {
                return ItemStack.field_190927_a;
            }

            slot.func_190901_a(playerIn, itemstack1);
        }

        return itemstack;
    }
}
