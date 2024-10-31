import BubbleSorting.OrdernarEmpresas;
import Models.Empresa;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        OrdernarEmpresas ordenarEmpresas = new OrdernarEmpresas();

        List<Empresa> empresas = ordenarEmpresas.lerEmpresas();

        // Ordena as empresas
        ordenarEmpresas.bubbleSorting(empresas);

        // Exibe apenas as empresas ordenadas
        for (Empresa empresa : empresas) {
            System.out.println(empresa);
        }
    }
}
