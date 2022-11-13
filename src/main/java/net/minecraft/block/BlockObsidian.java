package net.minecraft.block;

import java.util.Random;

import fz.frazionz.block.enums.ExplosiveType;
import fz.frazionz.block.interfaces.FzExplosionChance;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockObsidian extends Block implements FzExplosionChance
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

    @Override
    public float getExplosionChance(ExplosiveType type) {
        if(this == Blocks.OBSIDIAN) {
            switch(type){
                case TNT:
                    return 0.08f;
                case Z_TNT:
                    return 1;
                default:
                    return 0;
            }
        }
        if(this == Blocks.OBSIDIAN_YELLITE) {
            switch(type){
                case TNT:
                    return 0.05f;
                case Z_TNT:
                    return 1;
                default:
                    return 0;
            }
        }
        if(this == Blocks.OBSIDIAN_BAUXITE) {
            switch(type){
                case TNT:
                    return 0.02f;
                case Z_TNT:
                    return 0.5f;
                default:
                    return 0;
            }
        }
        if(this == Blocks.OBSIDIAN_ONYX) {
            switch(type){
                case Z_TNT:
                    return 0.2f;
                default:
                    return 0;
            }
        }
        if(this == Blocks.OBSIDIAN_FRAZION) {
            switch(type){
                case Z_TNT:
                    return 0.05f;
                default:
                    return 0;

            }
        }
        return 0;
    }
}
