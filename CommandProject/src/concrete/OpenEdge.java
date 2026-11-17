package concrete;

import command.Command;
import receiver.Edge;

public class OpenEdge implements Command {
	private final Edge edge;
	
	public OpenEdge (Edge edge) {
		this.edge = edge;
	}
	
	public void execute() {
		edge.open();
    }
	
	public void undo() {
		edge.close();
    }
}
