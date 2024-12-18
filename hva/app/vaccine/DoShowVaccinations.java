package hva.app.vaccine;

import hva.core.Hotel;
import pt.tecnico.uilib.menus.Command;
import java.util.stream.Collectors;


/**
 * Show all applied vacines by all veterinarians of this zoo hotel.
 **/
class DoShowVaccinations extends Command<Hotel> {

  DoShowVaccinations(Hotel receiver) {
    super(Label.SHOW_VACCINATIONS, receiver);
  }
  
  @Override
  protected final void execute() {
    _display.popup(_receiver.showRegistry().stream().sorted().collect(Collectors.toList()));
  }
}
