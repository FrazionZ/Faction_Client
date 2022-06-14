package fz.frazionz.entity.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.PotionTypes;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityBigXp extends EntityThrowable
{
    public EntityBigXp(World worldIn)
    {
        super(worldIn);
    }

    public EntityBigXp(World worldIn, EntityLivingBase throwerIn)
    {
        super(worldIn, throwerIn);
    }

    public EntityBigXp(World worldIn, double x, double y, double z)
    {
        super(worldIn, x, y, z);
    }

    public static void registerFixesBigXp(DataFixer fixer)
    {
        EntityThrowable.registerFixesThrowable(fixer, "ThrowableBigXp");
    }

    /**
     * Gets the amount of gravity to apply to the thrown entity with each tick.
     */
    protected float getGravityVelocity()
    {
        return 0.07F;
    }

    // PVP_UPDATE
    protected float getVelocity()
    {
        return 0.7F;
    }
    // PVP_UPDATE
    protected float getInaccuracy()
    {
        return -20.0F;
    }

    /**
     * Called when this EntityThrowable hits a block or entity.
     */
    protected void onImpact(RayTraceResult result)
    {
        if (!this.world.isRemote)
        {
            this.world.playEvent(2002, new BlockPos(this), PotionUtils.getPotionColor(PotionTypes.WATER));
            int i = 30 + this.world.rand.nextInt(25) + this.world.rand.nextInt(25);

            while (i > 0)
            {
                int j = EntityXPOrb.getXPSplit(i);
                i -= j;
                this.world.spawnEntity(new EntityXPOrb(this.world, this.posX, this.posY, this.posZ, j));
            }

            this.setDead();
        }
    }
}
