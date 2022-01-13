package fz.frazionz.packets.client;

import java.io.IOException;
import java.util.UUID;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;

public class CPacketServerSwitch implements Packet<INetHandlerPlayServer> {

	private UUID uidP;
	private int serverID;
	
	public CPacketServerSwitch() {
	}
	
	public CPacketServerSwitch(UUID uidP, int serverID) {
		this.uidP = uidP;
		this.serverID = serverID;
	}
	
	public void writePacketData(PacketBuffer buf) throws IOException {
		buf.writeUuid(uidP);
		buf.writeInt(this.serverID);
	}

	public void readPacketData(PacketBuffer buf) throws IOException {
		this.uidP = buf.readUuid();
		this.serverID = buf.readInt();
	}

	public void processPacket(INetHandlerPlayServer handler) {}

}
