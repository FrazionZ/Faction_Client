package fz.frazionz.client.gui;

import fz.frazionz.FzClient;
import fz.frazionz.TTFFontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;

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

        this.drawTitle();
        
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    
    
    /**
     * Draw the title.
     */
    protected void drawTitle() {
        TTFFontRenderer titleRenderer = FzClient.getInstance().getTTFFontRenderers().get(24);
        int titleSize = titleRenderer.getWidth(this.title);
		
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
