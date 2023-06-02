package UI;

import Agent.AdaugaAgentDialog;
import Agent.EditeazaAgentDialog;
import Agent.LoadAgenti;
import Apartament.AdaugaApartamentDialog;
import Apartament.EditeazaApartamentDialog;
import Apartament.LoadApartamente;
import Database.DatabaseManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UI extends JFrame {
    private final DatabaseManager databaseManager;
    private final JTable apartamenteTable;
    private final JTable agentiTable;

    public UI() {
        databaseManager = new DatabaseManager();

        DefaultTableModel apartamenteTableModel = new DefaultTableModel(
                new String[]{"Cod Apartament", "Etaj", "Nr Camere", "Pret", "Metri Patrati", "Cod Agent"}, 0);
        DefaultTableModel agentiTableModel = new DefaultTableModel(
                new String[]{"Cod Agent", "Nume", "Prenume", "Varsta", "Telefon"}, 0);

        // Creare tabele
        apartamenteTable = new JTable(apartamenteTableModel);
        agentiTable = new JTable(agentiTableModel);

        // Adăugare modele de tabel la tabele
        apartamenteTable.setModel(apartamenteTableModel);
        agentiTable.setModel(agentiTableModel);

        // Încărcare inițială date în tabele
        LoadApartamente.load(apartamenteTable);
        LoadAgenti.load(agentiTable);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Aplicația Apartamente");
        setPreferredSize(new Dimension(900, 400));

        // Creare panou principal
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Creare scroll pane pentru tabelul apartamente
        JScrollPane apartamenteScrollPane = new JScrollPane(apartamenteTable);
        // Text informativ pentru scroll pane
        apartamenteScrollPane.setBorder(BorderFactory.createTitledBorder("Apartamente"));
        apartamenteScrollPane.setPreferredSize(new Dimension(500, 400));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.5;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(apartamenteScrollPane, gbc);

        // Creare scroll pane pentru tabelul agenți
        JScrollPane agentiScrollPane = new JScrollPane(agentiTable);
        // Text informativ pentru scroll pane
        agentiScrollPane.setBorder(BorderFactory.createTitledBorder("Agenți"));
        agentiScrollPane.setPreferredSize(new Dimension(400, 400));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.5;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(agentiScrollPane, gbc);

        // Creare meniu principal
        JMenuBar menuBar = new JMenuBar();

        // Meniu "Apartamente"
        JMenu apartamenteMenu = new JMenu("Apartamente");
        JMenuItem adaugaApartamentMenuItem = new JMenuItem("Adaugă Apartament");
        adaugaApartamentMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deschideAdaugaApartamentDialog();
            }
        });
        JMenuItem editeazaApartamentMenuItem = new JMenuItem("Editează Apartament");
        editeazaApartamentMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deschideEditeazaApartamentDialog();
            }
        });
        JMenuItem listaPatruCamereEtajeDoiSiTreiMenuItem = new JMenuItem("Listă apartamente 4 camere, etaje 2 și 3");
        listaPatruCamereEtajeDoiSiTreiMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                afiseazaApartamentePatruCamereEtajeDoiSiTrei();
            }
        });
        apartamenteMenu.add(adaugaApartamentMenuItem);
        apartamenteMenu.add(editeazaApartamentMenuItem);
        apartamenteMenu.add(listaPatruCamereEtajeDoiSiTreiMenuItem);

        // Meniu "Agenți"
        JMenu agentiMenu = new JMenu("Agenți");
        JMenuItem adaugaAgentMenuItem = new JMenuItem("Adaugă Agent");
        adaugaAgentMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deschideAdaugaAgentDialog();
            }
        });
        JMenuItem editeazaAgentMenuItem = new JMenuItem("Editează Agent");
        editeazaAgentMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deschideEditeazaAgentDialog();
            }
        });
        JMenuItem cautareAgentiVarstaTelefonMenuItem = new JMenuItem("Căutare agenți (20-30 ani)");
        cautareAgentiVarstaTelefonMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                afiseazaAgentiVarstaTelefon();
            }
        });
        JMenuItem listaAgentiTotalVanzariMenuItem = new JMenuItem("Listă agenți și total vânzări");
        listaAgentiTotalVanzariMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                afiseazaAgentiTotalVanzari();
            }
        });
        agentiMenu.add(adaugaAgentMenuItem);
        agentiMenu.add(editeazaAgentMenuItem);
        agentiMenu.add(cautareAgentiVarstaTelefonMenuItem);
        agentiMenu.add(listaAgentiTotalVanzariMenuItem);

        // Adăugare meniuri la bara de meniu
        menuBar.add(apartamenteMenu);
        menuBar.add(agentiMenu);

        JMenuItem refreshMenuItem = new JMenuItem("Refresh database");
        refreshMenuItem.setHorizontalAlignment(SwingConstants.CENTER);
        refreshMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LoadAgenti.load(agentiTable);
                LoadApartamente.load(apartamenteTable);
            }
        });

        menuBar.add(Box.createHorizontalGlue());
        menuBar.add(new JLabel("   "));
        menuBar.add(refreshMenuItem);


        // Setare bara de meniu
        setJMenuBar(menuBar);

        // Adăugare panou principal în fereastra
        add(mainPanel);

        // Configurare afișare fereastră
        pack();
        setLocationRelativeTo(null);
    }

    private void deschideAdaugaApartamentDialog() {
        AdaugaApartamentDialog dialog = new AdaugaApartamentDialog(UI.this, databaseManager, apartamenteTable);
        dialog.setVisible(true);
    }

    private void deschideAdaugaAgentDialog() {
        AdaugaAgentDialog dialog = new AdaugaAgentDialog(UI.this, databaseManager, agentiTable);
        dialog.setVisible(true);
    }

    private void deschideEditeazaApartamentDialog() {
        int selectedRow = apartamenteTable.getSelectedRow();
        if (selectedRow >= 0) {
            EditeazaApartamentDialog dialog = new EditeazaApartamentDialog(UI.this, databaseManager, apartamenteTable, selectedRow);
            dialog.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(UI.this, "Selectați un apartament pentru a-l edita.", "Eroare", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deschideEditeazaAgentDialog() {
        int selectedRow = agentiTable.getSelectedRow();
        if (selectedRow >= 0) {
            EditeazaAgentDialog dialog = new EditeazaAgentDialog(UI.this, databaseManager, agentiTable, selectedRow);
            dialog.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(UI.this, "Selectați un agent pentru a-l edita.", "Eroare", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void afiseazaApartamentePatruCamereEtajeDoiSiTrei() {
        ResultSet resultSet = databaseManager.getApartamentePatruCamereEtajeDoiSiTrei();
        String[] columnNames = {"Etaj", "NrCamere", "Pret", "MetriPatrati",};
        createResultDialog("Apartamente cu 4 camere, etaje 2 și 3", columnNames, resultSet);
    }

    private void afiseazaAgentiVarstaTelefon() {
        ResultSet resultSet = databaseManager.getAgentiTelefonVarstaInterval(20, 30);
        String[] columnNames = {"Nume", "Prenume", "Varsta", "Telefon"};
        createResultDialog("Agenți cu vârsta între 20 și 30 de ani", columnNames, resultSet);
    }

    private void afiseazaAgentiTotalVanzari() {
        ResultSet resultSet = databaseManager.getAgentiCuVanzari();
        String[] columnNames = {"Nume", "Prenume", "Varsta", "Telefon", "TotalVanzari"};
        createResultDialog("Agenți și suma totală de vânzări", columnNames, resultSet);
    }

    private void createResultDialog(String title, String[] columnNames, ResultSet resultSet) {
        JDialog dialog = new JDialog(UI.this, title, true);

        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable resultTable = new JTable(model);

        try {
            while (resultSet.next()) {
                Object[] rowData = new Object[columnNames.length];
                for (int i = 0; i < columnNames.length; i++) {
                    rowData[i] = resultSet.getString(columnNames[i]);
                }
                model.addRow(rowData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(resultTable);
        dialog.getContentPane().add(scrollPane);

        dialog.pack();
        dialog.setLocationRelativeTo(UI.this);
        dialog.setVisible(true);
    }


}
