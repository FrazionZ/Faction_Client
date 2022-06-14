package fz.frazionz.gui.success;

import fz.frazionz.api.HTTPEndpoints;
import fz.frazionz.api.gsonObj.SuccessObj;
import fz.frazionz.api.gsonObj.SuccessType;
import fz.frazionz.gui.GuiButtonOnlyImage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.io.IOException;

public class GuiSuccessItem extends GuiScreen {

    private static final ResourceLocation background = new ResourceLocation("textures/gui/frazionz/interface_background.png");
    private final GuiScreen lastScreen;
    private final SuccessObj item;
    private DynamicTexture imgMinia;

    public GuiSuccessItem(GuiScreen lastScreen, Minecraft mc, SuccessObj item)
    {
        this.lastScreen = lastScreen;
        this.mc = mc;
        this.item = item;
        try {
            this.imgMinia = new DynamicTexture(this.getDynamicTextureFromUrl(HTTPEndpoints.WEBSITE+item.getImgMinia()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private final int xSize = 388;
    private final int ySize = 239;

    private int guiLeft;
    private int guiTop;

    public void initGui()
    {

        guiLeft = (this.width - this.xSize) / 2;
        guiTop = (this.height - this.ySize) / 2;

        this.buttonList.add(new GuiButtonOnlyImage(50, this.guiLeft + 10, this.guiTop + 5, 21, 20, 388, 0, 0, background));

        super.initGui();
    }

    public GuiScreen getLastScreen() {
        return lastScreen;
    }

    public SuccessObj getItem() {
        return item;
    }
    
    protected void actionPerformed(GuiButton button, int mouseButton) throws IOException {
        switch (button.id)
        {
            case 50:
                this.mc.displayGuiScreen(this.lastScreen);
                break;
        }
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        this.drawBackgroundImage();

        this.fontRenderer.drawScaleString("Mes succ�s", this.guiLeft + 40, this.guiTop + 8, 2F, Color.white);

        if(this.imgMinia != null){
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.imgMinia.getGlTextureId());
            drawModalRectWithCustomSizedTexture(this.guiLeft + 10, this.guiTop, 0.0F, 0.0F, xSize - 20, 150, xSize - 20, 150);
        }

        GlStateManager.pushMatrix();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(background);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        GlStateManager.enableBlend();
        this.drawModalRectWithCustomSizedTexture(i, guiTop, 0, 0, this.xSize, 30, 512.0F, 512.0F);
        GlStateManager.popMatrix();
        
        drawCard();

        this.fontRenderer.drawScaleString("Mes succès", this.guiLeft + 40, this.guiTop + 8, 2F, Color.white);
        this.fontRenderer.drawScaleString(item.getTitle(), this.guiLeft + 40, this.guiTop + 95, 1.5F, Color.white);
        this.fontRenderer.drawScaleString(item.getDescription(), this.guiLeft + 40, this.guiTop + 115, 1F, Color.white);
        
        int xProgress = this.guiLeft + xSize - this.fontRenderer.getStringWidth(item.getProgress()+"%");
        this.fontRenderer.drawScaleString(item.getProgress()+"%", xProgress - 45, this.guiTop + 115, 1.5F, Color.white);
        this.drawHorizontalLine(this.guiLeft + 40, this.guiLeft + xSize - 40, this.guiTop + 135, 0xFFFFFFFF);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        
        super.drawScreen(mouseX, mouseY, partialTicks);
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
    
    public void drawCard()
    {
        GlStateManager.pushMatrix();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(background);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        GlStateManager.enableBlend();
        this.drawModalRectWithCustomSizedTexture(i+16, j+75, 3, 382, this.xSize, this.ySize-80, 622.0F, 542.0F);
        GlStateManager.popMatrix();
    }
}
