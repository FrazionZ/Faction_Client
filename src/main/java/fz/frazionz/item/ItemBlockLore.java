package fz.frazionz.item;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBlockLore extends ItemBlock
{
	protected final String[] lore;
	
    public ItemBlockLore(Block block, String... lore)
    {
    	super(block);
    	this.lore = lore;
    }
    
    public void addInformation(ItemStack stack, @Nullable World playerIn, List<String> tooltip, ITooltipFlag advanced)
    {
    	for (String string : this.lore) {
			tooltip.add(string);
		}
    }

}
