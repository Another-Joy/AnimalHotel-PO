package hva.app.animal;

import java.util.stream.Collectors;

import hva.core.Hotel;
import pt.tecnico.uilib.menus.Command;
//FIXME add more imports if needed

/**
 * Show all animals registered in this zoo hotel.
 */
class DoShowAllAnimals extends Command<Hotel> {

  DoShowAllAnimals(Hotel receiver) {
    super(Label.SHOW_ALL_ANIMALS, receiver);
  }
  
  @Override
  protected final void execute() {
    _display.popup(_receiver.showAnimals().stream().sorted().collect(Collectors.toList()));
  }
}
