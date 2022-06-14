package fz.frazionz.item;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemWitheredBoneMeal extends Item
{
    public ItemWitheredBoneMeal()
    {
        this.setMaxDamage(0);
        this.setCreativeTab(CreativeTabs.MATERIALS);
    }

    /**
     * Called when a Block is right-clicked with this Item
     */
    public EnumActionResult onItemUse(EntityPlayer stack, World playerIn, BlockPos worldIn, EnumHand pos, EnumFacing hand, float facing, float hitX, float hitY)
    {
        ItemStack itemstack = stack.getHeldItem(pos);
        if (applyBonemeal(itemstack, playerIn, worldIn))
        {
            if (!playerIn.isRemote)
            {
                playerIn.playEvent(2005, worldIn, 0);
            }

            return EnumActionResult.SUCCESS;
        }

        return EnumActionResult.PASS;
    }

    public static boolean applyBonemeal(ItemStack stack, World worldIn, BlockPos target)
    {
        IBlockState iblockstate = worldIn.getBlockState(target);

        if (iblockstate.getBlock() instanceof IGrowable)
        {
            IGrowable igrowable = (IGrowable)iblockstate.getBlock();

            if (igrowable.canGrow(worldIn, target, iblockstate, worldIn.isRemote))
            {
                if (!worldIn.isRemote)
                {
                    if (igrowable.canUseBonemeal(worldIn, worldIn.rand, target, iblockstate))
                    {
                        igrowable.grow(worldIn, worldIn.rand, target, iblockstate, true);
                    }

                    stack.shrink(1);
                }

                return true;
            }
        }

        return false;
    }

    public static void spawnBonemealParticles(World worldIn, BlockPos pos, int amount)
    {
        if (amount == 0)
        {
            amount = 15;
        }

        IBlockState iblockstate = worldIn.getBlockState(pos);

        if (iblockstate.getMaterial() != Material.AIR)
        {
            for (int i = 0; i < amount; ++i)
            {
                double d0 = itemRand.nextGaussian() * 0.02D;
                double d1 = itemRand.nextGaussian() * 0.02D;
                double d2 = itemRand.nextGaussian() * 0.02D;
                worldIn.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, (double)((float)pos.getX() + itemRand.nextFloat()), (double)pos.getY() + (double)itemRand.nextFloat() * iblockstate.getBoundingBox(worldIn, pos).maxY, (double)((float)pos.getZ() + itemRand.nextFloat()), d0, d1, d2);
            }
        }
    }
    
    @Override
    public void addInformation(ItemStack stack, World playerIn, List<String> tooltip, ITooltipFlag advanced) {
    	tooltip.add(" ");
    	tooltip.add("\u00A76\u00bb \u00A7ePermet de faire pousser les plantations sur");
    	tooltip.add("\u00A76\u00bb \u00A7eune grande zone. Tel la bone meal sur l'herbe.");
    	tooltip.add(" ");
    }
}
