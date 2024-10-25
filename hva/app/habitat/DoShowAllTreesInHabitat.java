package hva.app.habitat;

import hva.app.exception.UnknownHabitatKeyException;
import hva.core.Hotel;
import hva.core.exception.UnknownHabitatException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Show all trees in a given habitat.
 **/
class DoShowAllTreesInHabitat extends Command<Hotel> {

  DoShowAllTreesInHabitat(Hotel receiver) {
    super(Label.SHOW_TREES_IN_HABITAT, receiver);
    addStringField("idHabitat", Prompt.habitatKey());

  }
  
  @Override
  protected void execute() throws CommandException {
    try {
      // _receiver.showAllTrees
    } catch (UnknownHabitatException ex) {
      throw new UnknownHabitatKeyException(ex.getID())
    }
  }
}
