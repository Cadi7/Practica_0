package UI;

import Agent.AdaugaAgentDialog;
import Agent.LoadAgenti;
import Apartament.AdaugaApartamentDialog;
import Apartament.LoadApartamente;
import Database.DatabaseManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI extends JFrame {
    private DatabaseManager databaseManager;

    public UI() {
        databaseManager = new DatabaseManager();

        DefaultTableModel apartamenteTableModel = new DefaultTableModel(
                new String[]{"Cod Apartament", "Etaj", "Nr Camere", "Pret", "Metri Patrati", "Cod Agent"}, 0);
        DefaultTableModel agentiTableModel = new DefaultTableModel(
                new String[]{"Cod Agent", "Nume", "Prenume", "Varsta", "Telefon"}, 0);

        // Creare tabele
        JTable apartamenteTable = new JTable(apartamenteTableModel);
        JTable agentiTable = new JTable(agentiTableModel);

        // Adăugare modele de tabel la tabele
        apartamenteTable.setModel(apartamenteTableModel);
        agentiTable.setModel(agentiTableModel);

        // Încărcare inițială date în tabele
        LoadApartamente.load(apartamenteTable);
        LoadAgenti.load(agentiTable);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Aplicația Apartamente");
        setPreferredSize(new Dimension(800, 400));

        // Creare panou principal
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Creare scroll pane pentru tabelul apartamente
        JScrollPane apartamenteScrollPane = new JScrollPane(apartamenteTable);
        apartamenteScrollPane.setPreferredSize(new Dimension(450, 400));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.5;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(apartamenteScrollPane, gbc);

        // Creare scroll pane pentru tabelul agenti
        JScrollPane agentiScrollPane = new JScrollPane(agentiTable);
        agentiScrollPane.setPreferredSize(new Dimension(450, 400));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.5;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(agentiScrollPane, gbc);

        // Creare meniuri
        JMenuBar menuBar = new JMenuBar();
        JMenu adaugaMenu = new JMenu("Adaugă/Editează");
        JMenu operatiiMenu = new JMenu("Operații Speciale");

        // Submeniu pentru adăugarea și editarea apartamentelor
        JMenuItem adaugaApartamentItem = new JMenuItem("Adaugă Apartament");
        adaugaApartamentItem.addActionListener(e -> {
            AdaugaApartamentDialog dialog = new AdaugaApartamentDialog(UI.this, databaseManager, apartamenteTable);
            dialog.setVisible(true);
        });
        adaugaMenu.add(adaugaApartamentItem);

        // Submeniu pentru adăugarea și editarea agenților
        JMenuItem adaugaAgentItem = new JMenuItem("Adaugă Agent");
        adaugaAgentItem.addActionListener(e -> {
            AdaugaAgentDialog dialog = new AdaugaAgentDialog(UI.this, databaseManager, agentiTable);
            dialog.setVisible(true);
        });
        adaugaMenu.add(adaugaAgentItem);
        LoadAgenti.load(agentiTable);

        // Submeniu pentru operațiile speciale
        JMenuItem apartamentePatruCamereEtajeDoiSiTreiItem = new JMenuItem("Apartamente 4 Camere Etaje 2 și 3");
        apartamentePatruCamereEtajeDoiSiTreiItem.addActionListener(e -> afiseazaApartamentePatruCamereEtajeDoiSiTrei());
        operatiiMenu.add(apartamentePatruCamereEtajeDoiSiTreiItem);

        JMenuItem agentiTelefonVarstaIntervalItem = new JMenuItem("Agenți Telefon și Vârstă Interval 20-30");
        agentiTelefonVarstaIntervalItem.addActionListener(e -> afiseazaAgentiTelefonVarstaInterval());
        operatiiMenu.add(agentiTelefonVarstaIntervalItem);

        JMenuItem agentiSumaTotalaVanzariItem = new JMenuItem("Agenți și Suma Totală Vânzări");
        agentiSumaTotalaVanzariItem.addActionListener(e -> afiseazaAgentiCuSumaTotalaVanzari());
        operatiiMenu.add(agentiSumaTotalaVanzariItem);

        menuBar.add(adaugaMenu);
        menuBar.add(operatiiMenu);
        setJMenuBar(menuBar);

        // Adăugare panoul principal la fereastra
        add(mainPanel);

        pack();
        setLocationRelativeTo(null);
    }

    public void afiseazaApartamentePatruCamereEtajeDoiSiTrei() {
        // ...
    }

    public void afiseazaAgentiTelefonVarstaInterval() {
        // ...
    }

    public void afiseazaAgentiCuSumaTotalaVanzari() {
        // ...
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                UI ui = new UI();
                ui.setVisible(true);
            }
        });
    }
}
