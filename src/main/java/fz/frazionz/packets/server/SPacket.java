package fz.frazionz.packets.server;

import java.io.IOException;

import fz.frazionz.packets.ECPacket;
import net.minecraft.network.PacketBuffer;

public abstract class SPacket extends ECPacket{

	
	@Override
	public void writePacketData(PacketBuffer buf) throws IOException {
		
		//Sent from the server, we don't write things, we only read
		
	}
	
}
