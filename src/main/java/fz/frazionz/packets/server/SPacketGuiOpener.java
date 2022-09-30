package fz.frazionz.packets.server;

import fz.frazionz.client.gui.GuiServerSwitcher;
import fz.frazionz.enums.EnumGui;
import net.minecraft.client.Minecraft;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.PacketThreadUtil;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.network.play.INetHandlerPlayServer;
import org.lwjgl.Sys;

import java.io.IOException;

public class SPacketGuiOpener implements Packet<INetHandlerPlayClient> {

	Minecraft mc = Minecraft.getMinecraft();
	private EnumGui gui;
	private int info;

	public SPacketGuiOpener() {
	}

	public SPacketGuiOpener(EnumGui gui) {
		this.gui = gui;
		this.info = 0;
	}
	public SPacketGuiOpener(EnumGui gui, int info) {
		this.gui = gui;
		this.info = info;
	}
	
	public void readPacketData(PacketBuffer buf) throws IOException {
		this.gui = buf.readEnumValue(EnumGui.class);
		this.info = buf.readInt();
	}

	public void processPacket(INetHandlerPlayClient handler) {
		PacketThreadUtil.checkThreadAndEnqueue(this, handler, this.mc);

		switch (this.gui) {
			case SERVER_SWITCHER:
				this.mc.displayGuiScreen(new GuiServerSwitcher(this.mc.currentScreen, this.mc));
				break;
			default:
				break;
		}
	}

	public void writePacketData(PacketBuffer buf) throws IOException {
	}
}
