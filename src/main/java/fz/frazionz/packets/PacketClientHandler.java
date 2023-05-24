package fz.frazionz.packets;

import fz.frazionz.client.gui.custom.GuiCustom;
import fz.frazionz.client.gui.shop.GuiShopMenu;
import fz.frazionz.enums.EnumGui;
import fz.frazionz.client.gui.market.GuiMarket;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.server.SPacketCustomPayload;

public class PacketClientHandler {

    static Minecraft mc = Minecraft.getMinecraft();

    public static void handleCustomPayload(SPacketCustomPayload packetIn) {
        switch(EnumGui.fromKey(packetIn.getChannelName())) {
            case MARKET_LIST:
                mc.displayGuiScreen(new GuiMarket(mc.currentScreen, mc, packetIn.getBufferData().readString(80000)));
                break;
            case MARKET_ITEM_LIST:
                if(mc.currentScreen instanceof GuiMarket) {
                    ((GuiMarket) mc.currentScreen).setItems(packetIn.getBufferData().readString(80000));
                }
                //mc.displayGuiScreen(new GuiMarketItemList(mc.currentScreen, mc, packetIn.getBufferData().readString(80000)));
                break;
            case MARKET_ITEM:
                //mc.displayGuiScreen(new GuiMarketItem(mc.currentScreen, mc, packetIn.getBufferData().readString(80000)));
                break;
            case CUSTOM_GUI:
                mc.displayGuiScreen(new GuiCustom(mc.currentScreen, mc, packetIn.getBufferData().readString(80000)));
                break;
            case SHOP_MENU:
                mc.displayGuiScreen(new GuiShopMenu(mc.currentScreen, mc, packetIn.getBufferData().readString(80000)));
                break;
            default:
                break;
        }

    }

}
