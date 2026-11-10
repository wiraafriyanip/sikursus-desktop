package id.ac.uinbukittinggi.sikursus.dekstop;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import view.LoginFrame;

/** Titik masuk aplikasi: setiap proses baru dimulai dari LoginFrame. */
public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
            // Gunakan Look and Feel bawaan Java jika tema sistem tidak tersedia.
        }
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}
