package view;

import dao.KursusDAO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.sql.SQLException;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.Kursus;

/** JFrame Form Master Kursus: CRUD program, level, dan biaya. */
public class KursusFrame extends javax.swing.JFrame {
    private final KursusDAO dao = new KursusDAO();
    private int selectedId = -1;
    private DefaultTableModel tableModel;



    public KursusFrame() {
        initComponents();
        setMinimumSize(new Dimension(1000, 620));
        setSize(1180, 700);
        setLocationRelativeTo(null);
        setupTable();
        setupActions();
        clearForm();
        loadTable();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        setTitle("SIKURSUS Desktop | Master Kursus");
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        pnlRoot = new JPanel(new java.awt.GridBagLayout());
        pnlRoot.setBackground(MasterFormSupport.BG);
        pnlHeader = new JPanel(new java.awt.GridBagLayout());
        pnlHeader.setBackground(MasterFormSupport.NAVY);
        MasterFormSupport.styleHeader(pnlHeader);
        lblTitle = new JLabel("MASTER KURSUS");
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 22));
        lblSubTitle = new JLabel("SIKURSUS Desktop  |  Kelola program, level, dan biaya kursus");
        lblSubTitle.setForeground(new Color(205, 226, 226));
        lblSubTitle.setFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 12));
        MasterFormSupport.add(pnlHeader, lblTitle, 0, 0, 1, 0, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 4, 0));
        MasterFormSupport.add(pnlHeader, lblSubTitle, 0, 1, 1, 0, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0));

        pnlContent = new JPanel(new java.awt.GridBagLayout());
        pnlContent.setBackground(MasterFormSupport.BG);
        pnlContent.setBorder(BorderFactory.createEmptyBorder(14, 16, 14, 16));
        pnlEditor = MasterFormSupport.panel(Color.WHITE);
        pnlDaftar = MasterFormSupport.panel(Color.WHITE);
        MasterFormSupport.styleCard(pnlEditor);
        MasterFormSupport.styleCard(pnlDaftar);
        MasterFormSupport.add(pnlContent, pnlEditor, 0, 0, 0.42, 1, GridBagConstraints.BOTH, new Insets(0, 0, 0, 10));
        MasterFormSupport.add(pnlContent, pnlDaftar, 1, 0, 0.58, 1, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0));

        lblEditorTitle = sectionTitle("DATA KURSUS");
        lblEditorHint = helperText("Masukkan identitas program dan biaya kursus.");
        pnlFields = new JPanel(new java.awt.GridBagLayout());
        pnlFields.setOpaque(false);
        lblKode = MasterFormSupport.label("Kode kursus *", true);
        lblNama = MasterFormSupport.label("Nama kursus *", true);
        lblLevel = MasterFormSupport.label("Level *", true);
        lblBiaya = MasterFormSupport.label("Biaya (Rp) *", true);
        txtKode = MasterFormSupport.field();
        txtNama = MasterFormSupport.field();
        cmbLevel = new JComboBox<>(new String[]{"BASIC", "INTERMEDIATE", "ADVANCED"});
        MasterFormSupport.styleCombo(cmbLevel);
        txtBiaya = MasterFormSupport.field();
        addField(pnlFields, lblKode, txtKode, 0);
        addField(pnlFields, lblNama, txtNama, 1);
        addField(pnlFields, lblLevel, cmbLevel, 2);
        addField(pnlFields, lblBiaya, txtBiaya, 3);

        btnSimpan = MasterFormSupport.button("SIMPAN", new Color(12, 153, 119), Color.WHITE);
        btnUbah = MasterFormSupport.button("UBAH", new Color(39, 103, 172), Color.WHITE);
        btnHapus = MasterFormSupport.button("HAPUS", new Color(186, 64, 75), Color.WHITE);
        btnReset = MasterFormSupport.button("RESET FORM", new Color(228, 239, 240), MasterFormSupport.INK);
        btnMuat = MasterFormSupport.button("MUAT ULANG", new Color(228, 239, 240), MasterFormSupport.INK);
        pnlActions = new JPanel(new java.awt.GridBagLayout());
        pnlActions.setOpaque(false);
        addActionButtons();
        lblTerpilih = helperText("Mode: tambah kursus baru");
        MasterFormSupport.add(pnlEditor, lblEditorTitle, 0, 0, 1, 0, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 4, 0));
        MasterFormSupport.add(pnlEditor, lblEditorHint, 0, 1, 1, 0, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 10, 0));
        MasterFormSupport.add(pnlEditor, pnlFields, 0, 2, 1, 0, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 12, 0));
        MasterFormSupport.add(pnlEditor, pnlActions, 0, 3, 1, 0, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 8, 0));
        MasterFormSupport.add(pnlEditor, lblTerpilih, 0, 4, 1, 1, GridBagConstraints.HORIZONTAL, new Insets(4, 0, 0, 0));

        lblDaftarTitle = sectionTitle("DAFTAR KURSUS");
        lblCariHint = helperText("Cari berdasarkan kode, nama program, atau level");
        txtCari = MasterFormSupport.field();
        btnCari = MasterFormSupport.button("CARI", new Color(13, 123, 102), Color.WHITE);
        pnlCari = new JPanel(new java.awt.GridBagLayout());
        pnlCari.setOpaque(false);
        MasterFormSupport.add(pnlCari, txtCari, 0, 0, 1, 0, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 8));
        MasterFormSupport.add(pnlCari, btnCari, 1, 0, 0, 0, GridBagConstraints.NONE, new Insets(0, 0, 0, 0));
        lblJumlah = helperText("0 kursus ditampilkan");
        tblKursus = new JTable();
        scrollKursus = new JScrollPane(tblKursus);
        MasterFormSupport.styleScroll(scrollKursus);
        lblTableHint = helperText("Klik satu baris untuk mengubah atau menghapus data kursus.");
        MasterFormSupport.add(pnlDaftar, lblDaftarTitle, 0, 0, 1, 0, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 3, 0));
        MasterFormSupport.add(pnlDaftar, lblCariHint, 0, 1, 1, 0, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 8, 0));
        MasterFormSupport.add(pnlDaftar, pnlCari, 0, 2, 1, 0, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 8, 0));
        MasterFormSupport.add(pnlDaftar, lblJumlah, 0, 3, 1, 0, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 8, 0));
        MasterFormSupport.add(pnlDaftar, scrollKursus, 0, 4, 1, 1, GridBagConstraints.BOTH, new Insets(0, 0, 8, 0));
        MasterFormSupport.add(pnlDaftar, lblTableHint, 0, 5, 1, 0, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0));

        pnlRoot.add(pnlHeader, MasterFormSupport.gbc(0, 0, 1, 1, 1, 0, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0)));
        pnlRoot.add(pnlContent, MasterFormSupport.gbc(0, 1, 1, 1, 1, 1, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0)));
        lblStatus = helperText("Siap. Aktifkan MySQL Laragon untuk memuat data.");
        pnlFooter = new JPanel(new java.awt.GridBagLayout());
        pnlFooter.setBackground(Color.WHITE);
        MasterFormSupport.add(pnlFooter, lblStatus, 0, 0, 1, 0, GridBagConstraints.HORIZONTAL, new Insets(5, 16, 5, 0));
        pnlRoot.add(pnlFooter, MasterFormSupport.gbc(0, 2, 1, 1, 1, 0, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0)));
        setContentPane(pnlRoot);
    }

    // </editor-fold>//GEN-END:initComponents

    private void addField(JPanel panel, JLabel label, java.awt.Component field, int row) {
        MasterFormSupport.add(panel, label, 0, row * 2, 1, 0, GridBagConstraints.HORIZONTAL, new Insets(7, 0, 3, 0));
        MasterFormSupport.add(panel, field, 0, row * 2 + 1, 1, 0, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 2, 0));
    }

    private void addActionButtons() {
        MasterFormSupport.add(pnlActions, btnSimpan, 0, 0, 1, 0, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 7, 6));
        MasterFormSupport.add(pnlActions, btnUbah, 1, 0, 1, 0, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 7, 6));
        MasterFormSupport.add(pnlActions, btnHapus, 2, 0, 1, 0, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 7, 0));
        MasterFormSupport.add(pnlActions, btnReset, 0, 1, 1, 0, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 6));
        MasterFormSupport.add(pnlActions, btnMuat, 1, 1, 1, 0, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 6));
    }

    private JLabel sectionTitle(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(MasterFormSupport.TEAL);
        label.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 16));
        return label;
    }
    private JLabel helperText(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(MasterFormSupport.MUTED);
        label.setFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 11));
        return label;
    }

    private void setupTable() {
        tableModel = new DefaultTableModel(new String[]{"ID", "Kode", "Nama Kursus", "Level", "Biaya (Rp)"}, 0) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };
        tblKursus.setModel(tableModel);
        MasterFormSupport.styleTable(tblKursus);
        int[] widths = {45, 95, 210, 105, 120};
        for (int i = 0; i < widths.length; i++) tblKursus.getColumnModel().getColumn(i).setPreferredWidth(widths[i]);
        tblKursus.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) selectTableRow();
        });
    }

    private void setupActions() {
        btnSimpan.addActionListener(e -> simpan());
        btnUbah.addActionListener(e -> ubah());
        btnHapus.addActionListener(e -> hapus());
        btnReset.addActionListener(e -> clearForm());
        btnMuat.addActionListener(e -> { txtCari.setText(""); clearForm(); loadTable(); });
        btnCari.addActionListener(e -> cari());
        txtCari.addActionListener(e -> cari());
        getRootPane().setDefaultButton(btnCari);
    }

    private void fillTable(List<Kursus> rows) {
        tableModel.setRowCount(0);
        java.text.NumberFormat rupiah = java.text.NumberFormat.getNumberInstance(new java.util.Locale("id", "ID"));
        for (Kursus item : rows) tableModel.addRow(new Object[]{item.getIdKursus(), item.getKode(),
                item.getNama(), item.getLevel(), rupiah.format(item.getBiaya())});
        lblJumlah.setText(rows.size() + " kursus ditampilkan");
    }

    private void loadTable() {
        try { fillTable(dao.findAll()); lblStatus.setText("Data kursus berhasil dimuat dari MySQL."); }
        catch (SQLException ex) { showSqlError("Gagal memuat data kursus", ex); }
    }

    private void cari() {
        String keyword = txtCari.getText().trim();
        clearForm();
        try {
            fillTable(keyword.isEmpty() ? dao.findAll() : dao.search(keyword));
            lblStatus.setText(keyword.isEmpty() ? "Seluruh data kursus ditampilkan." : "Hasil pencarian: " + keyword);
        } catch (SQLException ex) { showSqlError("Pencarian kursus gagal", ex); }
    }

    private void clearForm() {
        selectedId = -1;
        txtKode.setText(""); txtNama.setText(""); txtBiaya.setText(""); cmbLevel.setSelectedIndex(0);
        tblKursus.clearSelection();
        btnSimpan.setEnabled(true); btnUbah.setEnabled(false); btnHapus.setEnabled(false);
        lblTerpilih.setText("Mode: tambah kursus baru");
        txtKode.requestFocusInWindow();
    }

    private String valueAt(int row, int col) {
        Object value = tableModel.getValueAt(row, col);
        return value == null ? "" : value.toString();
    }

    private void selectTableRow() {
        int viewRow = tblKursus.getSelectedRow();
        if (viewRow < 0) return;
        int row = tblKursus.convertRowIndexToModel(viewRow);
        selectedId = Integer.parseInt(valueAt(row, 0));
        txtKode.setText(valueAt(row, 1)); txtNama.setText(valueAt(row, 2));
        cmbLevel.setSelectedItem(valueAt(row, 3));
        txtBiaya.setText(valueAt(row, 4).replace(".", "").replace(",", "."));
        btnSimpan.setEnabled(false); btnUbah.setEnabled(true); btnHapus.setEnabled(true);
        lblTerpilih.setText("Mode: edit kursus ID " + selectedId);
    }

    private Kursus formData() {
        String biayaText = txtBiaya.getText().trim().replace("Rp", "").replace("rp", "").replace(" ", "");
        if (biayaText.isEmpty()) throw new IllegalArgumentException("Biaya kursus wajib diisi.");
        // Format Indonesia: 500.000 atau 500.000,50; titik tunggal pada angka kecil dapat menjadi desimal.
        if (biayaText.indexOf(',') >= 0) {
            biayaText = biayaText.replace(".", "").replace(',', '.');
        } else if (biayaText.matches("[0-9]{1,3}(\\.[0-9]{3})+")) {
            biayaText = biayaText.replace(".", "");
        }
        double biaya;
        try { biaya = Double.parseDouble(biayaText); }
        catch (NumberFormatException ex) { throw new IllegalArgumentException("Biaya harus berupa angka, tanpa simbol atau format yang tidak valid."); }
        return new Kursus(selectedId > 0 ? selectedId : 0, txtKode.getText(), txtNama.getText(),
                String.valueOf(cmbLevel.getSelectedItem()), biaya);
    }

    private boolean validasi() {
        if (txtKode.getText().trim().isEmpty()) { warn("Kode kursus wajib diisi.", txtKode); return false; }
        if (txtNama.getText().trim().isEmpty()) { warn("Nama kursus wajib diisi.", txtNama); return false; }
        try { formData(); return true; }
        catch (IllegalArgumentException ex) { warn(ex.getMessage(), null); return false; }
    }

    private void simpan() {
        if (selectedId > 0) { warn("Klik Reset Form untuk menambahkan kursus baru.", null); return; }
        if (!validasi()) return;
        try {
            Kursus item = formData();
            if (dao.insert(item)) {
                JOptionPane.showMessageDialog(this, "Data kursus berhasil disimpan.\nID: " + item.getIdKursus(),
                        "Simpan Berhasil", JOptionPane.INFORMATION_MESSAGE);
                clearForm(); loadTable();
            }
        } catch (SQLException ex) { showSqlError("Gagal menyimpan kursus", ex); }
    }

    private void ubah() {
        if (selectedId <= 0) { warn("Pilih satu baris kursus dari tabel terlebih dahulu.", null); return; }
        if (!validasi()) return;
        try {
            if (dao.update(formData())) {
                JOptionPane.showMessageDialog(this, "Data kursus berhasil diubah.", "Ubah Berhasil", JOptionPane.INFORMATION_MESSAGE);
                clearForm(); loadTable();
            }
        } catch (SQLException ex) { showSqlError("Gagal mengubah kursus", ex); }
    }

    private void hapus() {
        if (selectedId <= 0) { warn("Pilih kursus yang ingin dihapus.", null); return; }
        int confirm = JOptionPane.showConfirmDialog(this, "Hapus kursus " + txtNama.getText() + "?",
                "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (confirm != JOptionPane.YES_OPTION) return;
        try {
            if (dao.delete(selectedId)) { clearForm(); loadTable(); }
        } catch (SQLException ex) {
            if (ex.getErrorCode() == 1451) warn("Kursus masih digunakan pada data kelas. Hapus/ubah kelas terkait terlebih dahulu.", null);
            else showSqlError("Gagal menghapus kursus", ex);
        }
    }

    private void warn(String message, java.awt.Component focus) {
        JOptionPane.showMessageDialog(this, message, "Validasi Master Kursus", JOptionPane.WARNING_MESSAGE);
        if (focus != null) focus.requestFocusInWindow();
    }

    private void showSqlError(String action, SQLException ex) {
        String detail = ex.getMessage() == null ? "Tidak ada detail dari database." : ex.getMessage();
        JOptionPane.showMessageDialog(this, action + ".\nPastikan MySQL Laragon aktif dan tabel kursus tersedia.\n\nDetail: " + detail,
                "Database Error", JOptionPane.ERROR_MESSAGE);
        lblStatus.setText(action + ". Periksa koneksi MySQL.");
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JPanel pnlRoot, pnlHeader, pnlContent, pnlEditor, pnlDaftar, pnlFields, pnlActions, pnlCari, pnlFooter;
    private JLabel lblTitle, lblSubTitle, lblEditorTitle, lblEditorHint, lblKode, lblNama,
            lblLevel, lblBiaya, lblDaftarTitle, lblCariHint, lblJumlah, lblTerpilih, lblTableHint, lblStatus;
    private JTextField txtKode, txtNama, txtBiaya, txtCari;
    private JComboBox<String> cmbLevel;
    private JButton btnSimpan, btnUbah, btnHapus, btnReset, btnMuat, btnCari;
    private JTable tblKursus;
    private JScrollPane scrollKursus;
    //GEN-END:variables
}
