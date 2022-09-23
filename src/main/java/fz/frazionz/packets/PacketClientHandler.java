package fz.frazionz.packets;

import fz.frazionz.enums.EnumGui;
import fz.frazionz.client.gui.shop.GuiMarketCategory;
import fz.frazionz.client.gui.shop.GuiMarketItemList;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.server.SPacketCustomPayload;

public class PacketClientHandler {

    static Minecraft mc = Minecraft.getMinecraft();

    public static void handleCustomPayload(SPacketCustomPayload packetIn) {
        switch(EnumGui.fromKey(packetIn.getChannelName())) {
            case MARKET_LIST:
                mc.displayGuiScreen(new GuiMarketCategory(mc.currentScreen, mc, packetIn.getBufferData().readString(80000)));
                break;
            case MARKET_ITEM_LIST:
                mc.displayGuiScreen(new GuiMarketItemList(mc.currentScreen, mc, packetIn.getBufferData().readString(80000)));
                break;
            case MARKET_ITEM:
                //mc.displayGuiScreen(new GuiMarketItem(mc.currentScreen, mc, packetIn.getBufferData().readString(80000)));
                break;
            default:
                break;
        }

    }

}
