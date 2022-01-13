package fz.frazionz.item;

import java.util.List;
import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ItemHammer extends ItemTool
{
    private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(Blocks.YELLITE_BLOCK, Blocks.YELLITE_ORE, Blocks.BAUXITE_BLOCK, Blocks.BAUXITE_ORE, Blocks.ONYX_BLOCK, Blocks.ONYX_ORE, Blocks.FRAZION_BLOCK, Blocks.FRAZION_ORE, Blocks.ACTIVATOR_RAIL, Blocks.COAL_ORE, Blocks.COBBLESTONE, Blocks.DETECTOR_RAIL, Blocks.DIAMOND_BLOCK, Blocks.DIAMOND_ORE, Blocks.DOUBLE_STONE_SLAB, Blocks.GOLDEN_RAIL, Blocks.GOLD_BLOCK, Blocks.GOLD_ORE, Blocks.ICE, Blocks.IRON_BLOCK, Blocks.IRON_ORE, Blocks.LAPIS_BLOCK, Blocks.LAPIS_ORE, Blocks.LIT_REDSTONE_ORE, Blocks.MOSSY_COBBLESTONE, Blocks.NETHERRACK, Blocks.PACKED_ICE, Blocks.RAIL, Blocks.REDSTONE_ORE, Blocks.SANDSTONE, Blocks.RED_SANDSTONE, Blocks.STONE, Blocks.STONE_SLAB, Blocks.STONE_BUTTON, Blocks.STONE_PRESSURE_PLATE);

    private static final Set<Block> NOT_EFFECTIVE = Sets.newHashSet(
    		Blocks.OBSIDIAN,
    		Blocks.OBSIDIAN_BAUXITE,
    		Blocks.OBSIDIAN_FRAZION,
    		Blocks.OBSIDIAN_ONYX,
    		Blocks.OBSIDIAN_YELLITE,
    		Blocks.BEDROCK,
    		Blocks.WATER,
    		Blocks.LAVA,
    		Blocks.FLOWING_LAVA, 
    		Blocks.FLOWING_WATER,
    		Blocks.CHEST, 
    		Blocks.YELLITE_CHEST,
    		Blocks.BAUXITE_CHEST,
    		Blocks.ONYX_CHEST,
    		Blocks.FRAZION_CHEST,
    		Blocks.FURNACE,
    		Blocks.LIT_FURNACE, 
    		Blocks.BAUXITE_FURNACE,
    		Blocks.LIT_BAUXITE_FURNACE,
    		Blocks.YELLITE_FURNACE,
    		Blocks.LIT_YELLITE_FURNACE,
    		Blocks.FRAZION_FURNACE, 
    		Blocks.LIT_FRAZION_FURNACE,
    		Blocks.ONYX_FURNACE,
    		Blocks.LIT_ONYX_FURNACE
    		);
    
    public ItemHammer(Item.ToolMaterial material)
    {
        super(1.0F, material, EFFECTIVE_ON);
    }

    /**
     * Check whether this Item can harvest the given Block
     */
    public boolean canHarvestBlock(IBlockState blockIn)
    {
        Block block = blockIn.getBlock();

        if (block == Blocks.OBSIDIAN || block == Blocks.OBSIDIAN_YELLITE || block == Blocks.OBSIDIAN_BAUXITE || block == Blocks.OBSIDIAN_FRAZION || block == Blocks.OBSIDIAN_ONYX)
        {
            return this.toolMaterial.getHarvestLevel() == 3;
        }

        if (block == Blocks.YELLITE_ORE || block == Blocks.YELLITE_BLOCK)
        {
            return this.toolMaterial.getHarvestLevel() >= 2;
        }

        if (block == Blocks.BAUXITE_ORE || block == Blocks.BAUXITE_BLOCK)
        {
            return this.toolMaterial.getHarvestLevel() >= 2;
        }

        if (block == Blocks.ONYX_ORE || block == Blocks.ONYX_BLOCK)
        {
            return this.toolMaterial.getHarvestLevel() >= 2;
        }

        if (block == Blocks.FRAZION_ORE || block == Blocks.FRAZION_BLOCK)
        {
            return this.toolMaterial.getHarvestLevel() == 3;
        }
        else if (block != Blocks.DIAMOND_BLOCK && block != Blocks.DIAMOND_ORE)
        {
            if (block != Blocks.EMERALD_ORE && block != Blocks.EMERALD_BLOCK)
            {
                if (block != Blocks.GOLD_BLOCK && block != Blocks.GOLD_ORE)
                {
                    if (block != Blocks.IRON_BLOCK && block != Blocks.IRON_ORE)
                    {
                        if (block != Blocks.LAPIS_BLOCK && block != Blocks.LAPIS_ORE)
                        {
                            if (block != Blocks.REDSTONE_ORE && block != Blocks.LIT_REDSTONE_ORE)
                            {
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
                            else
                            {
                                return this.toolMaterial.getHarvestLevel() >= 2;
                            }
                        }
                        else
                        {
                            return this.toolMaterial.getHarvestLevel() >= 1;
                        }
                    }
                    else
                    {
                        return this.toolMaterial.getHarvestLevel() >= 1;
                    }
                }
                else
                {
                    return this.toolMaterial.getHarvestLevel() >= 2;
                }
            }
            else
            {
                return this.toolMaterial.getHarvestLevel() >= 2;
            }
        }
        else
        {
            return this.toolMaterial.getHarvestLevel() >= 2;
        }
    }

    public float getStrVsBlock(ItemStack stack, IBlockState state)
    {
        Material material = state.getMaterial();
        return material != Material.IRON && material != Material.ANVIL && material != Material.ROCK ? super.getStrVsBlock(stack, state) : this.efficiencyOnProperMaterial;
    }

    @Override
    public boolean onBlockDestroyed(ItemStack breaker, World w, IBlockState state, BlockPos pos, EntityLivingBase e)
    {
    	
    	if(state.getBlock().getBlockHardness(state , w, pos) <= 0.2F) {
    		return false;
    	}
    	
    	if (e instanceof EntityPlayer && !w.isRemote)
        {
            EntityPlayer p = (EntityPlayer) e;
            breaker.damageItem(1, e);
            int x = pos.getX();
            int y = pos.getY();
            int z = pos.getZ();

            // Y
            // UP - DOWN
            for (int x1 = -1; x1 < 2; x1++)
            {
                for (int y1 = -1; y1 < 2; y1++)
                {
                    for (int z1 = -1; z1 < 2; z1++)
                    {
                        this.destroyAndDropBlock(w, p, breaker, x + x1, y + y1, z + z1 , state);
                    }
                }
            }

            return true;
        }

        return super.onBlockDestroyed(breaker, w, state, pos, e);
    }
    private void destroyAndDropBlock(World w, EntityPlayer p, ItemStack breaker, int x, int y, int z, IBlockState state)
    {
        BlockPos pos = new BlockPos(x, y, z);

        if (w.getBlockState(pos).getBlock().getBlockHardness(state , w, pos) >= 0)
        {
            if (!NOT_EFFECTIVE.contains(w.getBlockState(pos).getBlock()))
            {
                w.getBlockState(pos).getBlock().harvestBlock(w, p, pos, w.getBlockState(pos), w.getTileEntity(pos), breaker);
                w.setBlockToAir(pos);
            }
        }
    }
    
    
    public void addInformation(ItemStack stack, @Nullable World playerIn, List<String> tooltip, ITooltipFlag advanced)
    {
    	
    		tooltip.add(" ");
    		tooltip.add("\u00A76\u00bb \u00A7ePermet de miner en 3x3x3");
    		tooltip.add(" ");
    	
    }
    
}
