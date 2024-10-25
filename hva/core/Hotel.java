package hva.core;
import hva.core.enums.Influence;
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
  
  // private String _fileName;
  private Season _season; // = Season.SPRING;
  private LinkedHashMap<String, Species> _species;
  private LinkedHashMap<String, Animal> _animals;
  private LinkedHashMap<String, Habitat> _habitats;
  private LinkedHashMap<String, Tree> _trees;
  private LinkedHashMap<String, Employee> _employees;
  private LinkedHashMap<String, Vaccine> _vaccines;
  private ArrayList<VaccineRegistry> _registries;
  private boolean _changes;
  

  public Hotel(){
    // _fileName = null;
    _season = Season.SPRING;
    _species = new LinkedHashMap<String, Species>();
    _animals = new LinkedHashMap<String, Animal>();
    _habitats = new LinkedHashMap<String, Habitat>();
    _trees = new LinkedHashMap<String, Tree>();
    _employees = new LinkedHashMap<String, Employee>();
    _vaccines = new LinkedHashMap<String, Vaccine>();
    _registries = new ArrayList<VaccineRegistry>();
  } 



  // public String getFileName(){
  //   return _fileName;
  // }
  
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

  public Animal getAnimal(String idAnimal)throws UnknownAnimalException{
    if (!_animals.containsKey(idAnimal)){
      throw new UnknownAnimalException(idAnimal);
    }
    return _animals.get(idAnimal);
  }
  public Habitat getHabitat(String idHabitat)throws UnknownHabitatException{
    if (!_habitats.containsKey(idHabitat)){
      throw new UnknownHabitatException(idHabitat);
    }
    return _habitats.get(idHabitat);
  }
  public Employee getEmployee(String idEmployee)throws UnknownEmployeeException{
    if (!_employees.containsKey(idEmployee)){
      throw new UnknownEmployeeException(idEmployee);
    }
    return _employees.get(idEmployee);
  }
  public Vaccine getVaccine(String idVaccine)throws UnknownVaccineException{
    if (!_vaccines.containsKey(idVaccine)){
      throw new UnknownVaccineException(idVaccine);
    }
    return _vaccines.get(idVaccine);
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
    setChanges(true);
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

      setChanges(true);
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
  
    setChanges(true);
  }


  public void registerHabitat(String id, String name, int area) throws DuplicateKeyException{
    Habitat h = new Habitat(id, name, area);
    if(_habitats.putIfAbsent(id, h) != null){
      throw new DuplicateKeyException(id);
    }
    setChanges(true);
  }


  public void createTree(String id, String name, String treeType, int age, int difficulty) throws DuplicateKeyException{
    Tree t = null;
    switch (treeType) {
      case "CADUCA":
        t = new DeciduousTree(id, name, age, _season, difficulty);
        break;
      case "PERENE":
        t = new EvergreenTree(id, name, age, _season, difficulty);
      
        
    }
    if(_trees.putIfAbsent(id, t) != null){
      throw new DuplicateKeyException(id);
    }

    setChanges(true);
  }

  public void plantTree(String treeId, String habitatId){
    Tree t = _trees.get(treeId);
    Habitat h = _habitats.get(habitatId);
    h.addTree(t);
  }



  public void addResponsibility(String employee, String responsibility) throws UnknownEmployeeException, UnknownSpeciesException, UnknownHabitatException {
    if(!_employees.containsKey(employee)){
      throw new UnknownEmployeeException(employee);
    }
    Employee e = _employees.get(employee);
    if (e instanceof Vet){
      Species s = _species.get(responsibility);
      if (s == null){ throw new UnknownSpeciesException(responsibility);}
      ((Vet) e).addSpecies(s);
    }
    else if (e instanceof Keeper){
      Habitat h = _habitats.get(responsibility);
      if (h == null){ throw new UnknownHabitatException(responsibility);}
      ((Keeper) e).addHabitat(h);
    }
    setChanges(true);
  }

  public void removeResponsibility(String employee, String responsibility) throws UnknownEmployeeException, UnknownSpeciesException, UnknownHabitatException {
    if(!_employees.containsKey(employee)){
    throw new UnknownEmployeeException(employee);
  }
  Employee e = _employees.get(employee);
  if (e instanceof Vet){
    Species s = _species.get(responsibility);
    if (s == null){ throw new UnknownSpeciesException(responsibility);}
    ((Vet) e).removeSpecies(s);
  }
  else if (e instanceof Keeper){
    Habitat h = _habitats.get(responsibility);
    if (h == null){ throw new UnknownHabitatException(responsibility);}
    ((Keeper) e).removeHabitat(h);
  }
  setChanges(true);
  }

  public Collection<Animal> showAnimals() {
    return Collections.unmodifiableCollection(_animals.values());
  }


  public Collection<Habitat> showHabitats() {
    return Collections.unmodifiableCollection(_habitats.values());
  }


  public Collection<Vaccine> showVaccines() {
    return Collections.unmodifiableCollection(_vaccines.values());
  }


  public Collection<Employee> showEmployees() {
    return Collections.unmodifiableCollection(_employees.values());
  }

  public Collection<VaccineRegistry> showRegistry() {
    return Collections.unmodifiableCollection(_registries);
  }


  public boolean getChanges() {return _changes;}

  public void setChanges(boolean changes) {_changes = changes;}

  public void transferAnimal(String idAnimal,String idHabitat) throws UnknownAnimalException, UnknownHabitatException {
    
    getAnimal(idAnimal).changeHabitat(getHabitat(idHabitat));

    setChanges(true);
  }

  public void setArea(String idHabitat, int area) throws UnknownHabitatException {
    if(!_habitats.containsKey(idHabitat)){
      throw new UnknownHabitatException(idHabitat);
    }
    Habitat h = _habitats.get(idHabitat);

    h.setArea(area);

    setChanges(true);
  }


  public void changeHabitatInfluence(String habitat, String species, String influence) throws UnknownHabitatException, UnknownSpeciesException{
    if(!_habitats.containsKey(habitat)){
      throw new UnknownHabitatException(habitat);
    }
    Habitat h = _habitats.get(habitat);
    if(!_species.containsKey(species)){
      throw new UnknownSpeciesException(species);
    }
    Species s = _species.get(species);
    
    switch (influence) {
      case "POS":
        h.changeInfluence(s, Influence.POS);
        break;
      case "NEU":
        h.changeInfluence(s, Influence.NEU);
        break;
      case "NEG":
        h.changeInfluence(s, Influence.NEG);
        break;

    }
    setChanges(true);
  }

  public void advanceSeason() {
    _season = _season.next();
    for (String k: _trees.keySet()){
      if (_trees.get(k) == null) {
        // Handle the null case if needed, e.g., log a warning or continue.
        System.out.println("found a null value from non/null key:" + k);
        continue;
      }
      _trees.get(k).seasonalUpdate(_season);
    }
  }

  public boolean getChangeState() {
    return _changes;
  }

  public void vaccineate(String idVacina, String idVet, String idAnimal) throws UnknownEmployeeException, UnknownVaccineException, UnknownAnimalException, WrongResponsibilityException{
   
    if(!_vaccines.containsKey(idVacina)){
      throw new UnknownVaccineException(idVacina);
    }
    Vaccine vaccine = _vaccines.get(idVacina);

    if(!_employees.containsKey(idVet)){
      throw new UnknownEmployeeException(idVet);
    }
    Employee vet = _employees.get(idVet);

    if(!_animals.containsKey(idAnimal)){
      throw new UnknownAnimalException(idAnimal);
    }
    Animal a = _animals.get(idAnimal);

    ((Vet) vet).vacinate(vaccine, a);
  
    setChanges(true);
  }

  public float getSatisfaction(Animal animal) throws UnknownAnimalException{
    if (animal == null) {
      throw new UnknownAnimalException(animal.getKey());
    }

    return animal.getSatisfaction();
  } 
  

}
