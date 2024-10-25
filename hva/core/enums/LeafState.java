package hva.core.enums;

public enum LeafState{
    NO("SEMFOLHAS"),
    GENERATE("GERARFOLHAS"),
    WITH("COMFOLHAS"),
    DROP("LARGARFOLHAS");


    private String _str;

    private LeafState(String str){
        _str = str;
    }

    public String toString(){
        return _str;
    }

}
