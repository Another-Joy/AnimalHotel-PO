package hva.core.enums;

public enum Influence {
    POS(20, "POS"),
    NEU(0, "NEU"),
    NEG(-20, "NEG");

    private int _value;
    private String _type;

    private Influence(int value, String type){
        _value = value;
        _type = type;
    }

    public int getInt(){
        return _value;
    }

    public String toString(){
        return _type;
    }
}
