package net.minecraft.entity.monster;

import com.google.common.base.Predicate;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityEvokerFangs;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;

public class EntityDemonZ extends EntitySpellcasterIllager
{
    private EntitySheep field_190763_bw;
    private final BossInfoServer bossInfo = (BossInfoServer)(new BossInfoServer(this.getDisplayName(), BossInfo.Color.YELLOW, BossInfo.Overlay.PROGRESS)).setDarkenSky(true);

    public EntityDemonZ(World p_i47287_1_)
    {
        super(p_i47287_1_);
        this.setSize(0.6F, 2.5F);
        this.experienceValue = 10;
    }
    
    public static void registerFixesDemonZ(DataFixer fixer)
    {
        EntityLiving.registerFixesMob(fixer, EntityDemonZ.class);
    }

    protected void initEntityAI()
    {
        super.initEntityAI();
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityDemonZ.AICastingSpell());
        this.tasks.addTask(2, new EntityAIAvoidEntity(this, EntityPlayer.class, 0.0F, 0.6D, 0.6D));
        this.tasks.addTask(4, new EntityDemonZ.AISummonSpell());
        this.tasks.addTask(5, new EntityDemonZ.AIAttackSpell());
        this.tasks.addTask(6, new EntityAIWander(this, 0.6D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 3.0F, 1.0F));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityLiving.class, 8.0F));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[] {EntityDemonZ.class}));
        this.targetTasks.addTask(2, (new EntityAINearestAttackableTarget(this, EntityPlayer.class, true)).func_190882_b(300));
        this.targetTasks.addTask(3, (new EntityAINearestAttackableTarget(this, EntityVillager.class, false)).func_190882_b(300));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityIronGolem.class, false));
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(64.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(1000.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(10.0D);
    }

    protected void entityInit()
    {
        super.entityInit();
    }
    
    
     // TELEPORT //
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
    	
    	
        if (this.isEntityInvulnerable(source))
        {
            return false;
        }
    	
    	else {
    		
    		boolean flag = super.attackEntityFrom(source, amount);
    		return flag;
    		
    		
    	}
    	
        /*if (this.isEntityInvulnerable(source))
        {
            return false;
        }
        
        else if (source instanceof EntityDamageSourceIndirect)
        {
            for (int i = 0; i < 64; ++i)
            {
                if (this.teleportRandomly())
                {
                    return true;
                }
            }

            return false;
            
        }
        else
        {
            boolean flag = super.attackEntityFrom(source, amount);

            if (source.isUnblockable() && this.rand.nextInt(10) != 0)
            {
                this.teleportRandomly();
            }

            return flag;
        }*/
    }
    
    
    // TELEPORT //
    
    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
    }

    protected ResourceLocation getLootTable()
    {
        return LootTableList.BOSS_HEROBRINE_YELLOW;
    }

    protected void updateAITasks()
    {

        if (this.world.isDaytime() && this.ticksExisted >= 600)
        {
            float f = this.getBrightness();

           /* if (this.world.canSeeSky(new BlockPos(this)) && this.rand.nextFloat() * 30.0F < (f - 0.4F) * 2.0F)
            {
                this.setAttackTarget((EntityLivingBase)null);
            }*/
        }
    	
    	this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
    	
        super.updateAITasks();
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        super.onUpdate();
    }

    
    public void setInWeb()
    {
    }

    /**
     * Add the given player to the list of players tracking this entity. For instance, a player may track a boss in
     * order to view its associated boss bar.
     */
    public void addTrackingPlayer(EntityPlayerMP player)
    {
        super.addTrackingPlayer(player);
        this.bossInfo.addPlayer(player);
    }
    
    /**
     * Removes the given player from the list of players tracking this entity. See {@link Entity#addTrackingPlayer} for
     * more information on tracking.
     */
    public void removeTrackingPlayer(EntityPlayerMP player)
    {
        super.removeTrackingPlayer(player);
        this.bossInfo.removePlayer(player);
    }
    
    /**
     * Returns whether this Entity is on the same team as the given Entity.
     */
    public boolean isOnSameTeam(Entity entityIn)
    {
        if (entityIn == null)
        {
            return false;
        }
        else if (entityIn == this)
        {
            return true;
        }
        else if (super.isOnSameTeam(entityIn))
        {
            return true;
        }
        else if (entityIn instanceof EntityVex)
        {
            return this.isOnSameTeam(((EntityVex)entityIn).func_190645_o());
        }
        else if (entityIn instanceof EntityLivingBase && ((EntityLivingBase)entityIn).getCreatureAttribute() == EnumCreatureAttribute.ILLAGER)
        {
            return this.getTeam() == null && entityIn.getTeam() == null;
        }
        else
        {
            return false;
        }
    }

    protected SoundEvent getAmbientSound()
    {
        return SoundEvents.field_191243_bm;
    }

    protected SoundEvent getDeathSound()
    {
        return SoundEvents.field_191245_bo;
    }

    protected SoundEvent getHurtSound(DamageSource p_184601_1_)
    {
        return SoundEvents.field_191246_bp;
    }

    private void func_190748_a(@Nullable EntitySheep p_190748_1_)
    {
        this.field_190763_bw = p_190748_1_;
    }

    @Nullable
    private EntitySheep func_190751_dj()
    {
        return this.field_190763_bw;
    }

    protected SoundEvent func_193086_dk()
    {
        return SoundEvents.field_191244_bn;
    }

    class AIAttackSpell extends EntitySpellcasterIllager.AIUseSpell
    {
        private AIAttackSpell()
        {
        }

        protected int func_190869_f()
        {
            return 40;
        }

        protected int func_190872_i()
        {
            return 100;
        }

        protected void func_190868_j()
        {
            EntityLivingBase entitylivingbase = EntityDemonZ.this.getAttackTarget();
            double d0 = Math.min(entitylivingbase.posY, EntityDemonZ.this.posY);
            double d1 = Math.max(entitylivingbase.posY, EntityDemonZ.this.posY) + 1.0D;
            float f = (float)MathHelper.atan2(entitylivingbase.posZ - EntityDemonZ.this.posZ, entitylivingbase.posX - EntityDemonZ.this.posX);

            if (EntityDemonZ.this.getDistanceSqToEntity(entitylivingbase) < 2.0D)
            {
                for (int i = 0; i < 5; ++i)
                {
                    float f1 = f + (float)i * (float)Math.PI * 0.4F;
                    this.func_190876_a(EntityDemonZ.this.posX + (double)MathHelper.cos(f1) * 1.5D, EntityDemonZ.this.posZ + (double)MathHelper.sin(f1) * 1.5D, d0, d1, f1, 0);
                }

                for (int k = 0; k < 8; ++k)
                {
                    float f2 = f + (float)k * (float)Math.PI * 2.0F / 8.0F + ((float)Math.PI * 2F / 5F);
                    this.func_190876_a(EntityDemonZ.this.posX + (double)MathHelper.cos(f2) * 2.5D, EntityDemonZ.this.posZ + (double)MathHelper.sin(f2) * 2.5D, d0, d1, f2, 3);
                }
            }
            else
            {
                for (int l = 0; l < 20; ++l)
                {
                    double d2 = 1.25D * (double)(l + 1);
                    int j = 1 * l;
                    
                    this.func_190876_a(EntityDemonZ.this.posX + (double)MathHelper.cos(f) * d2, EntityDemonZ.this.posZ + (double)MathHelper.sin(f) * d2, d0, d1, f, j);
                }
            }
        }
        
        private void func_190876_a(double p_190876_1_, double p_190876_3_, double p_190876_5_, double p_190876_7_, float p_190876_9_, int p_190876_10_)
        {
            BlockPos blockpos = new BlockPos(p_190876_1_, p_190876_7_, p_190876_3_);
            boolean flag = false;
            double d0 = 0.0D;

            while (true)
            {
                if (!EntityDemonZ.this.world.isBlockNormalCube(blockpos, true) && EntityDemonZ.this.world.isBlockNormalCube(blockpos.down(), true))
                {
                    if (!EntityDemonZ.this.world.isAirBlock(blockpos))
                    {
                        IBlockState iblockstate = EntityDemonZ.this.world.getBlockState(blockpos);
                        AxisAlignedBB axisalignedbb = iblockstate.getCollisionBoundingBox(EntityDemonZ.this.world, blockpos);

                        if (axisalignedbb != null)
                        {
                            d0 = axisalignedbb.maxY;
                        }
                    }

                    flag = true;
                    break;
                }

                blockpos = blockpos.down();

                if (blockpos.getY() < MathHelper.floor(p_190876_5_) - 1)
                {
                    break;
                }
            }

            if (flag)
            {
                EntityEvokerFangs entityevokerfangs = new EntityEvokerFangs(EntityDemonZ.this.world, p_190876_1_, (double)blockpos.getY() + d0, p_190876_3_, p_190876_9_, p_190876_10_, EntityDemonZ.this);
                EntityEvokerFangs entityevokerfangs2 = new EntityEvokerFangs(EntityDemonZ.this.world, p_190876_1_ - 1, (double)blockpos.getY() + d0, p_190876_3_ - 1, p_190876_9_, p_190876_10_, EntityDemonZ.this);
                EntityEvokerFangs entityevokerfangs3 = new EntityEvokerFangs(EntityDemonZ.this.world, p_190876_1_ + 1, (double)blockpos.getY() + d0, p_190876_3_ - 1, p_190876_9_, p_190876_10_, EntityDemonZ.this);
                EntityEvokerFangs entityevokerfangs4 = new EntityEvokerFangs(EntityDemonZ.this.world, p_190876_1_ - 2, (double)blockpos.getY() + d0, p_190876_3_ - 2, p_190876_9_, p_190876_10_, EntityDemonZ.this);
                EntityEvokerFangs entityevokerfangs5 = new EntityEvokerFangs(EntityDemonZ.this.world, p_190876_1_ + 2, (double)blockpos.getY() + d0, p_190876_3_ - 2, p_190876_9_, p_190876_10_, EntityDemonZ.this);
                
                EntityDemonZ.this.world.spawnEntityInWorld(entityevokerfangs);
                EntityDemonZ.this.world.spawnEntityInWorld(entityevokerfangs2);
                EntityDemonZ.this.world.spawnEntityInWorld(entityevokerfangs3);
                EntityDemonZ.this.world.spawnEntityInWorld(entityevokerfangs4);
                EntityDemonZ.this.world.spawnEntityInWorld(entityevokerfangs5);
            }
        }
        
        protected SoundEvent func_190871_k()
        {
            return SoundEvents.field_191247_bq;
        }

        protected EntitySpellcasterIllager.SpellType func_193320_l()
        {
            return EntitySpellcasterIllager.SpellType.FANGS;
        }
    }
    
    class AICastingSpell extends EntitySpellcasterIllager.AICastingApell
    {
        private AICastingSpell()
        {
        }

        public void updateTask()
        {
            if (EntityDemonZ.this.getAttackTarget() != null)
            {
                EntityDemonZ.this.getLookHelper().setLookPositionWithEntity(EntityDemonZ.this.getAttackTarget(), (float)EntityDemonZ.this.getHorizontalFaceSpeed(), (float)EntityDemonZ.this.getVerticalFaceSpeed());
            }
            else if (EntityDemonZ.this.func_190751_dj() != null)
            {
                EntityDemonZ.this.getLookHelper().setLookPositionWithEntity(EntityDemonZ.this.func_190751_dj(), (float)EntityDemonZ.this.getHorizontalFaceSpeed(), (float)EntityDemonZ.this.getVerticalFaceSpeed());
            }
        }
    }

    class AISummonSpell extends EntitySpellcasterIllager.AIUseSpell
    {
        private AISummonSpell()
        {
        }

        public boolean shouldExecute()
        {
            if (!super.shouldExecute())
            {
                return false;
            }
            else
            {
                int i = EntityDemonZ.this.world.getEntitiesWithinAABB(EntityVindicator.class, EntityDemonZ.this.getEntityBoundingBox().expandXyz(16.0D)).size();
                return EntityDemonZ.this.rand.nextInt(8) + 1 > i;
            }
        }

        protected int func_190869_f()
        {
            return 100;
        }

        protected int func_190872_i()
        {
            return 100;
        }

        protected void func_190868_j()
        {
            for (int i = 0; i < 6; ++i)
            {
                BlockPos blockpos = (new BlockPos(EntityDemonZ.this)).add(-2 + EntityDemonZ.this.rand.nextInt(5), 1, -2 + EntityDemonZ.this.rand.nextInt(5));
                EntityVindicator entity = new EntityVindicator(EntityDemonZ.this.world);
                
                entity.moveToBlockPosAndAngles(blockpos, 0.0F, 0.0F);
                entity.onInitialSpawn(EntityDemonZ.this.world.getDifficultyForLocation(blockpos), (IEntityLivingData)null);
                EntityDemonZ.this.world.spawnEntityInWorld(entity);
            }
        }

        protected SoundEvent func_190871_k()
        {
            return SoundEvents.field_191248_br;
        }

        protected EntitySpellcasterIllager.SpellType func_193320_l()
        {
            return EntitySpellcasterIllager.SpellType.SUMMON_VEX;
        }
    }

}
