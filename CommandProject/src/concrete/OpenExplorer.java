package concrete;

import command.Command;
import receiver.Explorer;

public class OpenExplorer implements Command {
	private final Explorer explorer;
	
	public OpenExplorer (Explorer explorer) {
		this.explorer = explorer;
	}

	public void execute() {
		explorer.open();
	}

	public void undo() {
		explorer.close();
	}
}