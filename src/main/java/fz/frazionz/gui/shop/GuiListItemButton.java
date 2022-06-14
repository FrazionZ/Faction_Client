package fz.frazionz.gui.shop;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class GuiListItemButton extends GuiButton {
	
    private final ResourceLocation resourceLocation;
    private final int textureX;
    private final int textureY;
    private ItemStack itemToDisplay;

    public GuiListItemButton(ItemStack itemToDisplay, String displayString, int buttonId, int x, int y, int widthIn, int heightIn, int textureX, int textureY, ResourceLocation resourceLocation)
    {
        super(buttonId, x, y, widthIn, heightIn, displayString);
        this.textureX = textureX;
        this.textureY = textureY;
        this.itemToDisplay = itemToDisplay;
        this.resourceLocation = resourceLocation;
    }

    public void drawButton(Minecraft mc, int mouseX, int mouseY, float p_191745_4_)
    {
        if (this.visible)
        {
        	
        	FontRenderer fontrenderer = mc.fontRenderer;
        	this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
            mc.getTextureManager().bindTexture(this.resourceLocation);
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
            
            RenderHelper.enableGUIStandardItemLighting();
            RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
            GlStateManager.enableRescaleNormal();
            renderItem.zLevel = 100F;
            renderItem.renderItemIntoGUI(this.itemToDisplay, this.x + 12, this.y + (this.height / 2) - 8);
            this.drawString(mc.fontRenderer, this.displayString, this.x + 36, this.y + (this.height / 2) - 3, 0xFFFFFFFF);
            RenderHelper.disableStandardItemLighting();
        }
    }
}
