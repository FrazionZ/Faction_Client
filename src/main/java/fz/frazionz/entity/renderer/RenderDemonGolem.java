package fz.frazionz.entity.renderer;

import fz.frazionz.entity.model.ModelDemonGolem;
import fz.frazionz.entity.monster.EntityDemonGolem;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderDemonGolem<T extends EntityDemonGolem> extends RenderLiving<T>
{
    private static final ResourceLocation TEXTURES = new ResourceLocation("textures/entity/frazionz/demon_golem.png");

    public RenderDemonGolem(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelDemonGolem(), 0.5F);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(T entity)
    {
        return TEXTURES;
    }
}
