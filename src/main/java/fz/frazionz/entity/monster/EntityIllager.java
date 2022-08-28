package fz.frazionz.entity.monster;

import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.AbstractIllager;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;

public class EntityIllager extends AbstractIllager
{
    private static final Predicate<Entity> field_190644_c = new Predicate<Entity>()
    {
        public boolean apply(@Nullable Entity p_apply_1_)
        {
            return p_apply_1_ instanceof EntityLivingBase && ((EntityLivingBase)p_apply_1_).attackable();
        }
    };

    public EntityIllager(World worldIn)
    {
        super(worldIn);
        this.setSize(0.6F, 2.00F);
    }

    public static void registerFixesIllager(DataFixer fixer)
    {
        EntityLiving.registerFixesMob(fixer, EntityIllager.class);
    }

    protected void initEntityAI()
    {
    	this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(4, new EntityAIAttackMelee(this, 1.0D, false));
        this.tasks.addTask(8, new EntityAIWander(this, 0.6D));
        this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 3.0F, 1.0F));
        this.tasks.addTask(10, new EntityAIWatchClosest(this, EntityLiving.class, 8.0F));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[] {EntityIllager.class}));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityVillager.class, true));
        this.targetTasks.addTask(4, new EntityIllager.AIJohnnyAttack(this));
    }



    protected void entityInit()
    {
        super.entityInit();
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        super.onUpdate();

    }
    
    public boolean isAggressive()
    {
        return this.isAggressive(1);
    }

    public void setAggressive(boolean p_190636_1_)
    {
        this.setAggressive(1, p_190636_1_);
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3499999940395355D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(24.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(60.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(12.0D);
    }

    protected SoundEvent getAmbientSound()
    {
        return SoundEvents.VINDICATION_ILLAGER_AMBIENT;
    }

    protected SoundEvent getDeathSound()
    {
        return SoundEvents.VINDICATION_ILLAGER_DEATH;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return SoundEvents.ENTITY_VINDICATION_ILLAGER_HURT;
    }


    @Nullable
    protected ResourceLocation getLootTable()
    {
        return LootTableList.ENTITIES_COSMICKER;
    }


    /**
     * Sets the Entity inside a web block.
     */
    public void setInWeb()
    {
    }


    public boolean isPotionApplicable(PotionEffect potioneffectIn)
    {
        return potioneffectIn.getPotion() == MobEffects.POISON ? false : super.isPotionApplicable(potioneffectIn);
    }

    @Nullable

    /**
     * Called only once on an entity when first time spawned, via egg, mob spawner, natural spawning etc, but not called
     * when entity is reloaded from nbt. Mainly used for initializing attributes and inventory
     */
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
    {
        IEntityLivingData ientitylivingdata = super.onInitialSpawn(difficulty, livingdata);
        this.setEquipmentBasedOnDifficulty(difficulty);
        this.setEnchantmentBasedOnDifficulty(difficulty);
        return ientitylivingdata;
    }

    public static class GroupData implements IEntityLivingData
    {
        public Potion effect;

        public void setRandomEffect(Random rand)
        {
            int i = rand.nextInt(5);

            if (i <= 1)
            {
                this.effect = MobEffects.SPEED;
            }
            else if (i <= 2)
            {
                this.effect = MobEffects.STRENGTH;
            }
            else if (i <= 3)
            {
                this.effect = MobEffects.REGENERATION;
            }
        }
    }
    
    
   public boolean isOnSameTeam(Entity entityIn)
   {
       if (super.isOnSameTeam(entityIn))
       {
           return true;
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
    
    
    static class AIJohnnyAttack extends EntityAINearestAttackableTarget<EntityLivingBase>
    {
        public AIJohnnyAttack(EntityIllager p_i47345_1_)
        {
            super(p_i47345_1_, EntityLivingBase.class, 0, true, true, EntityIllager.field_190644_c);
        }

    }
}
