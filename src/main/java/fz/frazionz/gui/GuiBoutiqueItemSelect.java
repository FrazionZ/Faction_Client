package fz.frazionz.gui;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.concurrent.Executors;

import fz.frazionz.Client;
import fz.frazionz.api.gsonObj.BoutiqueItem;
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

public class GuiBoutiqueItemSelect extends GuiScreen {
	
	private static final ResourceLocation background = new ResourceLocation("textures/gui/frazionz/interface_background.png");
	
	private final GuiScreen lastScreen;
	private BoutiqueItem item;
	
	private Minecraft mc;
	
	public GuiBoutiqueItemSelect(GuiScreen lastScreen, Minecraft mc, BoutiqueItem item) {	
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
		this.buttonList.add(new GuiButtonImage(1, "Acheter", this.width / 2 + 32, this.guiTop + 166, 125, 25, 125, 311, true, background));

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
        		this.mc.player.connection.sendPacket(new CPacketShopTrade(this.item.getId(), this.mc.player.getUniqueID(), 1, 2));
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
        renderItem.renderItemIntoGUI(itemStack, this.width / 2 - this.fontRendererObj.getStringWidth(this.item.getBoutiqueItemName()) / 2 - 24, this.guiTop + 7);
        this.fontRendererObj.drawString(this.item.getBoutiqueItemName(), this.width / 2 - this.fontRendererObj.getStringWidth(this.item.getBoutiqueItemName()) / 2, this.guiTop + 12, 16777215, true);
        
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
        
        // Draw Sell Price
        this.fontRendererObj.drawString("Prix :", this.width / 2 + 35, this.guiTop + 52, 16777215, true);
        NumberFormat nf = NumberFormat.getInstance(Locale.US);
        String sellPrice = nf.format(this.item.getBuyPrice()) + " \u00A76$\u00A7r";
        this.drawFzCustomBackgroundSize(this.mc, this.width / 2 + 32, this.guiTop + 60, 125, 25);
        this.drawString(this.fontRendererObj, sellPrice, this.width / 2 + 32 + 29 + 67/2 - (this.fontRendererObj.getStringWidth(sellPrice)/2), this.guiTop + 60 + 9, 0xFFFFFFFF);
        
        // Draw Description
        this.fontRendererObj.drawString("Description :", this.width / 2 - 32 - 122, this.guiTop + 52, 16777215, true);
        this.drawFzCustomBackgroundSize(this.mc, this.width / 2 - 32 - 125, this.guiTop + 60, 180, 131);
        this.drawString(this.fontRendererObj, this.item.getDescription(), this.width / 2 - 32 - 117, this.guiTop + 68, 0xFFFFFFFF);
        
        
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
