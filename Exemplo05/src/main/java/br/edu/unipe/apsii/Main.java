package br.edu.unipe.apsii;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);
        CD cd = new CD();

        System.out.println("Qual o nome do album?");
        cd.setNome(entrada.nextLine());
        System.out.println("Qual o preco do album? ");
        cd.setPreco(entrada.nextDouble());
        System.out.println("Quantas faixas tem no album?");
        cd.setNumFaixas(entrada.nextInt());

        System.out.println(cd.exibeInformacoes());

        entrada.close();

    }
}