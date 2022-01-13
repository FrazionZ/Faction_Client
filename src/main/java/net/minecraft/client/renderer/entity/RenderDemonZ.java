package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelDemonZ;
import net.minecraft.entity.monster.EntityDemonZ;
import net.minecraft.util.ResourceLocation;

public class RenderDemonZ<T extends EntityDemonZ> extends RenderLiving<T>
{
    private static final ResourceLocation TEXTURES = new ResourceLocation("textures/entity/frazionz/demon.png");

    public RenderDemonZ(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelDemonZ(), 0.5F);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(T entity)
    {
        return TEXTURES;
    }
}
