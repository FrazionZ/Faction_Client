package fz.frazionz.client.gui.shop;

import fz.frazionz.api.gsonObj.ShopType;
import fz.frazionz.client.gui.buttons.GuiItemstackButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class GuiBoutiqueCategoryList extends GuiListFrazionZInterface
{
    public GuiBoutiqueCategoryList(GuiScreen lastScreen, Minecraft mcIn, int left, int right, int top, int bottom, int slotHeight)
    {
        super(lastScreen, mcIn, left, right, top, bottom, slotHeight);
        this.listEntries = new GuiListExtended.IGuiListEntry[0];
        /*this.listEntries = new GuiListExtended.IGuiListEntry[ShopAPIDataStocker.boutiqueTypes.size()];
        int i = 0;
        
        for (ShopType boutiqueType : ShopAPIDataStocker.boutiqueTypes)
        {
        	this.listEntries[i++] = new GuiBoutiqueCategoryList.BoutiqueTypeEntry(boutiqueType);
        }*/
    }
    
    @Override
    protected void elementClicked(int slotIndex, boolean isDoubleClick, int mouseX, int mouseY)
    {
    	ShopType type = ((GuiBoutiqueCategoryList.BoutiqueTypeEntry)this.listEntries[slotIndex]).getBoutiqueType();
    	GuiButton guiButton = ((GuiBoutiqueCategoryList.BoutiqueTypeEntry)this.listEntries[slotIndex]).getShopTypeButton();
    	guiButton.playPressSound(this.mc.getSoundHandler());
    	this.mc.displayGuiScreen(new GuiBoutiqueItems(this.lastScreen, this.mc, type));
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
                this.drawRoundedSliderButton(i, this.top, j, this.bottom, 0xFF0E1014);
                this.drawRoundedSliderButton(i, l1, j, (l1 + k1), 0xFFE48515);
            }
            
            GlStateManager.enableTexture2D();
            GlStateManager.shadeModel(7424);
            GlStateManager.enableAlpha();
            GlStateManager.disableBlend();
        }
    }

    public class BoutiqueTypeEntry implements GuiListExtended.IGuiListEntry
    {
        private final ShopType boutiqueType;
        private final GuiButton shopTypeButton;
        private final ResourceLocation background = new ResourceLocation("textures/gui/frazionz/interface_background.png");	
        
        private BoutiqueTypeEntry(ShopType type)
        {
            this.boutiqueType = type;
            this.shopTypeButton = new GuiItemstackButton(0, type.getItemStack(), type.getTypeName(), 0, 0, 298, 36);
        }
        
        public void drawEntry(int slotIndex, int x, int y, int listWidth, int slotHeight, int mouseX, int mouseY, boolean isSelected, float partialTicks)
        {
            this.shopTypeButton.x = x - 3;
            this.shopTypeButton.y = y;
            this.shopTypeButton.drawButton(GuiBoutiqueCategoryList.this.mc, mouseX, mouseY, partialTicks);
        }
        
        public boolean mousePressed(int slotIndex, int mouseX, int mouseY, int mouseEvent, int relativeX, int relativeY)
        {
        	if(this.shopTypeButton.mousePressed(GuiBoutiqueCategoryList.this.mc, mouseX, mouseY)) {
        		return true;
        	}
        	return false;
        }
        
        public void mouseReleased(int slotIndex, int x, int y, int mouseEvent, int relativeX, int relativeY)
        {
            this.shopTypeButton.mouseReleased(x, y);
        }
        
		public void updatePosition(int slotIndex, int x, int y, float partialTicks) {
		}
        
        public ShopType getBoutiqueType() {
			return boutiqueType;
		}
        
        public GuiButton getShopTypeButton() {
			return shopTypeButton;
		}
    }
}
