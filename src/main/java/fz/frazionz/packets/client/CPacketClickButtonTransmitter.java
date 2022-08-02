package fz.frazionz.packets.client;

import java.io.IOException;
import java.util.UUID;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;

public abstract class CPacketClickButtonTransmitter implements Packet<INetHandlerPlayServer> {
	
	protected UUID playerUUID;
	protected ButtonType buttonType;
	
	public CPacketClickButtonTransmitter() {
	}
	
	public CPacketClickButtonTransmitter(UUID playerUUID, ButtonType buttonType) {
		this.playerUUID = playerUUID;
		this.buttonType = buttonType;
	}
	
	public void writePacketData(PacketBuffer buf) throws IOException {
		buf.writeUniqueId(playerUUID);
		buf.writeString(buttonType.name());
	}

	public void readPacketData(PacketBuffer buf) throws IOException {
		this.playerUUID = buf.readUniqueId();
		this.buttonType = buf.readEnumValue(ButtonType.class);
	}

	public void processPacket(INetHandlerPlayServer handler) {}
	
	public enum ButtonType {
		
		SKILL_CLAIM_REWARD,
		
	}
}
