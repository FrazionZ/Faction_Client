package fz.frazionz.client.gui.inventory;

import fz.frazionz.inventory.ContainerHdvChest;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.IInventory;
import net.minecraft.resources.ResourceLocation;

public class GuiHdvChest extends GuiContainer
{
    /** The ResourceLocation containing the chest GUI texture. */
    private static final ResourceLocation CHEST_GUI_TEXTURE = new ResourceLocation("textures/gui/frazionz/container/hdv_gui.png");
    private final IInventory upperChestInventory;
    private final IInventory lowerChestInventory;

    /**
     * window height is calculated with these values; the more rows, the heigher
     */
    private final int inventoryRows;

    public GuiHdvChest(IInventory upperInv, IInventory lowerInv)
    {
        super(new ContainerHdvChest(upperInv, lowerInv, Minecraft.getMinecraft().player));
        this.upperChestInventory = upperInv;
        this.lowerChestInventory = lowerInv;
        this.allowUserInput = false;
        this.inventoryRows = lowerInv.getSizeInventory() / 9;
        this.ySize = 114 + this.inventoryRows * 18;
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */

    /**
     * Draws the background layer of this container (behind the items).
     */
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(CHEST_GUI_TEXTURE);
        int i = (this.width - 210) / 2;
        int j = (this.height - 256) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, 210, 256);
    }
}
