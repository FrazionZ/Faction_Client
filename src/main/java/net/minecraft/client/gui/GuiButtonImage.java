package net.minecraft.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class GuiButtonImage extends GuiButton
{
    private final ResourceLocation resourceLocation;
    private final int xTexStart;
    private final int yTexStart;
    private final int yDiffText;

    public GuiButtonImage(int buttonId, int xIn, int yIn, int widthIn, int heightIn, int textureOffestX, int textureOffestY, int yDiffText, ResourceLocation resource)
    {
        super(buttonId, xIn, yIn, widthIn, heightIn, "");
        this.xTexStart = textureOffestX;
        this.yTexStart = textureOffestY;
        this.yDiffText = yDiffText;
        this.resourceLocation = resource;
    }

    public void setPosition(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * Draws this button to the screen.
     */
    public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks)
    {
        if (this.visible)
        {
            this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
            mc.getTextureManager().bindTexture(this.resourceLocation);
            GlStateManager.disableDepth();
            int i = this.xTexStart;
            int j = this.yTexStart;

            if (this.hovered)
            {
                j += this.yDiffText;
            }

            this.drawTexturedModalRect(this.x, this.y, i, j, this.width, this.height);
            GlStateManager.enableDepth();
        }
    }
}
