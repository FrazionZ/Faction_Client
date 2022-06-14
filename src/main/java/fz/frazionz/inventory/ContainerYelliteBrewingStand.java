package fz.frazionz.inventory;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionHelper;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;

public class ContainerYelliteBrewingStand extends Container
{
    private final IInventory tileBrewingStand;

    /** Instance of Slot. */
    private final Slot theSlot;

    /**
     * Used to cache the brewing time to send changes to ICrafting listeners.
     */
    private int prevBrewTime;

    /**
     * Used to cache the fuel remaining in the brewing stand to send changes to ICrafting listeners.
     */
    private int prevFuel;

    public ContainerYelliteBrewingStand(InventoryPlayer playerInventory, IInventory tileBrewingStandIn)
    {
        this.tileBrewingStand = tileBrewingStandIn;
        this.addSlotToContainer(new ContainerYelliteBrewingStand.Potion(tileBrewingStandIn, 0, 61, 73));
        this.addSlotToContainer(new ContainerYelliteBrewingStand.Potion(tileBrewingStandIn, 1, 84, 80));
        this.addSlotToContainer(new ContainerYelliteBrewingStand.Potion(tileBrewingStandIn, 2, 107, 73));
        this.theSlot = this.addSlotToContainer(new ContainerYelliteBrewingStand.Ingredient(tileBrewingStandIn, 3, 84, 39));
        this.addSlotToContainer(new ContainerYelliteBrewingStand.Fuel(tileBrewingStandIn, 4, 22, 39));

        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, 13 + j * 18, 106 + i * 18));
            }
        }

        for (int k = 0; k < 9; ++k)
        {
            this.addSlotToContainer(new Slot(playerInventory, k, 13 + k * 18, 164));
        }
    }

    public void addListener(IContainerListener listener)
    {
        super.addListener(listener);
        listener.sendAllWindowProperties(this, this.tileBrewingStand);
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

            if (this.prevBrewTime != this.tileBrewingStand.getField(0))
            {
                icontainerlistener.sendWindowProperty(this, 0, this.tileBrewingStand.getField(0));
            }

            if (this.prevFuel != this.tileBrewingStand.getField(1))
            {
                icontainerlistener.sendWindowProperty(this, 1, this.tileBrewingStand.getField(1));
            }
        }

        this.prevBrewTime = this.tileBrewingStand.getField(0);
        this.prevFuel = this.tileBrewingStand.getField(1);
    }

    public void updateProgressBar(int id, int data)
    {
        this.tileBrewingStand.setField(id, data);
    }

    /**
     * Determines whether supplied player can use this container
     */
    public boolean canInteractWith(EntityPlayer playerIn)
    {
        return this.tileBrewingStand.isUsableByPlayer(playerIn);
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

            if ((index < 0 || index > 2) && index != 3 && index != 4)
            {
                if (this.theSlot.isItemValid(itemstack1))
                {
                    if (!this.mergeItemStack(itemstack1, 3, 4, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                else if (ContainerYelliteBrewingStand.Potion.canHoldPotion(itemstack) && itemstack.getCount() == 1)
                {
                    if (!this.mergeItemStack(itemstack1, 0, 3, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                else if (ContainerYelliteBrewingStand.Fuel.isValidBrewingFuel(itemstack))
                {
                    if (!this.mergeItemStack(itemstack1, 4, 5, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                else if (index >= 5 && index < 32)
                {
                    if (!this.mergeItemStack(itemstack1, 32, 41, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                else if (index >= 32 && index < 41)
                {
                    if (!this.mergeItemStack(itemstack1, 5, 32, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                else if (!this.mergeItemStack(itemstack1, 5, 41, false))
                {
                    return ItemStack.EMPTY;
                }
            }
            else
            {
                if (!this.mergeItemStack(itemstack1, 5, 41, true))
                {
                    return ItemStack.EMPTY;
                }

                slot.onSlotChange(itemstack1, itemstack);
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

    static class Fuel extends Slot
    {
        public Fuel(IInventory iInventoryIn, int index, int xPosition, int yPosition)
        {
            super(iInventoryIn, index, xPosition, yPosition);
        }

        public boolean isItemValid(ItemStack stack)
        {
            return isValidBrewingFuel(stack);
        }

        public static boolean isValidBrewingFuel(ItemStack itemStackIn)
        {
            return itemStackIn.getItem() == Items.BLAZE_POWDER;
        }

        public int getSlotStackLimit()
        {
            return 64;
        }
    }

    static class Ingredient extends Slot
    {
        public Ingredient(IInventory iInventoryIn, int index, int xPosition, int yPosition)
        {
            super(iInventoryIn, index, xPosition, yPosition);
        }

        public boolean isItemValid(ItemStack stack)
        {
            return PotionHelper.isReagent(stack);
        }

        public int getSlotStackLimit()
        {
            return 64;
        }
    }

    static class Potion extends Slot
    
    {
    	
        public Potion(IInventory p_i47598_1_, int p_i47598_2_, int p_i47598_3_, int p_i47598_4_)
        {
            super(p_i47598_1_, p_i47598_2_, p_i47598_3_, p_i47598_4_);
        }

        public boolean isItemValid(ItemStack stack)
        {
            return canHoldPotion(stack);
        }

        public int getSlotStackLimit()
        {
            return 2;
        }

        public ItemStack onTake(EntityPlayer thePlayer, ItemStack stack)
        {
            PotionType potiontype = PotionUtils.getPotionFromItem(stack);

            if (thePlayer instanceof EntityPlayerMP)
            {
                CriteriaTriggers.BREWED_POTION.trigger((EntityPlayerMP)thePlayer, potiontype);
            }

            super.onTake(thePlayer, stack);
            return stack;
        }

        public static boolean canHoldPotion(ItemStack stack)
        {
            Item item = stack.getItem();
            return item == Items.POTIONITEM || item == Items.SPLASH_POTION || item == Items.LINGERING_POTION || item == Items.GLASS_BOTTLE;
        }
    }
}
