package hva.core;

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
  private ArrayList<Species> _species;
  private ArrayList<Animal> _animals;
  private ArrayList<Habitat> _habitats;
  private ArrayList<Tree> _trees;
  private ArrayList<Employee> _employees;
  private ArrayList<Vaccine> _vaccines;
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

  public void registerAnimal(String id, String name, String habitatId, String speciesId) {
    // TODO Auto-generated method stub
    /* try {
      _species.contains(speciesId);
    } catch{pass}
     */
    Animal a = new Animal(); 
    _animals.put(a);
      // throw new UnsupportedOperationException("Unimplemented method 'registerAnimal'");
  }

  public void registerSpecies(String id, String name) {
      // TODO Auto-generated method stub
    Species s = new Species(id, name);
    _species.put(s);
      throw new UnsupportedOperationException("Unimplemented method 'registerSpecies'");
  }

  public void registerEmployee(String id, String name, String empType) {
      // TODO Auto-generated method stub
    Employee e;
    switch (empType) {
      case "VET":
        e = new Vet(id, name);
        break;
      case "TRT":
        e = new Keeper(id, name);
      default:
        break;
    }
    _employees.put(e);
      throw new UnsupportedOperationException("Unimplemented method 'registerEmployee'");
  }

  public void registerVaccine(String id, String name, String[] speciesIds) {
      // TODO Auto-generated method stub
    Vaccine v = new Vaccine(id, name, /* ??? */);
      throw new UnsupportedOperationException("Unimplemented method 'registerVaccine'");
  }

  public Habitat registerHabitat(String id, String name, int area) {
      // TODO Auto-generated method stub
      Habitat h = new Habitat(id, name, area);
      _habitats.put(h);
      throw new UnsupportedOperationException("Unimplemented method 'registerHabitat'");
  }
}
