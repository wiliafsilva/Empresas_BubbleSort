package InterfaceGrafica;

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

public class EmpresaGUI extends JFrame {
    private JTextArea textArea;
    private JButton downloadButton;
    private OrdernarEmpresas ordenarEmpresas;

    public EmpresaGUI() {
        ordenarEmpresas = new OrdernarEmpresas();
        List<Empresa> empresas = ordenarEmpresas.lerEmpresas();
        ordenarEmpresas.bubbleSorting(empresas);

        setTitle("Empresas Ordenadas");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setText(getEmpresasAsString(empresas));
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        downloadButton = new JButton("Baixar em formato TXT");
        downloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                baixarEmpresas(empresas);
            }
        });
        add(downloadButton, BorderLayout.SOUTH);
    }

    private String getEmpresasAsString(List<Empresa> empresas) {
        StringBuilder sb = new StringBuilder();
        for (Empresa empresa : empresas) {
            sb.append(empresa.toString()).append("\n");
        }
        return sb.toString();
    }

    private void baixarEmpresas(List<Empresa> empresas) {
        // Usar JFileChooser para selecionar o local de salvamento
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Salvar como");
        fileChooser.setSelectedFile(new File("EmpresasFiltradas.txt")); // Nome padrão do arquivo

        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            System.out.println("Caminho do arquivo: " + fileToSave.getAbsolutePath());

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileToSave))) {
                for (Empresa empresa : empresas) {
                    bw.write(empresa.toString());
                    bw.newLine();
                }
                JOptionPane.showMessageDialog(this, "Arquivo baixado com sucesso em: " + fileToSave.getAbsolutePath());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Erro ao baixar o arquivo: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Operação de salvamento cancelada.", "Informação", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            EmpresaGUI gui = new EmpresaGUI();
            gui.setVisible(true);
        });
    }
}
