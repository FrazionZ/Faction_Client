package net.minecraft.client.gui;

import java.io.Closeable;
import java.io.IOException;
import java.util.Random;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;

import com.google.common.util.concurrent.Runnables;

import fz.frazionz.FzClient;
import fz.frazionz.client.gui.buttons.GuiMenuButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.realms.RealmsBridge;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.StringUtils;
import net.minecraft.util.text.TextFormatting;

public class GuiMainMenu extends GuiScreen
{
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Random RANDOM = new Random();

    /**
     * A random number between 0.0 and 1.0, used to determine if the title screen says <a
     * href="https://minecraft.gamepedia.com/Menu_screen#Minceraft">Minceraft</a> instead of Minecraft. Set during
     * construction; if the value is less than .0001, then Minceraft is displayed.
     */
    private final float minceraftRoll;

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
    private static final ResourceLocation INTERFACE_BACKGROUND_FZ = new ResourceLocation("textures/gui/frazionz/interface_background.png");
    //private static DynamicTexture AVATAR_HEAD;
    
    /** An array of all the paths to the panorama pictures. */
    private static final ResourceLocation[] TITLE_PANORAMA_PATHS = new ResourceLocation[] {new ResourceLocation("textures/gui/title/background/panorama_0.png"), new ResourceLocation("textures/gui/title/background/panorama_1.png"), new ResourceLocation("textures/gui/title/background/panorama_2.png"), new ResourceLocation("textures/gui/title/background/panorama_3.png"), new ResourceLocation("textures/gui/title/background/panorama_4.png"), new ResourceLocation("textures/gui/title/background/panorama_5.png")};
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
    private int widthCopyright;
    private int widthCopyrightRest;
    private GuiButton modButton;
    private GuiScreen modUpdateNotification;
    
    private int menuWidth;
    private int menuHeight;
    
    public GuiMainMenu()
    {
    	this.openGLWarning2 = MORE_INFO_TEXT;
        IResource iresource = null;
        {
            IOUtils.closeQuietly((Closeable)iresource);
        }
        this.minceraftRoll = RANDOM.nextFloat();
        this.openGLWarning1 = "";

        if (!GLContext.getCapabilities().OpenGL20 && !OpenGlHelper.areShadersSupported())
        {
            this.openGLWarning1 = I18n.format("title.oldgl1");
            this.openGLWarning2 = I18n.format("title.oldgl2");
            this.openGLWarningLink = "https://help.mojang.com/customer/portal/articles/325948?ref=game";
        }
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
        /*
         * if (this.areRealmsNotificationsEnabled())
        {
            this.realmsNotification.updateScreen();
        }
        */
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
    	this.menuWidth = 220;
    	this.menuHeight = this.height;

        FzClient.getInstance().getDiscordRP().update("Menu Principal", "www.frazionz.net");
        this.viewportTexture = new DynamicTexture(256, 256);
        this.backgroundTexture = this.mc.getTextureManager().getDynamicTextureLocation("background", this.viewportTexture);
        this.widthCopyright = this.fontRenderer.getStringWidth("Copyright Mojang AB. Do not distribute!");
        this.widthCopyrightRest = this.width - this.widthCopyright - 2;
        
        this.addMenuButtons(160, 28, 8);
    	
        /*try {
            AVATAR_HEAD = new DynamicTexture(this.getDynamicTextureFromUrl(new File(FzUtils.getLauncherDir(), "avatars/"+this.mc.getSession().getPlayerID()+".png")));
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        /*positionProfil = 0;
        currentProfileItem = PlayerDataStocker.getPlayer(positionProfil);*/

        
        /*synchronized (this.threadLock)
        {
            this.openGLWarning1Width = this.fontRenderer.getStringWidth(this.openGLWarning1);
            this.openGLWarning2Width = this.fontRenderer.getStringWidth(this.openGLWarning2);
            int k = Math.max(this.openGLWarning1Width, this.openGLWarning2Width);
            this.openGLWarningX1 = (this.width - k) / 2;
            this.openGLWarningX2 = this.openGLWarningX1 + k;
            this.openGLWarningY2 = this.openGLWarningY1 + 24;
        }*/
    }

    private void addMenuButtons(int width , int height, int gap)
    {
    	int menuPadding = 20;
    	
    	this.buttonList.add(new GuiMenuButton(1, menuPadding, menuPadding, width, height, "REJOINDRE"));
    	this.buttonList.add(new GuiMenuButton(2, menuPadding, menuPadding + height + gap, width, height, "SOLO"));
    	this.buttonList.add(new GuiMenuButton(3, menuPadding, menuPadding + height*2 + gap*2, width, height, "OPTIONS"));
    	this.buttonList.add(new GuiMenuButton(4, menuPadding, this.height - menuPadding - height, width, height, "QUITTER"));
    }

    /**
     * Called by the controls from the buttonList when activated. (Mouse pressed for buttons)
     */
    protected void actionPerformed(GuiButton button, int keyCode) throws IOException
    {
        switch(button.id)
        {
 	       case 1:
               //this.mc.displayGuiScreen(new GuiConnecting(this, mc, "localhost", 25565));
               //this.mc.displayGuiScreen(new GuiConnecting(this, mc, "185.157.246.85", 25587));
               this.mc.displayGuiScreen(new GuiConnecting(this, mc, "frazionz.net", 25565));
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
        }
    }

    private void switchToRealms()
    {
        RealmsBridge realmsbridge = new RealmsBridge();
        realmsbridge.switchToRealms(this);
    }

    public void confirmClicked(boolean result, int id)
    {
        /*if (result && id == 12)
        {
            ISaveFormat isaveformat = this.mc.getSaveLoader();
            isaveformat.flushCache();
            isaveformat.deleteWorldDirectory("Demo_World");
            this.mc.displayGuiScreen(this);
        }
        else if (id == 12)
        {*/
            this.mc.displayGuiScreen(this);
        /*}
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
        }*/
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        mc.getTextureManager().bindTexture(BACKGROUND_TEXTURE);
    	GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        Gui.drawScaledCustomSizeModalRect(0, 0, 0, 0, 1, 1, this.width, this.height, 1, 1);
        GL11.glEnable(GL11.GL_BLEND);
        
        String info = "FrazionZ n'est pas affilié à Mojang";
        this.drawString(this.fontRenderer, info, this.width - this.fontRenderer.getStringWidth(info) - 1, this.height - 10, -1);

        this.widthCopyright = this.fontRenderer.getStringWidth("Copyright Mojang AB. Do not distribute!");
        
        // Money 
        
        /*
         * Locale usa = new Locale("en", "US");
        Currency dollars = Currency.getInstance(usa);
        NumberFormat dollarFormat = NumberFormat.getCurrencyInstance(usa);

        String money = null;
        if(Client.getInstance().getFactionProfile() == null)
            this.fontRenderer.drawScaleString(connectedAt, this.width - 125, 20, 1.2, Color.white);
        else {
            money = Client.getInstance().getFactionProfile().getMoney();
            if (money == null)
                this.fontRenderer.drawScaleString(connectedAt, this.width - 125, 20, 1.2, Color.white);
            this.fontRenderer.drawScaleString(connectedAt, this.width - 120, 17, 1.2, Color.white);
            this.fontRenderer.drawScaleString(FzUtils.convertMoney(money)+" Coins", this.width - 120, 28, 0.8, Color.white);
        }
        //this.drawString(this.fontRenderer, "Copyright Mojang AB. Do not distribute!", this.widthCopyrightRest, this.height - 10, -1);

        /*if(AVATAR_HEAD != null){
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, AVATAR_HEAD.getGlTextureId());
            int size = ((money == null) ? 16 : 20);
            int y = ((money == null) ? 17 : 15);
            drawModalRectWithCustomSizedTexture(width - 145, y, 0.0F, 0.0F, size, size, size, size);
        }*/

        /*if (mouseX > this.width - this.fontRenderer.getStringWidth(info) - 1 && mouseX < this.width - this.fontRenderer.getStringWidth(info) - 1 + this.widthCopyright && mouseY > this.height - 10 && mouseY < this.height && Mouse.isInsideWindow())
        {
            drawRect(this.width - this.fontRenderer.getStringWidth(info) - 1, this.height - 1, this.width - this.fontRenderer.getStringWidth(info) - 20 + this.widthCopyright, this.height, -1);
        }*/
        
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

        if (mouseX > this.widthCopyrightRest && mouseX < this.widthCopyrightRest + this.widthCopyright && mouseY > this.height - 10 && mouseY < this.height)
        {
            this.mc.displayGuiScreen(new GuiWinGame(false, Runnables.doNothing()));
        }
    }

    /**
     * Called when the screen is unloaded. Used to disable keyboard repeat events
     */
    public void onGuiClosed()
    {
    }
}
