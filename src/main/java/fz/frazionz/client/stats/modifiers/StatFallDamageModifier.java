package fz.frazionz.client.stats.modifiers;

import fz.frazionz.client.stats.PlayerStats;
import net.minecraft.util.text.translation.I18n;

public class StatFallDamageModifier extends StatModifier {

    private boolean hasFallDamage;

    public StatFallDamageModifier(boolean hasFallDamage) {
        this.hasFallDamage = hasFallDamage;
    }

    @Override
    public boolean applyModifier(PlayerStats stats) {
        stats.hasFallDamage = hasFallDamage;
        return true;
    }

    @Override
    public String toString() {
        return hasFallDamage ? I18n.translateToLocal("frazionz.stat.modifier.hasFallDamage.enable") : I18n.translateToLocal("frazionz.stat.modifier.hasFallDamage.disable");
    }
}
