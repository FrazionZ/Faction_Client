package net.minecraft.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class GuiButtonImage extends GuiButton
{
    private final ResourceLocation ressourceLocation;
    private final int textureX;
    private final int textureY;
    private final int field_191749_r;

    public GuiButtonImage(int buttonId, int x, int y, int widthIn, int heightIn, int textureX, int textureY, int p_i47392_8_, ResourceLocation ressourceLocation)
    {
        super(buttonId, x, y, widthIn, heightIn, "");
        this.textureX = textureX;
        this.textureY = textureY;
        this.field_191749_r = p_i47392_8_;
        this.ressourceLocation = ressourceLocation;
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
            this.hovered = p_191745_2_ >= this.xPosition && p_191745_3_ >= this.yPosition && p_191745_2_ < this.xPosition + this.width && p_191745_3_ < this.yPosition + this.height;
            p_191745_1_.getTextureManager().bindTexture(this.ressourceLocation);
            GlStateManager.disableDepth();
            int i = this.textureX;
            int j = this.textureY;

            if (this.hovered)
            {
                j += this.field_191749_r;
            }

            this.drawTexturedModalRect(this.xPosition, this.yPosition, i, j, this.width, this.height);
            GlStateManager.enableDepth();
        }
    }
}
