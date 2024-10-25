package hva.core.keyedEntities;

import java.io.Serializable;

import hva.core.keyedEntities.employees.Vet;

public class VaccineRegistry implements Serializable{
    private Vaccine _vaccine;
    private Species _species;
    private Vet _vet;

    public VaccineRegistry(Vaccine vaccine, Species species, Vet vet){
        _vaccine = vaccine;
        _species = species;
        _vet = vet;
    }

    public String toString(){
        return ("REGISTO-VACINA|" + _vaccine.getKey() + "|" + _vet.getKey() + "|" + _species.getKey());
    }

    public Object getVaccine() {
        return _vaccine;
    }
    
}
