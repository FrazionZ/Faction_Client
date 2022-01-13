package net.minecraft.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class BuiButtonImageItem extends GuiButton
{
    private final ResourceLocation field_191750_o;
    private final int field_191747_p;
    private final int field_191748_q;
    private final int field_191749_r;
    private ItemStack itemToDisplay;
    public String displayString;
    public String displayPrice;

    public BuiButtonImageItem(ItemStack itemToDisplay, int p_i47392_1_, int p_i47392_2_, int p_i47392_3_, int p_i47392_4_, int p_i47392_5_, int p_i47392_6_, int p_i47392_7_, int p_i47392_8_, ResourceLocation p_i47392_9_, String buttonText, String displayPrice)
    {
        super(p_i47392_1_, p_i47392_2_, p_i47392_3_, p_i47392_4_, p_i47392_5_, "");
        this.field_191747_p = p_i47392_6_;
        this.field_191748_q = p_i47392_7_;
        this.field_191749_r = p_i47392_8_;
        this.field_191750_o = p_i47392_9_;
        this.itemToDisplay = itemToDisplay;
        this.displayString = buttonText;
        this.displayPrice = displayPrice;
    }
    
    public BuiButtonImageItem(ItemStack itemToDisplay, int p_i47392_1_, int p_i47392_2_, int p_i47392_3_, int p_i47392_4_, int p_i47392_5_, int p_i47392_6_, int p_i47392_7_, int p_i47392_8_, ResourceLocation p_i47392_9_, String buttonText)
    {
        super(p_i47392_1_, p_i47392_2_, p_i47392_3_, p_i47392_4_, p_i47392_5_, "");
        this.field_191747_p = p_i47392_6_;
        this.field_191748_q = p_i47392_7_;
        this.field_191749_r = p_i47392_8_;
        this.field_191750_o = p_i47392_9_;
        this.itemToDisplay = itemToDisplay;
        this.displayString = buttonText;
        this.displayPrice = "";
    }

    public void func_191746_c(int p_191746_1_, int p_191746_2_)
    {
        this.xPosition = p_191746_1_;
        this.yPosition = p_191746_2_;
    }

    public void func_191745_a(Minecraft p_191745_1_, int p_191745_2_, int p_191745_3_, float p_191745_4_)
    {
        if (this.visible)
        {
        	
            RenderHelper.enableGUIStandardItemLighting();
        	
        	GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            
            
        	FontRenderer fontrenderer = p_191745_1_.fontRendererObj;
        	this.hovered = p_191745_2_ >= this.xPosition && p_191745_3_ >= this.yPosition && p_191745_2_ < this.xPosition + this.width && p_191745_3_ < this.yPosition + this.height;
            p_191745_1_.getTextureManager().bindTexture(this.field_191750_o);
            GlStateManager.disableDepth();
            int i = this.field_191747_p;
            int j = this.field_191748_q;

            if (this.hovered)
            {
                j += this.field_191749_r;
            }

            this.drawTexturedModalRect(this.xPosition, this.yPosition, i, j, this.width, this.height);
            
            int k = 14737632;

            if (!this.enabled)
            {
                k = 10526880;
            }
            else if (this.hovered)
            {
                k = 16777120;
            }
            
            this.drawCenteredString(fontrenderer, this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height + 4), k);
            this.drawCenteredString(fontrenderer, this.displayPrice, this.xPosition + this.width / 2, this.yPosition + (this.height + 18), k);
            GlStateManager.enableDepth();
            
            RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
            GlStateManager.enableRescaleNormal();
            renderItem.zLevel = 100F;
            renderItem.renderItemIntoGUI(itemToDisplay, this.xPosition + (this.width / 2) - 8, this.yPosition + (this.height / 2) - 8);
            
        }
    }
}
