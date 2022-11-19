package fz.frazionz.item.trophies;

import java.util.*;

import fz.frazionz.client.stats.EnumStats;
import fz.frazionz.client.stats.StatHelper;
import fz.frazionz.item.interfaces.IStatItem;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

public abstract class ItemTrophy extends Item implements IStatItem {

	public ItemTrophy() {
		this.setCreativeTab(CreativeTabs.MATERIALS);
		this.setMaxStackSize(1);
	}
    
    @Override
    public void addInformation(ItemStack stack, World playerIn, List<String> tooltip, ITooltipFlag advanced) {
        NBTTagCompound nbt = stack.getTagCompound();
        if(nbt == null || !nbt.hasKey("stats")) {
            tooltip.add("\u00A7eTrophy without stats...");
        }
    }

    public abstract int getRandomStatModifier();

    public abstract EnumStats getBaseStat();

    public void randomBaseStat(ItemStack stack) {
        setBaseStatValue(stack, getRandomStatModifier());
    }
    public int getBaseStatValue(ItemStack stack) {
        return getStatValue(stack, getBaseStat());
    }

    public void setBaseStatValue(ItemStack stack, int statValue) {
        setStatValue(stack, getBaseStat(), statValue);
    }

    public void setStatValue(ItemStack stack, EnumStats stat, int value) {
        StatHelper.applyStats(stack, stat, value);
    }

    public int getStatValue(ItemStack stack, EnumStats stat) {
        return StatHelper.getStatValue(stack, stat);
    }
}
