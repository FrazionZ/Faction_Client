package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelNetherSpider;
import net.minecraft.entity.monster.EntityNetherSpider;
import net.minecraft.util.ResourceLocation;

public class RenderNetherSpider<T extends EntityNetherSpider> extends RenderLiving<T>
{
    private static final ResourceLocation SPIDER_TEXTURES = new ResourceLocation("textures/entity/nether_spider/nether_spider.png");

    public RenderNetherSpider(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelNetherSpider(), 1.0F);
    }

    protected float getDeathMaxRotation(T entityLivingBaseIn)
    {
        return 180.0F;
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(T entity)
    {
        return SPIDER_TEXTURES;
    }
}
