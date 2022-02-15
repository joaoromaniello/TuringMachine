package views;

import service.MachineService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.SwingConstants.CENTER;

public class ResultView extends JFrame implements ActionListener {

    private final JButton left = new JButton();
    private final JButton right = new JButton();
    private final JButton finalButton = new JButton();
    private final JLabel stepLabel = new JLabel();
    private final JLabel acceptLabel = new JLabel();
    private final JLabel ruleLabel = new JLabel();
    private final JLabel tapeLabel = new JLabel();
    private String lastStatus = "";
    private Integer step;
    private Integer oldStep;
    private Integer stepView;

    public ResultView() {
        this.step = 0;
        this.oldStep = 0;
        this.stepView = 0;
    }

    public void showDialog(Component parentComponent, MachineService service) {
        setupFrame();
        setupButton(left, "Anterior", 20);
        setupButton(right, "Proximo", 470);
        setupFinalButton();
        applyButtonActions(service);
        updateView(service);
    }

    private void setupLabel(MachineService service) {
        stepLabel.setText((step + 1) + ". Passo");
        acceptLabel.setText(lastStatus);
        if (!lastStatus.equals("")) {
            acceptLabel.setVisible(true);
            if (lastStatus.equals("ACEITA")) {
                finalButton.setVisible(true);
            }
        } else {
            acceptLabel.setVisible(false);
            finalButton.setVisible(false);
        }
        stepLabel.setFont(new Font(null, Font.BOLD, 20));
        stepLabel.setHorizontalAlignment(CENTER);
        stepLabel.setBounds(130, 20, 340, 20);
        add(stepLabel);
        acceptLabel.setFont(new Font(null, Font.BOLD, 15));
        acceptLabel.setHorizontalAlignment(CENTER);
        acceptLabel.setBounds(130, 50, 340, 20);
        add(acceptLabel);
    }

    private void setupButton(JButton button, String text, Integer x) {
        button.setText(text);
        button.setBounds(x, 20, 110, 50);
        button.addActionListener(this);
        add(button);
    }

    private void setupFinalButton() {
        finalButton.setText("Ver final");
        finalButton.setBounds(245, 305, 110, 50);
        finalButton.addActionListener(this);
        add(finalButton);
    }

    private void applyButtonActions(MachineService service) {
        left.addActionListener(e -> {
            oldStep = step;
            step = Math.max(step - 1, 0);
            updateView(service);
        });

        right.addActionListener(e -> {
            oldStep = step;
            step = Math.min(step + 1, service.getAppliedRules().size() - 1);
            updateView(service);
        });

        finalButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Cadeia foi aceita!\n\n" + "Fita inicial: [ " + service.getOldTape() + " ]\nFita final:    [ " + service.getFinalTape() + " ]\n\n", "Maquina de turing", JOptionPane.WARNING_MESSAGE);
        });
    }

    private void updateView(MachineService service) {
        lastStatus = setupMainLabel(service);
        setupLabel(service);
    }

    private String setupMainLabel(MachineService service) {
        String status = "";
        if (service.getAppliedRules().size() != service.getTapeStates().size() && service.getAppliedRules().size() - 1 == step) {
            ruleLabel.setText("Não existe regra aplicavel...");
            status = "REJEITA";
        } else {
            ruleLabel.setText(service.getAppliedRules().get(step).toString());
            status = "ACEITA";
        }

        if (service.getTape().toString().length() > 30) {
            tapeLabel.setText(service.getTapeStates().get(step).substring(
                    Math.max(0, Math.min(service.getTapeStates().get(step).length() - 30, stepView)),
                    Math.min(service.getTapeStates().get(step).length(), stepView + 30)
            ));
            int offset = service.getAppliedRules().get(step).getDirection().equals("R") ? (step > oldStep ? 2 : -2) : -2;
            if (!(stepView + offset < 0 || stepView + offset >= service.getTapeStates().get(step).length()) && !step.equals(oldStep)) {
                stepView += offset;
            }
        } else {
            tapeLabel.setText(service.getTapeStates().get(step));
        }

        tapeLabel.setFont(new Font(null, Font.BOLD, 20));
        tapeLabel.setHorizontalAlignment(CENTER);
        tapeLabel.setBounds(120, 100, 340, 80);
        add(tapeLabel);

        ruleLabel.setHorizontalAlignment(CENTER);
        ruleLabel.setBounds(120, 220, 340, 80);
        add(ruleLabel);

        return service.getAppliedRules().size() - 1 == step ? status : "";
    }

    private void setupFrame() {
        setLayout(null);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setTitle("Passo a passo");
        setLocation(760, 340);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
