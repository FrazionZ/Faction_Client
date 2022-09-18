package fz.frazionz.gui.shop;

import java.io.IOException;

import fz.frazionz.Client;
import fz.frazionz.TTFFontRenderer;
import fz.frazionz.api.gsonObj.MarketItem;
import fz.frazionz.gui.GuiButtonImage;
import fz.frazionz.gui.GuiFrazionZInterface;
import fz.frazionz.packets.client.CPacketShopTrade;
import fz.frazionz.utils.FzUtils;
import fz.frazionz.utils.MathUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.item.ItemStack;

public class GuiShopItemSelect extends GuiFrazionZInterface {
	
	private MarketItem item;
	private int totalSellItems = 1;
	private int totalBuyItems = 1;
	
	public GuiShopItemSelect(GuiScreen lastScreen, Minecraft mc, MarketItem item) {	
		super(item.getItemStack().getDisplayName(), lastScreen, mc);
		this.item = item;
		this.hasBackButton = true;
	}

	public void initGui()
	{	
		super.initGui();
		this.buttonList.add(new GuiButtonImage(2, "Acheter", this.width / 2 - 32 - 125, this.guiTop + 148, 124, 24, 124, 310, true, BACKGROUND_1));
		this.buttonList.add(new GuiButtonImage(3, "Vendre", this.width / 2 + 32, this.guiTop + 148, 124, 24, 0, 310, true, BACKGROUND_1));
		this.buttonList.add(new GuiButtonImage(4, "Tout Vendre", this.width / 2 + 32, this.guiTop + 176, 124, 24, 0, 310, true, BACKGROUND_1));

		
		this.buttonList.add(new GuiButtonImage(5, "", this.width / 2 - 32 - 125, this.guiTop + 75, 24, 24, 296, 310, true, BACKGROUND_1));
		this.buttonList.add(new GuiButtonImage(6, "", this.width / 2 - 32 - 25, this.guiTop + 75, 24, 24, 272, 310, true, BACKGROUND_1));
		
		this.buttonList.add(new GuiButtonImage(7, "", this.width / 2 + 32 + 100, this.guiTop + 75, 24, 24, 272, 310, true, BACKGROUND_1));
		this.buttonList.add(new GuiButtonImage(8, "", this.width / 2 + 32, this.guiTop + 75, 24, 24, 296, 310, true, BACKGROUND_1));
	}
	
	
	protected void actionPerformed(GuiButton button, int mouseButton) throws IOException {
        switch (button.id)
        {
	    	case 1:
				this.mc.displayGuiScreen(lastScreen);
				break;
        	case 2:
        		this.mc.player.connection.sendPacket(new CPacketShopTrade(this.item.getId(), this.mc.player.getUniqueID(), this.totalBuyItems, 0));
        		break;
        	case 3:
        		this.mc.player.connection.sendPacket(new CPacketShopTrade(this.item.getId(), this.mc.player.getUniqueID(), this.totalSellItems, 1));
        		break;
        	case 4:
        		this.mc.player.connection.sendPacket(new CPacketShopTrade(this.item.getId(), this.mc.player.getUniqueID(), 3000, 1));
        		break;
        	case 5:
        		if(mouseButton == 0)
        			this.totalBuyItems -= 1;
        		else if(mouseButton == 1)
        			this.totalBuyItems -= 10;
        		if(this.totalBuyItems < 1)
        			this.totalBuyItems = 1;
        		break;
        		
        	case 6:
        		if(mouseButton == 0)
        			this.totalBuyItems += 1;
        		else if(mouseButton == 1)
        			this.totalBuyItems += 10;
        		break;
        		
        	case 7:
        		if(mouseButton == 0)
        			this.totalSellItems += 1;
        		else if(mouseButton == 1)
        			this.totalSellItems += 10;
        		break;
        		
        	case 8:
        		if(mouseButton == 0)
        			this.totalSellItems -= 1;
        		else if(mouseButton == 1)
        			this.totalSellItems -= 10;
        		if(this.totalSellItems < 1)
        			this.totalSellItems = 1;
        		break;
        }
	}

	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);

        // Draw More Information
        double money = (double)(Client.getInstance().getFactionProfile().getMoney());
        String s = "\u00A76M\u00A7foney : " + FzUtils.convertMoney(money) + " Coins";
        this.fontRenderer.drawString(s, this.width / 2 - this.fontRenderer.getStringWidth(s) / 2, this.guiTop + this.ySize - 22, 16777215, true);
	
        this.fontRenderer.drawString("Vente", this.width / 2 + 61 + 67/2 - (this.fontRenderer.getStringWidth("Vente") / 2 ), this.guiTop + 52, 16777215, true);
        this.fontRenderer.drawString("Achat",  this.width / 2 - 61 - 67/2 - (this.fontRenderer.getStringWidth("Achat") / 2 ), this.guiTop + 52, 16777215, true);
        
        // Draw Quantity
        this.drawFzCustomBackgroundSize(this.mc, this.width / 2 - 31 - 96, this.guiTop + 77, 68, 25);
        this.drawString(this.fontRenderer, Integer.toString(this.totalBuyItems), this.width / 2 - 32 - 29 - 67/2 - (this.fontRenderer.getStringWidth(Integer.toString(this.totalBuyItems))/2), this.guiTop + 75 + 9, 0xFFFFFFFF);
        this.drawFzCustomBackgroundSize(this.mc, this.width / 2 + 33 + 29, this.guiTop + 77, 68, 25);
        this.drawString(this.fontRenderer, Integer.toString(this.totalSellItems), this.width / 2 + 32 + 29 + 67/2 - (this.fontRenderer.getStringWidth(Integer.toString(this.totalSellItems))/2), this.guiTop + 75 + 9, 0xFFFFFFFF);

        
        // Draw Prices
        String buyPrice = Double.toString(MathUtils.roundAvoid(this.totalBuyItems * this.item.getActualBuyPrice(), 2)) + " \u00A76$\u00A7r";
        String sellPrice = Double.toString(MathUtils.roundAvoid(this.totalSellItems * this.item.getActualSellPrice(), 2)) + " \u00A76$\u00A7r";
        this.drawFzCustomBackgroundSize(this.mc, this.width / 2 - 31 - 124, this.guiTop + 110, 124, 25);
        this.drawString(this.fontRenderer, buyPrice, this.width / 2 - 32 - 29 - 67/2 - (this.fontRenderer.getStringWidth(buyPrice)/2), this.guiTop + 108 + 9, 0xFFFFFFFF);
        this.drawFzCustomBackgroundSize(this.mc, this.width / 2 + 34, this.guiTop + 110, 124, 25);
        this.drawString(this.fontRenderer, sellPrice, this.width / 2 + 32 + 29 + 67/2 - (this.fontRenderer.getStringWidth(sellPrice)/2), this.guiTop + 108 + 9, 0xFFFFFFFF);
	}
    
    @Override
	public void drawTitle() {
    	
        TTFFontRenderer titleRenderer = Client.getInstance().getTTFFontRenderers().get(24);
        int titleSize = titleRenderer.getWidth(this.title) + 14;
		
		// draw title background
        
        this.mc.getTextureManager().bindTexture(BACKGROUND_2);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.enableBlend();
        this.drawModalRectWithCustomSizedTexture(this.guiLeft + this.xSize/2 - titleSize/2 - 28, this.guiTop - 10, 0, 0, 28, 26, 512.0F, 512.0F);
        this.mc.getTextureManager().bindTexture(BACKGROUND_2);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.enableBlend();
        this.drawModalRectWithCustomSizedTexture(this.guiLeft + this.xSize/2 - titleSize/2, this.guiTop - 10, 28, 0, titleSize, 26, 512.0F, 512.0F);
        this.mc.getTextureManager().bindTexture(BACKGROUND_2);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.enableBlend();
        this.drawModalRectWithCustomSizedTexture(this.guiLeft + this.xSize/2 + titleSize/2, this.guiTop - 10, 366, 0, 28 , 26, 512.0F, 512.0F);
    	
        ItemStack itemStack = this.item.getItemStack(); 
        RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
        RenderHelper.enableGUIStandardItemLighting();
        GlStateManager.enableRescaleNormal();
        renderItem.zLevel = 100F;
        renderItem.renderItemIntoGUI(itemStack, this.guiLeft + this.xSize/2 - titleSize/2 - 7, this.guiTop - 5);
        GlStateManager.disableRescaleNormal();
        RenderHelper.disableStandardItemLighting();
        
		titleRenderer.drawCenteredString(this.title, this.guiLeft + this.xSize/2 + 7, this.guiTop + 3, 0xFFFFFFFF);
	}

}
