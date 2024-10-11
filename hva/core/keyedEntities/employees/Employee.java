package hva.core.keyedEntities.employees;

import hva.core.keyedEntities.KeyedEntity;

public abstract class Employee extends KeyedEntity {

    public Employee(String key, String name) {
        super(key, name);
    }
    
}
