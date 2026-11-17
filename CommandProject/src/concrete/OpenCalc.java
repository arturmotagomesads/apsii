package concrete;

import receiver.Calc;

import command.Command;

public class OpenCalc implements Command {
	private final Calc calc;
	
	public OpenCalc (Calc calc) {
		this.calc = calc;
	}
	
	public void execute() {
		calc.abrir();
	}
	
	public void undo() {
		calc.fechar();
    }
}
