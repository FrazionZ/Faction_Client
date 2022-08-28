package net.optifine.entity.model;

import net.minecraft.resources.ResourceLocation;

public interface IEntityRenderer
{
    Class getEntityClass();

    void setEntityClass(Class var1);

    ResourceLocation getLocationTextureCustom();

    void setLocationTextureCustom(ResourceLocation var1);
}
