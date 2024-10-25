package hva.core.keyedEntities;
import java.util.*;

import hva.core.keyedEntities.employees.Vet;

public class Species extends KeyedEntity {
    private ArrayList<Animal> _animals;
    private ArrayList<Vet> _vets;

    public Species(String key, String name){
        super(key, name);
        _animals = new ArrayList<Animal>();
    }

    public String toString(){
        return ("ESPÉCIE|" + super.toString());
    }

    public int calculateDiference(ArrayList<Species> species) {
        if (species.contains(this)){
            return 0;
        }
        int[] common = new int[species.size()];int i = 0;
        for (Species s: species){
            common[i] = 0;
            HashMap<Character, Integer> count = new HashMap<Character, Integer>();
            for (Character c: (s.getName()).toCharArray()){
                count.put(c, count.get(c)+ 1);
            }
            for (Character c: this.getName().toCharArray()){
                if (count.get(c) != 0){
                    common[i] += 1;
                    count.put(c, count.get(c)- 1);
                }
            }
            i += 1;
            int lenBig = compareSizes(s);
            common[i] = lenBig-common[i];
        }
        return Arrays.stream(common).max().getAsInt(); 
    }
    

    private int compareSizes(Species s) {
        if (this.getName().length() < s.getName().length()){
            return s.getName().length();
        }
        return this.getName().length();
    }

    public double satisfactionCost(){
        double res = (_animals.size()/_vets.size());
        return res;
    }
}
