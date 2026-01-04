package view;

import controller.SessionController;
import dao.DatabaseManager;
import model.TherapistSession;
import model.User;
import util.ValidationUtils;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SessionFormWindow extends JFrame {
    private JFrame parent;
    private JComboBox<User> userCombo;
    private JTextField therapistField;
    private JTextField dateField;
    private JTextField durationField;
    private JTextArea notesArea;
    private SessionController controller;

    public SessionFormWindow(JFrame parent) {
        this.parent = parent;
        setTitle("Therapist Session");
        setSize(520,440);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        try { controller = new SessionController(); } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "DB init error: " + ex.getMessage());
        }

        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(6,6,6,6);
        c.fill = GridBagConstraints.HORIZONTAL;

        c.gridx=0; c.gridy=0;
        form.add(new JLabel("User:"), c);
        c.gridx=1;
        userCombo = new JComboBox<>();
        try {
            List<User> users = DatabaseManager.getInstance().getAllUsers();
            for (User u : users) userCombo.addItem(u);
        } catch (Exception e) {}
        form.add(userCombo, c);

        c.gridx=0; c.gridy++;
        form.add(new JLabel("Therapist Name:"), c);
        c.gridx=1;
        therapistField = new JTextField(20);
        form.add(therapistField, c);

        c.gridx=0; c.gridy++;
        form.add(new JLabel("Date (YYYY-MM-DD):"), c);
        c.gridx=1;
        dateField = new JTextField(10);
        dateField.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        form.add(dateField, c);

        c.gridx=0; c.gridy++;
        form.add(new JLabel("Duration (minutes):"), c);
        c.gridx=1;
        durationField = new JTextField(6);
        form.add(durationField, c);

        c.gridx=0; c.gridy++;
        form.add(new JLabel("Notes:"), c);
        c.gridx=1;
        notesArea = new JTextArea(6,20);
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
            User u = (User) userCombo.getSelectedItem();
            if (u == null) {
                JOptionPane.showMessageDialog(this, "Select a user.");
                return;
            }
            String therapist = therapistField.getText().trim();
            String date = dateField.getText().trim();
            String durText = durationField.getText().trim();
            String notes = notesArea.getText().trim();

            String err = ValidationUtils.validateSession(therapist, date, durText);
            if (!err.isEmpty()) {
                JOptionPane.showMessageDialog(this, err, "Validation", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int duration = Integer.parseInt(durText);
            TherapistSession s = new TherapistSession(0, u.getId(), therapist, date, duration, notes);
            boolean ok = controller.saveSession(s);
            if (ok) {
                JOptionPane.showMessageDialog(this, "Saved.");
                onClear();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void onClear() {
        therapistField.setText("");
        durationField.setText("");
        notesArea.setText("");
        dateField.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
    }
}
