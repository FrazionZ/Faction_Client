package fz.frazionz.entity.player;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.entity.player.EntityPlayer;

public class PlayerAttribute {

    // Main Attribute
    public static final IAttribute HEALTH = (new RangedAttribute((IAttribute)null, "frazionz.attribute.health", 0.0D, 0.0D, 8.0D)).setDescription("Health").setShouldWatch(true);
    public static final IAttribute DAMAGE = (new RangedAttribute((IAttribute)null, "frazionz.attribute.damage", 1.0D, 0.7D, 1.3D)).setDescription("Damage").setShouldWatch(true);
    public static final IAttribute RESISTANCE = (new RangedAttribute((IAttribute)null, "frazionz.attribute.resistance", 1.0D, 0.7D, 1.3D)).setDescription("Resistance").setShouldWatch(true);
    public static final IAttribute LEECHING = (new RangedAttribute((IAttribute)null, "frazionz.attribute.leeching", 0.0D, 0.0D, 0.1D)).setDescription("Leeching").setShouldWatch(true);
    public static final IAttribute REGENERATION = (new RangedAttribute((IAttribute)null, "frazionz.attribute.regeneration", 1.0D, 0.8D, 1.2D)).setDescription("Regeneration").setShouldWatch(true);
    public static final IAttribute SPEED = (new RangedAttribute((IAttribute)null, "frazionz.attribute.speed", 1.0D, 0.8D, 1.2D)).setDescription("Speed").setShouldWatch(true);
    public static final IAttribute WATER_SPEED = (new RangedAttribute((IAttribute)null, "frazionz.attribute.water_speed", 1.0D, 0.8D, 1.2D)).setDescription("Water Speed").setShouldWatch(true);
    public static final IAttribute MINING_SPEED = (new RangedAttribute((IAttribute)null, "frazionz.attribute.mining_speed", 1.0D, 0.8D, 1.2D)).setDescription("Mining Speed").setShouldWatch(true);
    
    // Experience Bonus
    public static final IAttribute MINING_EXPERIENCE_BONUS = (new RangedAttribute((IAttribute)null, "frazionz.attribute.mining_exp_bonus", 1.0D, 0.5D, 1.5D)).setDescription("Water Speed").setShouldWatch(true);
    public static final IAttribute COMBAT_EXPERIENCE_BONUS = (new RangedAttribute((IAttribute)null, "frazionz.attribute.combat_exp_bonus", 1.0D, 0.5D, 1.5D)).setDescription("Water Speed").setShouldWatch(true);
    
    // Skill Exp Bonus
    public static final IAttribute MINING_SKILL_EXP_BONUS = (new RangedAttribute((IAttribute)null, "frazionz.attribute.skill_exp_mining", 1.0D, 0.5D, 1.5D)).setDescription("Mining Skill Exp").setShouldWatch(true);
    public static final IAttribute FARMING_SKILL_EXP_BONUS = (new RangedAttribute((IAttribute)null, "frazionz.attribute.skill_exp_farming", 1.0D, 0.5D, 1.5D)).setDescription("Farming Skill Exp").setShouldWatch(true);
    public static final IAttribute PILLAGE_SKILL_EXP_BONUS = (new RangedAttribute((IAttribute)null, "frazionz.attribute.skill_exp_pillage", 1.0D, 0.5D, 1.5D)).setDescription("Pillage Skill Exp").setShouldWatch(true);
    public static final IAttribute COMBAT_SKILL_EXP_BONUS = (new RangedAttribute((IAttribute)null, "frazionz.attribute.skill_exp_combat", 1.0D, 0.5D, 1.5D)).setDescription("Combat Skill Exp").setShouldWatch(true);
    public static final IAttribute FORGE_MAGIE_SKILL_EXP_BONUS = (new RangedAttribute((IAttribute)null, "frazionz.attribute.skill_exp_forge_magie", 1.0D, 0.5D, 1.5D)).setDescription("Forge-Magie Skill Exp").setShouldWatch(true);

    public static void registerAllAttribute(EntityPlayer player) {
    	for (Field field : PlayerAttribute.class.getDeclaredFields()) {
    		if(field.getType().equals(IAttribute.class) && Modifier.isStatic(field.getModifiers())) {
    			try {
					player.getAttributeMap().registerAttribute((IAttribute)field.get(null));
				} catch (Exception e) {
					e.printStackTrace();
				}
    		}
    	}
    }
	
}
