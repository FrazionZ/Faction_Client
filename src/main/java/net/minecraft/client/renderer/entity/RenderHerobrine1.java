package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelHerobrine1;
import net.minecraft.entity.monster.EntityHerobrine1;
import net.minecraft.util.ResourceLocation;

public class RenderHerobrine1<T extends EntityHerobrine1> extends RenderLiving<T>
{
    private static final ResourceLocation TEXTURES = new ResourceLocation("textures/entity/herobrine/herobrine_1.png");

    public RenderHerobrine1(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelHerobrine1(), 1.0F);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(T entity)
    {
        return TEXTURES;
    }
}
