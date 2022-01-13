package net.minecraft.network.play.server;

import java.io.IOException;

import javax.annotation.Nullable;

import net.minecraft.entity.Entity;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.world.World;

public class SPacketOpenGuiBoutique implements Packet<INetHandlerPlayClient>
{
	
    public int entityId;
    private int type;
    
    public SPacketOpenGuiBoutique()
    {
    }
	
	public SPacketOpenGuiBoutique(Entity entityIn, int typeIn) {
		
		this.entityId = entityIn.getEntityId();
        this.type = typeIn;
		
	}

    /**
     * Reads the raw packet data from the data stream.
     */
    public void readPacketData(PacketBuffer buf) throws IOException
    {
        this.entityId = buf.readVarIntFromBuffer();
        this.type = buf.readByte();
    }

    /**
     * Writes the raw packet data to the data stream.
     */
    public void writePacketData(PacketBuffer buf) throws IOException
    {
        buf.writeVarIntToBuffer(this.entityId);
        buf.writeByte(this.type);
    }

    /**
     * Passes this Packet on to the NetHandler for processing.
     */
    public void processPacket(INetHandlerPlayClient handler)
    {
        //handler.handleOpenGuiBoutique(this);
    }

    @Nullable
    public Entity getEntity(World worldIn)
    {
        return worldIn.getEntityByID(this.entityId);
    }
    
    public int getType()
    {
        return this.type;
    }


}
