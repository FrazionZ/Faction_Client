package fz.frazionz.item;

import java.util.*;
import java.util.Map.Entry;

import com.google.common.collect.Multimap;

import fz.frazionz.client.stats.EnumStats;
import fz.frazionz.utils.MathUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

public abstract class ItemTrophy extends Item {

	public ItemTrophy() {
		this.setCreativeTab(CreativeTabs.MATERIALS);
		this.setMaxStackSize(1);
	}
    
    @Override
    public void addInformation(ItemStack stack, World playerIn, List<String> tooltip, ITooltipFlag advanced) {

        NBTTagCompound nbt = stack.getTagCompound();
        tooltip.add(" ");
        if(nbt != null) {
            for(EnumStats stat : EnumStats.values()) {
                if(nbt.hasKey(stat.name())) {
                    int value = nbt.getInteger(stat.name());
                    if(value > 0) {
                        tooltip.add("\u00A76\u2022 \u00A7e" + I18n.translateToLocal("frazionz.stat." + stat.name().toLowerCase() + ".name") + " \u00A77" + value);
                    }
                    else if(value < 0) {
                        tooltip.add("\u00A76\u2022 \u00A7e" + I18n.translateToLocal("frazionz.stat." + stat.name().toLowerCase() + ".name") + " \u00A77" + value);
                    }
                }
            }
        }
        else {
            tooltip.add("\u00A76\u2022 \u00A7eTrophy without stats...");
        }
        tooltip.add(" ");
    }

    public abstract int getRandomStatModifier();

    public abstract EnumStats getBaseStat();

    public void randomBaseStat(ItemStack stack) {
        if(stack.getTagCompound() == null) {
            stack.setTagCompound(new NBTTagCompound());
        }
        setBaseStatValue(stack, getRandomStatModifier());
    }
    public int getBaseStatValue(ItemStack stack) {
        return getOtherStatValue(stack, getBaseStat());
    }

    public void setBaseStatValue(ItemStack stack, int statValue) {
        setOtherStatValue(stack, getBaseStat(), statValue);
    }

    public void setOtherStatValue(ItemStack stack, EnumStats stat, int value) {
        NBTTagCompound nbt = stack.getTagCompound();
        if(nbt == null) {
            nbt = new NBTTagCompound();
        }
        nbt.setInteger(stat.name(), value);
        stack.setTagCompound(nbt);
    }

    public int getOtherStatValue(ItemStack stack, EnumStats stat) {
        NBTTagCompound nbt = stack.getTagCompound();
        if(nbt != null) {
            if(nbt.hasKey(stat.name())) {
                return nbt.getInteger(stat.name());
            }
        }
        return 0;
    }
}
