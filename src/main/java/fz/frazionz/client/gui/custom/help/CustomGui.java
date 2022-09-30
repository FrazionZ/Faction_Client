package fz.frazionz.client.gui.custom.help;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.client.gui.GuiScreen;

import java.util.ArrayList;
import java.util.List;

public class CustomGui {

    private String title;
    private String infos;
    private CustomGuiType type;
    private List<CustomObject> objects = new ArrayList<>();
    private List<CustomButton> buttons = new ArrayList<>();

    public CustomGui() {}
    public CustomGui(String title, String infos, CustomGuiType type) {
        this.title = title;
        this.infos = infos;
        this.type = type;
    }

    public CustomGui(String title, String infos, CustomGuiType type, List<CustomObject> objects, List<CustomButton> buttons) {
        this.title = title;
        this.infos = infos;
        this.type = type;
        this.objects = objects;
        this.buttons = buttons;
    }

    public void addObject(CustomObject object) {
        objects.add(object);
    }

    public void addButton(CustomButton button) {
        buttons.add(button);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInfos() {
        return infos;
    }

    public void setInfos(String infos) {
        this.infos = infos;
    }

    public List<CustomObject> getObjects() {
        return objects;
    }

    public void setObjects(List<CustomObject> objects) {
        this.objects = objects;
    }

    public List<CustomButton> getButtons() {
        return buttons;
    }

    public CustomGuiType getType() {
        return type;
    }

    public void setType(CustomGuiType type) {
        this.type = type;
    }

    public void setButtons(List<CustomButton> buttons) {
        this.buttons = buttons;
    }

    public static CustomGui fromJson(String json) {
        CustomGui gui = new CustomGui();
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        gui.title = jsonObject.get("title").getAsString();
        gui.infos = jsonObject.get("infos").getAsString();
        gui.type = CustomGuiType.valueOf(jsonObject.get("type").getAsString());
        for (JsonElement element : jsonObject.get("objects").getAsJsonArray()) {
            gui.addObject(CustomObject.fromJson(element.toString()));
        }
        for (JsonElement element : jsonObject.get("buttons").getAsJsonArray()) {
            gui.addButton(CustomButton.fromJson(element.toString()));
        }
        return gui;
    }

    public String toJson() {
        String json = "{";
        json += "\"title\":\"" + title + "\",";
        json += "\"infos\":\"" + infos + "\",";
        json += "\"type\":\"" + type.name() + "\",";
        json += "\"objects\":[";
        for (int i = 0; i < objects.size(); i++) {
            json += objects.get(i).toJson();
            if (i < objects.size() - 1) {
                json += ",";
            }
        }
        json += "],";
        json += "\"buttons\":[";
        for (int i = 0; i < buttons.size(); i++) {
            json += buttons.get(i).toJson();
            if (i < buttons.size() - 1) {
                json += ",";
            }
        }
        json += "]";
        json += "}";
        return json;
    }
}
