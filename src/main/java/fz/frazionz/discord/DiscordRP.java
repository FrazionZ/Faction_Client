package fz.frazionz.discord;

import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;
import net.arikia.dev.drpc.DiscordUser;
import net.arikia.dev.drpc.callbacks.ReadyCallback;
import net.minecraft.client.Minecraft;

public class DiscordRP {
	
	private boolean running = true;
	private long created = 0;
	
	public void start() {
		
		this.created = System.currentTimeMillis();
		
		DiscordEventHandlers handlers = new DiscordEventHandlers.Builder().setReadyEventHandler(new ReadyCallback() {
			
			public void apply(DiscordUser user) {
				
				System.out.println("Welcome " + user.username + "#" + user.discriminator + ".");
				update("Booting up...", "");
				
				
			}
			
		}).build();
		
		DiscordRPC.discordInitialize("666379133546135572", handlers, true);
		
		new Thread("Discord RPC Callback") {
			
			@Override
			public void run() {
				
				while(running) {
					
					DiscordRPC.discordRunCallbacks();
					
				}
				
			}
			
		}.start();
		
	}
	
	public void shutdown() {
		
		running = false;
		
		DiscordRPC.discordShutdown();
		
	}
	
	public void update(String firstline, String secondline) {
		
		DiscordRichPresence.Builder b = new DiscordRichPresence.Builder(secondline);
		b.setBigImage("large", "FrazionZ");
		b.setSmallImage("large", Minecraft.getMinecraft().getSession().getUsername());
		
		b.setDetails(firstline);
		b.setStartTimestamps(created);
		
		DiscordRPC.discordUpdatePresence(b.build());
		
	}

}
