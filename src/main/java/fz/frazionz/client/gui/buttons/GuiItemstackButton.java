package fz.frazionz.client.gui.buttons;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.item.ItemStack;

public class GuiItemstackButton extends GuiHoverButton {

    private ItemStack itemToDisplay;

    public GuiItemstackButton(int buttonId, ItemStack itemToDisplay, String displayString, int x, int y, int width, int height)
    {
        super(buttonId, x, y, width, height, displayString);
        this.itemToDisplay = itemToDisplay;
    }

    @Override
    protected boolean drawString() {
        return false;
    }

    public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks)
    {
        super.drawButton(mc, mouseX, mouseY, partialTicks);
        if (this.visible)
        {
            RenderHelper.enableGUIStandardItemLighting();
            RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
            GlStateManager.enableRescaleNormal();
            renderItem.zLevel = 100F;
            renderItem.renderItemIntoGUI(this.itemToDisplay, this.x + 8, this.y + (this.height / 2) - 8);
            this.drawString(mc.fontRenderer, this.displayString, this.x + 32, this.y + (this.height / 2) - 3, 0xFFFFFFFF);
            RenderHelper.disableStandardItemLighting();
        }
    }
}
