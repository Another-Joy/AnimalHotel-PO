package hva.core.keyedEntities.employees;

import java.util.ArrayList;

import hva.core.keyedEntities.Habitat;

public class Keeper extends Employee{
    private ArrayList<Habitat> _habitats;


    public Keeper(String key, String name){
        super(key, name);
        _habitats = new ArrayList<Habitat>();
    }
    
}
