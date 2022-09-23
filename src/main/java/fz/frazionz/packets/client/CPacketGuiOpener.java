package fz.frazionz.packets.client;

import java.io.IOException;

import fz.frazionz.enums.EnumGui;
import fz.frazionz.client.gui.GuiServerSwitcher;
import net.minecraft.client.Minecraft;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.PacketThreadUtil;
import net.minecraft.network.play.INetHandlerPlayServer;

public class CPacketGuiOpener implements Packet<INetHandlerPlayServer> {

	Minecraft mc = Minecraft.getMinecraft();
	private EnumGui gui;
	private int info;
	
	public CPacketGuiOpener() {
	}

	public CPacketGuiOpener(EnumGui gui) {
		this.gui = gui;
		this.info = 0;
	}
	public CPacketGuiOpener(EnumGui gui, int info) {
		this.gui = gui;
		this.info = info;
	}
	
	public void readPacketData(PacketBuffer buf) throws IOException {
	}

	public void processPacket(INetHandlerPlayServer handler) {
	}

	public void writePacketData(PacketBuffer buf) throws IOException {
		buf.writeEnumValue(this.gui);
		buf.writeInt(this.info);
	}
}
