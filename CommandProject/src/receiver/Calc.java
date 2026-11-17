package receiver;

import java.io.IOException;

public class Calc {
	ProcessBuilder pb = new ProcessBuilder("calc.exe");
	Process processo;
	
	public void abrir() {
		try { processo = pb.start(); }
        catch (IOException e) { e.printStackTrace(); }
    }
	
	public void fechar() {
		try {
            new ProcessBuilder("taskkill", "/F", "/IM", "CalculatorApp.exe").start();
            
            if (processo != null) {
                processo.destroy();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
