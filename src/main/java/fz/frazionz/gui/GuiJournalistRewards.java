package fz.frazionz.gui;

import fz.frazionz.api.HTTPFunctions;
import fz.frazionz.api.gsonObj.ShopItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiRoundedButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.concurrent.Executors;

public class GuiJournalistRewards extends GuiScreen {

    private static final ResourceLocation background = new ResourceLocation("textures/gui/frazionz/interface_background.png");

    private Minecraft mc;
    private int visibleTime;

    public GuiJournalistRewards(Minecraft mc)
    {
        this.mc = mc;
    }

    // 776 - 478 //

    private final int xSize = 388;
    private final int ySize = 239;

    private int guiLeft;
    private int guiTop;

    public void initGui()
    {

        guiLeft = (this.width - this.xSize) / 2;
        guiTop = (this.height - this.ySize) / 2;

        int width = this.width/7;
        int height = this.height / 19;

        if(height != this.mc.fontrenderer.getSize()) {
            this.mc.fontrenderer = new fz.frazionz.gui.renderer.fonts.FontRenderer(this.mc.FONT_LOCATION, height);
        }
        if((int) (height*1.5) != this.mc.fontrendererTitle.getSize()) {
            this.mc.fontrendererTitle = new fz.frazionz.gui.renderer.fonts.FontRenderer(this.mc.FONT_LOCATION, (float) (height*1.5));
        }
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
               callbackAfterLoad(width, height);
            }
        }, 2500);


        super.initGui();
    }

    public void callbackAfterLoad(int width, int height){
        buttonList.clear();
        buttonList.add(new GuiRoundedButton(0, this.width / 2 - width / 2, this.guiTop + this.ySize - 68, width, height, "Récuperer maintenant", false, this.mc.fontrenderer, 1));
    }


    public void handleMouseInput() throws IOException
    {
        super.handleMouseInput();
    }


    protected void actionPerformed(GuiButton button, int mouseButton) throws IOException {

    }

    public void updateScreen()
    {
        super.updateScreen();
        ++this.visibleTime;
    }

    public void drawBackgroundItem(ItemStack itemStack, int day, int x, int y){
        mc.getTextureManager().bindTexture(background);
        GlStateManager.disableDepth();
        GlStateManager.enableBlend();
        this.drawModalRectWithCustomSizedTexture(x, y, 300, 364, 70, 64, 512, 512);
        GlStateManager.enableDepth();

        this.mc.fontRendererObj.drawScaleString("Jour "+day, x + 26 - this.fontRendererObj.getStringWidth("Jour "+day) / 2, y-10, 1.2F, Color.WHITE);

        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, 0);
        GlStateManager.scale(2.1F, 2F, 1);
        GlStateManager.translate(-x, -y, 0);

        RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
        renderItem.zLevel = 100F;
        renderItem.renderItemIntoGUI(itemStack, x + 7, y + 7);
        GlStateManager.enableRescaleNormal();

        GlStateManager.popMatrix();
    }

    public void drawBackgroundItemCurrent(ItemStack itemStack, int day, int x, int y){
        mc.getTextureManager().bindTexture(background);
        GlStateManager.disableDepth();
        GlStateManager.enableBlend();
        this.drawModalRectWithCustomSizedTexture(x, y, 360, 512, 75, 78, 612, 612);
        GlStateManager.enableDepth();


        this.mc.fontRendererObj.drawScaleString("Jour "+day, x + 26 - this.fontRendererObj.getStringWidth("Jour "+day) / 2, y-15, 1.6F, Color.WHITE);

        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, 0);
        GlStateManager.scale(3F, 3.1F, 1);
        GlStateManager.translate(-x, -y, 0);

        RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
        renderItem.zLevel = 100F;
        renderItem.renderItemIntoGUI(itemStack, x + 5, y + 4);
        GlStateManager.enableRescaleNormal();

        GlStateManager.popMatrix();
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        this.drawBackgroundImage();
        this.drawTopList();
        drawBackgroundItem(ShopItem.getItemStack("frazion"), 1,this.width / 2 - 160, this.guiTop + this.ySize - 160);
        drawBackgroundItemCurrent(ShopItem.getItemStack("diamond"), 2, this.width / 2 - 35, this.guiTop + this.ySize - 165);
        drawBackgroundItem(ShopItem.getItemStack("bauxite_sword"),3,this.width / 2 + 100, this.guiTop + this.ySize - 160);
        String title = "Récompense Journalière";
        Minecraft.fontRendererObj.drawScaleString(title, this.width / 2 - this.fontRendererObj.getStringWidth(title) / 1 + 20, this.guiTop + 8, 1.6D, Color.WHITE);
        String s = "Les récompenses sont récupérables une fois par jour.\nAucun remboursement n'est possible.";
        String[] ss = s.split("\n");
        List<String> list = Arrays.asList(ss);
        Collections.reverse(list);
        for(int i=0; i<list.size();i++){
            Minecraft.fontRendererObj.drawScaleString(list.get(i), this.width / 2 - this.fontRendererObj.getStringWidth(list.get(i)) / 2 + 30, this.guiTop + this.ySize - 22 + -10 * i, 0.8D, Color.GRAY);
        }
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
    {
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }


    public void drawBackgroundImage()
    {
        GlStateManager.pushMatrix();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(background);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        GlStateManager.enableBlend();
        this.drawModalRectWithCustomSizedTexture(i, j, 0, 0, this.xSize, this.ySize, 512.0F, 512.0F);
        GlStateManager.popMatrix();
    }

    public void drawTopList()
    {
        GlStateManager.pushMatrix();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(background);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawModalRectWithCustomSizedTexture(i + 10, j, 10, 0, this.xSize - 20, 37, 512.0F, 512.0F);

        this.mc.getTextureManager().bindTexture(background);
        int i1 = (this.width - this.xSize) / 2;
        int j1 = (this.height - this.ySize) / 2;
        this.drawModalRectWithCustomSizedTexture(i1 + 20, j1 + 202, 20, 30, this.xSize - 40, 37, 512.0F, 512.0F);
        GlStateManager.popMatrix();
    }
}
