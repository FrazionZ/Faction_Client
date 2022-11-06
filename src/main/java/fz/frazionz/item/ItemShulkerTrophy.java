package fz.frazionz.item;

import fz.frazionz.client.stats.EnumStats;

import java.util.Random;

public class ItemShulkerTrophy extends ItemTrophy {
	
	public ItemShulkerTrophy() {
		super();
	}

	@Override
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
    	return EnumStats.RESISTANCE;
    }
	
}
