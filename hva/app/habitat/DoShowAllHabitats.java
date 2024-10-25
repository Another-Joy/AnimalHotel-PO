package hva.app.habitat;

import java.util.stream.Collector;
import java.util.stream.Collectors;

import hva.core.Hotel;
import pt.tecnico.uilib.menus.Command;
//FIXME add more imports if needed

/**
 * Show all habitats of this zoo hotel.
 **/
class DoShowAllHabitats extends Command<Hotel> {

  DoShowAllHabitats(Hotel receiver) {
    super(Label.SHOW_ALL_HABITATS, receiver);
  }
  
  @Override
  protected void execute() {
    
   _display.popup(_receiver.showHabitats().stream().sorted().collect(Collectors.toList())); 
  }
}
