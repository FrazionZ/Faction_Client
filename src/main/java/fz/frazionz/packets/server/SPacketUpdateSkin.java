package fz.frazionz.packets.server;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.text.ITextComponent;

import java.io.IOException;
import java.util.UUID;

public class SPacketUpdateSkin implements Packet<INetHandlerPlayClient> {

    private UUID uuid;

    public SPacketUpdateSkin() {}

    public SPacketUpdateSkin(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {
        this.uuid = buf.readUniqueId();
    }

    @Override
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeUniqueId(uuid);
    }

    @Override
    public void processPacket(INetHandlerPlayClient handler) {
        handler.handleUpdateSkin(this);
    }

    public UUID getUuid() {
        return uuid;
    }
}
