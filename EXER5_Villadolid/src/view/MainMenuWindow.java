package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainMenuWindow extends JFrame {
    public MainMenuWindow() {
        setTitle("Personalized Mental Wellness - Main Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(480, 320);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel header = new JLabel("Personalized Mental Wellness System", SwingConstants.CENTER);
        header.setFont(header.getFont().deriveFont(16f));
        add(header, BorderLayout.NORTH);

        JPanel center = new JPanel(new GridLayout(5,1,5,5));
        JButton btnCheckIn = new JButton("Daily Check-In");
        JButton btnSession = new JButton("Therapist Sessions");
        JButton btnRecords = new JButton("View Records");
        JButton btnReports = new JButton("Reports");
        JButton btnExit = new JButton("Exit");

        center.add(btnCheckIn);
        center.add(btnSession);
        center.add(btnRecords);
        center.add(btnReports);
        center.add(btnExit);

        add(center, BorderLayout.CENTER);

        btnCheckIn.addActionListener(e -> {
            CheckInFormWindow win = new CheckInFormWindow(this);
            win.setVisible(true);
            setVisible(false);
        });

        btnSession.addActionListener(e -> {
            SessionFormWindow win = new SessionFormWindow(this);
            win.setVisible(true);
            setVisible(false);
        });

        btnRecords.addActionListener(e -> {
            RecordsWindow win = new RecordsWindow(this);
            win.setVisible(true);
            setVisible(false);
        });

        btnReports.addActionListener(e -> {
            ReportsWindow win = new ReportsWindow(this);
            win.setVisible(true);
            setVisible(false);
        });

        btnExit.addActionListener(e -> {
            dispose();
            System.exit(0);
        });

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}
