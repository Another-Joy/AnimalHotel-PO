package hva.app.habitat;

import hva.app.exception.UnknownHabitatKeyException;
import hva.core.Hotel;
import hva.core.exception.UnknownHabitatException;
import pt.tecnico.uilib.forms.Field;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import java.util.stream.Collectors;
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
      // _receiver.showTrees(stringField("idHabitat"));
      _display.popup(_receiver.showTrees(stringField("idHabitat")).stream().sorted().collect(Collectors.toList()));
    } catch (UnknownHabitatException ex) {
      throw new UnknownHabitatKeyException(ex.getID());
    }
  }
}
