package fz.frazionz.client.stats.modifiers;

import fz.frazionz.client.stats.PlayerStats;
import net.minecraft.util.text.translation.I18n;

public class StatFireDamageModifier extends StatModifier {

    private boolean hasFireDamage;

    public StatFireDamageModifier(boolean hasFireDamage) {
        this.hasFireDamage = hasFireDamage;
    }

    @Override
    public boolean applyModifier(PlayerStats stats) {
        stats.hasFireDamage = hasFireDamage;
        return true;
    }

    @Override
    public String toString() {
        return hasFireDamage ? I18n.translateToLocal("frazionz.stat.modifier.hasfiredamage.enable") : I18n.translateToLocal("frazionz.stat.modifier.hasfiredamage.disable");
    }
}
