package fz.frazionz.item;

import java.util.List;
import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemMultiTool extends ItemTool
{
    private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(
    		Blocks.PLANKS,
    		Blocks.OBSIDIAN_YELLITE, Blocks.OBSIDIAN_BAUXITE, Blocks.OBSIDIAN_ONYX, Blocks.OBSIDIAN_FRAZION,
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
    		Blocks.CRIMSON_LOG,
    		Blocks.YELLITE_CHEST, Blocks.BAUXITE_CHEST, Blocks.ONYX_CHEST, Blocks.FRAZION_CHEST, Blocks.DIRT_CHEST,
    		Blocks.YELLITE_BLOCK, Blocks.YELLITE_ORE,
    		Blocks.BAUXITE_BLOCK, Blocks.BAUXITE_ORE,
    		Blocks.ONYX_BLOCK, Blocks.ONYX_ORE,
    		Blocks.FRAZION_BLOCK, Blocks.FRAZION_ORE,
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
    		Blocks.CLAY,
    		Blocks.DIRT,
    		Blocks.FARMLAND,
    		Blocks.GRASS,
    		Blocks.GRAVEL,
    		Blocks.MYCELIUM,
    		Blocks.SAND,
    		Blocks.SNOW,
    		Blocks.SNOW_LAYER,
    		Blocks.SOUL_SAND,
    		Blocks.GRASS_PATH,
    		Blocks.field_192444_dS,
    		Blocks.AMELIORATOR,
    		Blocks.WOODEN_SLAB,
    		Blocks.DOUBLE_WOODEN_SLAB,
    		Blocks.ACACIA_STAIRS,
    		Blocks.BIRCH_STAIRS,
    		Blocks.DARK_OAK_STAIRS,
    		Blocks.JUNGLE_STAIRS,
    		Blocks.OAK_STAIRS,
    		Blocks.SPRUCE_STAIRS,
    		Blocks.OAK_FENCE,
    		Blocks.OAK_FENCE_GATE,
    		Blocks.ACACIA_FENCE,
    		Blocks.ACACIA_FENCE_GATE,
    		Blocks.BIRCH_FENCE,
    		Blocks.BIRCH_FENCE_GATE,
    		Blocks.DARK_OAK_FENCE,
    		Blocks.DARK_OAK_FENCE_GATE,
    		Blocks.JUNGLE_FENCE,
    		Blocks.JUNGLE_FENCE_GATE,
    		Blocks.NETHER_BRICK_FENCE,
    		Blocks.SPRUCE_FENCE,
    		Blocks.SPRUCE_FENCE_GATE,
    		Blocks.CHEST,
    		Blocks.TRAPDOOR,
    		Blocks.TRAPPED_CHEST,
    		Blocks.WEB,
    		Blocks.ACACIA_DOOR,
    		Blocks.BIRCH_DOOR,
    		Blocks.DARK_OAK_DOOR,
    		Blocks.JUNGLE_DOOR,
    		Blocks.OAK_DOOR,
    		Blocks.SPRUCE_DOOR,
    		Blocks.STANDING_SIGN,
    		Blocks.WALL_SIGN,
    		Blocks.RENFORCED_SAND,
    		Blocks.HOPPER,
    		Blocks.Z_HOPPER,
    		Blocks.CRAFTING_TABLE,
    		Blocks.TROPHY_FORGE,
    		Blocks.HAY_BLOCK,
    		Blocks.NETHER_WART_BLOCK2,
    		Blocks.NETHER_WART_BLOCK,
    		Blocks.SPONGE,
    		Blocks.LADDER,
    		Blocks.BAUXITE_LADDER,
    		Blocks.FRAZION_LADDER,
    		Blocks.ONYX_LADDER,
    		Blocks.YELLITE_LADDER
    		);

    private static final Set<Block> HARVEST_LEVEL_1 = Sets.newHashSet(Blocks.IRON_ORE, Blocks.IRON_BLOCK, Blocks.LAPIS_BLOCK, Blocks.LAPIS_ORE);
    private static final Set<Block> HARVEST_LEVEL_2 = Sets.newHashSet(Blocks.DIAMOND_BLOCK, Blocks.DIAMOND_ORE, Blocks.EMERALD_BLOCK, Blocks.EMERALD_ORE, Blocks.REDSTONE_BLOCK, Blocks.REDSTONE_ORE, Blocks.GOLD_BLOCK, Blocks.GOLD_ORE);
    private static final Set<Block> HARVEST_LEVEL_3 = Sets.newHashSet(Blocks.OBSIDIAN, Blocks.YELLITE_ORE, Blocks.YELLITE_BLOCK, Blocks.YELLITE_FURNACE, Blocks.LIT_YELLITE_FURNACE);
    private static final Set<Block> HARVEST_LEVEL_4 = Sets.newHashSet(Blocks.OBSIDIAN_YELLITE, Blocks.BAUXITE_ORE, Blocks.BAUXITE_BLOCK, Blocks.BAUXITE_FURNACE, Blocks.LIT_BAUXITE_FURNACE);
    private static final Set<Block> HARVEST_LEVEL_5 = Sets.newHashSet(Blocks.OBSIDIAN_BAUXITE, Blocks.RANDOM_ORE, Blocks.ONYX_ORE, Blocks.ONYX_BLOCK, Blocks.ONYX_FURNACE, Blocks.LIT_ONYX_FURNACE);
    private static final Set<Block> HARVEST_LEVEL_6 = Sets.newHashSet(Blocks.OBSIDIAN_ONYX, Blocks.TROPHY_FORGE, Blocks.AMELIORATOR, Blocks.FRAZION_ORE, Blocks.FRAZION_BLOCK, Blocks.FRAZION_FURNACE, Blocks.LIT_FRAZION_FURNACE);
    private static final Set<Block> HARVEST_LEVEL_7 = Sets.newHashSet(Blocks.OBSIDIAN_FRAZION);
    
    public ItemMultiTool(Item.ToolMaterial material)
    {
        super(1.0F, material, EFFECTIVE_ON);
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
    
    @SuppressWarnings("incomplete-switch")
    
    /**
     * Called when a Block is right-clicked with this Item
     */
    public EnumActionResult onItemUse(EntityPlayer stack, World playerIn, BlockPos worldIn, EnumHand pos, EnumFacing hand, float facing, float hitX, float hitY)
    {
        ItemStack itemstack = stack.getHeldItem(pos);

        if (!stack.canPlayerEdit(worldIn.offset(hand), hand, itemstack))
        {
            return EnumActionResult.FAIL;
        }
        else
        {
            IBlockState iblockstate = playerIn.getBlockState(worldIn);
            Block block = iblockstate.getBlock();

            if (hand != EnumFacing.DOWN && playerIn.getBlockState(worldIn.up()).getMaterial() == Material.AIR)
            {
                if (block == Blocks.GRASS || block == Blocks.GRASS_PATH)
                {
                    this.setBlock(itemstack, stack, playerIn, worldIn, Blocks.FARMLAND.getDefaultState());
                    return EnumActionResult.SUCCESS;
                }

                if (block == Blocks.DIRT)
                {
                    switch ((BlockDirt.DirtType)iblockstate.getValue(BlockDirt.VARIANT))
                    {
                        case DIRT:
                            this.setBlock(itemstack, stack, playerIn, worldIn, Blocks.FARMLAND.getDefaultState());
                            return EnumActionResult.SUCCESS;

                        case COARSE_DIRT:
                            this.setBlock(itemstack, stack, playerIn, worldIn, Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.DIRT));
                            return EnumActionResult.SUCCESS;
                    }
                }
            }

            return EnumActionResult.PASS;
        }
    }
    
    @Override
    public boolean onBlockDestroyed(ItemStack breaker, World w, IBlockState state, BlockPos pos, EntityLivingBase e)
    {
    	if(this.toolMaterial.getHarvestLevel() >= 7) {
        	if(state.getBlock() == Blocks.CARROTS || state.getBlock() == Blocks.POTATOES || state.getBlock() == Blocks.WHEAT || state.getBlock() == Blocks.NETHER_WART || state.getBlock() == Blocks.BEETROOTS) 
        	{
        		
            	if (e instanceof EntityPlayer && !w.isRemote)
                {
                    EntityPlayer p = (EntityPlayer) e;
                    int x = pos.getX();
                    int y = pos.getY();
                    int z = pos.getZ();

                    if(state.getBlock() == Blocks.CARROTS && p.inventory.hasItemStack(new ItemStack(Items.CARROT)))
                    {
                    	w.setBlockState(pos, Blocks.CARROTS.getDefaultState());
                    	removeItems(p, new ItemStack(Items.CARROT), 1);
                    	
                    	return true;
                    }
                    
                    else if(state.getBlock() == Blocks.POTATOES && p.inventory.hasItemStack(new ItemStack(Items.POTATO)))
                    {
                    	w.setBlockState(pos, Blocks.POTATOES.getDefaultState());
                    	removeItems(p, new ItemStack(Items.POTATO), 1);
                    	return true;
                    }
                    
                    else if(state.getBlock() == Blocks.NETHER_WART && p.inventory.hasItemStack(new ItemStack(Items.NETHER_WART)))
                    {
                    	w.setBlockState(pos, Blocks.NETHER_WART.getDefaultState());
                    	removeItems(p, new ItemStack(Items.NETHER_WART), 1);
                    	
                    	return true;
                    }
                    
                    else if(state.getBlock() == Blocks.WHEAT && p.inventory.hasItemStack(new ItemStack(Items.WHEAT_SEEDS)))
                    {
                    	w.setBlockState(pos, Blocks.WHEAT.getDefaultState());
                    	removeItems(p, new ItemStack(Items.WHEAT_SEEDS), 1);
                    	
                    	return true;
                    }
                    
                    else if(state.getBlock() == Blocks.BEETROOTS && p.inventory.hasItemStack(new ItemStack(Items.BEETROOT_SEEDS)))
                    {
                    	w.setBlockState(pos, Blocks.BEETROOTS.getDefaultState());
                    	removeItems(p, new ItemStack(Items.BEETROOT_SEEDS), 1);
                    	
                    	return true;
                    }
                    
                    else {
                    	return super.onBlockDestroyed(breaker, w, state, pos, e);
                    }
                }
        	}
        	return super.onBlockDestroyed(breaker, w, state, pos, e);
    	}
    	return super.onBlockDestroyed(breaker, w, state, pos, e);
    }
    
    protected void setBlock(ItemStack stack, EntityPlayer player, World worldIn, BlockPos pos, IBlockState state)
    {
        worldIn.playSound(player, pos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);

        if (!worldIn.isRemote)
        {
            worldIn.setBlockState(pos, state, 11);
            stack.damageItem(1, player);
        }
    }
    
    public void removeItems(EntityPlayer player , ItemStack stack, int amount) {
        int size = player.inventory.getSizeInventory();
        
        for (int slot = 0; slot < size; slot++) 
        {
            ItemStack is = player.inventory.getStackInSlot(slot);        
            
            if (is.getItem() == stack.getItem()) 
            {
                int newAmount = is.getStackSize() - amount;
                
                if (newAmount > 0)
                {
                    is.setStackSize(newAmount);
                    break;
                }
                else 
                {
                    player.inventory.removeStackFromSlot(slot);
                    amount = -newAmount;
                    if (amount == 0) 
                    {
                    	break;
                    }
                }
            }
            else
            	continue;
        }
    }

    public float getStrVsBlock(ItemStack stack, IBlockState state)
    {
        Material material = state.getMaterial();
        
        if(this.toolMaterial.getHarvestLevel() >= 7) {
            Block block = state.getBlock();
            
            if (block == Blocks.OBSIDIAN || block == Blocks.OBSIDIAN_YELLITE || block == Blocks.OBSIDIAN_BAUXITE || block == Blocks.OBSIDIAN_ONYX || block == Blocks.OBSIDIAN_FRAZION)
            	return 90.0F;
            else
            	return material != Material.IRON && material != Material.ANVIL && material != Material.ROCK ? super.getStrVsBlock(stack, state) : this.efficiencyOnProperMaterial;
        }
        return material != Material.IRON && material != Material.ANVIL && material != Material.ROCK ? super.getStrVsBlock(stack, state) : this.efficiencyOnProperMaterial;
    }
    
    public void addInformation(ItemStack stack, @Nullable World playerIn, List<String> tooltip, ITooltipFlag advanced)
    {
    	if(this.toolMaterial.getHarvestLevel() >= 7) {
    		tooltip.add(" ");
    		tooltip.add("\u00A76\u00bb \u00A7eMine plus vite l'obsidienne");
    		tooltip.add("\u00A76\u00bb \u00A7ePermet de replanter les plantations cassées");
    		tooltip.add(" ");
    	}
    }
}
