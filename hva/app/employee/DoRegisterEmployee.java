package hva.app.employee;

import hva.core.Hotel;
import hva.core.exception.DuplicateKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import hva.app.exception.DuplicateEmployeeKeyException;

/**
 * Adds a new employee to this zoo hotel.
 **/
class DoRegisterEmployee extends Command<Hotel> {

  DoRegisterEmployee(Hotel receiver) {
    super(Label.REGISTER_EMPLOYEE, receiver);
    addStringField("id",Prompt.employeeKey());
    addStringField("name",Prompt.employeeName());
    addStringField("type",Prompt.employeeType());
    
  }
  
  @Override
  protected void execute() throws CommandException {
    try {
   _receiver.registerEmployee(stringField("id"),stringField("name") ,stringField("type"));
  } catch (DuplicateKeyException ex){
    throw new DuplicateEmployeeKeyException(ex.getID());
  }
}
}
