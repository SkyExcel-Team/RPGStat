package git.skyexcel.me.data.stat;

public interface Stat {

    public Stat addModifier(StatType stat);
    public void setStat(double value);
}
