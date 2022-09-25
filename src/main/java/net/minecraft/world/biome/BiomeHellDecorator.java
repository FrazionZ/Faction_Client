package net.minecraft.world.biome;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class BiomeHellDecorator extends BiomeDecorator
{
	protected BlockPos chunkPos;

    @Override
    public void decorate(World worldIn, Random random, Biome biome, BlockPos pos)
    {
    	this.chunkPos = pos;
    	
        if (this.decorating)
        {
            throw new RuntimeException("Already decorating");
        }
        else
        {
            this.genDecorations(biome, worldIn, random);
            this.decorating = false;
        }
    }
    
    protected void genDecorations(Biome biomeIn, World worldIn, Random random)
    {
        int k1 = this.treesPerChunk;

        if (random.nextFloat() < this.extraTreeChance)
        {
            ++k1;
        }

        for (int j2 = 0; j2 < k1; ++j2)
        {
            WorldGenAbstractTree worldgenabstracttree = biomeIn.getRandomTreeFeature(random);
            worldgenabstracttree.setDecorationDefaults();
            BlockPos blockpos;
            int tryCount = 0;
            do {
                int x = random.nextInt(16) + 8;
                int z = random.nextInt(16) + 8;
                blockpos = this.chunkPos.add(x, random.nextInt(120) + 4, z);
            } while(!worldgenabstracttree.generate(worldIn, random, blockpos) && tryCount++ < 20);
        }
    }
}
