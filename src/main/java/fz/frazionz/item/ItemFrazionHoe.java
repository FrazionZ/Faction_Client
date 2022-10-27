package fz.frazionz.item;

import java.util.List;
import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemFrazionHoe extends ItemTool
{
    private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(
    		Blocks.HAY_BLOCK,
    		Blocks.NETHER_WART_BLOCK2,
    		Blocks.NETHER_WART_BLOCK,
    		Blocks.SPONGE,
            Blocks.MELON_BLOCK,
            Blocks.PUMPKIN
    		);
	
    protected Item.ToolMaterial theToolMaterial;

    public ItemFrazionHoe(Item.ToolMaterial material)
    {
        super(0.0F, material, EFFECTIVE_ON);
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

    /**
     * Current implementations of this method in child classes do not use the entry argument beside ev. They just raise
     * the damage on the stack.
     */
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
        stack.damageItem(1, attacker);
        return true;
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

    /**
     * Returns True is the item is renderer in full 3D when hold.
     */
    public boolean isFull3D()
    {
        return true;
    }

    /**
     * Returns the name of the material this tool is made from as it is declared in EnumToolMaterial (meaning diamond
     * would return "EMERALD")
     */
    
    
    @Override
    public boolean onBlockDestroyed(ItemStack breaker, World w, IBlockState state, BlockPos pos, EntityLivingBase e)
    {
    	
    	if(state.getBlock() == Blocks.CARROTS || state.getBlock() == Blocks.STRAWBERRIES || state.getBlock() == Blocks.POTATOES || state.getBlock() == Blocks.WHEAT || state.getBlock() == Blocks.NETHER_WART || state.getBlock() == Blocks.BEETROOTS)
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
                	breaker.damageItem(1, e);
                	removeItems(p, new ItemStack(Items.CARROT), 1);
                	
                	return true;
                }
                
                else if(state.getBlock() == Blocks.POTATOES && p.inventory.hasItemStack(new ItemStack(Items.POTATO)))
                {
                	w.setBlockState(pos, Blocks.POTATOES.getDefaultState());
                	breaker.damageItem(1, e);
                	removeItems(p, new ItemStack(Items.POTATO), 1);
                	return true;
                }
                
                else if(state.getBlock() == Blocks.NETHER_WART && p.inventory.hasItemStack(new ItemStack(Items.NETHER_WART)))
                {
                	w.setBlockState(pos, Blocks.NETHER_WART.getDefaultState());
                	breaker.damageItem(1, e);
                	removeItems(p, new ItemStack(Items.NETHER_WART), 1);
                	
                	return true;
                }
                
                else if(state.getBlock() == Blocks.WHEAT && p.inventory.hasItemStack(new ItemStack(Items.WHEAT_SEEDS)))
                {
                	w.setBlockState(pos, Blocks.WHEAT.getDefaultState());
                	breaker.damageItem(1, e);
                	removeItems(p, new ItemStack(Items.WHEAT_SEEDS), 1);
                	
                	return true;
                }
                
                else if(state.getBlock() == Blocks.BEETROOTS && p.inventory.hasItemStack(new ItemStack(Items.BEETROOT_SEEDS)))
                {
                	w.setBlockState(pos, Blocks.BEETROOTS.getDefaultState());
                	breaker.damageItem(1, e);
                	removeItems(p, new ItemStack(Items.BEETROOT_SEEDS), 1);
                	
                	return true;
                }

                else if(state.getBlock() == Blocks.STRAWBERRIES && p.inventory.hasItemStack(new ItemStack(Items.STRAWBERRY)))
                {
                    w.setBlockState(pos, Blocks.STRAWBERRIES.getDefaultState());
                    breaker.damageItem(1, e);
                    removeItems(p, new ItemStack(Items.STRAWBERRY), 1);

                    return true;
                }
                
                else {
                	return super.onBlockDestroyed(breaker, w, state, pos, e);
                }
            }
    	}
   
        return super.onBlockDestroyed(breaker, w, state, pos, e);
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
            
            else {
            	
            	continue;
            	
            }
        }
    }
    
    public String getMaterialName()
    {
        return this.theToolMaterial.toString();
    }

    public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot)
    {
        Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);

        if (equipmentSlot == EntityEquipmentSlot.MAINHAND)
        {
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", 0.0D, 0));
        }

        return multimap;
    }
    
    
    public void addInformation(ItemStack stack, @Nullable World playerIn, List<String> tooltip, ITooltipFlag advanced)
    {
        int fortuneLevel = EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, stack);
        fortuneLevel++;
        tooltip.add(" ");
        tooltip.add("\u00A76\u00bb \u00A7ePermet de replanter les plantations cassées");
        tooltip.add("\u00A76\u00bb \u00A7eUtilise les graines dans l'inventaire");
        tooltip.add(" ");
        tooltip.add("\u00A76\u00bb \u00A7eUn niveau de Fortune supplémentaire");
        tooltip.add("\u00A76\u00bb \u00A7eFortune: \u00A76" + fortuneLevel);
        tooltip.add(" ");
    }
}
