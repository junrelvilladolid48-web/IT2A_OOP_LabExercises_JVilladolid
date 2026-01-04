package controller;

import dao.DatabaseManager;
import model.TherapistSession;

import javax.swing.*;
import java.sql.SQLException;

public class SessionController {
    private DatabaseManager db;

    public SessionController() throws SQLException {
        db = DatabaseManager.getInstance();
    }

    public boolean saveSession(TherapistSession s) {
        try {
            db.insertSession(s);
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error saving session: " + ex.getMessage());
            return false;
        }
    }
}
