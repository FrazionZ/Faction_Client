package fz.frazionz.client.renderer.entity.layers;

import fz.frazionz.client.model.entity.GrimoireModel;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EnumPlayerModelParts;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;

public class LayerGrimoire implements LayerRenderer<AbstractClientPlayer>
{
    private final RenderPlayer playerRenderer;

    public LayerGrimoire(RenderPlayer playerRendererIn)
    {
        this.playerRenderer = playerRendererIn;
    }

    public void doRenderLayer(AbstractClientPlayer entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        if (entitylivingbaseIn.hasPlayerInfo() && entitylivingbaseIn.hasGrimoire())
        {
            GlStateManager.pushMatrix();
            float f = entitylivingbaseIn.getTickCount() + partialTicks;
            GlStateManager.translate(0.0f, -1.4f, 0.0f);
            GlStateManager.translate(0.0F, 0.1F + MathHelper.sin(f * 0.15F) * 0.01F, 0.0F);
            
            GlStateManager.rotate(-f/50 * (180F / (float)Math.PI), 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(-65.0F, 1.0F, 0.0F, 0.0F);
            
            this.playerRenderer.bindTexture(GrimoireModel.TEXTURE_BOOK);
            GlStateManager.enableCull();
            this.playerRenderer.getMainModel().renderGrimoire(scale*1.35f);
            GlStateManager.popMatrix();
        }
    }

    public boolean shouldCombineTextures()
    {
        return false;
    }
}