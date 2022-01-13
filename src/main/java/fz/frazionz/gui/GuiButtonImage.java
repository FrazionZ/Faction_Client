package fz.frazionz.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

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

    public void func_191746_c(int p_191746_1_, int p_191746_2_)
    {
        this.xPosition = p_191746_1_;
        this.yPosition = p_191746_2_;
    }

    public void func_191745_a(Minecraft mc, int mouseX, int mouseY, float partialTick)
    {
        if (this.visible)
        {
            this.hovered = this.xPosition <= mouseX && mouseX <= this.xPosition + this.width && this.yPosition <= mouseY && mouseY <= this.yPosition + this.height;
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
            this.drawModalRectWithCustomSizedTexture(this.xPosition, this.yPosition, i, j, this.width, this.height, 512.0F, 512.0F);
            mc.fontRendererObj.drawString(this.displayString, this.xPosition + (this.width - mc.fontRendererObj.getStringWidth(this.displayString)) / 2, this.yPosition + this.height/2 - 3, 0xFFFFFFFF, true);
            GlStateManager.enableDepth();
        }
    }
}
