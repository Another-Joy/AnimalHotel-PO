package hva.core.exception;

public class UnknownHabitatException extends Exception{
    private String _id;

    public UnknownHabitatException(String id) {
        _id = id;
    }
    
    public String getID(){
        return _id;
    }
}
