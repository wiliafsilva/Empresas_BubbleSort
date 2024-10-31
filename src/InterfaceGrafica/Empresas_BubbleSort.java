package InterfaceGrafica;

// Importando classes necessárias para a ordenação, modelos, e interface gráfica.
import BubbleSorting.OrdernarEmpresas;
import Models.Empresa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

// Classe que estende JFrame para criar a interface gráfica
public class Empresas_BubbleSort extends JFrame {
    // Definição de componentes da interface gráfica
    private JTextArea textArea; // Área de texto para exibir as empresas
    private JButton downloadButton; // Botão para baixar a lista de empresas
    private OrdernarEmpresas ordenarEmpresas; // Objeto para gerenciar a ordenação das empresas

    // Construtor da classe
    public Empresas_BubbleSort() {
        // Inicializa o objeto de ordenação e lê as empresas
        ordenarEmpresas = new OrdernarEmpresas();
        List<Empresa> empresas = ordenarEmpresas.lerEmpresas(); // Lê a lista de empresas
        ordenarEmpresas.bubbleSorting(empresas); // Ordena as empresas usando bubble sort

        // Configurações da janela
        setTitle("Empresas Ordenadas"); // Título da janela
        setSize(1080, 720); // Tamanho da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Comportamento ao fechar a janela
        setLayout(new BorderLayout()); // Layout da janela

        // Configuração da área de texto
        textArea = new JTextArea();
        textArea.setEditable(false); // A área de texto não é editável
        textArea.setText(getEmpresasAsString(empresas)); // Popula a área de texto com as empresas ordenadas
        add(new JScrollPane(textArea), BorderLayout.CENTER); // Adiciona a área de texto com rolagem

        // Configuração do botão de download
        downloadButton = new JButton("Baixar em formato TXT");
        downloadButton.addActionListener(new ActionListener() { // Adiciona um listener ao botão
            @Override
            public void actionPerformed(ActionEvent e) {
                baixarEmpresas(empresas); // Chama o método para baixar as empresas
            }
        });
        add(downloadButton, BorderLayout.SOUTH); // Adiciona o botão na parte inferior da janela
    }

    // Método que converte a lista de empresas em uma string
    private String getEmpresasAsString(List<Empresa> empresas) {
        StringBuilder sb = new StringBuilder(); // StringBuilder para construir a string
        for (Empresa empresa : empresas) {
            sb.append(empresa.toString()).append("\n"); // Adiciona cada empresa à string
        }
        return sb.toString(); // Retorna a string completa
    }

    // Método para baixar a lista de empresas em um arquivo TXT
    private void baixarEmpresas(List<Empresa> empresas) {
        // Usar JFileChooser para selecionar o local de salvamento
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Salvar como");
        fileChooser.setSelectedFile(new File("EmpresasFiltradas.txt")); // Nome padrão do arquivo

        int userSelection = fileChooser.showSaveDialog(this); // Abre a janela de salvamento
        if (userSelection == JFileChooser.APPROVE_OPTION) { // Se o usuário selecionou um arquivo
            File fileToSave = fileChooser.getSelectedFile();
            System.out.println("Caminho do arquivo: " + fileToSave.getAbsolutePath()); // Exibe o caminho do arquivo

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileToSave))) { // Tenta escrever no arquivo
                for (Empresa empresa : empresas) {
                    bw.write(empresa.toString()); // Escreve cada empresa no arquivo
                    bw.newLine(); // Adiciona uma nova linha
                }
                // Mensagem de sucesso
                JOptionPane.showMessageDialog(this, "Arquivo baixado com sucesso em: " + fileToSave.getAbsolutePath());
            } catch (IOException e) {
                // Mensagem de erro se ocorrer uma exceção
                JOptionPane.showMessageDialog(this, "Erro ao baixar o arquivo: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // Mensagem se a operação de salvamento for cancelada
            JOptionPane.showMessageDialog(this, "Operação de salvamento cancelada.", "Informação", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // Método principal para iniciar a aplicação
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Empresas_BubbleSort gui = new Empresas_BubbleSort(); // Cria a interface gráfica
            gui.setVisible(true); // Torna a janela visível
        });
    }
}
