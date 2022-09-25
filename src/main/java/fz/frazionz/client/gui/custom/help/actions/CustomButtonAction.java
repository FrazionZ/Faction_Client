package fz.frazionz.client.gui.custom.help.actions;

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
}
