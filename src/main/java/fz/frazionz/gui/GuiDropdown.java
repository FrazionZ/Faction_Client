package fz.frazionz.gui;

import fz.frazionz.api.gsonObj.SuccessObj;
import fz.frazionz.api.gsonObj.SuccessType;
import fz.frazionz.gui.success.GuiSuccessItemsList;
import fz.frazionz.gui.success.GuiSuccessTypeList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

import java.awt.*;
import java.util.ArrayList;

public class GuiDropdown extends GuiScreen {

    private final int ySize;
    private final int xSize;
    private final int xPos;
    private final int yPos;
    private boolean isShow;

    private static final ResourceLocation background = new ResourceLocation("textures/gui/frazionz/interface_background.png");

    public GuiDropdown(Minecraft mc, int xPos, int yPos, int xSize, int ySize){
        this.isShow = false;
        this.xPos = xPos;
        this.yPos = yPos;
        this.xSize = xSize;
        this.ySize = ySize;

    }

    public void toggle(){
        this.isShow = !isShow;
    }

    public void initGui()
    {
        this.buttonList.add(new GuiButtonImage(4, "", xPos, yPos, 25, 25, 300, 311, true, background));
    }

    public void drawDropdown(Minecraft minecraft, float partialTicks)
    {
        if(isShow){
            this.drawRect(xPos, yPos, xSize, ySize, 0xFFFFFFFF);
            drawScreen(xPos, yPos, partialTicks);
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks){
        super.drawScreen(xPos, yPos, partialTicks);
    }

}
