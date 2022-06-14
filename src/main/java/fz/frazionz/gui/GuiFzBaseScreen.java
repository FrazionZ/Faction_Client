package fz.frazionz.gui;

import java.io.IOException;

import fz.frazionz.gui.buttons.GuiFzButton;
import fz.frazionz.gui.buttons.GuiFzOptionButton;
import fz.frazionz.gui.buttons.GuiFzSlider;
import fz.frazionz.gui.renderer.fonts.FzFontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiControls;
import net.minecraft.client.gui.GuiCustomizeSkin;
import net.minecraft.client.gui.GuiLanguage;
import net.minecraft.client.gui.GuiLockIconButton;
import net.minecraft.client.gui.GuiOptionButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiScreenOptionsSounds;
import net.minecraft.client.gui.GuiScreenResourcePacks;
import net.minecraft.client.gui.GuiSnooper;
import net.minecraft.client.gui.GuiVideoSettings;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.gui.ScreenChatOptions;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.EnumDifficulty;

public class GuiFzBaseScreen extends GuiScreen
{
    protected final GuiScreen lastScreen;
    protected String title = "Default Title";
    
	protected final int xSize = 368;
	protected final int ySize = 238;
	protected int guiLeft;
	protected int guiTop;

    public GuiFzBaseScreen(GuiScreen lastScreen)
    {
        this.lastScreen = lastScreen;
    }

    /**
     * Adds the buttons (and other controls) to the screen in question. Called when the GUI is displayed and when the
     * window resizes, the buttonList is cleared beforehand.
     */
    public void initGui()
    {        
		this.guiLeft = (this.width - this.xSize) / 2;
		this.guiTop = (this.height - this.ySize) / 2;
        
        addAllButtons();
    }
    
    /**
     * Add all buttons
     */
    protected void addAllButtons()
    {    
    }
    
    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {    	
    	this.drawDefaultBackground();
    	
        this.mc.getTextureManager().bindTexture(INTERFACE_BACKGROUND_1);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.enableBlend();
        this.drawModalRectWithCustomSizedTexture(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize, 512.0F, 512.0F);
    	this.drawTitle();
        
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    
    
    /**
     * Draw the title.
     */
    protected void drawTitle() {
        FzFontRenderer titleRenderer = this.mc.fzFontRenderers.get(24);
        int titleSize = titleRenderer.getStringWidth(this.title);
		
		// draw title background
        this.mc.getTextureManager().bindTexture(INTERFACE_BACKGROUND_2);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.enableBlend();
        this.drawModalRectWithCustomSizedTexture(this.guiLeft + this.xSize/2 - titleSize/2 - 28, this.guiTop - 10, 0, 0, 28, 26, 512.0F, 512.0F);
        this.mc.getTextureManager().bindTexture(INTERFACE_BACKGROUND_2);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.enableBlend();
        this.drawModalRectWithCustomSizedTexture(this.guiLeft + this.xSize/2 - titleSize/2, this.guiTop - 10, 28, 0, titleSize, 26, 512.0F, 512.0F);
        this.mc.getTextureManager().bindTexture(INTERFACE_BACKGROUND_2);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.enableBlend();
        this.drawModalRectWithCustomSizedTexture(this.guiLeft + this.xSize/2 + titleSize/2, this.guiTop - 10, 366, 0, 28 , 26, 512.0F, 512.0F);
        
        titleRenderer.drawCenteredString(this.title, this.width / 2, this.height/2 - 116, 0xFFFFFFFF);
	}
}
