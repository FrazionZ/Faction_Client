package fz.frazionz.client.gui.custom.help.actions;

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

    public String toJson() {
        return "{\"action\": \"" + action.toString() + "\", \"packetName\": \"" + packetName + "\", \"packetInformations\": " + packetInformations + "}";
    }
}
