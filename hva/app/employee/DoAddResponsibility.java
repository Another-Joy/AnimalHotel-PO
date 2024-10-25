package hva.app.employee;

import hva.app.exception.UnknownEmployeeKeyException;
import hva.app.exception.UnknownHabitatKeyException;
import hva.app.exception.UnknownSpeciesKeyException;
import hva.core.Hotel;
import hva.core.enums.Season;
import hva.core.exception.UnknownEmployeeException;
import hva.core.exception.UnknownHabitatException;
import hva.core.exception.UnknownSpeciesException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;


/**
 * Add a new responsability to an employee: species to veterinarians and 
 * habitats to zookeepers.
 **/
class DoAddResponsibility extends Command<Hotel> {

  DoAddResponsibility(Hotel receiver) {
    super(Label.ADD_RESPONSABILITY, receiver);
    addStringField("employee", Prompt.employeeKey());
    addStringField("responsibility", Prompt.responsibilityKey());
  }
  
  @Override
  protected void execute() throws CommandException {
    try{
    _receiver.addResponsibility(stringField("employee"), stringField("responsibility"));
  } catch (UnknownEmployeeException ex) {
    throw new UnknownEmployeeKeyException("employee");
  } catch (UnknownHabitatException ex){
    throw new UnknownHabitatKeyException("responsibility");
  } catch (UnknownSpeciesException ex) {
    throw new UnknownSpeciesKeyException("responsibility");
  }

  }
}
