package fz.frazionz.block;

import java.util.Random;

import fz.frazionz.block.enums.ExplosiveType;
import fz.frazionz.block.interfaces.FzExplosionChance;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockRenforcedSand extends BlockFalling implements FzExplosionChance
{

    /**
     * Get the MapColor for this Block and the given BlockState
     */
    public MapColor getMapColor(IBlockState state, IBlockAccess p_180659_2_, BlockPos p_180659_3_)
    {
        return MapColor.SAND;
    }

    public int getDustColor(IBlockState p_189876_1_)
    {
        return -2370656;
    }

    @Override
    public float getExplosionChance(ExplosiveType type) {
        switch (type) {
            case TNT:
                return 0.3333F;
            case Z_TNT:
                return 1F;
            default:
                return 0;
        }
    }
}
