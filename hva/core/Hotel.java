package hva.core;
import hva.core.enums.Season;
import hva.core.exception.*;
import hva.core.keyedEntities.*;
import hva.core.keyedEntities.employees.*;
import hva.core.keyedEntities.trees.*;

import java.io.*;
import java.util.*;

public class Hotel implements Serializable {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  private String _fileName;
  private Season _season; // = Season.SPRING;
  private LinkedHashMap<String, Species> _species;
  private LinkedHashMap<String, Animal> _animals;
  private LinkedHashMap<String, Habitat> _habitats;
  private LinkedHashMap<String, Tree> _trees;
  private LinkedHashMap<String, Employee> _employees;
  private LinkedHashMap<String, Vaccine> _vaccines;
  private ArrayList<VaccineRegistry> _registries;
  

  public Hotel(){
    _fileName = null;
    _season = Season.SPRING;
    _species = new LinkedHashMap<String, Species>();
    _animals = new LinkedHashMap<String, Animal>();
    _habitats = new LinkedHashMap<String, Habitat>();
    _trees = new LinkedHashMap<String, Tree>();
    _employees = new LinkedHashMap<String, Employee>();
    _vaccines = new LinkedHashMap<String, Vaccine>();
    _registries = new ArrayList<VaccineRegistry>();
  } 



  public String getFileName(){
    return _fileName;
  }
  
  /**
   * Read text input file and create corresponding domain entities.
   * 
   * @param filename name of the text input file
   * @throws UnrecognizedEntryException if some entry is not correct
   * @throws IOException if there is an IO erro while processing the text file
   **/
  void importFile(String filename) throws UnrecognizedEntryException, IOException {
    Parser p = new Parser(this);
    p.parseFile(filename);
  }

  public void registerAnimal(String id, String name, String habitatId, String speciesId) throws UnknownSpeciesException, UnknownHabitatException, DuplicateKeyException{
    Species s = _species.get(speciesId);
    Habitat h = _habitats.get(habitatId);
    if (s == null){
      throw new UnknownSpeciesException(speciesId);
    } else if(h == null){
      throw new UnknownHabitatException(habitatId);
    }
     
    Animal a = new Animal(id, name, s, h); 
    if (_animals.putIfAbsent(id, a) != null){
      throw new DuplicateKeyException(id);
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


  public void registerEmployee(String id, String name, String empType) throws DuplicateKeyException{
    Employee e = null;
    switch (empType) {
      case "VET":
        e = new Vet(id, name);
        break;
      case "TRT":
        e = new Keeper(id, name);
    }
    if(_employees.putIfAbsent(id, e) != null){
      throw new DuplicateKeyException(id);
    }

      // throw new UnsupportedOperationException("Unimplemented method 'registerEmployee'");
  }


  public void registerVaccine(String id, String name, String[] speciesIds) throws DuplicateKeyException, UnknownSpeciesException {
    ArrayList<Species> species = new ArrayList<Species>();

    for(String str : speciesIds) {
      Species s = _species.get(str);
      if (s == null){throw new UnknownSpeciesException(str);};
      species.add(s);
    }

    Vaccine v = new Vaccine(id, name, species);
    
    if(_vaccines.putIfAbsent(id, v) != null){
      throw new DuplicateKeyException(id);
    }
  
  }


  public void registerHabitat(String id, String name, int area) throws DuplicateKeyException{
    Habitat h = new Habitat(id, name, area);
    if(_habitats.putIfAbsent(id, h) != null){
      throw new DuplicateKeyException(id);
    }
  }


  public void createTree(String id, String name, String treeType, int age, int difficulty) throws DuplicateKeyException, IOException{
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
      throw new DuplicateKeyException(id);
    }
  }

  public void plantTree(String treeId, String habitatId){
    Tree t = _trees.get(treeId);
    Habitat h = _habitats.get(habitatId);
    h.addTree(t);
  }



  public void addResponsibility(String employee, String responsibility) throws WrongResponsibilityException {
    Employee e = _employees.get(employee);
    if (e instanceof Vet){
      Species s = _species.get(responsibility);
      if (s == null){ throw new WrongResponsibilityException(employee, responsibility);}
    }
    else if (e instanceof Keeper){
      Habitat h = _habitats.get(responsibility);
      if (h == null){ throw new WrongResponsibilityException(employee, responsibility);}
    }
  }


  

  public String show(String type){
    String s = new String();
    switch (type) {
      case "Animals":
        for (Animal a : _animals.values()){
          s = s + a.toString() + "\n";
        }
        break;      
      case "Vaccines":
        for (Vaccine v : _vaccines.values()){
          s = s + v.toString() + "\n";
        }      
        break;
      case "Employees":
        for (Employee e : _employees.values()){
          s = s + e.toString() + "\n";
        }
        break;  
      case "Habitats":
        for (Habitat h : _habitats.values()){
          s = s + h.toString() + "\n";
        }
        break;
      default:
        break;
    }
    return s.substring(0, s.length()-1);
  }


}
