package fz.frazionz.packets.client;

import java.io.IOException;
import java.util.UUID;

import fz.frazionz.enums.EnumGui;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;

public class CPacketShopTrade implements Packet<INetHandlerPlayServer> {

	private EnumGui gui;
	private int id;
	private int amount;
	private int tradeType;
	
	public CPacketShopTrade() {
	}
	
	public CPacketShopTrade(EnumGui gui, int id, int amount, int tradeType) {
		this.gui = gui;
		this.id = id;
		this.amount = amount;
		this.tradeType = tradeType;
	}
	
	public void writePacketData(PacketBuffer buf) throws IOException {
		buf.writeEnumValue(this.gui);
		buf.writeInt(this.id);
		buf.writeInt(this.amount);
		buf.writeInt(this.tradeType);
	}

	public void readPacketData(PacketBuffer buf) throws IOException {
		this.gui = buf.readEnumValue(EnumGui.class);
		this.id = buf.readInt();
		this.amount = buf.readInt();
		this.tradeType = buf.readInt();
	}

	public void processPacket(INetHandlerPlayServer handler) {}

}
