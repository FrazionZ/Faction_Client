package fz.frazionz.client.stats.modifiers;

import fz.frazionz.client.stats.EnumStats;
import fz.frazionz.utils.StringUtils;

public class StatValueCappingModifier extends StatValueModifier {

    private StatCapType type;

    public StatValueCappingModifier(StatCapType type, EnumStats stat, int value) {
        super(stat, value);
        this.type = type;
    }

    public StatCapType getType() {
        return type;
    }
    public enum StatCapType {
        MAX,
        MIN
    }

    public String toString() {
        return stat.prefix() + " " + StringUtils.capitalize(type.name()) + " " + stat.name_tr() + " " + value;
    }
}
