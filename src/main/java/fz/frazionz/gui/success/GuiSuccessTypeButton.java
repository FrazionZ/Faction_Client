package fz.frazionz.gui.success;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class GuiSuccessTypeButton extends GuiButton {

    private final ResourceLocation resourceLocation;
    private final int textureX;
    private final int textureY;

    public GuiSuccessTypeButton(String displayString, int buttonId, int x, int y, int widthIn, int heightIn, int textureX, int textureY, ResourceLocation resourceLocation)
    {
        super(buttonId, x, y, widthIn, heightIn, displayString);
        this.textureX = textureX;
        this.textureY = textureY;
        this.resourceLocation = resourceLocation;
    }

    public void drawButton(Minecraft mc, int mouseX, int mouseY, float p_191745_4_)
    {
        if (this.visible)
        {
        	
            RenderHelper.enableGUIStandardItemLighting();
        	
        	GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            
            
        	FontRenderer fontrenderer = mc.fontRenderer;
        	this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
            mc.getTextureManager().bindTexture(this.resourceLocation);
            GlStateManager.disableDepth();
            int i = this.textureX;
            int j = this.textureY;

            if (this.hovered)
            {
                j += this.height;
            }

            this.drawModalRectWithCustomSizedTexture(this.x, this.y, i, j, this.width, this.height,  512.0F, 512.0F);
            
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
            this.drawString(mc.fontRenderer, this.displayString, this.x + 12, this.y + (this.height / 2) - 3, 0xFFFFFFFF);
            
        }
    }
}
