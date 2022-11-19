package fz.frazionz.client.stats.modifiers;

import fz.frazionz.client.stats.PlayerStats;
import net.minecraft.util.text.translation.I18n;

public class StatDrowningDamageModifier extends StatModifier {

    private boolean hasDrowningDamage;

    public StatDrowningDamageModifier(boolean hasDrowningDamage) {
        this.hasDrowningDamage = hasDrowningDamage;
    }

    @Override
    public boolean applyModifier(PlayerStats stats) {
        stats.hasDrowningDamage = hasDrowningDamage;
        return true;
    }

    @Override
    public String toString() {
        return hasDrowningDamage ? I18n.translateToLocal("frazionz.stat.modifier.hasDrowningDamage.enable") : I18n.translateToLocal("frazionz.stat.modifier.hasDrowningDamage.disable");
    }
}
