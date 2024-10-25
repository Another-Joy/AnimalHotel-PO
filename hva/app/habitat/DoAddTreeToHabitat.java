package hva.app.habitat;

import hva.app.exception.DuplicateTreeKeyException;
import hva.core.Hotel;
import hva.core.exception.DuplicateKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Add a new tree to a given habitat of the current zoo hotel.
 **/
class DoAddTreeToHabitat extends Command<Hotel> {

  DoAddTreeToHabitat(Hotel receiver) {
    super(Label.ADD_TREE_TO_HABITAT, receiver);
    addStringField("id", Prompt.treeKey());
    addStringField("name", Prompt.treeName());
    addStringField("treeType", Prompt.treeType());
    addIntegerField("age", Prompt.treeAge());
    addIntegerField("dificulty", Prompt.treeDifficulty());
  }
  
  @Override
  protected void execute() throws CommandException {
    try{
    _receiver.createTree(stringField("id"), stringField("name"), stringField("treeType"), integerField("age"), integerField("deficulty"));
    } catch (DuplicateKeyException ex) {
      throw new DuplicateTreeKeyException("id");
    }
  }
}
