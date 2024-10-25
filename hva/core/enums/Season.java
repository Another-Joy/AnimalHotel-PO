package hva.core.enums;


public enum Season{
    SPRING,
    SUMMER,
    FALL,
    WINTER;

    static public final Season[] _seasons = values();

    public Season next() {
        return _seasons[(this.ordinal() + 1) % _seasons.length];
    }
}


