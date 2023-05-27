package fz.frazionz.client.gui.list.slot;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ButtonsSlotList extends SimpleSlotList {

    private final Minecraft mc = Minecraft.getMinecraft();
    private List<GuiButton> buttonList;

    public ButtonsSlotList(int x, int y, int width, int height) {
        super(x, y, width, height);
        buttonList = new ArrayList<>();
    }

    public ButtonsSlotList(int x, int y, int width, int height, List<GuiButton> buttonList) {
        super(x, y, width, height);
        this.buttonList = buttonList;
    }

    public List<GuiButton> getButtonList() {
        return buttonList;
    }

    public void setButtonList(List<GuiButton> buttonList) {
        this.buttonList = buttonList;
    }

    protected void initButtons() {}

    protected void clearButtons() {
        buttonList.clear();
    }

    @Override
    public void drawSlot(int mouseX, int mouseY, float partialTicks) {

    }

    protected void actionPerformed(GuiButton button, int mouseButton) throws IOException {}

    @Override
    public void mousePressed(int mouseX, int mouseY, int mouseButton) {
        for (GuiButton button : buttonList) {
            if (button.mousePressed(mc, mouseX, mouseY)) {
                try {
                    actionPerformed(button, mouseButton);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void updateSlotPosition() {
        buttonList.forEach(button -> {
            button.x = getSlotX() + button.x;
            button.y = getSlotY() + button.y;
        });
    }
}
