package Apartament;

import Database.DatabaseManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class EditeazaApartamentDialog extends JDialog {

    private JTextField etajField;
    private JTextField nrCamereField;
    private JTextField pretField;
    private JTextField metriPatratiField;
    private JTextField codAgentField;
    private JButton salveazaButton;
    private int codApartament;
    private DatabaseManager databaseManager;

    public EditeazaApartamentDialog(Frame parent, DatabaseManager databaseManager, JTable apartamenteTable, int selectedRow) {
        super(parent, "Editează Apartament", true);
        this.databaseManager = databaseManager;

        Object codApartamentValue = apartamenteTable.getValueAt(selectedRow, 0);
        if (codApartamentValue instanceof Integer) {
            codApartament = (Integer) codApartamentValue;
        }

        Object etajValue = apartamenteTable.getValueAt(selectedRow, 1);
        Object nrCamereValue = apartamenteTable.getValueAt(selectedRow, 2);
        Object pretValue = apartamenteTable.getValueAt(selectedRow, 3);
        Object metriPatratiValue = apartamenteTable.getValueAt(selectedRow, 4);
        Object codAgentValue = apartamenteTable.getValueAt(selectedRow, 5);

        setLayout(new GridLayout(6, 2, 10, 10));
        add(new JLabel("Etaj:"));
        etajField = new JTextField();
        add(etajField);
        etajField.setText(etajValue != null ? etajValue.toString() : "");

        add(new JLabel("Nr Camere:"));
        nrCamereField = new JTextField();
        add(nrCamereField);
        nrCamereField.setText(nrCamereValue != null ? nrCamereValue.toString() : "");

        add(new JLabel("Pret:"));
        pretField = new JTextField();
        add(pretField);
        pretField.setText(pretValue != null ? pretValue.toString() : "");

        add(new JLabel("Metri Pătrați:"));
        metriPatratiField = new JTextField();
        add(metriPatratiField);
        metriPatratiField.setText(metriPatratiValue != null ? metriPatratiValue.toString() : "");

        add(new JLabel("Cod Agent:"));
        codAgentField = new JTextField();
        add(codAgentField);
        codAgentField.setText(codAgentValue != null ? codAgentValue.toString() : "");

        salveazaButton = new JButton("Salvează");
        salveazaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                salveazaApartament();
                dispose();
            }
        });
        add(salveazaButton);

        pack();
        setLocationRelativeTo(parent);
    }

    private void salveazaApartament() {
        int etaj = Integer.parseInt(etajField.getText());
        int nrCamere = Integer.parseInt(nrCamereField.getText());
        double pret = Double.parseDouble(pretField.getText());
        double metriPatrati = Double.parseDouble(metriPatratiField.getText());
        int codAgent = Integer.parseInt(codAgentField.getText());

        Apartament apartament = new Apartament(codApartament, etaj, nrCamere, pret, metriPatrati, codAgent);
        databaseManager.actualizeazaApartament(apartament);
    }
}
