package view;

import dao.PendaftaranDAO;
import java.awt.Color;
import java.awt.Dimension;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import report.ReportService;

/** JFrame Form untuk memilih satu transaksi pendaftaran dan mencetak laporannya. */
public class PendaftaranFrame extends javax.swing.JFrame {
    private final PendaftaranDAO dao = new PendaftaranDAO();
    private DefaultTableModel tableModel;

    public PendaftaranFrame() {
        initComponents();
        setMinimumSize(new Dimension(980, 590));
        setSize(1120, 680);
        setLocationRelativeTo(null);
        setupTable();
        setupActions();
        loadData();
    }

    private void setupTable() {
        tableModel = new DefaultTableModel(new Object[]{
            "ID Pendaftaran", "NIM", "Nama Peserta", "Kelas", "Tanggal Daftar", "Status"
        }, 0) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };
        tblPendaftaran.setModel(tableModel);
        MasterFormSupport.styleTable(tblPendaftaran);
        tblPendaftaran.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        int[] widths = {95, 100, 190, 140, 115, 95};
        for (int i = 0; i < widths.length; i++) {
            tblPendaftaran.getColumnModel().getColumn(i).setPreferredWidth(widths[i]);
        }
        MasterFormSupport.styleScroll(scrollPendaftaran);
    }

    private void setupActions() {
        btnCetakTerpilih.addActionListener(e -> cetakTerpilih());
        btnMuat.addActionListener(e -> loadData());
        getRootPane().setDefaultButton(btnCetakTerpilih);
    }

    private void loadData() {
        tableModel.setRowCount(0);
        try {
            List<Object[]> rows = dao.findAll();
            for (Object[] row : rows) tableModel.addRow(row);
            lblStatus.setText(rows.size() + " pendaftaran dimuat dari MySQL.");
            if (rows.isEmpty()) {
                lblStatus.setText("Belum ada data pendaftaran. Tambahkan data pada tabel pendaftaran terlebih dahulu.");
            }
        } catch (SQLException ex) {
            lblStatus.setText("Gagal memuat data pendaftaran dari MySQL.");
            JOptionPane.showMessageDialog(this,
                    "Pastikan tabel pendaftaran dan seluruh relasi database sudah tersedia.\n\n"
                    + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cetakTerpilih() {
        int selectedViewRow = tblPendaftaran.getSelectedRow();
        if (selectedViewRow < 0) {
            JOptionPane.showMessageDialog(this,
                    "Pilih satu baris pendaftaran terlebih dahulu.",
                    "Pilih Data", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int selectedModelRow = tblPendaftaran.convertRowIndexToModel(selectedViewRow);
        Object value = tableModel.getValueAt(selectedModelRow, 0);
        int idPendaftaran;
        try {
            idPendaftaran = Integer.parseInt(String.valueOf(value));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID pendaftaran pada tabel tidak valid.",
                    "Data Tidak Valid", JOptionPane.ERROR_MESSAGE);
            return;
        }
        ReportService.cetakPendaftaran(idPendaftaran);
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
        lblTitle = new javax.swing.JLabel("TRANSAKSI PENDAFTARAN");
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 22));
        lblSubTitle = new javax.swing.JLabel("SIKURSUS Desktop  |  Daftar transaksi dan cetak laporan berdasarkan ID terpilih");
        lblSubTitle.setForeground(new Color(205, 226, 226));
        lblSubTitle.setFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 12));
        MasterFormSupport.add(pnlHeader, lblTitle, 0, 0, 1, 0,
                java.awt.GridBagConstraints.HORIZONTAL, new java.awt.Insets(0, 0, 4, 0));
        MasterFormSupport.add(pnlHeader, lblSubTitle, 0, 1, 1, 0,
                java.awt.GridBagConstraints.HORIZONTAL, new java.awt.Insets(0, 0, 0, 0));

        pnlContent = MasterFormSupport.panel(Color.WHITE);
        MasterFormSupport.styleCard(pnlContent);
        lblIntro = MasterFormSupport.label(
                "Pilih satu baris pendaftaran, lalu cetak laporan peserta, kelas, kursus, dan instruktur.", false);
        MasterFormSupport.add(pnlContent, lblIntro, 0, 0, 1, 0,
                java.awt.GridBagConstraints.HORIZONTAL, new java.awt.Insets(0, 0, 12, 0));
        scrollPendaftaran = new javax.swing.JScrollPane();
        tblPendaftaran = new javax.swing.JTable();
        scrollPendaftaran.setViewportView(tblPendaftaran);
        MasterFormSupport.add(pnlContent, scrollPendaftaran, 0, 1, 1, 1,
                java.awt.GridBagConstraints.BOTH, new java.awt.Insets(0, 0, 12, 0));
        pnlActions = new javax.swing.JPanel(new java.awt.GridBagLayout());
        pnlActions.setOpaque(false);
        btnCetakTerpilih = MasterFormSupport.button(
                "CETAK PENDAFTARAN TERPILIH", MasterFormSupport.TEAL, Color.WHITE);
        btnCetakTerpilih.setPreferredSize(new Dimension(250, 39));
        btnMuat = MasterFormSupport.button(
                "MUAT ULANG", new Color(228, 239, 240), MasterFormSupport.INK);
        btnMuat.setPreferredSize(new Dimension(145, 39));
        MasterFormSupport.add(pnlActions, btnCetakTerpilih, 0, 0, 0, 0,
                java.awt.GridBagConstraints.NONE, new java.awt.Insets(0, 0, 0, 10));
        MasterFormSupport.add(pnlActions, btnMuat, 1, 0, 0, 0,
                java.awt.GridBagConstraints.NONE, new java.awt.Insets(0, 0, 0, 0));
        MasterFormSupport.add(pnlContent, pnlActions, 0, 2, 1, 0,
                java.awt.GridBagConstraints.HORIZONTAL, new java.awt.Insets(0, 0, 9, 0));
        lblStatus = MasterFormSupport.label("Menyiapkan data pendaftaran...", false);
        MasterFormSupport.add(pnlContent, lblStatus, 0, 3, 1, 0,
                java.awt.GridBagConstraints.HORIZONTAL, new java.awt.Insets(2, 0, 0, 0));

        gbc = MasterFormSupport.gbc(0, 0, 1, 1, 1, 0,
                java.awt.GridBagConstraints.HORIZONTAL, new java.awt.Insets(0, 0, 0, 0));
        pnlRoot.add(pnlHeader, gbc);
        gbc = MasterFormSupport.gbc(0, 1, 1, 1, 1, 1,
                java.awt.GridBagConstraints.BOTH, new java.awt.Insets(14, 16, 14, 16));
        pnlRoot.add(pnlContent, gbc);
        getContentPane().setLayout(new java.awt.GridBagLayout());
        getContentPane().add(pnlRoot, MasterFormSupport.gbc(0, 0, 1, 1, 1, 1,
                java.awt.GridBagConstraints.BOTH, new java.awt.Insets(0, 0, 0, 0)));
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SIKURSUS Desktop | Transaksi Pendaftaran");
        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify
    private javax.swing.JButton btnCetakTerpilih;
    private javax.swing.JButton btnMuat;
    private javax.swing.JLabel lblIntro;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblSubTitle;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pnlActions;
    private javax.swing.JPanel pnlContent;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JPanel pnlRoot;
    private javax.swing.JScrollPane scrollPendaftaran;
    private javax.swing.JTable tblPendaftaran;
    // End of variables declaration
}
