public class Principal {

    public static void main (String [] args){

        Veiculo v1 = new Veiculo("Corolla", 2000);
        Carro c1 = new Carro("Corolla", 2000, 4);
        Moto m1 = new Moto("Biz", 2002, 100);

        System.out.println(v1.ImprimirDetalhes());
        System.out.println(c1.ImprimirDetalhes());
        System.out.println(m1.ImprimirDetalhes());
    }
}
