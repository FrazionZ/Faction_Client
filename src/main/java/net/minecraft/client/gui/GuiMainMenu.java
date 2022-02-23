package net.minecraft.client.gui;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URI;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import fz.frazionz.api.data.PlayerDataStocker;
import fz.frazionz.api.gsonObj.ProfilItem;
import fz.frazionz.gui.GuiButtonImage;
import fz.frazionz.gui.GuiDropdown;
import fz.frazionz.gui.GuiShopItemsList;
import fz.frazionz.gui.toasts.FzToast;
import fz.frazionz.gui.toasts.SuccessToast;
import fz.frazionz.packets.server.SPacketToast;
import fz.frazionz.utils.FzUtils;
import net.minecraft.client.gui.toasts.SystemToast;
import net.minecraft.util.Session;
import net.minecraft.util.text.TextComponentString;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.Runnables;

import fz.frazionz.Client;
import fz.frazionz.forgemods.smoothscrollingeverywhere.RunSixtyTimesEverySec;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.storage.ISaveFormat;
import optifine.CustomPanorama;
import optifine.CustomPanoramaProperties;
import optifine.Reflector;

import javax.imageio.ImageIO;

public class GuiMainMenu extends GuiScreen
{	
    private ServerSelectionList serverListSelector;
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Random RANDOM = new Random();

    /** Counts the number of screen updates. */
    private final float updateCounter;

    /** The splash message. */
    private String splashText;
    private GuiButton buttonResetDemo;

    /** Timer used to rotate the panorama, increases every tick. */
    private float panoramaTimer;

    /**
     * Texture allocated for the current viewport of the main menu's panorama background.
     */
    private DynamicTexture viewportTexture;

    /**
     * The Object object utilized as a thread lock when performing non thread-safe operations
     */
    private final Object threadLock = new Object();
    public static final String MORE_INFO_TEXT = "Please click " + TextFormatting.UNDERLINE + "here" + TextFormatting.RESET + " for more information.";

    /** Width of openGLWarning2 */
    private int openGLWarning2Width;

    /** Width of openGLWarning1 */
    private int openGLWarning1Width;

    /** Left x coordinate of the OpenGL warning */
    private int openGLWarningX1;

    /** Top y coordinate of the OpenGL warning */
    private int openGLWarningY1;

    /** Right x coordinate of the OpenGL warning */
    private int openGLWarningX2;

    /** Bottom y coordinate of the OpenGL warning */
    private int openGLWarningY2;

    /** OpenGL graphics card warning. */
    private String openGLWarning1;

    /** OpenGL graphics card warning. */
    private String openGLWarning2;

    /** Link to the Mojang Support about minimum requirements */
    private String openGLWarningLink;
    
    private Random rand = new Random();
    
    private static final ResourceLocation BACKGROUND_TEXTURE  = new ResourceLocation("textures/gui/title/background/fz_background.png");
    private static final ResourceLocation MINECRAFT_TITLE_TEXTURES = new ResourceLocation("textures/gui/title/background/fz_logo.png");
    private static final ResourceLocation field_194400_H = new ResourceLocation("textures/gui/title/background/fz_logo.png");
    private static final ResourceLocation INTERFACE_BACKGROUND_FZ = new ResourceLocation("textures/gui/frazionz/interface_background.png");
    //private static DynamicTexture AVATAR_HEAD;

    private float i = 0;
    private boolean boolI = false;
    private boolean reverse = false;
    
    /** An array of all the paths to the panorama pictures. */
    private ResourceLocation backgroundTexture;

    /** Minecraft Realms button. */
    private GuiButton realmsButton;

    /** Has the check for a realms notification screen been performed? */
    private boolean hasCheckedForRealmsNotification;

    /**
     * A screen generated by realms for notifications; drawn in adition to the main menu (buttons and such from both are
     * drawn at the same time). May be null.
     */
    private GuiScreen realmsNotification;
    private int field_193978_M;
    private int field_193979_N;
    private GuiButton modButton;
    private GuiScreen modUpdateNotification;
    private RunSixtyTimesEverySec scroller;
    private int xInfosUser;
    private GuiDropdown dropdownUserAccount;
    private String connectedAt;
    /*private GuiButtonImage leftSwitchAccountButton;
	private GuiButtonImage rightSwitchAccountButton;
	private GuiRoundedButton loginSwitchAccountButton;
    private ProfilItem currentProfileItem;
    private int positionProfil;
    private boolean showAccount = false;*/

    public GuiMainMenu()
    {
        this.openGLWarning2 = MORE_INFO_TEXT;
        IResource iresource = null;
        {
            IOUtils.closeQuietly((Closeable)iresource);
        }
        this.updateCounter = RANDOM.nextFloat();
        this.openGLWarning1 = "";


        PlayerDataStocker.loadProfilItems();

        if (!GLContext.getCapabilities().OpenGL20 && !OpenGlHelper.areShadersSupported())
        {
            this.openGLWarning1 = I18n.format("title.oldgl1");
            this.openGLWarning2 = I18n.format("title.oldgl2");
            this.openGLWarningLink = "https://help.mojang.com/customer/portal/articles/325948?ref=game";
        }
        
        this.scroller = (() -> {
        	if(this.boolI) { // DESCEND
        		if(this.i - this.height/22 < this.height/22) {
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
        		if(this.i + this.height/22 > this.height/22) {
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
        
        this.scroller.registerTick();
    }

    /**
     * Is there currently a realms notification screen, and are realms notifications enabled?
     */
    private boolean areRealmsNotificationsEnabled()
    {
        return Minecraft.getMinecraft().gameSettings.getOptionOrdinalValue(GameSettings.Options.REALMS_NOTIFICATIONS) && this.realmsNotification != null;
    }
    
    /**
     * Called from the main game loop to update the screen.
     */
    public void updateScreen()
    {	
    }

    /**
     * Returns true if this GUI should pause the game when it is displayed in single-player
     */
    public boolean doesGuiPauseGame()
    {
        return false;
    }

    /**
     * Fired when a key is typed (except F11 which toggles full screen). This is the equivalent of
     * KeyListener.keyTyped(KeyEvent e). Args : character (character on the key), keyCode (lwjgl Keyboard key code)
     */
    protected void keyTyped(char typedChar, int keyCode) throws IOException
    {
    }

    /**
     * Adds the buttons (and other controls) to the screen in question. Called when the GUI is displayed and when the
     * window resizes, the buttonList is cleared beforehand.
     */
    
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

        /*try {
            AVATAR_HEAD = new DynamicTexture(this.getDynamicTextureFromUrl(new File(FzUtils.getLauncherDir(), "avatars/"+this.mc.getSession().getPlayerID()+".png")));
        } catch (IOException e) {
            e.printStackTrace();
        }



        positionProfil = 0;
        currentProfileItem = PlayerDataStocker.getPlayer(positionProfil);*/

        Client.getInstance().getDiscordRP().update("Menu Principal", "www.frazionz.net");
        this.viewportTexture = new DynamicTexture(256, 256);
        this.backgroundTexture = this.mc.getTextureManager().getDynamicTextureLocation("background", this.viewportTexture);
        this.field_193978_M = this.fontRendererObj.getStringWidth("Copyright Mojang AB. Do not distribute!");
        this.field_193979_N = this.width - this.field_193978_M - 2;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        this.addAllButtons(width, height, h);

        /*connectedAt = this.mc.getSession().getUsername();
        xInfosUser = this.width - this.fontRendererObj.getStringWidth(connectedAt);
        dropdownUserAccount = new GuiDropdown(this.mc, xInfosUser - 42, 35, this.width -10, 100);*/

        synchronized (this.threadLock)
        {
            this.openGLWarning1Width = this.fontRendererObj.getStringWidth(this.openGLWarning1);
            this.openGLWarning2Width = this.fontRendererObj.getStringWidth(this.openGLWarning2);
            int k = Math.max(this.openGLWarning1Width, this.openGLWarning2Width);
            this.openGLWarningX1 = (this.width - k) / 2;
            this.openGLWarningX2 = this.openGLWarningX1 + k;
            this.openGLWarningY2 = this.openGLWarningY1 + 24;
        }
    }

    /**
     * Adds Singleplayer and Multiplayer buttons on Main Menu for players who have bought the game.
     */
    private void addAllButtons(int width , int height, int h)
    {
    	int x = this.width/16;
    	int y = (this.height / 20);
    	
        this.buttonList.add(new GuiRoundedButton(1, x, y, width, height, "REJOINDRE", false, this.mc.fontrenderer, 1));
        this.buttonList.add(new GuiRoundedButton(2, x, y + h, width, height, "SOLO", false, this.mc.fontrenderer, 1));
        this.buttonList.add(new GuiRoundedButton(3, x, y + (h * 2), width, height, "OPTIONS", false, this.mc.fontrenderer, 1));
        this.buttonList.add(new GuiRoundedButton(4, x, this.height - height - y, width, height, "QUITTER", true, this.mc.fontrenderer, 1));

        /*leftSwitchAccountButton = new GuiButtonImage(6, "", this.width - 166, 9, 25, 25, 350, 311, true, INTERFACE_BACKGROUND_FZ);
        rightSwitchAccountButton = new GuiButtonImage(7, "", this.width - 29, 9, 25, 25, 325, 311, true, INTERFACE_BACKGROUND_FZ);
        loginSwitchAccountButton = new GuiRoundedButton(8, this.width - 125, 35, 80, height, "Se connecter", false, this.mc.fontrenderer, 1);
        leftSwitchAccountButton.visible = false;
        rightSwitchAccountButton.visible = false; //(PlayerDataStocker.profilItems.size() >= 2);
        loginSwitchAccountButton.visible = false;
        this.buttonList.add(leftSwitchAccountButton);
        this.buttonList.add(rightSwitchAccountButton);
        this.buttonList.add(loginSwitchAccountButton);*/
    }
    
    /**
    * Called by the controls from the buttonList when activated. (Mouse pressed for buttons)
    */
    protected void actionPerformed(GuiButton button, int mouseButton) throws IOException
    {
       switch(button.id)
       {
	       case 1:
		    	//this.mc.displayGuiScreen(new GuiConnecting(this, mc, "localhost", 25565));
		    	this.mc.displayGuiScreen(new GuiConnecting(this, mc, "185.157.246.85", 25587));
		       	break;
	       case 2:
	           this.mc.displayGuiScreen(new GuiWorldSelection(this));
	           break;
	       case 3: 
	    	   this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
	           break;
	       case 4:
	    	   this.mc.shutdown();
	    	   break;
           /*case 6:
               positionProfil--;
               changeProfile(PlayerDataStocker.getPlayer(positionProfil));
               rightSwitchAccountButton.visible = true;
               if(positionProfil == 0)
                   leftSwitchAccountButton.visible = false;
               break;
           case 7:
               positionProfil++;
               changeProfile(PlayerDataStocker.getPlayer(positionProfil));
               leftSwitchAccountButton.visible = true;
               if(positionProfil + 1 == PlayerDataStocker.profilItems.size())
                   rightSwitchAccountButton.visible = false;
               break;
           case 8:
               Session session = new Session(currentProfileItem.getUsername(), currentProfileItem.getUuid(), currentProfileItem.getToken(), "legacy", false);
               this.mc.setSession(session);
               loginSwitchAccountButton.visible = false;
               PlayerDataStocker.loadProfilItems();
               toggleChangeAccount();
               this.mc.getGuiToast().func_192988_a(new FzToast(SPacketToast.Type.NORMAL, SPacketToast.Icon.LOGO, new TextComponentString("INFORMATION"), new TextComponentString("Bonjour "+currentProfileItem.getUsername()+" !")));
               break;*/
       }    	
    }

    /*public void changeProfile(ProfilItem profilItem){
        currentProfileItem = profilItem;
        connectedAt = currentProfileItem.getUsername();
        try {
            AVATAR_HEAD = new DynamicTexture(this.getDynamicTextureFromUrl(new File(FzUtils.getLauncherDir(), "avatars/"+currentProfileItem.getUuid()+".png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        loginSwitchAccountButton.visible = !currentProfileItem.getUuid().equalsIgnoreCase(this.mc.getSession().getPlayerID());
    }*/

    public void confirmClicked(boolean result, int id)
    {
        if (result && id == 12)
        {
            ISaveFormat isaveformat = this.mc.getSaveLoader();
            isaveformat.flushCache();
            isaveformat.deleteWorldDirectory("Demo_World");
            this.mc.displayGuiScreen(this);
        }
        else if (id == 12)
        {
            this.mc.displayGuiScreen(this);
        }
        else if (id == 13)
        {
            if (result)
            {
                try
                {
                    Class<?> oclass = Class.forName("java.awt.Desktop");
                    Object object = oclass.getMethod("getDesktop").invoke((Object)null);
                    oclass.getMethod("browse", URI.class).invoke(object, new URI(this.openGLWarningLink));
                }
                catch (Throwable throwable1)
                {
                    LOGGER.error("Couldn't open link", throwable1);
                }
            }

            this.mc.displayGuiScreen(this);
        }
    }

    /*private void renderSkybox(int mouseX, int mouseY, float partialTicks)
    {
        this.mc.getFramebuffer().unbindFramebuffer();
        GlStateManager.viewport(0, 0, 256, 256);
        //this.drawPanorama(mouseX, mouseY, partialTicks);
        int i = 3;
        CustomPanoramaProperties custompanoramaproperties = CustomPanorama.getCustomPanoramaProperties();

        if (custompanoramaproperties != null)
        {
            i = custompanoramaproperties.getBlur3();
        }

        this.mc.getFramebuffer().bindFramebuffer(true);
        GlStateManager.viewport(0, 0, this.mc.displayWidth, this.mc.displayHeight);
        float f2 = 120.0F / (float)(this.width > this.height ? this.width : this.height);
        float f = (float)this.height * f2 / 256.0F;
        float f1 = (float)this.width * f2 / 256.0F;
        int k = this.width;
        int l = this.height;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
        bufferbuilder.pos(0.0D, (double)l, (double)this.zLevel).tex((double)(0.5F - f), (double)(0.5F + f1)).color(1.0F, 1.0F, 1.0F, 1.0F).endVertex();
        bufferbuilder.pos((double)k, (double)l, (double)this.zLevel).tex((double)(0.5F - f), (double)(0.5F - f1)).color(1.0F, 1.0F, 1.0F, 1.0F).endVertex();
        bufferbuilder.pos((double)k, 0.0D, (double)this.zLevel).tex((double)(0.5F + f), (double)(0.5F - f1)).color(1.0F, 1.0F, 1.0F, 1.0F).endVertex();
        bufferbuilder.pos(0.0D, 0.0D, (double)this.zLevel).tex((double)(0.5F + f), (double)(0.5F + f1)).color(1.0F, 1.0F, 1.0F, 1.0F).endVertex();
        tessellator.draw();
    }*/

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        mc.getTextureManager().bindTexture(BACKGROUND_TEXTURE);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        Gui.drawScaledCustomSizeModalRect(0, 0, 0, 0, 1, 1, this.width, this.height, 1, 1);
        
        //GlStateManager.enableAlpha();
        GL11.glEnable(GL11.GL_BLEND);
        //GL11.glDisable(GL11.GL_DEPTH_TEST);
        //GL11.glDepthMask(false);
        //GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        //GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        //GL11.glDisable(GL11.GL_ALPHA_TEST);
        
        // FrazionZ Logo //
        this.mc.getTextureManager().bindTexture(field_194400_H);
        drawModalRectWithCustomSizedTexture(this.width*2/3 - this.width/7, (int) (this.height/4 + this.i), 0.0F, 0.0F, this.height/2 - this.height/40, this.height/2 - this.height/40, this.height/2 - this.height/40, this.height/2 - this.height/40);

        /*if (Reflector.FMLCommonHandler_getBrandings.exists())
        {
            Object object = Reflector.call(Reflector.FMLCommonHandler_instance);
            List<String> list = Lists.<String>reverse((List)Reflector.call(object, Reflector.FMLCommonHandler_getBrandings, true));

            for (int l1 = 0; l1 < list.size(); ++l1)
            {
                String s1 = list.get(l1);

                if (!Strings.isNullOrEmpty(s1))
                {
                    this.drawString(this.fontRendererObj, s1, 2, this.height - (10 + l1 * (this.fontRendererObj.FONT_HEIGHT + 1)), 16777215);
                }
            }
        }
        else
        {
            this.drawString(this.fontRendererObj, "", 2, this.height - 10, -1);
        }*/

        String info = "FrazionZ n'est pas affilié à Mojang";
        this.drawString(this.fontRendererObj, info, this.width - this.fontRendererObj.getStringWidth(info) - 1, this.height - 10, -1);

        this.field_193978_M = this.fontRendererObj.getStringWidth("Copyright Mojang AB. Do not distribute!");
        //this.drawGradientRoundedButton(this.width - 130, 12, this.width - 40, 32, this.FIRST_GRADIENT_COLOR, this.SECOND_GRADIENT_COLOR);

        //this.drawString(this.fontRendererObj, connectedAt, this.width - 110, 18, -1);

        //this.dropdownUserAccount.drawDropdown(this.mc, partialTicks);

        /*if(AVATAR_HEAD != null){
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, AVATAR_HEAD.getGlTextureId());
            drawModalRectWithCustomSizedTexture(width - 130, 14, 0.0F, 0.0F, 16, 16, 16, 16);
        }*/

        if (mouseX > this.width - this.fontRendererObj.getStringWidth(info) - 1 && mouseX < this.width - this.fontRendererObj.getStringWidth(info) - 1 + this.field_193978_M && mouseY > this.height - 10 && mouseY < this.height && Mouse.isInsideWindow())
        {
            drawRect(this.width - this.fontRendererObj.getStringWidth(info) - 1, this.height - 1, this.width - this.fontRendererObj.getStringWidth(info) - 20 + this.field_193978_M, this.height, -1);
        }
        
        this.drawRect(0, 0, this.width/3 - this.width/80, this.height, 0x72000000);
        
        /*if (this.openGLWarning1 != null && !this.openGLWarning1.isEmpty())
        {
            drawRect(this.openGLWarningX1 - 2, this.openGLWarningY1 - 2, this.openGLWarningX2 + 2, this.openGLWarningY2 - 1, 1428160512);
            this.drawString(this.fontRendererObj, this.openGLWarning1, this.openGLWarningX1, this.openGLWarningY1, -1);
            this.drawString(this.fontRendererObj, this.openGLWarning2, (this.width - this.openGLWarning2Width) / 2, (this.buttonList.get(0)).yPosition - 12, -1);
        }*/
        
        super.drawScreen(mouseX, mouseY, partialTicks);
    }



    /**
     * Called when the mouse is clicked. Args : mouseX, mouseY, clickedButton
     */
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
    {
        super.mouseClicked(mouseX, mouseY, mouseButton);

        synchronized (this.threadLock)
        {
            if (!this.openGLWarning1.isEmpty() && !StringUtils.isNullOrEmpty(this.openGLWarningLink) && mouseX >= this.openGLWarningX1 && mouseX <= this.openGLWarningX2 && mouseY >= this.openGLWarningY1 && mouseY <= this.openGLWarningY2)
            {
                GuiConfirmOpenLink guiconfirmopenlink = new GuiConfirmOpenLink(this, this.openGLWarningLink, 13, true);
                guiconfirmopenlink.disableSecurityWarning();
                this.mc.displayGuiScreen(guiconfirmopenlink);
            }
        }

        if (this.areRealmsNotificationsEnabled())
        {
            this.realmsNotification.mouseClicked(mouseX, mouseY, mouseButton);
        }

        if (mouseX > this.field_193979_N && mouseX < this.field_193979_N + this.field_193978_M && mouseY > this.height - 10 && mouseY < this.height)
        {
            this.mc.displayGuiScreen(new GuiWinGame(false, Runnables.doNothing()));
        }

        /*if (mouseX > this.width - 130 && mouseX < this.width - 40 && mouseY > 12 && mouseY < 32){
            loginSwitchAccountButton.playPressSound(this.mc.getSoundHandler());
            toggleChangeAccount();
        }*/
    }

   /* public void toggleChangeAccount(){
        showAccount = !showAccount;
        if(showAccount){
            leftSwitchAccountButton.visible =  (positionProfil != 0);
            rightSwitchAccountButton.visible = (PlayerDataStocker.profilItems.size() >= 2) && (positionProfil + 1 != PlayerDataStocker.profilItems.size());
            loginSwitchAccountButton.visible = !currentProfileItem.getUuid().equalsIgnoreCase(this.mc.getSession().getPlayerID());
        }else{
            positionProfil = 0;
            changeProfile(PlayerDataStocker.getPlayer(this.mc.getSession().getPlayerID()));
            leftSwitchAccountButton.visible = false;
            rightSwitchAccountButton.visible = false;
            loginSwitchAccountButton.visible = false;
        }
    }*/

    /**
     * Called when the screen is unloaded. Used to disable keyboard repeat events
     */
    public void onGuiClosed()
    {
        /*if (this.realmsNotification != null)
        {
            this.realmsNotification.onGuiClosed();
        }*/
    }
}
