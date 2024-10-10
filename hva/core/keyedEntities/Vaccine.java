package hva.core.keyedEntities;
import java.util.ArrayList;

public class Vaccine extends KeyedEntity{
    private ArrayList<Species> _species;
    private ArrayList<VaccineRegistry> _registry;

    public Vaccine(String key, String name, ArrayList<Species> species){
        super(key, name);
        _species = species;
        _registry = new ArrayList<VaccineRegistry>();
    }
}