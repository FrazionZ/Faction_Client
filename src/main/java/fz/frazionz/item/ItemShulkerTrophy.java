package fz.frazionz.item;

import java.util.List;
import java.util.Map.Entry;
import java.util.Random;
import java.util.UUID;

import com.google.common.collect.Multimap;

import fz.frazionz.entity.player.PlayerAttribute;
import fz.frazionz.utils.MathUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

public class ItemShulkerTrophy extends ItemTrophy {
	
	public ItemShulkerTrophy() {
		super();
	}
    
    public AttributeModifier getRandomAttributeModifier() {
    	Random rand = new Random();
    	float randFloat = rand.nextFloat();
    	return new AttributeModifier(UUID.fromString("d69b4274-443c-40a6-8332-a375ebf0feed"), "Health", 2.0, 1);
    	/*if(randFloat <= 0.5)
    	{
    		return new AttributeModifier(UUID.fromString("d69b4274-443c-40a6-8332-a375ebf0feed"), "Health", 0.01 + MathUtils.roundAvoid(rand.nextFloat() * 0.04, 2), 2);
    	}
    	else if(randFloat <= 0.80) 
    	{
    		return new AttributeModifier(UUID.fromString("d69b4274-443c-40a6-8332-a375ebf0feed"), "Health", 0.05 + MathUtils.roundAvoid(rand.nextFloat() * 0.04, 2), 2);
    	}
    	else if(randFloat <= 0.95) 
    	{
    		return new AttributeModifier(UUID.fromString("d69b4274-443c-40a6-8332-a375ebf0feed"), "Health", 0.09 + MathUtils.roundAvoid(rand.nextFloat() * 0.04, 2), 2);
    	}
    	else
    	{
    		return new AttributeModifier(UUID.fromString("d69b4274-443c-40a6-8332-a375ebf0feed"), "Health", 0.13 + MathUtils.roundAvoid(rand.nextFloat() * 0.02, 2), 2);
    	}*/
    }
    
    public IAttribute getAttributes()
    {
    	return PlayerAttribute.SPEED;
    	//return PlayerAttribute.HEALTH;
    }
	
}
