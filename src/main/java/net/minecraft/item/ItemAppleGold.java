package net.minecraft.item;

import java.util.Map;

import com.google.common.collect.Maps;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

public class ItemAppleGold extends ItemFood
{
	public static int amount;
	public static float saturation;
	public static boolean isWolfFood;
	
    public ItemAppleGold(int amount, float saturation, boolean isWolfFood)
    {
        super(amount, saturation, isWolfFood);
        this.amount = amount;
        this.saturation = saturation;
        this.isWolfFood = isWolfFood;
        this.setHasSubtypes(true);
        this.setMaxStackSize(16);
        	
    }
    
    public boolean hasEffect(ItemStack stack)
    {
        return super.hasEffect(stack) || stack.getMetadata() > 0;
    }

    
    public EnumRarity getRarity(ItemStack stack)
    {
        return stack.getMetadata() == 0 ? EnumRarity.RARE : EnumRarity.EPIC;
    }

    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player)
    {
        if (!worldIn.isRemote)
        {
            if (stack.getMetadata() > 0)
            {
                player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 600, 4));
                player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 6000, 0));
                player.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 6000, 0));
            }
            else
            {
                player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 100, 1));
                player.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 2400, 0));
            }
        }
    }

    /**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     */
    public void getSubItems(CreativeTabs itemIn, NonNullList<ItemStack> tab)
    {
        if (this.func_194125_a(itemIn))
        {
        	for(ItemAppleGold.AppleType appletype : ItemAppleGold.AppleType.values()) {
        		
        		ItemStack stack = new ItemStack(this, 1, appletype.getMeta());
        		tab.add(stack);
        	}
        }
    }
    
    public static enum AppleType
    {
        NORMAL(0, 16, ItemAppleGold.amount, ItemAppleGold.saturation, ItemAppleGold.isWolfFood),
        CHEAT(1, 8, ItemAppleGold.amount, ItemAppleGold.saturation, ItemAppleGold.isWolfFood);
    	
        private final int meta;
        private final int maxStackSize;
        private final int amount;
        private final float saturation;
        private final boolean isWolfFood;

        AppleType(int meta, int maxStackSize, int amount, float saturation, boolean isWolfFood)
        {
            this.meta = meta;
            this.maxStackSize = maxStackSize;
            this.amount = amount;
            this.saturation = saturation;
            this.isWolfFood = isWolfFood;
        }
        
        public int getAmount() {
			return amount;
		}
        
        public int getMaxStackSize() {
			return maxStackSize;
		}
        
        public int getMeta() {
			return meta;
		}
        
        public float getSaturation() {
			return saturation;
		}
    }
}
