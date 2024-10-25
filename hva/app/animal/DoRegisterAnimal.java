package hva.app.animal;

import hva.app.exception.DuplicateAnimalKeyException;
import hva.app.exception.UnknownHabitatKeyException;
import hva.app.exception.UnknownSpeciesKeyException;
import hva.core.Hotel;
import hva.core.exception.DuplicateKeyException;
import hva.core.exception.UnknownHabitatException;
import hva.core.exception.UnknownSpeciesException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;


/**
 * Register a new animal in this zoo hotel.
 */
class DoRegisterAnimal extends Command<Hotel> {

  DoRegisterAnimal(Hotel receiver) {
    super(Label.REGISTER_ANIMAL, receiver);
    
    addStringField("id",Prompt.animalKey());
    addStringField("name",Prompt.animalName());
    addStringField("idHabitat",hva.app.habitat.Prompt.habitatKey());
    addStringField("idSpecies",Prompt.speciesKey());
  }
  
  @Override
  protected final void execute() throws CommandException{
    try{
    _receiver.registerAnimal(stringField("id"), stringField("name"), stringField("idHabitat"), stringField("idSpecies"));
  } catch (UnknownSpeciesException ex){
    throw new UnknownSpeciesKeyException(ex.getID());
  } catch (UnknownHabitatException ex){
    throw new UnknownHabitatKeyException(ex.getID());
  } catch (DuplicateKeyException ex){
    throw new DuplicateAnimalKeyException(ex.getID());
  }

}
}
