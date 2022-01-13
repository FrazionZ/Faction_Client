package fz.frazionz.packets.server;

import java.io.IOException;

import fz.frazionz.data.FzUserData;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class SPacketUserDataString extends SPacket {

	Minecraft mc = Minecraft.getMinecraft();
	private String key;
	private String value;
	
	@Override
	public void readPacketData(PacketBuffer buf) throws IOException {
		this.key = buf.readStringFromBuffer(32);
		this.value = buf.readStringFromBuffer(256);
	}

	@Override
	public void processPacket(INetHandlerPlayClient handler) {
		FzUserData.EnumUserData.valueOf(this.key).setValue(this.value);
	}
	
}
