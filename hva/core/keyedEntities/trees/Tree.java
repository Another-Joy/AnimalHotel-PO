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
        _seasonalEffort = getSeasonalEffort(season);
        _leafState = getLeafState(season);
    }


    public double getCleaningEffort(){
        return (_cleaningDifficulty*_seasonalEffort*(Math.log(_age)));
    }

    public void seasonalUpdate(Season season){
        if (season == _agingSeason){ _age += 1;}
        _leafState = getLeafState(season);
        _seasonalEffort = getSeasonalEffort(season);
    }

    public LeafState getLeafState(){
        return _leafState;
    }

    public String toString(){
        return ("ÁRVORE|" + super.toString() + "|" + _age + "|" + _cleaningDifficulty);
    }
    
    abstract int getSeasonalEffort(Season season);
    
    abstract LeafState getLeafState(Season season);


}