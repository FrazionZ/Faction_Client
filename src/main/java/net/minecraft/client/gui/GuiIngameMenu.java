package net.minecraft.client.gui;

import java.awt.Color;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.entity.player.EntityPlayerMP;
import optifine.SkinUtils;
import org.lwjgl.opengl.GL11;

import fz.frazionz.api.HTTPFunctions;
import fz.frazionz.api.data.ShopAPIDataStocker;
import fz.frazionz.api.gsonObj.ShopType;
import fz.frazionz.forgemods.smoothscrollingeverywhere.RunSixtyTimesEverySec;
import fz.frazionz.gui.GuiModToggle;
import net.minecraft.client.gui.achievement.GuiStats;
import net.minecraft.client.gui.advancements.GuiScreenAdvancements;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.realms.RealmsBridge;
import net.minecraft.util.ResourceLocation;
import optifine.GuiModsConfig;

public class GuiIngameMenu extends GuiScreen
{
    private int saveStep;
    private int visibleTime;

    private float i = 0;
    private boolean boolI = false;
    private int i2 = 0;
    private int y = 0;
    private double deg = 0;
    private Random rand = new Random();
    
    private RunSixtyTimesEverySec scroller;
    
    
    private static final ResourceLocation FZ_LOGO = new ResourceLocation("textures/gui/title/background/fz_logo.png");
    /**
     * Adds the buttons (and other controls) to the screen in question. Called when the GUI is displayed and when the
     * window resizes, the buttonList is cleared beforehand.
     */
    
    int z = 84;
    
    public void initGui()
    {
    	int width = this.width/5;
    	int height = this.height / 19;
    	int h = height + height/3;
    	
    	if(height != this.mc.fontrenderer.getSize()) {
    		this.mc.fontrenderer = new fz.frazionz.gui.renderer.fonts.FontRenderer(this.mc.FONT_LOCATION, height);
    	}
    	if((int) (height*1.5) != this.mc.fontrendererTitle.getSize()) {
    		this.mc.fontrendererTitle = new fz.frazionz.gui.renderer.fonts.FontRenderer(this.mc.FONT_LOCATION, (float) (height*1.5));
    	}
    	
        this.saveStep = 0;
        this.buttonList.clear();
        addAllButtons(width, height, h);
        this.scroller = (() -> {
        	
        	if(this.boolI) { // DESCEND
        		if(this.i-this.height/22 < this.height/22) {
        			if(this.mc.displayWidth < 1280)
        				this.i += 0.25;
        			else
        				this.i += 0.5;
        		}
        		else {
        			this.boolI = false;
        		}
        	}
        	else { // MONTE
        		if(this.i+this.height/22 > this.height/22) {
        			if(this.mc.displayWidth < 1280)
        				this.i -= 0.25;
        			else
        				this.i -= 0.5;
        		}
        		else {
        			this.boolI = true;
        		}
        	}
        });
        
        if(!this.scroller.isRegistered())
        	this.scroller.registerTick();
    }
    
    private void addAllButtons(int width , int height, int h)
    {
    	int x = this.width/16;
    	int y = (this.height / 20);
		//SkinUtils.downloadSkin(this);
        this.buttonList.add(new GuiRoundedButton(0, x, y, width, height, "RETOUR", false, this.mc.fontrenderer, 1));
        this.buttonList.add(new GuiRoundedButton(1, x, y + h, width, height, "OPTIONS", false, this.mc.fontrenderer, 1));
        this.buttonList.add(new GuiRoundedButton(2, x, y + (h * 2), width, height, "SUCCES", false, this.mc.fontrenderer,1));
        this.buttonList.add(new GuiRoundedButton(3, x, y + (h * 3), width, height, "STATS", false, this.mc.fontrenderer, 1));
        this.buttonList.add(new GuiRoundedButton(4, x, y + (h * 4), width, height, "MODS", false, this.mc.fontrenderer, 1));
        this.buttonList.add(new GuiRoundedButton(5, x, this.height - height - y, width, height, "QUITTER", true, this.mc.fontrenderer, 1));
    }
    
    
    protected void actionPerformed(GuiButton button, int mouseButton) throws IOException
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
	            
	        case 5:
	        	button.enabled = false;
	            this.mc.world.sendQuittingDisconnectingPacket();
	            this.mc.loadWorld((WorldClient)null);
	            this.mc.displayGuiScreen(new GuiMainMenu());
	            break;
	            
	        case 4:
	        	this.mc.displayGuiScreen(new GuiModsConfig(this, this.mc.gameSettings));
	        	//this.mc.displayGuiScreen(new GuiModToggle(this));
	            break;
	            
	        case 3:
	            this.mc.displayGuiScreen(new GuiStats(this, this.mc.player.getStatFileWriter()));
	            break;
	            
	        case 2:
	            this.mc.displayGuiScreen(new GuiScreenAdvancements(this.mc.player.connection.func_191982_f()));
	            break;
        }
        this.scroller.unregisterTick();
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

        this.mc.getTextureManager().bindTexture(FZ_LOGO);
        drawModalRectWithCustomSizedTexture(this.width*2/3 - this.width/7, (int) (this.height/4 + this.i), 0.0F, 0.0F, this.height/2 - this.height/40, this.height/2 - this.height/40, this.height/2 - this.height/40, this.height/2 - this.height/40);
    
        this.drawRect(0, 0, this.width/3 - this.width/80, this.height, 0x72000000);
        
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
