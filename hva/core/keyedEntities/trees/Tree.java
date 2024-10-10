package hva.core.keyedEntities.trees;

import hva.core.keyedEntities.KeyedEntity;
import hva.core.enums.LeafState;
import hva.core.enums.Season;

public abstract class Tree extends KeyedEntity{
    private int _age;
    private Season _agingSeason;
    private int _cleaningDifficulty;
    private int _seasonalEffort;
    private LeafState _leafState;

    public Tree(String key, String name, Season season, int cleaningDifficulty){
        super(key, name);
        _age = 0;
        _agingSeason = season;
        _cleaningDifficulty = cleaningDifficulty;
        _seasonalEffort = getCleaningEffort(season);
        _leafState = getLeafState(season);
    }

    abstract void seasonalUpdate(Season season);

    public String toString(){
        return ("ÁRVORE|" + super.toString() + "|" + _age + "|" + _cleaningDifficulty);
    }
}