package fz.frazionz.item;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.Multimap;

import fz.frazionz.utils.EffectItem;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemLegendarySword extends ItemWeaponEffects
{
    public ItemLegendarySword(Item.ToolMaterial material)
    {
    	super(material, Arrays.asList(new EffectItem(MobEffects.SPEED, 200000, 1),new EffectItem(MobEffects.STRENGTH, 200000, 1)));
    }
    
    public void addInformation(ItemStack stack, @Nullable World playerIn, List<String> tooltip, ITooltipFlag advanced)
    {
    	tooltip.add(" ");
    	tooltip.add("\u00A76\u00bb \u00A7eD\u00E9gats normaux");
    	tooltip.add("\u00A76\u00bb \u00A7eReach normale");
    	tooltip.add("\u00A76\u00bb \u00A7eAttaque normale");
    	tooltip.add(" ");
    	tooltip.add("\u00A76\u00bb \u00A7eEffect : Speed 2 / Force 2");
    	tooltip.add(" ");
    }
}
