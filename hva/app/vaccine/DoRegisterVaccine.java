package hva.app.vaccine;

import hva.core.Hotel;
import hva.core.exception.DuplicateKeyException;
import hva.core.exception.UnknownSpeciesException;
import hva.app.exception.UnknownSpeciesKeyException;
import hva.app.exception.DuplicateVaccineKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Apply a vaccine to a given animal.
 **/
class DoRegisterVaccine extends Command<Hotel> {

  DoRegisterVaccine(Hotel receiver) {
    super(Label.REGISTER_VACCINE, receiver);
    addStringField("id", Prompt.vaccineKey());
    addStringField("name", Prompt.vaccineName());
    addStringField("speciesList", Prompt.listOfSpeciesKeys());

  }
  @Override
  protected final void execute() throws CommandException {
    try {
      _receiver.registerVaccine(stringField("id"), stringField("name"), stringField("speciesList").replaceAll("\\w", "").split(","));
    } catch (UnknownSpeciesException ex) {
      throw new UnknownSpeciesKeyException(ex.getID());
    } catch (DuplicateKeyException ex) {
      throw new DuplicateVaccineKeyException(ex.getID());
    }
  }
}
