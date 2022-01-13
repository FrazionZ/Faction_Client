package net.minecraft.client.renderer.entity;

import net.minecraft.entity.projectile.EntityDynamiteArrow;
import net.minecraft.util.ResourceLocation;

public class RenderDynamiteArrow extends RenderArrow<EntityDynamiteArrow>
{
    public static final ResourceLocation TEXTURE = new ResourceLocation("textures/entity/projectiles/dynamite_arrow.png");

    public RenderDynamiteArrow(RenderManager manager)
    {
        super(manager);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityDynamiteArrow entity)
    {
        return TEXTURE;
    }
}
