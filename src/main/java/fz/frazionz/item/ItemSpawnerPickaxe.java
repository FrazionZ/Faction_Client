package fz.frazionz.item;

import java.util.List;
import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.world.World;

public class ItemSpawnerPickaxe extends ItemTool
{
    private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(
    		Blocks.MOB_SPAWNER
    		);

    public ItemSpawnerPickaxe()
    {
        super(1.25F, Item.ToolMaterial.SPAWNER_PICKAXE, EFFECTIVE_ON);
    }

    /**
     * Check whether this Item can harvest the given Block
     */
    public boolean canHarvestBlock(IBlockState blockIn)
    {
    	return this.toolMaterial.getHarvestLevel() >= 3;
    }

    public float getStrVsBlock(ItemStack stack, IBlockState state)
    {
        Material material = state.getMaterial();
        return material != Material.IRON && material != Material.ANVIL && material != Material.ROCK ? super.getStrVsBlock(stack, state) : this.efficiencyOnProperMaterial;
    }
    
    public void addInformation(ItemStack stack, @Nullable World playerIn, List<String> tooltip, ITooltipFlag advanced)
    {
    	tooltip.add(" ");
    	tooltip.add("\u00A76\u00bb \u00A7ePermet de r\u00E9cup\u00E9rer 10 Spawners");
    	tooltip.add("\u00A76\u00bb \u00A7eUnbreaking inutile sur la pioche.");
    	tooltip.add(" ");	
    }
    
}
