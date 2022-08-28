package fz.frazionz.gui;

import java.io.IOException;

import fz.frazionz.Client;
import fz.frazionz.TTFFontRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.resources.ResourceLocation;

public class GuiFrazionZInterface extends GuiScreen {

	protected static final ResourceLocation BACKGROUND_1 = new ResourceLocation("textures/gui/frazionz/interface_background.png");
	protected static final ResourceLocation BACKGROUND_2 = new ResourceLocation("textures/gui/frazionz/interface_background_2.png");
	protected static final ResourceLocation ICONS = new ResourceLocation("textures/gui/frazionz/icons.png");
	
	protected final GuiScreen lastScreen;
	protected final int xSize = 368;
	protected final int ySize = 238;
	protected int guiLeft;
	protected int guiTop;
	protected String title;
	protected GuiBackButton backButton;
	protected boolean hasBackButton;
	protected boolean drawButtonLater;

	public GuiFrazionZInterface(String title, GuiScreen lastScreen, Minecraft mc) {
		this.lastScreen = lastScreen;
		this.mc = mc;
		this.title = title;
		this.hasBackButton = false;
		this.drawButtonLater = false;
	}
	
	public void initGui() {
		this.guiLeft = (this.width - this.xSize) / 2;
		this.guiTop = (this.height - this.ySize) / 2;
		
		if(this.hasBackButton) {
			this.backButton = new GuiBackButton(1, this.guiLeft - 6, this.guiTop - 6);
			this.buttonList.add(this.backButton);
		}
		super.initGui();
	}
	
    public void updateScreen()
    {
        super.updateScreen();
    }
	
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		this.drawBackgroundImage();
        this.drawTitle();
        if(!drawButtonLater)
        	this.drawButtons(mouseX, mouseY, partialTicks);
	}
	
	@Override
	protected void actionPerformed(GuiButton button, int keyCode) throws IOException {
		
		switch(keyCode) {
			case 1:
				this.mc.displayGuiScreen(lastScreen);
				break;
		}
		
	}
	
	public void drawBackgroundImage() {
		
        this.mc.getTextureManager().bindTexture(BACKGROUND_1);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.enableBlend();
        this.drawModalRectWithCustomSizedTexture(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize, 512.0F, 512.0F);
		
	}
	
	public void drawIcon(int x, int y, int iconX, int iconY) {
        this.mc.getTextureManager().bindTexture(ICONS);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.enableBlend();
        this.drawModalRectWithCustomSizedTexture(x, y, iconX*16, iconY*16, 16, 16, 128.0F, 128.0F);
	}
	
	public void drawTitle() {
        TTFFontRenderer titleRenderer = Client.getInstance().getTTFFontRenderers().get(24);
        int titleSize = titleRenderer.getWidth(this.title);
		
		// draw title background
        this.mc.getTextureManager().bindTexture(BACKGROUND_2);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.enableBlend();
        this.drawModalRectWithCustomSizedTexture(this.guiLeft + this.xSize/2 - titleSize/2 - 28, this.guiTop - 10, 0, 0, 28, 26, 512.0F, 512.0F);
        this.mc.getTextureManager().bindTexture(BACKGROUND_2);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.enableBlend();
        this.drawModalRectWithCustomSizedTexture(this.guiLeft + this.xSize/2 - titleSize/2, this.guiTop - 10, 28, 0, titleSize, 26, 512.0F, 512.0F);
        this.mc.getTextureManager().bindTexture(BACKGROUND_2);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.enableBlend();
        this.drawModalRectWithCustomSizedTexture(this.guiLeft + this.xSize/2 + titleSize/2, this.guiTop - 10, 366, 0, 28 , 26, 512.0F, 512.0F);
                
		titleRenderer.drawCenteredString(this.title, this.guiLeft + this.xSize/2, this.guiTop + 3, 0xFFFFFFFF);
	}
	
	public class GuiBackButton extends GuiButton
	{
	    public GuiBackButton(int buttonId, int x, int y)
	    {
			super(buttonId, x, y, 26, 26, "");
	    }

	    public void setPosition(int x, int y)
	    {
	        this.x = x;
	        this.y = y;
	    }

	    public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks)
	    {
	        if (this.visible)
	        {
	            this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + 26 && mouseY < this.y + 26;
	            int i = 460;
	            int j = 0;

	            if (this.hovered)
	            {
	                i += 26;
	            }
	            
	            mc.getTextureManager().bindTexture(BACKGROUND_2);
	            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
	            GlStateManager.enableBlend();
	            this.drawModalRectWithCustomSizedTexture(this.x, this.y, i, j, 26, 26, 512.0F, 512.0F);
	            
	            drawIcon(this.x + 5, this.y + 5, 0, 0);
	        }
	    }
	}
}
