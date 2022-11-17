package fz.frazionz.client.stats;

import fz.frazionz.utils.StringUtils;
import net.minecraft.util.text.translation.I18n;

public class StatCapModifier extends StatModifier {

    private StatCapType type;

    public StatCapModifier(StatCapType type, EnumStats stat, int value) {
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
        return StringUtils.capitalize(stat.prefix() + " " + type.name()) + " " + stat.name_tr() + " " + value;
    }
}
