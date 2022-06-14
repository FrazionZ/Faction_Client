package fz.frazionz.packets.server;

import java.io.IOException;

import fz.frazionz.data.FzUserData;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class SPacketUserDataDouble extends SPacket {

	Minecraft mc = Minecraft.getMinecraft();
	private String key;
	private double value;
	
	@Override
	public void readPacketData(PacketBuffer buf) throws IOException {
		this.key = buf.readString(32);
		this.value = buf.readDouble();
	}

	@Override
	public void processPacket(INetHandlerPlayClient handler) {
		FzUserData.EnumUserData.valueOf(this.key).setValue(this.value);
	}
	
}
