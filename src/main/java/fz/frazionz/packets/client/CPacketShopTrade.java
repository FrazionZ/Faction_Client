package fz.frazionz.packets.client;

import java.io.IOException;
import java.util.UUID;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;

public class CPacketShopTrade implements Packet<INetHandlerPlayServer> {

	private int boutiqueTypeId;
	private UUID playerUUID;
	private int amount;
	private int tradeType;
	
	public CPacketShopTrade() {
	}
	
	public CPacketShopTrade(int boutiqueTypeId, UUID playerUUID, int amount, int tradeType) {
		this.boutiqueTypeId = boutiqueTypeId;
		this.playerUUID = playerUUID;
		this.amount = amount;
		this.tradeType = tradeType;
	}
	
	public void writePacketData(PacketBuffer buf) throws IOException {
		buf.writeInt(this.boutiqueTypeId);
		buf.writeUniqueId(this.playerUUID);
		buf.writeInt(this.amount);
		buf.writeInt(this.tradeType);
	}

	public void readPacketData(PacketBuffer buf) throws IOException {
		this.boutiqueTypeId = buf.readInt();
		this.playerUUID = buf.readUniqueId();
		this.amount = buf.readInt();
		this.tradeType = buf.readInt();
	}

	public void processPacket(INetHandlerPlayServer handler) {}

}
