package br.edu.unipe.apsii;

public class Gerente extends FuncionarioCLT{

    private int tamanhoEquipe;
    private double percentualBonus;

    public Gerente(String nome, String matricula, double salarioBase, String dataAdmissao, double valeTransporte, double valeAlimentacao, int tamanhoEquipe, double percentualBonus) {
        super(nome, matricula, salarioBase, dataAdmissao, valeTransporte, valeAlimentacao);
        this.tamanhoEquipe = tamanhoEquipe;
        this.percentualBonus = percentualBonus;
    }

    @Override
    public double calcularSalario() {
        return super.calcularSalario() * (1 + this.percentualBonus);
    }

    @Override
    public double calcularDesconto() {
        if (this.tamanhoEquipe > 10) {
            return super.calcularDesconto() + 100.00;
        }
        return super.calcularDesconto();
    }
}
