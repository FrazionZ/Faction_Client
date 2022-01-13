package net.minecraft.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.world.World;

public class EntityDynamiteArrow extends EntityArrow
{

    public EntityDynamiteArrow(World worldIn)
    {
        super(worldIn);
    }

    public EntityDynamiteArrow(World worldIn, EntityLivingBase shooter)
    {
        super(worldIn, shooter);
    }

    public EntityDynamiteArrow(World worldIn, double x, double y, double z)
    {
        super(worldIn, x, y, z);
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        super.onUpdate();
    }

    protected ItemStack getArrowStack()
    {
        return new ItemStack(Items.DYNAMITE_ARROW);
    }
    
    public static ItemStack getDynamiteArrowStack()
    {
        return new ItemStack(Items.DYNAMITE_ARROW);
    }


    public static void registerFixesSpectralArrow(DataFixer fixer)
    {
        EntityArrow.registerFixesArrow(fixer, "dynamite_arrow");
    }

}
