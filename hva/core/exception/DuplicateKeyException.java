package hva.core.exception;

public class DuplicateKeyException extends Exception{
    private String _id;

    public DuplicateKeyException(String id) {
        _id = id;
    }
    
    public String getID(){
        return _id;
    }
}
