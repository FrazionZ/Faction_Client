package fz.frazionz.client.gui.market;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fz.frazionz.FzClient;
import fz.frazionz.client.gui.GuiFzBaseScreen;
import fz.frazionz.client.gui.buttons.GuiMenuButton;
import fz.frazionz.client.gui.impl.ExcludeScaledResolution;
import fz.frazionz.client.gui.list.*;
import fz.frazionz.client.gui.utils.RoundedGradientShaderRenderer;
import fz.frazionz.client.gui.utils.RoundedShaderRenderer;
import fz.frazionz.enums.EnumGui;
import fz.frazionz.packets.client.CPacketGuiOpener;
import fz.frazionz.packets.client.CPacketShopTrade;
import fz.frazionz.utils.FzUtils;
import fz.frazionz.utils.MathUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;

public class GuiMarket extends GuiFzBaseScreen implements ExcludeScaledResolution {
		
	private GuiSlotList listType;
    private GuiSlotList listItems;
    private MarketType[] types;
    private MarketItem[] items;
    private MarketItem selected;
	
	public GuiMarket(GuiScreen lastScreen, Minecraft mc, String json)
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
        int listWidth = 760;
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

        drawPlayerHead(width-64-32, 64, 32, 32);

        this.drawButtons(mouseX, mouseY, partialTicks);
        this.listType.drawScreen(mouseX, mouseY, partialTicks);
        this.listItems.drawScreen(mouseX, mouseY, partialTicks);
        this.drawButtons(mouseX, mouseY, partialTicks);

        String money = FzUtils.convertMoney(FzClient.getInstance().getFactionProfile().getMoney());
        String coins = "  Coins";
        int moneyWidth = FzClient.getInstance().getTTFFontRenderers().get(18).getWidth(money);
        int coinsWidth = FzClient.getInstance().getTTFFontRenderers().get(18).getWidth(coins);
        FzClient.getInstance().getTTFFontRenderers().get(18).drawCenteredStringVertically(money, width-64-32-16-coinsWidth-moneyWidth, 64+16, 0xFFFFFFFF);
        FzClient.getInstance().getTTFFontRenderers().get(18).drawCenteredStringVertically(coins, width-64-32-16-coinsWidth, 64+16, Gui.COLOR_2);
        //this.fontRenderer.drawString(s, this.width / 2 - this.fontRenderer.getStringWidth(s) / 2, this.guiTop + this.ySize - 22, 16777215, true);

        RoundedGradientShaderRenderer.getInstance().drawRoundTrapeze(width/2-230, 64, 460, 52, 8, 20, Gui.GRADIENT_BUTTON_1, Gui.GRADIENT_BUTTON_2);
        FzClient.getInstance().getTTFFontRenderers().get(32).drawCenteredString("Market", width/2, 64+26, Gui.TEXT_COLOR_ON_GRADIENT);
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
        int headHeight;

        private String name;
        private ItemStack stack;

        private boolean type;
        private boolean active;

        private int totalSellItems = 1;
        private int totalBuyItems = 1;

        private List<GuiButton> buttonList;
        int left = 0;

        public Slot(int id, String name, ItemStack stack, boolean type) {
            this.id = id;
            this.name = name;
            this.stack = stack;
            this.width = 400;
            this.height = 72;
            this.headHeight = height;
            this.type = type;
            this.active = false;
            if(!type) {
                this.buttonList = new ArrayList<GuiButton>();
            }
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

        @Override
        public void setSlotHeight(int height) {
            this.height = height;
        }

        public void setActive(boolean active) {
            this.active = active;
        }

        protected void clearButtons() {
            this.buttonList.clear();
        }

        protected void addButtons() {
            int gap = 64;
            int elementGap = 16;
            int buttonWidth = 280;
            int buttonHeight = 52;

            int left = this.posX + this.width / 2 - buttonWidth - gap/2;
            this.buttonList.add(new GuiMenuButton(1, left, posY+369, buttonWidth, buttonHeight, "Acheter", 0xFF052B05, 0xFF69C82F, 0xFF26910B));
            this.buttonList.add(new GuiMenuButton(2, left + buttonWidth + gap, posY+369, buttonWidth, buttonHeight, "Vendre", 0xFF260503, 0xFFD44C2E, 0xFF910B0B));
            this.buttonList.add(new GuiMenuButton(3, left + buttonWidth + gap, posY+369+elementGap+buttonHeight, buttonWidth, buttonHeight, "Vendre mon Inventaire", 0xFF260503,0xFFD44C2E, 0xFF910B0B));

            int top = posY+headHeight+18+43+2+42+32;
            this.buttonList.add(new GuiMenuButton(4, left, top, 48, 48, "-"));
            this.buttonList.add(new GuiMenuButton(5, left + buttonWidth - 48, top, 48, 48, "+"));
            this.buttonList.add(new GuiMenuButton(7, left + buttonWidth + gap, top, 48, 48, "-"));
            this.buttonList.add(new GuiMenuButton(6, left + buttonWidth*2 + gap - 48, top, 48, 48, "+"));
        }

        protected void actionPerformed(GuiButton button, int mouseButton) throws IOException {
            switch (button.id)
            {
                case 1:
                    mc.player.connection.sendPacket(new CPacketShopTrade(EnumGui.MARKET_ITEM, id, this.totalBuyItems, 0));
                    break;
                case 2:
                    mc.player.connection.sendPacket(new CPacketShopTrade(EnumGui.MARKET_ITEM, id, this.totalSellItems, 1));
                    break;
                case 3:
                    mc.player.connection.sendPacket(new CPacketShopTrade(EnumGui.MARKET_ITEM, id, 3000, 1));
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
            }
            mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
        }

        @Override
        public void updateSlotPosition() {
            if(!type && active) {
                int gap = 64;
                int elementGap = 16;
                int buttonWidth = 280;
                int buttonHeight = 52;

                this.buttonList.get(0).y = posY + 369;
                this.buttonList.get(1).y = posY + 369;
                this.buttonList.get(2).y = posY + 369 + elementGap + buttonHeight;

                this.buttonList.get(3).y = posY+headHeight+18+43+2+42+32;
                this.buttonList.get(4).y = posY+headHeight+18+43+2+42+32;
                this.buttonList.get(5).y = posY+headHeight+18+43+2+42+32;
                this.buttonList.get(6).y = posY+headHeight+18+43+2+42+32;
            }
            left = this.posX + this.width / 2 - 280 - 32;
        }

        @Override
        public void drawSlot(int mouseX, int mouseY, float partialTicks) {
            boolean hovered = hovered(mouseX, mouseY);
            if(hovered && !active) {
                RoundedShaderRenderer.getInstance().drawRoundRect(posX-2, posY-2, width+4, headHeight+4, 10, 0xFFFFFFFF);
            }
            if(!type && active) {
                int gap = 64;
                int elementGap = 16;
                int buttonWidth = 280;
                int buttonHeight = 52;

                RoundedShaderRenderer.getInstance().drawRoundRect(posX, posY, width, height, 8, Gui.BLACK_2);
                RoundedGradientShaderRenderer.getInstance().drawRoundRect(posX, posY, width, headHeight, 8, Gui.GRADIENT_BUTTON_1, Gui.GRADIENT_BUTTON_2);

                int top = posY + headHeight + 18;
                FzClient.getInstance().getTTFFontRenderers().get(18).drawCenteredStringHorizontally("Achat", left + buttonWidth/2, top, 0xFFFFFFFF);
                FzClient.getInstance().getTTFFontRenderers().get(18).drawCenteredStringHorizontally("Vente", left + buttonWidth + gap + buttonWidth/2, top, 0xFFFFFFFF);

                top += 43;
                RoundedShaderRenderer.getInstance().drawRoundRect(left+buttonWidth-100, top, 100, 28, 4, Gui.BLACK_4);
                RoundedShaderRenderer.getInstance().drawRoundRect(left+buttonWidth*2+gap-100, top, 100, 28, 4, Gui.BLACK_4);
                top += 2;
                FzClient.getInstance().getTTFFontRenderers().get(16).drawString("Prix d'Achat", left, top, 0xFFFFFFFF);
                FzClient.getInstance().getTTFFontRenderers().get(16).drawString("Prix de Vente", left + buttonWidth + gap, top, 0xFFFFFFFF);
                FzClient.getInstance().getTTFFontRenderers().get(16).drawCenteredStringHorizontally(Double.toString(selected.getBuyPrice()), left+buttonWidth-50, top, 0xFFFFFFFF);
                FzClient.getInstance().getTTFFontRenderers().get(16).drawCenteredStringHorizontally(Double.toString(selected.getSellPrice()), left+buttonWidth*2+gap-50, top, 0xFFFFFFFF);

                top += 42;
                FzClient.getInstance().getTTFFontRenderers().get(16).drawString("Quantité", left, top, 0xFFFFFFFF);
                FzClient.getInstance().getTTFFontRenderers().get(16).drawString("Quantité", left + buttonWidth + gap, top, 0xFFFFFFFF);
                top+= 32;
                // quantity
                RoundedShaderRenderer.getInstance().drawRoundRect(left+64, top, 152, 48, 4, Gui.BLACK_4);
                RoundedShaderRenderer.getInstance().drawRoundRect(left+buttonWidth+gap+64, top, 152, 48, 4, Gui.BLACK_4);
                top += 24;
                FzClient.getInstance().getTTFFontRenderers().get(16).drawCenteredString(Integer.toString(totalBuyItems), left+buttonWidth/2, top, 0xFFFFFFFF);
                FzClient.getInstance().getTTFFontRenderers().get(16).drawCenteredString(Integer.toString(totalSellItems), left+buttonWidth+gap+buttonWidth/2, top, 0xFFFFFFFF);

                top+= 24+16;
                FzClient.getInstance().getTTFFontRenderers().get(16).drawString("Prix à payer", left, top, 0xFFFFFFFF);
                FzClient.getInstance().getTTFFontRenderers().get(16).drawString("Argent gagné", left + buttonWidth + gap, top, 0xFFFFFFFF);

                top += 32;
                RoundedShaderRenderer.getInstance().drawRoundRect(left, top, 280, 48, 4, Gui.BLACK_4);
                RoundedShaderRenderer.getInstance().drawRoundRect(left+buttonWidth+gap, top, 280, 48, 4, Gui.BLACK_4);

                top += 24;
                FzClient.getInstance().getTTFFontRenderers().get(16).drawCenteredString(MathUtils.roundAvoid(this.totalBuyItems * selected.getBuyPrice(), 2) + " Coins", left + buttonWidth/2, top, 0xFFFFFFFF);
                FzClient.getInstance().getTTFFontRenderers().get(16).drawCenteredString( MathUtils.roundAvoid(this.totalSellItems * selected.getSellPrice(), 2) + " Coins", left + buttonWidth + gap + buttonWidth/2, top, 0xFFFFFFFF);


                for(GuiButton button : buttonList) {
                    button.drawButton(mc, mouseX, mouseY, partialTicks);
                }
            }
            else {
                if(active)
                    RoundedGradientShaderRenderer.getInstance().drawRoundRect(posX, posY, width, headHeight, 8, Gui.GRADIENT_BUTTON_1, Gui.GRADIENT_BUTTON_2);
                else
                    RoundedShaderRenderer.getInstance().drawRoundRect(posX, posY, width, headHeight, 8, Gui.BLACK_2);
            }

            RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();

            GlStateManager.pushMatrix();

            GlStateManager.translate(posX, posY, 0);
            GlStateManager.scale(2F, 2F, 1);
            GlStateManager.translate(-posX, -posY, 0);

            RenderHelper.enableGUIStandardItemLighting();
            renderItem.zLevel = 100F;
            renderItem.renderItemIntoGUI(this.stack, this.posX + 8, this.posY + (headHeight/4)-8);
            GlStateManager.enableRescaleNormal();

            RenderHelper.disableStandardItemLighting();
            GlStateManager.popMatrix();
            FzClient.getInstance().getTTFFontRenderers().get(24).drawCenteredStringVertically(this.name, this.posX + 56, this.posY + (this.headHeight / 2), active ? Gui.TEXT_COLOR_ON_GRADIENT : 0xFFFFFFFF);

        }

        @Override
        public void onClick(int mouseX, int mouseY, int mouseButton) {
            if(type) {
                for(FzSlot slot : listType.getSlots()) {
                    ((Slot)(slot)).setActive(false);
                }
                this.active = true;
                mc.player.connection.sendPacket(new CPacketGuiOpener(EnumGui.MARKET_ITEM_LIST, id));
                mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
            }
            else {
                if(mouseX >= posX && mouseY >= posY && mouseX < posX + width && mouseY < posY + headHeight) {
                    if (this.active) {
                        this.active = false;
                        this.height = headHeight;
                        clearButtons();
                    } else {
                        int i = 0;
                        for (FzSlot slot : listItems.getSlots()) {
                            ((Slot) (slot)).setActive(false);
                            ((Slot) (slot)).clearButtons();
                            slot.setSlotHeight(headHeight);
                            if(slot == this)
                                selected = items[i];
                            i++;
                        }

                        this.active = true;
                        mc.player.connection.sendPacket(new CPacketGuiOpener(EnumGui.MARKET_ITEM, id));
                        this.height = 508;
                        addButtons();
                    }
                    listItems.updateScreen();
                    mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
                }
                else {
                    for (GuiButton button : buttonList) {
                        if (button.mousePressed(mc, mouseX, mouseY)) {
                            try {
                                actionPerformed(button, mouseButton);
                            }
                            catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }
}

