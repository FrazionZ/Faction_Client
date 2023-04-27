package fz.frazionz.client.gui.faction;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.resources.ResourceLocation;

import java.io.IOException;

public class BlasonCreatorGUI extends GuiScreen {

    private GuiScreen prevScreen;

    public BlasonCreatorGUI(GuiScreen prev) {
        this.prevScreen = prev;
    }

    @Override
    protected void actionPerformed(GuiButton button, int keyCode) throws IOException {
        super.actionPerformed(button, keyCode);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {

        ResourceLocation background = new ResourceLocation("frazionz", "textures/faction/blason/backgrounds/background_1.png");
        mc.getTextureManager().bindTexture(background);

        GlStateManager.color((float) 150 /255, 0.0F, (float) 230 /255, 1.0F);
        GlStateManager.enableBlend();
        drawModalRectWithCustomSizedTexture(width/2-32, height/2-32, 0, 0, 64, 64, 64.0F, 64.0F);


        background = new ResourceLocation("frazionz", "textures/faction/blason/containers/shield_1_in.png");
        mc.getTextureManager().bindTexture(background);

        GlStateManager.color((float) 150 /255, 0.0F, (float) 200 /255, 1.0F);
        GlStateManager.enableBlend();
        drawModalRectWithCustomSizedTexture(width/2-32, height/2-32, 0, 0, 64, 64, 64.0F, 64.0F);


        background = new ResourceLocation("frazionz", "textures/faction/blason/containers/shield_1_out.png");
        mc.getTextureManager().bindTexture(background);

        GlStateManager.color(0.0F, 1.0F, 0.0F, 1.0F);
        GlStateManager.enableBlend();
        drawModalRectWithCustomSizedTexture(width/2-32, height/2-32, 0, 0, 64, 64, 64.0F, 64.0F);


        background = new ResourceLocation("frazionz", "textures/faction/blason/emblemes/embleme_2.png");
        mc.getTextureManager().bindTexture(background);

        GlStateManager.color(1.0F, 1.0F, 0.0F, 1.0F);
        GlStateManager.enableBlend();
        drawModalRectWithCustomSizedTexture(width/2-32, height/2-32, 0, 0, 64, 64, 64.0F, 64.0F);

    }
}
