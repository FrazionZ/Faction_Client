package fz.frazionz.client.gui.shop;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;

import fz.frazionz.Client;
import fz.frazionz.TTFFontRenderer;
import fz.frazionz.api.gsonObj.ShopItem;
import fz.frazionz.client.gui.GuiButtonImage;
import fz.frazionz.client.gui.GuiFrazionZInterface;
import fz.frazionz.enums.EnumGui;
import fz.frazionz.packets.client.CPacketShopTrade;
import fz.frazionz.utils.FzUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.item.ItemStack;

public class GuiBoutiqueItemSelect extends GuiFrazionZInterface {
	
	private ShopItem item;
	
	public GuiBoutiqueItemSelect(GuiScreen lastScreen, Minecraft mc, ShopItem item) {
		super(item.getBoutiqueItemName(), lastScreen, mc);
		this.item = item;
		this.hasBackButton = true;
	}

	public void initGui()
	{
		super.initGui();
		this.buttonList.add(new GuiButtonImage(2, "Acheter", this.width / 2 + 32, this.guiTop + 169, 124, 24, 124, 310, true, BACKGROUND_1));
	}
	
	
	protected void actionPerformed(GuiButton button, int mouseButton) throws IOException {
        switch (button.id)
        {
	    	case 1:
				this.mc.displayGuiScreen(this.lastScreen);
				break;
        	case 2:
        		this.mc.player.connection.sendPacket(new CPacketShopTrade(EnumGui.MARKET_ITEM, this.item.getId(), 1, 2));
        		break;
	
        }
        super.actionPerformed(button, mouseButton);
	}

	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        // Draw More Information
        double money = (double)(Client.getInstance().getFactionProfile().getMoney());
        String s = "\u00A76M\u00A7foney : " + FzUtils.convertMoney(money) + " Coins";
        this.fontRenderer.drawString(s, this.width / 2 - this.fontRenderer.getStringWidth(s) / 2, this.guiTop + this.ySize - 22, 16777215, true);
	
        // 92 = (368 / 4) = taille du background gris
        
        // Draw Sell Price
        this.fontRenderer.drawString("Prix :", this.width / 2 + 35, this.guiTop + 52, 16777215, true);
        NumberFormat nf = NumberFormat.getInstance(Locale.US);
        String sellPrice = nf.format(this.item.getBuyPrice()) + " \u00A76$\u00A7r";
        this.drawFzCustomBackgroundSize(this.mc, this.width / 2 + 32, this.guiTop + 64, 125, 25);
        this.drawString(this.fontRenderer, sellPrice, this.width / 2 + 32 + 29 + 67/2 - (this.fontRenderer.getStringWidth(sellPrice)/2), this.guiTop + 62 + 9, 0xFFFFFFFF);
        
        // Draw Description
        this.fontRenderer.drawString("Description :", this.width / 2 - 32 - 122, this.guiTop + 52, 16777215, true);
        this.drawFzCustomBackgroundSize(this.mc, this.width / 2 - 32 - 125, this.guiTop + 64, 180, 131);
        this.drawString(this.fontRenderer, this.item.getDescription(), this.width / 2 - 32 - 117, this.guiTop + 62 + 9, 0xFFFFFFFF);
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
