package Agent;

import Database.DatabaseManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoadAgenti {
    public static void load(JTable table) {
        DatabaseManager databaseManager = new DatabaseManager();
        DefaultTableModel agentiTableModel = (DefaultTableModel) table.getModel();
        agentiTableModel.setRowCount(0);

        try {
            Connection connection = databaseManager.getConnection();
            if (connection != null) {
                System.out.println("Connected to the database!");
            }

            Statement statement = connection.createStatement();
            ResultSet agentiResultSet = statement.executeQuery("SELECT * FROM Agenti");

            while (agentiResultSet.next()) {
                int codAgent = agentiResultSet.getInt("CodAgent");
                String nume = agentiResultSet.getString("Nume");
                String prenume = agentiResultSet.getString("Prenume");
                int varsta = agentiResultSet.getInt("Varsta");
                String telefon = agentiResultSet.getString("Telefon");

                Object[] row = {codAgent, nume, prenume, varsta, telefon};
                agentiTableModel.addRow(row);
                agentiTableModel.fireTableDataChanged();
            }

            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}