package fz.frazionz.gui;

import java.io.IOException;
import java.util.concurrent.Executors;

import fz.frazionz.Client;
import fz.frazionz.api.data.ShopAPIDataStocker;
import fz.frazionz.api.gsonObj.ShopItem;
import fz.frazionz.data.FzUserData;
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
import net.minecraft.util.ResourceLocation;

public class GuiShopItemSelect extends GuiScreen {
	
	private static final ResourceLocation background = new ResourceLocation("textures/gui/frazionz/interface_background.png");
	
	private final GuiScreen lastScreen;
	private ShopItem item;
	
	private int totalSellItems = 1;
	private int totalBuyItems = 1;
	
	private Minecraft mc;
	
	public GuiShopItemSelect(GuiScreen lastScreen, Minecraft mc, ShopItem item) {	
		this.lastScreen = lastScreen;
		this.mc = mc;
		this.item = item;
	}
	
	// 776 - 478 // 
	
	private final int xSize = 388;
	private final int ySize = 239;
	
	private int guiLeft;
	private int guiTop;
	
	public void initGui()
	{	
		guiLeft = (this.width - this.xSize) / 2;
		guiTop = (this.height - this.ySize) / 2;
		
		this.buttonList.add(new GuiButtonOnlyImage(50, this.guiLeft + 10, this.guiTop + 5, 21, 20, 388, 0, 0, background));
		
		this.buttonList.add(new GuiButtonImage(1, "Acheter", this.width / 2 - 32 - 125, this.guiTop + 148, 125, 25, 125, 311, true, background));
		this.buttonList.add(new GuiButtonImage(2, "Vendre", this.width / 2 + 32, this.guiTop + 148, 125, 25, 0, 311, true, background));
		this.buttonList.add(new GuiButtonImage(3, "Tout Vendre", this.width / 2 + 32, this.guiTop + 176, 125, 25, 0, 311, true, background));

		
		this.buttonList.add(new GuiButtonImage(4, "", this.width / 2 - 32 - 125, this.guiTop + 75, 25, 25, 300, 311, true, background));
		this.buttonList.add(new GuiButtonImage(5, "", this.width / 2 - 32 - 25, this.guiTop + 75, 25, 25, 275, 311, true, background));
		
		this.buttonList.add(new GuiButtonImage(6, "", this.width / 2 + 32 + 100, this.guiTop + 75, 25, 25, 275, 311, true, background));
		this.buttonList.add(new GuiButtonImage(7, "", this.width / 2 + 32, this.guiTop + 75, 25, 25, 300, 311, true, background));

		Executors.newCachedThreadPool().submit(new Runnable() {
			@Override
			public void run() {
				Client.getInstance().updateFProfile();
			}
		});

		super.initGui();
	}
	
	
	protected void actionPerformed(GuiButton button, int mouseButton) throws IOException {
        switch (button.id)
        {
        	case 1:
        		this.mc.player.connection.sendPacket(new CPacketShopTrade(this.item.getId(), this.mc.player.getUniqueID(), this.totalBuyItems, 0));
        		break;
        	case 2:
        		this.mc.player.connection.sendPacket(new CPacketShopTrade(this.item.getId(), this.mc.player.getUniqueID(), this.totalSellItems, 1));
        		break;
        	case 3:
        		this.mc.player.connection.sendPacket(new CPacketShopTrade(this.item.getId(), this.mc.player.getUniqueID(), 3000, 1));
        		break;
        	case 4:
        		if(mouseButton == 0)
        			this.totalBuyItems -= 1;
        		else if(mouseButton == 1)
        			this.totalBuyItems -= 10;
        		if(this.totalBuyItems < 1)
        			this.totalBuyItems = 1;
        		break;
        		
        	case 5:
        		if(mouseButton == 0)
        			this.totalBuyItems += 1;
        		else if(mouseButton == 1)
        			this.totalBuyItems += 10;
        		break;
        		
        	case 6:
        		if(mouseButton == 0)
        			this.totalSellItems += 1;
        		else if(mouseButton == 1)
        			this.totalSellItems += 10;
        		break;
        		
        	case 7:
        		if(mouseButton == 0)
        			this.totalSellItems -= 1;
        		else if(mouseButton == 1)
        			this.totalSellItems -= 10;
        		if(this.totalSellItems < 1)
        			this.totalSellItems = 1;
        		break;
        		
            case 50:
            	this.mc.displayGuiScreen(this.lastScreen);
            	break;
        }
	}

	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		this.drawBackgroundImage();
        
		// Draw Title //
        ItemStack itemStack = this.item.getItemStack(); 
        RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
        RenderHelper.enableGUIStandardItemLighting();
        //GlStateManager.enableDepth();
        GlStateManager.enableRescaleNormal();
        renderItem.zLevel = 100F;
        renderItem.renderItemIntoGUI(itemStack, this.width / 2 - this.fontRendererObj.getStringWidth(itemStack.getDisplayName()) / 2 - 24, this.guiTop + 7);
        this.fontRendererObj.drawString(itemStack.getDisplayName(), this.width / 2 - this.fontRendererObj.getStringWidth(itemStack.getDisplayName()) / 2, this.guiTop + 12, 16777215, true);
        
        // Draw More Information
		String money = null;
		if(Client.getInstance().getFactionProfile() != null) {
			money = Client.getInstance().getFactionProfile().getMoney();
			if (money == null)
				money = "N/A";
		}else
			money = "N/A";
		String s = "\u00A76M\u00A7foney : " + FzUtils.conversMoney(money) + " Coins";
        this.fontRendererObj.drawString(s, this.width / 2 - this.fontRendererObj.getStringWidth(s) / 2, this.guiTop + this.ySize - 22, 16777215, true);
        // 92 = (368 / 4) = taille du background gris
        this.fontRendererObj.drawString("Vente", this.width / 2 + 61 + 67/2 - (this.fontRendererObj.getStringWidth("Vente") / 2 ), this.guiTop + 52, 16777215, true);
        this.fontRendererObj.drawString("Achat",  this.width / 2 - 61 - 67/2 - (this.fontRendererObj.getStringWidth("Achat") / 2 ), this.guiTop + 52, 16777215, true);
        
        // Draw Quantity
        this.drawFzCustomBackgroundSize(this.mc, this.width / 2 - 32 - 96, this.guiTop + 75, 67, 25);
        this.drawString(this.fontRendererObj, Integer.toString(this.totalBuyItems), this.width / 2 - 32 - 29 - 67/2 - (this.fontRendererObj.getStringWidth(Integer.toString(this.totalBuyItems))/2), this.guiTop + 75 + 9, 0xFFFFFFFF);
        this.drawFzCustomBackgroundSize(this.mc, this.width / 2 + 32 + 29, this.guiTop + 75, 67, 25);
        this.drawString(this.fontRendererObj, Integer.toString(this.totalSellItems), this.width / 2 + 32 + 29 + 67/2 - (this.fontRendererObj.getStringWidth(Integer.toString(this.totalSellItems))/2), this.guiTop + 75 + 9, 0xFFFFFFFF);

        
        // Draw Prices
        String buyPrice = Double.toString(MathUtils.roundAvoid(this.totalBuyItems * this.item.getActualBuyPrice(), 2)) + " \u00A76$\u00A7r";
        String sellPrice = Double.toString(MathUtils.roundAvoid(this.totalSellItems * this.item.getActualSellPrice(), 2)) + " \u00A76$\u00A7r";
        this.drawFzCustomBackgroundSize(this.mc, this.width / 2 - 32 - 125, this.guiTop + 108, 125, 25);
        this.drawString(this.fontRendererObj, buyPrice, this.width / 2 - 32 - 29 - 67/2 - (this.fontRendererObj.getStringWidth(buyPrice)/2), this.guiTop + 108 + 9, 0xFFFFFFFF);
        this.drawFzCustomBackgroundSize(this.mc, this.width / 2 + 32, this.guiTop + 108, 125, 25);
        this.drawString(this.fontRendererObj, sellPrice, this.width / 2 + 32 + 29 + 67/2 - (this.fontRendererObj.getStringWidth(sellPrice)/2), this.guiTop + 108 + 9, 0xFFFFFFFF);
  
        super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
    {
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

	
	public void drawBackgroundImage()
	{
		GlStateManager.pushMatrix();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(background);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        GlStateManager.enableBlend();
        this.drawModalRectWithCustomSizedTexture(i, j, 0, 0, this.xSize, this.ySize, 512.0F, 512.0F);
        GlStateManager.popMatrix();
	}

}
