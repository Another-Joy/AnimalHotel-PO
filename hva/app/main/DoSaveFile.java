package hva.app.main;

import hva.core.HotelManager;
import hva.core.exception.MissingFileAssociationException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import java.io.FileNotFoundException;
import java.io.IOException;
// FIXME add more imports if needed

/**
 * Save to file under current name (if unnamed, query for name).
 */
class DoSaveFile extends Command<HotelManager> {
  DoSaveFile(HotelManager receiver) {
    super(Label.SAVE_FILE, receiver, r -> r.getHotel() != null);
  }

  @Override
  protected final void execute() throws CommandException{
     try{
      String fileName = _receiver.getFileName();
      if(fileName == null) {
        _receiver.saveAs(Form.requestString(Prompt.newSaveAs()));
      }
      _receiver.save();
      _receiver.getHotel().setChanges(false);

     } catch (FileNotFoundException ex) {
      _display.popup(ex.getMessage());
     } catch (MissingFileAssociationException ex) {
      _display.popup(ex.getMessage());
     } catch (IOException ex) {
      _display.popup(ex.getMessage());
     }
  }
}
// 