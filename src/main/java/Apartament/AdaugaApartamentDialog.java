package Apartament;

import Database.DatabaseManager;
import UI.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdaugaApartamentDialog extends JDialog {
    private JTextField codApartamentField;
    private final JTextField etajField;
    private final JTextField nrCamereField;
    private final JTextField pretField;
    private final JTextField metriPatratiField;
    private final JTextField codAgentField;

    private final DatabaseManager databaseManager;

    public AdaugaApartamentDialog(Frame parent, DatabaseManager dbManager, JTable apartamenteTable) {
        super(parent, "Adaugă Apartament", true);
        databaseManager = dbManager;

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;

        JLabel etajLabel = new JLabel("Etaj: ");
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(etajLabel, constraints);

        etajField = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 1;
        panel.add(etajField, constraints);


        JLabel nrCamereLabel = new JLabel("Numar Camere: ");
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(nrCamereLabel, constraints);

        nrCamereField = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 2;
        panel.add(nrCamereField, constraints);

        JLabel pretLabel = new JLabel("Pret: ");
        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(pretLabel, constraints);

        pretField = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 3;
        panel.add(pretField, constraints);

        JLabel metriPatratiLabel = new JLabel("Metri Patrati: ");
        constraints.gridx = 0;
        constraints.gridy = 4;
        panel.add(metriPatratiLabel, constraints);

        metriPatratiField = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 4;
        panel.add(metriPatratiField, constraints);

        JLabel codAgentLabel = new JLabel("Cod Agent: ");
        constraints.gridx = 0;
        constraints.gridy = 5;
        panel.add(codAgentLabel, constraints);

        codAgentField = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 5;
        panel.add(codAgentField, constraints);


        JButton adaugaButton = new JButton("Adaugă");
        adaugaButton.addActionListener(e -> {
            int etaj = Integer.parseInt(etajField.getText());
            int nrCamere = Integer.parseInt(nrCamereField.getText());
            int pret = Integer.parseInt(pretField.getText());
            int metriPatrati = Integer.parseInt(metriPatratiField.getText());
            int codAgent = Integer.parseInt(codAgentField.getText());

            Apartament apartament = new Apartament(0, etaj, nrCamere, pret, metriPatrati, codAgent);

            databaseManager.adaugaApartament(apartament);
            LoadApartamente.load(apartamenteTable);

            dispose();
        });
        constraints.gridx = 0;
        constraints.gridy = 6;
        constraints.gridwidth = 2;
        panel.add(adaugaButton, constraints);

        add(panel);
        pack();
        setLocationRelativeTo(parent);
    }
}

