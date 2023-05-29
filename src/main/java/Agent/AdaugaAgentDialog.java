package Agent;

import Database.DatabaseManager;
import UI.UI;

import javax.swing.*;
import java.awt.*;

public class AdaugaAgentDialog extends JDialog {
    private JTextField codAgentField;
    private final JTextField numeField;
    private final JTextField prenumeField;
    private final JTextField varstaField;
    private final JTextField telefonField;

    private final DatabaseManager databaseManager;

    public AdaugaAgentDialog(Frame parent, DatabaseManager dbManager, JTable agentiTable) {
        super(parent, "Adaugă Agent", true);
        databaseManager = dbManager;

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;

        JLabel numeLabel = new JLabel("Nume: ");
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(numeLabel, constraints);

        numeField = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 1;
        panel.add(numeField, constraints);

        JLabel prenumeLabel = new JLabel("Prenume: ");
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(prenumeLabel, constraints);

        prenumeField = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 2;
        panel.add(prenumeField, constraints);

        JLabel varstaLabel = new JLabel("Varsta: ");
        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(varstaLabel, constraints);

        varstaField = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 3;
        panel.add(varstaField, constraints);

        JLabel telefonLabel = new JLabel("Telefon: ");
        constraints.gridx = 0;
        constraints.gridy = 4;
        panel.add(telefonLabel, constraints);

        telefonField = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 4;
        panel.add(telefonField, constraints);

        JButton adaugaButton = new JButton("Adaugă");
        adaugaButton.addActionListener(e -> {
            String nume = numeField.getText();
            String prenume = prenumeField.getText();
            int varsta = Integer.parseInt(varstaField.getText());
            String telefon = telefonField.getText();


            Agent agent = new Agent(0, nume, prenume, varsta, telefon);

            databaseManager.adaugaAgent(agent);
            LoadAgenti.load(agentiTable);
            dispose();
        });
        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 2;
        panel.add(adaugaButton, constraints);

        add(panel);
        pack();
        setLocationRelativeTo(parent);
    }
}
