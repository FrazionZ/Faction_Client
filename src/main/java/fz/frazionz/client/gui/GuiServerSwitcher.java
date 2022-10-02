package fz.frazionz.client.gui;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;

import fz.frazionz.FzClient;
import fz.frazionz.TTFFontRenderer;
import fz.frazionz.api.HTTPFunctions;
import fz.frazionz.packets.client.CPacketServerSwitch;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.resources.ResourceLocation;

public class GuiServerSwitcher extends GuiFrazionZInterface {

	private static final ResourceLocation SERVER_SWITCHER_RESOURCE = new ResourceLocation("textures/gui/frazionz/server_switcher.png");
	
	public GuiServerSwitcher(GuiScreen lastScreen, Minecraft mc) {
		super("Server Switcher", lastScreen, mc);
		this.hasBackButton = true;
	}
	
	public void initGui() {
		String ip = "185.157.246.85";

		SwitcherButton faction = new SwitcherButton(2, "Faction", (this.width / 2) - 103, (this.height / 2) - 59, 84, 141, 0, 0, this.fontRenderer, FzClient.getInstance().getTTFFontRenderers().get(24), 4, 1);
		SwitcherButton minage  = new SwitcherButton(3, "Minage", (this.width / 2) + 23, (this.height / 2) - 59, 84, 141, 0, 0, this.fontRenderer, FzClient.getInstance().getTTFFontRenderers().get(24), 3, 0);
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
			case 1:
				this.mc.displayGuiScreen(lastScreen);
				break;
	        case 2:
	        case 3:
	        	this.mc.player.connection.sendPacket(new CPacketServerSwitch(this.mc.player.getUniqueID(), button.id - 2));
	        	this.mc.displayGuiScreen(null);
	        	break;
        }
	}
	
	private class SwitcherButton extends GuiButton
	{
	    private final int textureX;
	    private final int textureY;
	    private final FontRenderer fontRenderer;
	    private final TTFFontRenderer ttfFontRenderer;
		private String serverCount;
		
		private int iconX;
		private int iconY;

	    public SwitcherButton(int buttonId, String displayString, int x, int y, int widthIn, int heightIn, int textureX, int textureY, FontRenderer fontRenderer, TTFFontRenderer tfFontRenderer, int iconX, int iconY)
	    {
	        super(buttonId, x, y, widthIn, heightIn, displayString);
	        this.width = widthIn;
	        this.height = heightIn;
	        this.textureX = textureX;
	        this.textureY = textureY;
			this.fontRenderer = fontRenderer;
			this.iconX = iconX;
			this.iconY = iconY;
			this.ttfFontRenderer = tfFontRenderer;
	    }

		public void setServerCount(String serverCount) {
			this.serverCount = serverCount;
		}

		public void drawButton(Minecraft mc, int mouseX, int mouseY, float p_191745_4_)
	    {
	        if (this.visible)
	        {	    
	            this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX <= this.x + this.width && mouseY <= this.y + this.height;
	        	mc.getTextureManager().bindTexture(SERVER_SWITCHER_RESOURCE);
	            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
	            int x = this.textureX;
	            int y = this.textureY;
	            
	            if (this.hovered) {
	            	x += 84;
	            }
	            
	            this.drawModalRectWithCustomSizedTexture(this.x, this.y, x, y, this.width, this.height, 512.0F, 512.0F);

				mc.getTextureManager().bindTexture(SERVER_SWITCHER_RESOURCE);
				GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
	            this.drawModalRectWithCustomSizedTexture(this.x + this.width/2 - 32, this.y + 15, iconX * 64, 141 + iconY * 64, 64, 64, 512.0F, 512.0F);
	            
	            String info = (this.serverCount == null ? "Recherche..." : serverCount + (Integer.parseInt(serverCount) > 1 ? " Joueurs" :" Joueur"));
				this.drawString(fontRenderer, info, this.x + this.width/2 - fontRenderer.getStringWidth(info)/2, this.y + this.height - 16, 0xFFFFFFFF);

	            ttfFontRenderer.drawCenteredString(this.displayString, this.x + this.width / 2, this.y + this.height/2 + 24, 0xFFFFFFFF);

	            this.mouseDragged(mc, mouseX, mouseY);
	        }
	    }
	}
	
}
