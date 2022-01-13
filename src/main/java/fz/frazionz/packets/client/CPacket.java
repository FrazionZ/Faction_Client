package fz.frazionz.packets.client;

import java.io.IOException;

import fz.frazionz.packets.ECPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public abstract class CPacket extends ECPacket {

	@Override
	public void readPacketData(PacketBuffer buf) throws IOException {
		
		//Sending from client, we don't need to read
		
	}
	
	@Override
	public void processPacket(INetHandlerPlayClient handler) {
		
		//Sending from client, we don't need to process
		
	}
	
}
