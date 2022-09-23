package fz.frazionz.client.gui.inventory;

import fz.frazionz.inventory.ContainerAmeliorator;
import fz.frazionz.tileentity.TileEntityAmeliorator;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.resources.ResourceLocation;

public class GuiAmeliorator extends GuiContainer
{
    private static final ResourceLocation FURNACE_GUI_TEXTURES = new ResourceLocation("textures/gui/frazionz/container/ameliorator/ameliorator.png");
    private static final ResourceLocation FURNACE_GUI_TEXTURES_OVERLAY = new ResourceLocation("textures/gui/frazionz/container/ameliorator/ameliorator_overlay.png");
    private static final int[] BUBBLELENGTHS = new int[] {200, 188, 176, 163, 150, 132, 115, 100, 83, 68, 49, 33, 0};

    /** The player inventory bound to this GUI. */
    private final InventoryPlayer playerInventory;
    private final IInventory tileFurnace;
    
    public GuiAmeliorator(InventoryPlayer playerInv, IInventory furnaceInv)
    {
        super(new ContainerAmeliorator(playerInv, furnaceInv));
        this.playerInventory = playerInv;
        this.tileFurnace = furnaceInv;
        this.xSize = 246;
        this.ySize = 200;
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
        this.fontRenderer.drawString(p, 44, this.ySize - 96 + 2, 16777215, true);
    }

    /**
     * Draws the background layer of this container (behind the items).
     */
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(FURNACE_GUI_TEXTURES);
        int i = this.guiLeft;
        int j = this.guiTop;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);

        
        this.mc.getTextureManager().bindTexture(FURNACE_GUI_TEXTURES_OVERLAY);
        
        if (TileEntityAmeliorator.isBurning(this.tileFurnace))
        {
            
            int i1 = this.tileFurnace.getField(0);

            if (i1 > 0)
            {
                int j1 = (int)(28.0F * (1.0F - (float)i1 / 400.0F));

                j1 = BUBBLELENGTHS[i1 / 2 % 13];

                if (j1 > 0)
                {
                    this.drawTexturedModalRect(i + 3, j + 4 + 200 - j1, 15, 200 - j1, 60, j1);
                }
            }
            
        }
 
        int l = this.getCookProgressScaled(185);
        this.drawTexturedModalRect(i + 221, j + 8 + (184 - l), 0, 184 - l, 14, l);
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
