package fz.frazionz.packets.client;

import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;

public class CPacketSkillClaimButton extends CPacketClickButtonTransmitter {
	
	private int skillType;
	private int level;
	private int rewardId;
	
	public CPacketSkillClaimButton() {
	}
	
	public CPacketSkillClaimButton(int skillType, int level, int rewardId) {
		super(Minecraft.getMinecraft().player.getUniqueID(), ButtonType.SKILL_CLAIM_REWARD);
		this.skillType = skillType;
		this.level = level;
		this.rewardId = rewardId;
	}
	
	@Override
	public void writePacketData(PacketBuffer buf) throws IOException {
		super.writePacketData(buf);
		buf.writeInt(this.skillType);
		buf.writeInt(this.level);
		buf.writeInt(this.rewardId);
	}
	
}
