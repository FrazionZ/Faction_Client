package fz.frazionz.client.gui.shop;

import fz.frazionz.api.gsonObj.ShopCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

import java.util.List;

public class GuiShopMenu extends GuiScreen {

    protected final GuiScreen lastScreen;
    protected List<ShopCategory> categories;
    public GuiShopMenu(GuiScreen parent, Minecraft mc, String json) {
        this.lastScreen = parent;
        this.mc = mc;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
