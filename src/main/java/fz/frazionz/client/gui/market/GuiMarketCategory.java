package fz.frazionz.client.gui.market;

import java.io.IOException;

import fz.frazionz.FzClient;
import fz.frazionz.TTFFontRenderer;
import fz.frazionz.client.gui.GuiFzBaseScreen;
import fz.frazionz.client.gui.buttons.GuiMenuButton;
import fz.frazionz.client.gui.impl.ExcludeScaledResolution;
import fz.frazionz.client.gui.list.*;
import fz.frazionz.client.gui.utils.RoundedGradientShaderRenderer;
import fz.frazionz.client.gui.utils.RoundedShaderRenderer;
import fz.frazionz.enums.EnumGui;
import fz.frazionz.client.gui.GuiFrazionZInterface;
import fz.frazionz.packets.client.CPacketGuiOpener;
import fz.frazionz.utils.FzUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;

public class GuiMarketCategory extends GuiFzBaseScreen implements ExcludeScaledResolution {
		
	private GuiSlotList listType;
    private GuiSlotList listItems;
    private MarketType[] types;
    private MarketItem[] items;
	
	public GuiMarketCategory(GuiScreen lastScreen, Minecraft mc, String json)
    {
		super(lastScreen);
        this.types = MarketType.deserializeList(json);
	}

    public void setItems(String json) {
        this.items = MarketItem.deserializeList(json);

        Slot[] slots = new Slot[items.length];
        int i = 0;
        for(MarketItem item : items) {
            slots[i++] = new Slot(item.getId(), item.getItemStack().getDisplayName(), item.getItemStack(), false);
        }
        this.listItems.setSlots(slots);
        this.listItems.setAmountScrolled(0);
    }

    public void initGui()
	{
		super.initGui();

        Slot[] slots = new Slot[types.length];
        int i = 0;
        for(MarketType type : types) {
            slots[i++] = new Slot(type.getId(), type.getTypeName(), type.getItemStack(), true);
        }

        mc.player.connection.sendPacket(new CPacketGuiOpener(EnumGui.MARKET_ITEM_LIST, slots[0].id));
        slots[0].active = true;

        int gap = 64;
        int menuWidth = 290;
        int listWidth = 700;
        int totalWidth = menuWidth + listWidth + gap;
        this.listType = new GuiSlotList(this.mc, slots, width/2 - totalWidth/2, 164, menuWidth, 700, 16);
        this.listItems = new GuiSlotList(this.mc, null, width/2 - totalWidth/2 + menuWidth + gap, 164, listWidth, 720, 16);

        this.buttonList.add(new GuiMenuButton(0, 64, 64, 132, 56, "Back"));
    }
	
    /**
     * Handles mouse input.
     */
    public void handleMouseInput() throws IOException
    {
        super.handleMouseInput();
        this.listItems.handleMouseInput();
        this.listType.handleMouseInput();
    }
	
	
	protected void actionPerformed(GuiButton button, int mouseButton) throws IOException {
        switch (button.id)
        {				
        	case 0:
				this.mc.displayGuiScreen(lastScreen);
				break;
            default:
                this.listType.actionPerformed(button, mouseButton);
                this.listItems.actionPerformed(button, mouseButton);
                break;
        }
	}

	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        Gui.drawRect(0, 0, this.width, this.height, Gui.BLACK_4);

        this.drawButtons(mouseX, mouseY, partialTicks);
        this.listType.drawScreen(mouseX, mouseY, partialTicks);
        this.listItems.drawScreen(mouseX, mouseY, partialTicks);
        this.drawButtons(mouseX, mouseY, partialTicks);

        //double money = (double)(FzClient.getInstance().getFactionProfile().getMoney());
        //String s = "\u00A76M\u00A7foney : " + FzUtils.convertMoney(money) + " Coins";
        //this.fontRenderer.drawString(s, this.width / 2 - this.fontRenderer.getStringWidth(s) / 2, this.guiTop + this.ySize - 22, 16777215, true);
	}
    
    @Override
    public void drawTitle() {
    }

    private class Slot implements FzSlot {

        int id;
        int posX;
        int posY;
        int width;
        int height;

        private String name;
        private ItemStack stack;

        private boolean type;
        private boolean active;

        public Slot(int id, String name, ItemStack stack, boolean type) {
            this.id = id;
            this.name = name;
            this.stack = stack;
            this.width = 400;
            this.height = 72;
            this.type = type;
            this.active = false;
        }

        @Override
        public int getSlotHeight() {
            return height;
        }

        @Override
        public int getSlotWidth() {
            return width;
        }

        @Override
        public int getSlotX() {
            return posX;
        }

        @Override
        public int getSlotY() {
            return posY;
        }

        @Override
        public void setSlotX(int x) {
            posX = x;
        }

        @Override
        public void setSlotY(int y) {
            posY = y;
        }

        @Override
        public void setSlotWidth(int width) {
            this.width = width;
        }

        public void setActive(boolean active) {
            this.active = active;
        }

        @Override
        public void drawSlot(int mouseX, int mouseY, float partialTicks) {

            if(hovered(mouseX, mouseY)) {
                RoundedShaderRenderer.getInstance().drawRoundRect(posX-2, posY-2, width+4, height+4, 10, 0xFFFFFFFF);
            }
            if(active)
                RoundedGradientShaderRenderer.getInstance().drawRoundRect(posX, posY, width, height, 8, Gui.GRADIENT_BUTTON_1, Gui.GRADIENT_BUTTON_2);
            else
                RoundedShaderRenderer.getInstance().drawRoundRect(posX, posY, width, height, 8, Gui.BLACK_2);

            RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();

            GlStateManager.pushMatrix();

            GlStateManager.translate(posX, posY, 0);
            GlStateManager.scale(2F, 2F, 1);
            GlStateManager.translate(-posX, -posY, 0);

            RenderHelper.enableGUIStandardItemLighting();
            renderItem.zLevel = 100F;
            renderItem.renderItemIntoGUI(this.stack, this.posX + 8, this.posY + (height/4)-8);
            GlStateManager.enableRescaleNormal();

            RenderHelper.disableStandardItemLighting();
            GlStateManager.popMatrix();
            FzClient.getInstance().getTTFFontRenderers().get(24).drawCenteredStringVertically(this.name, this.posX + 56, this.posY + (this.height / 2), active ? Gui.TEXT_COLOR_ON_GRADIENT : 0xFFFFFFFF);

        }

        @Override
        public void onClick(int mouseX, int mouseY, int mouseButton) {
            mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
            if(type) {
                for(FzSlot slot : listType.getSlots()) {
                    ((Slot)(slot)).setActive(false);
                }

                this.active = true;
                mc.player.connection.sendPacket(new CPacketGuiOpener(EnumGui.MARKET_ITEM_LIST, id));
            }
            else {
                mc.player.connection.sendPacket(new CPacketGuiOpener(EnumGui.MARKET_ITEM, id));
            }
        }
    }
}

