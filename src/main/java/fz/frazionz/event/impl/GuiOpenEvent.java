package fz.frazionz.event.impl;

import fz.frazionz.event.EventCancelable;
import net.minecraft.client.gui.GuiScreen;

public class GuiOpenEvent extends EventCancelable {

    private GuiScreen gui;

    public GuiOpenEvent(GuiScreen gui) {
        super();
        this.gui = gui;
    }

    public GuiScreen getGui() {
        return gui;
    }

    public void setGui(GuiScreen gui) {
        this.gui = gui;
    }
}
