package hva.core.exception;

public class UnknownVaccineException extends Exception{
    private String _id;

    public UnknownVaccineException(String id) {
        _id = id;
    }
    
    public String getID(){
        return _id;
    }
}
