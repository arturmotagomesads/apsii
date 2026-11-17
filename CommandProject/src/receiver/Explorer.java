package receiver;

import java.io.IOException;

public class Explorer{

	public void open() {
		try {
			new ProcessBuilder("explorer.exe").start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		String script = "(New-Object -ComObject Shell.Application).Windows() "
				+ "| Where-Object { $_.FullName -like '*\\explorer.exe' } "
				+ "| ForEach-Object { $_.Quit() }";
		try {
			new ProcessBuilder("powershell", "-NoProfile", "-Command", script).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}