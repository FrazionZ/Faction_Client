package net.minecraft.world.biome;

import fz.frazionz.world.gen.feature.WorldGenNetherTrees;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import java.util.Random;

public class BiomeHellForest extends Biome
{
    protected final static WorldGenNetherTrees NETHER_TREES = new WorldGenNetherTrees(false);

    public BiomeHellForest(BiomeProperties properties)
    {
        super(properties);
        this.decorator = new BiomeHellDecorator();
        this.decorator.treesPerChunk = 12;
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableMonsterList.add(new SpawnListEntry(EntityPigZombie.class, 100, 4, 4));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityEnderman.class, 10, 4, 4));
    }

    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
        return NETHER_TREES;
    }
}
