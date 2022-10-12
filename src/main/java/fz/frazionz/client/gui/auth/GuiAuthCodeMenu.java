package fz.frazionz.client.gui.auth;

import fz.frazionz.client.gui.GuiFrazionZInterface;;
import fz.frazionz.client.gui.buttons.GuiFzInput;
import fz.frazionz.client.gui.buttons.GuiHoverButton;
import fz.frazionz.enums.CustomPayloadChannel;
import io.netty.buffer.Unpooled;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.CPacketCustomPayload;

import java.io.IOException;

public class GuiAuthCodeMenu extends GuiFrazionZInterface {

    private GuiFzInput[] codeFields = new GuiFzInput[6];
    private boolean codeSend = false;
    public GuiAuthCodeMenu(GuiScreen lastScreen, Minecraft mc) {
        super("Auth Code", lastScreen, mc);
    }

    @Override
    public void initGui() {
        super.initGui();

        this.buttonList.add(new GuiHoverButton(2, this.width / 2 - 100, this.guiTop + this.ySize - 30, 200, 20, "Envoyer le Code").setBackgroundColor(0x20660b));

        for(int i = 0; i < 6; i++) {
            codeFields[i] = new CodeInput(i, this.width / 2 - 145 + (i * 50), this.height/2 - 30, 40, 60);
        }
        codeFields[0].setFocused(true);
    }

    @Override
    public void updateScreen() {
        super.updateScreen();

        for(GuiFzInput field : this.codeFields) {
            field.updateCursorCounter();
        }

        if(!codeFields[0].getText().isEmpty() && !codeFields[1].getText().isEmpty() && !codeFields[2].getText().isEmpty() && !codeFields[3].getText().isEmpty() && !codeFields[4].getText().isEmpty() && !codeFields[5].getText().isEmpty()) {
            if(!codeSend) {
                this.sendCode();
            }
        }
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        if(keyCode == 1)
            return;

        if(keyCode == 14) {
            for (int i = 5; i >= 0; i--) {
                if (!codeFields[i].getText().isEmpty()) {
                    codeFields[i].setText("");
                    codeFields[i].setFocused(true);
                    if(i < 5)
                        codeFields[i+1].setFocused(false);
                    break;
                }
            }
        }

        if(keyCode == 28) {
            this.sendCode();
            return;
        }

        for(int i = 0; i < 6; i++) {
            boolean write = codeFields[i].textboxKeyTyped(typedChar, keyCode);
            if(write) {
                codeFields[i].setFocused(false);
                if(i < 5) {
                    codeFields[i + 1].setFocused(true);
                }
                break;
            }
        }
        super.keyTyped(typedChar, keyCode);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        for(GuiFzInput field : this.codeFields) {
            field.mouseClicked(mouseX, mouseY, mouseButton);
        }

        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);

        for(GuiFzInput field : codeFields) {
            field.drawTextBox();
        }

    }

    private void sendCode() {
        PacketBuffer packetBuffer = new PacketBuffer(Unpooled.buffer());
        String code = codeFields[0].getText() + codeFields[1].getText() + codeFields[2].getText() + codeFields[3].getText() + codeFields[4].getText() + codeFields[5].getText();
        packetBuffer.writeString(code);
        this.mc.player.connection.sendPacket(new CPacketCustomPayload(CustomPayloadChannel.AUTH_CODE.getChannel(), packetBuffer));
        codeSend = true;
    }

    @Override
    protected void actionPerformed(GuiButton button, int keyCode) throws IOException {
        super.actionPerformed(button, keyCode);

        switch (button.id) {
            case 2:
                this.sendCode();
                break;
        }
    }

    private class CodeInput extends GuiFzInput {

        public CodeInput(int id, int x, int y, int width, int height) {
            super(id, x, y, width, height);
            this.maxStringLength = 1;
            this.textCenter = true;
        }

        @Override
        public boolean textboxKeyTyped(char typedChar, int keyCode) {
            if(Character.isDigit(typedChar)) {
                return super.textboxKeyTyped(typedChar, keyCode);
            }
            return false;
        }
    }
}
