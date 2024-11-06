package BubbleSorting;

import Models.Empresa;

import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class OrdernarEmpresas {
    public List<Empresa> lerEmpresas() {
        List<Empresa> empresas = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader("src/Empresas_BubbleSort.txt"))){
            String line;
            while ((line = br.readLine()) != null){
                String[] data = line.split(" \\| ");
                String nomeEmpresa = data[0];
                String inscricaoEmpresa = data[1];
                Double valorMercado = Double.parseDouble(data[2].replaceAll(",", "").trim());
                empresas.add(new Empresa(nomeEmpresa, inscricaoEmpresa, valorMercado));
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao abrir o arquivo: " + e.getMessage());
        }
        return empresas;
    }

    public void bubbleSorting(List<Empresa> empresas) {
        int n = empresas.size();
        for (int i = 0; i < n - 1; i++) { // Percorre as comparações
            for (int j = 0; j < n - i - 1; j++) {
                if (empresas.get(j).getValorMercado() < empresas.get(j + 1).getValorMercado()) {
                    Empresa temporarioVar = empresas.get(j);
                    empresas.set(j, empresas.get(j + 1));
                    empresas.set(j + 1, temporarioVar);
                }
            }
        }
    }
    public void escreverEmpresas(List<Empresa> empresas) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/EmpresasFiltradas.txt"))){
            for (Empresa empresa : empresas) {
                bw.write(empresa.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Algo deu errado ao escrever o arquivo: " + e.getMessage());
        }
    }
}
