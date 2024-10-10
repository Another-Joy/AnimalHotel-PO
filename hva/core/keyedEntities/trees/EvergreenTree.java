package hva.core.keyedEntities.trees;

import hva.core.enums.Season;

public class EvergreenTree extends Tree {

    public EvergreenTree(String key, String name, Season season, int cleaningDifficulty) {
        super(key, name, season, cleaningDifficulty);
    }

    @Override
    void seasonalUpdate(Season season) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'seasonalUpdate'");
    }
    
    public String toString(){
        return (super.toString() + "|PERENE|" + getLeafState());
    }



}
