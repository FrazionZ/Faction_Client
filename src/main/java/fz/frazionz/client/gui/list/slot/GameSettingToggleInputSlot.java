package fz.frazionz.client.gui.list.slot;

import fz.frazionz.FzClient;
import fz.frazionz.client.gui.utils.RoundedGradientShaderRenderer;
import fz.frazionz.client.gui.utils.RoundedShaderRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.init.SoundEvents;

public class GameSettingToggleInputSlot extends ToggleInputSlot {

    protected GameSettings.Options option;

    public GameSettingToggleInputSlot(int x, int y, int width, GameSettings.Options optionIn) {
        this(I18n.format(optionIn.getTranslation()), x, y, width, "", optionIn);
    }

    public GameSettingToggleInputSlot(String label, int x, int y, int width, String description, GameSettings.Options optionIn) {
        super(label, x, y, width, description);
        this.option = optionIn;
        this.active = Minecraft.getMinecraft().gameSettings.getOptionOrdinalValue(optionIn);
    }
    @Override
    public void mousePressed(int mouseX, int mouseY, int mouseButton) {
        if(mouseButton == 0 && isMouseOver(mouseX, mouseY)) {
            active = !active;
            Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
            mc.gameSettings.invertBooleanOptionValue(option);
        }
    }
}
