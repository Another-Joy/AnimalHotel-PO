package hva.core.keyedEntities;
import java.util.ArrayList;

public class Animal extends KeyedEntity{
    private ArrayList<String> _healthState;
    private ArrayList<VaccineRegistry> _vaccines;
    private Habitat _habitat;
    private Species _species;

    public Animal(String key, String name, Species species){
        super(key, name);
        _healthState = new ArrayList<String>();
        _vaccines = new ArrayList<VaccineRegistry>();
        _species = species;
    }

    public String toString(){
        return ("ANIMAL|" + super.toString() + "|" + _species.getKey() + "|" + printSaude() + "|" + _habitat.getKey());
    }
}
