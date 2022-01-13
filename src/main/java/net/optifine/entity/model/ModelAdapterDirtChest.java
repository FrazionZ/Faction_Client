package net.optifine.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityDirtChestRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntityDirtChest;
import optifine.Config;
import optifine.Reflector;

public class ModelAdapterDirtChest extends ModelAdapter
{
    public ModelAdapterDirtChest()
    {
        super(TileEntityDirtChest.class, "dirt_chest", 0.0F);
    }

    public ModelBase makeModel()
    {
        return new ModelChest();
    }

    public ModelRenderer getModelRenderer(ModelBase model, String modelPart)
    {
        if (!(model instanceof ModelChest))
        {
            return null;
        }
        else
        {
            ModelChest modelchest = (ModelChest)model;

            if (modelPart.equals("lid"))
            {
                return modelchest.chestLid;
            }
            else if (modelPart.equals("base"))
            {
                return modelchest.chestBelow;
            }
            else
            {
                return modelPart.equals("knob") ? modelchest.chestKnob : null;
            }
        }
    }

    public IEntityRenderer makeEntityRender(ModelBase modelBase, float shadowSize)
    {
        TileEntityRendererDispatcher tileentityrendererdispatcher = TileEntityRendererDispatcher.instance;
        TileEntitySpecialRenderer tileentityspecialrenderer = tileentityrendererdispatcher.getSpecialRendererByClass(TileEntityDirtChest.class);

        if (!(tileentityspecialrenderer instanceof TileEntityDirtChestRenderer))
        {
            return null;
        }
        else
        {
            if (tileentityspecialrenderer.getEntityClass() == null)
            {
                tileentityspecialrenderer = new TileEntityDirtChestRenderer();
                tileentityspecialrenderer.setRendererDispatcher(tileentityrendererdispatcher);
            }

            if (!Reflector.TileEntityChestRenderer_simpleChest.exists())
            {
                Config.warn("Field not found: TileEntityChestRenderer.simpleChest");
                return null;
            }
            else
            {
                Reflector.setFieldValue(tileentityspecialrenderer, Reflector.TileEntityChestRenderer_simpleChest, modelBase);
                return tileentityspecialrenderer;
            }
        }
    }
}
