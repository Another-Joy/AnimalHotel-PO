package hva.core.keyedEntities;
import java.util.ArrayList;
import java.util.HashMap;

import hva.core.enums.Influence;
import hva.core.keyedEntities.employees.Keeper;
import hva.core.keyedEntities.trees.Tree;

public class Habitat extends KeyedEntity {
    private int _area;
    private HashMap<Species, Influence> _influences;
    private ArrayList<Animal> _animals;
    private ArrayList<Tree> _trees;
    private ArrayList<Keeper> _keepers;
    
    public Habitat(String key, String name, int area){
        super(key, name);
        _area = area;
        _animals = new ArrayList<Animal>();
        _trees = new ArrayList<Tree>();
        _keepers = new ArrayList<Keeper>();
        _influences = new HashMap<Species, Influence>();
    }


    public ArrayList<Animal> getAnimals(){
        return _animals;
    }

    public String toString(){
        return ("HABITAT|" + super.toString() + "|" + _area + "|" + _trees.size());
    }


    public void addTree(Tree t) {
        _trees.add(t);
    }


    public float genericAnimalSatisfaction(Animal animal) {        
        return ((_animals.size()/_area) + _influences.get(animal.getSpecies()).getInt());
    }

}
