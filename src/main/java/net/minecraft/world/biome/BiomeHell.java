package net.minecraft.world.biome;

import java.util.Random;

import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class BiomeHell extends Biome
{
    public BiomeHell(Biome.BiomeProperties properties)
    {
        super(properties);
        this.decorator.treesPerChunk = 12;
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityGhast.class, 50, 4, 4));
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityPigZombie.class, 100, 4, 4));
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityMagmaCube.class, 2, 4, 4));
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityEnderman.class, 1, 4, 4));
        this.decorator = new BiomeHellDecorator();
    }
    
    public WorldGenAbstractTree genBigTreeChance(Random rand)
    {
        return (WorldGenAbstractTree)(rand.nextInt(3) == 0 ? BIG_TREE_FEATURE : TREE_FEATURE);
    }
    
    public void netherDecorate(World worldIn, Random rand, BlockPos pos)
    {
        super.netherDecorate(worldIn, rand, pos);
    }
}
