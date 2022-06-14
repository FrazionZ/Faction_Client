package fz.frazionz.block;

import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockNetherLog extends BlockRotatedPillar
{

    public BlockNetherLog()
    {
    	
        super(Material.WOOD, MapColor.RED);
        this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        this.setHardness(2.0F);
        this.setSoundType(SoundType.WOOD);
        
    }

}
