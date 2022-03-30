package fz.frazionz.api.data;

public class Itemdata {

    private int type;
    private String name;
    private String text_type;

    public Itemdata(int type, String name, String text_type) {
        this.type = type;
        this.name = name;
        this.text_type = text_type;
    }

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getText_type() {
        return text_type;
    }
}
