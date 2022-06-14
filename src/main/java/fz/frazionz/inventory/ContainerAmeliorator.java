package fz.frazionz.inventory;

import fz.frazionz.inventory.slots.SlotAmelioratorFuel;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerAmeliorator extends Container
{
	
	private final IInventory tileAmeliorator;
	
    private int cookTime;
    private int totalCookTime;
    private int burnTime;
    private int currentItemBurnTime;
	
    private int SlotLimit = 1;
	
	public ContainerAmeliorator(InventoryPlayer playerInventory, IInventory furnaceInventory)
    {
        this.tileAmeliorator = furnaceInventory;
        this.addSlotToContainer(new SlotAmelioratorFuel(furnaceInventory, 0, 44, 8));
        this.addSlotToContainer(new Slot(furnaceInventory, 1, 116, 8)
        		{
        	public int getSlotStackLimit()
            {
                return SlotLimit;
            }}
        		);
        this.addSlotToContainer(new Slot(furnaceInventory, 2, 146, 18)
        {
        	public int getSlotStackLimit()
            {
                return SlotLimit;
            }}
        		);
        this.addSlotToContainer(new Slot(furnaceInventory, 3, 156, 48)
        {
        	public int getSlotStackLimit()
            {
                return SlotLimit;
            }}
        		);
        this.addSlotToContainer(new Slot(furnaceInventory, 4, 146, 78)
        {
        	public int getSlotStackLimit()
            {
                return SlotLimit;
            }}
        		);
        this.addSlotToContainer(new Slot(furnaceInventory, 5, 116, 88)
        {
        	public int getSlotStackLimit()
            {
                return SlotLimit;
            }}
        		);
        this.addSlotToContainer(new Slot(furnaceInventory, 6, 86, 78)
        {
        	public int getSlotStackLimit()
            {
                return SlotLimit;
            }}
        		);
        this.addSlotToContainer(new Slot(furnaceInventory, 7, 76, 48)
        {
        	public int getSlotStackLimit()
            {
                return SlotLimit;
            }}
        		);
        this.addSlotToContainer(new Slot(furnaceInventory, 8, 86, 18)
        {
        	public int getSlotStackLimit()
            {
                return SlotLimit;
            }}
        		);
        this.addSlotToContainer(new Slot(furnaceInventory, 9, 116, 48)
        {
        	public int getSlotStackLimit()
            {
                return SlotLimit;
            }}
        		);
        
        

        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, 44 + j * 18, 118 + i * 18));
            }
        }

        for (int k = 0; k < 9; ++k)
        {
            this.addSlotToContainer(new Slot(playerInventory, k, 44 + k * 18, 176));
        }
    }
	
	public void addListener(IContainerListener listener)
    {
        super.addListener(listener);
        listener.sendAllWindowProperties(this, this.tileAmeliorator);
    }
	
	
	
	public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int i = 0; i < this.listeners.size(); ++i)
        {
            IContainerListener icontainerlistener = this.listeners.get(i);

            if (this.cookTime != this.tileAmeliorator.getField(2))
            {
                icontainerlistener.sendWindowProperty(this, 2, this.tileAmeliorator.getField(2));
            }

            if (this.burnTime != this.tileAmeliorator.getField(0))
            {
                icontainerlistener.sendWindowProperty(this, 0, this.tileAmeliorator.getField(0));
            }

            if (this.currentItemBurnTime != this.tileAmeliorator.getField(1))
            {
                icontainerlistener.sendWindowProperty(this, 1, this.tileAmeliorator.getField(1));
            }

            if (this.totalCookTime != this.tileAmeliorator.getField(3))
            {
                icontainerlistener.sendWindowProperty(this, 3, this.tileAmeliorator.getField(3));
            }
        }

        this.cookTime = this.tileAmeliorator.getField(2);
        this.burnTime = this.tileAmeliorator.getField(0);
        this.currentItemBurnTime = this.tileAmeliorator.getField(1);
        this.totalCookTime = this.tileAmeliorator.getField(3);
    }
	
	
	public void updateProgressBar(int id, int data)
    {
        this.tileAmeliorator.setField(id, data);
    }
	
	
    public boolean canInteractWith(EntityPlayer playerIn)
    {
        return this.tileAmeliorator.isUsableByPlayer(playerIn);
    }
	
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            
            if (index >= 0 && index < 10)
            {
                if (!this.mergeItemStack(itemstack1, 10, 46, false))
                {
                    return ItemStack.EMPTY;
                }
            }
            
            else if (!this.mergeItemStack(itemstack1, 0, 9, false))
                {
                        return ItemStack.EMPTY;
                }
                
        	/*else if (TileEntityAmeliorator.isItemFuel(itemstack1))
                {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false))
                    {
                        return ItemStack.field_190927_a;
                    }
                }*/
            else if (index >= 10 && index < 37)
                {
                    if (!this.mergeItemStack(itemstack1, 37, 46, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
            else if (index >= 37 && index < 46)
            {
                 if (!this.mergeItemStack(itemstack1, 10, 37, false))
                 {
                        return ItemStack.EMPTY;
                 }
            }
            
            else if (!this.mergeItemStack(itemstack1, 10, 46, false))
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
