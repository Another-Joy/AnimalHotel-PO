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
        String res =  ("HABITAT|" + super.toString() + "|" + _area + "|" + _trees.size());
        for (Tree t: _trees){
            res += "\n" + t.toString();
        }
        return res;
    }

    public void addAnimal(Animal a){
        _animals.add(a);
    }
    public void removeAnimal(Animal a){
        _animals.remove(a);
    }


    public void addTree(Tree t) {
        _trees.add(t);
    }

    public ArrayList<Tree> getTrees(){
        return _trees;
    }


    public float genericAnimalSatisfaction(Animal animal) {      
        return ((_area/_animals.size()) + _influences.getOrDefault(animal.getSpecies(), Influence.NEU).getInt());
    }

    public void setArea(int area) {
        _area = area;
    } 

    public void changeInfluence(Species species, Influence influence) {
        _influences.put(species, influence);
    }


    public double satisfactionCost(){
        double res = _area + (3*_animals.size());
        for (Tree t: _trees){
            res += t.getCleaningEffort();
        }
        res = res/_keepers.size();
        return res;
    }

}
