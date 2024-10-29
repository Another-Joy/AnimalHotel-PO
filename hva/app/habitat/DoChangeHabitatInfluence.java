package hva.app.habitat;

import hva.app.exception.UnknownHabitatKeyException;
import hva.app.exception.UnknownSpeciesKeyException;
import hva.core.Hotel;
import hva.core.exception.UnknownHabitatException;
import hva.core.exception.UnknownSpeciesException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Associate (positive or negatively) a species to a given habitat.
 **/
class DoChangeHabitatInfluence extends Command<Hotel> {

  DoChangeHabitatInfluence(Hotel receiver) {
    super(Label.CHANGE_HABITAT_INFLUENCE, receiver);
    addStringField("idHabitat", Prompt.habitatKey());
    addStringField("idSpecies", hva.app.animal.Prompt.speciesKey());
    addOptionField("influence", Prompt.habitatInfluence(),"POS", "NEG", "NEU");
  }
  
  @Override
  protected void execute() throws CommandException {
    try {
    _receiver.changeHabitatInfluence(stringField("idHabitat"), stringField("idSpecies"), optionField("influence"));
  } catch (UnknownHabitatException ex) {
    throw new UnknownHabitatKeyException(ex.getID());
  } catch (UnknownSpeciesException ex) {
      throw new UnknownSpeciesKeyException(ex.getID());
}
}
}
