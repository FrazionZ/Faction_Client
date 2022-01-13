package net.minecraft.item;

import com.google.common.collect.Sets;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;

public class ItemAxe extends ItemTool
{
    private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(
    		Blocks.PLANKS,
    		Blocks.BOOKSHELF,
    		Blocks.LOG,
    		Blocks.LOG2,
    		Blocks.CHEST,
    		Blocks.PUMPKIN,
    		Blocks.LIT_PUMPKIN,
    		Blocks.MELON_BLOCK,
    		Blocks.LADDER,
    		Blocks.WOODEN_BUTTON,
    		Blocks.WOODEN_PRESSURE_PLATE,
    		Blocks.CRIMSON_LOG
    		);

    protected ItemAxe(Item.ToolMaterial material)
    {
    	super(2.0F, material, EFFECTIVE_ON);
    }

    public float getStrVsBlock(ItemStack stack, IBlockState state)
    {
        Material material = state.getMaterial();
        return material != Material.WOOD && material != Material.PLANTS && material != Material.VINE ? super.getStrVsBlock(stack, state) : this.efficiencyOnProperMaterial;
    }
}
