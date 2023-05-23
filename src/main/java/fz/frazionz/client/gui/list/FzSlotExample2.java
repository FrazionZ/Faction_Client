package fz.frazionz.client.gui.list;

import fz.frazionz.FzClient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.Gui;
import net.minecraft.init.SoundEvents;

public class FzSlotExample2 implements FzSlot {

    private int x;
    private int y;
    private int width = 150;
    private int number;
    private int color = 0xFFFF0000;

    public FzSlotExample2(int number) {
        this.number = number;
    }

    @Override
    public int getSlotHeight() {
        return 80;
    }

    @Override
    public int getSlotWidth() {
        return width;
    }

    @Override
    public int getSlotX() {
        return x;
    }

    @Override
    public int getSlotY() {
        return y;
    }

    @Override
    public void setSlotX(int x) {
        this.x = x;
    }

    @Override
    public void setSlotY(int y) {
        this.y = y;
    }

    @Override
    public void setSlotWidth(int width) {
        this.width = width;
    }

    @Override
    public void setSlotHeight(int height) {

    }

    @Override
    public void drawSlot(int mouseX, int mouseY, float partialTicks) {
        if(hovered(mouseX, mouseY))
            Gui.drawRect(x-2, y-2, x + getSlotWidth()+2, y + getSlotHeight()+2, 0xFFFFFFFF);
        Gui.drawRect(x, y, x + getSlotWidth(), y + getSlotHeight(), color);
        FzClient.getInstance().getTTFFontRenderers().get(24).drawCenteredString("Slot " + number, x + getSlotWidth() / 2, y + getSlotHeight() / 2 - 5, 0xFFFFFFFF);
    }

    @Override
    public void onClick(int mouseX, int mouseY, int mouseButton) {
        Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
        if(color == 0xFFFF0000)
            color = 0xFF0000FF;
        else
            color = 0xFFFF0000;
    }

    @Override
    public void updateSlotPosition() {

    }
}
