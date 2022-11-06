package fz.frazionz.client.gui.inventory;

import java.io.IOException;

import fz.frazionz.inventory.ContainerTrophyForge;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.resources.ResourceLocation;

public class GuiTrophyForge extends GuiContainer
{
    private static final ResourceLocation FORGE_GUI_TEXTURES = new ResourceLocation("frazionz", "textures/gui/container/trophy_forge/trophy_forge.png");
    private static final ResourceLocation FORGE_GUI_TEXTURES_OVERLAY = new ResourceLocation("frazionz", "textures/gui/container/trophy_forge/trophy_forge_overlay.png");

    /** The player inventory bound to this GUI. */
    private final InventoryPlayer playerInventory;
    private final ContainerTrophyForge container;
    private final IInventory tileTrophyForge;
    
    public GuiTrophyForge(InventoryPlayer playerInv, IInventory tileTrophyForge)
    {
        super(new ContainerTrophyForge(playerInv, tileTrophyForge));
        this.playerInventory = playerInv;
        this.container = (ContainerTrophyForge) this.inventorySlots;
        this.tileTrophyForge = tileTrophyForge;

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
        this.fontRenderer.drawString(p, 44, this.ySize - 96 + 2, 6710886, true);
    }

    /**
     * Draws the background layer of this container (behind the items).
     */
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(FORGE_GUI_TEXTURES);
        int i = this.guiLeft;
        int j = this.guiTop;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);

        boolean forging = this.tileTrophyForge.getField(2) == 1;
        
        this.mc.getTextureManager().bindTexture(FORGE_GUI_TEXTURES_OVERLAY);
        if (forging)
        {
            int l = this.getCookProgressScaled(184);
            this.drawTexturedModalRect(i + 13, j + 8 + 184-l, 0, 184-l, 14, l);
        }
        
        this.mc.getTextureManager().bindTexture(FORGE_GUI_TEXTURES_OVERLAY);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        String text = "Forger";
        
        if(forging) {
        	text = "En cours...";
        }
        
        if(this.container.canForge() && !forging) {
        	this.drawTexturedModalRect(i + 171, j + 66, 14, 0, 62, 18);
        	this.fontRenderer.drawString(text, i + 202 - (this.fontRenderer.getStringWidth(text)/2), j + 66 + 5, 16777215, true);
        }
        else {
        	this.drawTexturedModalRect(i + 171, j + 66, 14, 18, 62, 18);
        	this.fontRenderer.drawString(text, i + 202 - (this.fontRenderer.getStringWidth(text)/2), j + 66 + 5, 6710886, true);
        }
    }
    
    /**
     * Called when the mouse is clicked. Args : mouseX, mouseY, clickedButton
     */
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
    {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        
        
        int x = mouseX - (i + 171);
        int y = mouseY - (j + 66);

        if (x >= 0 && y >= 0 && x < 66 && y < 18 && this.container.canForge())
        {
            this.mc.playerController.sendStartMachinePacket(this.container.windowId);
            this.tileTrophyForge.setField(2, 1);
        }
    }

    private int getCookProgressScaled(int pixels)
    {
        int i = this.tileTrophyForge.getField(0);
        int j = this.tileTrophyForge.getField(1);
        return j != 0 && i != 0 ? i * pixels / j : 0;
    }
}
