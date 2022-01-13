package net.minecraft.client.gui.inventory;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerBauxiteBrewingStand;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class GuiBauxiteBrewingStand extends GuiContainer
{
    private static final ResourceLocation BREWING_STAND_GUI_TEXTURES = new ResourceLocation("textures/gui/container/new_brewing_stand.png");
    private static final int[] BUBBLELENGTHS = new int[] {29, 24, 20, 16, 11, 6, 0};

    /** The player inventory bound to this GUI. */
    private final InventoryPlayer playerInventory;
    private final IInventory tileBrewingStand;

    public GuiBauxiteBrewingStand(InventoryPlayer playerInv, IInventory p_i45506_2_)
    {
        super(new ContainerBauxiteBrewingStand(playerInv, p_i45506_2_));
        this.playerInventory = playerInv;
        this.tileBrewingStand = p_i45506_2_;
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
        String s = "Potion";
        String p = this.playerInventory.getDisplayName().getUnformattedText();
        this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 16777067, true);
        this.fontRendererObj.drawString(p, 11, this.ySize - 118 + 2, 16777067, true);
    }

    /**
     * Draws the background layer of this container (behind the items).
     */
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(BREWING_STAND_GUI_TEXTURES);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);
        int k = this.tileBrewingStand.getField(1);
        int l = MathHelper.clamp((18 * k + 20 - 1) / 20, 0, 18);

        if (l > 0)
        {
            this.drawTexturedModalRect(i + 65, j + 66, 186, 29, l, 4);
        }

        int i1 = this.tileBrewingStand.getField(0);

        if (i1 > 0)
        {
            int j1 = (int)(28.0F * (1.0F - (float)i1 / 200.0F));

            if (j1 > 0)
            {
                this.drawTexturedModalRect(i + 102, j + 38, 186, 0, 9, j1);
            }

            j1 = BUBBLELENGTHS[i1 / 2 % 7];

            if (j1 > 0)
            {
                this.drawTexturedModalRect(i + 68, j + 36 + 29 - j1, 195, 29 - j1, 12, j1);
            }
        }
    }
}
