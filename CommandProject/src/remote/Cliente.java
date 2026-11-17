package remote;

import ui.*;


import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;

import javax.swing.SwingUtilities;

import concrete.*;
import receiver.*;

public class Cliente {

	public static void main(String[] args) {
		Remote remote = new Remote();
		
		Edge edge = new Edge();
		Paint paint = new Paint();
		Calc calculadora = new Calc();
		Notepad notepad = new Notepad();
		Settings settings = new Settings();
		Volume volumeup = new Volume();
		Volume volumedown = new Volume();
		Mute mute = new Mute();
		Desktop desktop = new Desktop();
		Explorer explorer = new Explorer();
		
		OpenEdge openEdge = new OpenEdge(edge);
		OpenPaint openPaint = new OpenPaint(paint);
		OpenCalc openCalculadora = new OpenCalc(calculadora);
		OpenNotepad openNotepad = new OpenNotepad(notepad);
		OpenSettings openSettings = new OpenSettings(settings);
		VolumeUp volume_up = new VolumeUp(volumeup);
		VolumeDown volume_down = new VolumeDown(volumedown);
		ActivateMute activateMute = new ActivateMute(mute);
		ShowDesktop showDesktop = new ShowDesktop(desktop);
		OpenExplorer openExplorer = new OpenExplorer(explorer);
		
		remote.setCommand(KeyEvent.VK_F1, openExplorer);
		remote.setCommand(KeyEvent.VK_F2, openEdge);
		remote.setCommand(KeyEvent.VK_F3, openPaint);
		remote.setCommand(KeyEvent.VK_F4, openCalculadora);
		remote.setCommand(KeyEvent.VK_F5, openNotepad);
		remote.setCommand(KeyEvent.VK_F6, openSettings);
		remote.setCommand(KeyEvent.VK_F7, volume_up);
		remote.setCommand(KeyEvent.VK_F8, volume_down);
		remote.setCommand(KeyEvent.VK_F9, activateMute);
		remote.setCommand(KeyEvent.VK_F10, showDesktop);
		
		SwingUtilities.invokeLater(() -> {
			Janela janela = new Janela();
			iniciarDetectorDeTeclas(remote, janela);
			janela.setVisible(true);
		});
	}
	
	private static void iniciarDetectorDeTeclas(Remote remote, Janela janela) {
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(e -> {
			if (e.getID() != KeyEvent.KEY_PRESSED) {
				return false;
			}

			int code = e.getKeyCode();
			boolean ehF = code >= KeyEvent.VK_F1 && code <= KeyEvent.VK_F12;

			if (!ehF || e.isAltDown()) {
				return false;
			}
			
	        if (code == KeyEvent.VK_F12) {
	            janela.dispose();      
	            System.exit(0);       
	            return true;
	        }
	        
	        if (code == KeyEvent.VK_F11) {
	            remote.undoButtonPushed();       
	            return true;
	        }

			janela.setUltimaTecla(code - KeyEvent.VK_F1 + 1);
			remote.ButtonPushed(code);

			return true;
		});
	}
	
}