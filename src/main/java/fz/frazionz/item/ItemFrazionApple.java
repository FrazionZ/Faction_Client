package fz.frazionz.item;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemFrazionApple extends ItemFood
{
    public ItemFrazionApple(int amount, float saturation, boolean isWolfFood)
    {
        super(amount, saturation, isWolfFood);
        this.setMaxStackSize(2);
        this.setHasSubtypes(true);
    }

    public boolean hasEffect(ItemStack stack)
    {
        return super.hasEffect(stack) || stack.getMetadata() > 0;
    }

    /**
     * Return an item rarity from EnumRarity
     */
    public EnumRarity getRarity(ItemStack stack)
    {
        return stack.getMetadata() == 0 ? EnumRarity.RARE : EnumRarity.EPIC;
    }

    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player)
    {
        if (!worldIn.isRemote)
        {
                player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 24000, 1));
                player.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 24000, 0));
                player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 800, 3));
                player.addPotionEffect(new PotionEffect(MobEffects.HEALTH_BOOST, 24000, 4));
                player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 24000, 1));
                player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 24000, 1));
        }
    }
    
    @Override
    public void addInformation(ItemStack stack, World playerIn, List<String> tooltip, ITooltipFlag advanced) {
    	tooltip.add(" ");
    	tooltip.add("\u00A76\u00bb \u00A7eRegeneration 4 : 40s");
    	tooltip.add("\u00A76\u00bb \u00A7eSpeed 2 : 20mins");
    	tooltip.add("\u00A76\u00bb \u00A7eForce 2 : 20mins");
    	tooltip.add("\u00A76\u00bb \u00A7eResistance 2 : 20mins");
    	tooltip.add("\u00A76\u00bb \u00A7eFire Resistance 2 : 20mins");
    	tooltip.add("\u00A76\u00bb \u00A7e6 Coeur supplémentaire : 20mins");
    	tooltip.add(" ");
    }
}
