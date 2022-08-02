package fz.frazionz.packets.server;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import net.minecraft.client.Minecraft;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class SPacketSkillUpdater implements Packet<INetHandlerPlayClient>   {

	Minecraft mc = Minecraft.getMinecraft();
	private SkillUpdaterCategory category;
	private int skillId;
	private Object skillInfoUpdate;
	
    public SPacketSkillUpdater() {}

    public SPacketSkillUpdater(SkillUpdaterCategory category, int skillId, Object skillInfoUpdate) {
    	this.category = category;
    	this.skillId = skillId;
    	this.skillInfoUpdate = skillInfoUpdate;
    }
	
	public void readPacketData(PacketBuffer buf) throws IOException {
		int category = buf.readInt();
		this.skillId = buf.readInt();
		
		this.category = SkillUpdaterCategory.valueOf(category);
		
		switch(this.category) {
			case UNKNOWN:
				this.skillInfoUpdate = null;
				break;
			case WIN_EXP:
			case LEVEL_UPDATE:
				this.skillInfoUpdate = buf.readInt();
				break;
		}
	}

	public void processPacket(INetHandlerPlayClient handler) {
		
		System.out.println("Receive Packet: " + this.category + " - " + this.skillId + " - " + this.skillInfoUpdate);
		
	}

	@Override
	public void writePacketData(PacketBuffer buf) throws IOException {
		buf.writeInt(this.category.id);
		buf.writeInt(this.skillId);
		switch(this.category) {
			case WIN_EXP:
			case LEVEL_UPDATE:
				buf.writeInt((int) this.skillInfoUpdate);
				break;
			default:
				break;
		}
	}
	
	public enum SkillUpdaterCategory {
		
		UNKNOWN(0),
		WIN_EXP(1),
		LEVEL_UPDATE(2),
		;
		
		private int id;
		
		SkillUpdaterCategory(int id) {
			this.id = id;
		}
		
		public int getId() {
			return id;
		}
		
		public static SkillUpdaterCategory valueOf(int id) {
			return Arrays.stream(values()).filter(category -> category.id == id).findFirst().orElse(UNKNOWN);
		}
	}
	
}
