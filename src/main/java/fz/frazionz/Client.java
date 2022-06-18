package fz.frazionz;

import fz.frazionz.api.HTTPFunctions;
import fz.frazionz.api.data.FactionProfile;
import fz.frazionz.api.data.ShopAPIDataStocker;
import fz.frazionz.discord.DiscordRP;
import fz.frazionz.event.EventManager;
import fz.frazionz.event.EventTarget;
import fz.frazionz.event.impl.ClientTickEvent;
import fz.frazionz.gui.hud.HUDManager;
import fz.frazionz.mods.FileManager;
import fz.frazionz.mods.ModInstances;
import fz.frazionz.mods.blockrenderer.BlockRenderer;
import net.minecraft.client.Minecraft;

public class Client {

	private static final Client INSTANCE = new Client();


	public static final Client getInstance() {
		return INSTANCE;
	}
	
	private DiscordRP discordRP = new DiscordRP();
	
	private HUDManager hudManager;

	private FactionProfile factionProfile;
	
	public void init() {
		FileManager.init();
		if(Minecraft.getMinecraft().getSession().getDiscordRPC())
			discordRP.start();
		
		EventManager.register(this);

		ShopAPIDataStocker.loadAPIData();
		//SuccessAPIDataStocker.loadAPIData();
		this.factionProfile = HTTPFunctions.getFactionProfile();
	}
	
	
	public void start() {
		hudManager = HUDManager.getInstance();
		ModInstances.register(hudManager);
		new BlockRenderer();
	}
	
	public void shutdown() {
		discordRP.shutdown();
		
	}
	
	public DiscordRP getDiscordRP() {
		return discordRP;
	}
	
	@EventTarget
	public void onTick(ClientTickEvent e) {
		if(Minecraft.getMinecraft().gameSettings.CLIENT_GUI_MOD_POS.isPressed()) {	
			hudManager.openConfigScreen();			
		}
	}

	public FactionProfile getFactionProfile() {
		return factionProfile;
	}
}
