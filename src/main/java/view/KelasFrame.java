package view;

import dao.InstrukturDAO;
import dao.KelasDAO;
import dao.KursusDAO;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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
import model.Instruktur;
import model.Kelas;
import model.Kursus;

/** JFrame Form Master Kelas: CRUD jadwal dan relasi kursus/instruktur. */
public class KelasFrame extends javax.swing.JFrame {
    private final KelasDAO dao = new KelasDAO();
    private final KursusDAO kursusDAO = new KursusDAO();
    private final InstrukturDAO instrukturDAO = new InstrukturDAO();
    private int selectedId = -1;
    private DefaultTableModel tableModel;



    public KelasFrame() {
        initComponents();
        setMinimumSize(new Dimension(1060, 640));
        setSize(1240, 720);
        setLocationRelativeTo(null);
        setupTable();
        setupActions();
        clearForm();
        loadCombos();
        loadTable();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        setTitle("SIKURSUS Desktop | Master Kelas");
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        pnlRoot = new JPanel(new java.awt.GridBagLayout());
        pnlRoot.setBackground(MasterFormSupport.BG);
        pnlHeader = new JPanel(new java.awt.GridBagLayout());
        pnlHeader.setBackground(MasterFormSupport.NAVY);
        MasterFormSupport.styleHeader(pnlHeader);
        lblTitle = new JLabel("MASTER KELAS");
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 22));
        lblSubTitle = new JLabel("SIKURSUS Desktop  |  Kelola jadwal, kursus, dan instruktur kelas");
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

        lblEditorTitle = sectionTitle("DATA KELAS");
        lblEditorHint = helperText("Pilih kursus dan instruktur, lalu lengkapi jadwal kelas.");
        pnlFields = new JPanel(new java.awt.GridBagLayout());
        pnlFields.setOpaque(false);
        lblNamaKelas = MasterFormSupport.label("Nama kelas *", true);
        lblKursus = MasterFormSupport.label("Kursus *", true);
        lblInstruktur = MasterFormSupport.label("Instruktur *", true);
        lblTanggalMulai = MasterFormSupport.label("Tanggal mulai * (YYYY-MM-DD)", true);
        lblKapasitas = MasterFormSupport.label("Kapasitas peserta *", true);
        txtNamaKelas = MasterFormSupport.field();
        cmbKursus = new JComboBox<>();
        cmbInstruktur = new JComboBox<>();
        MasterFormSupport.styleCombo(cmbKursus);
        MasterFormSupport.styleCombo(cmbInstruktur);
        txtTanggalMulai = MasterFormSupport.field();
        txtTanggalMulai.setToolTipText("Contoh: 2026-11-15");
        txtKapasitas = MasterFormSupport.field();
        txtKapasitas.setText("20");
        addField(pnlFields, lblNamaKelas, txtNamaKelas, 0);
        addField(pnlFields, lblKursus, cmbKursus, 1);
        addField(pnlFields, lblInstruktur, cmbInstruktur, 2);
        addField(pnlFields, lblTanggalMulai, txtTanggalMulai, 3);
        addField(pnlFields, lblKapasitas, txtKapasitas, 4);

        btnSimpan = MasterFormSupport.button("SIMPAN", new Color(12, 153, 119), Color.WHITE);
        btnUbah = MasterFormSupport.button("UBAH", new Color(39, 103, 172), Color.WHITE);
        btnHapus = MasterFormSupport.button("HAPUS", new Color(186, 64, 75), Color.WHITE);
        btnReset = MasterFormSupport.button("RESET FORM", new Color(228, 239, 240), MasterFormSupport.INK);
        btnMuat = MasterFormSupport.button("MUAT ULANG", new Color(228, 239, 240), MasterFormSupport.INK);
        pnlActions = new JPanel(new java.awt.GridBagLayout());
        pnlActions.setOpaque(false);
        addActionButtons();
        lblTerpilih = helperText("Mode: tambah kelas baru");
        MasterFormSupport.add(pnlEditor, lblEditorTitle, 0, 0, 1, 0, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 4, 0));
        MasterFormSupport.add(pnlEditor, lblEditorHint, 0, 1, 1, 0, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 10, 0));
        MasterFormSupport.add(pnlEditor, pnlFields, 0, 2, 1, 0, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 12, 0));
        MasterFormSupport.add(pnlEditor, pnlActions, 0, 3, 1, 0, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 8, 0));
        MasterFormSupport.add(pnlEditor, lblTerpilih, 0, 4, 1, 1, GridBagConstraints.HORIZONTAL, new Insets(4, 0, 0, 0));

        lblDaftarTitle = sectionTitle("DAFTAR KELAS");
        lblCariHint = helperText("Cari berdasarkan nama kelas, kursus, atau instruktur");
        txtCari = MasterFormSupport.field();
        btnCari = MasterFormSupport.button("CARI", new Color(13, 123, 102), Color.WHITE);
        pnlCari = new JPanel(new java.awt.GridBagLayout());
        pnlCari.setOpaque(false);
        MasterFormSupport.add(pnlCari, txtCari, 0, 0, 1, 0, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 8));
        MasterFormSupport.add(pnlCari, btnCari, 1, 0, 0, 0, GridBagConstraints.NONE, new Insets(0, 0, 0, 0));
        lblJumlah = helperText("0 kelas ditampilkan");
        tblKelas = new JTable();
        scrollKelas = new JScrollPane(tblKelas);
        MasterFormSupport.styleScroll(scrollKelas);
        lblTableHint = helperText("Klik satu baris untuk mengubah atau menghapus data kelas.");
        MasterFormSupport.add(pnlDaftar, lblDaftarTitle, 0, 0, 1, 0, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 3, 0));
        MasterFormSupport.add(pnlDaftar, lblCariHint, 0, 1, 1, 0, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 8, 0));
        MasterFormSupport.add(pnlDaftar, pnlCari, 0, 2, 1, 0, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 8, 0));
        MasterFormSupport.add(pnlDaftar, lblJumlah, 0, 3, 1, 0, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 8, 0));
        MasterFormSupport.add(pnlDaftar, scrollKelas, 0, 4, 1, 1, GridBagConstraints.BOTH, new Insets(0, 0, 8, 0));
        MasterFormSupport.add(pnlDaftar, lblTableHint, 0, 5, 1, 0, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0));

        pnlRoot.add(pnlHeader, MasterFormSupport.gbc(0, 0, 1, 1, 1, 0, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0)));
        pnlRoot.add(pnlContent, MasterFormSupport.gbc(0, 1, 1, 1, 1, 1, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0)));
        lblStatus = helperText("Siap. Pastikan tabel kursus dan instruktur sudah memiliki data.");
        pnlFooter = new JPanel(new java.awt.GridBagLayout());
        pnlFooter.setBackground(Color.WHITE);
        MasterFormSupport.add(pnlFooter, lblStatus, 0, 0, 1, 0, GridBagConstraints.HORIZONTAL, new Insets(5, 16, 5, 0));
        pnlRoot.add(pnlFooter, MasterFormSupport.gbc(0, 2, 1, 1, 1, 0, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0)));
        setContentPane(pnlRoot);
    }

    // </editor-fold>//GEN-END:initComponents

    private void addField(JPanel panel, JLabel label, java.awt.Component field, int row) {
        MasterFormSupport.add(panel, label, 0, row * 2, 1, 0, GridBagConstraints.HORIZONTAL, new Insets(6, 0, 2, 0));
        MasterFormSupport.add(panel, field, 0, row * 2 + 1, 1, 0, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 1, 0));
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
        tableModel = new DefaultTableModel(new String[]{"ID", "Nama Kelas", "Kursus", "Instruktur", "Tanggal Mulai", "Kapasitas"}, 0) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };
        tblKelas.setModel(tableModel);
        MasterFormSupport.styleTable(tblKelas);
        int[] widths = {38, 90, 120, 115, 105, 75};
        for (int i = 0; i < widths.length; i++) tblKelas.getColumnModel().getColumn(i).setPreferredWidth(widths[i]);
        tblKelas.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) selectTableRow();
        });
    }

    private void setupActions() {
        btnSimpan.addActionListener(e -> simpan());
        btnUbah.addActionListener(e -> ubah());
        btnHapus.addActionListener(e -> hapus());
        btnReset.addActionListener(e -> clearForm());
        btnMuat.addActionListener(e -> { txtCari.setText(""); clearForm(); loadCombos(); loadTable(); });
        btnCari.addActionListener(e -> cari());
        txtCari.addActionListener(e -> cari());
        getRootPane().setDefaultButton(btnCari);
    }

    private void loadCombos() {
        try {
            Object selectedCourse = cmbKursus.getSelectedItem();
            Object selectedInstructor = cmbInstruktur.getSelectedItem();
            cmbKursus.removeAllItems();
            for (Kursus item : kursusDAO.findAll()) cmbKursus.addItem(item);
            cmbInstruktur.removeAllItems();
            for (Instruktur item : instrukturDAO.findAll()) cmbInstruktur.addItem(item);
            if (selectedCourse instanceof Kursus) selectCourse(((Kursus) selectedCourse).getIdKursus());
            if (selectedInstructor instanceof Instruktur) selectInstructor(((Instruktur) selectedInstructor).getId());
            if (cmbKursus.getItemCount() == 0 || cmbInstruktur.getItemCount() == 0) {
                lblStatus.setText("Tambahkan minimal satu kursus dan satu instruktur sebelum membuat kelas.");
            }
        } catch (SQLException ex) { showSqlError("Gagal memuat pilihan kursus/instruktur", ex); }
    }

    private void fillTable(List<Kelas> rows) {
        tableModel.setRowCount(0);
        for (Kelas item : rows) tableModel.addRow(new Object[]{item.getIdKelas(), item.getNamaKelas(),
                item.getNamaKursus(), item.getNamaInstruktur(), item.getTanggalMulai(), item.getKapasitas()});
        lblJumlah.setText(rows.size() + " kelas ditampilkan");
    }

    private void loadTable() {
        try { fillTable(dao.findAll()); lblStatus.setText("Data kelas berhasil dimuat dari MySQL."); }
        catch (SQLException ex) { showSqlError("Gagal memuat data kelas", ex); }
    }

    private void cari() {
        String keyword = txtCari.getText().trim();
        clearForm();
        try {
            fillTable(keyword.isEmpty() ? dao.findAll() : dao.search(keyword));
            lblStatus.setText(keyword.isEmpty() ? "Seluruh data kelas ditampilkan." : "Hasil pencarian: " + keyword);
        } catch (SQLException ex) { showSqlError("Pencarian kelas gagal", ex); }
    }

    private void clearForm() {
        selectedId = -1;
        txtNamaKelas.setText("");
        txtTanggalMulai.setText("");
        txtKapasitas.setText("20");
        if (cmbKursus.getItemCount() > 0) cmbKursus.setSelectedIndex(0);
        if (cmbInstruktur.getItemCount() > 0) cmbInstruktur.setSelectedIndex(0);
        tblKelas.clearSelection();
        btnSimpan.setEnabled(true); btnUbah.setEnabled(false); btnHapus.setEnabled(false);
        lblTerpilih.setText("Mode: tambah kelas baru");
        txtNamaKelas.requestFocusInWindow();
    }

    private void selectTableRow() {
        int viewRow = tblKelas.getSelectedRow();
        if (viewRow < 0) return;
        int row = tblKelas.convertRowIndexToModel(viewRow);
        selectedId = Integer.parseInt(valueAt(row, 0));
        txtNamaKelas.setText(valueAt(row, 1));
        // Cari ulang ID relasi berdasarkan nama yang tampil pada tabel.
        String courseName = valueAt(row, 2);
        String instructorName = valueAt(row, 3);
        try {
            Kelas selected = findById(selectedId);
            if (selected != null) {
                selectCourse(selected.getIdKursus());
                selectInstructor(selected.getIdInstruktur());
                txtTanggalMulai.setText(selected.getTanggalMulai().toString());
                txtKapasitas.setText(String.valueOf(selected.getKapasitas()));
            } else {
                txtTanggalMulai.setText("");
                txtKapasitas.setText("20");
            }
        } catch (SQLException ex) { showSqlError("Gagal membaca detail kelas", ex); }
        btnSimpan.setEnabled(false); btnUbah.setEnabled(true); btnHapus.setEnabled(true);
        lblTerpilih.setText("Mode: edit kelas ID " + selectedId);
    }

    private Kelas findById(int id) throws SQLException {
        for (Kelas item : dao.findAll()) if (item.getIdKelas() == id) return item;
        return null;
    }

    private String valueAt(int row, int col) {
        Object value = tableModel.getValueAt(row, col);
        return value == null ? "" : value.toString();
    }

    private void selectCourse(int id) {
        for (int i = 0; i < cmbKursus.getItemCount(); i++) {
            Kursus item = cmbKursus.getItemAt(i);
            if (item.getIdKursus() == id) { cmbKursus.setSelectedIndex(i); return; }
        }
    }
    private void selectInstructor(int id) {
        for (int i = 0; i < cmbInstruktur.getItemCount(); i++) {
            Instruktur item = cmbInstruktur.getItemAt(i);
            if (item.getId() == id) { cmbInstruktur.setSelectedIndex(i); return; }
        }
    }

    private Kelas formData() {
        Object courseValue = cmbKursus.getSelectedItem();
        Object instructorValue = cmbInstruktur.getSelectedItem();
        if (!(courseValue instanceof Kursus)) throw new IllegalArgumentException("Belum ada data kursus. Tambahkan kursus terlebih dahulu.");
        if (!(instructorValue instanceof Instruktur)) throw new IllegalArgumentException("Belum ada data instruktur. Tambahkan instruktur terlebih dahulu.");
        LocalDate date;
        try { date = LocalDate.parse(txtTanggalMulai.getText().trim()); }
        catch (DateTimeParseException ex) { throw new IllegalArgumentException("Tanggal mulai harus menggunakan format YYYY-MM-DD, contoh 2026-11-15."); }
        int capacity;
        try { capacity = Integer.parseInt(txtKapasitas.getText().trim()); }
        catch (NumberFormatException ex) { throw new IllegalArgumentException("Kapasitas harus berupa bilangan bulat."); }
        Kursus course = (Kursus) courseValue;
        Instruktur instructor = (Instruktur) instructorValue;
        return new Kelas(selectedId > 0 ? selectedId : 0, course.getIdKursus(), instructor.getId(),
                txtNamaKelas.getText(), date, capacity);
    }

    private boolean validasi() {
        if (txtNamaKelas.getText().trim().isEmpty()) { warn("Nama kelas wajib diisi.", txtNamaKelas); return false; }
        try { formData(); return true; }
        catch (IllegalArgumentException ex) { warn(ex.getMessage(), null); return false; }
    }

    private void simpan() {
        if (selectedId > 0) { warn("Klik Reset Form untuk menambahkan kelas baru.", null); return; }
        if (!validasi()) return;
        try {
            Kelas item = formData();
            if (dao.insert(item)) {
                JOptionPane.showMessageDialog(this, "Data kelas berhasil disimpan.\nID: " + item.getIdKelas(),
                        "Simpan Berhasil", JOptionPane.INFORMATION_MESSAGE);
                clearForm(); loadTable();
            }
        } catch (SQLException ex) { showSqlError("Gagal menyimpan kelas", ex); }
    }

    private void ubah() {
        if (selectedId <= 0) { warn("Pilih satu baris kelas dari tabel terlebih dahulu.", null); return; }
        if (!validasi()) return;
        try {
            if (dao.update(formData())) {
                JOptionPane.showMessageDialog(this, "Data kelas berhasil diubah.", "Ubah Berhasil", JOptionPane.INFORMATION_MESSAGE);
                clearForm(); loadTable();
            }
        } catch (SQLException ex) { showSqlError("Gagal mengubah kelas", ex); }
    }

    private void hapus() {
        if (selectedId <= 0) { warn("Pilih kelas yang ingin dihapus.", null); return; }
        int confirm = JOptionPane.showConfirmDialog(this, "Hapus kelas " + txtNamaKelas.getText() + "?",
                "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (confirm != JOptionPane.YES_OPTION) return;
        try {
            if (dao.delete(selectedId)) { clearForm(); loadTable(); }
        } catch (SQLException ex) {
            if (ex.getErrorCode() == 1451) warn("Kelas masih memiliki data pendaftaran. Data tidak dapat dihapus sebelum pendaftaran terkait ditangani.", null);
            else showSqlError("Gagal menghapus kelas", ex);
        }
    }

    private void warn(String message, java.awt.Component focus) {
        JOptionPane.showMessageDialog(this, message, "Validasi Master Kelas", JOptionPane.WARNING_MESSAGE);
        if (focus != null) focus.requestFocusInWindow();
    }

    private void showSqlError(String action, SQLException ex) {
        String detail = ex.getMessage() == null ? "Tidak ada detail dari database." : ex.getMessage();
        JOptionPane.showMessageDialog(this, action + ".\nPastikan MySQL Laragon aktif dan tabel kelas, kursus, serta instruktur tersedia.\n\nDetail: " + detail,
                "Database Error", JOptionPane.ERROR_MESSAGE);
        lblStatus.setText(action + ". Periksa koneksi MySQL.");
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JPanel pnlRoot, pnlHeader, pnlContent, pnlEditor, pnlDaftar, pnlFields, pnlActions, pnlCari, pnlFooter;
    private JLabel lblTitle, lblSubTitle, lblEditorTitle, lblEditorHint, lblNamaKelas, lblKursus,
            lblInstruktur, lblTanggalMulai, lblKapasitas, lblDaftarTitle, lblCariHint,
            lblJumlah, lblTerpilih, lblTableHint, lblStatus;
    private JTextField txtNamaKelas, txtTanggalMulai, txtKapasitas, txtCari;
    private JComboBox<Kursus> cmbKursus;
    private JComboBox<Instruktur> cmbInstruktur;
    private JButton btnSimpan, btnUbah, btnHapus, btnReset, btnMuat, btnCari;
    private JTable tblKelas;
    private JScrollPane scrollKelas;
    //GEN-END:variables
}
