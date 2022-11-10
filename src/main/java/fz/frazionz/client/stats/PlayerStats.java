package fz.frazionz.client.stats;

import fz.frazionz.item.interfaces.IStatItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.HashMap;
import java.util.Map;

public class PlayerStats {

    private Map<EnumStats, SimpleStat> stats = new HashMap<>();
    private final EntityPlayer player;

    public PlayerStats(EntityPlayer player) {
        this.player = player;

        for(EnumStats stat : EnumStats.values()) {
            stats.put(stat, new SimpleStat(stat));
        }
    }

    public void update() {
        for(EnumStats stat : EnumStats.values()) {
            stats.get(stat).setValue(stat.getBaseValue());
        }
        for(ItemStack stack : player.inventory.armorInventory) {
            updateStats(stack);
        }
        for(ItemStack stack : player.inventory.trophyInventory) {
            updateStats(stack);
        }
        updateStats(player.getHeldItemMainhand());
        updateStats(player.getHeldItemOffhand());

        for(SimpleStat stat : stats.values()) {
            if(stat.getValue() < stat.getStat().getMinValue()) {
                stat.setValue(stat.getStat().getMinValue());
            }
            else if(stat.getValue() > stat.getStat().getMaxValue()) {
                stat.setValue(stat.getStat().getMaxValue());
            }
        }
    }

    private void updateStats(ItemStack stack) {
        if(stack.getItem() instanceof IStatItem) {
            NBTTagCompound tag = stack.getTagCompound();
            if(tag != null) {
                for(EnumStats stat : EnumStats.values()) {
                    SimpleStat simpleStat = stats.get(stat);
                    if(tag.hasKey(stat.name())) {
                        simpleStat.setValue(simpleStat.getValue() + tag.getInteger(stat.name()));
                    }
                }
            }
        }
    }

    public int getStat(EnumStats stat) {
        return stats.get(stat).getValue();
    }

    public void setStat(EnumStats stat, int value) {
        stats.get(stat).setValue(value);
    }

    public Map<EnumStats, SimpleStat> getStatsMap() {
        return stats;
    }
}
