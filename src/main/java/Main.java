import Database.DatabaseManager;
import UI.UI;

import javax.swing.*;

public class Main {
    static DatabaseManager databaseManager = new DatabaseManager();
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            databaseManager.createTables();
            UI ui = new UI();
            ui.setVisible(true);
        });
    }
}
