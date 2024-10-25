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

    @Override
    public String toString(){
        return String.format("TRT|%s|%s%s",super.getKey(),super.getName(), printResponsibilities());
    }



    private String printResponsibilities(){
        if (_habitats.isEmpty()){return ("");}
        String s = new String();
        for (Habitat habitat: _habitats){
            s = s + "," + habitat.getKey();
        }
        return ("|" + s.substring(1));
    }

    public void addHabitat(Habitat habitat) {
        _habitats.add(habitat);
    }


    public void removeHabitat(Habitat habitat) {
        _habitats.remove(habitat);
    }


    @Override
    public double calculateSatisfaction() {
        double res = 300;
        for (Habitat h : _habitats){
            res -= h.satisfactionCost();
        }
        return res;
    }
}
