package view;

import java.awt.Color;
import java.awt.Dimension;
import java.sql.Date;
import javax.swing.JOptionPane;
import report.ReportService;

/** JFrame Form untuk filter laporan pembayaran berdasarkan rentang tanggal. */
public class PembayaranReportFrame extends javax.swing.JFrame {
    public PembayaranReportFrame() {
        initComponents();
        setMinimumSize(new Dimension(700, 470));
        setSize(820, 540);
        setLocationRelativeTo(null);
        setupVisuals();
        setupActions();
    }

    private void setupVisuals() {
        MasterFormSupport.styleCard(pnlCard);
        txtTanggalAwal.setToolTipText("Format tanggal: YYYY-MM-DD, contoh 2026-11-01");
        txtTanggalAkhir.setToolTipText("Format tanggal: YYYY-MM-DD, contoh 2026-11-30");
        for (javax.swing.JTextField field : new javax.swing.JTextField[]{txtTanggalAwal, txtTanggalAkhir}) {
            field.setFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 14));
            field.setForeground(MasterFormSupport.INK);
            field.setBackground(Color.WHITE);
            field.setCaretColor(MasterFormSupport.TEAL);
            field.setMargin(new java.awt.Insets(7, 10, 7, 10));
            field.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                    javax.swing.BorderFactory.createLineBorder(MasterFormSupport.LINE),
                    javax.swing.BorderFactory.createEmptyBorder(4, 7, 4, 7)));
        }
    }

    private void setupActions() {
        btnTampilkan.addActionListener(e -> tampilkanLaporan());
        btnReset.addActionListener(e -> {
            txtTanggalAwal.setText("");
            txtTanggalAkhir.setText("");
            lblStatus.setText("Masukkan periode tanggal untuk menampilkan laporan.");
            txtTanggalAwal.requestFocusInWindow();
        });
        getRootPane().setDefaultButton(btnTampilkan);
    }

    private void tampilkanLaporan() {
        String awalText = txtTanggalAwal.getText().trim();
        String akhirText = txtTanggalAkhir.getText().trim();
        if (awalText.isEmpty() || akhirText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tanggal awal dan tanggal akhir wajib diisi.",
                    "Validasi Tanggal", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            Date awal = Date.valueOf(awalText);
            Date akhir = Date.valueOf(akhirText);
            if (awal.after(akhir)) {
                JOptionPane.showMessageDialog(this, "Tanggal awal tidak boleh setelah tanggal akhir.",
                        "Validasi Tanggal", JOptionPane.WARNING_MESSAGE);
                return;
            }
            lblStatus.setText("Menampilkan transaksi " + awal + " sampai " + akhir + "...");
            ReportService.cetakPembayaran(awal, akhir);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this,
                    "Format tanggal harus YYYY-MM-DD. Contoh: 2026-11-01.",
                    "Format Tanggal", JOptionPane.WARNING_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gbc;
        pnlRoot = new javax.swing.JPanel(new java.awt.GridBagLayout());
        pnlRoot.setBackground(MasterFormSupport.BG);
        pnlHeader = new javax.swing.JPanel(new java.awt.GridBagLayout());
        pnlHeader.setBackground(MasterFormSupport.NAVY);
        MasterFormSupport.styleHeader(pnlHeader);
        lblTitle = new javax.swing.JLabel("LAPORAN PEMBAYARAN");
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 22));
        lblSubTitle = new javax.swing.JLabel("SIKURSUS Desktop  |  Filter transaksi berdasarkan rentang tanggal");
        lblSubTitle.setForeground(new Color(205, 226, 226));
        lblSubTitle.setFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 12));
        MasterFormSupport.add(pnlHeader, lblTitle, 0, 0, 1, 0,
                java.awt.GridBagConstraints.HORIZONTAL, new java.awt.Insets(0, 0, 4, 0));
        MasterFormSupport.add(pnlHeader, lblSubTitle, 0, 1, 1, 0,
                java.awt.GridBagConstraints.HORIZONTAL, new java.awt.Insets(0, 0, 0, 0));

        pnlCard = MasterFormSupport.panel(Color.WHITE);
        MasterFormSupport.styleCard(pnlCard);
        lblIntro = MasterFormSupport.label("Tentukan periode tanggal pembayaran yang ingin dicetak.", false);
        MasterFormSupport.add(pnlCard, lblIntro, 0, 0, 1, 0,
                java.awt.GridBagConstraints.HORIZONTAL, new java.awt.Insets(0, 0, 14, 0));
        lblTanggalAwal = MasterFormSupport.label("Tanggal awal *", true);
        txtTanggalAwal = MasterFormSupport.field();
        txtTanggalAwal.setColumns(24);
        MasterFormSupport.add(pnlCard, lblTanggalAwal, 0, 1, 1, 0,
                java.awt.GridBagConstraints.HORIZONTAL, new java.awt.Insets(0, 0, 5, 0));
        MasterFormSupport.add(pnlCard, txtTanggalAwal, 0, 2, 1, 0,
                java.awt.GridBagConstraints.HORIZONTAL, new java.awt.Insets(0, 0, 12, 0));
        lblTanggalAkhir = MasterFormSupport.label("Tanggal akhir *", true);
        txtTanggalAkhir = MasterFormSupport.field();
        txtTanggalAkhir.setColumns(24);
        MasterFormSupport.add(pnlCard, lblTanggalAkhir, 0, 3, 1, 0,
                java.awt.GridBagConstraints.HORIZONTAL, new java.awt.Insets(0, 0, 5, 0));
        MasterFormSupport.add(pnlCard, txtTanggalAkhir, 0, 4, 1, 0,
                java.awt.GridBagConstraints.HORIZONTAL, new java.awt.Insets(0, 0, 12, 0));
        lblFormat = MasterFormSupport.label("Format tanggal: YYYY-MM-DD (contoh 2026-11-01)", false);
        MasterFormSupport.add(pnlCard, lblFormat, 0, 5, 1, 0,
                java.awt.GridBagConstraints.HORIZONTAL, new java.awt.Insets(0, 0, 16, 0));
        pnlActions = new javax.swing.JPanel(new java.awt.GridBagLayout());
        pnlActions.setOpaque(false);
        btnTampilkan = MasterFormSupport.button("TAMPILKAN LAPORAN", MasterFormSupport.TEAL, Color.WHITE);
        btnTampilkan.setPreferredSize(new Dimension(220, 40));
        btnReset = MasterFormSupport.button("RESET", new Color(228, 239, 240), MasterFormSupport.INK);
        btnReset.setPreferredSize(new Dimension(120, 40));
        MasterFormSupport.add(pnlActions, btnTampilkan, 0, 0, 0, 0,
                java.awt.GridBagConstraints.NONE, new java.awt.Insets(0, 0, 0, 10));
        MasterFormSupport.add(pnlActions, btnReset, 1, 0, 0, 0,
                java.awt.GridBagConstraints.NONE, new java.awt.Insets(0, 0, 0, 0));
        MasterFormSupport.add(pnlCard, pnlActions, 0, 6, 1, 0,
                java.awt.GridBagConstraints.HORIZONTAL, new java.awt.Insets(0, 0, 12, 0));
        lblStatus = MasterFormSupport.label("Masukkan periode tanggal untuk menampilkan laporan.", false);
        MasterFormSupport.add(pnlCard, lblStatus, 0, 7, 1, 0,
                java.awt.GridBagConstraints.HORIZONTAL, new java.awt.Insets(0, 0, 0, 0));

        gbc = MasterFormSupport.gbc(0, 0, 1, 1, 1, 0,
                java.awt.GridBagConstraints.HORIZONTAL, new java.awt.Insets(0, 0, 0, 0));
        pnlRoot.add(pnlHeader, gbc);
        gbc = MasterFormSupport.gbc(0, 1, 1, 1, 1, 1,
                java.awt.GridBagConstraints.BOTH, new java.awt.Insets(28, 42, 30, 42));
        pnlRoot.add(pnlCard, gbc);
        getContentPane().setLayout(new java.awt.GridBagLayout());
        getContentPane().add(pnlRoot, MasterFormSupport.gbc(0, 0, 1, 1, 1, 1,
                java.awt.GridBagConstraints.BOTH, new java.awt.Insets(0, 0, 0, 0)));
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SIKURSUS Desktop | Laporan Pembayaran");
        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnTampilkan;
    private javax.swing.JLabel lblFormat;
    private javax.swing.JLabel lblIntro;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblSubTitle;
    private javax.swing.JLabel lblTanggalAkhir;
    private javax.swing.JLabel lblTanggalAwal;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pnlActions;
    private javax.swing.JPanel pnlCard;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JPanel pnlRoot;
    private javax.swing.JTextField txtTanggalAkhir;
    private javax.swing.JTextField txtTanggalAwal;
    // End of variables declaration
}
