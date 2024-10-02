package hva.core.keyedEntities.employees;
import java.util.ArrayList;

import hva.core.keyedEntities.Species;
import hva.core.keyedEntities.VaccineRegistry;

public class Vet extends Employee {
    private ArrayList<Species> _species;
    private ArrayList<VaccineRegistry> _registry;

    public Vet(String key, String name, ArrayList<Species> species){
        super(key, name);
        _species = species;
        _registry = new ArrayList<VaccineRegistry>();
    }

}
