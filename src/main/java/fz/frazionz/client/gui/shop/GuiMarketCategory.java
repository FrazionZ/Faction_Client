package fz.frazionz.client.gui.shop;

import java.io.IOException;

import fz.frazionz.Client;
import fz.frazionz.TTFFontRenderer;
import fz.frazionz.api.gsonObj.MarketType;
import fz.frazionz.enums.EnumGui;
import fz.frazionz.client.gui.GuiFrazionZInterface;
import fz.frazionz.client.gui.list.DoubleListEntry;
import fz.frazionz.client.gui.list.DoubleListItemEntry;
import fz.frazionz.client.gui.list.GuiDoubleList;
import fz.frazionz.packets.client.CPacketGuiOpener;
import fz.frazionz.utils.FzUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;

public class GuiMarketCategory extends GuiFrazionZInterface {
		
	private GuiDoubleList shopTypeList;
    private MarketType[] types;
	
	public GuiMarketCategory(GuiScreen lastScreen, Minecraft mc, String json)
    {
		super("Market", lastScreen, mc);
		this.hasBackButton = true;
		this.drawButtonLater = true;
        this.types = MarketType.deserializeList(json);
	}

	public void initGui()
	{
		super.initGui();
        this.shopTypeList = new GuiList(this, this.mc, this.guiLeft, this.guiLeft + this.xSize, this.guiTop + 45, this.guiTop + this.ySize - 38, 36);
        DoubleListItemEntry[] entries = new DoubleListItemEntry[types.length];
        int i = 0;
        for(MarketType type : types) {
            entries[i++] = new DoubleListItemEntry(type.getId(), type.getTypeName(), type.getItemStack());
        }
        this.shopTypeList.setListEntry(entries);
	}
	
    /**
     * Handles mouse input.
     */
    public void handleMouseInput() throws IOException
    {
        super.handleMouseInput();
        this.shopTypeList.handleMouseInput();
    }
	
	
	protected void actionPerformed(GuiButton button, int mouseButton) throws IOException {
        switch (button.id)
        {				
        	case 1:
				this.mc.displayGuiScreen(lastScreen);
				break;
            default:
                this.shopTypeList.actionPerformed(button, mouseButton);
                break;
        }
	}

	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
        this.shopTypeList.drawScreen(mouseX, mouseY, partialTicks);
        this.drawTopList();
        this.drawButtons(mouseX, mouseY, partialTicks);

        double money = (double)(Client.getInstance().getFactionProfile().getMoney());
        String s = "\u00A76M\u00A7foney : " + FzUtils.convertMoney(money) + " Coins";
        this.fontRenderer.drawString(s, this.width / 2 - this.fontRenderer.getStringWidth(s) / 2, this.guiTop + this.ySize - 22, 16777215, true);
	}
    
    @Override
    public void drawTitle() {
    }
	
	public void drawTopList()
	{
        TTFFontRenderer titleRenderer = Client.getInstance().getTTFFontRenderers().get(24);
        int titleSize = titleRenderer.getWidth(this.title);
		
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
        
        titleRenderer.drawCenteredString(this.title, this.guiLeft + this.xSize/2, this.guiTop + 3, 0xFFFFFFFF);
	}

    private class GuiList extends GuiDoubleList {

        public GuiList(GuiScreen lastScreen, Minecraft mcIn, int left, int right, int top, int bottom, int slotHeight) {
            super(lastScreen, mcIn, left, right, top, bottom, slotHeight);
        }

        @Override
        protected void elementClicked(int slotIndex, boolean isDoubleClick, int mouseX, int mouseY)
        {
            this.mc.player.connection.sendPacket(new CPacketGuiOpener(EnumGui.MARKET_ITEM_LIST, ((DoubleListEntry)this.listEntries[slotIndex]).getId()));
        }
    }
}
