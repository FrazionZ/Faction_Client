package fz.frazionz.client.gui.list.slot;

import fz.frazionz.FzClient;
import fz.frazionz.client.gui.utils.RoundedGradientShaderRenderer;
import fz.frazionz.client.gui.utils.RoundedShaderRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.Gui;
import net.minecraft.init.SoundEvents;

public class ToggleInputSlot extends SimpleSlot {

    protected boolean active;
    protected String label;
    protected String description;


    public ToggleInputSlot(String label, int x, int y, int width) {
        this(label, x, y, width, "");
    }

    public ToggleInputSlot(String label, int x, int y, int width, String description) {
        super(x, y, width, 68);
        this.active = false;
        this.label = label;
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }

    public String getLabel() {
        return label;
    }

    public String getDescription() {
        return description;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public void mousePressed(int mouseX, int mouseY, int mouseButton) {
        if(mouseButton == 0 && isMouseOver(mouseX, mouseY)) {
            active = !active;
            Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
        }
    }

    protected boolean isMouseOver(int mouseX, int mouseY) {
        return mouseX >= x+ width - 80 && mouseX <= this.x + this.width && mouseY >= this.y+this.height/2-20 && mouseY <= this.y+this.height/2+20;
    }
    @Override
    public void drawSlot(int mouseX, int mouseY, float partialTicks) {
        FzClient.getInstance().getTTFFontRenderers().get(18).drawString(this.label, this.x, this.y, 0xFFFFFFFF);
        int descriptionHeight = FzClient.getInstance().getTTFFontRenderers().get(14).getHeight(this.description);
        FzClient.getInstance().getTTFFontRenderers().get(14).drawString(this.description, this.x, this.y+this.height-descriptionHeight, 0xFF8F8F8F);
        drawToggle();
    }

    private void drawToggle() {
        if(active) {
            RoundedGradientShaderRenderer.getInstance().drawRoundRect(x+ width - 80, this.y+this.height/2-20, 80, 40, 19f, Gui.GRADIENT_BUTTON_1, Gui.GRADIENT_BUTTON_2);
            RoundedShaderRenderer.getInstance().drawRoundRect(x+ width - 6-28, this.y+this.height/2-14, 28, 28, 13f, 0xFFFFFFFF);
        }
        else {
            RoundedShaderRenderer.getInstance().drawRoundRect(x+ width - 80, this.y+this.height/2-20, 80, 40, 19f, Gui.BLACK_1);
            RoundedShaderRenderer.getInstance().drawRoundRect(x+ width - 80 + 6, this.y+this.height/2-14, 28, 28, 13f, 0xFFFFFFFF);
        }
    }
}
