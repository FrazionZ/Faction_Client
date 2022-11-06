package fz.frazionz.item;

import fz.frazionz.client.stats.EnumStats;

public class ItemShulkerTrophy extends ItemTrophy {
	
	public ItemShulkerTrophy() {
		super();
	}

	@Override
	public int getRandomStatModifier() {
		return 0;
	}

	public EnumStats getBaseStat()
    {
    	return EnumStats.RESISTANCE;
    }
	
}
