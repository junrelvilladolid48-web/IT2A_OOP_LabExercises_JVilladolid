package view;

import dao.DatabaseManager;
import model.DailyCheckIn;
import model.TherapistSession;
import util.CSVUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class RecordsWindow extends JFrame {
    private JFrame parent;
    private JTable checkinTable;
    private JTable sessionTable;

    public RecordsWindow(JFrame parent) {
        this.parent = parent;
        setTitle("Records");
        setSize(900,600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JTabbedPane tabs = new JTabbedPane();

        checkinTable = new JTable();
        JPanel chPanel = new JPanel(new BorderLayout());
        chPanel.add(new JScrollPane(checkinTable), BorderLayout.CENTER);
        JPanel chButtons = new JPanel();
        JButton btnRefresh = new JButton("Refresh");
        JButton btnExportCSV = new JButton("Export CheckIns CSV");
        JButton btnDelete = new JButton("Delete Selected");
        JButton back = new JButton("Back");
        chButtons.add(btnRefresh); chButtons.add(btnExportCSV); chButtons.add(btnDelete); chButtons.add(back);
        chPanel.add(chButtons, BorderLayout.SOUTH);

        tabs.addTab("Daily Check-Ins", chPanel);

        sessionTable = new JTable();
        JPanel sPanel = new JPanel(new BorderLayout());
        sPanel.add(new JScrollPane(sessionTable), BorderLayout.CENTER);
        JPanel sButtons = new JPanel();
        JButton sRefresh = new JButton("Refresh");
        JButton sExport = new JButton("Export Sessions CSV");
        JButton sDelete = new JButton("Delete Selected");
        sButtons.add(sRefresh); sButtons.add(sExport); sButtons.add(sDelete);
        sPanel.add(sButtons, BorderLayout.SOUTH);

        tabs.addTab("Therapist Sessions", sPanel);

        add(tabs, BorderLayout.CENTER);

        btnRefresh.addActionListener(e -> loadCheckins());
        btnExportCSV.addActionListener(e -> {
            try {
                CSVUtils.exportCheckinsToCSV(DatabaseManager.getInstance().getAllCheckIns());
                JOptionPane.showMessageDialog(this, "Exported checkins.csv in project folder.");
            } catch (Exception ex) { JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage()); }
        });
        btnDelete.addActionListener(e -> deleteSelectedCheckin());

        sRefresh.addActionListener(e -> loadSessions());
        sExport.addActionListener(e -> {
            try {
                CSVUtils.exportSessionsToCSV(DatabaseManager.getInstance().getAllSessions());
                JOptionPane.showMessageDialog(this, "Exported sessions.csv in project folder.");
            } catch (Exception ex) { JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage()); }
        });
        sDelete.addActionListener(e -> deleteSelectedSession());

        back.addActionListener(e -> {
            parent.setVisible(true);
            dispose();
        });

        loadCheckins();
        loadSessions();
    }

    private void loadCheckins() {
        try {
            List<DailyCheckIn> list = DatabaseManager.getInstance().getAllCheckIns();
            String[] cols = {"ID","UserID","Mood","Stress","Notes","Date"};
            DefaultTableModel m = new DefaultTableModel(cols, 0);
            for (DailyCheckIn d : list) {
                m.addRow(new Object[]{d.getCheckInId(), d.getUserId(), d.getMoodRating(), d.getStressLevel(), d.getNotes(), d.getDate()});
            }
            checkinTable.setModel(m);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Load error: " + ex.getMessage());
        }
    }

    private void loadSessions() {
        try {
            List<TherapistSession> list = DatabaseManager.getInstance().getAllSessions();
            String[] cols = {"ID","UserID","Therapist","Date","Duration","Notes"};
            DefaultTableModel m = new DefaultTableModel(cols, 0);
            for (TherapistSession s : list) {
                m.addRow(new Object[]{s.getSessionId(), s.getUserId(), s.getTherapistName(), s.getSessionDate(), s.getDurationMinutes(), s.getNotes()});
            }
            sessionTable.setModel(m);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Load error: " + ex.getMessage());
        }
    }

    private void deleteSelectedCheckin() {
        int row = checkinTable.getSelectedRow();
        if (row < 0) { JOptionPane.showMessageDialog(this, "Select a row."); return; }
        int id = (Integer) checkinTable.getValueAt(row, 0);
        try {
            DatabaseManager.getInstance().deleteCheckIn(id);
            loadCheckins();
            JOptionPane.showMessageDialog(this, "Deleted.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void deleteSelectedSession() {
        int row = sessionTable.getSelectedRow();
        if (row < 0) { JOptionPane.showMessageDialog(this, "Select a row."); return; }
        int id = (Integer) sessionTable.getValueAt(row, 0);
        try {
            DatabaseManager.getInstance().deleteSession(id);
            loadSessions();
            JOptionPane.showMessageDialog(this, "Deleted.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
}
