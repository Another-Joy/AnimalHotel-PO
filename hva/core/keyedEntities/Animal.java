package hva.core.keyedEntities;
import java.util.ArrayList;

import hva.core.enums.HealthState;

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
    }

    public String toString(){
        return ("ANIMAL|" + super.toString() + "|" + _species.getKey() + printSaude() + "|" + _habitat.getKey());
    }

    public void changeHabitat(Habitat hab){
        _habitat = hab;
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
