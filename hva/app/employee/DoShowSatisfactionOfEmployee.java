package hva.app.employee;

import hva.app.exception.UnknownEmployeeKeyException;
import hva.core.Hotel;
import hva.core.exception.UnknownEmployeeException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Show the satisfaction of a given employee.
 **/
class DoShowSatisfactionOfEmployee extends Command<Hotel> {

  DoShowSatisfactionOfEmployee(Hotel receiver) {
    super(Label.SHOW_SATISFACTION_OF_EMPLOYEE, receiver);
    addStringField("employeeId", Prompt.employeeKey());
  }
  
  @Override
  protected void execute() throws CommandException {
    int satis = 0;
    try {
      satis = (int) Math.round(_receiver.getEmployeeSatisfaction(stringField("employeeId")));
    } catch (UnknownEmployeeException ex) {
      throw new UnknownEmployeeKeyException(ex.getID());
    }
    _display.popup(satis);
  }
}
