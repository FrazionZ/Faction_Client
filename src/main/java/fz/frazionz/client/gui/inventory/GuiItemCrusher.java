package fz.frazionz.client.gui.inventory;

import java.io.IOException;

import fz.frazionz.inventory.ContainerItemCrusher;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.resources.ResourceLocation;
import org.lwjgl.Sys;

public class GuiItemCrusher extends GuiContainer
{
    private static final ResourceLocation TEXTURE = new ResourceLocation("frazionz", "textures/gui/container/item_crusher/item_crusher.png");
    private static final ResourceLocation OVERLAY = new ResourceLocation("frazionz", "textures/gui/container/item_crusher/item_crusher_overlay.png");

    /** The player inventory bound to this GUI. */
    private final InventoryPlayer playerInventory;
    private final ContainerItemCrusher container;
    private final IInventory tileEntity;
    
    public GuiItemCrusher(InventoryPlayer playerInv, IInventory tileEntity)
    {
        super(new ContainerItemCrusher(playerInv, tileEntity));
        this.playerInventory = playerInv;
        this.container = (ContainerItemCrusher) this.inventorySlots;
        this.tileEntity = tileEntity;

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
        this.mc.getTextureManager().bindTexture(TEXTURE);
        int i = this.guiLeft;
        int j = this.guiTop;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);

        boolean forging = this.tileEntity.getField(2) == 1;
        
        this.mc.getTextureManager().bindTexture(OVERLAY);
        if (forging)
        {
            int l = this.getCookProgressScaled(184);
            this.drawTexturedModalRect(i + 15, j + 8 + 184-l, 0, 184-l, 14, l);
        }
        
        this.mc.getTextureManager().bindTexture(OVERLAY);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        String text = "Crush";
        
        if(forging) {
        	text = "En cours...";
        }
        
        if(this.container.canCrush() && !forging) {
        	this.drawTexturedModalRect(i + 169, j + 50, 14, 0, 61, 18);
        	this.fontRenderer.drawString(text, i + 200 - (this.fontRenderer.getStringWidth(text)/2), j + 50 + 5, 16777215, true);
        }
        else {
        	this.drawTexturedModalRect(i + 169, j + 50, 14, 18, 61, 18);
        	this.fontRenderer.drawString(text, i + 200 - (this.fontRenderer.getStringWidth(text)/2), j + 50 + 5, 6710886, true);
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
        
        
        int x = mouseX - (i + 169);
        int y = mouseY - (j + 50);

        if (x >= 0 && y >= 0 && x < 61 && y < 18 && this.container.canCrush())
        {
            this.mc.playerController.sendStartMachinePacket(this.container.windowId);
            this.tileEntity.setField(2, 1);
        }
    }

    private int getCookProgressScaled(int pixels)
    {
        int i = this.tileEntity.getField(0);
        int j = this.tileEntity.getField(1);
        return j != 0 && i != 0 ? i * pixels / j : 0;
    }
}
