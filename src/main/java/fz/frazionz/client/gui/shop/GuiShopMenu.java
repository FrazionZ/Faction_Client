package fz.frazionz.client.gui.shop;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import fz.frazionz.FzClient;
import fz.frazionz.client.gui.buttons.GuiHoverButton;
import fz.frazionz.client.gui.list.GuiListFrazionZInterface;
import fz.frazionz.client.gui.utils.RoundedShaderRenderer;
import fz.frazionz.utils.Colors;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.MathHelper;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class GuiShopMenu extends GuiScreen {

    protected final GuiScreen lastScreen;
    private List<ShopCategory> categories;
    private GuiList list;
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
        int totalWidth = 0;
        for(int i = 0; i < categories.size(); i++) {
            ShopCategory category = categories.get(i);
            int width = FzClient.getInstance().getTTFFontRenderers().get(16).getWidth(category.getName())+20;
            this.buttonList.add(new GuiHoverButton(i, 10 + totalWidth, 10, width, 24, category.getName()).setBackgroundColor(Gui.BLACK_1));
            totalWidth += width+10;
        }
        this.list = new GuiList(lastScreen, mc, 0, this.width, 44, this.height-44);
        GuiEntry[] entries = new GuiEntry[categories.size()*5];
        for(int j = 0; j < 5; j++) {
            for (int i = 0; i < categories.size(); i++) {
                ShopCategory category = categories.get(i);
                entries[j+5*i] = new GuiEntry(category);
            }
        }
        this.list.setListEntry(entries);
    }

    protected void actionPerformed(GuiButton button, int mouseButton) throws IOException {
        switch (button.id)
        {
            default:
                this.list.actionPerformed(button, mouseButton);
                break;
        }
    }

    public void handleMouseInput() throws IOException
    {
        super.handleMouseInput();
        this.list.handleMouseInput();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        Gui.drawRect(0, 0, this.width, this.height, BLACK_4);
        this.list.drawScreen(mouseX, mouseY, partialTicks);
        Gui.drawRect(0, 0, this.width, 44, BLACK_4);
        Gui.drawRect(0, this.height-44, this.width, this.height, BLACK_4);
        this.drawButtons(mouseX, mouseY, partialTicks);
    }

    private class GuiList extends GuiListFrazionZInterface {

        public GuiList(GuiScreen lastScreen, Minecraft mcIn, int left, int right, int top, int bottom) {
            super(lastScreen, mcIn, left, right, top, bottom, 36);
        }

        @Override
        public void drawScreen(int mouseXIn, int mouseYIn, float partialTicks) {
            if (this.visible)
            {
                this.mouseX = mouseXIn;
                this.mouseY = mouseYIn;
                this.drawBackground();
                int i = this.right - 10;
                int j = i + 6;
                this.bindAmountScrolled();
                GlStateManager.disableLighting();
                GlStateManager.disableFog();
                Tessellator tessellator = Tessellator.getInstance();
                this.drawContainerBackground(tessellator);

                int k = this.left + 10;
                int l = this.top - (int)this.amountScrolled;

                if (this.hasListHeader)
                {
                    this.drawListHeader(k, l, tessellator);
                }

                this.drawSelectionBox(k, l, mouseXIn, mouseYIn, partialTicks);
                int j1 = this.getMaxScroll();

                if (j1 > 0)
                {
                    int k1 = (this.bottom - this.top) * (this.bottom - this.top) / this.getContentHeight();
                    k1 = MathHelper.clamp(k1, 32, this.bottom - this.top - 8);
                    int l1 = (int)this.amountScrolled * (this.bottom - this.top - k1) / j1 + this.top;

                    if (l1 < this.top)
                    {
                        l1 = this.top;
                    }
                    this.drawRoundedSliderButton(i, this.top, j, this.bottom, Colors.BLACK_4);
                    this.drawRoundedSliderButton(i, l1, j, (l1 + k1), Colors.COLOR_2);
                }

                GlStateManager.enableTexture2D();
                GlStateManager.shadeModel(7424);
                GlStateManager.enableAlpha();
                GlStateManager.disableBlend();
            }
        }

        @Override
        protected void drawSelectionBox(int insideLeft, int insideTop, int mouseXIn, int mouseYIn, float partialTicks) {
            int i = this.getSize();
            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder bufferbuilder = tessellator.getBuffer();

            for (int j = 0; j < i; ++j) {
                int k = (insideTop + j * this.slotHeight + this.headerPadding) + j * getItemMargin();
                int l = this.slotHeight;

                if (k > this.bottom || k + l < this.top) {
                    this.updateItemPos(j, insideLeft, k, partialTicks);
                }

                if (k >= this.top - this.slotHeight && k <= this.bottom) {
                    if (this.showSelectionBox && this.isSelected(j)) {
                        int i1 = this.left + (this.width / 2 - this.getListWidth() / 2);
                        int j1 = this.left + this.width / 2 + this.getListWidth() / 2;
                        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                        GlStateManager.disableTexture2D();
                        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
                        bufferbuilder.pos((double) i1, (double) (k + l + 2), 0.0D).tex(0.0D, 1.0D).color(128, 128, 128, 255).endVertex();
                        bufferbuilder.pos((double) j1, (double) (k + l + 2), 0.0D).tex(1.0D, 1.0D).color(128, 128, 128, 255).endVertex();
                        bufferbuilder.pos((double) j1, (double) (k - 2), 0.0D).tex(1.0D, 0.0D).color(128, 128, 128, 255).endVertex();
                        bufferbuilder.pos((double) i1, (double) (k - 2), 0.0D).tex(0.0D, 0.0D).color(128, 128, 128, 255).endVertex();
                        bufferbuilder.pos((double) (i1 + 1), (double) (k + l + 1), 0.0D).tex(0.0D, 1.0D).color(0, 0, 0, 255).endVertex();
                        bufferbuilder.pos((double) (j1 - 1), (double) (k + l + 1), 0.0D).tex(1.0D, 1.0D).color(0, 0, 0, 255).endVertex();
                        bufferbuilder.pos((double) (j1 - 1), (double) (k - 1), 0.0D).tex(1.0D, 0.0D).color(0, 0, 0, 255).endVertex();
                        bufferbuilder.pos((double) (i1 + 1), (double) (k - 1), 0.0D).tex(0.0D, 0.0D).color(0, 0, 0, 255).endVertex();
                        tessellator.draw();
                        GlStateManager.enableTexture2D();
                    }
                    this.drawSlot(j, insideLeft, k, l, mouseXIn, mouseYIn, partialTicks);
                }
            }
        }
    }

    private class GuiEntry implements GuiListExtended.IGuiListEntry {

        private ShopCategory category;

        public GuiEntry(ShopCategory category) {
            this.category = category;
        }
        @Override
        public void updatePosition(int slotIndex, int x, int y, float partialTicks) {

        }

        @Override
        public void drawEntry(int slotIndex, int x, int y, int listWidth, int slotHeight, int mouseX, int mouseY, boolean isSelected, float partialTicks) {
            RoundedShaderRenderer.getInstance().drawRoundRect(x, y, 200, 30, 4, BLACK_1);
            FzClient.getInstance().getTTFFontRenderers().get(24).drawString(category.getName(), x+10, y+6, 0xFFFFFF);
        }

        @Override
        public boolean mousePressed(int slotIndex, int mouseX, int mouseY, int mouseEvent, int relativeX, int relativeY) {
            return false;
        }

        @Override
        public void mouseReleased(int slotIndex, int x, int y, int mouseEvent, int relativeX, int relativeY) {

        }
    }
}
