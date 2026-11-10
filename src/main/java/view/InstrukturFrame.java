package view;

import dao.InstrukturDAO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.SQLException;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import model.Instruktur;

/** JFrame Form Master Instruktur: CRUD MySQL + pencarian + JTable. */
public class InstrukturFrame extends javax.swing.JFrame {
    private final InstrukturDAO dao = new InstrukturDAO();
    private int selectedId = -1;
    private DefaultTableModel tableModel;

    // Komponen form (dipertahankan sebagai field agar mudah dikelola di NetBeans).


    public InstrukturFrame() {
        initComponents();
        setMinimumSize(new Dimension(1000, 620));
        setSize(1180, 700);
        setLocationRelativeTo(null);
        setupTable();
        setupActions();
        clearForm();
        loadTable();
    }

    /** Komponen JFrame Form. Struktur UI dibuat eksplisit agar tetap mudah dibaca. */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        setTitle("SIKURSUS Desktop | Master Instruktur");
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        pnlRoot = new JPanel(new GridBagLayout());
        pnlRoot.setBackground(MasterFormSupport.BG);

        pnlHeader = new JPanel(new GridBagLayout());
        pnlHeader.setBackground(MasterFormSupport.NAVY);
        MasterFormSupport.styleHeader(pnlHeader);
        lblTitle = new JLabel("MASTER INSTRUKTUR");
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 22));
        lblSubTitle = new JLabel("SIKURSUS Desktop  |  Kelola profil instruktur dan keahlian");
        lblSubTitle.setForeground(new Color(205, 226, 226));
        lblSubTitle.setFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 12));
        MasterFormSupport.add(pnlHeader, lblTitle, 0, 0, 1, 0, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 4, 0));
        MasterFormSupport.add(pnlHeader, lblSubTitle, 0, 1, 1, 0, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0));

        pnlContent = new JPanel(new GridBagLayout());
        pnlContent.setBackground(MasterFormSupport.BG);
        pnlContent.setBorder(BorderFactory.createEmptyBorder(14, 16, 14, 16));
        pnlEditor = MasterFormSupport.panel(Color.WHITE);
        pnlDaftar = MasterFormSupport.panel(Color.WHITE);
        MasterFormSupport.styleCard(pnlEditor);
        MasterFormSupport.styleCard(pnlDaftar);
        MasterFormSupport.add(pnlContent, pnlEditor, 0, 0, 0.42, 1, GridBagConstraints.BOTH, new Insets(0, 0, 0, 10));
        MasterFormSupport.add(pnlContent, pnlDaftar, 1, 0, 0.58, 1, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0));

        lblEditorTitle = sectionTitle("DATA INSTRUKTUR");
        lblEditorHint = helperText("Lengkapi data instruktur, lalu pilih Simpan.");
        pnlFields = new JPanel(new GridBagLayout());
        pnlFields.setOpaque(false);
        lblNama = MasterFormSupport.label("Nama instruktur *", true);
        lblKeahlian = MasterFormSupport.label("Keahlian *", true);
        lblNoHp = MasterFormSupport.label("Nomor HP", true);
        lblEmail = MasterFormSupport.label("Email", true);
        txtNama = MasterFormSupport.field();
        txtKeahlian = MasterFormSupport.field();
        txtNoHp = MasterFormSupport.field();
        txtEmail = MasterFormSupport.field();
        addField(pnlFields, lblNama, txtNama, 0);
        addField(pnlFields, lblKeahlian, txtKeahlian, 1);
        addField(pnlFields, lblNoHp, txtNoHp, 2);
        addField(pnlFields, lblEmail, txtEmail, 3);

        btnSimpan = MasterFormSupport.button("SIMPAN", new Color(12, 153, 119), Color.WHITE);
        btnUbah = MasterFormSupport.button("UBAH", new Color(39, 103, 172), Color.WHITE);
        btnHapus = MasterFormSupport.button("HAPUS", new Color(186, 64, 75), Color.WHITE);
        btnReset = MasterFormSupport.button("RESET FORM", new Color(228, 239, 240), MasterFormSupport.INK);
        btnMuat = MasterFormSupport.button("MUAT ULANG", new Color(228, 239, 240), MasterFormSupport.INK);
        pnlActions = new JPanel(new GridBagLayout());
        pnlActions.setOpaque(false);
        addActionButtons();
        lblTerpilih = helperText("Mode: tambah instruktur baru");
        MasterFormSupport.add(pnlEditor, lblEditorTitle, 0, 0, 1, 0, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 4, 0));
        MasterFormSupport.add(pnlEditor, lblEditorHint, 0, 1, 1, 0, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 10, 0));
        MasterFormSupport.add(pnlEditor, pnlFields, 0, 2, 1, 0, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 12, 0));
        MasterFormSupport.add(pnlEditor, pnlActions, 0, 3, 1, 0, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 8, 0));
        MasterFormSupport.add(pnlEditor, lblTerpilih, 0, 4, 1, 1, GridBagConstraints.HORIZONTAL, new Insets(4, 0, 0, 0));

        lblDaftarTitle = sectionTitle("DAFTAR INSTRUKTUR");
        lblCariHint = helperText("Cari berdasarkan nama, keahlian, nomor HP, atau email");
        txtCari = MasterFormSupport.field();
        btnCari = MasterFormSupport.button("CARI", new Color(13, 123, 102), Color.WHITE);
        pnlCari = new JPanel(new GridBagLayout());
        pnlCari.setOpaque(false);
        MasterFormSupport.add(pnlCari, txtCari, 0, 0, 1, 0, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 8));
        MasterFormSupport.add(pnlCari, btnCari, 1, 0, 0, 0, GridBagConstraints.NONE, new Insets(0, 0, 0, 0));
        lblJumlah = helperText("0 instruktur ditampilkan");
        tblInstruktur = new JTable();
        scrollInstruktur = new JScrollPane(tblInstruktur);
        MasterFormSupport.styleScroll(scrollInstruktur);
        lblTableHint = helperText("Klik satu baris tabel untuk mengisi form dan mengaktifkan Ubah/Hapus.");
        MasterFormSupport.add(pnlDaftar, lblDaftarTitle, 0, 0, 1, 0, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 3, 0));
        MasterFormSupport.add(pnlDaftar, lblCariHint, 0, 1, 1, 0, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 8, 0));
        MasterFormSupport.add(pnlDaftar, pnlCari, 0, 2, 1, 0, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 8, 0));
        MasterFormSupport.add(pnlDaftar, lblJumlah, 0, 3, 1, 0, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 8, 0));
        MasterFormSupport.add(pnlDaftar, scrollInstruktur, 0, 4, 1, 1, GridBagConstraints.BOTH, new Insets(0, 0, 8, 0));
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

    private void addField(JPanel panel, JLabel label, JTextField field, int row) {
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
        tableModel = new DefaultTableModel(new String[]{"ID", "Nama", "Keahlian", "No. HP", "Email"}, 0) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };
        tblInstruktur.setModel(tableModel);
        MasterFormSupport.styleTable(tblInstruktur);
        int[] widths = {45, 145, 150, 105, 170};
        for (int i = 0; i < widths.length; i++) tblInstruktur.getColumnModel().getColumn(i).setPreferredWidth(widths[i]);
        tblInstruktur.getSelectionModel().addListSelectionListener(e -> {
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

    private void fillTable(List<Instruktur> rows) {
        tableModel.setRowCount(0);
        for (Instruktur item : rows) tableModel.addRow(new Object[]{item.getId(), item.getNama(),
                item.getKeahlian(), item.getNoHp(), item.getEmail()});
        lblJumlah.setText(rows.size() + " instruktur ditampilkan");
    }

    private void loadTable() {
        try {
            fillTable(dao.findAll());
            lblStatus.setText("Data instruktur berhasil dimuat dari MySQL.");
        } catch (SQLException ex) { showSqlError("Gagal memuat data instruktur", ex); }
    }

    private void cari() {
        String keyword = txtCari.getText().trim();
        clearForm();
        try {
            fillTable(keyword.isEmpty() ? dao.findAll() : dao.search(keyword));
            lblStatus.setText(keyword.isEmpty() ? "Seluruh data instruktur ditampilkan." : "Hasil pencarian: " + keyword);
        } catch (SQLException ex) { showSqlError("Pencarian instruktur gagal", ex); }
    }

    private void clearForm() {
        selectedId = -1;
        txtNama.setText(""); txtKeahlian.setText(""); txtNoHp.setText(""); txtEmail.setText("");
        tblInstruktur.clearSelection();
        btnSimpan.setEnabled(true); btnUbah.setEnabled(false); btnHapus.setEnabled(false);
        lblTerpilih.setText("Mode: tambah instruktur baru");
        txtNama.requestFocusInWindow();
    }

    private void selectTableRow() {
        int viewRow = tblInstruktur.getSelectedRow();
        if (viewRow < 0) return;
        int row = tblInstruktur.convertRowIndexToModel(viewRow);
        selectedId = Integer.parseInt(String.valueOf(tableModel.getValueAt(row, 0)));
        txtNama.setText(valueAt(row, 1)); txtKeahlian.setText(valueAt(row, 2));
        txtNoHp.setText(valueAt(row, 3)); txtEmail.setText(valueAt(row, 4));
        btnSimpan.setEnabled(false); btnUbah.setEnabled(true); btnHapus.setEnabled(true);
        lblTerpilih.setText("Mode: edit instruktur ID " + selectedId);
    }

    private String valueAt(int row, int col) {
        Object value = tableModel.getValueAt(row, col);
        return value == null ? "" : value.toString();
    }

    private Instruktur formData() {
        return new Instruktur(selectedId > 0 ? selectedId : 0, txtNama.getText(),
                txtKeahlian.getText(), txtNoHp.getText(), txtEmail.getText());
    }

    private boolean validasi() {
        if (txtNama.getText().trim().isEmpty()) { warn("Nama instruktur wajib diisi.", txtNama); return false; }
        if (txtKeahlian.getText().trim().isEmpty()) { warn("Keahlian instruktur wajib diisi.", txtKeahlian); return false; }
        try { formData(); return true; }
        catch (IllegalArgumentException ex) { warn(ex.getMessage(), null); return false; }
    }

    private void simpan() {
        if (selectedId > 0) { warn("Klik Reset Form untuk menambahkan instruktur baru.", null); return; }
        if (!validasi()) return;
        try {
            Instruktur item = formData();
            if (dao.insert(item)) {
                JOptionPane.showMessageDialog(this, "Data instruktur berhasil disimpan.\nID: " + item.getId(),
                        "Simpan Berhasil", JOptionPane.INFORMATION_MESSAGE);
                clearForm(); loadTable();
            }
        } catch (SQLException ex) { showSqlError("Gagal menyimpan instruktur", ex); }
    }

    private void ubah() {
        if (selectedId <= 0) { warn("Pilih satu baris instruktur dari tabel terlebih dahulu.", null); return; }
        if (!validasi()) return;
        try {
            if (dao.update(formData())) {
                JOptionPane.showMessageDialog(this, "Data instruktur berhasil diubah.", "Ubah Berhasil", JOptionPane.INFORMATION_MESSAGE);
                clearForm(); loadTable();
            }
        } catch (SQLException ex) { showSqlError("Gagal mengubah instruktur", ex); }
    }

    private void hapus() {
        if (selectedId <= 0) { warn("Pilih instruktur yang ingin dihapus.", null); return; }
        int confirm = JOptionPane.showConfirmDialog(this, "Hapus instruktur " + txtNama.getText() + "?",
                "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (confirm != JOptionPane.YES_OPTION) return;
        try {
            if (dao.delete(selectedId)) { clearForm(); loadTable(); }
        } catch (SQLException ex) {
            if (ex.getErrorCode() == 1451) warn("Instruktur masih digunakan pada data kelas. Hapus/ubah kelas terkait terlebih dahulu.", null);
            else showSqlError("Gagal menghapus instruktur", ex);
        }
    }

    private void warn(String message, java.awt.Component focus) {
        JOptionPane.showMessageDialog(this, message, "Validasi Master Instruktur", JOptionPane.WARNING_MESSAGE);
        if (focus != null) focus.requestFocusInWindow();
    }

    private void showSqlError(String action, SQLException ex) {
        String detail = ex.getMessage() == null ? "Tidak ada detail dari database." : ex.getMessage();
        JOptionPane.showMessageDialog(this, action + ".\nPastikan MySQL Laragon aktif dan tabel instruktur tersedia.\n\nDetail: " + detail,
                "Database Error", JOptionPane.ERROR_MESSAGE);
        lblStatus.setText(action + ". Periksa koneksi MySQL.");
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JPanel pnlRoot, pnlHeader, pnlContent, pnlEditor, pnlDaftar, pnlFields, pnlActions, pnlCari, pnlFooter;
    private JLabel lblTitle, lblSubTitle, lblEditorTitle, lblEditorHint, lblNama, lblKeahlian,
            lblNoHp, lblEmail, lblDaftarTitle, lblCariHint, lblJumlah, lblTerpilih, lblTableHint, lblStatus;
    private JTextField txtNama, txtKeahlian, txtNoHp, txtEmail, txtCari;
    private JButton btnSimpan, btnUbah, btnHapus, btnReset, btnMuat, btnCari;
    private JTable tblInstruktur;
    private JScrollPane scrollInstruktur;
    //GEN-END:variables
}
