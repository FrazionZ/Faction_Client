package fz.frazionz.gui;

import fz.frazionz.api.gsonObj.ShopItem;
import fz.frazionz.api.gsonObj.ShopType;
import fz.frazionz.api.gsonObj.SuccessObj;
import fz.frazionz.api.gsonObj.SuccessType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.*;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class GuiSuccessList extends GuiListExtended
{
    private final Minecraft mc;
    private final IGuiListEntry[] listEntries;
    private int maxListLabelWidth;
    private SuccessType type;
    private GuiScreen lastScreen;

    public GuiSuccessList(GuiScreen lastScreen, SuccessType type, Minecraft mcIn, int left, int right, int top, int bottom, int slotHeight, int spaceBetween)
    {
        super(mcIn, left, right, top, bottom, slotHeight, true, spaceBetween);
        this.mc = mcIn;
        this.type = type;
        this.lastScreen = lastScreen;
        this.listEntries = new IGuiListEntry[type.getItems().size()];
        int i = 0;

        for (SuccessObj item : type.getItems())
        {
        	this.listEntries[i++] = new GuiSuccessList.SuccessEntry(item);
        }
    }

    protected int getSize()
    {
        return this.listEntries.length;
    }

    /**
     * Gets the IGuiListEntry object for the given index
     */
    public IGuiListEntry getListEntry(int index)
    {
        return this.listEntries[index];
    }

    protected int getScrollBarX()
    {
    	if(this.mc.gameSettings.frazionz_ui) {
            return super.getScrollBarX();
    	}
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
            
            this.func_192638_a(k, l, mouseXIn, mouseYIn, partialTicks);
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

    public class SuccessEntry implements IGuiListEntry
    {
        private final SuccessObj item;
        private final GuiButton shopItemButton;
        private final ResourceLocation background = new ResourceLocation("textures/gui/frazionz/interface_background.png");	
        
        private SuccessEntry(SuccessObj item)
        {
            this.item = item;
            this.shopItemButton = new GuiSuccessButton("test");
        }
        
        public void func_192634_a(int p_192634_1_, int p_192634_2_, int p_192634_3_, int p_192634_4_, int p_192634_5_, int p_192634_6_, int p_192634_7_, boolean p_192634_8_, float p_192634_9_)
        {
            this.shopItemButton.xPosition = p_192634_2_ - 3;
            this.shopItemButton.yPosition = p_192634_3_;
            this.shopItemButton.func_191745_a(GuiSuccessList.this.mc, p_192634_6_, p_192634_7_, p_192634_9_);
        }
        
        public boolean mousePressed(int slotIndex, int mouseX, int mouseY, int mouseEvent, int relativeX, int relativeY)
        {
        	if(this.shopItemButton.mousePressed(GuiSuccessList.this.mc, mouseX, mouseY)) {
        		return true;
        	}
        	return false;
        }
        
        public void mouseReleased(int slotIndex, int x, int y, int mouseEvent, int relativeX, int relativeY)
        {
            this.shopItemButton.mouseReleased(x, y);
        }
        
        public void func_192633_a(int p_192633_1_, int p_192633_2_, int p_192633_3_, float p_192633_4_)
        {
        }
        
        public GuiButton getShopItemButton() {
			return shopItemButton;
		}
    }
    
    public class GuiSuccessButton extends GuiButton {

        private String title;

        public GuiSuccessButton(String title)
        {
            super(0, 0, 0, 0, 0, title);
            this.title = title;
        }

        public void func_191745_a(Minecraft mc, int mouseX, int mouseY, float p_191745_4_)
        {
            if (this.visible)
            {
            	
                RenderHelper.enableGUIStandardItemLighting();
            	
            	GlStateManager.enableBlend();
                GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
                GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
                
                
            	FontRenderer fontrenderer = mc.fontRendererObj;
            	this.hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
                //mc.getTextureManager().bindTexture(this.resourceLocation);
                GlStateManager.disableDepth();
                //int i = this.textureX;
                //int j = this.textureY;
                
                /*RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
                GlStateManager.enableRescaleNormal();
                renderItem.zLevel = 100F;
                renderItem.renderItemIntoGUI(this.itemToDisplay, this.xPosition + 12, this.yPosition + (this.height / 2) - 8);*/
                this.drawString(mc.fontRendererObj, this.title, this.xPosition + 36, this.yPosition + (this.height / 2) - 3, 0xFFFFFFFF);
                //this.drawString(mc.fontRendererObj, this.price, this.xPosition + this.width - 12 - mc.fontRendererObj.getStringWidth(this.price), this.yPosition + (this.height / 2) - 3, 0xFFFFFFFF);
                
            }
        }
    }
}
