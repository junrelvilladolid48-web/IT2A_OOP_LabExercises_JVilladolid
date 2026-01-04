package controller;

import dao.DatabaseManager;
import model.DailyCheckIn;

import javax.swing.*;
import java.sql.SQLException;

public class CheckInController {
    private DatabaseManager db;

    public CheckInController() throws SQLException {
        db = DatabaseManager.getInstance();
    }

    public boolean saveCheckIn(DailyCheckIn c) {
        try {
            db.insertCheckIn(c);
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error saving check-in: " + ex.getMessage());
            return false;
        }
    }
}
