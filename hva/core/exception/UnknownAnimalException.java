package hva.core.exception;

public class UnknownAnimalException extends Exception{
    private String _id;

    public UnknownAnimalException(String id) {
        _id = id;
    }
    
    public String getID(){
        return _id;
    }
}
