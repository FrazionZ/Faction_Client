package net.minecraft.block;

import java.util.Random;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockObsidian extends Block
{
    public BlockObsidian()
    {
        super(Material.ROCK);
        this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
    }

    /**
     * Get the Item that this Block should drop when harvested.
     */
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        if (this == Blocks.OBSIDIAN)
        {
            return Item.getItemFromBlock(Blocks.OBSIDIAN);
        }
        else if (this == Blocks.OBSIDIAN_YELLITE)
        {
            return Item.getItemFromBlock(Blocks.OBSIDIAN_YELLITE);
        }
        else if (this == Blocks.OBSIDIAN_BAUXITE)
        {
            return Item.getItemFromBlock(Blocks.OBSIDIAN_BAUXITE);
        }
        else if (this == Blocks.OBSIDIAN_ONYX)
        {
            return Item.getItemFromBlock(Blocks.OBSIDIAN_ONYX);
        }
        else
        {
            return this == Blocks.OBSIDIAN_FRAZION ? Item.getItemFromBlock(Blocks.OBSIDIAN_FRAZION) : Item.getItemFromBlock(this);
        }
    }

    /**
     * Get the MapColor for this Block and the given BlockState
     * @deprecated call via {@link IBlockState#getMapColor(IBlockAccess,BlockPos)} whenever possible.
     * Implementing/overriding is fine.
     */
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        return MapColor.BLACK;
    }
}
