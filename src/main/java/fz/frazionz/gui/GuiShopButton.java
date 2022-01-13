package fz.frazionz.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class GuiShopButton extends GuiButton {
	
    private final ResourceLocation resourceLocation;
    private final int textureX;
    private final int textureY;
    private ItemStack itemToDisplay;

    public GuiShopButton(ItemStack itemToDisplay, String displayString, int buttonId, int x, int y, int widthIn, int heightIn, int textureX, int textureY, ResourceLocation resourceLocation)
    {
        super(buttonId, x, y, widthIn, heightIn, displayString);
        this.textureX = textureX;
        this.textureY = textureY;
        this.itemToDisplay = itemToDisplay;
        this.resourceLocation = resourceLocation;
    }

    public void func_191745_a(Minecraft mc, int mouseX, int mouseY, float p_191745_4_)
    {
        if (this.visible)
        {
        	
            RenderHelper.enableGUIStandardItemLighting();
        	
        	GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            
            
        	FontRenderer fontrenderer = mc.fontRendererObj;
        	this.hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
            mc.getTextureManager().bindTexture(this.resourceLocation);
            GlStateManager.disableDepth();
            int i = this.textureX;
            int j = this.textureY;

            if (this.hovered)
            {
                j += this.height;
            }

            this.drawModalRectWithCustomSizedTexture(this.xPosition, this.yPosition, i, j, this.width, this.height,  512.0F, 512.0F);
            
            int k = 14737632;

            if (!this.enabled)
            {
                k = 10526880;
            }
            else if (this.hovered)
            {
                k = 16777120;
            }
            
            GlStateManager.enableDepth();
            
            RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
            GlStateManager.enableRescaleNormal();
            renderItem.zLevel = 100F;
            renderItem.renderItemIntoGUI(this.itemToDisplay, this.xPosition + 12, this.yPosition + (this.height / 2) - 8);
            this.drawString(mc.fontRendererObj, this.displayString, this.xPosition + 36, this.yPosition + (this.height / 2) - 3, 0xFFFFFFFF);
            
        }
    }
}
