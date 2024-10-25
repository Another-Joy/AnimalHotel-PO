package hva.core.keyedEntities.employees;
import java.util.ArrayList;

import hva.core.exception.WrongResponsibilityException;
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
        return String.format("VET|%s|%s%s",super.getKey(),super.getName(), printResponsibilities());
    }


    public void vacinate(Vaccine vac, Animal animal) throws WrongResponsibilityException{
        if (!(_species.contains(animal.getSpecies()))){throw new WrongResponsibilityException(this.getKey(), animal.getSpecies().getKey());}
        VaccineRegistry reg = animal.registerVaccine(this, vac);
        vac.registerVaccine(reg);
        animal.updateHealthState(vac);
    }



    private String printResponsibilities(){
        if (_species.isEmpty()){return ("");}
        String s = new String();
        for (Species species: _species){
            s = s + "," + species.getKey();
        }
        return ("|" + s.substring(1));
    }

    public void addSpecies(Species species) {
        _species.add(species);
    }


    public void removeSpecies(Species species) {
       _species.remove(species);
    }


    @Override
    public double calculateSatisfaction() {
        double res = 20;
        for (Species s: _species){
            res -= s.satisfactionCost();
        }
        return res;
    }
    }



}
