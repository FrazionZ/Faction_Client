package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class BlockFrazionOre extends Block
{
    public BlockFrazionOre()
    {
        this(Material.ROCK.getMaterialMapColor());
    }

    public BlockFrazionOre(MapColor color)
    {
        super(Material.ROCK, color);
        this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
    }

    /**
     * Get the Item that this Block should drop when harvested.
     */
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Items.FRAZION_POWDER;
    }


    /**
     * Spawns this Block's drops into the World as EntityItems.
     */
    public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune)
    {
        super.dropBlockAsItemWithChance(worldIn, pos, state, chance, fortune);

        if (this.getItemDropped(state, worldIn.rand, fortune) != Item.getItemFromBlock(this))
        {
            int i = 0;

            if (this == Blocks.COAL_ORE)
            {
                i = MathHelper.getInt(worldIn.rand, 0, 2);
            }
            else if (this == Blocks.DIAMOND_ORE)
            {
                i = MathHelper.getInt(worldIn.rand, 3, 7);
            }
            else if (this == Blocks.YELLITE_ORE)
            {
                i = MathHelper.getInt(worldIn.rand, 3, 7);
            }
            else if (this == Blocks.BAUXITE_ORE)
            {
                i = MathHelper.getInt(worldIn.rand, 3, 7);
            }
            else if (this == Blocks.ONYX_ORE)
            {
                i = MathHelper.getInt(worldIn.rand, 3, 7);
            }
            else if (this == Blocks.FRAZION_ORE)
            {
                i = MathHelper.getInt(worldIn.rand, 3, 7);
            }
            else if (this == Blocks.EMERALD_ORE)
            {
                i = MathHelper.getInt(worldIn.rand, 3, 7);
            }
            else if (this == Blocks.LAPIS_ORE)
            {
                i = MathHelper.getInt(worldIn.rand, 2, 5);
            }
            else if (this == Blocks.QUARTZ_ORE)
            {
                i = MathHelper.getInt(worldIn.rand, 2, 5);
            }

            this.dropXpOnBlockBreak(worldIn, pos, i);
        }
    }
}
