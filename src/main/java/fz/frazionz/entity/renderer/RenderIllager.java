package fz.frazionz.entity.renderer;

import fz.frazionz.entity.model.ModelNewIllager;
import fz.frazionz.entity.monster.EntityIllager;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderIllager<T extends EntityIllager> extends RenderLiving<T>
{
    private static final ResourceLocation SPIDER_TEXTURES = new ResourceLocation("textures/entity/illager.png");

    public RenderIllager(RenderManager renderManagerIn)
    {
        super(renderManagerIn, (ModelBase)new ModelNewIllager(0.0F, 0.0F, 64, 64), 0.5F);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(T entity)
    {
        return SPIDER_TEXTURES;
    }
}
