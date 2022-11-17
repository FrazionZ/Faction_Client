package fz.frazionz.client.stats;

import net.minecraft.util.text.translation.I18n;

public enum EnumStats {

    HEALTH(-100, 0, 100),
    REGENERATION(-50, 0, 50),
    SPEED(40, 100, 160),
    DAMAGE(50, 100, 125),
    RESISTANCE(50, 100, 125),
    MINING_SPEED(25, 100, 150),
    DUPLICATE_MINING_CHANCE(50, 100, 200),
    FARMING_SKILL_XP(50, 100, 200),
    MINING_SKILL_XP(50, 100, 200),
    COMBAT_SKILL_XP(50, 100, 200),
    PILLAGE_SKILL_XP(50, 100, 200);
    ;

    private int minValue;
    private int baseValue;
    private int maxValue;

    EnumStats(int minValue, int baseValue, int maxValue) {
        this.minValue = minValue;
        this.baseValue = baseValue;
        this.maxValue = maxValue;
    }

    public int getMinValue() {
        return minValue;
    }

    public int getBaseValue() {
        return baseValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public String toString() {
        return color() + prefix() + " " + name_tr() + suffix();
    }

    public String name_tr() {
        return I18n.translateToLocal("frazionz.stat." + name().toLowerCase() + ".name");
    }

    public String suffix() {
        return I18n.translateToLocal("frazionz.stat." + name().toLowerCase() + ".suffix");
    }

    public String prefix() {
        return I18n.translateToLocal("frazionz.stat." + name().toLowerCase() + ".prefix");
    }

    public String description() {
        return I18n.translateToLocal("frazionz.stat." + name().toLowerCase() + ".description");
    }

    public String color() {
        return I18n.translateToLocal("frazionz.stat." + name().toLowerCase() + ".color");
    }
}
