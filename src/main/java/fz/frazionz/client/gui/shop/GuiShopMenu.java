package fz.frazionz.client.gui.shop;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;

import java.util.Arrays;
import java.util.List;

public class GuiShopMenu extends GuiScreen {

    protected final GuiScreen lastScreen;
    protected List<ShopCategory> categories;
    public GuiShopMenu(GuiScreen parent, Minecraft mc, String json) {
        this.lastScreen = parent;
        this.mc = mc;
        if(json != null && !json.isEmpty()) {
            JsonElement infos = new JsonParser().parse(json);
            this.categories = Arrays.asList(ShopCategory.deserializeList(infos.getAsJsonObject().get("categories").toString()));
        }
    }

    @Override
    public void initGui() {

    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        Gui.drawRect(0, 0, this.width, this.height, BLACK_4);
    }
}
