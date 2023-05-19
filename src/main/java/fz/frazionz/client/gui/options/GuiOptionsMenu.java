package fz.frazionz.client.gui.options;

import fz.frazionz.client.gui.buttons.GuiMenuButton;
import fz.frazionz.client.gui.impl.ExcludeScaledResolution;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;

import java.io.IOException;

public class GuiOptionsMenu extends GuiScreen implements ExcludeScaledResolution {

    private static final GameSettings.Options[] SCREEN_OPTIONS = new GameSettings.Options[] {GameSettings.Options.FOV};

    private final GameSettings settings;
    private final GuiScreen lastScreen;
    private int activeMenu;
    private int padding = 24;

    public GuiOptionsMenu(GuiScreen lastScreen, GameSettings gameSettings) {
        this.settings = gameSettings;
        this.lastScreen = lastScreen;
        this.activeMenu = 0;
    }

    public GuiOptionsMenu(GuiScreen lastScreen, GameSettings gameSettings, int activeMenu) {
        this.settings = gameSettings;
        this.lastScreen = lastScreen;
        this.activeMenu = activeMenu;
    }

    @Override
    public void initGui() {
        addMenuButton();
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

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawRect(0, 0, this.width, this.height, BLACK_4);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
