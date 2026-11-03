package view;

import dao.PesertaDAO;
import java.awt.Color;
import java.awt.Dimension;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import model.Peserta;

/**
 * Pertemuan 10: Master Peserta, memperluas INSERT P9 menjadi CRUD lengkap.
 * Form ini adalah JFrame Form NetBeans (.java + .form), bukan proyek terpisah.
 */
public class PesertaFrame extends javax.swing.JFrame {

    private final PesertaDAO dao = new PesertaDAO();
    private int selectedId = -1;
    private DefaultTableModel modelTabel;

    public PesertaFrame() {
        initComponents();
        setMinimumSize(new Dimension(1070, 620));
        setSize(1260, 735);
        setLocationRelativeTo(null);
        setResizable(true);
        setupTable();
        setupVisualTheme();
        setupActions();
        clearForm();
        loadTable();
    }

    /** JTable non-editable. Perubahan data wajib melalui form dan DAO. */
    private void setupTable() {
        modelTabel = new DefaultTableModel(
                new String[]{"ID", "NIM", "Nama", "JK", "Prodi", "No. HP", "Email"}, 0) {
            @Override
            public boolean isCellEditable(int row, int col) { return false; }
        };
        tblPeserta.setModel(modelTabel);
        tblPeserta.setRowHeight(29);
        tblPeserta.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblPeserta.setFillsViewportHeight(true);
        tblPeserta.setShowHorizontalLines(true);
        tblPeserta.setGridColor(new Color(226, 235, 232));
        tblPeserta.setSelectionBackground(new Color(214, 236, 229));
        tblPeserta.setSelectionForeground(new Color(22, 54, 69));
        // Sorting tidak diaktifkan agar selection konsisten saat model di-refresh.
        tblPeserta.getColumnModel().getColumn(0).setPreferredWidth(46);
        tblPeserta.getColumnModel().getColumn(1).setPreferredWidth(86);
        tblPeserta.getColumnModel().getColumn(2).setPreferredWidth(145);
        tblPeserta.getColumnModel().getColumn(3).setPreferredWidth(36);
        tblPeserta.getColumnModel().getColumn(4).setPreferredWidth(122);
        tblPeserta.getColumnModel().getColumn(5).setPreferredWidth(112);
        tblPeserta.getColumnModel().getColumn(6).setPreferredWidth(165);
        JTableHeader header = tblPeserta.getTableHeader();
        header.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 12));
        header.setBackground(new Color(22, 54, 69));
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(100, 36));
        scrollPeserta.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(220, 230, 228)));
        pnlEditor.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(220, 230, 228)));
        pnlDaftar.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(220, 230, 228)));
        for (javax.swing.JButton button : new javax.swing.JButton[]{
                btnSimpan, btnUbah, btnHapus, btnReset, btnMuat, btnCari}) {
            button.setFocusPainted(false);
            button.setPreferredSize(new Dimension(120, 37));
        }
        btnMuat.setPreferredSize(new Dimension(140, 37));
        for (javax.swing.JTextField textField : new javax.swing.JTextField[]{
                txtNim, txtNama, txtProdi, txtNoHp, txtEmail, txtCari}) {
            textField.setPreferredSize(new Dimension(175, 32));
            textField.setMargin(new java.awt.Insets(5, 8, 5, 8));
        }
        // Listener selection juga bekerja saat pengguna memakai keyboard atau sorting.
        tblPeserta.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) ambilBarisTerpilih();
        });
    }

    /**
     * Tampilan Pertemuan 10. Styling diterapkan setelah initComponents(),
     * sehingga pasangan .form NetBeans tetap bisa diedit lewat tab Design.
     * BasicButtonUI custom mencegah Windows Look and Feel mengabaikan warna.
     */
    private void setupVisualTheme() {
        java.awt.Color ink = new java.awt.Color(25, 56, 70);
        java.awt.Color line = new java.awt.Color(214, 226, 226);
        java.awt.Color forest = new java.awt.Color(16, 142, 111);

        // Satu garis tepi bersih dan padding konsisten untuk masing-masing kartu.
        pnlEditor.setBorder(javax.swing.BorderFactory.createLineBorder(line));
        pnlDaftar.setBorder(javax.swing.BorderFactory.createLineBorder(line));
        pnlFields.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 18, 2, 18));
        pnlAksi.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 18, 2, 18));
        lblTerpilih.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 18, 0, 18));
        pnlCari.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 18, 0, 18));
        lblJumlah.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 18, 0, 18));
        lblTableHint.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 18, 0, 18));
        scrollPeserta.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                javax.swing.BorderFactory.createEmptyBorder(0, 18, 0, 18),
                javax.swing.BorderFactory.createLineBorder(line)));
        scrollPeserta.getViewport().setBackground(java.awt.Color.WHITE);

        for (javax.swing.JTextField field : new javax.swing.JTextField[]{
                txtNim, txtNama, txtProdi, txtNoHp, txtEmail, txtCari}) {
            field.setFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 13));
            field.setBackground(java.awt.Color.WHITE);
            field.setForeground(ink);
            field.setCaretColor(forest);
            field.setSelectionColor(new java.awt.Color(199, 232, 225));
            field.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                    javax.swing.BorderFactory.createLineBorder(line, 1),
                    javax.swing.BorderFactory.createEmptyBorder(5, 9, 5, 9)));
            field.setPreferredSize(new java.awt.Dimension(160, 35));
        }

        // Gunakan UI tombol yang mewarnai permukaan sendiri; tidak bergantung
        // pada Windows/Nimbus/Metal yang dapat mengabaikan setBackground().
        styleAction(btnSimpan, new java.awt.Color(12, 153, 119), java.awt.Color.WHITE);
        styleAction(btnUbah, new java.awt.Color(39, 103, 172), java.awt.Color.WHITE);
        styleAction(btnHapus, new java.awt.Color(186, 64, 75), java.awt.Color.WHITE);
        styleAction(btnReset, new java.awt.Color(228, 239, 240), ink);
        styleAction(btnMuat, new java.awt.Color(228, 239, 240), ink);
        styleAction(btnCari, new java.awt.Color(13, 123, 102), java.awt.Color.WHITE);
        btnCari.setPreferredSize(new java.awt.Dimension(94, 36));

        // Kepala tabel dulu tidak terbaca (warna putih pada latar terang).
        // Renderer opaque membuat kontras tetap jelas pada semua Look and Feel.
        javax.swing.table.JTableHeader head = tblPeserta.getTableHeader();
        head.setReorderingAllowed(false);
        head.setPreferredSize(new java.awt.Dimension(1, 39));
        head.setDefaultRenderer(new javax.swing.table.DefaultTableCellRenderer() {
            @Override public java.awt.Component getTableCellRendererComponent(
                    javax.swing.JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, false, false, row, column);
                setOpaque(true);
                setBackground(new java.awt.Color(22, 54, 69));
                setForeground(java.awt.Color.WHITE);
                setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 12));
                setBorder(javax.swing.BorderFactory.createCompoundBorder(
                        javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(13, 123, 102)),
                        javax.swing.BorderFactory.createEmptyBorder(0, 9, 0, 3)));
                return this;
            }
        });
        tblPeserta.setRowHeight(38);
        tblPeserta.setFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 12));
        tblPeserta.setIntercellSpacing(new java.awt.Dimension(0, 1));
        tblPeserta.setShowVerticalLines(false);
        tblPeserta.setSelectionBackground(new java.awt.Color(204, 235, 225));
        tblPeserta.setSelectionForeground(ink);
        tblPeserta.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer() {
            @Override public java.awt.Component getTableCellRendererComponent(
                    javax.swing.JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int col) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
                setOpaque(true);
                setFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 12));
                if (!isSelected) {
                    setBackground(row % 2 == 0 ? java.awt.Color.WHITE : new java.awt.Color(245, 250, 249));
                    setForeground(ink);
                } else {
                    setBackground(new java.awt.Color(204, 235, 225));
                    setForeground(ink);
                }
                setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 9, 0, 5));
                setHorizontalAlignment(col == 0 || col == 3
                        ? javax.swing.SwingConstants.CENTER : javax.swing.SwingConstants.LEFT);
                return this;
            }
        });
        // Kolom menyesuaikan ukuran jendela agar tidak perlu scrollbar horizontal.
        int[] widths = {38, 88, 132, 34, 100, 104, 164};
        tblPeserta.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        for (int i = 0; i < widths.length; i++) {
            tblPeserta.getColumnModel().getColumn(i).setPreferredWidth(widths[i]);
        }
        lblEditorHint.setFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 12));
        lblCariHint.setFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 12));
        lblJumlah.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 12));
    }

    private static void styleAction(javax.swing.JButton button,
                                    java.awt.Color base, java.awt.Color text) {
        button.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 12));
        button.setForeground(text);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setRolloverEnabled(true);
        button.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
        button.setPreferredSize(new java.awt.Dimension(132, 38));
        button.setUI(new ColorButtonUI(base, text));
    }

    /** Menggambar tombol membulat dengan kondisi hover, tekan, dan disabled. */
    private static final class ColorButtonUI extends javax.swing.plaf.basic.BasicButtonUI {
        private final java.awt.Color base;
        private final java.awt.Color text;
        ColorButtonUI(java.awt.Color base, java.awt.Color text) {
            this.base = base;
            this.text = text;
        }
        @Override public void update(java.awt.Graphics g, javax.swing.JComponent c) {
            paint(g, c);
        }
        @Override public void paint(java.awt.Graphics g, javax.swing.JComponent c) {
            javax.swing.AbstractButton b = (javax.swing.AbstractButton)c;
            java.awt.Graphics2D g2 = (java.awt.Graphics2D)g.create();
            g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING,
                    java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
            boolean enabled = b.isEnabled();
            java.awt.Color bg = enabled ? base : mix(base, new java.awt.Color(242, 247, 248), 0.77);
            if (enabled && b.getModel().isPressed()) bg = mix(bg, java.awt.Color.BLACK, 0.18);
            else if (enabled && b.getModel().isRollover()) bg = mix(bg, java.awt.Color.WHITE, 0.12);
            int w = b.getWidth()-1, h=b.getHeight()-1;
            g2.setColor(bg);
            g2.fillRoundRect(0, 0, w, h, 12, 12);
            g2.setColor(mix(bg, java.awt.Color.BLACK, 0.12));
            g2.setStroke(new java.awt.BasicStroke(1f));
            g2.drawRoundRect(0, 0, w, h, 12, 12);
            if (b.hasFocus() && enabled) {
                g2.setColor(new java.awt.Color(15, 153, 119));
                g2.drawRoundRect(2, 2, w-4, h-4, 9, 9);
            }
            g2.dispose();
            super.paint(g, c);
        }
        @Override protected void paintText(java.awt.Graphics g,
                                           javax.swing.AbstractButton b,
                                           java.awt.Rectangle r, String label) {
            g.setFont(b.getFont());
            g.setColor(b.isEnabled() ? text : new java.awt.Color(105, 126, 136));
            javax.swing.plaf.basic.BasicGraphicsUtils.drawStringUnderlineCharAt(
                    g, label, b.getDisplayedMnemonicIndex(), r.x, r.y + g.getFontMetrics().getAscent());
        }
        private static java.awt.Color mix(java.awt.Color a, java.awt.Color b, double t) {
            return new java.awt.Color(
                    (int)(a.getRed()*(1-t)+b.getRed()*t),
                    (int)(a.getGreen()*(1-t)+b.getGreen()*t),
                    (int)(a.getBlue()*(1-t)+b.getBlue()*t));
        }
    }

    private void setupActions() {
        btnSimpan.addActionListener(e -> simpan());
        btnUbah.addActionListener(e -> ubah());
        btnHapus.addActionListener(e -> hapus());
        btnCari.addActionListener(e -> cari());
        btnReset.addActionListener(e -> resetSemua());
        btnMuat.addActionListener(e -> refreshData());
        txtCari.addActionListener(e -> cari());
        getRootPane().setDefaultButton(btnCari);
    }

    /** Bersihkan model dahulu agar refresh tidak menghasilkan row ganda. */
    private void fillTable(List<Peserta> peserta) {
        modelTabel.setRowCount(0);
        for (Peserta p : peserta) {
            modelTabel.addRow(new Object[]{
                p.getIdPeserta(), p.getNim(), p.getNama(), p.getJenisKelamin(),
                p.getProdi(), p.getNoHp(), p.getEmail()
            });
        }
        lblJumlah.setText(peserta.size() + " peserta ditampilkan");
    }

    private boolean loadTable() {
        try {
            fillTable(dao.findAll());
            lblStatus.setText("Data terbaru berhasil dimuat dari MySQL.");
            return true;
        } catch (SQLException ex) {
            showSqlError("Gagal memuat data peserta", ex);
            return false;
        }
    }

    /** Refresh setelah INSERT, UPDATE, DELETE, dan tombol Muat Ulang. */
    private void refreshData() {
        txtCari.setText("");
        clearForm();
        loadTable();
    }

    /** Dipanggil Form Pendaftaran P9 jika Master Peserta sedang terbuka. */
    public void refreshFromRegistration() {
        refreshData();
    }

    private void clearForm() {
        selectedId = -1;
        txtNim.setText("");
        txtNama.setText("");
        bgJenisKelamin.clearSelection();
        txtProdi.setText("");
        txtNoHp.setText("");
        txtEmail.setText("");
        tblPeserta.clearSelection();
        btnUbah.setEnabled(false);
        btnHapus.setEnabled(false);
        btnSimpan.setEnabled(true);
        lblTerpilih.setText("Mode: tambah peserta baru");
        txtNim.requestFocusInWindow();
    }

    private void resetSemua() {
        txtCari.setText("");
        clearForm();
        loadTable();
        lblStatus.setText("Form, pilihan tabel, dan pencarian telah direset.");
    }

    private String getJenisKelamin() {
        if (rbLaki.isSelected()) return "L";
        if (rbPerempuan.isSelected()) return "P";
        return null;
    }

    /** Validasi sebelum membuat Peserta, tidak bergantung pada query SQL. */
    private boolean validateForm() {
        if (txtNim.getText().trim().isEmpty()) {
            warn("NIM wajib diisi.", txtNim); return false;
        }
        if (txtNama.getText().trim().isEmpty()) {
            warn("Nama peserta wajib diisi.", txtNama); return false;
        }
        if (getJenisKelamin() == null) {
            warn("Pilih jenis kelamin.", null); return false;
        }
        if (txtProdi.getText().trim().isEmpty()) {
            warn("Program studi wajib diisi.", txtProdi); return false;
        }
        return true;
    }

    private static String str(Object x) {
        return x == null ? "" : x.toString();
    }

    private Peserta getFormData() {
        // Memakai constructor baru P9; constructor inheritance P5 tetap tersedia.
        Peserta p = new Peserta(
                txtNim.getText().trim(), txtNama.getText().trim(),
                getJenisKelamin(), txtProdi.getText().trim(),
                txtNoHp.getText().trim(), txtEmail.getText().trim());
        if (selectedId > 0) p.setIdPeserta(selectedId);
        return p;
    }

    /** Ambil PK dari kolom ID, BUKAN indeks baris JTable. */
    private void ambilBarisTerpilih() {
        int visualRow = tblPeserta.getSelectedRow();
        if (visualRow < 0) return;
        int row = tblPeserta.convertRowIndexToModel(visualRow);
        selectedId = Integer.parseInt(str(modelTabel.getValueAt(row, 0)));
        txtNim.setText(str(modelTabel.getValueAt(row, 1)));
        txtNama.setText(str(modelTabel.getValueAt(row, 2)));
        String jk = str(modelTabel.getValueAt(row, 3));
        rbLaki.setSelected("L".equals(jk));
        rbPerempuan.setSelected("P".equals(jk));
        txtProdi.setText(str(modelTabel.getValueAt(row, 4)));
        txtNoHp.setText(str(modelTabel.getValueAt(row, 5)));
        txtEmail.setText(str(modelTabel.getValueAt(row, 6)));
        btnUbah.setEnabled(true);
        btnHapus.setEnabled(true);
        btnSimpan.setEnabled(false); // cegah ketidaksengajaan INSERT data duplikat
        lblTerpilih.setText("Mode: edit peserta ID " + selectedId);
    }

    private void simpan() {
        if (selectedId > 0) {
            warn("Klik Reset Form untuk menambah peserta baru.", null);
            return;
        }
        if (!validateForm()) return;
        try {
            Peserta p = getFormData();
            if (dao.insert(p)) {
                JOptionPane.showMessageDialog(this,
                        "Data peserta berhasil disimpan.\nID: " + p.getIdPeserta(),
                        "Simpan Berhasil", JOptionPane.INFORMATION_MESSAGE);
                refreshData();
            } else {
                warn("Data peserta tidak tersimpan.", null);
            }
        } catch (IllegalArgumentException ex) {
            warn(ex.getMessage(), null);
        } catch (SQLException ex) {
            showSqlError("Gagal menyimpan peserta", ex);
        }
    }

    private void ubah() {
        if (selectedId <= 0) {
            warn("Pilih satu baris peserta dari tabel terlebih dahulu.", null);
            return;
        }
        if (!validateForm()) return;
        try {
            Peserta p = getFormData();
            if (dao.update(p)) {
                JOptionPane.showMessageDialog(this,
                        "Data peserta berhasil diubah.", "Ubah Berhasil",
                        JOptionPane.INFORMATION_MESSAGE);
                refreshData();
            } else {
                warn("Peserta tidak ditemukan atau belum ada perubahan.", null);
                refreshData();
            }
        } catch (IllegalArgumentException ex) {
            warn(ex.getMessage(), null);
        } catch (SQLException ex) {
            showSqlError("Gagal mengubah peserta", ex);
        }
    }

    private void hapus() {
        if (selectedId <= 0) {
            warn("Pilih peserta yang ingin dihapus.", null);
            return;
        }
        String nama = txtNama.getText().trim();
        int jawab = JOptionPane.showConfirmDialog(this,
                "Yakin ingin menghapus peserta:\n" + nama + " (ID " + selectedId + ")?",
                "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
        if (jawab != JOptionPane.YES_OPTION) return;
        try {
            if (dao.delete(selectedId)) {
                JOptionPane.showMessageDialog(this,
                        "Peserta berhasil dihapus.", "Hapus Berhasil",
                        JOptionPane.INFORMATION_MESSAGE);
                refreshData();
            } else {
                warn("Data peserta tidak ditemukan.", null);
                refreshData();
            }
        } catch (SQLException ex) {
            showSqlError("Gagal menghapus peserta", ex);
        }
    }

    private void cari() {
        String keyword = txtCari.getText().trim();
        clearForm();
        try {
            fillTable(keyword.isEmpty() ? dao.findAll() : dao.search(keyword));
            lblStatus.setText(keyword.isEmpty() ? "Seluruh data ditampilkan." :
                    "Hasil pencarian untuk: " + keyword);
        } catch (SQLException ex) {
            showSqlError("Pencarian gagal", ex);
        }
    }

    private void warn(String msg, javax.swing.JComponent comp) {
        JOptionPane.showMessageDialog(this, msg, "Validasi Peserta",
                JOptionPane.WARNING_MESSAGE);
        if (comp != null) comp.requestFocusInWindow();
    }

    private void showSqlError(String action, SQLException ex) {
        String info;
        if (ex.getErrorCode() == 1062) {
            info = "NIM sudah terdaftar. Gunakan NIM yang berbeda.";
        } else if (ex.getErrorCode() == 1451 || ex.getErrorCode() == 1452) {
            info = "Peserta masih memiliki data pendaftaran terkait, sehingga tidak dapat dihapus.";
        } else if (ex.getSQLState() != null && ex.getSQLState().startsWith("08")) {
            info = "Koneksi database gagal. Periksa MySQL Laragon dan port 3306.";
        } else {
            info = ex.getMessage();
        }
        lblStatus.setText(action + ". " + info);
        JOptionPane.showMessageDialog(this, action + ".\n" + info,
                "Database Error", JOptionPane.ERROR_MESSAGE);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        bgJenisKelamin = new javax.swing.ButtonGroup();
        pnlRoot = new javax.swing.JPanel();
        pnlHeader = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        lblSubTitle = new javax.swing.JLabel();
        pnlContent = new javax.swing.JPanel();
        pnlEditor = new javax.swing.JPanel();
        lblEditorTitle = new javax.swing.JLabel();
        lblEditorHint = new javax.swing.JLabel();
        pnlFields = new javax.swing.JPanel();
        lblNim = new javax.swing.JLabel();
        lblNama = new javax.swing.JLabel();
        txtNim = new javax.swing.JTextField();
        txtNama = new javax.swing.JTextField();
        lblKelamin = new javax.swing.JLabel();
        lblProdi = new javax.swing.JLabel();
        pnlKelamin = new javax.swing.JPanel();
        rbLaki = new javax.swing.JRadioButton();
        rbPerempuan = new javax.swing.JRadioButton();
        txtProdi = new javax.swing.JTextField();
        lblNoHp = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        txtNoHp = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        pnlAksi = new javax.swing.JPanel();
        btnSimpan = new javax.swing.JButton();
        btnUbah = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        btnMuat = new javax.swing.JButton();
        lblTerpilih = new javax.swing.JLabel();
        pnlFillerEditor = new javax.swing.JPanel();
        pnlDaftar = new javax.swing.JPanel();
        lblDaftarTitle = new javax.swing.JLabel();
        lblCariHint = new javax.swing.JLabel();
        pnlCari = new javax.swing.JPanel();
        txtCari = new javax.swing.JTextField();
        btnCari = new javax.swing.JButton();
        lblJumlah = new javax.swing.JLabel();
        scrollPeserta = new javax.swing.JScrollPane();
        tblPeserta = new javax.swing.JTable();
        lblTableHint = new javax.swing.JLabel();
        pnlFooter = new javax.swing.JPanel();
        lblStatus = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SIKURSUS Desktop | Master Peserta");
        getContentPane().setLayout(new java.awt.GridBagLayout());

        pnlRoot.setBackground(new java.awt.Color(243, 247, 246));
        pnlRoot.setLayout(new java.awt.GridBagLayout());

        pnlHeader.setBackground(new java.awt.Color(22, 54, 69));
        pnlHeader.setLayout(new java.awt.GridBagLayout());

        lblTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle.setFont(new java.awt.Font("SansSerif", 1, 21)); // NOI18N
        lblTitle.setText("MASTER PESERTA");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(18, 25, 3, 25);
        pnlHeader.add(lblTitle, gridBagConstraints);

        lblSubTitle.setForeground(new java.awt.Color(212, 229, 224));
        lblSubTitle.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblSubTitle.setText("Kelola data peserta  ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 25, 17, 25);
        pnlHeader.add(lblSubTitle, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        pnlRoot.add(pnlHeader, gridBagConstraints);

        pnlContent.setBackground(new java.awt.Color(243, 247, 246));
        pnlContent.setLayout(new java.awt.GridBagLayout());

        pnlEditor.setBackground(new java.awt.Color(255, 255, 255));
        pnlEditor.setLayout(new java.awt.GridBagLayout());

        lblEditorTitle.setForeground(new java.awt.Color(13, 120, 98));
        lblEditorTitle.setFont(new java.awt.Font("SansSerif", 1, 17)); // NOI18N
        lblEditorTitle.setText("DATA PESERTA");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(15, 18, 1, 18);
        pnlEditor.add(lblEditorTitle, gridBagConstraints);

        lblEditorHint.setForeground(new java.awt.Color(100, 122, 133));
        lblEditorHint.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        lblEditorHint.setText("Lengkapi data lalu Simpan, atau pilih baris untuk Ubah/Hapus.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 18, 14, 18);
        pnlEditor.add(lblEditorHint, gridBagConstraints);

        pnlFields.setBackground(new java.awt.Color(255, 255, 255));
        pnlFields.setLayout(new java.awt.GridBagLayout());

        lblNim.setForeground(new java.awt.Color(24, 55, 68));
        lblNim.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblNim.setText("NIM *");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(9, 0, 3, 8);
        pnlFields.add(lblNim, gridBagConstraints);

        lblNama.setForeground(new java.awt.Color(24, 55, 68));
        lblNama.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblNama.setText("Nama peserta *");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(9, 7, 3, 0);
        pnlFields.add(lblNama, gridBagConstraints);

        txtNim.setForeground(new java.awt.Color(24, 55, 68));
        txtNim.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        txtNim.setColumns(17);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 9);
        pnlFields.add(txtNim, gridBagConstraints);

        txtNama.setForeground(new java.awt.Color(24, 55, 68));
        txtNama.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        txtNama.setColumns(17);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 7, 5, 0);
        pnlFields.add(txtNama, gridBagConstraints);

        lblKelamin.setForeground(new java.awt.Color(24, 55, 68));
        lblKelamin.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblKelamin.setText("Jenis kelamin *");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(8, 0, 2, 8);
        pnlFields.add(lblKelamin, gridBagConstraints);

        lblProdi.setForeground(new java.awt.Color(24, 55, 68));
        lblProdi.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblProdi.setText("Program studi *");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(8, 7, 2, 0);
        pnlFields.add(lblProdi, gridBagConstraints);

        pnlKelamin.setBackground(new java.awt.Color(255, 255, 255));
        pnlKelamin.setLayout(new java.awt.GridBagLayout());

        rbLaki.setBackground(new java.awt.Color(255, 255, 255));
        bgJenisKelamin.add(rbLaki);
        rbLaki.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        rbLaki.setText("Laki-laki");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 12);
        pnlKelamin.add(rbLaki, gridBagConstraints);

        rbPerempuan.setBackground(new java.awt.Color(255, 255, 255));
        bgJenisKelamin.add(rbPerempuan);
        rbPerempuan.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        rbPerempuan.setText("Perempuan");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        pnlKelamin.add(rbPerempuan, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        pnlFields.add(pnlKelamin, gridBagConstraints);

        txtProdi.setForeground(new java.awt.Color(24, 55, 68));
        txtProdi.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        txtProdi.setColumns(17);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 7, 5, 0);
        pnlFields.add(txtProdi, gridBagConstraints);

        lblNoHp.setForeground(new java.awt.Color(24, 55, 68));
        lblNoHp.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblNoHp.setText("Nomor HP");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(8, 0, 3, 8);
        pnlFields.add(lblNoHp, gridBagConstraints);

        lblEmail.setForeground(new java.awt.Color(24, 55, 68));
        lblEmail.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblEmail.setText("Email");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(8, 7, 3, 0);
        pnlFields.add(lblEmail, gridBagConstraints);

        txtNoHp.setForeground(new java.awt.Color(24, 55, 68));
        txtNoHp.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        txtNoHp.setColumns(17);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 9);
        pnlFields.add(txtNoHp, gridBagConstraints);

        txtEmail.setForeground(new java.awt.Color(24, 55, 68));
        txtEmail.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        txtEmail.setColumns(17);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 7, 3, 0);
        pnlFields.add(txtEmail, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        pnlEditor.add(pnlFields, gridBagConstraints);

        pnlAksi.setBackground(new java.awt.Color(255, 255, 255));
        pnlAksi.setLayout(new java.awt.GridBagLayout());

        btnSimpan.setBackground(new java.awt.Color(23, 116, 91));
        btnSimpan.setForeground(new java.awt.Color(255, 255, 255));
        btnSimpan.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnSimpan.setText("SIMPAN");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 6, 5);
        pnlAksi.add(btnSimpan, gridBagConstraints);

        btnUbah.setBackground(new java.awt.Color(22, 54, 69));
        btnUbah.setForeground(new java.awt.Color(255, 255, 255));
        btnUbah.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnUbah.setText("UBAH");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 6, 5);
        pnlAksi.add(btnUbah, gridBagConstraints);

        btnHapus.setBackground(new java.awt.Color(173, 54, 68));
        btnHapus.setForeground(new java.awt.Color(255, 255, 255));
        btnHapus.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnHapus.setText("HAPUS");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 6, 0);
        pnlAksi.add(btnHapus, gridBagConstraints);

        btnReset.setBackground(new java.awt.Color(232, 241, 239));
        btnReset.setForeground(new java.awt.Color(22, 54, 69));
        btnReset.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnReset.setText("RESET FORM");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        pnlAksi.add(btnReset, gridBagConstraints);

        btnMuat.setBackground(new java.awt.Color(232, 241, 239));
        btnMuat.setForeground(new java.awt.Color(22, 54, 69));
        btnMuat.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnMuat.setText("MUAT ULANG");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        pnlAksi.add(btnMuat, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(14, 0, 0, 0);
        pnlEditor.add(pnlAksi, gridBagConstraints);

        lblTerpilih.setForeground(new java.awt.Color(100, 122, 133));
        lblTerpilih.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(13, 0, 7, 0);
        pnlEditor.add(lblTerpilih, gridBagConstraints);

        pnlFillerEditor.setBackground(new java.awt.Color(255, 255, 255));
        pnlFillerEditor.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlEditor.add(pnlFillerEditor, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.43;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(12, 16, 12, 7);
        pnlContent.add(pnlEditor, gridBagConstraints);

        pnlDaftar.setBackground(new java.awt.Color(255, 255, 255));
        pnlDaftar.setLayout(new java.awt.GridBagLayout());

        lblDaftarTitle.setForeground(new java.awt.Color(13, 120, 98));
        lblDaftarTitle.setFont(new java.awt.Font("SansSerif", 1, 17)); // NOI18N
        lblDaftarTitle.setText("DAFTAR PESERTA");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(15, 18, 2, 18);
        pnlDaftar.add(lblDaftarTitle, gridBagConstraints);

        lblCariHint.setForeground(new java.awt.Color(100, 122, 133));
        lblCariHint.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        lblCariHint.setText("Cari peserta berdasarkan nama atau NIM");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 18, 8, 18);
        pnlDaftar.add(lblCariHint, gridBagConstraints);

        pnlCari.setBackground(new java.awt.Color(255, 255, 255));
        pnlCari.setLayout(new java.awt.GridBagLayout());

        txtCari.setForeground(new java.awt.Color(24, 55, 68));
        txtCari.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        txtCari.setColumns(25);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 8);
        pnlCari.add(txtCari, gridBagConstraints);

        btnCari.setBackground(new java.awt.Color(13, 120, 98));
        btnCari.setForeground(new java.awt.Color(255, 255, 255));
        btnCari.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnCari.setText("CARI");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        pnlCari.add(btnCari, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        pnlDaftar.add(pnlCari, gridBagConstraints);

        lblJumlah.setForeground(new java.awt.Color(100, 122, 133));
        lblJumlah.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        lblJumlah.setText("0 peserta ditampilkan");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(12, 0, 0, 0);
        pnlDaftar.add(lblJumlah, gridBagConstraints);

        tblPeserta.setForeground(new java.awt.Color(24, 55, 68));
        tblPeserta.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        scrollPeserta.setViewportView(tblPeserta);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(9, 0, 8, 0);
        pnlDaftar.add(scrollPeserta, gridBagConstraints);

        lblTableHint.setForeground(new java.awt.Color(100, 122, 133));
        lblTableHint.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 8, 0);
        pnlDaftar.add(lblTableHint, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.57;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(12, 7, 12, 16);
        pnlContent.add(pnlDaftar, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlRoot.add(pnlContent, gridBagConstraints);

        pnlFooter.setBackground(new java.awt.Color(255, 255, 255));
        pnlFooter.setLayout(new java.awt.GridBagLayout());

        lblStatus.setForeground(new java.awt.Color(100, 122, 133));
        lblStatus.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        lblStatus.setText("Siap. Aktifkan MySQL Laragon untuk memuat data.");
        pnlFooter.add(lblStatus, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        pnlRoot.add(pnlFooter, gridBagConstraints);

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
    private javax.swing.ButtonGroup bgJenisKelamin;
    private javax.swing.JButton btnCari;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnMuat;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnUbah;
    private javax.swing.JLabel lblCariHint;
    private javax.swing.JLabel lblDaftarTitle;
    private javax.swing.JLabel lblEditorHint;
    private javax.swing.JLabel lblEditorTitle;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblJumlah;
    private javax.swing.JLabel lblKelamin;
    private javax.swing.JLabel lblNama;
    private javax.swing.JLabel lblNim;
    private javax.swing.JLabel lblNoHp;
    private javax.swing.JLabel lblProdi;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblSubTitle;
    private javax.swing.JLabel lblTableHint;
    private javax.swing.JLabel lblTerpilih;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pnlAksi;
    private javax.swing.JPanel pnlCari;
    private javax.swing.JPanel pnlContent;
    private javax.swing.JPanel pnlDaftar;
    private javax.swing.JPanel pnlEditor;
    private javax.swing.JPanel pnlFields;
    private javax.swing.JPanel pnlFillerEditor;
    private javax.swing.JPanel pnlFooter;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JPanel pnlKelamin;
    private javax.swing.JPanel pnlRoot;
    private javax.swing.JRadioButton rbLaki;
    private javax.swing.JRadioButton rbPerempuan;
    private javax.swing.JScrollPane scrollPeserta;
    private javax.swing.JTable tblPeserta;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtNim;
    private javax.swing.JTextField txtNoHp;
    private javax.swing.JTextField txtProdi;
    // End of variables declaration//GEN-END:variables

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
            // Pakai tampilan default Java bila tema sistem tidak tersedia.
        }
        SwingUtilities.invokeLater(() -> new PesertaFrame().setVisible(true));
    }
}
