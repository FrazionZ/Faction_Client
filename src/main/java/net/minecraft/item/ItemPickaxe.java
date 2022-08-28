package net.minecraft.item;

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
import net.minecraft.world.World;

public class ItemPickaxe extends ItemTool
{
    private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(
    		Blocks.YELLITE_CHEST,
    		Blocks.BAUXITE_CHEST,
    		Blocks.FRAZION_CHEST,
    		Blocks.ONYX_CHEST,
    		Blocks.DIRT_CHEST,
    		Blocks.YELLITE_BLOCK,
    		Blocks.YELLITE_ORE,
    		Blocks.BAUXITE_BLOCK,
    		Blocks.BAUXITE_ORE,
    		Blocks.ONYX_BLOCK,
    		Blocks.ONYX_ORE,
    		Blocks.FRAZION_BLOCK,
    		Blocks.FRAZION_ORE,
    		Blocks.ACTIVATOR_RAIL,
    		Blocks.COAL_ORE,
    		Blocks.COBBLESTONE,
    		Blocks.DETECTOR_RAIL,
    		Blocks.DIAMOND_BLOCK,
    		Blocks.DIAMOND_ORE,
    		Blocks.DOUBLE_STONE_SLAB,
    		Blocks.GOLDEN_RAIL,
    		Blocks.GOLD_BLOCK,
    		Blocks.GOLD_ORE,
    		Blocks.ICE,
    		Blocks.IRON_BLOCK,
    		Blocks.IRON_ORE,
    		Blocks.LAPIS_BLOCK,
    		Blocks.LAPIS_ORE,
    		Blocks.LIT_REDSTONE_ORE,
    		Blocks.MOSSY_COBBLESTONE,
    		Blocks.NETHERRACK,
    		Blocks.PACKED_ICE,
    		Blocks.RAIL,
    		Blocks.REDSTONE_ORE,
    		Blocks.SANDSTONE,
    		Blocks.RED_SANDSTONE,
    		Blocks.STONE,
    		Blocks.STONE_SLAB,
    		Blocks.STONE_BUTTON,
    		Blocks.OBSIDIAN,
    		Blocks.OBSIDIAN_YELLITE,
    		Blocks.OBSIDIAN_BAUXITE,
    		Blocks.OBSIDIAN_ONYX,
    		Blocks.STONE_PRESSURE_PLATE,
    		Blocks.SANDSTONE2,
    		Blocks.STONE_ANDESITE,
    		Blocks.STONE_ANDESITE_SMOOTH,
    		Blocks.STONE_DIORITE,
    		Blocks.STONE_DIORITE_SMOOTH,
    		Blocks.STONE_GRANITE,
    		Blocks.STONE_GRANITE_SMOOTH,
    		Blocks.BAUXITE_LADDER,
    		Blocks.YELLITE_LADDER,
    		Blocks.ONYX_LADDER,
    		Blocks.FRAZION_LADDER,
    		Blocks.RANDOM_ORE,
    		Blocks.AMELIORATOR,
    		Blocks.STONE_BLACKSTONE,
    		Blocks.STONE_BLACKSTONE_SMOOTH
    		);

    private static final Set<Block> HARVEST_LEVEL_1 = Sets.newHashSet(Blocks.IRON_ORE, Blocks.IRON_BLOCK, Blocks.LAPIS_BLOCK, Blocks.LAPIS_ORE);
    private static final Set<Block> HARVEST_LEVEL_2 = Sets.newHashSet(Blocks.DIAMOND_BLOCK, Blocks.DIAMOND_ORE, Blocks.EMERALD_BLOCK, Blocks.EMERALD_ORE, Blocks.REDSTONE_BLOCK, Blocks.REDSTONE_ORE, Blocks.GOLD_BLOCK, Blocks.GOLD_ORE);
    private static final Set<Block> HARVEST_LEVEL_3 = Sets.newHashSet(Blocks.OBSIDIAN, Blocks.YELLITE_ORE, Blocks.YELLITE_BLOCK, Blocks.YELLITE_FURNACE, Blocks.LIT_YELLITE_FURNACE);
    private static final Set<Block> HARVEST_LEVEL_4 = Sets.newHashSet(Blocks.OBSIDIAN_YELLITE, Blocks.BAUXITE_ORE, Blocks.BAUXITE_BLOCK, Blocks.BAUXITE_FURNACE, Blocks.LIT_BAUXITE_FURNACE);
    private static final Set<Block> HARVEST_LEVEL_5 = Sets.newHashSet(Blocks.OBSIDIAN_BAUXITE, Blocks.RANDOM_ORE, Blocks.ONYX_ORE, Blocks.ONYX_BLOCK, Blocks.ONYX_FURNACE, Blocks.LIT_ONYX_FURNACE);
    private static final Set<Block> HARVEST_LEVEL_6 = Sets.newHashSet(Blocks.OBSIDIAN_ONYX, Blocks.TROPHY_FORGE, Blocks.AMELIORATOR, Blocks.FRAZION_ORE, Blocks.FRAZION_BLOCK, Blocks.FRAZION_FURNACE, Blocks.LIT_FRAZION_FURNACE);
    private static final Set<Block> HARVEST_LEVEL_7 = Sets.newHashSet(Blocks.OBSIDIAN_FRAZION);
    
    public ItemPickaxe(Item.ToolMaterial material)
    {
        super(1.25F, material, EFFECTIVE_ON);
    }

    /**
     * Check whether this Item can harvest the given Block
     */
    public boolean canHarvestBlock(IBlockState blockIn)
    {
        Block block = blockIn.getBlock();
        
        if(HARVEST_LEVEL_1.contains(block)) {
            return this.toolMaterial.getHarvestLevel() >= 1;
        }
        else if(HARVEST_LEVEL_2.contains(block)) {
            return this.toolMaterial.getHarvestLevel() >= 2;
        }
        else if(HARVEST_LEVEL_3.contains(block)) {
            return this.toolMaterial.getHarvestLevel() >= 3;
        }
        else if(HARVEST_LEVEL_4.contains(block)) {
            return this.toolMaterial.getHarvestLevel() >= 4;
        }
        else if(HARVEST_LEVEL_5.contains(block)) {
            return this.toolMaterial.getHarvestLevel() >= 5;
        }
        else if(HARVEST_LEVEL_6.contains(block)) {
            return this.toolMaterial.getHarvestLevel() >= 6;
        }
        else if(HARVEST_LEVEL_7.contains(block)) {
            return this.toolMaterial.getHarvestLevel() >= 7;
        }
        else {
            Material material = blockIn.getMaterial();

            if (material == Material.ROCK)
            {
                return true;
            }
            else if (material == Material.IRON)
            {
                return true;
            }
            else
            {
                return material == Material.ANVIL;
            }
        }
    }

    public void addInformation(ItemStack stack, @Nullable World playerIn, List<String> tooltip, ITooltipFlag advanced)
    {
    	if(this.toolMaterial.getHarvestLevel() >= 7) {
    		tooltip.add(" ");
    		tooltip.add("\u00A76\u00bb \u00A7eMine plus vite l'obsidienne");
    		tooltip.add(" ");
    	}
    	if(this == Items.SPAWNER_PICKAXE) {
    		tooltip.add(" ");
    		tooltip.add("\u00A76\u00bb \u00A7ePermet de r\u00E9cup\u00E9rer les Spawners");
    		tooltip.add(" ");
    	}
    	
    }
    
    public float getDestroySpeed(ItemStack stack, IBlockState state)
    {
        Material material = state.getMaterial();
        
        if(this.toolMaterial.getHarvestLevel() >= 7) {
            Block block = state.getBlock();
            
            if (block == Blocks.OBSIDIAN || block == Blocks.OBSIDIAN_YELLITE || block == Blocks.OBSIDIAN_BAUXITE)
            	return 80.0F;
            else if(block == Blocks.OBSIDIAN_ONYX || block == Blocks.OBSIDIAN_FRAZION)
            	return 50.0F;
            else
            	return material != Material.IRON && material != Material.ANVIL && material != Material.ROCK ? super.getDestroySpeed(stack, state) : this.efficiency;
        }
        return material != Material.IRON && material != Material.ANVIL && material != Material.ROCK ? super.getDestroySpeed(stack, state) : this.efficiency;
    
    }
}
