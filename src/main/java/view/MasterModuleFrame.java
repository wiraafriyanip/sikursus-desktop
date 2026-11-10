package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/** Kerangka form master untuk modul yang CRUD-nya belum menjadi fokus P11. */
class MasterModuleFrame extends JFrame {
    private static final Color NAVY = new Color(22, 54, 69);
    private static final Color BG = new Color(243, 247, 246);
    private static final Color TEAL = new Color(12, 153, 119);

    MasterModuleFrame(String module, String description, String iconText) {
        setTitle("SIKURSUS Desktop | Master " + module);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(850, 560);
        setLocationRelativeTo(null);
        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(BG);

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(NAVY);
        header.setBorder(BorderFactory.createEmptyBorder(22, 26, 22, 26));
        JLabel title = new JLabel("MASTER " + module.toUpperCase());
        title.setForeground(Color.WHITE);
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        JLabel sub = new JLabel("SIKURSUS Desktop  |  Modul master");
        sub.setForeground(new Color(200, 224, 225));
        sub.setFont(new Font("SansSerif", Font.PLAIN, 12));
        JPanel texts = new JPanel(new BorderLayout(0, 5));
        texts.setOpaque(false);
        texts.add(title, BorderLayout.NORTH);
        texts.add(sub, BorderLayout.SOUTH);
        header.add(texts, BorderLayout.WEST);

        JPanel content = new JPanel(new GridBagLayout());
        content.setBackground(Color.WHITE);
        content.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(22, 22, 22, 22),
                BorderFactory.createLineBorder(new Color(218, 230, 228))));
        JPanel message = new JPanel(new BorderLayout(0, 14));
        message.setOpaque(false);
        JLabel icon = new JLabel(iconText, SwingConstants.CENTER);
        icon.setOpaque(true);
        icon.setBackground(new Color(226, 244, 239));
        icon.setForeground(TEAL);
        icon.setFont(new Font("SansSerif", Font.BOLD, 28));
        icon.setBorder(BorderFactory.createEmptyBorder(18, 20, 18, 20));
        JLabel headline = new JLabel("Modul " + module + " siap diakses");
        headline.setForeground(NAVY);
        headline.setFont(new Font("SansSerif", Font.BOLD, 20));
        JLabel detail = new JLabel("<html><div style='text-align:center;width:460px'>" + description
                + "<br><br>Kerangka navigasi ini disiapkan pada Pertemuan 11. Pengembangan CRUD lengkap "
                + "mengikuti pola DAO dan JTable pada modul Master Peserta.</div></html>");
        detail.setForeground(new Color(103, 124, 132));
        detail.setFont(new Font("SansSerif", Font.PLAIN, 13));
        detail.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel copy = new JPanel(new BorderLayout(0, 10));
        copy.setOpaque(false);
        copy.add(headline, BorderLayout.NORTH);
        copy.add(detail, BorderLayout.CENTER);
        message.add(icon, BorderLayout.NORTH);
        message.add(copy, BorderLayout.CENTER);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(30, 20, 30, 20);
        content.add(message, c);

        root.add(header, BorderLayout.NORTH);
        root.add(content, BorderLayout.CENTER);
        setContentPane(root);
    }
}
