package br.edu.unipe.apsii;

public class Livro extends Produto implements InfoGerais{

    private String autor;

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    @Override
    public String exibeInformacoes() {
        return "Nome do livro: " + getNome() + "\nPreço: " + getPreco() + "\nAutor do livro: " + getAutor();
    }
}
