package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.BasicButtonUI;
import model.User;
import util.UserSession;
import util.WindowManager;

/**
 * JFrame Form NetBeans sebagai dashboard dan pusat navigasi SIKURSUS.
 * Komponen visual dapat diedit melalui DashboardFrame.form.
 */
public class DashboardFrame extends javax.swing.JFrame {

    private static final Color NAVY = new Color(19, 48, 63);
    private static final Color TEAL = new Color(10, 151, 119);
    private static final Color BG = new Color(243, 247, 246);
    private static final Color MUTED = new Color(112, 132, 140);
    private static final Color LINE = new Color(218, 230, 228);

    private JMenu menuMaster;
    private JMenu menuTransaksi;
    private JMenu menuLaporan;
    private JMenu menuKeluar;
    private JMenuItem miPeserta;
    private JMenuItem miInstruktur;
    private JMenuItem miKursus;
    private JMenuItem miKelas;
    private JMenuItem miPendaftaran;
    private JMenuItem miPembayaran;
    private JMenuItem miLaporanPeserta;
    private JMenuItem miLaporanPendaftaran;
    private JMenuItem miLogout;
    private JMenuItem miTutup;

    public DashboardFrame() {
        initComponents();
        setMinimumSize(new Dimension(1020, 650));
        setSize(1180, 760);
        setLocationRelativeTo(null);
        setupVisualTheme();
        setupMenuBar();
        setupActions();
        applySession();

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                WindowManager.closeAll();
                UserSession.clear();
            }
        });
    }

    private void setupVisualTheme() {
        pnlRoot.setBackground(BG);
        pnlHeader.setBackground(NAVY);
        pnlContent.setBackground(BG);
        pnlDashboard.setBackground(Color.WHITE);
        pnlDashboard.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(LINE),
                BorderFactory.createEmptyBorder(8, 8, 8, 8)));
        pnlQuickAccess.setBackground(Color.WHITE);
        pnlFooter.setBackground(BG);

        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 23));
        lblSubtitle.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblUserCaption.setFont(new Font("SansSerif", Font.BOLD, 10));
        lblUser.setFont(new Font("SansSerif", Font.BOLD, 15));
        lblRole.setFont(new Font("SansSerif", Font.BOLD, 11));
        lblWelcome.setFont(new Font("SansSerif", Font.BOLD, 25));
        lblDescription.setFont(new Font("SansSerif", Font.PLAIN, 13));
        lblHint.setFont(new Font("SansSerif", Font.ITALIC, 11));
        lblStatus.setFont(new Font("SansSerif", Font.PLAIN, 11));

        styleCard(pnlPeserta, lblPesertaTitle, lblPesertaDesc);
        styleCard(pnlInstruktur, lblInstrukturTitle, lblInstrukturDesc);
        styleCard(pnlKursus, lblKursusTitle, lblKursusDesc);
        styleCard(pnlKelas, lblKelasTitle, lblKelasDesc);

        styleButton(btnPeserta, TEAL);
        styleButton(btnInstruktur, new Color(45, 107, 132));
        styleButton(btnKursus, new Color(45, 107, 132));
        styleButton(btnKelas, new Color(45, 107, 132));
    }

    private void styleCard(javax.swing.JPanel panel, javax.swing.JLabel title,
            javax.swing.JLabel description) {
        panel.setBackground(new Color(248, 251, 250));
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(222, 233, 231)),
                BorderFactory.createEmptyBorder(4, 4, 4, 4)));
        title.setForeground(NAVY);
        description.setForeground(MUTED);
        title.setFont(new Font("SansSerif", Font.BOLD, 12));
        description.setFont(new Font("SansSerif", Font.PLAIN, 11));
    }

    private void styleButton(JButton button, Color color) {
        button.setUI(new BasicButtonUI());
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("SansSerif", Font.BOLD, 10));
        button.setOpaque(true);
        button.setContentAreaFilled(true);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 12, 10, 12));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    private void setupMenuBar() {
        JMenuBar bar = new JMenuBar();
        bar.setBackground(Color.WHITE);
        bar.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, LINE));

        menuMaster = new JMenu("Master");
        menuTransaksi = new JMenu("Transaksi");
        menuLaporan = new JMenu("Laporan");
        menuKeluar = new JMenu("Keluar");

        miPeserta = new JMenuItem("Peserta");
        miInstruktur = new JMenuItem("Instruktur");
        miKursus = new JMenuItem("Kursus");
        miKelas = new JMenuItem("Kelas");
        miPeserta.setIcon(loadIcon("/icons/peserta.png"));
        miInstruktur.setIcon(loadIcon("/icons/instruktur.png"));
        miKursus.setIcon(loadIcon("/icons/kursus.png"));
        miKelas.setIcon(loadIcon("/icons/kelas.png"));
        menuMaster.add(miPeserta);
        menuMaster.add(miInstruktur);
        menuMaster.add(miKursus);
        menuMaster.add(miKelas);

        miPendaftaran = new JMenuItem("Pendaftaran");
        miPembayaran = new JMenuItem("Pembayaran");
        menuTransaksi.add(miPendaftaran);
        menuTransaksi.add(miPembayaran);

        miLaporanPeserta = new JMenuItem("Laporan Peserta");
        miLaporanPendaftaran = new JMenuItem("Laporan Pendaftaran");
        menuLaporan.add(miLaporanPeserta);
        menuLaporan.add(miLaporanPendaftaran);

        miLogout = new JMenuItem("Logout");
        miTutup = new JMenuItem("Tutup Aplikasi");
        menuKeluar.add(miLogout);
        menuKeluar.addSeparator();
        menuKeluar.add(miTutup);

        bar.add(menuMaster);
        bar.add(menuTransaksi);
        bar.add(menuLaporan);
        bar.add(menuKeluar);
        setJMenuBar(bar);

        miPeserta.addActionListener(e -> WindowManager.open(PesertaFrame.class, PesertaFrame::new));
        miInstruktur.addActionListener(e -> openInstruktur());
        miKursus.addActionListener(e -> openKursus());
        miKelas.addActionListener(e -> openKelas());
        miPendaftaran.addActionListener(e -> showComingSoon("Pendaftaran"));
        miPembayaran.addActionListener(e -> showComingSoon("Pembayaran"));
        miLaporanPeserta.addActionListener(e -> showComingSoon("Laporan Peserta"));
        miLaporanPendaftaran.addActionListener(e -> showComingSoon("Laporan Pendaftaran"));
        miLogout.addActionListener(e -> logout());
        miTutup.addActionListener(e -> exitApplication());
    }

    private javax.swing.ImageIcon loadIcon(String path) {
        java.net.URL resource = getClass().getResource(path);
        return resource == null ? null : new javax.swing.ImageIcon(resource);
    }

    private void setupActions() {
        btnPeserta.addActionListener(e -> WindowManager.open(PesertaFrame.class, PesertaFrame::new));
        btnInstruktur.addActionListener(e -> openInstruktur());
        btnKursus.addActionListener(e -> openKursus());
        btnKelas.addActionListener(e -> openKelas());

        JPopupMenu popup = new JPopupMenu();
        JMenuItem miTentang = new JMenuItem("Tentang Aplikasi");
        JMenuItem miRefresh = new JMenuItem("Refresh Dashboard");
        popup.add(miTentang);
        popup.add(miRefresh);
        miTentang.addActionListener(e -> JOptionPane.showMessageDialog(this,
                "SIKURSUS Desktop\nProject PBO - Program Studi Informatika\n"
                + "Pertemuan 11: Menu, Navigasi, Login, dan Integrasi Modul.",
                "Tentang Aplikasi", JOptionPane.INFORMATION_MESSAGE));
        miRefresh.addActionListener(e -> refreshDashboard());
        pnlDashboard.setComponentPopupMenu(popup);
        pnlContent.setComponentPopupMenu(popup);
    }

    private void applySession() {
        User user = UserSession.getCurrentUser();
        if (user == null) {
            JOptionPane.showMessageDialog(this,
                    "Session tidak ditemukan. Silakan login kembali.",
                    "Session Berakhir", JOptionPane.WARNING_MESSAGE);
            dispose();
            SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
            return;
        }

        lblUser.setText(user.getUsername());
        lblRole.setText("ROLE: " + user.getRole());

        boolean admin = user.isAdmin();
        miInstruktur.setEnabled(admin);
        miKursus.setEnabled(admin);
        miKelas.setEnabled(admin);
        btnInstruktur.setEnabled(admin);
        btnKursus.setEnabled(admin);
        btnKelas.setEnabled(admin);
    }

    private void openInstruktur() {
        if (UserSession.isAdmin()) {
            WindowManager.open(InstrukturFrame.class, InstrukturFrame::new);
        } else {
            showAccessDenied();
        }
    }

    private void openKursus() {
        if (UserSession.isAdmin()) {
            WindowManager.open(KursusFrame.class, KursusFrame::new);
        } else {
            showAccessDenied();
        }
    }

    private void openKelas() {
        if (UserSession.isAdmin()) {
            WindowManager.open(KelasFrame.class, KelasFrame::new);
        } else {
            showAccessDenied();
        }
    }

    private void showAccessDenied() {
        JOptionPane.showMessageDialog(this,
                "Akses modul ini hanya tersedia untuk ADMIN.",
                "Akses Ditolak", JOptionPane.WARNING_MESSAGE);
    }

    private void showComingSoon(String module) {
        String message = module.startsWith("Laporan")
                ? "Modul " + module + " akan diintegrasikan dengan JasperReports pada pertemuan berikutnya."
                : "Modul " + module + " sedang disiapkan untuk tahap pengembangan berikutnya.";
        JOptionPane.showMessageDialog(this, message, module, JOptionPane.INFORMATION_MESSAGE);
    }

    private void refreshDashboard() {
        applySession();
        JOptionPane.showMessageDialog(this,
                "Informasi dashboard berhasil diperbarui.",
                "Refresh Dashboard", JOptionPane.INFORMATION_MESSAGE);
    }

    private void logout() {
        int choice = JOptionPane.showConfirmDialog(this,
                "Logout dari aplikasi SIKURSUS?", "Konfirmasi Logout",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (choice == JOptionPane.YES_OPTION) {
            WindowManager.closeAll();
            UserSession.clear();
            dispose();
            SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
        }
    }

    private void exitApplication() {
        int choice = JOptionPane.showConfirmDialog(this,
                "Tutup aplikasi SIKURSUS?", "Konfirmasi",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (choice == JOptionPane.YES_OPTION) {
            WindowManager.closeAll();
            UserSession.clear();
            System.exit(0);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        pnlRoot = new javax.swing.JPanel();
        pnlHeader = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        lblSubtitle = new javax.swing.JLabel();
        lblUserCaption = new javax.swing.JLabel();
        lblUser = new javax.swing.JLabel();
        lblRole = new javax.swing.JLabel();
        pnlContent = new javax.swing.JPanel();
        pnlDashboard = new javax.swing.JPanel();
        lblWelcome = new javax.swing.JLabel();
        lblDescription = new javax.swing.JLabel();
        pnlQuickAccess = new javax.swing.JPanel();
        pnlPeserta = new javax.swing.JPanel();
        lblPesertaTitle = new javax.swing.JLabel();
        lblPesertaDesc = new javax.swing.JLabel();
        btnPeserta = new javax.swing.JButton();
        pnlInstruktur = new javax.swing.JPanel();
        lblInstrukturTitle = new javax.swing.JLabel();
        lblInstrukturDesc = new javax.swing.JLabel();
        btnInstruktur = new javax.swing.JButton();
        pnlKursus = new javax.swing.JPanel();
        lblKursusTitle = new javax.swing.JLabel();
        lblKursusDesc = new javax.swing.JLabel();
        btnKursus = new javax.swing.JButton();
        pnlKelas = new javax.swing.JPanel();
        lblKelasTitle = new javax.swing.JLabel();
        lblKelasDesc = new javax.swing.JLabel();
        btnKelas = new javax.swing.JButton();
        lblHint = new javax.swing.JLabel();
        pnlFooter = new javax.swing.JPanel();
        lblStatus = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Dashboard - SIKURSUS Desktop");
        getContentPane().setLayout(new java.awt.GridBagLayout());

        pnlRoot.setBackground(new java.awt.Color(243, 247, 246));
        pnlRoot.setLayout(new java.awt.GridBagLayout());

        pnlHeader.setBackground(new java.awt.Color(19, 48, 63));
        pnlHeader.setLayout(new java.awt.GridBagLayout());

        lblTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle.setFont(new java.awt.Font("SansSerif", 1, 23));
        lblTitle.setText("SIKURSUS DESKTOP");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0; gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(20, 28, 4, 20);
        pnlHeader.add(lblTitle, gridBagConstraints);

        lblSubtitle.setForeground(new java.awt.Color(202, 224, 226));
        lblSubtitle.setFont(new java.awt.Font("SansSerif", 0, 12));
        lblSubtitle.setText("Sistem Informasi Manajemen Kursus & Pelatihan");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0; gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 28, 20, 20);
        pnlHeader.add(lblSubtitle, gridBagConstraints);

        lblUserCaption.setForeground(new java.awt.Color(170, 205, 202));
        lblUserCaption.setFont(new java.awt.Font("SansSerif", 1, 10));
        lblUserCaption.setText("LOGIN SEBAGAI");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1; gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(15, 12, 2, 28);
        pnlHeader.add(lblUserCaption, gridBagConstraints);

        lblUser.setForeground(new java.awt.Color(255, 255, 255));
        lblUser.setFont(new java.awt.Font("SansSerif", 1, 15));
        lblUser.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1; gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 12, 2, 28);
        pnlHeader.add(lblUser, gridBagConstraints);

        lblRole.setForeground(new java.awt.Color(163, 225, 210));
        lblRole.setFont(new java.awt.Font("SansSerif", 1, 11));
        lblRole.setText("ROLE: -");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1; gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 12, 15, 28);
        pnlHeader.add(lblRole, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0; gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        pnlRoot.add(pnlHeader, gridBagConstraints);

        pnlContent.setBackground(new java.awt.Color(243, 247, 246));
        pnlContent.setLayout(new java.awt.GridBagLayout());

        pnlDashboard.setBackground(java.awt.Color.white);
        pnlDashboard.setLayout(new java.awt.GridBagLayout());

        lblWelcome.setForeground(new java.awt.Color(19, 48, 63));
        lblWelcome.setFont(new java.awt.Font("SansSerif", 1, 25));
        lblWelcome.setText("Selamat datang di Dashboard");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0; gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(26, 28, 7, 28);
        pnlDashboard.add(lblWelcome, gridBagConstraints);

        lblDescription.setForeground(new java.awt.Color(112, 132, 140));
        lblDescription.setFont(new java.awt.Font("SansSerif", 0, 13));
        lblDescription.setText("Pilih menu di bagian atas atau gunakan akses cepat untuk membuka modul.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0; gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 28, 22, 28);
        pnlDashboard.add(lblDescription, gridBagConstraints);

        pnlQuickAccess.setBackground(java.awt.Color.white);
        pnlQuickAccess.setLayout(new java.awt.GridBagLayout());

        pnlPeserta.setBackground(new java.awt.Color(248, 251, 250));
        pnlPeserta.setLayout(new java.awt.GridBagLayout());
        lblPesertaTitle.setForeground(new java.awt.Color(19, 48, 63));
        lblPesertaTitle.setFont(new java.awt.Font("SansSerif", 1, 12));
        lblPesertaTitle.setText("MASTER PESERTA");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0; gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(17, 16, 9, 16);
        pnlPeserta.add(lblPesertaTitle, gridBagConstraints);
        lblPesertaDesc.setForeground(new java.awt.Color(112, 132, 140));
        lblPesertaDesc.setFont(new java.awt.Font("SansSerif", 0, 11));
        lblPesertaDesc.setText("Kelola data peserta dan pencarian");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0; gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0; gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 16, 10, 16);
        pnlPeserta.add(lblPesertaDesc, gridBagConstraints);
        btnPeserta.setBackground(new java.awt.Color(10, 151, 119));
        btnPeserta.setForeground(new java.awt.Color(255, 255, 255));
        btnPeserta.setOpaque(true);
        btnPeserta.setText("BUKA MODUL");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0; gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 16, 16, 16);
        pnlPeserta.add(btnPeserta, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0; gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0; gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 7);
        pnlQuickAccess.add(pnlPeserta, gridBagConstraints);

        pnlInstruktur.setBackground(new java.awt.Color(248, 251, 250));
        pnlInstruktur.setLayout(new java.awt.GridBagLayout());
        lblInstrukturTitle.setForeground(new java.awt.Color(19, 48, 63));
        lblInstrukturTitle.setFont(new java.awt.Font("SansSerif", 1, 12));
        lblInstrukturTitle.setText("MASTER INSTRUKTUR");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0; gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(17, 16, 9, 16);
        pnlInstruktur.add(lblInstrukturTitle, gridBagConstraints);
        lblInstrukturDesc.setForeground(new java.awt.Color(112, 132, 140));
        lblInstrukturDesc.setFont(new java.awt.Font("SansSerif", 0, 11));
        lblInstrukturDesc.setText("Data instruktur dan keahlian");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0; gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0; gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 16, 10, 16);
        pnlInstruktur.add(lblInstrukturDesc, gridBagConstraints);
        btnInstruktur.setBackground(new java.awt.Color(45, 107, 132));
        btnInstruktur.setForeground(new java.awt.Color(255, 255, 255));
        btnInstruktur.setOpaque(true);
        btnInstruktur.setText("BUKA MODUL");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0; gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 16, 16, 16);
        pnlInstruktur.add(btnInstruktur, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1; gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0; gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 7, 0, 7);
        pnlQuickAccess.add(pnlInstruktur, gridBagConstraints);

        pnlKursus.setBackground(new java.awt.Color(248, 251, 250));
        pnlKursus.setLayout(new java.awt.GridBagLayout());
        lblKursusTitle.setForeground(new java.awt.Color(19, 48, 63));
        lblKursusTitle.setFont(new java.awt.Font("SansSerif", 1, 12));
        lblKursusTitle.setText("MASTER KURSUS");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0; gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(17, 16, 9, 16);
        pnlKursus.add(lblKursusTitle, gridBagConstraints);
        lblKursusDesc.setForeground(new java.awt.Color(112, 132, 140));
        lblKursusDesc.setFont(new java.awt.Font("SansSerif", 0, 11));
        lblKursusDesc.setText("Program dan biaya kursus");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0; gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0; gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 16, 10, 16);
        pnlKursus.add(lblKursusDesc, gridBagConstraints);
        btnKursus.setBackground(new java.awt.Color(45, 107, 132));
        btnKursus.setForeground(new java.awt.Color(255, 255, 255));
        btnKursus.setOpaque(true);
        btnKursus.setText("BUKA MODUL");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0; gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 16, 16, 16);
        pnlKursus.add(btnKursus, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2; gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0; gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 7, 0, 7);
        pnlQuickAccess.add(pnlKursus, gridBagConstraints);

        pnlKelas.setBackground(new java.awt.Color(248, 251, 250));
        pnlKelas.setLayout(new java.awt.GridBagLayout());
        lblKelasTitle.setForeground(new java.awt.Color(19, 48, 63));
        lblKelasTitle.setFont(new java.awt.Font("SansSerif", 1, 12));
        lblKelasTitle.setText("MASTER KELAS");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0; gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(17, 16, 9, 16);
        pnlKelas.add(lblKelasTitle, gridBagConstraints);
        lblKelasDesc.setForeground(new java.awt.Color(112, 132, 140));
        lblKelasDesc.setFont(new java.awt.Font("SansSerif", 0, 11));
        lblKelasDesc.setText("Jadwal kelas dan pelatihan");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0; gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0; gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 16, 10, 16);
        pnlKelas.add(lblKelasDesc, gridBagConstraints);
        btnKelas.setBackground(new java.awt.Color(45, 107, 132));
        btnKelas.setForeground(new java.awt.Color(255, 255, 255));
        btnKelas.setOpaque(true);
        btnKelas.setText("BUKA MODUL");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0; gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 16, 16, 16);
        pnlKelas.add(btnKelas, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3; gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0; gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 7, 0, 0);
        pnlQuickAccess.add(pnlKelas, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0; gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0; gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 28, 18, 28);
        pnlDashboard.add(pnlQuickAccess, gridBagConstraints);

        lblHint.setForeground(new java.awt.Color(112, 132, 140));
        lblHint.setFont(new java.awt.Font("SansSerif", 2, 11));
        lblHint.setText("Tips: klik kanan area dashboard untuk menampilkan menu pintasan.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0; gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 28, 24, 28);
        pnlDashboard.add(lblHint, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0; gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0; gridBagConstraints.weighty = 1.0;
        pnlContent.add(pnlDashboard, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0; gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0; gridBagConstraints.weighty = 1.0;
        pnlRoot.add(pnlContent, gridBagConstraints);

        pnlFooter.setBackground(new java.awt.Color(243, 247, 246));
        pnlFooter.setLayout(new java.awt.GridBagLayout());
        lblStatus.setForeground(new java.awt.Color(112, 132, 140));
        lblStatus.setFont(new java.awt.Font("SansSerif", 0, 11));
        lblStatus.setText("SIKURSUS Desktop  •  PBO 3 SKS  •  Pertemuan 11");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0; gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 28, 12, 28);
        pnlFooter.add(lblStatus, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0; gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        pnlRoot.add(pnlFooter, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0; gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0; gridBagConstraints.weighty = 1.0;
        getContentPane().add(pnlRoot, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnInstruktur;
    private javax.swing.JButton btnKelas;
    private javax.swing.JButton btnKursus;
    private javax.swing.JButton btnPeserta;
    private javax.swing.JLabel lblDescription;
    private javax.swing.JLabel lblHint;
    private javax.swing.JLabel lblInstrukturDesc;
    private javax.swing.JLabel lblInstrukturTitle;
    private javax.swing.JLabel lblKelasDesc;
    private javax.swing.JLabel lblKelasTitle;
    private javax.swing.JLabel lblKursusDesc;
    private javax.swing.JLabel lblKursusTitle;
    private javax.swing.JLabel lblPesertaDesc;
    private javax.swing.JLabel lblPesertaTitle;
    private javax.swing.JLabel lblRole;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblSubtitle;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblUser;
    private javax.swing.JLabel lblUserCaption;
    private javax.swing.JLabel lblWelcome;
    private javax.swing.JPanel pnlContent;
    private javax.swing.JPanel pnlDashboard;
    private javax.swing.JPanel pnlFooter;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JPanel pnlInstruktur;
    private javax.swing.JPanel pnlKelas;
    private javax.swing.JPanel pnlKursus;
    private javax.swing.JPanel pnlPeserta;
    private javax.swing.JPanel pnlQuickAccess;
    private javax.swing.JPanel pnlRoot;
    // End of variables declaration//GEN-END:variables
}
