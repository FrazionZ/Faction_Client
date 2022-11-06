package fz.frazionz.item;

import java.util.Random;

import fz.frazionz.client.stats.EnumStats;
import fz.frazionz.utils.MathUtils;

public class ItemSkeletonTrophy extends ItemTrophy {
	
	public ItemSkeletonTrophy() {
		super();
	}
    
    public int getRandomStatModifier() {
    	Random rand = new Random();
    	float randFloat = rand.nextFloat();
    	if(randFloat <= 0.5)
    	{
    		return 1 + rand.nextInt(10);
    	}
    	else if(randFloat <= 0.80) 
    	{
    		return 11 + rand.nextInt(10);
    	}
    	else if(randFloat <= 0.95) 
    	{
    		return 21 + rand.nextInt(10);
    	}
    	else
    	{
    		return 31 + rand.nextInt(4);
    	}
    }
    
    public EnumStats getBaseStat()
    {
    	return EnumStats.SPEED;
    }
	
}
