package receiver;

import java.io.IOException;

public class Mute {
    private ProcessBuilder pb = new ProcessBuilder(
        "powershell.exe", "-Command", "(New-Object -ComObject WScript.Shell).SendKeys([char]173)"
    );
    
    public void mute() {
        try {
            pb.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void unmute() {
        try {
            pb.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
