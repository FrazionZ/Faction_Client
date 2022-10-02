package fz.frazionz.client.gui.custom;

import fz.frazionz.FzClient;
import fz.frazionz.client.gui.GuiFrazionZInterface;
import fz.frazionz.client.gui.buttons.GuiHoverButton;
import fz.frazionz.client.gui.custom.help.CustomGui;
import fz.frazionz.client.gui.custom.help.CustomObject;
import fz.frazionz.client.gui.list.GuiListFrazionZInterface;
import fz.frazionz.client.gui.utils.RoundedShaderRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.gui.GuiScreen;

import java.io.IOException;

public class GuiCustom extends GuiFrazionZInterface {

    private CustomGui gui;
    private GuiListFrazionZInterface list;
    public GuiCustom(GuiScreen parent, Minecraft mc, String json) {
        super(parent, mc);
        this.gui = CustomGui.fromJson(json);
        this.title = this.gui.getTitle();
        this.drawButtonLater = true;
        System.out.println("GuiCustom");
    }

    @Override
    public void initGui() {
        super.initGui();
        switch(gui.getType()) {
            case LIST:
                System.out.println("LIST");
                list = new GuiListFrazionZInterface(this, this.mc, this.guiLeft, this.guiLeft + this.xSize, this.guiTop + 45, this.guiTop + this.ySize - 38, 36);
                GuiListExtended.IGuiListEntry[] entries = new GuiListExtended.IGuiListEntry[gui.getObjects().size()];
                int i = 0;
                for(CustomObject obj : gui.getObjects()) {
                    entries[i++] = new CustomEntry(obj.getName());
                }
                list.setListEntry(entries);
                i = 0;
                for(fz.frazionz.client.gui.custom.help.CustomButton button : gui.getButtons()) {
                    int x = this.guiLeft + (i+1)*(this.xSize/(gui.getButtons().size()+1)) - button.getWidth()/2;
                    int y = this.guiTop + this.ySize - (int)(button.getHeight()*1.5);
                    this.buttonList.add(new CustomButton(i++, x, y, button));
                }
                break;
            case MENU:
            case ERROR:
            case CONFIRM:
            default:
                break;
        }
    }

    @Override
    protected void actionPerformed(GuiButton button, int keyCode) throws IOException {
        ((GuiCustom.CustomButton) button).action();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.list.drawScreen(mouseX, mouseY, partialTicks);
        FzClient.getInstance().getTTFFontRenderers().get(16).drawCenteredStringVertically(gui.getInfos(), this.guiLeft + 40, this.guiTop + 35, 0xFFFFFFFF);
        this.drawButtons(mouseX, mouseY, partialTicks);
    }

    protected class CustomEntry implements GuiListExtended.IGuiListEntry {

        private String name;

        public CustomEntry(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void drawEntry(int slotIndex, int x, int y, int width, int height, int mouseX, int mouseY, boolean isSelected, float partialTicks)
        {
            RoundedShaderRenderer.getInstance().drawRoundRect(x, y, width, height, 2, BLACK_2);
            FzClient.getInstance().getTTFFontRenderers().get(20).drawCenteredStringVertically(name, x + 10, y + (height/2), 0xFFFFFFFF);
        }

        public boolean mousePressed(int slotIndex, int mouseX, int mouseY, int mouseEvent, int relativeX, int relativeY)
        {
            return false;
        }

        public void mouseReleased(int slotIndex, int x, int y, int mouseEvent, int relativeX, int relativeY) {}

        public void updatePosition(int slotIndex, int x, int y, float partialTicks) {}
    }

    protected class CustomButton extends GuiHoverButton {

        fz.frazionz.client.gui.custom.help.CustomButton button;
        private CustomButton(int id, int x, int y, fz.frazionz.client.gui.custom.help.CustomButton button) {
            super(id, x, y, button.getWidth(), button.getHeight(), button.getName());
            this.button = button;
        }

        @Override
        protected int buttonColor() {
            return button.getColor();
        }

        public void action() {
            button.getAction().processAction(lastScreen);
        }
    }
}
