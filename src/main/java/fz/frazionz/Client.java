package fz.frazionz;

import fz.frazionz.api.data.PlayerDataStocker;
import fz.frazionz.api.data.ShopAPIDataStocker;
import fz.frazionz.api.data.SuccessAPIDataStocker;
import fz.frazionz.discord.DiscordRP;
import fz.frazionz.event.EventManager;
import fz.frazionz.event.EventTarget;
import fz.frazionz.event.impl.ClientTickEvent;
import fz.frazionz.gui.hud.HUDManager;
import fz.frazionz.mods.FileManager;
import fz.frazionz.mods.ModInstances;
import net.minecraft.client.Minecraft;

public class Client {

	private static final Client INSTANCE = new Client();


	public static final Client getInstance() {
		return INSTANCE;
	}
	
	private DiscordRP discordRP = new DiscordRP();
	
	private HUDManager hudManager;
	
	public void init() {
		FileManager.init();
		if(Minecraft.getMinecraft().getSession().getDiscordRPC())
			discordRP.start();
		
		EventManager.register(this);

		ShopAPIDataStocker.loadAPIData();
		SuccessAPIDataStocker.loadAPIData();
		PlayerDataStocker.loadProfilItems();

	}
	
	
	public void start() {
		hudManager = HUDManager.getInstance();
		ModInstances.register(hudManager);
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

}
