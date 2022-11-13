package fz.frazionz.client.stats;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class StatHelper {

    public static ItemStack applyStats(ItemStack itemStack, EnumStats stat, int value) {
        if (itemStack.hasTagCompound()) {
            if (itemStack.getTagCompound().hasKey("stats")) {
                itemStack.getTagCompound().getCompoundTag("stats").setInteger(stat.name(), value);
            } else {
                itemStack.getTagCompound().setTag("stats", new NBTTagCompound());
                itemStack.getTagCompound().getCompoundTag("stats").setInteger(stat.name(), value);
            }
        } else {
            itemStack.setTagCompound(new NBTTagCompound());
            itemStack.getTagCompound().setTag("stats", new NBTTagCompound());
            itemStack.getTagCompound().getCompoundTag("stats").setInteger(stat.name(), value);
        }
        return itemStack;
    }

    public static int getStatValue(ItemStack itemStack, EnumStats stat) {
        if(hasStat(itemStack, stat)) {
            return itemStack.getTagCompound().getCompoundTag("stats").getInteger(stat.name());
        }
        return 0;
    }

    public static boolean hasStat(ItemStack itemStack, EnumStats stat) {
        return hasStats(itemStack) && itemStack.getTagCompound().getCompoundTag("stats").hasKey(stat.name());
    }

    public static boolean hasStats(ItemStack itemStack) {
        return itemStack.hasTagCompound() && itemStack.getTagCompound().hasKey("stats");
    }

    public static boolean playerWearFullArmorSet(EntityPlayer player, ItemArmor.ArmorMaterial armor) {
        return player.isWearingFullArmorSet(armor);
    }

}
