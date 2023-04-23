package fz.frazionz.client.gui.hud;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;
import java.util.function.Predicate;

import fz.frazionz.mods.ModManager;
import fz.frazionz.mods.mod_hud.HUDManager;
import fz.frazionz.mods.mod_hud.IRenderer;
import fz.frazionz.mods.mod_hud.ScreenPosition;
import org.lwjgl.input.Keyboard;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;

public class HUDConfigScreen extends GuiScreen {

    int i = 0;

    // ADDED FOR SMOOTH DRAGGING

    private int smX, smY;

    private boolean dragged = false;

    protected boolean hovered;


    private final HashMap<IRenderer, ScreenPosition> renderers = new HashMap<IRenderer, ScreenPosition>();

    private Optional<IRenderer> selectedRenderer = Optional.empty();

    private int prevX, prevY;

    @Override
    public void initGui() {

        // modified to add your own buttons <3

        super.initGui();
    }

    @Override
    protected void actionPerformed(GuiButton button, int mouseButton) throws IOException {
        super.actionPerformed(button, mouseButton);
    }

    public HUDConfigScreen(HUDManager api) {

        Collection<IRenderer> registeredRenderers = api.getRegisteredRenderers();

        for (IRenderer ren : registeredRenderers) {
            if (!ren.isEnabled()) {
                continue;
            }

            ScreenPosition pos = ren.load();
            if (pos == null) {
                pos = ScreenPosition.fromRelativePosition(0.5, 0.5);
            }

            adjustBounds(ren, pos);
            this.renderers.put(ren, pos);
        }

    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {

        super.drawDefaultBackground();

        final float zBackup = this.zLevel;
        this.zLevel = 200;

        for (IRenderer renderer : renderers.keySet()) {

            ScreenPosition pos = renderers.get(renderer);
            Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY(), pos.getAbsoluteX() + renderer.getWidth(), pos.getAbsoluteY() + renderer.getHeight(), 0x33FFFFFF);
            this.drawHollowRect(pos.getAbsoluteX(), pos.getAbsoluteY(), renderer.getWidth(), renderer.getHeight(), 0x88FFFFFF);

            renderer.renderDummy(pos);

            // START OF SMOOTH DRAGGING
            if(selectedRenderer.isPresent() && renderer == selectedRenderer.get()) {
                if (dragged) {
                    pos.setAbsolute(pos.getAbsoluteX() + mouseX - this.prevX, pos.getAbsoluteY() + mouseY - this.prevY);

                    adjustBounds(renderer, pos);

                    this.prevX = mouseX;
                    this.prevY = mouseY;
                }
            }
            // END OF SMOOTH DRAGGING
        }

        this.smX = mouseX;
        this.smY = mouseY;

        this.zLevel = zBackup;
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    private void drawHollowRect(int x, int y, int w, int h, int color) {

        this.drawHorizontalLine(x, x + w, y, color);
        this.drawHorizontalLine(x, x + w, y + h, color);

        this.drawVerticalLine(x, y + h, y, color);
        this.drawVerticalLine(x + w, y + h, y, color);

    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        if (keyCode == Keyboard.KEY_ESCAPE) {
            renderers.entrySet().forEach((entry) -> {
                entry.getKey().save(entry.getValue());
            });
            this.mc.displayGuiScreen(null);
        }
    }

    @Override
    protected void mouseClickMove(int mouseX, int mouseY, int button, long time) {
        if (selectedRenderer.isPresent()) {
            moveSelectedRenderBy(mouseX - prevX, mouseY - prevY);
        }

        this.prevX = mouseX;
        this.prevY = mouseY;
    }

    private void moveSelectedRenderBy(int offsetX, int offsetY) {
        IRenderer renderer = selectedRenderer.get();
        ScreenPosition pos = renderers.get(renderer);

        pos.setAbsolute(pos.getAbsoluteX() + offsetX, pos.getAbsoluteY() + offsetY);

        if (pos.getAbsoluteX() == 0 << 1) {
            pos.setAbsolute(-1, pos.getAbsoluteY());
        }

        if (pos.getAbsoluteY() == 0 << 1) {
            pos.setAbsolute(pos.getAbsoluteX(), -1);
        }

        adjustBounds(renderer, pos);
    }

    @Override
    public void onGuiClosed() {

        for (IRenderer renderer : renderers.keySet()) {
            renderer.save(renderers.get(renderer));
        }

        ModManager.getInstance().saveModsConfig();
    }

    @Override
    public boolean doesGuiPauseGame() {
        return true;
    }

    private void adjustBounds(IRenderer renderer, ScreenPosition pos) {

        ScaledResolution res = new ScaledResolution(Minecraft.getMinecraft());

        int screenWidth = res.getScaledWidth();
        int screenHeight = res.getScaledHeight();

        int absoluteX = Math.max(0, Math.min(pos.getAbsoluteX(), Math.max(screenWidth - renderer.getWidth()-1, 0)));
        int absoluteY = Math.max(0, Math.min(pos.getAbsoluteY(), Math.max(screenHeight - renderer.getHeight()-1, 0)));

        pos.setAbsolute(absoluteX, absoluteY);
    }

    @Override
    protected void mouseClicked(int x, int y, int button) throws IOException {
        this.prevX = x;
        this.prevY = y;
        // NEEDED FOR SMOOTH DRAGGING
        dragged = true;

        loadMouseOver(x, y);
        super.mouseClicked(x, y, button);
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        // NEEDED FOR SMOOTH DRAGGING
        dragged = false;
        super.mouseReleased(mouseX, mouseY, state);
    }

    private void loadMouseOver(int x, int y) {
        this.selectedRenderer = renderers.keySet().stream().filter(new MouseOverFinder(x, y)).findFirst();
    }

    private class MouseOverFinder implements Predicate<IRenderer> {

        private int mouseX, mouseY;

        public MouseOverFinder(int x, int y) {
            this.mouseX = x;
            this.mouseY = y;
        }

        @Override
        public boolean test(IRenderer renderer) {

            ScreenPosition pos = renderers.get(renderer);
            int absoluteX = pos.getAbsoluteX();
            int absoluteY = pos.getAbsoluteY();

            if (mouseX >= absoluteX && mouseX <= absoluteX + renderer.getWidth()) {
                if (mouseY >= absoluteY && mouseY <= absoluteY + renderer.getHeight()) {
                    return true;
                }
            }
            return false;
        }
    }

}

