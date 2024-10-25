package hva.core.keyedEntities.employees;
import java.util.ArrayList;

import hva.core.keyedEntities.Animal;
import hva.core.keyedEntities.Habitat;
import hva.core.keyedEntities.Species;
import hva.core.keyedEntities.Vaccine;
import hva.core.keyedEntities.VaccineRegistry;

public class Vet extends Employee {
    private ArrayList<Species> _species;
    private ArrayList<VaccineRegistry> _registry;

    public Vet(String key, String name){
        super(key, name);
        _species = new ArrayList<Species>();
        _registry = new ArrayList<VaccineRegistry>();
    }


    public String toString(){
        return ("VET|" + super.toString() + printResponsibilities());
    }


    public void vacinate(Vaccine vac, Animal animal){
        VaccineRegistry reg = animal.registerVaccine(this, vac);
        vac.registerVaccine(reg);
        animal.updateHealthState(vac);
    }



    private String printResponsibilities(){
        if (_species.isEmpty()){return ("|VOID");}
        String s = new String();
        for (Species species: _species){
            s = s + "," + species.getKey();
        }
        return ("|" + s.substring(1));
    }

}
