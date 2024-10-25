package hva.app.vaccine;

import hva.core.Hotel;
import hva.core.exception.UnknownAnimalException;
import hva.core.exception.UnknownEmployeeException;
import hva.core.exception.UnknownVaccineException;
import hva.core.exception.WrongResponsibilityException;
import hva.app.exception.UnknownAnimalKeyException;
import hva.app.exception.UnknownVaccineKeyException;
import hva.app.exception.UnknownVeterinarianKeyException;
import hva.app.exception.VeterinarianNotAuthorizedException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Vaccinate by a given veterinarian a given animal with a given vaccine.
 **/
class DoVaccinateAnimal extends Command<Hotel> {
  DoVaccinateAnimal(Hotel receiver) {
    super(Label.VACCINATE_ANIMAL, receiver);
    addStringField("idVacina",Prompt.vaccineKey());
    addStringField("idVet", Prompt.veterinarianKey());
    addStringField("idAnimal", hva.app.animal.Prompt.animalKey());
}

  @Override
  protected final void execute() throws CommandException {
    try {
      _receiver.vaccinate(stringField("idVacina"),stringField("idVet"), stringField("idAnimal"));
    } catch (UnknownEmployeeException ex) {
      throw new UnknownVeterinarianKeyException(ex.getID());
    } 
    catch (WrongResponsibilityException ex) {
      throw new VeterinarianNotAuthorizedException(ex.getEmployee(),  ex.getResponsibility());
    } catch (UnknownVaccineException ex) {
      throw new UnknownVaccineKeyException(ex.getID());
    } catch (UnknownAnimalException ex) {
      throw new UnknownAnimalKeyException(ex.getID());
    }
  }
}
