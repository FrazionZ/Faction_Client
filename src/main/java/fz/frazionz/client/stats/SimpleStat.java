package fz.frazionz.client.stats;

public class SimpleStat {

    private EnumStats stat;
    private int value;

    public SimpleStat(EnumStats stat) {
        this.stat = stat;
        this.value = stat.getBaseValue();
    }
    public SimpleStat(EnumStats stat, int value) {
        this.stat = stat;
        this.value = value;
    }

    public EnumStats getStat() {
        return stat;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
