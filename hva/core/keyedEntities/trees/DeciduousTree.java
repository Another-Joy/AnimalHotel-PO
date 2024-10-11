package hva.core.keyedEntities.trees;

import hva.core.enums.LeafState;
import hva.core.enums.Season;

public class DeciduousTree extends Tree{

    public DeciduousTree(String key, String name, Season season, int cleaningDifficulty) {
        super(key, name, season, cleaningDifficulty);
    }

    @Override
    void seasonalUpdate(Season season) {
        throw new UnsupportedOperationException("Unimplemented method 'seasonalUpdate'");
    }
    
    public String toString(){
        return (super.toString() + "|CADUCA|" + getLeafState());
    }

    public LeafState getLeafState(Season season){
        return LeafState.GENERATE;
    }
}
