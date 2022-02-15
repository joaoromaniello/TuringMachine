package views;

import data.Machine;
import service.InputFileService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.SwingConstants.CENTER;

public class InitialView extends JFrame implements ActionListener {

    InputFileService inputFileService = new InputFileService();
    JLabel titleLabel = new JLabel("Maquina de turing");
    JButton openFileButton = new JButton("Arquivo...");
    JButton aboutButton = new JButton("Sobre...");

    public InitialView() {
        setupFrame();
        setupTitle();
        setupFileButton();
        setupAboutButton();
    }

    void setupFrame() {
        setLayout(null);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setTitle("Introdução");
        setLocation(760, 340);
    }

    private void setupTitle() {
        titleLabel.setHorizontalAlignment(CENTER);
        titleLabel.setFont(new Font(null, Font.BOLD, 20));
        titleLabel.setBounds(0, 20, 400, 20);
        add(titleLabel);
    }

    private void setupFileButton() {
        openFileButton.setBounds(220, 70, 120, 50);
        openFileButton.addActionListener(this);
        add(openFileButton);
    }

    private void setupAboutButton() {
        aboutButton.setBounds(60, 70, 120, 50);
        aboutButton.addActionListener(this);
        add(aboutButton);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == openFileButton) {
            try {
                Machine machine = inputFileService.parseJSON();
                //Palavra lida
                dispose();
                new views.StepView(machine);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else if (event.getSource() == aboutButton) {
            JOptionPane.showMessageDialog(this, "\n\nO programa tem a finalidade de simular o " +
                            "funcionamento de uma maquina de turing .\nPara tal, deve-se " +
                            "inserir a 7-tupla que o define em um arquivo json que contenha os campos como no exemplo:\n\n" +
                            "{\n" +
                            "   \"estados\": [\"q0\", ..., \"qn\"],\n" +
                            "   \"alfabeto\": \"01...\",\n" +
                            "   \"alfabetoFita\": \"Z0...\",\n" +
                            "   \"regras\": [\n" +
                            "       {\"estadoPartida\":\"qx\", \"simbolo\":\"1\",\"colocado\":\"0\", \"estadoDestino\":\"q1\", \"direcao\": \"R\"},\n" +
                            "       ...,\n" +
                            "   ]\n" +
                            "}\n\n" +
                            "   \"estadoInicial\": \"qx\",\n" +
                            "   \"estadosFinais\": [\"qx\", \"qy\"],\n" +
                            "Posteriormente, basta adicionar as cadeias que deseja testar, tendo a possibilidade de" +
                            " checar o veredito\nsobre sua aceitação na linguagem representada pelo autômato." +
                            "\n\n",
                    "Sobre o programa", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}