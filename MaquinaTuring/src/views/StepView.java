package views;
import data.Machine;
import data.Tape;
import service.MachineService;
import views.InitialView;

import javax.swing.*;
import java.awt.*;

import static javax.swing.SwingConstants.CENTER;

public class StepView extends JFrame {

    JTextField palavra = new JTextField();
    JButton validate = new JButton("Validar");
    JButton changeMachine = new JButton("<<<<");
    Machine machine;

    public StepView(Machine mch) {
            this.machine = mch;
            setupFrame();
            setupTitle();
            setupButtons();
            setValidateButton();

            //Adiciona os elementos da janela
            JScrollPane OriginalPane = new JScrollPane(buildTextArea(mch.toString()));
            OriginalPane.setBounds(20, 75, 930, 300);
            add(OriginalPane);


    }

    void setupFrame() {
        setLayout(null);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 500);
        palavra.setBounds(150, 390, 400, 30);
        palavra.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 128)));
        JLabel wordLabel = new JLabel("PALAVRA:");
        wordLabel.setBounds(80, 390, 100, 30);
        add(wordLabel);
        add(palavra);
        setLocation(460, 140);
    }

    private void setupTitle() {
        JLabel titleLabel = new JLabel("Maquina de turing");
        titleLabel.setHorizontalAlignment(CENTER);
        titleLabel.setFont(new Font(null, Font.BOLD, 20));
        titleLabel.setBounds(300, 20, 400, 20);
        add(titleLabel);
    }

    private void setupButtons() {
        validate.setBounds(580, 390, 390, 60);
        validate.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 128)));
        changeMachine.setBounds(20, 15, 100, 30);
        changeMachine.setBorder(BorderFactory.createLineBorder(new Color(255, 0, 0)));
        add(changeMachine);
        add(validate);

        this.changeMachineButtonAction();
    }

    private void changeMachineButtonAction() {
        changeMachine.addActionListener(e -> {
            dispose();
            new InitialView();
        });
    }

    public JTextArea buildTextArea(String text) {
        JTextArea textArea = new JTextArea();
        textArea.setText(text);
        textArea.setWrapStyleWord(true);
        textArea.setBorder(BorderFactory.createBevelBorder(1));
        textArea.setFont(new Font("", Font.PLAIN, 15));
        textArea.setCaretPosition(0);

        return textArea;
    }

    public void setValidateButton() {
        validate.addActionListener(event -> {
            try {
                MachineService pmachine = new MachineService(machine,new Tape(palavra.getText()),palavra.getText());
                ResultView resultView = new ResultView();
                if (pmachine.ProcessMachine() == 0) {
                    JOptionPane.showMessageDialog(this, "Cadeia foi Negada! D:\n Foi inserida uma palavra não pertencente ao alfabeto.."+ pmachine.rulesAppliedToString(), "Maquina de turing", JOptionPane.WARNING_MESSAGE);
                }
                if (pmachine.getAppliedRules().size() == 0) {
                    JOptionPane.showMessageDialog(this, "Cadeia foi Negada! Não foi possível aplicar nenhuma regra para o estado inicial!", "Maquina de turing", JOptionPane.WARNING_MESSAGE);
                } else {
                    resultView.showDialog(this, pmachine);
                }
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(this, "Não foi possivel processar esta palavra... \nPalavra nao pertence ao alfabeto\n", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

}
