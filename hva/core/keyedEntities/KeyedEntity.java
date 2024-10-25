package hva.core.keyedEntities;

import java.io.Serializable;

public abstract class KeyedEntity implements Serializable, Comparable<KeyedEntity>{
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

    @Override
    public int compareTo(KeyedEntity entity2) {
        return getKey().compareToIgnoreCase(entity2.getKey());
    }
}
