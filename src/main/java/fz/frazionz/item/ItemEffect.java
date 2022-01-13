package fz.frazionz.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityBigXp;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class ItemEffect extends Item
{

    public boolean hasEffect(ItemStack stack)
    {
        return true;
    }
}
