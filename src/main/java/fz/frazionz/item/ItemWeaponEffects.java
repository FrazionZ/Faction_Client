package fz.frazionz.item;

import java.util.List;

import com.google.common.collect.Multimap;

import fz.frazionz.utils.EffectItem;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class ItemWeaponEffects extends ItemSword
{
    private final float attackDamage;
    private final Item.ToolMaterial material;
	private List<EffectItem> effectList;
	private String[] lore;
	
    public ItemWeaponEffects(Item.ToolMaterial material, List<EffectItem> effectList)
    {
        super(material);
        this.material = material;
        this.maxStackSize = 1;
        this.setMaxDamage(material.getMaxUses());
        this.setCreativeTab(CreativeTabs.COMBAT);
        this.attackDamage = 3.0F + material.getDamageVsEntity();
        this.effectList = effectList;
    }

    public List<EffectItem> getEffectList()
    {
    	return this.effectList;
    }
}
