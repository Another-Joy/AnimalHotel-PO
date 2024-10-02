package hva.core.keyedEntities;
import java.util.ArrayList;

public class Animal extends KeyedEntity{
    private ArrayList<String> _healthState;
    private ArrayList<VaccineRegistry> _vaccines;
    private Habitat _habitat;

    public Animal(String key, String name){
        super(key, name);
        _healthState = new ArrayList<String>();
        _vaccines = new ArrayList<VaccineRegistry>();
    }
}
