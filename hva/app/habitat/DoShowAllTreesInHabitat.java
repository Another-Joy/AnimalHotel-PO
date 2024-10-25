package hva.app.habitat;

import hva.app.exception.UnknownHabitatKeyException;
import hva.core.Hotel;
import hva.core.exception.UnknownHabitatException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import java.util.stream.Collectors;


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
      _display.popup(_receiver.showTrees(stringField("idHabitat")).stream().sorted().collect(Collectors.toList()));
    } catch (UnknownHabitatException ex) {
      throw new UnknownHabitatKeyException(ex.getID());
    }
  }
}
