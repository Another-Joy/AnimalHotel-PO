package hva.core.enums;


public enum Season{
    SPRING,
    SUMMER,
    FALL,
    WINTER;

    static public final Season[] _seasons = values();

    public Season prev() {
        return _seasons[(ordinal() - 1  + _seasons.length) % _seasons.length];
    }

    public Season next() {
        return _seasons[(ordinal() + 1) % _seasons.length];
    }
}
}

