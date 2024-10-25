package hva.app.main;

import hva.core.HotelManager;

import java.io.IOException;

import hva.app.exception.FileOpenFailedException;
import hva.core.exception.MissingFileAssociationException;
import hva.core.exception.UnavailableFileException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Command to open a file.
 */
class DoOpenFile extends Command<HotelManager> {
  DoOpenFile(HotelManager receiver) {
    super(Label.OPEN_FILE, receiver);
  }

  @Override
  protected final void execute() throws CommandException {
    
    try {
      if(_receiver.getChangeState()){
        Form.requestString(Prompt.saveBeforeExit());
        try{
          _receiver.save();
        } catch (IOException | MissingFileAssociationException | NullPointerException ex) {
          _receiver.saveAs(Form.requestString(Prompt.newSaveAs()));
        }
      }
      _receiver.load(Form.requestString(Prompt.openFile()));
      _receiver.getHotel().setChanges(false);

    }catch (UnavailableFileException | ClassNotFoundException | IOException | MissingFileAssociationException ex){
      throw new FileOpenFailedException(ex);
    }
  }
}
