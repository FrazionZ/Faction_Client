package net.minecraft.item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.google.common.base.Predicates;
import com.google.common.collect.Multimap;

import fz.frazionz.utils.EffectItem;
import net.minecraft.block.BlockDispenser;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemArmor extends Item
{
    /** Holds the 'base' maxDamage that each armorType have. */
    private static final int[] MAX_DAMAGE_ARRAY = new int[] {40, 45, 50, 35};
    private static final UUID[] ARMOR_MODIFIERS = new UUID[] {UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"), UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"), UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"), UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150")};
    public static final String[] EMPTY_SLOT_NAMES = new String[] {"minecraft:items/empty_armor_slot_boots", "minecraft:items/empty_armor_slot_leggings", "minecraft:items/empty_armor_slot_chestplate", "minecraft:items/empty_armor_slot_helmet"};
    public static final IBehaviorDispenseItem DISPENSER_BEHAVIOR = new BehaviorDefaultDispenseItem()
    {
        protected ItemStack dispenseStack(IBlockSource source, ItemStack stack)
        {
            ItemStack itemstack = ItemArmor.dispenseArmor(source, stack);
            return itemstack.isEmpty() ? super.dispenseStack(source, stack) : itemstack;
        }
    };

    /**
     * Stores the armor type: 0 is helmet, 1 is plate, 2 is legs and 3 is boots
     */
    public final EntityEquipmentSlot armorType;

    /** Holds the amount of damage that the armor reduces at full durability. */
    public final int damageReduceAmount;
    public final float toughness;

    /**
     * Used on RenderPlayer to select the correspondent armor to be rendered on the player: 0 is cloth, 1 is chain, 2 is
     * iron, 3 is diamond and 4 is gold.
     */
    public final int renderIndex;

    /** The EnumArmorMaterial used for this ItemArmor */
    private final ItemArmor.ArmorMaterial material;

    public static ItemStack dispenseArmor(IBlockSource blockSource, ItemStack stack)
    {
        BlockPos blockpos = blockSource.getBlockPos().offset((EnumFacing)blockSource.getBlockState().getValue(BlockDispenser.FACING));
        List<EntityLivingBase> list = blockSource.getWorld().<EntityLivingBase>getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(blockpos), Predicates.and(EntitySelectors.NOT_SPECTATING, new EntitySelectors.ArmoredMob(stack)));

        if (list.isEmpty())
        {
            return ItemStack.EMPTY;
        }
        else
        {
            EntityLivingBase entitylivingbase = list.get(0);
            EntityEquipmentSlot entityequipmentslot = EntityLiving.getSlotForItemStack(stack);
            ItemStack itemstack = stack.splitStack(1);
            entitylivingbase.setItemStackToSlot(entityequipmentslot, itemstack);

            if (entitylivingbase instanceof EntityLiving)
            {
                ((EntityLiving)entitylivingbase).setDropChance(entityequipmentslot, 2.0F);
            }

            return stack;
        }
    }

    public ItemArmor(ItemArmor.ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn)
    {
        this.material = materialIn;
        this.armorType = equipmentSlotIn;
        this.renderIndex = renderIndexIn;
        this.damageReduceAmount = materialIn.getDamageReductionAmount(equipmentSlotIn);
        this.setMaxDamage(materialIn.getDurability(equipmentSlotIn));
        this.toughness = materialIn.getToughness();
        this.maxStackSize = 1;
        this.setCreativeTab(CreativeTabs.COMBAT);
        BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(this, DISPENSER_BEHAVIOR);
    }

    /**
     * Gets the equipment slot of this armor piece (formerly known as armor type)
     */
    public EntityEquipmentSlot getEquipmentSlot()
    {
        return this.armorType;
    }

    /**
     * Return the enchantability factor of the item, most of the time is based on material.
     */
    public int getItemEnchantability()
    {
        return this.material.getEnchantability();
    }

    /**
     * Return the armor material for this armor item.
     */
    public ItemArmor.ArmorMaterial getArmorMaterial()
    {
        return this.material;
    }

    /**
     * Return whether the specified armor ItemStack has a color.
     */
    public boolean hasColor(ItemStack stack)
    {
        if (this.material != ItemArmor.ArmorMaterial.LEATHER)
        {
            return false;
        }
        else
        {
            NBTTagCompound nbttagcompound = stack.getTagCompound();
            return nbttagcompound != null && nbttagcompound.hasKey("display", 10) ? nbttagcompound.getCompoundTag("display").hasKey("color", 3) : false;
        }
    }

    /**
     * Return the color for the specified armor ItemStack.
     */
    public int getColor(ItemStack stack)
    {
        if (this.material != ItemArmor.ArmorMaterial.LEATHER)
        {
            return 16777215;
        }
        else
        {
            NBTTagCompound nbttagcompound = stack.getTagCompound();

            if (nbttagcompound != null)
            {
                NBTTagCompound nbttagcompound1 = nbttagcompound.getCompoundTag("display");

                if (nbttagcompound1 != null && nbttagcompound1.hasKey("color", 3))
                {
                    return nbttagcompound1.getInteger("color");
                }
            }

            return 10511680;
        }
    }

    /**
     * Remove the color from the specified armor ItemStack.
     */
    public void removeColor(ItemStack stack)
    {
        if (this.material == ItemArmor.ArmorMaterial.LEATHER)
        {
            NBTTagCompound nbttagcompound = stack.getTagCompound();

            if (nbttagcompound != null)
            {
                NBTTagCompound nbttagcompound1 = nbttagcompound.getCompoundTag("display");

                if (nbttagcompound1.hasKey("color"))
                {
                    nbttagcompound1.removeTag("color");
                }
            }
        }
    }

    /**
     * Sets the color of the specified armor ItemStack
     */
    public void setColor(ItemStack stack, int color)
    {
        if (this.material != ItemArmor.ArmorMaterial.LEATHER)
        {
            throw new UnsupportedOperationException("Can't dye non-leather!");
        }
        else
        {
            NBTTagCompound nbttagcompound = stack.getTagCompound();

            if (nbttagcompound == null)
            {
                nbttagcompound = new NBTTagCompound();
                stack.setTagCompound(nbttagcompound);
            }

            NBTTagCompound nbttagcompound1 = nbttagcompound.getCompoundTag("display");

            if (!nbttagcompound.hasKey("display", 10))
            {
                nbttagcompound.setTag("display", nbttagcompound1);
            }

            nbttagcompound1.setInteger("color", color);
        }
    }

    /**
     * Return whether this item is repairable in an anvil.
     *  
     * @param toRepair the {@code ItemStack} being repaired
     * @param repair the {@code ItemStack} being used to perform the repair
     */
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
    {
        return this.material.getRepairItem() == repair.getItem() ? true : super.getIsRepairable(toRepair, repair);
    }

    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        EntityEquipmentSlot entityequipmentslot = EntityLiving.getSlotForItemStack(itemstack);
        ItemStack itemstack1 = playerIn.getItemStackFromSlot(entityequipmentslot);

        if (itemstack1.isEmpty())
        {
            playerIn.setItemStackToSlot(entityequipmentslot, itemstack.copy());
            itemstack.setCount(0);
            return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
        }
        else
        {
            return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);
        }
    }

    public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot)
    {
        Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);

        if (equipmentSlot == this.armorType)
        {
            multimap.put(SharedMonsterAttributes.ARMOR.getName(), new AttributeModifier(ARMOR_MODIFIERS[equipmentSlot.getIndex()], "Armor modifier", (double)this.damageReduceAmount, 0));
            multimap.put(SharedMonsterAttributes.ARMOR_TOUGHNESS.getName(), new AttributeModifier(ARMOR_MODIFIERS[equipmentSlot.getIndex()], "Armor toughness", (double)this.toughness, 0));
        }

        return multimap;
    }
    
    @Override
    public void addInformation(ItemStack stack, World playerIn, List<String> tooltip, ITooltipFlag advanced) {
    	
    	if(this.getArmorMaterial() == ArmorMaterial.TRAVELERS) {
        	tooltip.add(" ");
        	tooltip.add("\u00A76\u00bb \u00A7eEffects Full Armure :");
        	tooltip.add("\u00A76\u00bb \u00A7eSpeed 3");
        	tooltip.add("\u00A76\u00bb \u00A7eHaste 1");
        	tooltip.add("\u00A76\u00bb \u00A7eFire Resistance 1");
        	tooltip.add("\u00A76\u00bb \u00A7eNo Fall");
        	tooltip.add("\u00A76\u00bb \u00A7eSaturation 1");
        	tooltip.add(" ");
    	}
    	else if(this.getArmorMaterial() == ArmorMaterial.FRAZION_100) {
        	tooltip.add(" ");
        	tooltip.add("\u00A76\u00bb \u00A7eEffects Full Armure :");
        	tooltip.add("\u00A76\u00bb \u00A7eSpeed 1");
        	tooltip.add("\u00A76\u00bb \u00A7eForce 1");
        	tooltip.add("\u00A76\u00bb \u00A7eResistance 1");
        	tooltip.add("\u00A76\u00bb \u00A7eFire Resistance 1");
        	tooltip.add(" ");
        	tooltip.add("\u00A76\u00bb \u00A7eAnti Blindness");
        	tooltip.add("\u00A76\u00bb \u00A7eAnti Slowness");
        	tooltip.add("\u00A76\u00bb \u00A7eAnti Poison");
        	tooltip.add(" ");
    	}
    	else if(this.getArmorMaterial() == ArmorMaterial.FRAZION_70) {
        	tooltip.add(" ");
        	tooltip.add("\u00A76\u00bb \u00A7eEffects Full Armure :");
        	tooltip.add("\u00A76\u00bb \u00A7eSpeed 1");
        	tooltip.add("\u00A76\u00bb \u00A7eFire Resistance 1");
        	tooltip.add(" ");
        	tooltip.add("\u00A76\u00bb \u00A7eAnti Poison");
        	tooltip.add(" ");
    	}
    }

    public static enum ArmorMaterial
    {
        LEATHER("leather", 3, new int[]{1, 1, 1, 1}, 15, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F),
        CHAIN("chainmail", 7, new int[]{2, 2, 2, 2}, 12, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 0.0F),
        IRON("iron", 8, new int[] {3, 4, 3, 2}, 9, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F),
        GOLD("gold", 2, new int[]{3, 4, 3, 2}, 25, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0.0F),
        DIAMOND("diamond", 10, new int[]{4, 5, 4, 3}, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.0f/*0.25F*/),
        YELLITE("yellite", 25, new int[]{4, 5, 5, 4}, 12, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.0f/*0.50F*/),
        BAUXITE("bauxite", 30, new int[]{5, 6, 6, 5}, 12, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.0f/*0.80F*/),
        ONYX("onyx", 35, new int[]{6, 7, 7, 6}, 12, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.0f/*1.0F*/),
        FRAZION_45("frazion", 45, new int[]{7, 8, 8, 7}, 12, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.0f/*1.2F*/),
        FRAZION_70("frazion_70", 65, new int[]{7, 8, 8, 7}, 12, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.3125f/*1.5F*/,
        		Arrays.asList(new EffectItem(MobEffects.SPEED, 200000, 0),
        				new EffectItem(MobEffects.FIRE_RESISTANCE, 200000, 0)),
        		Arrays.asList(MobEffects.POISON)
        		),
        FRAZION_100("frazion_100", 80, new int[]{7, 8, 8, 7}, 12, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.625f/*1.8F*/,
        		Arrays.asList(
        				new EffectItem(MobEffects.SPEED, 200000, 0),
        				new EffectItem(MobEffects.RESISTANCE, 200000, 0),
        				new EffectItem(MobEffects.FIRE_RESISTANCE, 200000, 0),
        				new EffectItem(MobEffects.STRENGTH, 200000, 0)),
        		Arrays.asList(
        				MobEffects.POISON,
        				MobEffects.SLOWNESS,
        				MobEffects.BLINDNESS)
        		), 
        TRAVELERS("travelers", 18, new int[]{4, 5, 4, 3}, 12, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.0f/*1.0F*/, 
        		Arrays.asList(
        				new EffectItem(MobEffects.SPEED, 200000, 2),
        				new EffectItem(MobEffects.FIRE_RESISTANCE, 200000, 0),
        				new EffectItem(MobEffects.HASTE, 200000, 0),
        				new EffectItem(MobEffects.NO_FALL, 200000, 0),
        				new EffectItem(MobEffects.SATURATION, 200000, 0))
        		),
        ;
    	
        private final String name;
        private final int maxDamageFactor;
        private final int[] damageReductionAmountArray;
        private final int enchantability;
        private final SoundEvent soundEvent;
        private final float toughness;
        private List<EffectItem> effectList = new ArrayList<EffectItem>();
        private List<Potion> antiEffect = new ArrayList<Potion>();

        private ArmorMaterial(String nameIn, int maxDamageFactorIn, int[] damageReductionAmountArrayIn, int enchantabilityIn, SoundEvent soundEventIn, float toughnessIn)
        {
            this.name = nameIn;
            this.maxDamageFactor = maxDamageFactorIn;
            this.damageReductionAmountArray = damageReductionAmountArrayIn;
            this.enchantability = enchantabilityIn;
            this.soundEvent = soundEventIn;
            this.toughness = toughnessIn;
        }
        
        private ArmorMaterial(String nameIn, int maxDamageFactorIn, int[] damageReductionAmountArrayIn, int enchantabilityIn, SoundEvent soundEventIn, float toughnessIn, List<EffectItem> effectList)
        {
            this.name = nameIn;
            this.maxDamageFactor = maxDamageFactorIn;
            this.damageReductionAmountArray = damageReductionAmountArrayIn;
            this.enchantability = enchantabilityIn;
            this.soundEvent = soundEventIn;
            this.toughness = toughnessIn;
            this.effectList = effectList;
        }
        
        private ArmorMaterial(String nameIn, int maxDamageFactorIn, int[] damageReductionAmountArrayIn, int enchantabilityIn, SoundEvent soundEventIn, float toughnessIn, List<EffectItem> effectList, List<Potion> antiEffect)
        {
            this.name = nameIn;
            this.maxDamageFactor = maxDamageFactorIn;
            this.damageReductionAmountArray = damageReductionAmountArrayIn;
            this.enchantability = enchantabilityIn;
            this.soundEvent = soundEventIn;
            this.toughness = toughnessIn;
            this.effectList = effectList;
            this.antiEffect = antiEffect;
        }
        
        public boolean hasAntiEffect() {
        	return !this.antiEffect.isEmpty();
        }
        
        public boolean hasEffect() {
        	return !this.effectList.isEmpty();
        }
        
        public List<Potion> getAntiEffect() {
        	return this.antiEffect;
        }
        
        public List<EffectItem> getEffectList()
        {
        	return this.effectList;
        }

        public int getDurability(EntityEquipmentSlot armorType)
        {
            return ItemArmor.MAX_DAMAGE_ARRAY[armorType.getIndex()] * this.maxDamageFactor;
        }

        public int getDamageReductionAmount(EntityEquipmentSlot armorType)
        {
            return this.damageReductionAmountArray[armorType.getIndex()];
        }

        public int getEnchantability()
        {
            return this.enchantability;
        }

        public SoundEvent getSoundEvent()
        {
            return this.soundEvent;
        }

        public Item getRepairItem()
        {
            if (this == LEATHER)
            {
                return Items.LEATHER;
            }
            else if (this == CHAIN)
            {
                return Items.IRON_INGOT;
            }
            else if (this == GOLD)
            {
                return Items.GOLD_INGOT;
            }
            else if (this == IRON)
            {
                return Items.IRON_INGOT;
            }
            else if (this == YELLITE)
            {
                return Items.YELLITE;
            }
            else if (this == BAUXITE)
            {
                return Items.BAUXITE;
            }
            else if (this == ONYX)
            {
                return Items.ONYX;
            }
            else if (this == FRAZION_45)
            {
                return Items.FRAZION;
            }
            else if (this == FRAZION_70)
            {
                return Items.FRAZION;
            }
            else if (this == FRAZION_100)
            {
                return Items.FRAZION;
            }
            else if (this == TRAVELERS) {
            	return Items.ONYX;
            }
            else
            {
                return this == DIAMOND ? Items.DIAMOND : null;
            }
        }

        public String getName()
        {
            return this.name;
        }

        public float getToughness()
        {
            return this.toughness;
        }
    }
}
