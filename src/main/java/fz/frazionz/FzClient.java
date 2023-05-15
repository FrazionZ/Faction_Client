package fz.frazionz;

import java.util.HashMap;

import fz.frazionz.api.HTTPFunctions;
import fz.frazionz.api.data.FactionProfile;
import fz.frazionz.client.cache.CacheManager;
import fz.frazionz.event.EventManager;
import fz.frazionz.mods.mod_hud.HUDManager;
import fz.frazionz.mods.ModManager;
import fz.frazionz.mods.blockrenderer.BlockRenderer;
import fz.frazionz.utils.discord.DiscordRP;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;

public class FzClient {

	private static final FzClient INSTANCE = new FzClient();
	public HashMap<Integer, TTFFontRenderer> ttfFontRenderers = new HashMap<>();

	private DiscordRP discordRP = new DiscordRP();
	private HUDManager hudManager;
	private ModManager modManager;
	private FactionProfile factionProfile;
	private CacheManager cacheManager;

	public static final FzClient getInstance() {
		return INSTANCE;
	}
	
	public void init() {
		preInit();
		if(Minecraft.getMinecraft().getSession().getDiscordRPC())
			discordRP.start();
		
		EventManager.register(this);

		this.factionProfile = HTTPFunctions.getFactionProfile();
	}

	public void preInit() {

	}
	
	public void postMinecraftInit() {
		this.ttfFontRenderers.put(32, new TTFFontRenderer(new ResourceLocation("font/font.ttf"), 32));
		this.ttfFontRenderers.put(28, new TTFFontRenderer(new ResourceLocation("font/font.ttf"), 28));
		this.ttfFontRenderers.put(24, new TTFFontRenderer(new ResourceLocation("font/font.ttf"), 24));
		this.ttfFontRenderers.put(20, new TTFFontRenderer(new ResourceLocation("font/font.ttf"), 20));
		this.ttfFontRenderers.put(16, new TTFFontRenderer(new ResourceLocation("font/font.ttf"), 16));
	}
	
	
	public void onEnable() {
		hudManager = HUDManager.getInstance();
		modManager = ModManager.getInstance();
		modManager.onEnable();

		new BlockRenderer();
		cacheManager = new CacheManager();
	}
	
	public void onDisable() {
		discordRP.shutdown();
		modManager.onDisable();
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

	public CacheManager getCacheManager() {
		return cacheManager;
	}
}
