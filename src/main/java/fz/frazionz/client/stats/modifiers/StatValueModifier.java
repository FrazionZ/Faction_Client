package fz.frazionz.client.stats.modifiers;

import fz.frazionz.client.stats.EnumStats;
import net.minecraft.util.text.translation.I18n;

public class StatValueModifier extends StatModifier {

    protected EnumStats stat;
    protected int value;

    public StatValueModifier(EnumStats stat, int value) {
        this.stat = stat;
        this.value = value;
    }

    public EnumStats getStat() {
        return stat;
    }

    public int getValue() {
        return value;
    }

    public String toString() {
        return I18n.translateToLocal("frazionz.stat." + stat.name().toLowerCase() + ".name") + ": " + value;
    }

}
