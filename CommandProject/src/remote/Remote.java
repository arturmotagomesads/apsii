package remote;

import java.util.HashMap;
import java.util.Map;

import command.Command;
import command.NoCommand;

public class Remote {
	Map <Integer, Command> commands;
	Command undocommand;
	
	public Remote() {
		commands = new HashMap<>();
		Command noCommand = new NoCommand();
		
		for (int i = 112; i < 122; i++) {
			commands.put(i, noCommand);
		}
		undocommand = noCommand;
	}
	
	public void setCommand(int slot, Command command) {
		if (commands.containsKey(slot)) {
			commands.put(slot, command);
		}
	}
	
	public void ButtonPushed(int slot) {
		commands.get(slot).execute();
		undocommand = commands.get(slot);
	}
	
	public void undoButtonPushed() {
		undocommand.undo();
	}
}
