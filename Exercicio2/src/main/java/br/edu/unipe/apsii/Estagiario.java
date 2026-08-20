package br.edu.unipe.apsii;

public class Estagiario extends Funcionario{

    private double valorBolsaAuxilio;
    private int cargaHorariaSemanal;

    public Estagiario(String nome, String matricula, double valorBolsaAuxilio, String dataAdmissao, int cargaHorariaSemanal) {
        super(nome, matricula, valorBolsaAuxilio, dataAdmissao);
        this.cargaHorariaSemanal = cargaHorariaSemanal;
    }

    @Override
    public double calcularDesconto() {
        return 0.00;
    }
}
