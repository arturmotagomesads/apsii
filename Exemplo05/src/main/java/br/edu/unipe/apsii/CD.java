package br.edu.unipe.apsii;

public class CD extends Produto implements InfoGerais{

    private int numFaixas;

    public int getNumFaixas() {
        return numFaixas;
    }

    public void setNumFaixas(int numFaixas) {
        this.numFaixas = numFaixas;
    }

    @Override
    public String exibeInformacoes() {
        return "Nome do album: " + getNome() + "\nPreço: " + getPreco() + "\nNumero de Faixas: " + getNumFaixas();
    }
}
