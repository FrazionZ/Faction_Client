package fz.frazionz.inventory;

import fz.frazionz.inventory.slots.SlotZFurnaceOutput;
import fz.frazionz.item.crafting.ZFurnaceRecipes;
import fz.frazionz.tileentity.TileEntityZFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnaceFuel;
import net.minecraft.item.ItemStack;

public class ContainerZFurnace extends Container
{
    private final IInventory tileFurnace;
    private int cookTime;
    private int totalCookTime;
    private int furnaceBurnTime;
    private int currentItemBurnTime;

    public ContainerZFurnace(InventoryPlayer playerInventory, IInventory furnaceInventory)
    {
        this.tileFurnace = furnaceInventory;
        this.addSlotToContainer(new Slot(furnaceInventory, 0, 35, 14));
        this.addSlotToContainer(new Slot(furnaceInventory, 1, 53, 14));
        this.addSlotToContainer(new SlotFurnaceFuel(furnaceInventory, 2, 44, 50));
        this.addSlotToContainer(new SlotZFurnaceOutput(playerInventory.player, furnaceInventory, 3, 107, 32));
        this.addSlotToContainer(new SlotZFurnaceOutput(playerInventory.player, furnaceInventory, 4, 125, 32));

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

            if (index == 3 || index == 4)
            {
                if (!this.mergeItemStack(itemstack1, 5, 41, true))
                {
                    return ItemStack.EMPTY;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (index != 2 && index != 0 && index != 1)
            {
                if (!ZFurnaceRecipes.instance().getSmeltingResult(itemstack1).isEmpty())
                {
                    if (!this.mergeItemStack(itemstack1, 0, 2, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                else if (TileEntityZFurnace.isItemFuel(itemstack1))
                {
                    if (!this.mergeItemStack(itemstack1, 2, 4, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                else if (index >=5 && index < 32)
                {
                    if (!this.mergeItemStack(itemstack1, 32, 41, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                else if (index >= 32 && index < 41 && !this.mergeItemStack(itemstack1, 5, 32, false))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 5, 41, false))
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
