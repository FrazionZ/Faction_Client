package net.minecraft.client.gui;

import java.io.IOException;
import java.util.Random;

import fz.frazionz.client.gui.buttons.GuiHoverButton;
import fz.frazionz.client.gui.faction.BlasonCreatorGUI;

import fz.frazionz.client.gui.GuiModsConfig;
import fz.frazionz.client.gui.buttons.GuiMenuButton;
import net.minecraft.client.gui.achievement.GuiStats;
import net.minecraft.client.gui.advancements.GuiScreenAdvancements;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.resources.ResourceLocation;

public class GuiIngameMenu extends GuiScreen
{
    private int menuWidth;
    private int menuHeight;
    
    private static final ResourceLocation FZ_LOGO = new ResourceLocation("textures/gui/title/background/fz_logo.png");

    /**
     * Adds the buttons (and other controls) to the screen in question. Called when the GUI is displayed and when the
     * window resizes, the buttonList is cleared beforehand.
     */
    public void initGui()
    {
    	this.menuWidth = 220;
    	this.menuHeight = this.height;

        this.buttonList.clear();
        
        this.addMenuButtons(160, 28, 8);
    }
    
    private void addMenuButtons(int width , int height, int gap)
    {
    	int menuPadding = 20;
    	
        this.buttonList.add(new GuiMenuButton(0, menuPadding, menuPadding, width, height, "Retour"));
    	this.buttonList.add(new GuiMenuButton(1, menuPadding, menuPadding + height + gap, width, height, "Options"));
    	this.buttonList.add(new GuiMenuButton(2, menuPadding, menuPadding + height*2 + gap*2, width, height, "Succès"));
    	this.buttonList.add(new GuiMenuButton(3, menuPadding, menuPadding + height*3 + gap*3, width, height, "Stats"));
    	this.buttonList.add(new GuiMenuButton(4, menuPadding, menuPadding + height*4 + gap*4, width, height, "Mods"));
        this.buttonList.add(new GuiMenuButton(6, menuPadding, menuPadding + height*5 + gap*5, width, height, "MON TEST DE MORT"));
    	this.buttonList.add(new GuiMenuButton(5, menuPadding, this.height - menuPadding - height, width, height, "Quitter"));
    }

    /**
     * Called by the controls from the buttonList when activated. (Mouse pressed for buttons)
     */
    protected void actionPerformed(GuiButton button, int keyCode) throws IOException
    {
        switch (button.id)
        {
    	case 0:
            this.mc.displayGuiScreen((GuiScreen)null);
            this.mc.setIngameFocus();
            break;
            
        case 1:
        	this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
            break;
            
        case 2:
            this.mc.displayGuiScreen(new GuiScreenAdvancements(this.mc.player.connection.getAdvancementManager()));
            break;
            
        case 3:
            this.mc.displayGuiScreen(new GuiStats(this, this.mc.player.getStatFileWriter()));
            break;
            
        case 4:
        	this.mc.displayGuiScreen(new GuiModsConfig(this, this.mc.gameSettings));
        	//this.mc.displayGuiScreen(new GuiModToggle(this));
            break;

        case 6:
            this.mc.displayGuiScreen(new BlasonCreatorGUI(this));
            break;
            
		case 5:
			button.enabled = false;
			this.mc.world.sendQuittingDisconnectingPacket();
			this.mc.loadWorld((WorldClient)null);
			this.mc.displayGuiScreen(new GuiMainMenu());
			break;
        }
    }

    /**
     * Called from the main game loop to update the screen.
     */
    public void updateScreen()
    {
        super.updateScreen();
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();

        drawSideBar();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    
    private void drawSideBar()
    {
    	drawRect(0, 0, this.menuWidth, this.menuHeight, BLACK_4);
    	
        GlStateManager.pushMatrix();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        
        this.mc.getTextureManager().bindTexture(INTERFACE_BACKGROUND_2);
        
        int midDecorationBarHeight = (this.height - 40)/2;
        
        GlStateManager.translate(230.0F, 0.0F, 0.0F);
		GlStateManager.rotate(90F, 0.0F, 0.0F, 1.0F);
        drawModalRectWithCustomSizedTexture(20, 0, 0.0F, 0.0F, midDecorationBarHeight, 26, 512.0F, 512.0F);
        drawModalRectWithCustomSizedTexture(20 + midDecorationBarHeight, 0, 394 - midDecorationBarHeight, 0.0F, midDecorationBarHeight, 26, 512.0F, 512.0F);
        GlStateManager.popMatrix();
    }
}
