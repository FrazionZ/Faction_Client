package fz.frazionz.gui.inventory;

import fz.frazionz.inventory.ContainerFrazionFurnace;
import fz.frazionz.tileentity.TileEntityFrazionFurnace;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class GuiFrazionFurnace extends GuiContainer
{
    private static final ResourceLocation FURNACE_GUI_TEXTURES = new ResourceLocation("textures/gui/frazionz/container/furnace/frazion_furnace.png");

    /** The player inventory bound to this GUI. */
    private final InventoryPlayer playerInventory;
    private final IInventory tileFurnace;

    public GuiFrazionFurnace(InventoryPlayer playerInv, IInventory furnaceInv)
    {
        super(new ContainerFrazionFurnace(playerInv, furnaceInv));
        this.playerInventory = playerInv;
        this.tileFurnace = furnaceInv;
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
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        String p = this.playerInventory.getDisplayName().getUnformattedText();
        this.fontRenderer.drawString(p, this.xSize / 2 - this.fontRenderer.getStringWidth(p) / 2, this.ySize - 96 + 2, 16777215, true);
    }

    /**
     * Draws the background layer of this container (behind the items).
     */
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(FURNACE_GUI_TEXTURES);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);

        if (TileEntityFrazionFurnace.isBurning(this.tileFurnace))
        {
            int k = this.getBurnLeftScaled(13);
            this.drawTexturedModalRect(i + 6, j + 43 + 12 - k, 186, 14 - k, 14, k + 1);
        }

        int l = this.getCookProgressScaled(24);
        this.drawTexturedModalRect(i + 84, j + 33, 186, 14, l + 1, 16);
    }

    private int getCookProgressScaled(int pixels)
    {
        int i = this.tileFurnace.getField(2);
        int j = this.tileFurnace.getField(3);
        return j != 0 && i != 0 ? i * pixels / j : 0;
    }

    private int getBurnLeftScaled(int pixels)
    {
        int i = this.tileFurnace.getField(1);

        if (i == 0)
        {
            i = 200;
        }

        return this.tileFurnace.getField(0) * pixels / i;
    }
}
