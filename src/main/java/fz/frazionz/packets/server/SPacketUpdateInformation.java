package fz.frazionz.packets.server;

import java.io.IOException;
import java.util.concurrent.Executors;

import fz.frazionz.api.HTTPFunctions;
import fz.frazionz.api.data.ShopAPIDataStocker;
import fz.frazionz.data.FzUserData;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class SPacketUpdateInformation extends SPacket {

	private int information;
	
	public SPacketUpdateInformation() {
	}
	
	public SPacketUpdateInformation(int information) {
		this.information = information;
	}
	
	@Override
	public void readPacketData(PacketBuffer buf) throws IOException {
		this.information = buf.readInt();
	}

	@Override
	public void processPacket(INetHandlerPlayClient handler)
	{
        Executors.newCachedThreadPool().execute(new Runnable() {
			@Override
			public void run() {
				switch (information)
				{
					case 0:
						ShopAPIDataStocker.loadAPIData();
						break;
				}
			}
        });
	}
}
