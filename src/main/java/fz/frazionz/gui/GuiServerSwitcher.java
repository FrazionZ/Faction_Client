package fz.frazionz.gui;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;

import fz.frazionz.api.HTTPFunctions;
import fz.frazionz.packets.client.CPacketServerSwitch;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class GuiServerSwitcher extends GuiScreen {

	private static final ResourceLocation SERVER_SWITCHER_RESOURCE = new ResourceLocation("textures/gui/frazionz/server_switcher/server_switcher.png");
	
	private final GuiScreen lastScreen;
	private Minecraft mc;
	
	private final int xSize = 388;
	private final int ySize = 210;
	private int guiLeft;
	private int guiTop;
	private String factionCountPlayer = "-";
	private String minageCountPlayer = "-";

	public GuiServerSwitcher(GuiScreen lastScreen, Minecraft mc) {
		this.lastScreen = lastScreen;
		this.mc = mc;
	}
	
	public void initGui() {
		guiLeft = (this.width - this.xSize) / 2;
		guiTop = (this.height - this.ySize) / 2;

		String ip = "185.157.246.85";

		SwitcherButton faction = new SwitcherButton(0, (this.width / 2) - 103, (this.height / 2) - 59, 85, 142, 0, 352, this.SERVER_SWITCHER_RESOURCE, factionCountPlayer, this.fontRendererObj);
		SwitcherButton minage  = new SwitcherButton(1, (this.width / 2) + 23, (this.height / 2) - 59, 85, 142, 0, 210, this.SERVER_SWITCHER_RESOURCE, minageCountPlayer, this.fontRendererObj);
		this.buttonList.add(faction);
		this.buttonList.add(minage);

		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				Executors.newCachedThreadPool().submit(new Runnable() {
					@Override
					public void run() {
						faction.setServerCount(HTTPFunctions.getServerData(ip, "25566"));
						minage.setServerCount(HTTPFunctions.getServerData(ip, "25565"));
					}
				});
			}

		}, 0, 2500);

		super.initGui();
	}
	
	protected void actionPerformed(GuiButton button, int mouseButton) throws IOException {
        switch (button.id)
        {
	        case 0:
	        case 1:
	        	this.mc.player.connection.sendPacket(new CPacketServerSwitch(this.mc.player.getUniqueID(), button.id));
	        	this.mc.displayGuiScreen(null);
	        	break;
        }
	}
	
    public void updateScreen()
    {
        super.updateScreen();
    }
	
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawGradientRect(0, 0, this.width, this.height, -1072689136, -804253680);
		this.drawBackgroundImage();
        super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
    {
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

	
	public void drawBackgroundImage() {
		
		GlStateManager.pushMatrix();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(SERVER_SWITCHER_RESOURCE);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        GlStateManager.enableBlend();
        this.drawModalRectWithCustomSizedTexture(i, j, 0, 0, this.xSize, this.ySize, 512.0F, 512.0F);
        GlStateManager.popMatrix();
		
	}
	
	
	private class SwitcherButton extends GuiButton
	{
	    private final ResourceLocation resourceLocation;
	    private final int textureX;
	    private final int textureY;
	    private final FontRenderer fontRenderer;
		private String serverCount;

	    public SwitcherButton(int buttonId, int x, int y, int widthIn, int heightIn, int textureX, int textureY, ResourceLocation resourceLocation, String serverCount, FontRenderer fontRenderer)
	    {
	        super(buttonId, x, y, widthIn, heightIn, "");
	        this.width = widthIn;
	        this.height = heightIn;
	        this.textureX = textureX;
	        this.textureY = textureY;
	        this.resourceLocation = resourceLocation;
			this.fontRenderer = fontRenderer;
			this.serverCount = serverCount;
	    }

		public void setServerCount(String serverCount) {
			this.serverCount = serverCount;
		}

		public void func_191745_a(Minecraft mc, int mouseX, int mouseY, float p_191745_4_)
	    {
	        if (this.visible)
	        {	    
	            this.hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
	        	mc.getTextureManager().bindTexture(this.resourceLocation);
	            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
	            GlStateManager.enableBlend();
	            GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
	            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
	            
	            int i = this.textureX;
	            int j = this.textureY;
	            
	            if (this.hovered) {
	            	i += 85;
	            }
	            
	            this.drawModalRectWithCustomSizedTexture(this.xPosition, this.yPosition, i, j, this.width, this.height,  512.0F, 512.0F);
				this.drawString(fontRenderer, ((serverCount.equalsIgnoreCase("-")) ? "Recherche.." : serverCount+" Joueur(s)"), this.xPosition+12, this.yPosition + this.height - 17, -1);


	            this.mouseDragged(mc, mouseX, mouseY);
	        }
	    }
	}
	
}
