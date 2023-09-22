package fz.frazionz.client.gui.list.slot.keybinds;

import fz.frazionz.FzClient;
import fz.frazionz.client.gui.buttons.GuiFzButton;
import fz.frazionz.client.gui.buttons.GuiHoverButton;
import fz.frazionz.client.gui.list.slot.SimpleSlot;
import fz.frazionz.client.gui.options.GuiOptionsMenu;
import fz.frazionz.client.gui.utils.RoundedShaderRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiKeyBindingList;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.text.TextFormatting;

public class KeySlot extends SimpleSlot  {

    protected final KeyBinding keyBinding;
    protected final KeyBindButton btnChange;
    protected final GuiHoverButton btnReset;


    public KeySlot(KeyBinding keybind) {
        this(keybind, 0, 0, 100, 40);
    }

    public KeySlot(KeyBinding keybind, int x, int y, int width, int height) {
        super(x, y, width, height);
        this.keyBinding = keybind;
        this.btnReset = new GuiHoverButton(0, x+width-100, y, 100, 40, I18n.format("controls.reset"));
        this.btnChange = new KeyBindButton(1, x+width-100-240-24, y, 240, 40, GameSettings.getKeyDisplayString(keybind.getKeyCode()));
    }

    @Override
    public void drawSlot(int mouseX, int mouseY, float partialTicks) {
        if(mc.currentScreen instanceof GuiOptionsMenu) {
            String desc = I18n.format(keyBinding.getKeyDescription());
            btnChange.displayString = GameSettings.getKeyDisplayString(keyBinding.getKeyCode());
            boolean modifying = ((GuiOptionsMenu) mc.currentScreen).controlButtonId == this.keyBinding;
            boolean multiplePresence = false;
            if (this.keyBinding.getKeyCode() != 0)
            {
                for (KeyBinding keybinding : mc.gameSettings.keyBindings)
                {
                    if (keybinding != this.keyBinding && keybinding.getKeyCode() == this.keyBinding.getKeyCode())
                    {
                        multiplePresence = true;
                        break;
                    }
                }
            }

            FzClient.getInstance().getTTFFontRenderers().get(18).drawCenteredStringVertically(desc, x, y+20, 0xFFFFFFFF);
            btnChange.info = multiplePresence ? 2 : (modifying ? 1 : 0);
            btnChange.drawButton(mc, mouseX, mouseY, partialTicks);
            btnReset.enabled = keyBinding.getKeyCode() != keyBinding.getKeyCodeDefault();
            btnReset.drawButton(mc, mouseX, mouseY, partialTicks);
        }
    }

    @Override
    public void updateSlotPosition() {
        this.btnChange.x = this.x+this.width-100-240-24;
        this.btnChange.y = this.y;

        this.btnReset.x = this.x+this.width-100;
        this.btnReset.y = this.y;
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int mouseButton) {
        this.btnChange.mouseReleased(x, y);
        this.btnReset.mouseReleased(x, y);
    }

    @Override
    public void mousePressed(int mouseX, int mouseY, int mouseButton) {
        if(btnChange.mousePressed(mc, mouseX, mouseY)) {
            if(mc.currentScreen instanceof GuiOptionsMenu) {
                ((GuiOptionsMenu) mc.currentScreen).controlButtonId = this.keyBinding;
                mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
            }
        }
        if(btnReset.mousePressed(mc, mouseX, mouseY)) {
            mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
            mc.gameSettings.setOptionKeyBinding(this.keyBinding, this.keyBinding.getKeyCodeDefault());
            KeyBinding.resetKeyBindingArrayAndHash();
        }
        super.mousePressed(mouseX, mouseY, mouseButton);
    }

    private class KeyBindButton extends GuiFzButton {

        protected int hoveredValue = 0;
        public int info = 0;

        public KeyBindButton(int buttonId, int x, int y, String buttonText) {
            super(buttonId, x, y, buttonText);
        }

        public KeyBindButton(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText) {
            super(buttonId, x, y, widthIn, heightIn, buttonText);
        }

        @Override
        protected int buttonColor() {
            return BLACK_2;
        }

        @Override
        public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
            if(!this.visible) return;
            this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
            if (this.hovered && hoveredValue < 3)
                hoveredValue += 1;
            else if (!this.hovered && hoveredValue > 0)
                hoveredValue -= 1;
            if (hoveredValue > 0 || info == 1)
                RoundedShaderRenderer.getInstance().drawRoundRect(this.x - hoveredValue + 1, this.y - hoveredValue + 1, this.width + 2 * hoveredValue - 2, this.height + 2 * hoveredValue - 2, 3.5f, hoverColor());
            RoundedShaderRenderer.getInstance().drawRoundRect(this.x, this.y, this.width, this.height, 3.5f, BLACK_2);

            if(info == 1)
                FzClient.getInstance().getTTFFontRenderers().get(18).drawCenteredString("> " + displayString + " <", x+width/2, y+20, 0xFFFFFF55);
            if(info == 2) {
                FzClient.getInstance().getTTFFontRenderers().get(18).drawCenteredString("[ " + displayString + " ]", x+width/2, y+20, 0xFFAA0000);
                Gui.drawRect(x-16, y, x-8, y+40, 0xFFAA0000);
            }

            FzClient.getInstance().getTTFFontRenderers().get(18).drawCenteredString(displayString, x+width/2, y+20, 0xFFFFFFFF);
        }
    }
}
