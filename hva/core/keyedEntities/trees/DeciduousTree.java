package hva.core.keyedEntities.trees;

import hva.core.enums.LeafState;
import hva.core.enums.Season;

public class DeciduousTree extends Tree{

    public DeciduousTree(String key, String name, int age, Season season, int cleaningDifficulty) {
        super(key, name, age, season, cleaningDifficulty);
    }
    
    public String toString(){
        return (super.toString() + "|CADUCA|" + getLeafState());
    }

    @Override
    int getSeasonalEffort(Season season) {
        switch (season) {
            case SPRING:
                return 1;
            case SUMMER:
                return 2;
            case FALL:
                return 5;
            case WINTER:
                return 0;
        }
        return 0;
    }

    @Override
    LeafState getLeafState(Season season) {
        switch (season) {
            case SPRING:
                return LeafState.GENERATE;
            case SUMMER:
                return LeafState.WITH;
            case FALL:
                return LeafState.DROP;
            case WINTER:
                return LeafState.NO;
        }
        return null;
    }

}
