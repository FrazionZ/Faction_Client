package fz.frazionz.client.gui.custom.help;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class CustomObject {

    private String name;

    public CustomObject(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toJson() {
        return "{\"name\": \"" + name + "\"}";
    }

    public static CustomObject fromJson(String json) {
        JsonObject object = new JsonParser().parse(json).getAsJsonObject();
        return new CustomObject(object.get("name").getAsString());
    }
}
