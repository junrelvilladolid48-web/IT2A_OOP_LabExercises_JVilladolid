package view;

import controller.CheckInController;
import dao.DatabaseManager;
import model.DailyCheckIn;
import model.User;
import util.ValidationUtils;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CheckInFormWindow extends JFrame {
    private JFrame parent;
    private JComboBox<User> userCombo;
    private JSpinner moodSpinner;
    private JSpinner stressSpinner;
    private JTextField dateField;
    private JTextArea notesArea;
    private CheckInController controller;

    public CheckInFormWindow(JFrame parent) {
        this.parent = parent;
        setTitle("Daily Check-In");
        setSize(520,420);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        try { controller = new CheckInController(); } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "DB init error: " + ex.getMessage());
        }

        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(6,6,6,6);
        c.fill = GridBagConstraints.HORIZONTAL;

        c.gridx = 0; c.gridy = 0;
        form.add(new JLabel("User:"), c);
        c.gridx = 1;
        userCombo = new JComboBox<>();
        try {
            List<User> users = DatabaseManager.getInstance().getAllUsers();
            for (User u : users) userCombo.addItem(u);
        } catch (Exception e) { /* ignore */ }
        form.add(userCombo, c);

        c.gridx = 0; c.gridy++;
        form.add(new JLabel("Mood (1-10):"), c);
        c.gridx = 1;
        moodSpinner = new JSpinner(new SpinnerNumberModel(5, 1, 10, 1));
        form.add(moodSpinner, c);

        c.gridx = 0; c.gridy++;
        form.add(new JLabel("Stress (1-10):"), c);
        c.gridx = 1;
        stressSpinner = new JSpinner(new SpinnerNumberModel(5, 1, 10, 1));
        form.add(stressSpinner, c);

        c.gridx = 0; c.gridy++;
        form.add(new JLabel("Date (YYYY-MM-DD):"), c);
        c.gridx = 1;
        dateField = new JTextField(10);
        dateField.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        form.add(dateField, c);

        c.gridx = 0; c.gridy++;
        form.add(new JLabel("Notes:"), c);
        c.gridx = 1;
        notesArea = new JTextArea(6, 20);
        form.add(new JScrollPane(notesArea), c);

        JPanel buttons = new JPanel();
        JButton save = new JButton("Save");
        JButton clear = new JButton("Clear");
        JButton back = new JButton("Back");
        buttons.add(save); buttons.add(clear); buttons.add(back);

        save.addActionListener(e -> onSave());
        clear.addActionListener(e -> onClear());
        back.addActionListener(e -> {
            parent.setVisible(true);
            dispose();
        });

        add(form, BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);
    }

    private void onSave() {
        try {
            User selectedUser = (User) userCombo.getSelectedItem();
            if (selectedUser == null) {
                JOptionPane.showMessageDialog(this, "Select a user.");
                return;
            }
            int mood = (Integer) moodSpinner.getValue();
            int stress = (Integer) stressSpinner.getValue();
            String date = dateField.getText().trim();
            String notes = notesArea.getText().trim();

            String err = ValidationUtils.validateCheckIn(mood, stress, date);
            if (!err.isEmpty()) {
                JOptionPane.showMessageDialog(this, err, "Validation", JOptionPane.WARNING_MESSAGE);
                return;
            }

            DailyCheckIn c = new DailyCheckIn(0, selectedUser.getId(), mood, stress, notes, date);
            boolean ok = controller.saveCheckIn(c);
            if (ok) {
                JOptionPane.showMessageDialog(this, "Saved.");
                onClear();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void onClear() {
        moodSpinner.setValue(5);
        stressSpinner.setValue(5);
        notesArea.setText("");
        dateField.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
    }
}
