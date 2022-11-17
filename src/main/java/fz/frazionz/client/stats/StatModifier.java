package fz.frazionz.client.stats;

import net.minecraft.util.text.translation.I18n;

public class StatModifier {

    protected EnumStats stat;
    protected int value;

    public StatModifier(EnumStats stat, int value) {
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
