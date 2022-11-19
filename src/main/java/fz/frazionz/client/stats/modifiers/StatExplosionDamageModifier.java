package fz.frazionz.client.stats.modifiers;

import fz.frazionz.client.stats.PlayerStats;
import net.minecraft.util.text.translation.I18n;

public class StatExplosionDamageModifier extends StatModifier {

    private boolean hasExplosionDamage;

    public StatExplosionDamageModifier(boolean hasExplosionDamage) {
        this.hasExplosionDamage = hasExplosionDamage;
    }

    @Override
    public boolean applyModifier(PlayerStats stats) {
        stats.hasExplosionDamage = hasExplosionDamage;
        return true;
    }

    @Override
    public String toString() {
        return hasExplosionDamage ? I18n.translateToLocal("frazionz.stat.modifier.hasExplosionDamage.enable") : I18n.translateToLocal("frazionz.stat.modifier.hasExplosionDamage.disable");
    }
}
