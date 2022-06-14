package fz.frazionz.gui.shop;

import fz.frazionz.api.data.ShopAPIDataStocker;
import fz.frazionz.api.gsonObj.ShopType;
import fz.frazionz.utils.Colors;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiLanguage;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.resources.Language;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public abstract class GuiListFrazionZInterface extends GuiListExtended
{
    protected final Minecraft mc;
    protected GuiListExtended.IGuiListEntry[] listEntries;
    protected int maxListLabelWidth;
    protected GuiScreen lastScreen; 

    public GuiListFrazionZInterface(GuiScreen lastScreen, Minecraft mcIn, int left, int right, int top, int bottom, int slotHeight)
    {
        super(mcIn, left, right, top, bottom, slotHeight);
        this.mc = mcIn;
        this.lastScreen = lastScreen;
		this.listEntries = null;
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

    /**
     * Gets the width of the list
     */
    @Override
    public int getListWidth()
    {
        return 299;
    }
    
    @Override
    protected int getScrollBarX() {
    	return this.left + this.width - 24;
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
                this.drawRoundedSliderButton(i, this.top, j, this.bottom, Colors.BLACK_4);
                this.drawRoundedSliderButton(i, l1, j, (l1 + k1), Colors.COLOR_2);
            }
            
            GlStateManager.enableTexture2D();
            GlStateManager.shadeModel(7424);
            GlStateManager.enableAlpha();
            GlStateManager.disableBlend();
        }
    }
}
