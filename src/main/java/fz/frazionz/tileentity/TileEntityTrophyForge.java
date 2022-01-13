package fz.frazionz.tileentity;

import fz.frazionz.crafting.TrophyForgeRecipes;
import fz.frazionz.inventory.ContainerTrophyForge;
import fz.frazionz.item.ItemTrophy;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.walkers.ItemStackDataLists;

public class TileEntityTrophyForge extends TileEntityLockable implements ITickable
{
	
	private static final int[] SLOT_CRAFT = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
    
    private NonNullList<ItemStack> TrophyForgeItemStacks = NonNullList.<ItemStack>func_191197_a(12, ItemStack.field_190927_a);
    
    private int forgeTime;
    private int totalForgeTime;
    private int isForging = 0;

	
    // Taille de L'inventaire de l'entity //
    
	public int getSizeInventory() 
	{
		return this.TrophyForgeItemStacks.size();
	}

	public boolean func_191420_l()
    {
        for (ItemStack itemstack : this.TrophyForgeItemStacks)
        {
            if (!itemstack.func_190926_b())
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
		return "TrophyForge";
	}

	public boolean hasCustomName() {
		
		return false;
		
	}
	
    public static void registerFixesTrophyForge(DataFixer fixer)
    {
        fixer.registerWalker(FixTypes.BLOCK_ENTITY, new ItemStackDataLists(TileEntityTrophyForge.class, new String[] {"Items"}));
    }
    
    
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        this.TrophyForgeItemStacks = NonNullList.<ItemStack>func_191197_a(this.getSizeInventory(), ItemStack.field_190927_a);
        ItemStackHelper.func_191283_b(compound, this.TrophyForgeItemStacks);
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
        ItemStackHelper.func_191282_a(compound, this.TrophyForgeItemStacks);

        return compound;
    }

	public void update() {
        
        if (!this.world.isRemote) {
        	
        	if(this.isForging() && !this.canForge()) {
        		this.isForging = 0;
        		this.forgeTime = 0;
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
    	return 2400;
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
    		result.addAttributeModifier(item.getMonsterAttributes().getAttributeUnlocalizedName(), attribute, EntityEquipmentSlot.TROPHY_1);
    		result.addAttributeModifier(item.getMonsterAttributes().getAttributeUnlocalizedName(), attribute, EntityEquipmentSlot.TROPHY_2);
    		result.addAttributeModifier(item.getMonsterAttributes().getAttributeUnlocalizedName(), attribute, EntityEquipmentSlot.TROPHY_3);
    	}
        for(int i = 0; i < 12; i++) {
        	this.TrophyForgeItemStacks.get(i).substract(1);
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
            	break;
        }
    }

    public int getFieldCount()
    {
        return 3;
    }

    public void clear()
    {
        this.TrophyForgeItemStacks.clear();
    }
    
    public boolean isForging() {
		return this.isForging == 1;
	}
}
