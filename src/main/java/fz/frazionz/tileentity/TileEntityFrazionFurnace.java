package fz.frazionz.tileentity;

import fz.frazionz.block.BlockFrazionFurnace;
import fz.frazionz.inventory.ContainerFrazionFurnace;
import fz.frazionz.item.crafting.ZFurnaceRecipes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.SlotFurnaceFuel;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.walkers.ItemStackDataLists;
import net.minecraft.util.math.MathHelper;

public class TileEntityFrazionFurnace extends TileEntityLockable implements ITickable, ISidedInventory
{
    private static final int[] SLOTS_TOP = new int[] {0, 1, 2, 3, 4, 5};
    private static final int[] SLOTS_BOTTOM = new int[] {6, 7, 8, 9, 10, 11, 12};
    private static final int[] SLOTS_SIDES = new int[] {6};
    private NonNullList<ItemStack> furnaceItemStacks = NonNullList.<ItemStack>withSize(13, ItemStack.EMPTY);

    /** The number of ticks that the furnace will keep burning */
    private int furnaceBurnTime;

    /**
     * The number of ticks that a fresh copy of the currently-burning item would keep the furnace burning for
     */
    private int currentItemBurnTime;
    private int cookTime;
    private int totalCookTime;
    private String furnaceCustomName;

    /**
     * Returns the number of slots in the inventory.
     */
    public int getSizeInventory()
    {
        return this.furnaceItemStacks.size();
    }

    public boolean isEmpty()
    {
        for (ItemStack itemstack : this.furnaceItemStacks)
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
        return this.furnaceItemStacks.get(index);
    }

    /**
     * Removes up to a specified number of items from an inventory slot and returns them in a new stack.
     */
    public ItemStack decrStackSize(int index, int count)
    {
        return ItemStackHelper.getAndSplit(this.furnaceItemStacks, index, count);
    }

    /**
     * Removes a stack from the given slot and returns it.
     */
    public ItemStack removeStackFromSlot(int index)
    {
        return ItemStackHelper.getAndRemove(this.furnaceItemStacks, index);
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    public void setInventorySlotContents(int index, ItemStack stack)
    {
        ItemStack itemstack = this.furnaceItemStacks.get(index);
        boolean flag = !stack.isEmpty() && stack.isItemEqual(itemstack) && ItemStack.areItemStackTagsEqual(stack, itemstack);
        this.furnaceItemStacks.set(index, stack);

        if (stack.getCount() > this.getInventoryStackLimit())
        {
            stack.setCount(this.getInventoryStackLimit());
        }

        if ((index == 0 || index == 1 || index == 2 || index == 3 || index == 4 || index == 5) && !flag)
        {
            this.totalCookTime = this.getCookTime(stack);
            this.cookTime = 0;
            this.markDirty();
        }
    }

    /**
     * Get the name of this object. For players this returns their username
     */
    public String getName()
    {
        return this.hasCustomName() ? this.furnaceCustomName : "container.furnace";
    }

    /**
     * Returns true if this thing is named
     */
    public boolean hasCustomName()
    {
        return this.furnaceCustomName != null && !this.furnaceCustomName.isEmpty();
    }

    public void setCustomInventoryName(String p_145951_1_)
    {
        this.furnaceCustomName = p_145951_1_;
    }

    public static void registerFixesFurnace(DataFixer fixer)
    {
        fixer.registerWalker(FixTypes.BLOCK_ENTITY, new ItemStackDataLists(TileEntityFrazionFurnace.class, new String[] {"Items"}));
    }

    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        this.furnaceItemStacks = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound, this.furnaceItemStacks);
        this.furnaceBurnTime = compound.getShort("BurnTime");
        this.cookTime = compound.getShort("CookTime");
        this.totalCookTime = compound.getShort("CookTimeTotal");
        this.currentItemBurnTime = getItemBurnTime(this.furnaceItemStacks.get(1));

        if (compound.hasKey("CustomName", 8))
        {
            this.furnaceCustomName = compound.getString("CustomName");
        }
    }

    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setShort("BurnTime", (short)this.furnaceBurnTime);
        compound.setShort("CookTime", (short)this.cookTime);
        compound.setShort("CookTimeTotal", (short)this.totalCookTime);
        ItemStackHelper.saveAllItems(compound, this.furnaceItemStacks);

        if (this.hasCustomName())
        {
            compound.setString("CustomName", this.furnaceCustomName);
        }

        return compound;
    }

    /**
     * Returns the maximum stack size for a inventory slot. Seems to always be 64, possibly will be extended.
     */
    public int getInventoryStackLimit()
    {
        return 64;
    }

    /**
     * Furnace isBurning
     */
    public boolean isBurning()
    {
        return this.furnaceBurnTime > 0;
    }

    public static boolean isBurning(IInventory inventory)
    {
        return inventory.getField(0) > 0;
    }

    /**
     * Like the old updateEntity(), except more generic.
     */
    public void update()
    {
        boolean flag = this.isBurning();
        boolean flag1 = false;

        if (this.isBurning())
        {
            --this.furnaceBurnTime;
        }

        if (!this.world.isRemote)
        {
            ItemStack itemstack = this.furnaceItemStacks.get(6);

            // SI CA BRULE OU BRULE PAS //
            
            if ((this.isBurning() || (!itemstack.isEmpty() && !((ItemStack)this.furnaceItemStacks.get(0)).isEmpty()) || ( !itemstack.isEmpty() && !((ItemStack)this.furnaceItemStacks.get(1)).isEmpty()) || ( !itemstack.isEmpty() && !((ItemStack)this.furnaceItemStacks.get(2)).isEmpty()) || ( !itemstack.isEmpty() && !((ItemStack)this.furnaceItemStacks.get(3)).isEmpty() || ( !itemstack.isEmpty() && !((ItemStack)this.furnaceItemStacks.get(4)).isEmpty() || ( !itemstack.isEmpty() && !((ItemStack)this.furnaceItemStacks.get(5)).isEmpty())))))
            {
                if (!this.isBurning() && this.canSmelt())
                {
                    this.furnaceBurnTime = getItemBurnTime(itemstack);
                    this.currentItemBurnTime = this.furnaceBurnTime;

                    if (this.isBurning())
                    {
                        flag1 = true;

                        if (!itemstack.isEmpty())
                        {
                            Item item = itemstack.getItem();
                            itemstack.shrink(1);

                            if (itemstack.isEmpty())
                            {
                                Item item1 = item.getContainerItem();
                                this.furnaceItemStacks.set(6, item1 == null ? ItemStack.EMPTY : new ItemStack(item1));
                            }
                        }
                    }
                }

                if (this.isBurning() && this.canSmelt())
                {
                    ++this.cookTime;

                    if (this.cookTime == this.totalCookTime)
                    {
                        this.cookTime = 0;
                        this.totalCookTime = this.getCookTime(this.furnaceItemStacks.get(0));
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

            if (flag != this.isBurning())
            {
                flag1 = true;
                BlockFrazionFurnace.setState(this.isBurning(), this.world, this.pos);
            }
        }

        if (flag1)
        {
            this.markDirty();
        }
    }

    public int getCookTime(ItemStack stack)
    {
        return 5;
    }

    public boolean yolo(int aaa){
    	
    	return ((ItemStack)this.furnaceItemStacks.get(aaa)).isEmpty();
    	
    }
    
    /**
     * Returns true if the furnace can smelt an item, i.e. has a source item, destination stack isn't full, etc.
     */
    private boolean canSmelt()
    {
        if (yolo(0) && yolo(1) && yolo(2) && yolo(3) && yolo(4) && yolo(5))
        {
            return false;
        }
        else
        {
            ItemStack itemstack = ZFurnaceRecipes.instance().getSmeltingResult(this.furnaceItemStacks.get(0));
            
            ItemStack itemstackA = ZFurnaceRecipes.instance().getSmeltingResult(this.furnaceItemStacks.get(1));
            
            ItemStack itemstackB = ZFurnaceRecipes.instance().getSmeltingResult(this.furnaceItemStacks.get(2));
            
            ItemStack itemstackC = ZFurnaceRecipes.instance().getSmeltingResult(this.furnaceItemStacks.get(3));
            
            ItemStack itemstackD = ZFurnaceRecipes.instance().getSmeltingResult(this.furnaceItemStacks.get(4));
            
            ItemStack itemstackE = ZFurnaceRecipes.instance().getSmeltingResult(this.furnaceItemStacks.get(5));

        	if (itemstack.isEmpty() && !this.furnaceItemStacks.get(0).equals(ItemStack.EMPTY))
            {
                return false;
            }
        	else if (itemstackA.isEmpty() && !this.furnaceItemStacks.get(1).equals(ItemStack.EMPTY))
            {
                return false;
            }
        	else if (itemstackB.isEmpty() && !this.furnaceItemStacks.get(2).equals(ItemStack.EMPTY))
            {
                return false;
            }
        	else if (itemstackC.isEmpty() && !this.furnaceItemStacks.get(3).equals(ItemStack.EMPTY))
            {
                return false;
            }
        	else if (itemstackD.isEmpty() && !this.furnaceItemStacks.get(4).equals(ItemStack.EMPTY))
            {
                return false;
            }
        	else if (itemstackE.isEmpty() && !this.furnaceItemStacks.get(5).equals(ItemStack.EMPTY))
            {
                return false;
            }
        	else if (itemstack.isEmpty()  && itemstackA.isEmpty() && itemstackB.isEmpty() && itemstackC.isEmpty() && itemstackD.isEmpty() && itemstackE.isEmpty())
            {
                return false;
            }
            else
            {
                ItemStack itemstack1 = this.furnaceItemStacks.get(7);
                
                ItemStack itemstackA1 = this.furnaceItemStacks.get(8);
                
                ItemStack itemstackB1 = this.furnaceItemStacks.get(9);
                
                ItemStack itemstackC1 = this.furnaceItemStacks.get(10);
                
                ItemStack itemstackD1 = this.furnaceItemStacks.get(11);
                
                ItemStack itemstackE1 = this.furnaceItemStacks.get(12);

                if (itemstack1.isEmpty() && itemstackA1.isEmpty() && itemstackB1.isEmpty() && itemstackC1.isEmpty() && itemstackD1.isEmpty() && itemstackE1.isEmpty())
                {
                    return true;
                }
                else if (!itemstack1.isItemEqual(itemstack) && !itemstackA1.isItemEqual(itemstackA) && !itemstackB1.isItemEqual(itemstackB) && !itemstackC1.isItemEqual(itemstackC) && !itemstackD1.isItemEqual(itemstackD) && !itemstackE1.isItemEqual(itemstackE))
                {
                    return false;
                }
                else if (itemstack1.getCount() < this.getInventoryStackLimit() && itemstack1.getCount() < itemstack1.getMaxStackSize())
                {
                    return true;
                }
                else if (itemstackA1.getCount() < this.getInventoryStackLimit() && itemstackA1.getCount() < itemstackA1.getMaxStackSize())
                {
                    return true;
                }
                else if (itemstackB1.getCount() < this.getInventoryStackLimit() && itemstackB1.getCount() < itemstackB1.getMaxStackSize())
                {
                    return true;
                }
                else if (itemstackC1.getCount() < this.getInventoryStackLimit() && itemstackC1.getCount() < itemstackC1.getMaxStackSize())
                {
                    return true;
                }
                else if (itemstackD1.getCount() < this.getInventoryStackLimit() && itemstackD1.getCount() < itemstackD1.getMaxStackSize())
                {
                    return true;
                }
                else if (itemstackE1.getCount() < this.getInventoryStackLimit() && itemstackE1.getCount() < itemstackE1.getMaxStackSize())
                {
                    return true;
                }
                else
                {
                    return itemstack1.getCount() < itemstack.getMaxStackSize();
                }
            }
        }
    }

    /**
     * Turn one item from the furnace source stack into the appropriate smelted item in the furnace result stack
     */
    public void smeltItem()
    {
        if (this.canSmelt())
        {
            ItemStack itemstack = this.furnaceItemStacks.get(0);
            ItemStack itemstackA = this.furnaceItemStacks.get(1);
            ItemStack itemstackB = this.furnaceItemStacks.get(2);
            ItemStack itemstackC = this.furnaceItemStacks.get(3);
            ItemStack itemstackD = this.furnaceItemStacks.get(4);
            ItemStack itemstackE = this.furnaceItemStacks.get(5);
            ItemStack itemstack1 = ZFurnaceRecipes.instance().getSmeltingResult(itemstack);
            ItemStack itemstackA1 = ZFurnaceRecipes.instance().getSmeltingResult(itemstackA);
            ItemStack itemstackB1 = ZFurnaceRecipes.instance().getSmeltingResult(itemstackB);
            ItemStack itemstackC1 = ZFurnaceRecipes.instance().getSmeltingResult(itemstackC);
            ItemStack itemstackD1 = ZFurnaceRecipes.instance().getSmeltingResult(itemstackD);
            ItemStack itemstackE1 = ZFurnaceRecipes.instance().getSmeltingResult(itemstackE);
            ItemStack itemstack2 = this.furnaceItemStacks.get(7);
            ItemStack itemstackA2 = this.furnaceItemStacks.get(8);
            ItemStack itemstackB2 = this.furnaceItemStacks.get(9);
            ItemStack itemstackC2 = this.furnaceItemStacks.get(10);
            ItemStack itemstackD2 = this.furnaceItemStacks.get(11);
            ItemStack itemstackE2 = this.furnaceItemStacks.get(12);

            if (itemstack2.isEmpty())
            {
                this.furnaceItemStacks.set(7, itemstack1.copy());
                itemstack.shrink(1);
            }
            else if (itemstack2.getItem() == itemstack1.getItem())
            {
                itemstack2.grow(1);
                itemstack.shrink(1);
            }
            
            if (itemstackA2.isEmpty())
            {
                this.furnaceItemStacks.set(8, itemstackA1.copy());
                itemstackA.shrink(1);
            }
            else if (itemstackA2.getItem() == itemstackA1.getItem())
            {
                itemstackA2.grow(1);
                itemstackA.shrink(1);
            }
            
            if (itemstackB2.isEmpty())
            {
                this.furnaceItemStacks.set(9, itemstackB1.copy());
                itemstackB.shrink(1);
            }
            else if (itemstackB2.getItem() == itemstackB1.getItem())
            {
                itemstackB2.grow(1);
                itemstackB.shrink(1);
            }
            
            if (itemstackC2.isEmpty())
            {
                this.furnaceItemStacks.set(10, itemstackC1.copy());
                itemstackC.shrink(1);
            }
            else if (itemstackC2.getItem() == itemstackC1.getItem())
            {
                itemstackC2.grow(1);
                itemstackC.shrink(1);
            }
            
            if (itemstackD2.isEmpty())
            {
                this.furnaceItemStacks.set(11, itemstackD1.copy());
                itemstackD.shrink(1);
            }
            else if (itemstackD2.getItem() == itemstackD1.getItem())
            {
                itemstackD2.grow(1);
                itemstackD.shrink(1);
            }
            
            if (itemstackE2.isEmpty())
            {
                this.furnaceItemStacks.set(12, itemstackE1.copy());
                itemstackE.shrink(1);
            }
            else if (itemstackE2.getItem() == itemstackE1.getItem())
            {
                itemstackE2.grow(1);
                itemstackE.shrink(1);
            }

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

            if (item == Items.BLAZE_ROD)
            {
                return 1000;
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
        if (index == 7 || index == 8 || index == 9 || index == 10 || index == 11 || index == 12)
        {
            return false;
        }
        else if (index != 6)
        {
            return true;
        }
        else
        {
            ItemStack itemstack = this.furnaceItemStacks.get(6);
            return isItemFuel(stack) || SlotFurnaceFuel.isBucket(stack) && itemstack.getItem() != Items.BUCKET;
        }
    }

    public int[] getSlotsForFace(EnumFacing side)
    {
        if (side == EnumFacing.DOWN)
        {
            return SLOTS_BOTTOM;
        }
        else
        {
            return side == EnumFacing.UP ? SLOTS_TOP : SLOTS_SIDES;
        }
    }

    /**
     * Returns true if automation can insert the given item in the given slot from the given side.
     */
    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction)
    {
        return this.isItemValidForSlot(index, itemStackIn);
    }

    /**
     * Returns true if automation can extract the given item in the given slot from the given side.
     */
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction)
    {
        if (direction == EnumFacing.DOWN && index == 6)
        {
            Item item = stack.getItem();

            if (item != Items.WATER_BUCKET && item != Items.BUCKET)
            {
                return false;
            }
        }

        return true;
    }

    public String getGuiID()
    {
        return "minecraft:frazion_furnace";
    }

    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn)
    {
        return new ContainerFrazionFurnace(playerInventory, this);
    }

    public int getField(int id)
    {
        switch (id)
        {
            case 0:
                return this.furnaceBurnTime;

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
                this.furnaceBurnTime = value;
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
        this.furnaceItemStacks.clear();
    }
}
