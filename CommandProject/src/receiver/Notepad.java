package receiver;

import java.io.IOException;

public class Notepad{
	ProcessBuilder pb = new ProcessBuilder("notepad.exe");
	Process processo;
	
	public void open() {
		try { processo = pb.start(); }
        catch (IOException e) { e.printStackTrace(); }
    }
	
	public void close() {
		try {
            new ProcessBuilder("taskkill", "/F", "/IM", "notepad.exe").start();
            
            if (processo != null) {
                processo.destroy();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
