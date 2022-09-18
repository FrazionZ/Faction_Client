package fz.frazionz.gui.success;

import fz.frazionz.api.data.ShopAPIDataStocker;
import fz.frazionz.api.data.SuccessAPIDataStocker;
import fz.frazionz.api.gsonObj.MarketType;
import fz.frazionz.api.gsonObj.SuccessType;
import fz.frazionz.gui.GuiButtonOnlyImage;
import fz.frazionz.gui.shop.GuiListItemButton;
import fz.frazionz.gui.shop.GuiShopCategoryList;
import fz.frazionz.gui.shop.GuiShopItems;
import fz.frazionz.utils.MathUtils;
import fz.frazionz.utils.data.FzUserData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.advancements.GuiScreenAdvancements;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.math.MathHelper;

import java.io.IOException;

public class GuiSuccessTypeList extends GuiListExtended
{
    private final Minecraft mc;
    private final GuiListExtended.IGuiListEntry[] listEntries;
    private GuiScreen lastScreen;

    public GuiSuccessTypeList(GuiScreen lastScreen, Minecraft mcIn, int left, int right, int top, int bottom, int slotHeight, int spaceBetween)
    {
        super(mcIn, left, right, top, bottom, slotHeight, spaceBetween);
        this.mc = mcIn;
        this.lastScreen = lastScreen;
        this.listEntries = new GuiListExtended.IGuiListEntry[SuccessAPIDataStocker.successTypes.size()];
        int i = 0;

        for (SuccessType successType : SuccessAPIDataStocker.successTypes)
        {
            this.listEntries[i++] = new GuiSuccessTypeList.SuccessTypeEntry(successType);
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
        SuccessType type = ((GuiSuccessTypeList.SuccessTypeEntry)this.listEntries[slotIndex]).getSuccessType();
        if(type.getId() == -1) //SHOW ADVANCEMENT MINECRAFT DEFAULT
        	this.mc.displayGuiScreen(new GuiScreenAdvancements(this.mc.player.connection.getAdvancementManager()));
        else {
	        GuiButton guiButton = ((GuiSuccessTypeList.SuccessTypeEntry)this.listEntries[slotIndex]).getSuccessTypeButton();
	        guiButton.playPressSound(this.mc.getSoundHandler());
	        this.mc.displayGuiScreen(new GuiSuccessItems(this.lastScreen, this.mc, type));
        }
    }

    @Override
    public void drawScreen(int mouseXIn, int mouseYIn, float partialTicks)
    {
        if (this.visible)
        {
            this.mouseX = mouseXIn;
            this.mouseY = mouseYIn;
            int i = this.getScrollBarX();
            int j = i + 6;
            this.bindAmountScrolled();

            int k = this.left + this.width / 2 - this.getListWidth() / 2 + 2;
            int l = this.top - (int)this.amountScrolled;

            this.drawSelectionBox(k, l, mouseXIn, mouseYIn, partialTicks);
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
        }
    }

    public class SuccessTypeEntry implements GuiListExtended.IGuiListEntry
    {
        private final SuccessType successType;
        private final GuiSuccessTypeButton successTypeButton;
        private final ResourceLocation background = new ResourceLocation("textures/gui/frazionz/interface_background.png");

        private SuccessTypeEntry(SuccessType type)
        {
            this.successType = type;
            this.successTypeButton = new GuiSuccessTypeButton(successType.getDisplay(), 0, 0, 0, 299, 36, 0, 239, this.background);
        }

        public void drawEntry(int slotIndex, int x, int y, int listWidth, int slotHeight, int mouseX, int mouseY, boolean isSelected, float partialTicks)
        {
            this.successTypeButton.x = x - 3;
            this.successTypeButton.y = y;
            this.successTypeButton.drawButton(GuiSuccessTypeList.this.mc, mouseX, mouseY, partialTicks);
        }

        public boolean mousePressed(int slotIndex, int mouseX, int mouseY, int mouseEvent, int relativeX, int relativeY)
        {
            if(this.successTypeButton.mousePressed(GuiSuccessTypeList.this.mc, mouseX, mouseY)) {
                return true;
            }
            return false;
        }

        public void mouseReleased(int slotIndex, int x, int y, int mouseEvent, int relativeX, int relativeY)
        {
            this.successTypeButton.mouseReleased(x, y);
        }

		public void updatePosition(int slotIndex, int x, int y, float partialTicks) {
		}

        public SuccessType getSuccessType() {
            return successType;
        }

        public GuiButton getSuccessTypeButton() {
            return successTypeButton;
        }
    }
}
