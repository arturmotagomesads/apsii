package concrete;

import command.Command;
import receiver.Settings;

public class OpenSettings implements Command {
	private final Settings settings;
	
	public OpenSettings (Settings settings) {
		this.settings = settings;
	}
	
	public void execute() {
		settings.open();
    }
	
	public void undo() {
		settings.close();
    }
}
