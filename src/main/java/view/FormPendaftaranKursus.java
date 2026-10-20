package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * PBO Pertemuan 7 - Form Pendaftaran Kursus menggunakan JFrame Form NetBeans.
 * Pasangan file: FormPendaftaranKursus.form (GUI Builder / tab Design).
 * Tidak menggunakan database dan tidak mengubah form praktikum sebelumnya.
 */
public class FormPendaftaranKursus extends javax.swing.JFrame {

    public FormPendaftaranKursus() {
        initComponents();
        isiComboKursus();
        aturTampilan();
        txtRingkasan.setEditable(false);
        getRootPane().setDefaultButton(btnProses);
        setMinimumSize(new Dimension(850, 630));
        setSize(new Dimension(1110, 730));
        setLocationRelativeTo(null);
    }

    private void isiComboKursus() {
        cmbKursus.removeAllItems();
        cmbKursus.addItem("-- Pilih Kursus --");
        cmbKursus.addItem("Java Desktop");
        cmbKursus.addItem("Data Science");
        cmbKursus.addItem("Web Programming");
        cmbKursus.addItem("UI/UX Design");
    }

    /** Styling tambahan; struktur dan penempatan komponen tetap milik .form NetBeans. */
    private void aturTampilan() {
        pnlInput.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(219, 228, 227)));
        pnlHasil.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(219, 228, 227)));
        txtNama.setPreferredSize(new Dimension(260, 35));
        txtKodeAkses.setPreferredSize(new Dimension(260, 35));
        cmbKursus.setPreferredSize(new Dimension(260, 36));
        btnProses.setPreferredSize(new Dimension(220, 43));
        btnReset.setPreferredSize(new Dimension(112, 43));
        btnProses.setFocusPainted(false);
        btnReset.setFocusPainted(false);
        txtNama.setMargin(new Insets(6, 10, 6, 10));
        txtKodeAkses.setMargin(new Insets(6, 10, 6, 10));
        txtRingkasan.setMargin(new Insets(17, 14, 14, 14));
        txtRingkasan.setLineWrap(false);
        txtRingkasan.setText("");
        scrollRingkasan.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(219, 228, 227)));
    }

    private String getJenisPeserta() {
        if (rbMahasiswa.isSelected()) return "Mahasiswa";
        if (rbUmum.isSelected()) return "Umum";
        return "";
    }

    // Switch statement kompatibel Java 8 di Maven project sebelumnya.
    private double hitungBiayaDasar(String kursus) {
        switch (kursus) {
            case "Java Desktop": return 500_000;
            case "Data Science": return 650_000;
            case "Web Programming": return 450_000;
            case "UI/UX Design": return 400_000;
            default: return 0;
        }
    }

    private double hitungBiayaFasilitas() {
        double total = 0;
        if (cbModul.isSelected()) total += 50_000;
        if (cbSertifikat.isSelected()) total += 75_000;
        if (cbRekaman.isSelected()) total += 40_000;
        return total;
    }

    // Array + for loop eksplisit sesuai Sub-CPMK 7.
    private String buatRingkasanFasilitas() {
        String[] fasilitasDipilih = new String[3];
        int jumlah = 0;
        if (cbModul.isSelected()) fasilitasDipilih[jumlah++] = "Modul Cetak";
        if (cbSertifikat.isSelected()) fasilitasDipilih[jumlah++] = "Sertifikat";
        if (cbRekaman.isSelected()) fasilitasDipilih[jumlah++] = "Akses Rekaman";
        if (jumlah == 0) return "Tidak ada fasilitas tambahan\n";
        StringBuilder hasil = new StringBuilder();
        for (int i = 0; i < jumlah; i++) {
            hasil.append("- ").append(fasilitasDipilih[i]).append("\n");
        }
        return hasil.toString();
    }

    private boolean validasiInput() {
        String nama = txtNama.getText().trim();
        String kode = new String(txtKodeAkses.getPassword()); // DEMO saja, bukan login produksi
        if (nama.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama peserta wajib diisi.", "Validasi", JOptionPane.WARNING_MESSAGE);
            txtNama.requestFocus();
            return false;
        }
        if (!"PBO2026".equals(kode)) {
            JOptionPane.showMessageDialog(this, "Kode akses demo salah.", "Validasi", JOptionPane.WARNING_MESSAGE);
            txtKodeAkses.requestFocus();
            return false;
        }
        if (!rbMahasiswa.isSelected() && !rbUmum.isSelected()) {
            JOptionPane.showMessageDialog(this, "Pilih jenis peserta.", "Validasi", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (cmbKursus.getSelectedIndex() <= 0) {
            JOptionPane.showMessageDialog(this, "Pilih kursus terlebih dahulu.", "Validasi", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    private static String rupiah(double nilai) {
        return "Rp" + String.format(new Locale("id", "ID"), "%,.0f", nilai);
    }

    private void resetForm() {
        txtNama.setText("");
        txtKodeAkses.setText("");
        groupJenis.clearSelection();
        cmbKursus.setSelectedIndex(0);
        cbModul.setSelected(false);
        cbSertifikat.setSelected(false);
        cbRekaman.setSelected(false);
        txtRingkasan.setText("");
        txtNama.requestFocus();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        groupJenis = new javax.swing.ButtonGroup();
        pnlMain = new javax.swing.JPanel();
        pnlHeader = new javax.swing.JPanel();
        lblHeader = new javax.swing.JLabel();
        lblSubtitle = new javax.swing.JLabel();
        pnlBody = new javax.swing.JPanel();
        pnlInput = new javax.swing.JPanel();
        lblInputTitle = new javax.swing.JLabel();
        lblInputHint = new javax.swing.JLabel();
        lblNama = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        lblKode = new javax.swing.JLabel();
        txtKodeAkses = new javax.swing.JPasswordField();
        lblKodeHint = new javax.swing.JLabel();
        lblJenis = new javax.swing.JLabel();
        pnlJenis = new javax.swing.JPanel();
        rbMahasiswa = new javax.swing.JRadioButton();
        rbUmum = new javax.swing.JRadioButton();
        lblKursus = new javax.swing.JLabel();
        cmbKursus = new javax.swing.JComboBox<>();
        lblFasilitas = new javax.swing.JLabel();
        pnlFasilitas = new javax.swing.JPanel();
        cbModul = new javax.swing.JCheckBox();
        cbSertifikat = new javax.swing.JCheckBox();
        cbRekaman = new javax.swing.JCheckBox();
        pnlTombol = new javax.swing.JPanel();
        btnProses = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        lblOffline = new javax.swing.JLabel();
        pnlFiller = new javax.swing.JPanel();
        pnlHasil = new javax.swing.JPanel();
        lblHasilTitle = new javax.swing.JLabel();
        lblHasilHint = new javax.swing.JLabel();
        scrollRingkasan = new javax.swing.JScrollPane();
        txtRingkasan = new javax.swing.JTextArea();
        lblFormula = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SIKURSUS Desktop | Pendaftaran Kursus Offline - Pertemuan 7");
        getContentPane().setLayout(new java.awt.GridBagLayout());

        pnlMain.setBackground(new java.awt.Color(244, 247, 247));
        pnlMain.setLayout(new java.awt.GridBagLayout());

        pnlHeader.setBackground(new java.awt.Color(20, 52, 68));
        pnlHeader.setLayout(new java.awt.GridBagLayout());

        lblHeader.setText("SIKURSUS  ");
        lblHeader.setForeground(new java.awt.Color(255, 255, 255));
        lblHeader.setFont(new java.awt.Font("SansSerif", 1, 21)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(18, 25, 3, 25);
        pnlHeader.add(lblHeader, gridBagConstraints);

        lblSubtitle.setText("Aplikasi Kursus Offline  ");
        lblSubtitle.setForeground(new java.awt.Color(208, 228, 224));
        lblSubtitle.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 25, 19, 25);
        pnlHeader.add(lblSubtitle, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        pnlMain.add(pnlHeader, gridBagConstraints);

        pnlBody.setBackground(new java.awt.Color(244, 247, 247));
        pnlBody.setLayout(new java.awt.GridBagLayout());

        pnlInput.setBackground(new java.awt.Color(255, 255, 255));
        pnlInput.setLayout(new java.awt.GridBagLayout());

        lblInputTitle.setText("DATA PESERTA");
        lblInputTitle.setForeground(new java.awt.Color(28, 111, 88));
        lblInputTitle.setFont(new java.awt.Font("SansSerif", 1, 17)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(24, 25, 1, 25);
        pnlInput.add(lblInputTitle, gridBagConstraints);

        lblInputHint.setText("Lengkapi data untuk menghitung biaya kursus.");
        lblInputHint.setForeground(new java.awt.Color(105, 123, 130));
        lblInputHint.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 25, 15, 25);
        pnlInput.add(lblInputHint, gridBagConstraints);

        lblNama.setText("Nama Peserta *");
        lblNama.setForeground(new java.awt.Color(36, 55, 64));
        lblNama.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 25, 5, 25);
        pnlInput.add(lblNama, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 25, 10, 25);
        pnlInput.add(txtNama, gridBagConstraints);

        lblKode.setText("Kode Akses Demo *");
        lblKode.setForeground(new java.awt.Color(36, 55, 64));
        lblKode.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 25, 5, 25);
        pnlInput.add(lblKode, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 25, 5, 25);
        pnlInput.add(txtKodeAkses, gridBagConstraints);

        lblKodeHint.setText("Kode praktikum: PBO2026");
        lblKodeHint.setForeground(new java.awt.Color(105, 123, 130));
        lblKodeHint.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 25, 13, 25);
        pnlInput.add(lblKodeHint, gridBagConstraints);

        lblJenis.setText("Jenis Peserta *");
        lblJenis.setForeground(new java.awt.Color(36, 55, 64));
        lblJenis.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 25, 6, 25);
        pnlInput.add(lblJenis, gridBagConstraints);

        pnlJenis.setBackground(new java.awt.Color(255, 255, 255));
        pnlJenis.setLayout(new java.awt.GridBagLayout());

        rbMahasiswa.setBackground(new java.awt.Color(255, 255, 255));
        groupJenis.add(rbMahasiswa);
        rbMahasiswa.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        rbMahasiswa.setForeground(new java.awt.Color(36, 55, 64));
        rbMahasiswa.setText("Mahasiswa (diskon 10%)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        pnlJenis.add(rbMahasiswa, gridBagConstraints);

        rbUmum.setBackground(new java.awt.Color(255, 255, 255));
        groupJenis.add(rbUmum);
        rbUmum.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        rbUmum.setForeground(new java.awt.Color(36, 55, 64));
        rbUmum.setText("Umum");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        pnlJenis.add(rbUmum, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 25, 12, 25);
        pnlInput.add(pnlJenis, gridBagConstraints);

        lblKursus.setText("Kursus *");
        lblKursus.setForeground(new java.awt.Color(36, 55, 64));
        lblKursus.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 25, 5, 25);
        pnlInput.add(lblKursus, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 25, 15, 25);
        pnlInput.add(cmbKursus, gridBagConstraints);

        lblFasilitas.setText("Fasilitas Tambahan (opsional)");
        lblFasilitas.setForeground(new java.awt.Color(36, 55, 64));
        lblFasilitas.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 25, 6, 25);
        pnlInput.add(lblFasilitas, gridBagConstraints);

        pnlFasilitas.setBackground(new java.awt.Color(255, 255, 255));
        pnlFasilitas.setLayout(new java.awt.GridBagLayout());

        cbModul.setText("Modul Cetak  (+Rp50.000)");
        cbModul.setBackground(new java.awt.Color(255, 255, 255));
        cbModul.setForeground(new java.awt.Color(36, 55, 64));
        cbModul.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        pnlFasilitas.add(cbModul, gridBagConstraints);

        cbSertifikat.setText("Sertifikat  (+Rp75.000)");
        cbSertifikat.setBackground(new java.awt.Color(255, 255, 255));
        cbSertifikat.setForeground(new java.awt.Color(36, 55, 64));
        cbSertifikat.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        pnlFasilitas.add(cbSertifikat, gridBagConstraints);

        cbRekaman.setText("Akses Rekaman  (+Rp40.000)");
        cbRekaman.setBackground(new java.awt.Color(255, 255, 255));
        cbRekaman.setForeground(new java.awt.Color(36, 55, 64));
        cbRekaman.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        pnlFasilitas.add(cbRekaman, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 25, 16, 25);
        pnlInput.add(pnlFasilitas, gridBagConstraints);

        pnlTombol.setBackground(new java.awt.Color(255, 255, 255));
        pnlTombol.setLayout(new java.awt.GridBagLayout());

        btnProses.setText("PROSES PENDAFTARAN");
        btnProses.setBackground(new java.awt.Color(28, 111, 88));
        btnProses.setForeground(new java.awt.Color(255, 255, 255));
        btnProses.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnProses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProsesActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        pnlTombol.add(btnProses, gridBagConstraints);

        btnReset.setText("RESET");
        btnReset.setBackground(new java.awt.Color(238, 241, 241));
        btnReset.setForeground(new java.awt.Color(20, 52, 68));
        btnReset.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.45;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        pnlTombol.add(btnReset, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 25, 13, 25);
        pnlInput.add(pnlTombol, gridBagConstraints);

        lblOffline.setText("* Wajib diisi   ");
        lblOffline.setForeground(new java.awt.Color(105, 123, 130));
        lblOffline.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 25, 14, 25);
        pnlInput.add(lblOffline, gridBagConstraints);

        pnlFiller.setBackground(new java.awt.Color(255, 255, 255));
        pnlFiller.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 15;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 25, 0, 25);
        pnlInput.add(pnlFiller, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.55;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(15, 16, 16, 8);
        pnlBody.add(pnlInput, gridBagConstraints);

        pnlHasil.setBackground(new java.awt.Color(255, 255, 255));
        pnlHasil.setLayout(new java.awt.GridBagLayout());

        lblHasilTitle.setText("RINGKASAN PENDAFTARAN");
        lblHasilTitle.setForeground(new java.awt.Color(28, 111, 88));
        lblHasilTitle.setFont(new java.awt.Font("SansSerif", 1, 17)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(24, 23, 2, 23);
        pnlHasil.add(lblHasilTitle, gridBagConstraints);

        lblHasilHint.setForeground(new java.awt.Color(105, 123, 130));
        lblHasilHint.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 23, 15, 23);
        pnlHasil.add(lblHasilHint, gridBagConstraints);

        scrollRingkasan.setBackground(new java.awt.Color(248, 251, 250));

        txtRingkasan.setEditable(false);
        txtRingkasan.setRows(19);
        txtRingkasan.setColumns(35);
        txtRingkasan.setBackground(new java.awt.Color(248, 251, 250));
        txtRingkasan.setForeground(new java.awt.Color(20, 52, 68));
        txtRingkasan.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        scrollRingkasan.setViewportView(txtRingkasan);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 23, 12, 23);
        pnlHasil.add(scrollRingkasan, gridBagConstraints);

        lblFormula.setForeground(new java.awt.Color(105, 123, 130));
        lblFormula.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 23, 20, 23);
        pnlHasil.add(lblFormula, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.45;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(15, 8, 16, 16);
        pnlBody.add(pnlHasil, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlMain.add(pnlBody, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(pnlMain, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnProsesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProsesActionPerformed
        if (!validasiInput()) return;
        String nama = txtNama.getText().trim();
        String jenis = getJenisPeserta();
        String kursus = (String) cmbKursus.getSelectedItem();
        double biayaDasar = hitungBiayaDasar(kursus);
        double diskon = "Mahasiswa".equals(jenis) ? biayaDasar * 0.10 : 0;
        double biayaFasilitas = hitungBiayaFasilitas();
        double total = biayaDasar - diskon + biayaFasilitas;

        String ringkasan = "PENDAFTARAN KURSUS\n"
                + "==================================\n"
                + "Nama          : " + nama + "\n"
                + "Jenis Peserta : " + jenis + "\n"
                + "Kursus        : " + kursus + "\n"
                + "----------------------------------\n"
                + "Biaya Dasar   : " + rupiah(biayaDasar) + "\n"
                + "Diskon        : " + rupiah(diskon) + "\n"
                + "\nFasilitas:\n" + buatRingkasanFasilitas()
                + "Biaya Fasilitas: " + rupiah(biayaFasilitas) + "\n"
                + "==================================\n"
                + "TOTAL         : " + rupiah(total) + "\n";
        txtRingkasan.setText(ringkasan);
        txtRingkasan.setCaretPosition(0);
        JOptionPane.showMessageDialog(this, "Pendaftaran berhasil diproses.",
                "Berhasil", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnProsesActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        resetForm();
    }//GEN-LAST:event_btnResetActionPerformed

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo lf : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(lf.getName())) {
                    UIManager.setLookAndFeel(lf.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            // Lanjut dengan tampilan standar jika Nimbus tidak tersedia.
        }
        SwingUtilities.invokeLater(new Runnable() {
            public void run() { new FormPendaftaranKursus().setVisible(true); }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnProses;
    private javax.swing.JButton btnReset;
    private javax.swing.JCheckBox cbModul;
    private javax.swing.JCheckBox cbRekaman;
    private javax.swing.JCheckBox cbSertifikat;
    private javax.swing.JComboBox<String> cmbKursus;
    private javax.swing.ButtonGroup groupJenis;
    private javax.swing.JLabel lblFasilitas;
    private javax.swing.JLabel lblFormula;
    private javax.swing.JLabel lblHasilHint;
    private javax.swing.JLabel lblHasilTitle;
    private javax.swing.JLabel lblHeader;
    private javax.swing.JLabel lblInputHint;
    private javax.swing.JLabel lblInputTitle;
    private javax.swing.JLabel lblJenis;
    private javax.swing.JLabel lblKode;
    private javax.swing.JLabel lblKodeHint;
    private javax.swing.JLabel lblKursus;
    private javax.swing.JLabel lblNama;
    private javax.swing.JLabel lblOffline;
    private javax.swing.JLabel lblSubtitle;
    private javax.swing.JPanel pnlBody;
    private javax.swing.JPanel pnlFasilitas;
    private javax.swing.JPanel pnlFiller;
    private javax.swing.JPanel pnlHasil;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JPanel pnlInput;
    private javax.swing.JPanel pnlJenis;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlTombol;
    private javax.swing.JRadioButton rbMahasiswa;
    private javax.swing.JRadioButton rbUmum;
    private javax.swing.JScrollPane scrollRingkasan;
    private javax.swing.JPasswordField txtKodeAkses;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextArea txtRingkasan;
    // End of variables declaration//GEN-END:variables
}
