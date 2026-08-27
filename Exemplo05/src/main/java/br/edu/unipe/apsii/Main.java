package br.edu.unipe.apsii;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);
        CD cd = new CD();
        Livro livro = new Livro();

        System.out.println("----Cadastro do livro----");
        System.out.println("Qual o nome do livro?");
        livro.setNome(entrada.nextLine());
        System.out.println("Qual o preco do livro? ");
        livro.setPreco(entrada.nextDouble());
        entrada.nextLine();
        System.out.println("Qual o autor do livro?");
        livro.setAutor(entrada.nextLine());


        System.out.println("\n----Cadastro do CD----");
        System.out.println("Qual o nome do album?");
        cd.setNome(entrada.nextLine());
        System.out.println("Qual o preco do album? ");
        cd.setPreco(entrada.nextDouble());
        System.out.println("Quantas faixas tem no album?");
        cd.setNumFaixas(entrada.nextInt());

        System.out.println("\n" + livro.exibeInformacoes());
        System.out.println("\n" + cd.exibeInformacoes());

        entrada.close();

    }
}