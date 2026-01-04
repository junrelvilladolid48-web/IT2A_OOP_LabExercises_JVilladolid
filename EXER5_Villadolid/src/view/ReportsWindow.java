package view;

import dao.DatabaseManager;
import model.DailyCheckIn;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class ReportsWindow extends JFrame {
    private JFrame mainMenu;
    private JTextArea reportArea;

    public ReportsWindow(JFrame mainMenu) {
        this.mainMenu = mainMenu;

        setTitle("Reports");
        setSize(700,500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        reportArea = new JTextArea();
        reportArea.setEditable(false);
        add(new JScrollPane(reportArea), BorderLayout.CENTER);

        JPanel buttons = new JPanel();
        JButton generate = new JButton("Generate Mood & Stress Trend Summary");
        JButton back = new JButton("Back");
        buttons.add(generate);
        buttons.add(back);

        generate.addActionListener(e -> generateReport());

        back.addActionListener(e -> {
            mainMenu.setVisible(true);
            dispose();
        });

        add(buttons, BorderLayout.SOUTH);
    }

    private void generateReport() {
        try {
            List<DailyCheckIn> list = DatabaseManager.getInstance().getAllCheckIns();

            if (list.isEmpty()) {
                reportArea.setText("No check-ins.");
                return;
            }

            double avgMood = list.stream().mapToInt(DailyCheckIn::getMoodRating).average().orElse(0);
            double avgStress = list.stream().mapToInt(DailyCheckIn::getStressLevel).average().orElse(0);

            StringBuilder sb = new StringBuilder();
            sb.append("Mood & Stress Trend Report\n\n");
            sb.append(String.format("Average mood: %.2f (1-10)\n", avgMood));
            sb.append(String.format("Average stress: %.2f (1-10)\n\n", avgStress));
            sb.append("Latest entries:\n");

            int limit = Math.min(10, list.size());
            for (int i = 0; i < limit; i++) {
                DailyCheckIn d = list.get(i);
                sb.append(String.format("%s | Mood: %d | Stress: %d | Notes: %s\n",
                        d.getDate(), d.getMoodRating(), d.getStressLevel(), d.getNotes()));
            }

            reportArea.setText(sb.toString());

        } catch (SQLException ex) {
            reportArea.setText("Error: " + ex.getMessage());
        }
    }
}
