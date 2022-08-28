package fz.frazionz.tileentity;

import java.util.Random;

import fz.frazionz.tileentity.renderer.TickCounter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;

public class TileEntityGrimoirePedestal extends TileEntity implements ITickable, TickCounter
{
	private int tickCount;
    
    /**
     * Like the old updateEntity(), except more generic.
     */
    public void update()
    {  	
        ++this.tickCount;
    }
    
    @Override
    public int getTickCount() {
    	return tickCount;
    }
}
