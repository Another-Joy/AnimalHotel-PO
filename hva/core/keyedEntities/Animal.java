package hva.core.keyedEntities;
import java.util.ArrayList;

import hva.core.enums.HealthState;
import hva.core.keyedEntities.employees.Vet;

public class Animal extends KeyedEntity{
    private ArrayList<HealthState> _healthState;
    private ArrayList<VaccineRegistry> _vaccines;
    private Habitat _habitat;
    private Species _species; //Achas que em vez de uma especie pode levar um speciesID?

    public Animal(String key, String name, Species species, Habitat habitat){
        super(key, name);
        _healthState = new ArrayList<HealthState>();
        _vaccines = new ArrayList<VaccineRegistry>();
        _species = species;
        _habitat = habitat;
        _habitat.addAnimal(this);
    }

    public String toString(){
        return ("ANIMAL|" + super.toString() + "|" + _species.getKey() + printSaude() + "|" + _habitat.getKey());
    }

    public void changeHabitat(Habitat hab){
        _habitat.removeAnimal(this);
        _habitat = hab;
        _habitat.addAnimal(this);
    }

    public VaccineRegistry registerVaccine(Vet vet, Vaccine vac){
        VaccineRegistry reg = new VaccineRegistry(vac, _species, vet);
        _vaccines.add(reg);
        return reg;
    }


    public void updateHealthState(Vaccine vac) {
        _species.calculateDiference(vac.getSpecies());
    }

    public boolean equalSpecies(Animal a){
        return (a.getSpecies() == _species);
    }


    public Species getSpecies() {
        return _species;
    }

    public float getSatisfaction(){
        float res = 20;
        for (Animal a: _habitat.getAnimals()){
            if (equalSpecies(a)){res += 3;}
            else {res -= 2;}
        }
        res += _habitat.genericAnimalSatisfaction(this);
        return res;
    }

    private String printSaude(){
        if (_healthState.isEmpty()){return ("|VOID");}
        String s = new String();
        for (HealthState h: _healthState){
            s = s + "," + h.toString();
        }
        return ("|" + s.substring(1));
    }
}
