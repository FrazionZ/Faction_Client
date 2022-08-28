package fz.frazionz.entity.renderer;

import fz.frazionz.entity.projectile.EntityDynamiteArrow;
import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.resources.ResourceLocation;

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
