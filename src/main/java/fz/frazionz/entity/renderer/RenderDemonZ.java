package fz.frazionz.entity.renderer;

import fz.frazionz.client.model.JsonEntityModel;
import fz.frazionz.entity.monster.EntityDemonZ;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.resources.ResourceLocation;

public class RenderDemonZ<T extends EntityDemonZ> extends RenderLiving<T>
{
    private static final ResourceLocation TEXTURES = new ResourceLocation("frazionz", "textures/entity/demon.png");

    public RenderDemonZ(RenderManager renderManagerIn)
    {
        //super(renderManagerIn, new ModelDemonZ(), 0.5F);
        super(renderManagerIn, new JsonEntityModel("demon"), 0.5F);
    }
    
    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(T entity)
    {
        return TEXTURES;
    }
}
