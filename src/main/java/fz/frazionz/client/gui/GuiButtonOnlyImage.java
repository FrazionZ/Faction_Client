package fz.frazionz.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.resources.ResourceLocation;

public class GuiButtonOnlyImage extends GuiButton
{
    private final ResourceLocation ressourceLocation;
    private final int textureX;
    private final int textureY;
    private final int field_191749_r;

    public GuiButtonOnlyImage(int buttonId, int x, int y, int widthIn, int heightIn, int textureX, int textureY, int hoverY, ResourceLocation ressourceLocation)
    {
        super(buttonId, x, y, widthIn, heightIn, "");
        this.textureX = textureX;
        this.textureY = textureY;
        this.field_191749_r = hoverY;
        this.ressourceLocation = ressourceLocation;
    }

    public void setPosition(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks)
    {
        if (this.visible)
        {
            this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
            mc.getTextureManager().bindTexture(this.ressourceLocation);
            GlStateManager.disableDepth();
            int i = this.textureX;
            int j = this.textureY;

            if (this.hovered)
            {
                j += this.field_191749_r;
            }

            GlStateManager.enableBlend();
            this.drawModalRectWithCustomSizedTexture(this.x, this.y, i, j, this.width, this.height, 512.0F, 512.0F);
            GlStateManager.enableDepth();
        }
    }
}
