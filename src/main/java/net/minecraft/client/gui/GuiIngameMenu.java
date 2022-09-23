package net.minecraft.client.gui;

import java.io.IOException;
import java.util.Random;

import org.lwjgl.opengl.GL11;

import fz.frazionz.client.gui.GuiJournalistRewards;
import fz.frazionz.client.gui.GuiModsConfig;
import fz.frazionz.client.gui.GuiRoundedButton;
import fz.frazionz.client.gui.buttons.GuiMenuButton;
import net.minecraft.client.gui.achievement.GuiStats;
import net.minecraft.client.gui.advancements.GuiScreenAdvancements;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.resources.ResourceLocation;

public class GuiIngameMenu extends GuiScreen
{
    private int saveStep;
    private int visibleTime;

    private int i2 = 0;
    private int y = 0;
    private double deg = 0;
    private Random rand = new Random();
    
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
    	
        this.saveStep = 0;
        this.buttonList.clear();
        
        this.addMenuButtons(160, 28, 8);
    }
    
    private void addMenuButtons(int width , int height, int gap)
    {
    	int menuPadding = 20;
    	
    	this.buttonList.add(new GuiMenuButton(0, menuPadding, menuPadding, width, height, "RETOUR"));
    	this.buttonList.add(new GuiMenuButton(1, menuPadding, menuPadding + height + gap, width, height, "OPTIONS"));
    	this.buttonList.add(new GuiMenuButton(2, menuPadding, menuPadding + height*2 + gap*2, width, height, "SUCCES"));
    	this.buttonList.add(new GuiMenuButton(3, menuPadding, menuPadding + height*3 + gap*3, width, height, "STATS"));
    	this.buttonList.add(new GuiMenuButton(4, menuPadding, menuPadding + height*4 + gap*4, width, height, "MODS"));
    	this.buttonList.add(new GuiMenuButton(5, menuPadding, this.height - menuPadding - height, width, height, "QUITTER"));
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
        
        ++this.visibleTime;
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        //GlStateManager.enableAlpha();
        GL11.glEnable(GL11.GL_BLEND);
        //GL11.glDisable(GL11.GL_DEPTH_TEST);
        //GL11.glDepthMask(false);
        //GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        //GL11.glDisable(GL11.GL_ALPHA_TEST);

        drawSideBar();
        
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    
    private void drawSideBar()
    {
    	this.drawRect(0, 0, this.menuWidth, this.menuHeight, this.BLACK_4);
    	
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
        drawModalRectWithCustomSizedTexture(20+midDecorationBarHeight, 0, 394-midDecorationBarHeight, 0.0F, midDecorationBarHeight, 26, 512.0F, 512.0F);
        GlStateManager.popMatrix();
    }
}
