package net.minecraft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityAmeliorator;

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
        this.addSlotToContainer(new SlotAmelioratorFuel(furnaceInventory, 0, 14, 13));
        this.addSlotToContainer(new Slot(furnaceInventory, 1, 86, 13)
        		{
        	public int getSlotStackLimit()
            {
                return SlotLimit;
            }}
        		);
        this.addSlotToContainer(new Slot(furnaceInventory, 2, 116, 23)
        {
        	public int getSlotStackLimit()
            {
                return SlotLimit;
            }}
        		);
        this.addSlotToContainer(new Slot(furnaceInventory, 3, 126, 53)
        {
        	public int getSlotStackLimit()
            {
                return SlotLimit;
            }}
        		);
        this.addSlotToContainer(new Slot(furnaceInventory, 4, 116, 83)
        {
        	public int getSlotStackLimit()
            {
                return SlotLimit;
            }}
        		);
        this.addSlotToContainer(new Slot(furnaceInventory, 5, 86, 93)
        {
        	public int getSlotStackLimit()
            {
                return SlotLimit;
            }}
        		);
        this.addSlotToContainer(new Slot(furnaceInventory, 6, 56, 83)
        {
        	public int getSlotStackLimit()
            {
                return SlotLimit;
            }}
        		);
        this.addSlotToContainer(new Slot(furnaceInventory, 7, 46, 53)
        {
        	public int getSlotStackLimit()
            {
                return SlotLimit;
            }}
        		);
        this.addSlotToContainer(new Slot(furnaceInventory, 8, 56, 23)
        {
        	public int getSlotStackLimit()
            {
                return SlotLimit;
            }}
        		);
        this.addSlotToContainer(new Slot(furnaceInventory, 9, 86, 53)
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
                this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, 14 + j * 18, 123 + i * 18));
            }
        }

        for (int k = 0; k < 9; ++k)
        {
            this.addSlotToContainer(new Slot(playerInventory, k, 14 + k * 18, 181));
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
                icontainerlistener.sendProgressBarUpdate(this, 2, this.tileAmeliorator.getField(2));
            }

            if (this.burnTime != this.tileAmeliorator.getField(0))
            {
                icontainerlistener.sendProgressBarUpdate(this, 0, this.tileAmeliorator.getField(0));
            }

            if (this.currentItemBurnTime != this.tileAmeliorator.getField(1))
            {
                icontainerlistener.sendProgressBarUpdate(this, 1, this.tileAmeliorator.getField(1));
            }

            if (this.totalCookTime != this.tileAmeliorator.getField(3))
            {
                icontainerlistener.sendProgressBarUpdate(this, 3, this.tileAmeliorator.getField(3));
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
        ItemStack itemstack = ItemStack.field_190927_a;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            
            if (index >= 0 && index < 10)
            {
                if (!this.mergeItemStack(itemstack1, 10, 46, false))
                {
                    return ItemStack.field_190927_a;
                }
            }
            
            else if (!this.mergeItemStack(itemstack1, 0, 9, false))
                {
                        return ItemStack.field_190927_a;
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
                        return ItemStack.field_190927_a;
                    }
                }
            else if (index >= 37 && index < 46)
            {
                 if (!this.mergeItemStack(itemstack1, 10, 37, false))
                 {
                        return ItemStack.field_190927_a;
                 }
            }
            
            else if (!this.mergeItemStack(itemstack1, 10, 46, false))
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
