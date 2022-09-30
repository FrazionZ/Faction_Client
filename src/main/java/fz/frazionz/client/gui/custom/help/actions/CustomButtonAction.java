package fz.frazionz.client.gui.custom.help.actions;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;

public class CustomButtonAction {

    protected ButtonAction action;

    public CustomButtonAction(ButtonAction action) {
        this.action = action;
    }

    public ButtonAction getAction() {
        return action;
    }

    public void setAction(ButtonAction action) {
        this.action = action;
    }

    public String toJson() {
        return "{\"action\": " + action.toString() + "}";
    }

    public void processAction(GuiScreen lastScreen) {
        switch (action) {
            case CANCEL:
                Minecraft.getMinecraft().displayGuiScreen(lastScreen);
            case NOTHING:
            default:
                break;
        }
    }

    public static CustomButtonAction fromJson(String json) {
        JsonObject object = new JsonParser().parse(json).getAsJsonObject();
        JsonElement element = object.get("action");
        ButtonAction action = ButtonAction.valueOf(element.getAsString());
        switch(action) {
            case SEND_PACKET:
                return CustomButtonActionPacket.fromJson(json);
            case OPEN_LINK:
            case CANCEL:
                return new CustomButtonAction(action);
            case NOTHING:
            default:
                return null;
        }
    }
}
