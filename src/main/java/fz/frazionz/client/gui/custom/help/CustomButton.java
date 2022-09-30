package fz.frazionz.client.gui.custom.help;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import fz.frazionz.client.gui.custom.help.actions.CustomButtonAction;

public class CustomButton {

    private String name;
    private int color;
    private int width;
    private int height;
    private CustomButtonAction action;


    public CustomButton(String name, int color, int width, int height, CustomButtonAction action) {
        this.name = name;
        this.color = color;
        this.width = width;
        this.height = height;
        this.action = action;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public CustomButtonAction getAction() {
        return action;
    }

    public void setAction(CustomButtonAction action) {
        this.action = action;
    }

    public String toJson() {
        return "{\"name\": \"" + name + "\", \"color\": " +
                color + ", \"width\": " +
                width + ", \"height\": " +
                height + ", \"action\": " +
                action.toJson() + "}";
    }

    public static CustomButton fromJson(String json) {
        JsonObject object = new JsonParser().parse(json).getAsJsonObject();
        return new CustomButton(
                object.get("name").getAsString(),
                object.get("color").getAsInt(),
                object.get("width").getAsInt(),
                object.get("height").getAsInt(),
                CustomButtonAction.fromJson(object.get("action").toString())
        );
    }
}
