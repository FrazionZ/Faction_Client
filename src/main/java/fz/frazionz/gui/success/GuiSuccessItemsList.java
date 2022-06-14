package fz.frazionz.gui.success;

import fz.frazionz.api.gsonObj.ShopItem;
import fz.frazionz.api.gsonObj.ShopType;
import fz.frazionz.api.gsonObj.SuccessObj;
import fz.frazionz.api.gsonObj.SuccessType;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fz.frazionz.utils.FzUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

public class GuiSuccessItemsList extends GuiListExtended
{
    private final Minecraft mc;
    private final GuiListExtended.IGuiListEntry[] listEntries;
    private int maxListLabelWidth;
    private SuccessType type;
    private GuiScreen lastScreen;

    public GuiSuccessItemsList(GuiScreen lastScreen, SuccessType type, Minecraft mcIn, int left, int right, int top, int bottom, int slotHeight, int spaceBetween)
    {
        super(mcIn, left, right, top, bottom, slotHeight, spaceBetween);
        this.mc = mcIn;
        this.type = type;
        this.lastScreen = lastScreen;
        this.listEntries = new GuiListExtended.IGuiListEntry[type.getItems().size()];
        int i = 0;

        for (SuccessObj item : type.getItems())
        {
            this.listEntries[i++] = new GuiSuccessItemsList.SuccessEntry(item);
        }
    }

    protected int getSize()
    {
        return this.listEntries.length;
    }

    /**
     * Gets the IGuiListEntry object for the given index
     */
    public GuiListExtended.IGuiListEntry getListEntry(int index)
    {
        return this.listEntries[index];
    }

    protected int getScrollBarX()
    {
        return super.getScrollBarX();
    }

    /**
     * Gets the width of the list
     */
    @Override
    public int getListWidth()
    {
        return 299;
    }

    @Override
    protected void elementClicked(int slotIndex, boolean isDoubleClick, int mouseX, int mouseY)
    {
        SuccessObj item = ((GuiSuccessItemsList.SuccessEntry)this.listEntries[slotIndex]).getSuccessObj();
        GuiButton guiButton = ((GuiSuccessItemsList.SuccessEntry)this.listEntries[slotIndex]).getSuccessItemButton();
        guiButton.playPressSound(this.mc.getSoundHandler());
        this.mc.displayGuiScreen(new GuiSuccessItem(this.lastScreen, this.mc, item));
    }

    @Override
    public void drawScreen(int mouseXIn, int mouseYIn, float partialTicks)
    {
        if (this.visible)
        {
            this.mouseX = mouseXIn;
            this.mouseY = mouseYIn;
            this.drawBackground();
            int i = this.getScrollBarX();
            int j = i + 6;
            this.bindAmountScrolled();
            GlStateManager.disableLighting();
            GlStateManager.disableFog();
            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder bufferbuilder = tessellator.getBuffer();
            this.drawContainerBackground(tessellator);

            int k = this.left + this.width / 2 - this.getListWidth() / 2 + 2;
            int l = this.top - (int)this.amountScrolled;

            if (this.hasListHeader)
            {
                this.drawListHeader(k, l, tessellator);
            }

            this.drawSelectionBox(k, l, mouseXIn, mouseYIn, partialTicks);
            GlStateManager.disableDepth();
            this.overlayBackground(0, this.top, 255, 255);
            this.overlayBackground(this.bottom, this.height, 255, 255);
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ZERO, GlStateManager.DestFactor.ONE);
            GlStateManager.disableAlpha();
            GlStateManager.shadeModel(7425);

            int j1 = this.getMaxScroll();

            if (j1 > 0)
            {
                int k1 = (this.bottom - this.top) * (this.bottom - this.top) / this.getContentHeight();
                k1 = MathHelper.clamp(k1, 32, this.bottom - this.top - 8);
                int l1 = (int)this.amountScrolled * (this.bottom - this.top - k1) / j1 + this.top;

                if (l1 < this.top)
                {
                    l1 = this.top;
                }
                this.drawRoundedSliderButton(i, this.top, j, this.bottom, 0xFF101010);
                this.drawRoundedSliderButton(i, l1, j, (l1 + k1), 0xFFFEA801);
            }

            GlStateManager.enableTexture2D();
            GlStateManager.shadeModel(7424);
            GlStateManager.enableAlpha();
            GlStateManager.disableBlend();
        }
    }

    public class SuccessEntry implements GuiListExtended.IGuiListEntry
    {
        private final SuccessObj item;
        private final GuiButton successItemButton;
        private final ResourceLocation background = new ResourceLocation("textures/gui/frazionz/interface_background.png");

        private SuccessEntry(SuccessObj item)
        {
            this.item = item;
            this.successItemButton = new GuiSuccessItemsButton(item, 0, 0, 0, 299, 36, 0, 239, this.background);
        }

        public void drawEntry(int slotIndex, int x, int y, int listWidth, int slotHeight, int mouseX, int mouseY, boolean isSelected, float partialTicks)
        {
            this.successItemButton.x = x - 3;
            this.successItemButton.y = y;
            this.successItemButton.drawButton(GuiSuccessItemsList.this.mc, mouseX, mouseY, partialTicks);
        }

        public boolean mousePressed(int slotIndex, int mouseX, int mouseY, int mouseEvent, int relativeX, int relativeY)
        {
            if(this.successItemButton.mousePressed(GuiSuccessItemsList.this.mc, mouseX, mouseY)) {

                return true;
            }
            return false;
        }

        public void mouseReleased(int slotIndex, int x, int y, int mouseEvent, int relativeX, int relativeY)
        {
            this.successItemButton.mouseReleased(x, y);
        }

		public void updatePosition(int slotIndex, int x, int y, float partialTicks) {
		}

        public SuccessObj getSuccessObj() {
            return item;
        }

        public GuiButton getSuccessItemButton() {
            return successItemButton;
        }
    }

    public class GuiSuccessItemsButton extends GuiButton {

        private final ResourceLocation resourceLocation;
        private final int textureX;
        private final int textureY;
        private final SuccessObj successObj;

        public GuiSuccessItemsButton(SuccessObj successObj, int buttonId, int x, int y, int widthIn, int heightIn, int textureX, int textureY, ResourceLocation resourceLocation)
        {
            super(buttonId, x, y, widthIn, heightIn, successObj.getTitle());
            this.successObj = successObj;
            this.textureX = textureX;
            this.textureY = textureY;
            this.resourceLocation = resourceLocation;
        }

        public void drawButton(Minecraft mc, int mouseX, int mouseY, float p_191745_4_)
        {
            if (this.visible)
            {

                RenderHelper.enableGUIStandardItemLighting();

                GlStateManager.enableBlend();
                GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
                GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);

                this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;

                mc.getTextureManager().bindTexture(this.resourceLocation);
                GlStateManager.disableDepth();
                int i = this.textureX;
                int j = this.textureY;
                if (this.hovered)
                    j += this.height;
                this.drawModalRectWithCustomSizedTexture(this.x, this.y, i, j, this.width, this.height, 512.0F, 512.0F);

                GlStateManager.enableDepth();

                mc.fontRenderer.drawScaleString(this.displayString, this.x + 12, this.y + (this.height / 2) - 8, 1.2F, new Color(225, 121, 6));
                mc.fontRenderer.drawScaleString(successObj.getDescription(), this.x + 12, this.y + (this.height / 2) + 3, 0.8F, new Color(225, 255, 255));

                /*if(successObj.getImgMinia() != null){
                    GL11.glBindTexture(GL11.GL_TEXTURE_2D, successObj.getImgMinia().getGlTextureId());
                    drawModalRectWithCustomSizedTexture(this.xPosition + 26, this.yPosition + (this.height / 2) - 8, 0.0F, 0.0F, 64, 32, 64, 32);
                }*/

            }
        }
    }
}
