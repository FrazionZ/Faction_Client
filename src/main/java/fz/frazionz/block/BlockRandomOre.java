package fz.frazionz.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class BlockRandomOre extends Block
{
	private Random rand = new Random();
	
    public BlockRandomOre()
    {
        this(Material.ROCK.getMaterialMapColor());
    }

    public BlockRandomOre(MapColor color)
    {
        super(Material.ROCK, color);
        this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
    }

    /**
     * Get the Item that this Block should drop when harvested.
     */
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
    	float i1 = rand.nextFloat();
    	
    	if(i1 < 0.00010F) { // 0.01%
    		return Items.COSMIC_NUGGET;
    	}
    	else if (i1 < 0.0051F) // 0.5%
        {
            return Items.COSMIC_POWDER;
        }
        else if (i1 < 0.0151F) // 1%
        {
            return Items.FRAZION_POWDER;
        }
        else if (i1 < 0.1151F) // 10%
        {
            return Items.ONYX; 
        }
        else if (i1 < 0.4651F) // 35%
        {
            return Items.BAUXITE;
        }
        else if (i1 < 1.0F) // 53,49%
        {
            return Items.YELLITE;
        }
        else
        {
            return Items.YELLITE;
        }
    }

    /**
     * Get the quantity dropped based on the given fortune level
     */
    public int quantityDroppedWithBonus(int fortune, Random random)
    {
        if (fortune > 0 && Item.getItemFromBlock(this) != this.getItemDropped((IBlockState)this.getBlockState().getValidStates().iterator().next(), random, fortune))
        {
            int i = random.nextInt(fortune + 2) - 1;

            if (i < 0)
            {
                i = 0;
            }

            return this.quantityDropped(random) * (i + 1);
        }
        else
        {
            return this.quantityDropped(random);
        }
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
                      
            i = MathHelper.getInt(worldIn.rand, 3, 7);

            this.dropXpOnBlockBreak(worldIn, pos, i);
        }
    }

    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        return new ItemStack(this);
    }
}
