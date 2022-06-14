package fz.frazionz.item;

import fz.frazionz.entity.projectile.EntityDynamiteArrow;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemDynamiteArrow extends ItemArrow
{
    public EntityArrow createArrow(World worldIn, ItemStack stack, EntityLivingBase shooter)
    {
        return new EntityDynamiteArrow(worldIn, shooter);
    }
}
