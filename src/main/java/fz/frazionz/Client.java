package fz.frazionz;

import java.util.HashMap;

import fz.frazionz.api.HTTPFunctions;
import fz.frazionz.api.data.FactionProfile;
import fz.frazionz.api.data.ShopAPIDataStocker;
import fz.frazionz.discord.DiscordRP;
import fz.frazionz.event.EventManager;
import fz.frazionz.gui.hud.HUDManager;
import fz.frazionz.mods.FileManager;
import fz.frazionz.mods.ModInstances;
import fz.frazionz.mods.blockrenderer.BlockRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class Client {

	private static final Client INSTANCE = new Client();
	public HashMap<Integer, TTFFontRenderer> ttfFontRenderers = new HashMap<>();
	
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
	
	public void postMinecraftInit() {
		this.ttfFontRenderers.put(24, new TTFFontRenderer(new ResourceLocation("font/font.ttf"), 24));
		this.ttfFontRenderers.put(20, new TTFFontRenderer(new ResourceLocation("font/font.ttf"), 20));
		this.ttfFontRenderers.put(16, new TTFFontRenderer(new ResourceLocation("font/font.ttf"), 16));
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

	public FactionProfile getFactionProfile() {
		return factionProfile;
	}
	
	public HashMap<Integer, TTFFontRenderer> getTTFFontRenderers() {
		return ttfFontRenderers;
	}
}
