package fz.frazionz.client.stats;

import fz.frazionz.client.stats.modifiers.StatValueCappingModifier;
import fz.frazionz.client.stats.modifiers.StatModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class PlayerStats {

    private Map<EnumStats, SimpleStat> stats = new HashMap<>();
    private final EntityPlayer player;
    private Map<EnumStats, Integer> minCapping = new HashMap<>();
    private Map<EnumStats, Integer> maxCapping = new HashMap<>();

    public boolean hasFallDamage = true;
    public boolean hasFireDamage = true;
    public boolean hasDrowningDamage = true;
    public boolean hasExplosionDamage = true;

    public PlayerStats(EntityPlayer player) {
        this.player = player;

        for(EnumStats stat : EnumStats.values()) {
            stats.put(stat, new SimpleStat(stat));
        }
    }

    public void update() {
        resetStat();
        applyAllItemStat();
        applyFullArmorSet();
        applyStatCapping();
    }

    private void resetStat() {
        for(EnumStats stat : EnumStats.values()) {
            stats.get(stat).setValue(stat.getBaseValue());
            minCapping.put(stat, stat.getMinValue());
            maxCapping.put(stat, stat.getMaxValue());
        }
        hasFallDamage = true;
        hasFireDamage = true;
        hasDrowningDamage = true;
        hasExplosionDamage = true;
    }

    private void applyAllItemStat() {
        for(ItemStack stack : player.inventory.armorInventory) {
            updateStats(stack);
        }
        for(ItemStack stack : player.inventory.trophyInventory) {
            updateStats(stack);
        }
        updateStats(player.getHeldItemMainhand());
        updateStats(player.getHeldItemOffhand());
    }

    private void applyStatCapping() {
        for(SimpleStat stat : stats.values()) {
            if(stat.getValue() < minCapping.get(stat.getStat())) {
                stat.setValue(minCapping.get(stat.getStat()));
            }
            else if(stat.getValue() > maxCapping.get(stat.getStat())) {
                stat.setValue(maxCapping.get(stat.getStat()));
            }
        }
    }

    private void updateStats(ItemStack stack) {
        if(stack.isStatItem()) {
            for(EnumStats stat : EnumStats.values()) {
                if(StatHelper.hasStat(stack, stat)) {
                    stats.get(stat).setValue(stats.get(stat).getValue() + StatHelper.getStatValue(stack, stat));
                }
            }
        }
    }

    private void applyFullArmorSet() {
        if(player.isWearingFullArmorSet()) {
            ItemArmor.ArmorMaterial material = player.getFullArmorMaterial();
            for(Map.Entry<EnumStats, Integer> entry : material.getStats().entrySet()) {
                SimpleStat simpleStat = stats.get(entry.getKey());
                simpleStat.setValue(simpleStat.getValue() + entry.getValue());
            }
            if(!material.getModifiers().isEmpty()) {
                for(StatModifier modifier : material.getModifiers()) {
                    applyModifier(modifier);
                }
            }
        }
    }

    private void applyModifier(StatModifier modifier) {
        if(modifier instanceof StatValueCappingModifier) {
            StatValueCappingModifier cappingModifier = (StatValueCappingModifier) modifier;
            switch(cappingModifier.getType()) {
                case MIN:
                    if(minCapping.containsKey(cappingModifier.getStat()) && minCapping.get(cappingModifier.getStat()) > cappingModifier.getValue()) {
                        minCapping.put(cappingModifier.getStat(), cappingModifier.getValue());
                    }
                    break;
                case MAX:
                    if(maxCapping.containsKey(cappingModifier.getStat()) && maxCapping.get(cappingModifier.getStat()) < cappingModifier.getValue()) {
                        maxCapping.put(cappingModifier.getStat(), cappingModifier.getValue());
                    }
                    break;
            }
        }
        else {
            modifier.applyModifier(this);
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
