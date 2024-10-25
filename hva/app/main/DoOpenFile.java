package hva.app.main;

import hva.core.HotelManager;
import hva.app.exception.FileOpenFailedException;
import hva.core.exception.UnavailableFileException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Command to open a file.
 */
class DoOpenFile extends Command<HotelManager> {
  DoOpenFile(HotelManager receiver) {
    super(Label.OPEN_FILE, receiver);
    addStringField("filename", Prompt.openFile());
  }

  @Override
  protected final void execute() throws CommandException {
    var filename = stringField("filename");
    try {
      if(_receiver.getChangeState()){
        
      }
      _receiver.load(filename);

    }catch (UnavailableFileException | ClassNotFoundException ex){
      throw new FileOpenFailedException(ex);
    }
  }
}
