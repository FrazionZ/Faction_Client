package fz.frazionz.gui.inventory;

import java.io.IOException;

import fz.frazionz.inventory.ContainerTrophyForge;
import fz.frazionz.tileentity.TileEntityTrophyForge;
import io.netty.buffer.Unpooled;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.CPacketCloseWindow;
import net.minecraft.network.play.client.CPacketCustomPayload;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

public class GuiTrophyForge extends GuiContainer
{
    private static final ResourceLocation FORGE_GUI_TEXTURES = new ResourceLocation("textures/gui/frazionz/container/trophy_forge.png");
    private static final ResourceLocation FORGE_GUI_TEXTURES_OVERLAY = new ResourceLocation("textures/gui/frazionz/container/trophy_forge_overlay.png");

    /** The player inventory bound to this GUI. */
    private final InventoryPlayer playerInventory;
    private final ContainerTrophyForge container;
    private final IInventory tileTrophyForge;

    protected int xSize = 256;

    /** The Y size of the inventory window in pixels. */
    protected int ySize = 256;
    
    public GuiTrophyForge(InventoryPlayer playerInv, IInventory tileTrophyForge)
    {
        super(new ContainerTrophyForge(playerInv, tileTrophyForge));
        this.playerInventory = playerInv;
        this.container = (ContainerTrophyForge) this.inventorySlots;
        this.tileTrophyForge = tileTrophyForge;
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
        String s = this.tileTrophyForge.getDisplayName().getUnformattedText();
        String p = this.playerInventory.getDisplayName().getUnformattedText();
        this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2 - 32, -15, 16777215, true);
        this.fontRendererObj.drawString(p, 14, this.ySize - 147 + 2, 16777215, true);
    }

    /**
     * Draws the background layer of this container (behind the items).
     */
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(FORGE_GUI_TEXTURES);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);

        boolean forging = this.tileTrophyForge.getField(2) == 1;
        
        this.mc.getTextureManager().bindTexture(FORGE_GUI_TEXTURES_OVERLAY);
        if (forging)
        {
            int l = this.getCookProgressScaled(188);
            this.drawTexturedModalRect(i + 18, j + 34 + 188-l, 0, 188-l, 18, l);
        }
        
        this.mc.getTextureManager().bindTexture(FORGE_GUI_TEXTURES_OVERLAY);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        String text = "Forger";
        
        if(forging) {
        	text = "En cours...";
        }
        
        if(this.container.canForge() && !forging) {
        	this.drawTexturedModalRect(i + 173, j + 72, 18, 0, 64, 20);
        	this.fontRendererObj.drawString(text, i + 205 - (this.fontRendererObj.getStringWidth(text)/2), j + 72 + 6, 16777215, true);
        }
        else {
        	this.drawTexturedModalRect(i + 173, j + 72, 18, 20, 64, 20);
        	this.fontRendererObj.drawString(text, i + 205 - (this.fontRendererObj.getStringWidth(text)/2), j + 72 + 6, 6710886, true);
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
        
        
        int x = mouseX - (i + 173);
        int y = mouseY - (j + 72);

        if (x >= 0 && y >= 0 && x < 64 && y < 20 && this.container.canForge())
        {
            this.mc.playerController.sendTrophyForgePacket(this.container.windowId);
            this.mc.displayGuiScreen(new GuiTrophyForge(this.playerInventory, this.tileTrophyForge));
        }
    }

    private int getCookProgressScaled(int pixels)
    {
        int i = this.tileTrophyForge.getField(0);
        int j = this.tileTrophyForge.getField(1);
        return j != 0 && i != 0 ? i * pixels / j : 0;
    }
}
