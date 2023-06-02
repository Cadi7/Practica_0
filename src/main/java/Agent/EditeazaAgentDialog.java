package Agent;

import Database.DatabaseManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class EditeazaAgentDialog extends JDialog {

    private JTextField numeField;
    private JTextField prenumeField;
    private JTextField varstaField;
    private JTextField telefonField;
    private JButton salveazaButton;
    private int codAgent;
    private DatabaseManager databaseManager;

    public EditeazaAgentDialog(Frame parent, DatabaseManager databaseManager, JTable agentiTable, int selectedRow) {
        super(parent, "Editează Agent", true);
        this.databaseManager = databaseManager;

        Object codAgentValue = agentiTable.getValueAt(selectedRow, 0);
        if (codAgentValue instanceof Integer) {
            codAgent = (Integer) codAgentValue;
        }

        Object numeValue = agentiTable.getValueAt(selectedRow, 1);
        Object prenumeValue = agentiTable.getValueAt(selectedRow, 2);
        Object varstaValue = agentiTable.getValueAt(selectedRow, 3);
        Object telefonValue = agentiTable.getValueAt(selectedRow, 4);

        setLayout(new GridLayout(5, 2, 10, 10));
        add(new JLabel("Nume:"));
        numeField = new JTextField();
        add(numeField);
        numeField.setText(numeValue != null ? numeValue.toString() : "");

        add(new JLabel("Prenume:"));
        prenumeField = new JTextField();
        add(prenumeField);
        prenumeField.setText(prenumeValue != null ? prenumeValue.toString() : "");


        add(new JLabel("Varsta:"));
        varstaField = new JTextField();
        add(varstaField);
        varstaField.setText(varstaValue != null ? varstaValue.toString() : "");

        add(new JLabel("Telefon:"));
        telefonField = new JTextField();
        add(telefonField);
        telefonField.setText(telefonValue != null ? telefonValue.toString() : "");

        salveazaButton = new JButton("Salvează");
        salveazaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                salveazaAgent();
                dispose();
            }
        });
        add(salveazaButton);

        pack();
        setLocationRelativeTo(parent);
    }
    private void salveazaAgent() {
        String nume = numeField.getText();
        String prenume = prenumeField.getText();
        int varsta = Integer.parseInt(varstaField.getText());
        String telefon = telefonField.getText();

        Agent agent = new Agent(codAgent, nume, prenume, varsta, telefon);

    }
}
