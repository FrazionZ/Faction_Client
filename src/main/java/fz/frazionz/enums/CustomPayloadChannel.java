package fz.frazionz.enums;

public enum CustomPayloadChannel {

    AUTH_CODE("FZ|Auth_Code"),
    ;

    private String channel;
    CustomPayloadChannel(String s) {
        this.channel = s;
    }

    public String getChannel() {
        return channel;
    }
}
