package br.edu.unipe.apsii;

public class TesteFuncionarios {

    public static void main (String [] args){

        Funcionario Ana = new Funcionario("Ana Costa", "F001", 2500, "01/03/2020");
        FuncionarioCLT Bruno = new FuncionarioCLT("Bruno Reis", "C001", 3000, "10/06/2021", 220, 400);
        Gerente Carla = new Gerente("Carla Mendes", "G001", 8000, "15/01/2018", 300, 500, 12, 0.20);
        Estagiario Diego = new Estagiario("Diego Alves", "E001", 1500, "05/05/2024", 20);

        System.out.println(Ana);
        System.out.println(Bruno);
        System.out.println(Carla);
        System.out.println(Diego);
    }
}

