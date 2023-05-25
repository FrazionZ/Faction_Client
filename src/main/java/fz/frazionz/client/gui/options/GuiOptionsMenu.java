package fz.frazionz.client.gui.options;

import fz.frazionz.client.gui.buttons.GuiMenuButton;
import fz.frazionz.client.gui.impl.ExcludeScaledResolution;
import fz.frazionz.client.gui.list.slot.FzSlot;
import fz.frazionz.client.gui.list.GuiSlotList;
import net.minecraft.client.gui.*;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;

import java.io.IOException;

public class GuiOptionsMenu extends GuiScreen implements ExcludeScaledResolution {

    private static final GameSettings.Options[] SCREEN_OPTIONS = new GameSettings.Options[] {GameSettings.Options.FOV};

    private final GameSettings settings;
    private final GuiScreen lastScreen;
    private int activeMenu;
    private int padding = 24;

    private GuiSlotList list;

    public GuiOptionsMenu(GuiScreen lastScreen, GameSettings gameSettings) {
        this(lastScreen, gameSettings, 0);
    }

    public GuiOptionsMenu(GuiScreen lastScreen, GameSettings gameSettings, int activeMenu) {
        super();
        this.settings = gameSettings;
        this.lastScreen = lastScreen;
        this.activeMenu = activeMenu;
    }

    @Override
    public void initGui() {
        addMenuButton();
        FzSlot[] slots = new FzSlot[0];
        this.list = new GuiSlotList(mc, slots, width/2-350-16, 143, 760, 714, 24);
    }

    @Override
    protected void actionPerformed(GuiButton button, int keyCode) throws IOException {
        switch(button.id) {
            case 0:
                mc.displayGuiScreen(lastScreen);
                break;

            default:
                activeMenu = button.id - 1;
                buttonList.clear();
                initGui();
                break;
        }
    }

    private void addMenuButton() {
        int center = width / 2;
        int buttonGap = 16;
        int buttonWidth = 290;
        int buttonHeight = 55;

        String[] menuOptions = new String[] {
                "General",
                I18n.format("options.video"),
                I18n.format("options.sounds"),
                I18n.format("options.controls"),
                I18n.format("options.graphicsCustomisation")
        };
        int buttonsStartX = center - (int)((menuOptions.length/2.0f)*buttonWidth) - (int)(((menuOptions.length-1)/2.0f)*buttonGap);

        for (int i = 0; i < menuOptions.length; i++) {
            buttonList.add(new GuiMenuButton(i+1, buttonsStartX + i * (buttonWidth + buttonGap), padding, buttonWidth, buttonHeight, menuOptions[i])
                    .active(activeMenu == i));
        }

        buttonList.add(new GuiMenuButton(0, center - buttonWidth/2, height - padding - buttonHeight, buttonWidth, buttonHeight, I18n.format("gui.done")));
    }

    /**
     * Handles mouse input.
     */
    public void handleMouseInput() throws IOException
    {
        super.handleMouseInput();
        this.list.handleMouseInput();
    }


    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawRect(0, 0, this.width, this.height, BLACK_4);
        list.drawScreen(mouseX, mouseY, partialTicks);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
