package fz.frazionz.tileentity;

import fz.frazionz.inventory.ContainerAmeliorator;
import fz.frazionz.item.crafting.AmelioratorRecipes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.walkers.ItemStackDataLists;
import net.minecraft.util.math.MathHelper;

public class TileEntityAmeliorator extends TileEntityLockable implements ITickable
{
	
	private static final int[] SLOT_CRAFT = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9};
    private static final int[] SLOT_FUEL = new int[] {0};
    
    private NonNullList<ItemStack> AmelioratorItemStacks = NonNullList.<ItemStack>withSize(10, ItemStack.EMPTY);
    
    private int burnTime;
    private int currentItemBurnTime;
    private int cookTime;
    private int totalCookTime;

	
    // Taille de L'inventaire de l'entity //
    
	public int getSizeInventory() {
		
		return this.AmelioratorItemStacks.size();
		
	}

    public boolean isEmpty()
    {
        for (ItemStack itemstack : this.AmelioratorItemStacks)
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
        return this.AmelioratorItemStacks.get(index);
    }
	
	
    /**
     * Removes up to a specified number of items from an inventory slot and returns them in a new stack.
     */
	public ItemStack decrStackSize(int index, int count)
    {
        return ItemStackHelper.getAndSplit(this.AmelioratorItemStacks, index, count);
    }
	
    /**
     * Removes a stack from the given slot and returns it.
     */
	public ItemStack removeStackFromSlot(int index)
    {
        return ItemStackHelper.getAndRemove(this.AmelioratorItemStacks, index);
    }

	/**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    public void setInventorySlotContents(int index, ItemStack stack)
    {
    	
    	this.AmelioratorItemStacks.set(index, stack);
    	
        if (index >= 0 && index <= this.AmelioratorItemStacks.size())
        {
        	this.totalCookTime = this.getCookTime();
        	this.cookTime = 0;
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
		
		return "Ameliorator";
		
	}

	public boolean hasCustomName() {
		
		return false;
		
	}
	
    public static void registerFixesAmeliorator(DataFixer fixer)
    {
        fixer.registerWalker(FixTypes.BLOCK_ENTITY, new ItemStackDataLists(TileEntityAmeliorator.class, new String[] {"Items"}));
    }
    
    
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        this.AmelioratorItemStacks = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound, this.AmelioratorItemStacks);
        this.burnTime = compound.getShort("BurnTime");
        this.cookTime = compound.getShort("CookTime");
        this.totalCookTime = compound.getShort("CookTimeTotal");
        this.currentItemBurnTime = getItemBurnTime(this.AmelioratorItemStacks.get(0));
    }

    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setShort("BurnTime", (short)this.burnTime);
        compound.setShort("CookTime", (short)this.cookTime);
        compound.setShort("CookTimeTotal", (short)this.totalCookTime);
        ItemStackHelper.saveAllItems(compound, this.AmelioratorItemStacks);

        return compound;
    }
    
	/**
     * Furnace isBurning
     */
    public boolean isBurning()
    {
        return this.burnTime > 0;
    }

    public static boolean isBurning(IInventory inventory)
    {
        return inventory.getField(0) > 0;
    }

	public void update() {
		
		
		boolean flag = this.isBurning();
        boolean flag1 = false;

        if (this.isBurning())
        {
            --this.burnTime;
        }
        
        if (!this.world.isRemote) {
        	
        	ItemStack itemstack = this.AmelioratorItemStacks.get(0);
        	
        	if (this.isBurning() || this.canSmelt())
            {
     	
	            if (!this.isBurning() && this.canSmelt())
	            {
	            	this.burnTime = getItemBurnTime(itemstack);
	                this.currentItemBurnTime = this.burnTime;
	                
	                if (this.isBurning())
                    {
                        flag1 = true;
                        this.decrStackSize(0, 1);
                        
                    }
	            }
	     
	            if (this.isBurning() && this.canSmelt())
	            {
	                this.cookTime++;
	                
	                if (this.cookTime >= this.getCookTime())
	                {
	                	this.cookTime = 0;
	                	this.totalCookTime = this.getCookTime();
	                    this.smeltItem();
                        flag1 = true;
	                }
	            }
	            else
	            {
	            	this.cookTime = 0;
	            }
            
            }
        	
            else if (!this.isBurning() && this.cookTime > 0)
            {
                this.cookTime = MathHelper.clamp(this.cookTime - 2, 0, this.totalCookTime);
            }
            
            this.markDirty();
        }
	}

	
	
    public int getCookTime()
    {
        //return 36000;
    	return 6000;
    }
    
    public ItemStack getRecipeResult() {
        return AmelioratorRecipes.getAmelioratorResult(new ItemStack[] {
        		this.AmelioratorItemStacks.get(1),
        		this.AmelioratorItemStacks.get(2),
        		this.AmelioratorItemStacks.get(3),
        		this.AmelioratorItemStacks.get(4),
        		this.AmelioratorItemStacks.get(5),
        		this.AmelioratorItemStacks.get(6),
        		this.AmelioratorItemStacks.get(7),
        		this.AmelioratorItemStacks.get(8),
        		this.AmelioratorItemStacks.get(9),
        		});
    }
    
    public boolean canSmelt() {
    	return this.getRecipeResult() != null;
    }
    
    /**
     * Turn one item from the furnace source stack into the appropriate smelted item in the furnace result stack
     */
    public void smeltItem()
    {
        ItemStack result = this.getRecipeResult();
        
        ItemStack i1 = this.AmelioratorItemStacks.get(1);
        ItemStack i2 = this.AmelioratorItemStacks.get(2);
        ItemStack i3 = this.AmelioratorItemStacks.get(3);
        ItemStack i4 = this.AmelioratorItemStacks.get(4);
        ItemStack i5 = this.AmelioratorItemStacks.get(5);
        ItemStack i6 = this.AmelioratorItemStacks.get(6);
        ItemStack i7 = this.AmelioratorItemStacks.get(7);
        ItemStack i8 = this.AmelioratorItemStacks.get(8);
        ItemStack i9 = this.AmelioratorItemStacks.get(9);
        
        // On enlève un item de chaque ingrédient
        i1.shrink(1);
        i2.shrink(1);
        i3.shrink(1);
        i4.shrink(1);
        i5.shrink(1);
        i6.shrink(1);
        i7.shrink(1);
        i8.shrink(1);
        i9.shrink(1);
        // On récupère le slot de résultat
        ItemStack stack4 = this.getStackInSlot(9);
        // Si il est vide
        if(i9.isEmpty()) {
        	
        	this.AmelioratorItemStacks.set(9, result.copy());
        	
        }
    }
	
	
	/**
     * Returns the number of ticks that the supplied fuel item will keep the furnace burning, or 0 if the item isn't
     * fuel
     */
    public static int getItemBurnTime(ItemStack stack)
    {
        if (stack.isEmpty())
        {
            return 0;
        }
        else
        {
            Item item = stack.getItem();

            if (item == Items.BIG_XP)
            {
                return 190;
            }
            else
            {
                return 0;
            }
        }
    }
    
   
    
    

    public static boolean isItemFuel(ItemStack stack)
    {
        return getItemBurnTime(stack) > 0;
    }
	
	
	
	
	public String getGuiID() {
		return "frazionz:ameliorator";
	}
	
	
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn)
	{
		return new ContainerAmeliorator(playerInventory, this);
	}
	
	public int getField(int id)
    {
        switch (id)
        {
            case 0:
                return this.burnTime;

            case 1:
                return this.currentItemBurnTime;

            case 2:
                return this.cookTime;

            case 3:
                return this.totalCookTime;

            default:
                return 0;
        }
    }

    public void setField(int id, int value)
    {
        switch (id)
        {
            case 0:
                this.burnTime = value;
                break;

            case 1:
                this.currentItemBurnTime = value;
                break;

            case 2:
                this.cookTime = value;
                break;

            case 3:
                this.totalCookTime = value;
        }
    }

    public int getFieldCount()
    {
        return 4;
    }

    public void clear()
    {
        this.AmelioratorItemStacks.clear();
    }
	
	
}
