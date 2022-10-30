package fz.frazionz.inventory;

import fz.frazionz.crafting.TrophyForgeRecipes;
import fz.frazionz.tileentity.impl.TileMachine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerTrophyForge extends Container implements TileMachine
{
	
	private final IInventory tileTrophyForge;
    private int forgeTime;
    private int totalForgeTime;
	private int isForging;
	
	public ContainerTrophyForge(InventoryPlayer playerInventory, IInventory furnaceInventory)
    {
        this.tileTrophyForge = furnaceInventory;
        for(int i = 0; i < 3; ++i) {
        	for(int j = 0; j < 3; ++j) {
        		this.addSlotToContainer(new Slot(furnaceInventory, j + i * 3, 44 + i*22, 24 + j*22));
        	}
        }
        
        for(int i = 0; i < 3; i++) {
        	this.addSlotToContainer(new Slot(furnaceInventory, 9 + i, 138, 24 + i*22));
        }
        
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
        listener.sendAllWindowProperties(this, this.tileTrophyForge);
    }
	
	public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int i = 0; i < this.listeners.size(); ++i)
        {
            IContainerListener icontainerlistener = this.listeners.get(i);

            if (this.forgeTime != this.tileTrophyForge.getField(0))
            {
                icontainerlistener.sendWindowProperty(this, 0, this.tileTrophyForge.getField(0));
            }

            if (this.totalForgeTime != this.tileTrophyForge.getField(1))
            {
                icontainerlistener.sendWindowProperty(this, 1, this.tileTrophyForge.getField(1));
            }
            
            if (this.isForging != this.tileTrophyForge.getField(2))
            {
                icontainerlistener.sendWindowProperty(this, 2, this.tileTrophyForge.getField(2));
            }
        }

        this.forgeTime = this.tileTrophyForge.getField(0);
        this.totalForgeTime = this.tileTrophyForge.getField(1);
        this.isForging = this.tileTrophyForge.getField(2);
    }
	
	
	public void updateProgressBar(int id, int data)
    {
        this.tileTrophyForge.setField(id, data);
    }
	
	
    public boolean canInteractWith(EntityPlayer playerIn)
    {
        return this.tileTrophyForge.isUsableByPlayer(playerIn);
    }
	
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            
            if (index >= 0 && index < 12)
            {
                if (!this.mergeItemStack(itemstack1, 12, 48, false))
                {
                    return ItemStack.EMPTY;
                }
            }
            
            else if (!this.mergeItemStack(itemstack1, 0, 9, false))
            {
                        return ItemStack.EMPTY;
            }
            else if (index >= 12 && index < 39)
                {
                    if (!this.mergeItemStack(itemstack1, 39, 48, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
            else if (index >= 39 && index < 48)
            {
                 if (!this.mergeItemStack(itemstack1, 12, 39, false))
                 {
                        return ItemStack.EMPTY;
                 }
            }
            
            else if (!this.mergeItemStack(itemstack1, 12, 48, false))
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
    
    public void startMachine() {
    	this.tileTrophyForge.setField(2, 1);
    }
    
    public boolean canForge()
    {
        return TrophyForgeRecipes.getTrophyForgeResult(new ItemStack[] {
        		this.inventorySlots.get(0).getStack(),
        		this.inventorySlots.get(1).getStack(),
        		this.inventorySlots.get(2).getStack(),
        		this.inventorySlots.get(3).getStack(),
        		this.inventorySlots.get(4).getStack(),
        		this.inventorySlots.get(5).getStack(),
        		this.inventorySlots.get(6).getStack(),
        		this.inventorySlots.get(7).getStack(),
        		this.inventorySlots.get(8).getStack(),
        		}) != null;
    }
    
    public boolean isForging() {
    	return this.isForging == 1;
    }
    
    public IInventory getTileEntity() {
		return tileTrophyForge;
	}
}
