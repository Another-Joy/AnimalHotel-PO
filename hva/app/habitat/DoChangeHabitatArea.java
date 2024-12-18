package hva.app.habitat;

import hva.app.exception.UnknownHabitatKeyException;
import hva.core.Hotel;
import hva.core.exception.UnknownHabitatException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Change the area of a given habitat.
 **/
class DoChangeHabitatArea extends Command<Hotel> {

  DoChangeHabitatArea(Hotel receiver) {
    super(Label.CHANGE_HABITAT_AREA, receiver);
    addStringField("id", Prompt.habitatKey());  
    addIntegerField("area", Prompt.habitatArea());
}
  
  @Override
  protected void execute() throws CommandException {
    try {
      _receiver.setArea(stringField("id"), integerField("area"));
    } catch (UnknownHabitatException ex) {
      throw new UnknownHabitatKeyException(ex.getID());
    }
  }
}
