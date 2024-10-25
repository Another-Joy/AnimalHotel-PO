package hva.core;

import hva.core.exception.*;
import java.io.*;

// FIXME import classes

/**
 * Class representing the manager of this application. It manages the current
 * zoo hotel.
 **/
public class HotelManager implements Serializable {
  /** The current zoo hotel */ // Should we initialize this field?
  private Hotel _hotel = new Hotel();
  private String _filename = null;

  public void newHotel(){
    _hotel = new Hotel();
    
  }





  /**
   * Saves the serialized application's state into the file associated to the current network.
   *
   * @throws FileNotFoundException if for some reason the file cannot be created or opened. 
   * @throws MissingFileAssociationException if the current network does not have a file.
   * @throws IOException if there is some error while serializing the state of the network to disk.
   **/
  public void save() throws FileNotFoundException, MissingFileAssociationException, IOException {
    try {
      // Create a file to save the serialized object
      FileOutputStream fileOut = new FileOutputStream(_filename);
      ObjectOutputStream out = new ObjectOutputStream(fileOut);

      // Serialize the object
      out.writeObject(_hotel);
      out.close();
      fileOut.close();

      // System.out.println("Serialized data is saved in person.ser");
  } catch (IOException i) {
      i.printStackTrace();
  }
  }
  
  /**
   * Saves the serialized application's state into the specified file. The current network is
   * associated to this file.
   *
   * @param filename the name of the file.
   * @throws FileNotFoundException if for some reason the file cannot be created or opened.
   * @throws MissingFileAssociationException if the current network does not have a file.
   * @throws IOException if there is some error while serializing the state of the network to disk.
   **/
  public void saveAs(String filename) throws FileNotFoundException, MissingFileAssociationException, IOException {
    // FIXME implement serialization method
    _filename = filename;
  save();
  }
  
  /**
   * @param filename name of the file containing the serialized application's state
   *        to load.
   * @throws UnavailableFileException if the specified file does not exist or there is
   *         an error while processing this file.
   **/
  public void load(String filename) throws UnavailableFileException, ClassNotFoundException {
    // FIXME implement serialization method
    try {
      // Read the serialized object from the file
      FileInputStream fileIn = new FileInputStream(filename);
      ObjectInputStream in = new ObjectInputStream(fileIn);
 
      // Deserialize the object
      _hotel = (Hotel) in.readObject();
 
      in.close();
      fileIn.close();
      // System.out.println("Deserialized Person: " + person);
    } catch (IOException i) {
      throw new UnavailableFileException(filename);
  // } catch (ClassNotFoundException e) {
    // TODO Auto-generated catch block
    // e.printStackTrace();
    }
  }
  
  /**
   * Read text input file and initializes the current zoo hotel (which should be empty)
   * with the domain entitiesi representeed in the import file.
   *
   * @param filename name of the text input file
   * @throws ImportFileException if some error happens during the processing of the
   * import file.
   **/
  public void importFile(String filename) throws ImportFileException {
    File file = new File(filename);
    if(!file.exists() || file.length() == 0) {
      throw new ImportFileException(filename);
    }
    try {
      _hotel.importFile(filename);
    } catch (IOException | UnrecognizedEntryException e) {
      throw new ImportFileException(filename, e);
    }
  } 
  
  /**
   * Returns the zoo hotel managed by this instance.
   *
   * @return the current zoo hotel
   **/
  public final Hotel getHotel() {
    return _hotel;
  }

  public String getFileName() {
    return _filename;
  }
}
