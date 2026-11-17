package receiver;

import java.io.IOException;

public class Settings {
	ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", "start", "ms-settings:");
	Process processo;
	
	public void open() {
		try { processo = pb.start(); }
        catch (IOException e) { e.printStackTrace(); }
    }
	
	public void close() {
		try {
            new ProcessBuilder("taskkill", "/F", "/IM", "SystemSettings.exe").start();
            
            if (processo != null) {
                processo.destroy();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
