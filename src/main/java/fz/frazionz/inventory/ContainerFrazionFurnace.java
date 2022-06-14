package fz.frazionz.inventory;

import fz.frazionz.inventory.slots.SlotZFurnaceOutput;
import fz.frazionz.item.crafting.ZFurnaceRecipes;
import fz.frazionz.tileentity.TileEntityFrazionFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnaceFuel;
import net.minecraft.item.ItemStack;

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
        this.addSlotToContainer(new Slot(furnaceInventory, 0, 26, 12));
        this.addSlotToContainer(new Slot(furnaceInventory, 1, 44, 12));
        this.addSlotToContainer(new Slot(furnaceInventory, 2, 62, 12));
        this.addSlotToContainer(new Slot(furnaceInventory, 3, 26, 30));
        this.addSlotToContainer(new Slot(furnaceInventory, 4, 44, 30));
        this.addSlotToContainer(new Slot(furnaceInventory, 5, 62, 30));
        this.addSlotToContainer(new SlotFurnaceFuel(furnaceInventory, 6, 44, 55));
        this.addSlotToContainer(new SlotZFurnaceOutput(playerInventory.player, furnaceInventory, 7, 113, 23));
        this.addSlotToContainer(new SlotZFurnaceOutput(playerInventory.player, furnaceInventory, 8, 131, 23));
        this.addSlotToContainer(new SlotZFurnaceOutput(playerInventory.player, furnaceInventory, 9, 149, 23));
        this.addSlotToContainer(new SlotZFurnaceOutput(playerInventory.player, furnaceInventory, 10, 113, 41));
        this.addSlotToContainer(new SlotZFurnaceOutput(playerInventory.player, furnaceInventory, 11, 131, 41));
        this.addSlotToContainer(new SlotZFurnaceOutput(playerInventory.player, furnaceInventory, 12, 149, 41));

        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int k = 0; k < 9; ++k)
        {
            this.addSlotToContainer(new Slot(playerInventory, k, 8 + k * 18, 142));
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
                icontainerlistener.sendWindowProperty(this, 2, this.tileFurnace.getField(2));
            }

            if (this.furnaceBurnTime != this.tileFurnace.getField(0))
            {
                icontainerlistener.sendWindowProperty(this, 0, this.tileFurnace.getField(0));
            }

            if (this.currentItemBurnTime != this.tileFurnace.getField(1))
            {
                icontainerlistener.sendWindowProperty(this, 1, this.tileFurnace.getField(1));
            }

            if (this.totalCookTime != this.tileFurnace.getField(3))
            {
                icontainerlistener.sendWindowProperty(this, 3, this.tileFurnace.getField(3));
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
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index == 7 || index == 8 || index == 9 || index == 10 || index == 11 || index == 12)
            {
                if (!this.mergeItemStack(itemstack1, 13, 49, true))
                {
                    return ItemStack.EMPTY;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (index != 2 && index != 0 && index != 1 && index != 3 && index != 4 && index != 5 && index != 6)
            {
                if (!ZFurnaceRecipes.instance().getSmeltingResult(itemstack1).isEmpty())
                {
                    if (!this.mergeItemStack(itemstack1, 0, 6, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                else if (TileEntityFrazionFurnace.isItemFuel(itemstack1))
                {
                    if (!this.mergeItemStack(itemstack1, 6, 12, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                else if (index >=13 && index < 40)
                {
                    if (!this.mergeItemStack(itemstack1, 40, 49, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                else if (index >= 40 && index < 49 && !this.mergeItemStack(itemstack1, 13, 40, false))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 13, 49, false))
            {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty())
            {
                slot.putStack(ItemStack.EMPTY);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount())
            {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, itemstack1);
        }

        return itemstack;
    }
}
