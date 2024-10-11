package hva.core;

import hva.app.exception.DuplicateAnimalKeyException;
import hva.app.exception.DuplicateEmployeeKeyException;
import hva.app.exception.DuplicateHabitatKeyException;
import hva.app.exception.DuplicateTreeKeyException;
import hva.app.exception.DuplicateVaccineKeyException;
import hva.app.exception.NoResponsibilityException;
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
  private ArrayList<VaccineRegistry> _registries;
  

  public Hotel(){
    _season = Season.SPRING;
    _species = new HashMap<String, Species>();
    _animals = new HashMap<String, Animal>();
    _habitats = new HashMap<String, Habitat>();
    _trees = new HashMap<String, Tree>();
    _employees = new HashMap<String, Employee>();
    _vaccines = new HashMap<String, Vaccine>();
    _registries = new ArrayList<VaccineRegistry>();
  } 


  // FIXME define more methods
  
  /**
   * Read text input file and create corresponding domain entities.
   * 
   * @param filename name of the text input file
   * @throws UnrecognizedEntryException if some entry is not correct
   * @throws IOException if there is an IO erro while processing the text file
   **/
  void importFile(String filename) throws UnrecognizedEntryException, IOException /* FIXME maybe other exceptions */  {
    Parser p = new Parser(this);
    p.parseFile(filename);
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


  public void registerEmployee(String id, String name, String empType) throws DuplicateEmployeeKeyException{
    Employee e = null;
    switch (empType) {
      case "VET":
        e = new Vet(id, name);
        break;
      case "TRT":
        e = new Keeper(id, name);
    }
    if(_employees.putIfAbsent(id, e) != null){
      throw new DuplicateEmployeeKeyException(id);
    }

      // throw new UnsupportedOperationException("Unimplemented method 'registerEmployee'");
  }


  public void registerVaccine(String id, String name, String[] speciesIds) throws DuplicateVaccineKeyException, UnknownSpeciesKeyException {
    ArrayList<Species> species = new ArrayList<Species>();

    for(String str : speciesIds) {
      Species s = _species.get(str);
      if (s == null){throw new UnknownSpeciesKeyException(str);};
      species.add(s);
    }

    Vaccine v = new Vaccine(id, name, species);
    
    if(_vaccines.putIfAbsent(id, v) != null){
      throw new DuplicateVaccineKeyException(id);
    }
  
  }


  public Habitat registerHabitat(String id, String name, int area) throws DuplicateHabitatKeyException{
      Habitat h = new Habitat(id, name, area);
      if(_habitats.putIfAbsent(id, h) != null){
        throw new DuplicateHabitatKeyException(id);
      }
      return h;
  }


  public void plantTree(String id, String name, int age, int difficulty, String treeType, String habitatId) throws DuplicateTreeKeyException, IOException{
    Tree t = null;
    switch (treeType) {
      case "CADUCA":
        t = new DeciduousTree(id, name, _season, difficulty);
        break;
      case "PERENE":
        t = new EvergreenTree(id, name, _season, difficulty);
      default:
        throw new IOException("Wrong Type");
    }
    if(_trees.putIfAbsent(id, t) != null){
      throw new DuplicateTreeKeyException(id);
    }

    Habitat h = _habitats.get(habitatId);
    h.addTree(t);

  }


  public void addResponsibility(String employee, String responsibility) throws NoResponsibilityException {
    Employee e = _employees.get(employee);
    if (e instanceof Vet){
      Species s = _species.get(responsibility);
      if (s == null){ throw new NoResponsibilityException(employee, responsibility);}
    }
    else if (e instanceof Keeper){
      Habitat h = _habitats.get(responsibility);
      if (h == null){ throw new NoResponsibilityException(employee, responsibility);}
    }
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'addResponsibility'");
  }

}
