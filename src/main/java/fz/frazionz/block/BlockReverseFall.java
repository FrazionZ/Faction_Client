package fz.frazionz.block;

import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockReverseFall extends BlockBreakable
{
    public BlockReverseFall()
    {
        super(Material.GRASS, false, MapColor.YELLOW);
        this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
    }
    
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }

    /**
     * Block's chance to react to a living entity falling on it.
     */
    public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance)
    {
    	if(entityIn instanceof EntityLivingBase && ((EntityLivingBase) entityIn).isPotionActive(Potion.getPotionById(28))) {
    		super.onFallenUpon(worldIn, pos, entityIn, fallDistance);
    	}
    	else {
    		entityIn.fall(fallDistance, 0.0F);
    	}
    }
}
