package fz.frazionz.tileentity.renderer;

import fz.frazionz.client.model.entity.GrimoireModel;
import fz.frazionz.tileentity.TileEntityGrimoirePedestal;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class TileEntityGrimoirePedestalRenderer extends TileEntitySpecialRenderer<TileEntityGrimoirePedestal>
{
    /** The texture for the book above the enchantment table. */
    private static final ResourceLocation TEXTURE_BOOK = new ResourceLocation("frazionz", "textures/entity/grimoire.png");
    private final GrimoireModel modelBook = new GrimoireModel();

    public void render(TileEntityGrimoirePedestal te, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
    {
    	render(te, x, y, z, partialTicks, destroyStage, alpha, true);
    }
    
    public void render(TickCounter te, double x, double y, double z, float partialTicks, int destroyStage, float alpha, boolean renderBook)
    {
    	if(renderBook) {
            GlStateManager.pushMatrix();
            float f = te.getTickCount() + partialTicks;
            GlStateManager.translate((float)x + 0.5f, (float)y + 0.8f, (float)z + 0.5f);
            GlStateManager.translate(0.0F, 0.1F + MathHelper.sin(f * 0.15F) * 0.01F, 0.0F);
            
            GlStateManager.rotate(-f/50 * (180F / (float)Math.PI), 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(105.0F, 1.0F, 0.0F, 0.0F);
            
            this.bindTexture(TEXTURE_BOOK);
            
            GlStateManager.enableCull();
            this.modelBook.render((Entity)null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0F, 0.08F);
            GlStateManager.popMatrix();
    	}
    }
}
