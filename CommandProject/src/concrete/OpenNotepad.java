package concrete;

import command.Command;
import receiver.Notepad;

public class OpenNotepad implements Command{
	private final Notepad notepad;
	
	public OpenNotepad (Notepad notepad) {
		this.notepad = notepad;
	}
	
	public void execute() {
		notepad.open();
    }
	
	public void undo() {
		notepad.close();
    }
}
