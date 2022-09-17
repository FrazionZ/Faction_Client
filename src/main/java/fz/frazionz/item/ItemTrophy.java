package fz.frazionz.item;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.Map.Entry;

import com.google.common.collect.Multimap;

import fz.frazionz.utils.MathUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

public abstract class ItemTrophy extends Item {

	public ItemTrophy() {
		this.setCreativeTab(CreativeTabs.MATERIALS);
		this.setMaxStackSize(1);
	}
    
    @Override
    public void addInformation(ItemStack stack, World playerIn, List<String> tooltip, ITooltipFlag advanced) {
    	
    	Multimap<String, AttributeModifier> multimap = stack.getAttributeModifiers(EntityEquipmentSlot.TROPHY_1);
    		
    	if(!multimap.isEmpty()) {
        	tooltip.add(" ");
        		
            for (Entry<String, AttributeModifier> entry : multimap.entries())
            {
                AttributeModifier attributemodifier = entry.getValue();
                double d0 = attributemodifier.getAmount();
                    
                double d1;

                if (attributemodifier.getOperation() != 1 && attributemodifier.getOperation() != 2)
                {
                    d1 = d0;
                }
                else
                {
                    d1 = d0 * 100.0D;
                }
                    
            	if (d0 > 0.0D)
                {
            		tooltip.add("\u00A76\u2022 \u00A7e" + I18n.translateToLocal("attribute.name." + (String)entry.getKey()) + " \u00A7a" + I18n.translateToLocalFormatted("attribute.modifier.plus." + attributemodifier.getOperation(), ItemStack.DECIMALFORMAT.format(d1), ""));
                }
                else if (d0 < 0.0D)
                {
                    d1 = d1 * -1.0D;
                    tooltip.add("\u00A76\u2022 \u00A7e " + I18n.translateToLocal("attribute.name." + (String)entry.getKey()) + " \u00A7c" + I18n.translateToLocalFormatted("attribute.modifier.take." + attributemodifier.getOperation(), ItemStack.DECIMALFORMAT.format(d1), ""));
                }
            }
                
        	tooltip.add(" ");
        		
    	}
    }
    
    public abstract AttributeModifier getRandomAttributeModifier();
    
    public abstract IAttribute getAttributes();
	
}
