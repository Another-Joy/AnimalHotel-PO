package hva.core.exception;

import java.io.Serial;

public class UnknownSpeciesException extends Exception {
  private String _id;

  public UnknownSpeciesException(String id) {
      _id = id;
  }
  
  public String getID(){
      return _id;
  }
}

