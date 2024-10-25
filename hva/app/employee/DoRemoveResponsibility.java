package hva.app.employee;

import hva.app.exception.UnknownEmployeeKeyException;
import hva.app.exception.UnknownHabitatKeyException;
import hva.app.exception.UnknownSpeciesKeyException;
import hva.core.Hotel;
import hva.core.exception.UnknownEmployeeException;
import hva.core.exception.UnknownHabitatException;
import hva.core.exception.UnknownSpeciesException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;


/**
 * Remove a given responsability from a given employee of this zoo hotel.
 **/
class DoRemoveResponsibility extends Command<Hotel> {

  DoRemoveResponsibility(Hotel receiver) {
    super(Label.REMOVE_RESPONSABILITY, receiver);
    addStringField("employee", Prompt.employeeKey());
    addStringField("responsibility", Prompt.responsibilityKey());
  }
  
  @Override
  protected void execute() throws CommandException {
    try {
  _receiver.removeResponsibility(stringField("employee"), stringField("responsibility"));
    } catch (UnknownEmployeeException ex) {
      throw new UnknownEmployeeKeyException(ex.getID());
    } catch (UnknownHabitatException ex) {
      throw new UnknownHabitatKeyException(ex.getID());
    } catch (UnknownSpeciesException ex) {
      throw new UnknownSpeciesKeyException(ex.getID());
    }
  }
}
