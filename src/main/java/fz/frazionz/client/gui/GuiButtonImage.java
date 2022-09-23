package fz.frazionz.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.resources.ResourceLocation;

public class GuiButtonImage extends GuiButton
{
    private final ResourceLocation ressourceLocation;
    private final int textureX;
    private final int textureY;
    private final boolean hoverTextureBottom;

    public GuiButtonImage(int buttonId, String displayString, int x, int y, int widthIn, int heightIn, int textureX, int textureY, boolean hoverTextureBottom, ResourceLocation ressourceLocation)
    {
        super(buttonId, x, y, widthIn, heightIn, displayString);
        this.textureX = textureX;
        this.textureY = textureY;
        this.hoverTextureBottom = hoverTextureBottom;
        this.ressourceLocation = ressourceLocation;
    }

    public void setPosition(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTick)
    {
        if (this.visible)
        {
            this.hovered = this.x <= mouseX && mouseX <= this.x + this.width && this.y <= mouseY && mouseY <= this.y + this.height;
            mc.getTextureManager().bindTexture(this.ressourceLocation);
            GlStateManager.disableDepth();
            int i = this.textureX;
            int j = this.textureY;

            if (this.hovered)
            {
            	if(hoverTextureBottom)
            		j += this.height;
            	else
            		i += this.width;
            }

            GlStateManager.enableBlend();
            this.drawModalRectWithCustomSizedTexture(this.x, this.y, i, j, this.width, this.height, 512.0F, 512.0F);
            mc.fontRenderer.drawString(this.displayString, this.x + (this.width - mc.fontRenderer.getStringWidth(this.displayString)) / 2, this.y + this.height/2 - 3, 0xFFFFFFFF, true);
            GlStateManager.enableDepth();
        }
    }
}
