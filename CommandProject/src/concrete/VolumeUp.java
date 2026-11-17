package concrete;


import command.Command;
import receiver.Volume;

public class VolumeUp implements Command {
    private final Volume volume;

    public VolumeUp (Volume volume) {
    	this.volume = volume;
    }
    
    public void execute() {
        volume.volumeup();
    }

    public void undo() {
        volume.volumedown();
    }
}
