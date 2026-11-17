package concrete;


import command.Command;
import receiver.Volume;

public class VolumeDown implements Command {
    private final Volume volume;

    public VolumeDown (Volume volume) {
    	this.volume = volume;
    }
    
    public void execute() {
        volume.volumedown();
    }

    public void undo() {
        volume.volumeup();
    }
}
