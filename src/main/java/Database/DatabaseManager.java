package Database;

import Agent.Agent;
import Apartament.Apartament;

import javax.swing.*;
import java.sql.*;

public class DatabaseManager {
    private Connection connection;

    public DatabaseManager() {
        try {
            connection = getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        String url = "jdbc:sqlite:apartamente.db";
        return DriverManager.getConnection(url);
    }

    public void createTables() {
        createApartamenteTable();
        createAgentiTable();
    }

    private void createApartamenteTable() {
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS Apartamente (" +
                    "CodApartament INTEGER PRIMARY KEY," +
                    "Etaj INTEGER," +
                    "NrCamere INTEGER," +
                    "Pret REAL," +
                    "MetriPatrati REAL," +
                    "CodAgent INTEGER," +
                    "FOREIGN KEY (CodAgent) REFERENCES Agenti(CodAgent)" +
                    ")";
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createAgentiTable() {
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS Agenti (" +
                    "CodAgent INTEGER PRIMARY KEY," +
                    "Nume TEXT," +
                    "Prenume TEXT," +
                    "Varsta INTEGER," +
                    "Telefon TEXT" +
                    ")";
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void adaugaApartament(Apartament apartament) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO Apartamente (Etaj, NrCamere, Pret, MetriPatrati, CodAgent) VALUES (?, ?, ?, ?, ?)"
            );
            preparedStatement.setInt(1, apartament.getEtaj());
            preparedStatement.setInt(2, apartament.getNrCamere());
            preparedStatement.setDouble(3, apartament.getPret());
            preparedStatement.setDouble(4, apartament.getMetriPatrati());
            preparedStatement.setInt(5, apartament.getCodAgent());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void adaugaAgent(Agent agent) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO Agenti (Nume, Prenume, Varsta, Telefon) VALUES (?, ?, ?, ?)"
            );
            //preparedStatement.setInt(1, agent.getCodAgent());
            preparedStatement.setString(1, agent.getNume());
            preparedStatement.setString(2, agent.getPrenume());
            preparedStatement.setInt(3, agent.getVarsta());
            preparedStatement.setString(4, agent.getTelefon());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getApartamentePatruCamereEtajeDoiSiTrei() {
        ResultSet resultSet = null;
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Apartamente WHERE NrCamere = 4 AND Etaj IN (2, 3)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet getAgentiTelefonVarstaInterval(int varstaMinima, int varstaMaxima) {
        ResultSet resultSet = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM Agenti WHERE Varsta >= ? AND Varsta <= ?"
            );
            preparedStatement.setInt(1, varstaMinima);
            preparedStatement.setInt(2, varstaMaxima);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet getAgentiCuVanzari() {
        ResultSet resultSet = null;
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(
                    "SELECT Agenti.*, SUM(Apartamente.Pret) AS TotalVanzari " +
                            "FROM Agenti LEFT JOIN Apartamente ON Agenti.CodAgent = Apartamente.CodAgent " +
                            "GROUP BY Agenti.CodAgent"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
    public void actualizeazaApartament(Apartament apartament) {
        try {
            Connection connection = getConnection();
            if (connection != null) {
                System.out.println("Connected to the database!");
            }

            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE Apartamente SET Etaj = ?, NrCamere = ?, Pret = ?, MetriPatrati = ?, CodAgent = ? WHERE CodApartament = ?"
            );
            statement.setInt(1, apartament.getEtaj());
            statement.setInt(2, apartament.getNrCamere());
            statement.setDouble(3, apartament.getPret());
            statement.setDouble(4, apartament.getMetriPatrati());
            statement.setInt(5, apartament.getCodAgent());
            statement.setInt(6, apartament.getCodApartament());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Apartamentul a fost actualizat cu succes!");
            }

            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void actualizeazaAgent(Agent agent) {
        try {
            Connection connection = getConnection();
            if (connection != null) {
                System.out.println("Connected to the database!");
            }

            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE Agenti SET Nume = ?, Prenume = ?, Varsta = ?, Telefon = ? WHERE CodAgent = ?"
            );
            statement.setString(1, agent.getNume());
            statement.setString(2, agent.getPrenume());
            statement.setInt(3, agent.getVarsta());
            statement.setString(4, agent.getTelefon());
            statement.setInt(5, agent.getCodAgent());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Agentul a fost actualizat cu succes!");
            }

            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
