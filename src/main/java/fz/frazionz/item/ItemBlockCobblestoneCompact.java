package fz.frazionz.item;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBlockCobblestoneCompact extends ItemBlock
{
	protected final String[] lore;
	protected final int type;
	
    public ItemBlockCobblestoneCompact(Block block, int type, String... lore)
    {
    	super(block);
    	this.type = type;
    	this.lore = lore;
    }
    
    public void addInformation(ItemStack stack, @Nullable World playerIn, List<String> tooltip, ITooltipFlag advanced)
    {
    	tooltip.add(" ");
    	tooltip.add("\u00A76\u00bb \u00A7eSoit : \u00A76" + (int)(stack.getStackSize() * Math.pow(9, this.type)) + "\u00A7e cobblestones");
    	for (String string : this.lore) {
			tooltip.add(string);
		}
    }

}
