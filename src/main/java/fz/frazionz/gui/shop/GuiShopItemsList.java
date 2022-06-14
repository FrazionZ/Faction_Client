package fz.frazionz.gui.shop;

import fz.frazionz.api.gsonObj.ShopItem;
import fz.frazionz.api.gsonObj.ShopType;
import fz.frazionz.utils.Colors;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class GuiShopItemsList extends GuiListFrazionZInterface
{
    private ShopType type;

    public GuiShopItemsList(GuiScreen lastScreen, ShopType type, Minecraft mcIn, int left, int right, int top, int bottom, int slotHeight)
    {
        super(lastScreen, mcIn, left, right, top, bottom, slotHeight);
        
        this.listEntries = new GuiListExtended.IGuiListEntry[type.getItems().size()];
        int i = 0;
        for (ShopItem item : type.getItems())
        {
        	this.listEntries[i++] = new GuiShopItemsList.ShopEntry(item);
        }
    }

    @Override
    protected void elementClicked(int slotIndex, boolean isDoubleClick, int mouseX, int mouseY)
    {
    	ShopItem item = ((GuiShopItemsList.ShopEntry)this.listEntries[slotIndex]).getShopItem();
    	GuiButton guiButton = ((GuiShopItemsList.ShopEntry)this.listEntries[slotIndex]).getShopItemButton();
    	guiButton.playPressSound(this.mc.getSoundHandler());
    	this.mc.displayGuiScreen(new GuiShopItemSelect(this.lastScreen, this.mc, item));
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

    public class ShopEntry implements GuiListExtended.IGuiListEntry
    {
        private final ShopItem item;
        private final GuiButton shopItemButton;
        private final ResourceLocation background = new ResourceLocation("textures/gui/frazionz/interface_background.png");	
        
        private ShopEntry(ShopItem item)
        {
            this.item = item;
            ItemStack itemStack = this.item.getItemStack();
            this.shopItemButton = new GuiShopItemButton(itemStack, "\u00A7f" + itemStack.getDisplayName(), Double.toString(this.item.getActualBuyPrice()) + "\u00A72$\u00A7r / " + Double.toString(this.item.getActualSellPrice()) + "\u00A74$\u00A7r", 0, 0, 0, 298, 36, 0, 238, this.background);
        }
        
        public void drawEntry(int slotIndex, int x, int y, int listWidth, int slotHeight, int mouseX, int mouseY, boolean isSelected, float partialTicks)
        {
            this.shopItemButton.x = x - 3;
            this.shopItemButton.y = y;
            this.shopItemButton.drawButton(GuiShopItemsList.this.mc, mouseX, mouseY, partialTicks);
        }
        
        public boolean mousePressed(int slotIndex, int mouseX, int mouseY, int mouseEvent, int relativeX, int relativeY)
        {
        	if(this.shopItemButton.mousePressed(GuiShopItemsList.this.mc, mouseX, mouseY)) {
        		return true;
        	}
        	return false;
        }
        
        public void mouseReleased(int slotIndex, int x, int y, int mouseEvent, int relativeX, int relativeY)
        {
            this.shopItemButton.mouseReleased(x, y);
        }
        
		public void updatePosition(int slotIndex, int x, int y, float partialTicks) {
		}
        
        public ShopItem getShopItem() {
			return this.item;
		}
        
        public GuiButton getShopItemButton() {
			return shopItemButton;
		}
    }
    
    public class GuiShopItemButton extends GuiButton {
    	
        private final ResourceLocation resourceLocation;
        private final int textureX;
        private final int textureY;
        private ItemStack itemToDisplay;
        private String price;

        public GuiShopItemButton(ItemStack itemToDisplay, String displayString, String price, int buttonId, int x, int y, int widthIn, int heightIn, int textureX, int textureY, ResourceLocation resourceLocation)
        {
            super(buttonId, x, y, widthIn, heightIn, displayString);
            this.textureX = textureX;
            this.textureY = textureY;
            this.itemToDisplay = itemToDisplay;
            this.resourceLocation = resourceLocation;
            this.price = price;
        }

        public void drawButton(Minecraft mc, int mouseX, int mouseY, float p_191745_4_)
        {
            if (this.visible)
            {
            	FontRenderer fontrenderer = mc.fontRenderer;
            	this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
                mc.getTextureManager().bindTexture(this.resourceLocation);
                
                int i = this.textureX;
                int j = this.textureY;
                if (this.hovered)
                {
                    j += this.height;
                }
                this.drawModalRectWithCustomSizedTexture(this.x, this.y, i, j, this.width, this.height, 512.0F, 512.0F);
                
                RenderHelper.enableGUIStandardItemLighting();
                
                RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
                GlStateManager.enableRescaleNormal();
                renderItem.zLevel = 100F;
                renderItem.renderItemIntoGUI(this.itemToDisplay, this.x + 12, this.y + (this.height / 2) - 8);
                this.drawString(mc.fontRenderer, this.displayString, this.x + 36, this.y + (this.height / 2) - 3, 0xFFFFFFFF);
                this.drawString(mc.fontRenderer, this.price, this.x + this.width - 12 - mc.fontRenderer.getStringWidth(this.price), this.y + (this.height / 2) - 3, 0xFFFFFFFF);
                
                RenderHelper.disableStandardItemLighting();
            }
        }
    }
}
