package receiver;

import java.io.IOException;

public class Volume {
    ProcessBuilder pbUp = new ProcessBuilder(
        "powershell.exe", "-Command", "(New-Object -ComObject WScript.Shell).SendKeys([char]175)"
    );
    ProcessBuilder pbDown = new ProcessBuilder(
        "powershell.exe", "-Command", "(New-Object -ComObject WScript.Shell).SendKeys([char]174)"
    );

    public void volumeup() {
        try {
            pbUp.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void volumedown() {
        try {
            pbDown.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
