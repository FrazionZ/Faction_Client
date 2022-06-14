package fz.frazionz.item;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemBottleXP extends Item {

	private int minLevel = 30;
	private int maxLevel = 1000;
	
	public ItemBottleXP() {
		this.setCreativeTab(CreativeTabs.MISC);
	}
	
	@Override
	public boolean hasEffect(ItemStack stack) {
		return true;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World itemStackIn, EntityPlayer player, EnumHand hand) {
		
		ItemStack itemstack = player.getHeldItem(hand);
		if(hand.equals(EnumHand.MAIN_HAND)) {
		
			if(itemstack.getTagCompound() != null) {
				
				int level = itemstack.getTagCompound().getInteger("bottleLevel");
				
		        player.playSound(SoundEvents.ENTITY_PLAYER_LEVELUP, 0.5F, 1.0F);
		        player.addExperienceLevel(level, true);
		        
		        if (!player.capabilities.isCreativeMode)
		        {
		            itemstack.shrink(1);
		        }
		        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
			}
			return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);
		}
		return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);
	}
	
    public int getMinLevel() {
		return minLevel;
	}
    
    public int getMaxLevel() {
		return maxLevel;
	}
    
    @Override
    public void addInformation(ItemStack stack, World playerIn, List<String> tooltip, ITooltipFlag advanced) {
    	if(stack.getTagCompound() != null) {
        	int level = stack.getTagCompound().getInteger("bottleLevel");
    		tooltip.add(" ");
    		tooltip.add("\u00A76\u2022 \u00A7eBottle de \u00A76" + level + " \u00A7elevels.");
    		tooltip.add(" ");
    	}
    }
}
