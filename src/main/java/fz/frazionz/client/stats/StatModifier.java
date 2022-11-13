package fz.frazionz.client.stats;

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
}
