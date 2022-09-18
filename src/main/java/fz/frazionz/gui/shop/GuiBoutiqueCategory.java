package fz.frazionz.gui.shop;

import java.io.IOException;

import fz.frazionz.Client;
import fz.frazionz.TTFFontRenderer;
import fz.frazionz.gui.GuiFrazionZInterface;
import fz.frazionz.gui.utils.RoundedShaderRenderer;
import fz.frazionz.utils.FzUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;

public class GuiBoutiqueCategory extends GuiFrazionZInterface {
	
	private GuiBoutiqueCategoryList boutiqueTypeList;
	
	public GuiBoutiqueCategory(GuiScreen lastScreen, Minecraft mc)
	{	
		super("Boutique", lastScreen, mc);
		this.hasBackButton = true;
		this.drawButtonLater = true;
	}
	
	public void initGui()
	{
        super.initGui();
        this.boutiqueTypeList = new GuiBoutiqueCategoryList(this, this.mc, this.guiLeft, this.guiLeft + this.xSize, this.guiTop + 45, this.guiTop + this.ySize - 38, 36);
	}

    public void handleMouseInput() throws IOException
    {
        super.handleMouseInput();
        this.boutiqueTypeList.handleMouseInput();
    }
	
	
	protected void actionPerformed(GuiButton button, int mouseButton) throws IOException {
        switch (button.id)
        {	
			case 1:
				this.mc.displayGuiScreen(lastScreen);
				break;
            default:
                this.boutiqueTypeList.actionPerformed(button, mouseButton);
                break;
        }
	}

	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		
        this.boutiqueTypeList.drawScreen(mouseX, mouseY, partialTicks);
        this.drawTopList();
        this.drawButtons(mouseX, mouseY, partialTicks);

        double money = (double)(Client.getInstance().getFactionProfile().getMoney());
        String s = "\u00A76M\u00A7foney : " + FzUtils.convertMoney(money) + " Coins";
        this.fontRenderer.drawString(s, this.width / 2 - this.fontRenderer.getStringWidth(s) / 2, this.guiTop + this.ySize - 22, 16777215, true);
	}
    
    @Override
    public void drawTitle() {
    }
	
	public void drawTopList()
	{
        RoundedShaderRenderer.getInstance().drawRoundRect(this.guiLeft, this.guiTop, this.xSize, this.ySize, 8, 0x15171B);
		
		
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

}
