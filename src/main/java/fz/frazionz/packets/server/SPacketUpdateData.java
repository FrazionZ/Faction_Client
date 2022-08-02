package fz.frazionz.packets.server;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;

import fz.frazionz.Client;
import fz.frazionz.api.data.FactionProfile;
import net.minecraft.client.Minecraft;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class SPacketUpdateData implements Packet<INetHandlerPlayClient>   {

	Minecraft mc = Minecraft.getMinecraft();
	private String key;
	private String valueType;
	private String table;
	private Object value;
	
    public SPacketUpdateData() {}

    public SPacketUpdateData(String key, String valueType, String table, Object value) {
    	this.key = key;
    	this.valueType = valueType;
    	this.table = table;
    	this.value = value;
    }
	
	public void readPacketData(PacketBuffer buf) throws IOException {
		this.key = buf.readString(32);
		this.valueType = buf.readString(32);
		this.table = buf.readString(32);
		
		switch(this.valueType) {
			case "boolean":
				this.value = buf.readBoolean();
				break;
			case "String":
				this.value = buf.readString(256);
				break;
			case "double":
				this.value = buf.readDouble();
				break;
			case "float":
				this.value = buf.readFloat();
				break;
		}
	}

	public void processPacket(INetHandlerPlayClient handler) {
		switch(this.table) {
			case "FactionProfile":
				try {
					FactionProfile profile = Client.getInstance().getFactionProfile();
					if(profile == null)
						return;
					Method method = profile.getClass().getDeclaredMethod("set" + fz.frazionz.utils.StringUtils.capitalize(key), Object.class);
					if(method == null)
						return;
					method.invoke(profile, this.value);
				} catch (IllegalAccessException|InvocationTargetException|IllegalArgumentException|NoSuchMethodException|SecurityException e) {
					LogManager.getLogger().warn(e.getMessage());
				}
			break;
		}
	}

	@Override
	public void writePacketData(PacketBuffer buf) throws IOException {
	}
}
