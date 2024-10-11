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

    public String toString() {
        return ("VACINA|" + super.toString() + "|" + _registry.size() + printSpecies());
    }

    private String printSpecies(){
        String s = new String();
        for (Species species: _species){
            s = s + "|" + species.getKey();
        }
        return s;
    }
}
