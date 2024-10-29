package hva.app.habitat;

import hva.app.exception.DuplicateTreeKeyException;
import hva.app.exception.UnknownHabitatKeyException;
import hva.core.Hotel;
import hva.core.exception.DuplicateKeyException;
import hva.core.exception.UnknownHabitatException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Add a new tree to a given habitat of the current zoo hotel.
 **/
class DoAddTreeToHabitat extends Command<Hotel> {

  DoAddTreeToHabitat(Hotel receiver) {
    super(Label.ADD_TREE_TO_HABITAT, receiver);
    addStringField("hid", Prompt.habitatKey());
    addStringField("tid", Prompt.treeKey());
    addStringField("name", Prompt.treeName());
    addIntegerField("age", Prompt.treeAge());
    addIntegerField("dificulty", Prompt.treeDifficulty());
    addStringField("treeType", Prompt.treeType());
  }
  
  @Override
  protected void execute() throws CommandException {
    try{
    _display.popup(_receiver.plantTree(stringField("hid"), stringField("tid"), stringField("name"), stringField("treeType"), integerField("age"), integerField("dificulty")));
    } catch (DuplicateKeyException ex) {
      throw new DuplicateTreeKeyException(ex.getID());
    } catch (UnknownHabitatException ex) {
      throw new UnknownHabitatKeyException(ex.getID());
    }

  }
}
