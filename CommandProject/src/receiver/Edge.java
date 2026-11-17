package receiver;

import java.io.IOException;

public class Edge{
	ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", "start", "msedge.exe");
	Process processo;
	
	public void open() {
		try { processo = pb.start(); }
        catch (IOException e) { e.printStackTrace(); }
    }
	
	public void close() {
		try {
            new ProcessBuilder("taskkill", "/F", "/IM", "msedge.exe").start();
            
            if (processo != null) {
                processo.destroy();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
