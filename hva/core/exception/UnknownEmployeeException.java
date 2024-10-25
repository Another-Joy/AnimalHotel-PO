package hva.core.exception;

public class UnknownEmployeeException extends Exception{
    private String _id;

    public UnknownEmployeeException(String id) {
        _id = id;
    }
    
    public String getID(){
        return _id;
    }
}
