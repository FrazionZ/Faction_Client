package fz.frazionz.api.gsonObj;


import fz.frazionz.api.HTTPEndpoints;
import fz.frazionz.utils.FzUtils;
import net.minecraft.client.renderer.texture.DynamicTexture;

import java.io.IOException;

public class SuccessObj {

    private final boolean isObtain;
    private String id;
    private Object obtainVal;
    private String title;
    private String description;
    private String imgMinia;
    private int progress;
    private Type type;

    public SuccessObj(String id, Object obtainVal, Type type, String title, String description, boolean isObtain, String imgMinia, int progress){
        this.id = id;
        this.obtainVal = obtainVal;
        this.type = type;
        this.title = title;
        this.description = description;
        this.isObtain = isObtain;
        this.imgMinia = imgMinia;
        this.progress = progress;
    }

    public String getId() {
        return id;
    }

    public Object getObtainVal() {
        return obtainVal;
    }

    public String getTitle() {
        return title;
    }

    public  void setTitle(String title) {
        this.title = title;
    }

    public Type getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public String getImgMinia() {
        return imgMinia;
    }
    
    public int getProgress() {
        return progress;
    }

    public boolean isObtain() {
        return isObtain;
    }

    @Override
    public String toString() {
        return "Success{" +
                "id='" + id + '\'' +
                ", obtainVal=" + obtainVal +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", type=" + type +
                '}';
    }

    public static class Type {

        private int id;
        private String action;
        private String display;

        public Type(int id, String action, String display){
            this.id = id;
            this.action = action;
            this.display = display;
        }

        public int getId() {
            return id;
        }

        public String getAction() {
            return action;
        }

        public String getDisplay() {
            return display;
        }

        @Override
        public String toString() {
            return "Type{" +
                    "id=" + id +
                    ", action='" + action + '\'' +
                    ", display='" + display + '\'' +
                    '}';
        }
    }
}
