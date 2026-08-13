package Exercicio01;

public class Aniversariante {
	private String nome;
	private DataAniversario dataAniversario;
	
	public boolean equals(Object outroAniversario){
		if (outroAniversario instanceof Aniversariante){
			Aniversariante outroAniversariante = (Aniversariante) outroAniversario;
			return (outroAniversariante.nome == this.nome && outroAniversariante.dataAniversario == this.dataAniversario);
		}
		return false;
	}

	public Aniversariante(String nome, int dia, int mes) {
		this.nome = nome;
		this.dataAniversario = new DataAniversario(dia, mes);
	}

	public Aniversariante(String nome, DataAniversario dataAniversario) {
		this.nome = nome;
		this.dataAniversario = dataAniversario;
	}
}
