package concrete;


import command.Command;
import receiver.Paint;

public class OpenPaint implements Command {
	private final Paint paint;
	
	public OpenPaint (Paint paint) {
		this.paint = paint;
	}
	
	public void execute() {
		paint.open();
    }
	
	public void undo() {
		paint.close();
    }
}
