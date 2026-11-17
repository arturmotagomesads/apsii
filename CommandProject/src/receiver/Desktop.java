package receiver;

import java.io.IOException;

public class Desktop{
    ProcessBuilder pbMinimizar = new ProcessBuilder(
        "powershell.exe", "-Command", "(New-Object -ComObject Shell.Application).MinimizeAll()"
    );
    
    ProcessBuilder pbRestaurar = new ProcessBuilder(
        "powershell.exe", "-Command", "(New-Object -ComObject Shell.Application).UndoMinimizeALL()"
    );

    public void show() {
        try {
            pbMinimizar.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void hide() {
        try {
            pbRestaurar.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
