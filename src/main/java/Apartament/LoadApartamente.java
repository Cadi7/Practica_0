package Apartament;

import Database.DatabaseManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoadApartamente {
    public static void load(JTable table) {
        DatabaseManager databaseManager = new DatabaseManager();
        DefaultTableModel apartamenteTableModel = (DefaultTableModel) table.getModel();
        apartamenteTableModel.setRowCount(0);

        try {
            Connection connection = databaseManager.getConnection();
            if (connection != null) {
                System.out.println("Connected to the database!");
            }

            Statement statement = connection.createStatement();
            ResultSet apartamenteResultSet = statement.executeQuery("SELECT * FROM Apartamente");

            while (apartamenteResultSet.next()) {
                int codApartament = apartamenteResultSet.getInt("CodApartament");
                int etaj = apartamenteResultSet.getInt("Etaj");
                int nrCamere = apartamenteResultSet.getInt("NrCamere");
                double pret = apartamenteResultSet.getDouble("Pret");
                double metriPatrati = apartamenteResultSet.getDouble("MetriPatrati");
                int codAgent = apartamenteResultSet.getInt("CodAgent");

                Object[] row = {codApartament, etaj, nrCamere, pret, metriPatrati, codAgent};
                apartamenteTableModel.addRow(row);
                apartamenteTableModel.fireTableDataChanged();
            }

            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}