package fz.frazionz.event.impl;

import fz.frazionz.event.Event;
import net.minecraft.client.gui.GuiScreen;

public class GuiCloseEvent extends Event {

    private GuiScreen gui;

    public GuiCloseEvent(GuiScreen gui) {
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
