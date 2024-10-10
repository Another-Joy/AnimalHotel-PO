package hva.core;

import hva.core.exception.*;
import hva.core.keyedEntities.Habitat;

import java.io.*;
import java.util.*;
// FIXME import classes

public class Hotel implements Serializable {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  // FIXME define attributes
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
    throw new UnsupportedOperationException("Unimplemented method 'registerAnimal'");
}

public void registerSpecies(String id, String name) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'registerSpecies'");
}

public void registerEmployee(String id, String name, String empType) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'registerEmployee'");
}

public void registerVaccine(String id, String name, String[] speciesIds) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'registerVaccine'");
}

public Habitat registerHabitat(String id, String name, int area) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'registerHabitat'");
}
}
