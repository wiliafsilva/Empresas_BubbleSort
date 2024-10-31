import BubbleSorting.OrdernarEmpresas;
import Models.Empresa;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        OrdernarEmpresas ordenarEmpresas = new OrdernarEmpresas();

        List<Empresa> empresas = ordenarEmpresas.lerEmpresas();

        System.out.println("Empresas carregadas: ");
        for (Empresa empresa : empresas) {
            System.out.println(empresa);
        }

        ordenarEmpresas.bubbleSorting(empresas);

        System.out.println("\nEmpresas ordenadas pelo valor de mercado:"); // Botei pra debuggar
        for (Empresa empresa : empresas) {
            System.out.println(empresa);
        }

        ordenarEmpresas.escreverEmpresas(empresas);

        System.out.println("\nEmpresas ordenadas foram gravadas no arquivo EmpresasFiltradas.txt");
    }
}
