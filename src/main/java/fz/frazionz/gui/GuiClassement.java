package fz.frazionz.gui;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.imageio.ImageIO;

import org.lwjgl.opengl.GL11;

import fz.frazionz.api.HTTPFunctions;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiRoundedSideButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.EnumDifficulty;

public class GuiClassement extends GuiScreen
{
	private final GuiScreen lastScreen;
    private List info;
    protected String title;
    private int type;
    private int page;
    private DynamicTexture image;

    public GuiClassement(GuiScreen p_i1046_1_, int type, int page)
    {
        this.lastScreen = p_i1046_1_;
        this.type = type;
        this.page = page;
    }
    
    public GuiClassement(GuiScreen p_i1046_1_, int type)
    {
        this.lastScreen = p_i1046_1_;
        this.type = type;
        this.page = 0;
    }

    /**
     * Adds the buttons (and other controls) to the screen in question. Called when the GUI is displayed and when the
     * window resizes, the buttonList is cleared beforehand.
     */
    public void initGui()
    {	
    	this.title = I18n.format("Classement");
    	
        int width = this.width/5;
        int height = this.height / 19;
        int h = height + height/3;
        int y = (this.height / 22);
        	
        if(height != this.mc.fontrenderer.getSize()) {
        	this.mc.fontrenderer = new fz.frazionz.gui.renderer.fonts.FontRenderer(this.mc.FONT_LOCATION, height);
        }
        	
        if((int) (height*1.5) != this.mc.fontrendererTitle.getSize()) {
        	this.mc.fontrendererTitle = new fz.frazionz.gui.renderer.fonts.FontRenderer(this.mc.FONT_LOCATION, (float) (height*1.5));
        }
    	if(this.type == 1) {
    		//this.info = HTTPFunctions.getPlayerClassement(this.page);
    	}
    	else if(this.type == 2) {
    		//this.info = HTTPFunctions.getFactionClassement(this.page);
    	}
    	else if(this.type == 3) {
    		//this.info = HTTPFunctions.getFactionStats();
    	}
    	else if(this.type == 4) {
    		//this.info = HTTPFunctions.getPlayerStats();
    	}
    	
    	
    	this.addButtons(y, h, width, height);
    }
    
    private void addButtons(int y, int h, int width, int height)
    {
        this.buttonList.add(new GuiRoundedSideButton(1, this.width/8, y + height + (int)(2*h), width, height, I18n.format("FACTIONS"), false, this.mc.fontrenderer, 1, true, this.type==1));
        this.buttonList.add(new GuiRoundedSideButton(2, this.width/8, y + height + (int)(3.25*h), width, height, I18n.format("JOUEURS"), false, this.mc.fontrenderer, 1, true, this.type==2));
        this.buttonList.add(new GuiRoundedSideButton(3, this.width/8, y + height + (int)(9.25*h), width, height, I18n.format("MA FACTION"), false, this.mc.fontrenderer, 1, true, this.type==3));
        this.buttonList.add(new GuiRoundedSideButton(4, this.width/8, y + height + (int)(10.5*h), width, height, I18n.format("MOI"), false, this.mc.fontrenderer, 1, true, this.type==4));
    }

    public String getDifficultyText(EnumDifficulty p_175355_1_)
    {
        ITextComponent itextcomponent = new TextComponentString("");
        itextcomponent.appendSibling(new TextComponentTranslation("options.difficulty", new Object[0]));
        itextcomponent.appendText(": ");
        itextcomponent.appendSibling(new TextComponentTranslation(p_175355_1_.getDifficultyResourceKey(), new Object[0]));
        return itextcomponent.getFormattedText();
    }

    /**
     * Fired when a key is typed (except F11 which toggles full screen). This is the equivalent of
     * KeyListener.keyTyped(KeyEvent e). Args : character (character on the key), keyCode (lwjgl Keyboard key code)
     */
    protected void keyTyped(char typedChar, int keyCode) throws IOException
    {
        if (keyCode == 1)
        {
            this.mc.gameSettings.saveOptions();
        }

        super.keyTyped(typedChar, keyCode);
    }

    /**
     * Called by the controls from the buttonList when activated. (Mouse pressed for buttons)
     */
    protected void actionPerformed(GuiButton button, int mouseButton) throws IOException
    {
        if (button.enabled)
        {
        	switch(button.id) {
        		case 1:
        			this.mc.displayGuiScreen(new GuiClassement(this.lastScreen, 1));
        			break;
        		case 2:
        			this.mc.displayGuiScreen(new GuiClassement(this.lastScreen, 2));
        			break;
        		case 3:
        			this.mc.displayGuiScreen(new GuiClassement(this.lastScreen, 3));
        			break;
        		case 4:
        			this.mc.displayGuiScreen(new GuiClassement(this.lastScreen, 4));
        			break;
        	}
        }
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
    	int height = (int)((this.height / 14) * 1.5);
    	int y = this.height / 22;
   	    int ok = this.width/26;
   	    
   	 int width = this.width/5;
     int height2 = this.height / 19;
     int h = height2 + height2/3;

    	
    	this.drawDefaultBackground();
    	
        this.drawRect(this.width/8, this.height/8, this.width - this.width/8, this.height - this.height/20, this.BACKGROUND_COLOR);
        this.drawGradientRect(this.width/8 - ok, y, this.width - this.width/8 + ok, y + height, this.FIRST_GRADIENT_COLOR, this.SECOND_GRADIENT_COLOR);
    	
        
    	if(this.type == 1) {
    	}
    	else if(this.type == 2) {
    	}
    	else if(this.type == 3) {
    	}
    	else if(this.type == 4) {
    		if(this.info != null) {
    			
        		this.drawRoundedRectWithContent(this.width/2 + (int)(h/1.5), (int)(h*4.25), (int)(this.width/4.5), (int)(h*1.4), 0xFF2A2A2A, 5, this.mc.fontrenderer, this.info.get(0).toString());
        		// points
        		this.drawRoundedRectWithContent(this.width/2 - h, (int)(h*6.5), (int)(this.width/8), h, 0xFF2A2A2A, 5, this.mc.fontrenderer, "Points");
        		this.drawRoundedRectWithContent(this.width/2 + (int)(this.width/8) - (int)(h/1.25),  (int)(h*6.5), 60, h, 0xFF2A2A2A, 5, this.mc.fontrenderer, this.info.get(1).toString());
        		// kills
        		this.drawRoundedRectWithContent(this.width/2 - h, (int)(h*7.75), (int)(this.width/8), h, 0xFF2A2A2A, 5, this.mc.fontrenderer, "Kills");
        		this.drawRoundedRectWithContent(this.width/2 + (int)(this.width/8) - (int)(h/1.25), (int)(h*7.75), 60, h, 0xFF2A2A2A, 5, this.mc.fontrenderer, this.info.get(2).toString());
        		// deaths
        		this.drawRoundedRectWithContent(this.width/2 - h, (int)(h*9), (int)(this.width/8), h, 0xFF2A2A2A, 5, this.mc.fontrenderer, "Deaths");
        		this.drawRoundedRectWithContent(this.width/2 + (int)(this.width/8) - (int)(h/1.25), (int)(h*9), 60, h, 0xFF2A2A2A, 5, this.mc.fontrenderer, this.info.get(3).toString());
        		// ratio k/D
        		this.drawRoundedRectWithContent(this.width/2 - h, (int)(h*10.25), (int)(this.width/8), h, 0xFF2A2A2A, 5, this.mc.fontrenderer, "K/D Ratio");
        		float ratio = (int)this.info.get(3) == 0 ? (int)this.info.get(2) : (int)this.info.get(2) / (int)this.info.get(3);
        		this.drawRoundedRectWithContent(this.width/2 + (int)(this.width/8) - (int)(h/1.25), (int)(h*10.25), 60, h, 0xFF2A2A2A, 5, this.mc.fontrenderer,Float.toString(ratio));
   
        		this.drawRoundedPlayerHead(this.width/2 - h, (int)(h*4.25), (int)(h*1.4), (int)(h*1.4), 5, Minecraft.getMinecraft().getSession().getUsername());
    		}
    	}
    	
    	this.mc.fontrendererTitle.drawCenteredString(this.title, this.width / 2, y + (height-1)/2, 0xFFFFFFFF);
    	
       super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
