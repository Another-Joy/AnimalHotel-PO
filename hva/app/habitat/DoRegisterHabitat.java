package hva.app.habitat;

import hva.app.exception.DuplicateHabitatKeyException;
import hva.core.Hotel;
import hva.core.exception.DuplicateKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
/**
 * Add a new habitat to this zoo hotel.
 **/
class DoRegisterHabitat extends Command<Hotel> {

  DoRegisterHabitat(Hotel receiver) {
    super(Label.REGISTER_HABITAT, receiver);
    addStringField("idHabitat", Prompt.habitatKey());
    addStringField("name", Prompt.habitatName());
    addIntegerField("area", Prompt.habitatArea());
  }
  
  @Override
  protected void execute() throws CommandException {
    try {
      _receiver.registerHabitat(stringField("idHabitat"), stringField("name"), integerField("area"));
    } catch (DuplicateKeyException ex) {
      throw new DuplicateHabitatKeyException(ex.getID());
    }
  }
}
