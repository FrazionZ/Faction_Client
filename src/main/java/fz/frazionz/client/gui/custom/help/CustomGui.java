package fz.frazionz.client.gui.custom.help;

import java.util.ArrayList;
import java.util.List;

public class CustomGui {

    private String title;
    private String infos;
    private List<CustomObject> objects = new ArrayList<>();
    private List<CustomButton> buttons = new ArrayList<>();

    public CustomGui(String title, String infos) {
        this.title = title;
        this.infos = infos;
    }

    public CustomGui(String title, String infos, List<CustomObject> objects, List<CustomButton> buttons) {
        this.title = title;
        this.infos = infos;
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

    public void setButtons(List<CustomButton> buttons) {
        this.buttons = buttons;
    }

    public String toJson() {
        String json = "{";
        json += "\"title\":\"" + title + "\",";
        json += "\"infos\":\"" + infos + "\",";
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
