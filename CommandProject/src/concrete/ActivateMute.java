package concrete;

import command.Command;
import receiver.Mute;

public class ActivateMute implements Command {
    private final Mute mute;
    
    public ActivateMute (Mute mute) {
    	this.mute = mute;
    }
    
    public void execute() {
        mute.mute();
    }

    public void undo() {
        mute.unmute();
    }
}
