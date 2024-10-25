package hva.app.animal;

import hva.app.exception.UnknownAnimalKeyException;
import hva.core.Hotel;
import hva.core.exception.UnknownAnimalException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
/**
 * Shows the satisfaction of a given animal.
 */
class DoShowSatisfactionOfAnimal extends Command<Hotel> {

  DoShowSatisfactionOfAnimal(Hotel receiver) {
    super(Label.SHOW_SATISFACTION_OF_ANIMAL, receiver);
    addStringField("animalId", Prompt.animalKey());
  }
  
  @Override
  protected final void execute() throws CommandException {
    try {
      _receiver.getAnimalSatisfaction(stringField("animalId"));
    } catch (UnknownAnimalException ex) {
      throw new UnknownAnimalKeyException(ex.getID());
    }
  }
}
