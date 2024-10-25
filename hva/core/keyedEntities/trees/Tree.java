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

    public Tree(String key, String name, int age ,Season season, int cleaningDifficulty){
        super(key, name);
        _age = age;
        _agingSeason = season;
        _cleaningDifficulty = cleaningDifficulty;
        _seasonalEffort = getCleaningEffort(season);
        _leafState = getLeafState(season);
    }



    abstract void seasonalUpdate(Season season);

    abstract LeafState getLeafState(Season season);
    public LeafState getLeafState(){
        return _leafState;
    }

    public String toString(){
        return ("ÁRVORE|" + super.toString() + "|" + _age + "|" + _cleaningDifficulty);
    }
    
    private int getCleaningEffort(Season season){
        return 0;
    }


}