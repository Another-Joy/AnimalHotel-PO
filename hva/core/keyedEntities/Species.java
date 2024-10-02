package hva.core.keyedEntities;
import java.util.ArrayList;

public class Species extends KeyedEntity {
    private ArrayList<Animal> _animals;

    public Species(String key, String name){
        super(key, name);
        _animals = new ArrayList<Animal>();
    }
}
