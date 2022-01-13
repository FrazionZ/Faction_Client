package net.minecraft.client.gui.inventory;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerAmeliorator;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntityAmeliorator;
import net.minecraft.util.ResourceLocation;

public class GuiAmeliorator extends GuiContainer
{
    private static final ResourceLocation FURNACE_GUI_TEXTURES = new ResourceLocation("textures/gui/frazionz/container/ameliorator/ameliorator.png");
    private static final ResourceLocation FURNACE_GUI_TEXTURES_OVERLAY = new ResourceLocation("textures/gui/frazionz/container/ameliorator/ameliorator_overlay.png");
    private static final int[] BUBBLELENGTHS = new int[] {200, 188, 176, 163, 150, 132, 115, 100, 83, 68, 49, 33, 0};

    /** The player inventory bound to this GUI. */
    private final InventoryPlayer playerInventory;
    private final IInventory tileFurnace;

    protected int xSize = 256;

    /** The Y size of the inventory window in pixels. */
    protected int ySize = 256;
    
    public GuiAmeliorator(InventoryPlayer playerInv, IInventory furnaceInv)
    {
        super(new ContainerAmeliorator(playerInv, furnaceInv));
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
        this.func_191948_b(mouseX, mouseY);
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        String s = this.tileFurnace.getDisplayName().getUnformattedText();
        String p = this.playerInventory.getDisplayName().getUnformattedText();
        this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2 - 32, -15, 16777215, true);
        this.fontRendererObj.drawString(p, 14, this.ySize - 147 + 2, 16777067, true);
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
                    this.drawTexturedModalRect(i + 8, j + 32 + 200 - j1, 15, 200 - j1, 60, j1);
                }
            }
            
        }
 
        int l = this.getCookProgressScaled(185);
        this.drawTexturedModalRect(i + 226, j + 20 + (200 - l), 0, 0, 14, l);
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
