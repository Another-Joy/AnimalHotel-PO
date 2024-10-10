package hva.core.keyedEntities;

public abstract class KeyedEntity {
    private final String _key;
    private final String _name;

    public KeyedEntity(String key, String name){
        _key = key;
        _name = name;
    }

    public String getKey(){
        return _key;
    }

    public String getName(){
        return _name;
    }

    public String toString(){
        return (_key + "|" + _name);
    }
}
