package fz.frazionz.client.gui.custom.help;

public class CustomButton {

    private String name;
    private int color;
    private int width;
    private int height;


    public CustomButton(String name, int color, int width, int height) {
        this.name = name;
        this.color = color;
        this.width = width;
        this.height = height;
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

    public String toJson() {
        return "{\"name\": \"" + name + "\", \"color\": " + color + ", \"width\": " + width + ", \"height\": " + height + "}";
    }
}
