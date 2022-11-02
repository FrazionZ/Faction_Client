package fz.frazionz.tileentity;

import fz.frazionz.client.renderer.tileentity.TickCounter;
import fz.frazionz.client.recipes.ItemCrusherRecipes;
import fz.frazionz.inventory.ContainerItemCrusher;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;

public class TileEntityItemCrusher extends TileEntityLockable implements ITickable, TickCounter, ISidedInventory
{
	private static final int[] SLOT_CRAFT = new int[] {0, 1, 2};
	private static final int[] SLOT_INVENTORY = new int[] {3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
    
    private NonNullList<ItemStack> itemstacks = NonNullList.<ItemStack>withSize(13, ItemStack.EMPTY);
    
    private int crushingTime;
    private int totalCrushingTime;
    private int isCrushing = 0;
    
    private int tickCount = 0;
    private boolean isAnimationEnd = true;

    public TileEntityItemCrusher()
    {
	}
	
    // Taille de L'inventaire de l'entity //
    
	public int getSizeInventory() 
	{
		return this.itemstacks.size();
	}

	public boolean isEmpty()
    {
        for (ItemStack itemstack : this.itemstacks)
        {
            if (!itemstack.isEmpty())
            {
                return false;
            }
        }

        return true;
    }
	
    /**
     * Returns the stack in the given slot.
     */
	public ItemStack getStackInSlot(int index)
    {
        return this.itemstacks.get(index);
    }
	
	
    /**
     * Removes up to a specified number of items from an inventory slot and returns them in a new stack.
     */
	public ItemStack decrStackSize(int index, int count)
    {
        return ItemStackHelper.getAndSplit(this.itemstacks, index, count);
    }
	
    /**
     * Removes a stack from the given slot and returns it.
     */
	public ItemStack removeStackFromSlot(int index)
    {
        return ItemStackHelper.getAndRemove(this.itemstacks, index);
    }

	/**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    public void setInventorySlotContents(int index, ItemStack stack)
    {
    	
    	this.itemstacks.set(index, stack);
    	
        if (index >= 0 && index <= this.itemstacks.size())
        {
        	this.totalCrushingTime = this.getForgeTime();
        	this.crushingTime = 0;
            this.markDirty();
        }
    }

    
	public int getInventoryStackLimit()
	{
		return 64;
	}

	/**
     * Don't rename this method to canInteractWith due to conflicts with Container
     */    
    public boolean isUsableByPlayer(EntityPlayer player)
    {
        if (this.world.getTileEntity(this.pos) != this)
        {
            return false;
        }
        else
        {
            return player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
        }
    }
    
    
    public void openInventory(EntityPlayer player)
    {
    }

    public void closeInventory(EntityPlayer player)
    {
    }
    
    /**
     * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot. For
     * guis use Slot.isItemValid
     */
    public boolean isItemValidForSlot(int index, ItemStack stack)
    {
    	return false;
    }

	public String getName() {
		return "Item Crusher";
	}

	public boolean hasCustomName() {
		return false;
	}
    
    
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        this.itemstacks = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound, this.itemstacks);
        this.crushingTime = compound.getShort("crushingTime");
        this.totalCrushingTime = compound.getShort("totalCrushingTime");
        this.isCrushing = compound.getShort("isCrushing");
    }

    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setShort("crushingTime", (short)this.crushingTime);
        compound.setShort("totalCrushingTime", (short)this.totalCrushingTime);
        compound.setShort("isCrushing", (short)this.isCrushing);
        ItemStackHelper.saveAllItems(compound, this.itemstacks);

        return compound;
    }

	public void update() {
		if(this.isAnimationEnd && tickCount != 0) {
			tickCount = 0;
		}
		if(this.isCrushing() || !this.isAnimationEnd) {
			tickCount++;
		}
        if (!this.world.isRemote) {
        	if(this.isCrushing() && !this.canCrush()) {
        		this.isCrushing = 0;
        		this.crushingTime = 0;
        		this.world.addBlockEvent(this.pos, this.getBlockType(), 1, 0);
        	}
        	else if(this.isCrushing() && this.canCrush()) {
        		this.crushingTime++;
                if (this.crushingTime >= this.getForgeTime())
                {
                	this.crushingTime = 0;
                	this.totalCrushingTime = this.getForgeTime();
                    this.crushItems();
                }
        		
        	}
            
            this.markDirty();
        }
        
	}

    public int getForgeTime()
    {
    	return 100;
    }
    
    public ItemStack[] getRecipeResult(int id) {
        return ItemCrusherRecipes.getResult(this.itemstacks.get(id));
    }
   
    public boolean canCrush() {
    	boolean canCrush = false;
    	for(int id : SLOT_CRAFT)
    		canCrush = canCrush || this.getRecipeResult(id) != null;
    	return canCrush;
    }
    
    /**
     * Turn one item from the furnace source stack into the appropriate smelted item in the furnace result stack
     */
    public void crushItems()
    {
        for(int id : SLOT_CRAFT) {
            ItemStack[] result = this.getRecipeResult(id);
            if(result != null) {
            	this.itemstacks.get(id).shrink(1);
            	for(ItemStack stack : result) {
            		addItemToInventory(stack);
            		if(!stack.isEmpty()) {
            			Block.spawnAsEntity(world, getFrontPosition(), stack);
            		}
            	}
            }
        }
    }
	
	public String getGuiID() {
		return "frazionz:item_crusher";
	}
	
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn)
	{
		return new ContainerItemCrusher(playerInventory, this);
	}
	
	public int getField(int id)
    {
        switch (id) {
            case 0:
                return this.crushingTime;

            case 1:
                return this.totalCrushingTime;
                
            case 2:
            	return this.isCrushing;

            default:
                return 0;
        }
    }

    public void setField(int id, int value)
    {
        switch (id)
        {
            case 0:
                this.crushingTime = value;
                break;

            case 1:
                this.totalCrushingTime = value;
                break;
                
            case 2: 
            	this.isCrushing = value; 
            	this.world.addBlockEvent(this.pos, this.getBlockType(), 1, value);
            	this.world.addBlockEvent(this.pos, this.getBlockType(), 2, 0);
            	break;
        }
    }

    public int getFieldCount()
    {
        return 3;
    }
    
    public boolean receiveClientEvent(int id, int type)
    {
    	switch(id) {
    		case 1:
                this.isCrushing = type;
                return true;
    		case 2:
                this.isAnimationEnd = type == 1;
                return true;
            default:
            	return super.receiveClientEvent(id, type);
    	}
    }

    public void clear()
    {
        this.itemstacks.clear();
    }
    
    public boolean isCrushing() {
		return this.isCrushing == 1;
	}

	public boolean isAnimationEnd() {
		return isAnimationEnd;
	}
	
	public boolean hasAvailableSlot() {
		for(int id : SLOT_INVENTORY) {
			if(this.itemstacks.get(id).isEmpty())
				return true;
		}
		return false;
	}
	
	public void addItemToInventory(ItemStack item) {
		int i = -1;
		for(int id : SLOT_INVENTORY) {
			ItemStack stack = this.itemstacks.get(id);
			if(i == -1 && stack.isEmpty())
				i = id;
			if(stack.getItem() == item.getItem()) {
				int stackCount = stack.getCount();
				if(stackCount < 64) {
					int newCount = stackCount + item.getCount();
					if(newCount > 64) {
						stack.setCount(64);
						item.setCount(item.getCount() - newCount + 64);
					} 
					else {
						stack.setCount(newCount);
						item.setCount(0);
						break;
					}
				}
			}
		}
		if(i != -1 && !item.isEmpty()) {
			this.itemstacks.set(i, item.copy());
			item.setCount(0);
		}
	}
	
	public BlockPos getFrontPosition() {
        int i = this.getBlockMetadata();
	    switch(i) 
	    {
	        case 3:
	        	return this.pos.add(0.5, 0.5, 0.5-1);
	        case 4:
	        	return this.pos.add(0.5, 0.5, 0.5+1);
	        case 5:
	        	return this.pos.add(0.5-1, 0.5, 0.5);
	        case 6:
	        	return this.pos.add(0.5+1, 0.5, 0.5);
	    }
	    return this.pos;
	}

	@Override
	public int getTickCount() {
		return tickCount;
	}

	@Override
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
		return false;
	}
	
	@Override
	public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
		return false;
	}

	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		return SLOT_INVENTORY;
	}
}
