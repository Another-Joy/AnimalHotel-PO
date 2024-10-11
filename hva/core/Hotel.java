package hva.core;

import hva.app.exception.DuplicateAnimalKeyException;
import hva.app.exception.UnknownHabitatKeyException;
import hva.app.exception.UnknownSpeciesKeyException;
import hva.core.enums.Season;
import hva.core.exception.*;
import hva.core.keyedEntities.*;
import hva.core.keyedEntities.employees.*;
import hva.core.keyedEntities.trees.*;

import java.io.*;
import java.util.*;
// FIXME import classes

public class Hotel implements Serializable {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  // FIXME define attributes
  private Season _season; // = Season.SPRING;
  private HashMap<String, Species> _species;
  private HashMap<String, Animal> _animals;
  private HashMap<String, Habitat> _habitats;
  private HashMap<String, Tree> _trees;
  private HashMap<String, Employee> _employees;
  private HashMap<String, Vaccine> _vaccines;
  private ArrayList<VaccineRegistry> _resgistries;
  // FIXME define contructor(s)
  // FIXME define more methods
  
  /**
   * Read text input file and create corresponding domain entities.
   * 
   * @param filename name of the text input file
   * @throws UnrecognizedEntryException if some entry is not correct
   * @throws IOException if there is an IO erro while processing the text file
   **/
  void importFile(String filename) throws UnrecognizedEntryException, IOException /* FIXME maybe other exceptions */  {
    //FIXME implement method
  }

  public void registerAnimal(String id, String name, String habitatId, String speciesId) throws UnknownSpeciesKeyException, UnknownHabitatKeyException, DuplicateAnimalKeyException{
    Species s = _species.get(speciesId);
    Habitat h = _habitats.get(habitatId);
    if (s == null){
      throw new UnknownSpeciesKeyException(speciesId);
    } else if(h == null){
      throw new UnknownHabitatKeyException(habitatId);
    }
     
    Animal a = new Animal(id, name, s, h); 
    if (_animals.putIfAbsent(id, a) != null){
      throw new DuplicateAnimalKeyException(id);
    }

  }


  public void registerSpecies(String id, String name) {
    Species s = new Species(id, name);
    for ( Species sp : _species.values()){
      if (sp.getName() == name){
        //throw DuplicateSpeciesNameException, untested & not needed
      }
    }
    _species.put(id, s);
  }


  public void registerEmployee(String id, String name, String empType) {

      throw new UnsupportedOperationException("Unimplemented method 'registerEmployee'");
  }

  public void registerVaccine(String id, String name, String[] speciesIds) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'registerVaccine'");
  }

  public Habitat registerHabitat(String id, String name, int area) {
      // TODO Auto-generated method stub
      Habitat h = new Habitat(id, name, area);
      _habitats.add(h);
      throw new UnsupportedOperationException("Unimplemented method 'registerHabitat'");
  }
}
