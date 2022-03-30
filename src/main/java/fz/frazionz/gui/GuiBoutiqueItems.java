package fz.frazionz.gui;

import java.io.IOException;
import java.util.concurrent.Executors;

import fz.frazionz.Client;
import fz.frazionz.api.gsonObj.BoutiqueType;
import fz.frazionz.data.FzUserData;
import fz.frazionz.utils.FzUtils;
import fz.frazionz.utils.MathUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class GuiBoutiqueItems extends GuiScreen {
	
	private static final ResourceLocation background = new ResourceLocation("textures/gui/frazionz/interface_background.png");	
	private final GuiScreen lastScreen;
	private GuiBoutiqueItemsList boutiqueTypeList;
	private BoutiqueType type;
	
	private Minecraft mc;
	
	public GuiBoutiqueItems(GuiScreen lastScreen, Minecraft mc, BoutiqueType type)
	{	
		this.lastScreen = lastScreen;
		this.mc = mc;
		this.type = type;
	}
	
	// 776 - 478 // 
	
	private final int xSize = 388;
	private final int ySize = 239;
	
	private int guiLeft;
	private int guiTop;
	
	public void initGui()
	{
        
		guiLeft = (this.width - this.xSize) / 2;
		guiTop = (this.height - this.ySize) / 2;
		
        this.boutiqueTypeList = new GuiBoutiqueItemsList(this, this.type, this.mc, this.guiLeft, this.guiLeft + this.xSize, this.guiTop + 45, this.guiTop + this.ySize - 38, 36, 4);
        this.buttonList.add(new GuiButtonOnlyImage(50, this.guiLeft + 10, this.guiTop + 5, 21, 20, 388, 0, 0, background));

        Executors.newCachedThreadPool().submit(new Runnable() {
            @Override
            public void run() {
                Client.getInstance().updateFProfile();
            }
        });

		super.initGui();
	}
	
    /**
     * Handles mouse input.
     */
    public void handleMouseInput() throws IOException
    {
        super.handleMouseInput();
        this.boutiqueTypeList.handleMouseInput();
    }
	
	
	protected void actionPerformed(GuiButton button, int mouseButton) throws IOException {
        switch (button.id)
        {	
            case 50:
            	this.mc.displayGuiScreen(this.lastScreen);
            	break;
            	
            default:
                this.boutiqueTypeList.actionPerformed(button, mouseButton);
        }
	}

	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		this.drawBackgroundImage();
        this.boutiqueTypeList.drawScreen(mouseX, mouseY, partialTicks);
        this.drawTopList();

        String money = null;
        if(Client.getInstance().getFactionProfile() != null) {
            money = Client.getInstance().getFactionProfile().getMoney();
            if (money == null)
                money = "N/A";
        }else
            money = "N/A";
        String s = "\u00A76M\u00A7foney : " + FzUtils.conversMoney(money) + " Coins";
        this.fontRendererObj.drawString(s, this.width / 2 - this.fontRendererObj.getStringWidth(s) / 2, this.guiTop + this.ySize - 22, 16777215, true);
        this.fontRendererObj.drawString(this.type.getTypeName(), this.width / 2 - this.fontRendererObj.getStringWidth(this.type.getTypeName()) / 2, this.guiTop + 12, 16777215, true);
        
        super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
    {
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

	
	public void drawBackgroundImage()
	{
		GlStateManager.pushMatrix();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(background);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        GlStateManager.enableBlend();
        this.drawModalRectWithCustomSizedTexture(i, j, 0, 0, this.xSize, this.ySize, 512.0F, 512.0F);
        GlStateManager.popMatrix();
	}
	
	public void drawTopList()
	{
		GlStateManager.pushMatrix();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(background);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawModalRectWithCustomSizedTexture(i + 10, j, 10, 0, this.xSize - 20, 37, 512.0F, 512.0F);
        
        this.mc.getTextureManager().bindTexture(background);
        int i1 = (this.width - this.xSize) / 2;
        int j1 = (this.height - this.ySize) / 2;
        this.drawModalRectWithCustomSizedTexture(i1 + 20, j1 + 202, 20, 30, this.xSize - 40, 37, 512.0F, 512.0F);
		GlStateManager.popMatrix();
	}

}
