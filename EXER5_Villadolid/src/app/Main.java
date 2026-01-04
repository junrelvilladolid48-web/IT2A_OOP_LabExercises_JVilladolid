package app;

import view.MainMenuWindow;
import javax.swing.SwingUtilities;

/**
 * Entry point
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainMenuWindow().setVisible(true);
        });
    }
}
