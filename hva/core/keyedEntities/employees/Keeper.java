package hva.core.keyedEntities.employees;

import java.util.ArrayList;

import hva.core.keyedEntities.Habitat;
import hva.core.keyedEntities.Species;

public class Keeper extends Employee{
    private ArrayList<Habitat> _habitats;


    public Keeper(String key, String name){
        super(key, name);
        _habitats = new ArrayList<Habitat>();
    }

    public String toString(){
        return ("TRT|" + super.toString() + printResponsibilities());
    }



    private String printResponsibilities(){
        if (_habitats.isEmpty()){return ("|VOID");}
        String s = new String();
        for (Habitat habitat: _habitats){
            s = s + "," + habitat.getKey();
        }
        return ("|" + s.substring(1));
    }
}
