package fz.frazionz.item.trophies;

import fz.frazionz.client.stats.EnumStats;

import java.util.Random;

public class ItemIronGolemTrophy extends ItemTrophy {

	public ItemIronGolemTrophy() {
		super();
	}

	@Override
	public int getRandomStatModifier() {
		Random rand = new Random();
		float randFloat = rand.nextFloat();
		if(randFloat <= 0.5)
		{
			return 1 + rand.nextInt(3);
		}
		else if(randFloat <= 0.80)
		{
			return 4 + rand.nextInt(3);
		}
		else if(randFloat <= 0.95)
		{
			return 8 + rand.nextInt(2);
		}
		else
		{
			return 11 + rand.nextInt(4);
		}
	}

	public EnumStats getBaseStat()
    {
    	return EnumStats.HEALTH;
    }
}
