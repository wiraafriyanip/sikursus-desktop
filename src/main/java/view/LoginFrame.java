package view;

import dao.UserDAO;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Cursor;
import java.awt.Font;
import java.sql.SQLException;
import java.util.Arrays;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.text.JTextComponent;
import model.User;
import util.UserSession;

/**
 * JFrame Form NetBeans untuk autentikasi pengguna SIKURSUS.
 * Layout visual berada pada LoginFrame.form; logika autentikasi tetap di UserDAO.
 */
public class LoginFrame extends javax.swing.JFrame {

    private static final Color NAVY = new Color(19, 48, 63);
    private static final Color TEAL = new Color(10, 151, 119);
    private static final Color TEAL_DARK = new Color(7, 126, 101);
    private static final Color BG = new Color(242, 247, 246);
    private static final Color TEXT = new Color(35, 58, 68);
    private static final Color MUTED = new Color(112, 132, 140);
    private static final Color LINE = new Color(218, 230, 228);

    private final UserDAO userDAO = new UserDAO();

    public LoginFrame() {
        initComponents();
        setMinimumSize(new Dimension(920, 620));
        setSize(1120, 720);
        setLocationRelativeTo(null);
        setupVisualTheme();
        setupActions();
    }

    private void setupVisualTheme() {
        pnlRoot.setBackground(BG);
        pnlBrand.setBackground(NAVY);
        pnlRight.setBackground(BG);
        pnlCard.setBackground(Color.WHITE);
        pnlCard.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(LINE),
                BorderFactory.createEmptyBorder(38, 42, 34, 42)));

        pnlBrand.setPreferredSize(new Dimension(340, 1));
        pnlCard.setPreferredSize(new Dimension(520, 500));

        lblBrand.setFont(new Font("SansSerif", Font.BOLD, 32));
        lblBrandCaption.setFont(new Font("SansSerif", Font.BOLD, 11));
        lblBrandDesc.setFont(new Font("SansSerif", Font.BOLD, 23));
        lblBrandFooter.setFont(new Font("SansSerif", Font.BOLD, 10));

        lblEyebrow.setFont(new Font("SansSerif", Font.BOLD, 11));
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 27));
        lblSubtitle.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblUsername.setFont(new Font("SansSerif", Font.BOLD, 11));
        lblPassword.setFont(new Font("SansSerif", Font.BOLD, 11));
        lblInfo.setFont(new Font("SansSerif", Font.PLAIN, 11));
        lblSecurity.setFont(new Font("SansSerif", Font.PLAIN, 10));

        for (JTextComponent field : new JTextComponent[]{txtUsername, pwdPassword}) {
            field.setFont(new Font("SansSerif", Font.PLAIN, 14));
            field.setForeground(TEXT);
            field.setBackground(Color.WHITE);
            field.setCaretColor(TEAL);
            field.setSelectionColor(new Color(199, 232, 225));
            field.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(198, 216, 214)),
                    BorderFactory.createEmptyBorder(10, 12, 10, 12)));
            field.setPreferredSize(new Dimension(420, 44));
        }

        styleButton(btnLogin, TEAL, Color.WHITE, true);
        styleButton(btnKeluar, new Color(238, 243, 244), NAVY, false);
        btnLogin.setPreferredSize(new Dimension(300, 46));
        btnKeluar.setPreferredSize(new Dimension(105, 46));
    }

    private void styleButton(JButton button, Color background, Color foreground, boolean primary) {
        // BasicButtonUI menjaga warna tombol tetap terlihat pada Windows Look and Feel.
        button.setUI(new BasicButtonUI());
        button.setFont(new Font("SansSerif", Font.BOLD, 11));
        button.setBackground(background);
        button.setForeground(foreground);
        button.setOpaque(true);
        button.setContentAreaFilled(true);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(12, 16, 12, 16));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setToolTipText(primary ? "Masuk ke aplikasi SIKURSUS" : "Keluar dari aplikasi");
    }

    private void setupActions() {
        getRootPane().setDefaultButton(btnLogin);
        btnLogin.addActionListener(e -> attemptLogin());
        btnKeluar.addActionListener(e -> {
            int answer = JOptionPane.showConfirmDialog(this,
                    "Yakin ingin keluar dari aplikasi SIKURSUS?",
                    "Konfirmasi Keluar", JOptionPane.YES_NO_OPTION);
            if (answer == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
    }

    private void attemptLogin() {
        String username = txtUsername.getText().trim();
        char[] password = pwdPassword.getPassword();

        if (username.isEmpty() || password.length == 0) {
            JOptionPane.showMessageDialog(this,
                    "Username dan password wajib diisi.",
                    "Data Belum Lengkap", JOptionPane.WARNING_MESSAGE);
            Arrays.fill(password, '\0');
            return;
        }

        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        btnLogin.setEnabled(false);
        lblInfo.setText("Memeriksa akun...");
        try {
            User user = userDAO.authenticate(username, password);
            if (user == null) {
                lblInfo.setText("Username atau password tidak sesuai.");
                JOptionPane.showMessageDialog(this,
                        "Login gagal. Periksa username, password, atau status akun.",
                        "Login Gagal", JOptionPane.WARNING_MESSAGE);
                pwdPassword.setText("");
                pwdPassword.requestFocus();
                return;
            }

            UserSession.setCurrentUser(user);
            dispose();
            SwingUtilities.invokeLater(() -> new DashboardFrame().setVisible(true));
        } catch (SQLException ex) {
            lblInfo.setText("Koneksi database bermasalah.");
            JOptionPane.showMessageDialog(this,
                    "Tidak dapat mengakses database.\n"
                    + "Pastikan Laragon/MySQL aktif dan konfigurasi Database.java benar.\n\n"
                    + "Detail: " + ex.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            Arrays.fill(password, '\0');
            btnLogin.setEnabled(true);
            setCursor(Cursor.getDefaultCursor());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        pnlRoot = new javax.swing.JPanel();
        pnlBrand = new javax.swing.JPanel();
        lblBrand = new javax.swing.JLabel();
        lblBrandCaption = new javax.swing.JLabel();
        lblBrandDesc = new javax.swing.JLabel();
        lblBrandFooter = new javax.swing.JLabel();
        pnlRight = new javax.swing.JPanel();
        pnlCard = new javax.swing.JPanel();
        lblEyebrow = new javax.swing.JLabel();
        lblTitle = new javax.swing.JLabel();
        lblSubtitle = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        lblPassword = new javax.swing.JLabel();
        pwdPassword = new javax.swing.JPasswordField();
        lblInfo = new javax.swing.JLabel();
        pnlActions = new javax.swing.JPanel();
        btnLogin = new javax.swing.JButton();
        btnKeluar = new javax.swing.JButton();
        lblSecurity = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login - SIKURSUS Desktop");
        getContentPane().setLayout(new java.awt.GridBagLayout());

        pnlRoot.setBackground(new java.awt.Color(242, 247, 246));
        pnlRoot.setLayout(new java.awt.GridBagLayout());

        pnlBrand.setBackground(new java.awt.Color(19, 48, 63));
        pnlBrand.setLayout(new java.awt.GridBagLayout());

        lblBrand.setText("SIKURSUS");
        lblBrand.setForeground(new java.awt.Color(255, 255, 255));
        lblBrand.setFont(new java.awt.Font("SansSerif", 1, 32)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(52, 40, 6, 32);
        pnlBrand.add(lblBrand, gridBagConstraints);

        lblBrandCaption.setText("SISTEM INFORMASI");
        lblBrandCaption.setForeground(new java.awt.Color(159, 220, 207));
        lblBrandCaption.setFont(new java.awt.Font("SansSerif", 1, 11)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(28, 42, 7, 32);
        pnlBrand.add(lblBrandCaption, gridBagConstraints);

        lblBrandDesc.setText("<html>Manajemen Kursus<br>&amp; Pelatihan</html>");
        lblBrandDesc.setForeground(new java.awt.Color(255, 255, 255));
        lblBrandDesc.setFont(new java.awt.Font("SansSerif", 1, 22)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 42, 10, 32);
        pnlBrand.add(lblBrandDesc, gridBagConstraints);

        lblBrandFooter.setForeground(new java.awt.Color(170, 211, 205));
        lblBrandFooter.setFont(new java.awt.Font("SansSerif", 1, 10)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 42, 34, 32);
        pnlBrand.add(lblBrandFooter, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.34;
        gridBagConstraints.weighty = 1.0;
        pnlRoot.add(pnlBrand, gridBagConstraints);

        pnlRight.setBackground(new java.awt.Color(242, 247, 246));
        pnlRight.setLayout(new java.awt.GridBagLayout());

        pnlCard.setBackground(new java.awt.Color(255, 255, 255));
        pnlCard.setLayout(new java.awt.GridBagLayout());

        lblEyebrow.setText("AKSES PENGGUNA");
        lblEyebrow.setForeground(new java.awt.Color(7, 126, 101));
        lblEyebrow.setFont(new java.awt.Font("SansSerif", 1, 11)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 8, 0);
        pnlCard.add(lblEyebrow, gridBagConstraints);

        lblTitle.setText("Selamat datang!");
        lblTitle.setForeground(new java.awt.Color(19, 48, 63));
        lblTitle.setFont(new java.awt.Font("SansSerif", 1, 27)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 7, 0);
        pnlCard.add(lblTitle, gridBagConstraints);

        lblSubtitle.setText("Silakan masuk untuk melanjutkan ke dashboard SIKURSUS.");
        lblSubtitle.setForeground(new java.awt.Color(112, 132, 140));
        lblSubtitle.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 27, 0);
        pnlCard.add(lblSubtitle, gridBagConstraints);

        lblUsername.setText("USERNAME");
        lblUsername.setForeground(new java.awt.Color(35, 58, 68));
        lblUsername.setFont(new java.awt.Font("SansSerif", 1, 11)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 7, 0);
        pnlCard.add(lblUsername, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 19, 0);
        pnlCard.add(txtUsername, gridBagConstraints);

        lblPassword.setText("PASSWORD");
        lblPassword.setForeground(new java.awt.Color(35, 58, 68));
        lblPassword.setFont(new java.awt.Font("SansSerif", 1, 11)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 7, 0);
        pnlCard.add(lblPassword, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 13, 0);
        pnlCard.add(pwdPassword, gridBagConstraints);

        lblInfo.setText("Masukkan akun SIKURSUS Anda untuk melanjutkan.");
        lblInfo.setForeground(new java.awt.Color(112, 132, 140));
        lblInfo.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 23, 0);
        pnlCard.add(lblInfo, gridBagConstraints);

        pnlActions.setBackground(new java.awt.Color(255, 255, 255));
        pnlActions.setLayout(new java.awt.GridBagLayout());

        btnLogin.setBackground(new java.awt.Color(10, 151, 119));
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setText("MASUK KE APLIKASI");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        pnlActions.add(btnLogin, gridBagConstraints);

        btnKeluar.setBackground(new java.awt.Color(238, 243, 244));
        btnKeluar.setForeground(new java.awt.Color(19, 48, 63));
        btnKeluar.setText("KELUAR");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        pnlActions.add(btnKeluar, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 25, 0);
        pnlCard.add(pnlActions, gridBagConstraints);

        lblSecurity.setText("Gunakan akun yang telah terdaftar pada sistem.");
        lblSecurity.setForeground(new java.awt.Color(139, 155, 160));
        lblSecurity.setFont(new java.awt.Font("SansSerif", 0, 10)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlCard.add(lblSecurity, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 20, 20);
        pnlRight.add(pnlCard, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlRoot.add(pnlRight, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(pnlRoot, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnKeluar;
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel lblBrand;
    private javax.swing.JLabel lblBrandCaption;
    private javax.swing.JLabel lblBrandDesc;
    private javax.swing.JLabel lblBrandFooter;
    private javax.swing.JLabel lblEyebrow;
    private javax.swing.JLabel lblInfo;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblSecurity;
    private javax.swing.JLabel lblSubtitle;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JPanel pnlActions;
    private javax.swing.JPanel pnlBrand;
    private javax.swing.JPanel pnlCard;
    private javax.swing.JPanel pnlRight;
    private javax.swing.JPanel pnlRoot;
    private javax.swing.JPasswordField pwdPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
