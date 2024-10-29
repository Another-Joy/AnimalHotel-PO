package hva.core.keyedEntities.trees;

import hva.core.enums.LeafState;
import hva.core.enums.Season;

public class EvergreenTree extends Tree {

    public EvergreenTree(String key, String name, int age, Season season, int cleaningDifficulty) {
        super(key, name, age, season, cleaningDifficulty);
    }
    
    public String toString(){
        return (super.toString() + "|PERENE|" + getLeafState());
    }

    @Override
    int getSeasonalEffort(Season season) {
        switch (season) {
            case SPRING:
                return 1;
            case SUMMER:
                return 1;
            case FALL:
                return 1;
            case WINTER:
                return 2;
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
                return LeafState.WITH;
            case WINTER:
                return LeafState.DROP;
        }
        return null;
    }


}
