package hva.app.animal;

import hva.app.exception.UnknownAnimalKeyException;
import hva.app.exception.UnknownHabitatKeyException;
import hva.core.Hotel;
import hva.core.exception.UnknownAnimalException;
import hva.core.exception.UnknownHabitatException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;


/**
 * Transfers a given animal to a new habitat of this zoo hotel.
 */
class DoTransferToHabitat extends Command<Hotel> {

  DoTransferToHabitat(Hotel hotel) {
    super(Label.TRANSFER_ANIMAL_TO_HABITAT, hotel);
    addStringField("animal", Prompt.animalKey());
    addStringField("habitat", hva.app.habitat.Prompt.habitatKey());
  }
  
  @Override
  protected final void execute() throws CommandException {
    try{
    _receiver.transferAnimal(stringField("animal"), stringField("habitat"));
    } catch (UnknownAnimalException ex) {
      throw new UnknownAnimalKeyException("animal");
    } catch (UnknownHabitatException ex) {
      throw new UnknownHabitatKeyException("habitat");
    }
  }

}
