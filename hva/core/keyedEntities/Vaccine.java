package hva.core.keyedEntities;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Vaccine extends KeyedEntity{
    private ArrayList<Species> _species;
    private ArrayList<VaccineRegistry> _registry;

    public Vaccine(String key, String name, ArrayList<Species> species){
        super(key, name);
        _species = species;
        _registry = new ArrayList<VaccineRegistry>();
    }

    @Override
    public String toString() {
        return ("VACINA|" + super.toString() + "|" + _registry.size() + printSpecies());
    }

    private String printSpecies(){
        if (_species.isEmpty()){return ("");}
        String s = new String();
        Collections.sort(_species, Comparator.comparing(Species::getKey));
        for (Species species: _species){
            s = s + "," + species.getKey();
        }
        return ("|" + s.substring(1));
        
    }

    public ArrayList<Species> getSpecies() {
        return _species;
    }

    public void registerVaccine(VaccineRegistry reg){
        _registry.add(reg);
    }


}


