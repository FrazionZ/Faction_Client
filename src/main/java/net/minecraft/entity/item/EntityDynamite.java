package net.minecraft.entity.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.PotionTypes;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityDynamite extends EntityThrowable
{
    public EntityDynamite(World worldIn)
    {
        super(worldIn);
    }

    public EntityDynamite(World worldIn, EntityLivingBase throwerIn)
    {
        super(worldIn, throwerIn);
    }

    public EntityDynamite(World worldIn, double x, double y, double z)
    {
        super(worldIn, x, y, z);
    }

    public static void registerFixesDynamite(DataFixer fixer)
    {
        EntityThrowable.registerFixesThrowable(fixer, "ThrowableDynamite");
    }

    /**
     * Gets the amount of gravity to apply to the thrown entity with each tick.
     */
    protected float getGravityVelocity()
    {
        return 0.07F;
    }

    /**
     * Called when this EntityThrowable hits a block or entity.
     */
    protected void onImpact(RayTraceResult result)
    {
        if (!this.world.isRemote)
        {           

            this.setDead();
            
            float f = 2.0F;
            this.world.createExplosion(this, this.posX, this.posY + (double)(this.height / 16.0F), this.posZ, f, true);
        }
    }
}
