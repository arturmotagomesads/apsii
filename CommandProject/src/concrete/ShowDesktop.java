package concrete;

import command.Command;
import receiver.Desktop;

public class ShowDesktop implements Command {
    private final Desktop desktop;

    public ShowDesktop (Desktop desktop) {
    	this.desktop = desktop;
    }
    
    public void execute() {
        desktop.show();
    }

    public void undo() {
       desktop.hide();
    }
}
