package fz.frazionz.block;

import fz.frazionz.block.enums.ExplosiveType;
import fz.frazionz.block.interfaces.FzExplosionChance;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;

public class BlockCompactCobblestone extends Block implements FzExplosionChance
{

    public BlockCompactCobblestone()
    {
        super(Material.ROCK);
    }

    @Override
    public float getExplosionChance(ExplosiveType type) {
        if(this == Blocks.COMPACT_COBBLESTONE_X1 || this == Blocks.COMPACT_COBBLESTONE_X2) {
            switch (type) {
                case TNT:
                case Z_TNT:
                    return 1F;
                default:
                    return 0;
            }
        }
        if(this == Blocks.COMPACT_COBBLESTONE_X3) {
            switch (type) {
                case TNT:
                    return 0.2F;
                case Z_TNT:
                    return 1F;
                default:
                    return 0;
            }
        }
        if(this == Blocks.COMPACT_COBBLESTONE_X4) {
            switch (type) {
                case Z_TNT:
                    return 0.8F;
                default:
                    return 0;
            }
        }
        if(this == Blocks.COMPACT_COBBLESTONE_X5) {
            switch (type) {
                case Z_TNT:
                    return 0.15F;
                default:
                    return 0;
            }
        }
        return 0;
    }
}