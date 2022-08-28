package fz.frazionz.tileentity.renderer;

import fz.frazionz.client.model.JsonEntityModel;
import fz.frazionz.tileentity.TileEntityTrophyForge;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class TileEntityTrophyForgeRenderer extends TileEntitySpecialRenderer<TileEntityTrophyForge>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation("frazionz", "textures/blocks/3d/trophy_forge.png");
    private final JsonEntityModel model = new JsonEntityModel("trophy_forge");

    public void render(TileEntityTrophyForge te, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
    {	
        int i = 0;

        if (te.hasWorld())
        {
            i = te.getBlockMetadata();
        }
        
        GlStateManager.pushMatrix();
        
        this.bindTexture(TEXTURE);
        
        GlStateManager.translate((float)x + 0.5f, (float)y+1.5f, (float)z + 0.5f);
        int j = 0;

        switch(i) {
	        case 3:
	        	j = 0;
	        	break;
	        case 4:
	        	j = -180;
	        	break;
	        case 5:
	        	j = 90;
	        	break;
	        case 6:
	        	j = -90;
        }
        GlStateManager.rotate((float)j, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(180.0F, 1.0F, 0.0F, 0.0F);
        
        float f = te.getTickCount() + partialTicks;
        if(te.isForging()) {
        	model.getCube("piston").offsetY = 0.18f + MathHelper.sin(f * 0.4F) * 0.18F;
        }
        else if(!te.isAnimationEnd()) {
        	model.getCube("piston").offsetY = 0.18f + MathHelper.sin(f * 0.4F) * 0.18F;
        	if(model.getCube("piston").offsetY <= 0.0005f ) {
        		model.getCube("piston").offsetY = 0.0f;
        		te.getWorld().addBlockEvent(te.getPos(), te.getBlockType(), 2, 1);
        	}
        }
        this.model.renderAll();
        
        model.getCube("piston").offsetY = 0.0f;
        GlStateManager.popMatrix();
    }
}
