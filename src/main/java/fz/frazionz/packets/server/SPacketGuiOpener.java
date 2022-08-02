package fz.frazionz.packets.server;

import java.io.IOException;

import fz.frazionz.gui.GuiServerSwitcher;
import fz.frazionz.gui.shop.GuiBoutiqueCategory;
import fz.frazionz.gui.shop.GuiShopCategory;
import fz.frazionz.gui.skills.GuiSkillList;
import net.minecraft.client.Minecraft;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.PacketThreadUtil;
import net.minecraft.network.play.INetHandlerPlayClient;

public class SPacketGuiOpener implements Packet<INetHandlerPlayClient> {

	Minecraft mc = Minecraft.getMinecraft();
	private int id;
	
	public SPacketGuiOpener() {
	}
	
	public SPacketGuiOpener(int id) {
		this.id = id;
	}
	
	public void readPacketData(PacketBuffer buf) throws IOException {
		this.id = buf.readInt();
	}

	public void processPacket(INetHandlerPlayClient handler) {
		PacketThreadUtil.checkThreadAndEnqueue(this, handler, this.mc);
		
		switch(this.id) {
			case 0:
				this.mc.displayGuiScreen(new GuiServerSwitcher(this.mc.currentScreen, this.mc));
				break;
			case 1:
				this.mc.displayGuiScreen(new GuiShopCategory(this.mc.currentScreen, this.mc));
				break;
			case 2:
				this.mc.displayGuiScreen(new GuiBoutiqueCategory(this.mc.currentScreen, this.mc));
				break;
			case 3:
				this.mc.displayGuiScreen(new GuiSkillList(this.mc.currentScreen, this.mc));
				break;
		}
	}

	public void writePacketData(PacketBuffer buf) throws IOException {
		buf.writeInt(this.id);
	}
}
