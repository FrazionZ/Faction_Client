package fz.frazionz.client.gui.custom.help;

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
}
