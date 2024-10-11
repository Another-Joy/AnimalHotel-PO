package hva.core.exception;

public class WrongResponsibilityException extends Exception{
    private String _employee;
    private String _responsability;

    public WrongResponsibilityException(String employee, String responsibility) {
        _employee = employee;
        _responsability = responsibility;
    }
    
    public String getEmployee(){
        return _employee;
    }
    public String getResponsibility(){
        return _responsability;
    }
}
