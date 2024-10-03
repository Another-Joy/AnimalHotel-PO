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

    public Tree(String key, String name, Season agingSeason, int cleaningDifficulty){
        super(key, name);
        _age = 0;
        _agingSeason = agingSeason;
        _cleaningDifficulty = cleaningDifficulty;
        _seasonalEffort = getCleaningEffort();
        _leafState = getLeafState();
    }
}
