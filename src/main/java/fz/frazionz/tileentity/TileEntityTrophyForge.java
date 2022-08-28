package fz.frazionz.tileentity;

import fz.frazionz.crafting.TrophyForgeRecipes;
import fz.frazionz.inventory.ContainerTrophyForge;
import fz.frazionz.item.ItemTrophy;
import fz.frazionz.tileentity.renderer.TickCounter;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;

public class TileEntityTrophyForge extends TileEntityLockable implements ITickable, TickCounter, ISidedInventory
{
	
	private static final int[] SLOT_CRAFT = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
    
    private NonNullList<ItemStack> TrophyForgeItemStacks = NonNullList.<ItemStack>withSize(12, ItemStack.EMPTY);
    
    private int forgeTime;
    private int totalForgeTime;
    private int isForging = 0;
    
    private int tickCount = 0;
    private boolean isAnimationEnd = true;

    public TileEntityTrophyForge()
    {
	}
	
	public int getSizeInventory() 
	{
		return this.TrophyForgeItemStacks.size();
	}

	public boolean isEmpty()
    {
        for (ItemStack itemstack : this.TrophyForgeItemStacks)
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
        return this.TrophyForgeItemStacks.get(index);
    }
	
	
    /**
     * Removes up to a specified number of items from an inventory slot and returns them in a new stack.
     */
	public ItemStack decrStackSize(int index, int count)
    {
        return ItemStackHelper.getAndSplit(this.TrophyForgeItemStacks, index, count);
    }
	
    /**
     * Removes a stack from the given slot and returns it.
     */
	public ItemStack removeStackFromSlot(int index)
    {
        return ItemStackHelper.getAndRemove(this.TrophyForgeItemStacks, index);
    }

	/**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    public void setInventorySlotContents(int index, ItemStack stack)
    {
    	this.TrophyForgeItemStacks.set(index, stack);
    	
        if (index >= 0 && index <= this.TrophyForgeItemStacks.size())
        {
        	this.totalForgeTime = this.getForgeTime();
        	this.forgeTime = 0;
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
		return "Trophy Forge";
	}

	public boolean hasCustomName() {
		
		return false;
		
	}
    
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        this.TrophyForgeItemStacks = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound, this.TrophyForgeItemStacks);
        this.forgeTime = compound.getShort("ForgeTime");
        this.totalForgeTime = compound.getShort("TotalForgeTime");
        this.isForging = compound.getShort("IsForging");
    }

    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setShort("ForgeTime", (short)this.forgeTime);
        compound.setShort("TotalForgeTime", (short)this.totalForgeTime);
        compound.setShort("IsForging", (short)this.isForging);
        ItemStackHelper.saveAllItems(compound, this.TrophyForgeItemStacks);

        return compound;
    }

	public void update() {
		if(this.isAnimationEnd && tickCount != 0) {
			tickCount = 0;
		}
		if(this.isForging() || !this.isAnimationEnd) {
			tickCount++;
		}
        if (!this.world.isRemote) {
        	if(this.isForging() && !this.canForge()) {
        		this.isForging = 0;
        		this.forgeTime = 0;
        		this.world.addBlockEvent(this.pos, this.getBlockType(), 1, 0);
        	}
        	else if(this.isForging() && this.canForge()) {
        		this.forgeTime++;
        		
                if (this.forgeTime >= this.getForgeTime())
                {
                	this.forgeTime = 0;
                	this.totalForgeTime = this.getForgeTime();
                    this.forgeItem();
                }
        		
        	}
            
            this.markDirty();
        }
        
	}

    public int getForgeTime()
    {
    	return 100;
    }
    
    public ItemStack getRecipeResult() {
        return TrophyForgeRecipes.getTrophyForgeResult(new ItemStack[] {
        		this.TrophyForgeItemStacks.get(0),
        		this.TrophyForgeItemStacks.get(1),
        		this.TrophyForgeItemStacks.get(2),
        		this.TrophyForgeItemStacks.get(3),
        		this.TrophyForgeItemStacks.get(4),
        		this.TrophyForgeItemStacks.get(5),
        		this.TrophyForgeItemStacks.get(6),
        		this.TrophyForgeItemStacks.get(7),
        		this.TrophyForgeItemStacks.get(8),
        		});
    }
   
    public boolean canForge() {
    	return this.getRecipeResult() != null;
    }
    
    /**
     * Turn one item from the furnace source stack into the appropriate smelted item in the furnace result stack
     */
    public void forgeItem()
    {
        ItemStack result = this.getRecipeResult();
    	if(result.getItem() instanceof ItemTrophy) {
    		ItemTrophy item = (ItemTrophy) result.getItem();
    		
    		result.setTagCompound(new NBTTagCompound());
    		AttributeModifier attribute = item.getRandomAttributeModifier();
    		result.addAttributeModifier(item.getMonsterAttributes().getName(), attribute, EntityEquipmentSlot.TROPHY_1);
    		result.addAttributeModifier(item.getMonsterAttributes().getName(), attribute, EntityEquipmentSlot.TROPHY_2);
    		result.addAttributeModifier(item.getMonsterAttributes().getName(), attribute, EntityEquipmentSlot.TROPHY_3);
    	}
        for(int i = 0; i < 12; i++) {
        	this.TrophyForgeItemStacks.get(i).shrink(1);
        }
        
        this.TrophyForgeItemStacks.set(4, result.copy());
    }
	
	public String getGuiID() {
		return "minecraft:trophy_forge";
	}
	
	
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn)
	{
		return new ContainerTrophyForge(playerInventory, this);
	}
	
	public int getField(int id)
    {
        switch (id) {
            case 0:
                return this.forgeTime;

            case 1:
                return this.totalForgeTime;
                
            case 2:
            	return this.isForging;

            default:
                return 0;
        }
    }

    public void setField(int id, int value)
    {
        switch (id)
        {
            case 0:
                this.forgeTime = value;
                break;

            case 1:
                this.totalForgeTime = value;
                break;
                
            case 2: 
            	this.isForging = value;
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
                this.isForging = type;
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
        this.TrophyForgeItemStacks.clear();
    }
    
    public boolean isForging() {
		return this.isForging == 1;
	}
    
    public boolean isAnimationEnd() {
		return isAnimationEnd;
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
		return SLOT_CRAFT;
	}
}
