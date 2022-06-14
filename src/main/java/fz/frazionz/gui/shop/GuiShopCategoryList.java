package fz.frazionz.gui.shop;

import fz.frazionz.api.data.ShopAPIDataStocker;
import fz.frazionz.api.gsonObj.ShopType;
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

public class GuiShopCategoryList extends GuiListFrazionZInterface
{

    public GuiShopCategoryList(GuiScreen lastScreen, Minecraft mcIn, int left, int right, int top, int bottom, int slotHeight)
    {
        super(lastScreen, mcIn, left, right, top, bottom, slotHeight);
        this.listEntries = new GuiListExtended.IGuiListEntry[ShopAPIDataStocker.shopTypes.size()];
        
        int i = 0;
        for (ShopType shopType : ShopAPIDataStocker.shopTypes)
        {
        	this.listEntries[i++] = new GuiShopCategoryList.ShopTypeEntry(shopType);
        }
    }
    
    @Override
    protected void elementClicked(int slotIndex, boolean isDoubleClick, int mouseX, int mouseY)
    {
    	ShopType type = ((GuiShopCategoryList.ShopTypeEntry)this.listEntries[slotIndex]).getShopType();
    	GuiButton guiButton = ((GuiShopCategoryList.ShopTypeEntry)this.listEntries[slotIndex]).getShopTypeButton();
    	guiButton.playPressSound(this.mc.getSoundHandler());
    	this.mc.displayGuiScreen(new GuiShopItems(this.lastScreen, this.mc, type));
    }

    public class ShopTypeEntry implements GuiListExtended.IGuiListEntry
    {
        private final ShopType shopType;
        private final GuiButton shopTypeButton;
        private final ResourceLocation background = new ResourceLocation("textures/gui/frazionz/interface_background.png");	
        
        private ShopTypeEntry(ShopType type)
        {
            this.shopType = type;
            this.shopTypeButton = new GuiListItemButton(type.getItemStack(), type.getTypeName(), 0, 0, 0, 298, 36, 0, 238, this.background);
        }
        
        public void drawEntry(int slotIndex, int x, int y, int listWidth, int slotHeight, int mouseX, int mouseY, boolean isSelected, float partialTicks)
        {
            this.shopTypeButton.x = x - 3;
            this.shopTypeButton.y = y;
            this.shopTypeButton.drawButton(GuiShopCategoryList.this.mc, mouseX, mouseY, partialTicks);
        }
        
        public boolean mousePressed(int slotIndex, int mouseX, int mouseY, int mouseEvent, int relativeX, int relativeY)
        {
        	if(this.shopTypeButton.mousePressed(GuiShopCategoryList.this.mc, mouseX, mouseY)) {
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
        
        public ShopType getShopType() {
			return shopType;
		}
        
        public GuiButton getShopTypeButton() {
			return shopTypeButton;
		}
    }
}
