package fz.frazionz.client.gui.custom.help.actions;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import fz.frazionz.enums.EnumGui;
import fz.frazionz.packets.client.CPacketShopTrade;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

public class CustomButtonActionPacket extends CustomButtonAction {

    private String packetName;
    private String packetInformations;

    public CustomButtonActionPacket(String packetName, String packetInformations) {
        super(ButtonAction.SEND_PACKET);
        this.packetName = packetName;
        this.packetInformations = packetInformations;
    }

    public String getPacketName() {
        return packetName;
    }

    public void setPacketName(String packetName) {
        this.packetName = packetName;
    }

    public String getPacketInformations() {
        return packetInformations;
    }

    public void setPacketInformations(String packetInformations) {
        this.packetInformations = packetInformations;
    }

    @Override
    public void processAction(GuiScreen lastScreen) {
        switch(packetName) {
            case "ShopTrade":
                Minecraft.getMinecraft().player.connection.sendPacket( new CPacketShopTrade(EnumGui.CUSTOM_GUI, 0, 200, 0));
                Minecraft.getMinecraft().displayGuiScreen(lastScreen);
                break;
        }
    }

    public String toJson() {
        return "{\"action\": \"" + action.toString() + "\", \"packetId\": \"" + packetName + "\", \"packetInformations\": " + packetInformations + "}";
    }

    public static CustomButtonActionPacket fromJson(String json) {
        JsonObject object = new JsonParser().parse(json).getAsJsonObject();
        String packetName = object.get("packetName").getAsString();
        String packetInformations = object.get("packetInformations").toString();
        return new CustomButtonActionPacket(packetName, packetInformations);
    }
}
