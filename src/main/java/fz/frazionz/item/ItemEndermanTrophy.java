package fz.frazionz.item;

import java.util.List;
import java.util.Map.Entry;
import java.util.Random;
import java.util.UUID;

import com.google.common.collect.Multimap;

import fz.frazionz.utils.MathUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

public class ItemEndermanTrophy extends ItemTrophy {
	
	public ItemEndermanTrophy() {
		super();
	}
    
    public AttributeModifier getRandomAttributeModifier() {
    	Random rand = new Random();
    	float randFloat = rand.nextFloat();
    	if(randFloat <= 0.5)
    	{
    		return new AttributeModifier(UUID.fromString("81ef1173-6068-4c9a-88db-5df409734476"), "Max Health", 0.01 + MathUtils.roundAvoid(rand.nextDouble() * 0.09, 2), 2);
    	}
    	else if(randFloat <= 0.75) 
    	{
    		return new AttributeModifier(UUID.fromString("81ef1173-6068-4c9a-88db-5df409734476"), "Max Health", 0.11 + MathUtils.roundAvoid(rand.nextDouble() * 0.09, 2), 2);
    	}
    	else if(randFloat <= 0.90) 
    	{
    		return new AttributeModifier(UUID.fromString("81ef1173-6068-4c9a-88db-5df409734476"), "Max Health", 0.21 + MathUtils.roundAvoid(rand.nextDouble() * 0.09, 2), 2);
    	}
    	else
    	{
    		return new AttributeModifier(UUID.fromString("81ef1173-6068-4c9a-88db-5df409734476"), "Max Health", 0.31 + MathUtils.roundAvoid(rand.nextDouble() * 0.04, 2), 2);
    	}
    }
    
    public IAttribute getMonsterAttributes()
    {
    	return SharedMonsterAttributes.MAX_HEALTH;
    }
	
}
