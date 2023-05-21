package fz.frazionz.client.gui.market;

import java.io.IOException;

import fz.frazionz.FzClient;
import fz.frazionz.TTFFontRenderer;
import fz.frazionz.client.gui.list.*;
import fz.frazionz.enums.EnumGui;
import fz.frazionz.client.gui.GuiFrazionZInterface;
import fz.frazionz.packets.client.CPacketGuiOpener;
import fz.frazionz.utils.FzUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;

public class GuiMarketCategory extends GuiFrazionZInterface {
		
	private GuiSlotList shopTypeList;
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

        Slot[] slots = new Slot[types.length];
        int i = 0;
        for(MarketType type : types) {
            slots[i++] = new Slot(type.getId(), type.getTypeName(), type.getItemStack());
        }
        this.shopTypeList = new GuiSlotList(this.mc, slots, this.guiLeft, this.guiTop, 400, 300, 24);
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

        double money = (double)(FzClient.getInstance().getFactionProfile().getMoney());
        String s = "\u00A76M\u00A7foney : " + FzUtils.convertMoney(money) + " Coins";
        this.fontRenderer.drawString(s, this.width / 2 - this.fontRenderer.getStringWidth(s) / 2, this.guiTop + this.ySize - 22, 16777215, true);
	}
    
    @Override
    public void drawTitle() {
    }
	
	public void drawTopList()
	{
        TTFFontRenderer titleRenderer = FzClient.getInstance().getTTFFontRenderers().get(24);
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

    private class Slot implements FzSlot {

        int id;
        private String name;
        private ItemStack stack;

        public Slot(int id, String name, ItemStack stack) {
            this.id = id;
            this.name = name;
            this.stack = stack;
        }

        @Override
        public int getSlotHeight() {
            return 0;
        }

        @Override
        public int getSlotWidth() {
            return 0;
        }

        @Override
        public int getSlotX() {
            return 0;
        }

        @Override
        public int getSlotY() {
            return 0;
        }

        @Override
        public void setSlotX(int x) {

        }

        @Override
        public void setSlotY(int y) {

        }

        @Override
        public void setSlotWidth(int width) {

        }

        @Override
        public void drawSlot(int mouseX, int mouseY, float partialTicks) {

        }

        @Override
        public void onClick(int mouseX, int mouseY, int mouseButton) {
            mc.player.connection.sendPacket(new CPacketGuiOpener(EnumGui.MARKET_ITEM_LIST, id));
        }
    }
}
