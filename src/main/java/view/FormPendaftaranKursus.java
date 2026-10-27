package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import dao.PesertaDAO;
import model.Peserta;
import java.sql.SQLException;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * SIKURSUS - Form Pendaftaran Kursus terintegrasi (Swing, JDBC, MySQL Laragon).
 * Pasangan file: FormPendaftaranKursus.form (GUI Builder / tab Design).
 * Satu JFrame Form NetBeans: hitung biaya kursus dan simpan peserta melalui DAO.
 */
public class FormPendaftaranKursus extends javax.swing.JFrame {

    public FormPendaftaranKursus() {
        initComponents();
        isiComboKursus();
        aturTampilan();
        setMinimumSize(new Dimension(1040, 650));
        setSize(new Dimension(1180, 705));
        setLocationRelativeTo(null);
        getRootPane().setDefaultButton(btnProses);
    }

    private void isiComboKursus() {
        cmbKursus.removeAllItems();
        cmbKursus.addItem("-- Pilih Kursus --");
        cmbKursus.addItem("Java Desktop");
        cmbKursus.addItem("Data Science");
        cmbKursus.addItem("Web Programming");
        cmbKursus.addItem("UI/UX Design");
    }

    /** Layout disusun di NetBeans GUI Builder. Properti tampilan tambahan nonstruktural. */
    private void aturTampilan() {
        pnlInput.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(220, 230, 228)));
        pnlHasil.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(220, 230, 228)));
        scrollRingkasan.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(220, 230, 228)));
        txtRingkasan.setMargin(new Insets(14, 12, 12, 12));
        txtRingkasan.setLineWrap(false);
        txtRingkasan.setText("");
        for (javax.swing.JTextField field : new javax.swing.JTextField[] {
                txtNim, txtNama, txtProdi, txtNoHp, txtEmail }) {
            field.setPreferredSize(new Dimension(245, 32));
            field.setMargin(new Insets(5, 9, 5, 9));
        }
        txtKodeAkses.setPreferredSize(new Dimension(245, 32));
        txtKodeAkses.setMargin(new Insets(5, 9, 5, 9));
        cmbKursus.setPreferredSize(new Dimension(245, 32));
        btnProses.setPreferredSize(new Dimension(140, 37));
        btnSimpan.setPreferredSize(new Dimension(180, 37));
        btnReset.setPreferredSize(new Dimension(87, 37));
        btnProses.setFocusPainted(false);
        btnSimpan.setFocusPainted(false);
        btnReset.setFocusPainted(false);
        lblDbStatus.setText("Status penyimpanan: belum ada data disimpan.");
        pnlKelamin.setOpaque(false);
        pnlJenis.setOpaque(false);
        rbLaki.setOpaque(false);
        rbPerempuan.setOpaque(false);
        rbMahasiswa.setOpaque(false);
        rbUmum.setOpaque(false);
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
        txtNim.setText("");
        txtProdi.setText("");
        txtNoHp.setText("");
        txtEmail.setText("");
        groupKelamin.clearSelection();
        lblDbStatus.setText("Status penyimpanan: belum ada data disimpan.");
        txtNama.requestFocus();
    }

    /** Praktikum 9 menyimpan baris ke tabel peserta, BUKAN tabel pendaftaran. */
    private boolean validasiPesertaDatabase() {
        if (!validasiInput()) return false;
        if (txtNim.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "NIM wajib diisi.", "Validasi", JOptionPane.WARNING_MESSAGE);
            txtNim.requestFocus();
            return false;
        }
        if (!rbLaki.isSelected() && !rbPerempuan.isSelected()) {
            JOptionPane.showMessageDialog(this, "Pilih jenis kelamin.", "Validasi", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (txtProdi.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Program studi wajib diisi.", "Validasi", JOptionPane.WARNING_MESSAGE);
            txtProdi.requestFocus();
            return false;
        }
        return true;
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        groupJenis = new javax.swing.ButtonGroup();
        groupKelamin = new javax.swing.ButtonGroup();
        pnlMain = new javax.swing.JPanel();
        pnlHeader = new javax.swing.JPanel();
        lblHeader = new javax.swing.JLabel();
        lblSubtitle = new javax.swing.JLabel();
        pnlBody = new javax.swing.JPanel();
        pnlInput = new javax.swing.JPanel();
        lblInputTitle = new javax.swing.JLabel();
        lblInputHint = new javax.swing.JLabel();
        pnlForm = new javax.swing.JPanel();
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
        lblJenis = new javax.swing.JLabel();
        lblKode = new javax.swing.JLabel();
        pnlJenis = new javax.swing.JPanel();
        rbMahasiswa = new javax.swing.JRadioButton();
        rbUmum = new javax.swing.JRadioButton();
        txtKodeAkses = new javax.swing.JPasswordField();
        lblKursus = new javax.swing.JLabel();
        lblFasilitas = new javax.swing.JLabel();
        cmbKursus = new javax.swing.JComboBox();
        pnlFasilitas = new javax.swing.JPanel();
        cbModul = new javax.swing.JCheckBox();
        cbSertifikat = new javax.swing.JCheckBox();
        cbRekaman = new javax.swing.JCheckBox();
        lblKodeHint = new javax.swing.JLabel();
        pnlTombol = new javax.swing.JPanel();
        btnProses = new javax.swing.JButton();
        btnSimpan = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        lblOffline = new javax.swing.JLabel();
        pnlFiller = new javax.swing.JPanel();
        pnlHasil = new javax.swing.JPanel();
        lblHasilTitle = new javax.swing.JLabel();
        lblHasilHint = new javax.swing.JLabel();
        scrollRingkasan = new javax.swing.JScrollPane();
        txtRingkasan = new javax.swing.JTextArea();
        lblDbStatus = new javax.swing.JLabel();
        lblFormula = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SIKURSUS Desktop | Pendaftaran Kursus");
        getContentPane().setLayout(new java.awt.GridBagLayout());

        pnlMain.setBackground(new java.awt.Color(242, 247, 246));
        pnlMain.setLayout(new java.awt.GridBagLayout());

        pnlHeader.setBackground(new java.awt.Color(22, 54, 69));
        pnlHeader.setLayout(new java.awt.GridBagLayout());

        lblHeader.setText("FORM PENDAFTARAN KURSUS");
        lblHeader.setForeground(new java.awt.Color(255, 255, 255));
        lblHeader.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(15, 24, 2, 24);
        pnlHeader.add(lblHeader, gridBagConstraints);

        lblSubtitle.setText("Pendaftaran kursus, perhitungan biaya, dan penyimpanan peserta");
        lblSubtitle.setForeground(new java.awt.Color(211, 229, 226));
        lblSubtitle.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 24, 15, 24);
        pnlHeader.add(lblSubtitle, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        pnlMain.add(pnlHeader, gridBagConstraints);

        pnlBody.setBackground(new java.awt.Color(242, 247, 246));
        pnlBody.setLayout(new java.awt.GridBagLayout());

        pnlInput.setBackground(new java.awt.Color(255, 255, 255));
        pnlInput.setLayout(new java.awt.GridBagLayout());

        lblInputTitle.setText("INFORMASI PENDAFTARAN");
        lblInputTitle.setForeground(new java.awt.Color(23, 116, 91));
        lblInputTitle.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(17, 21, 1, 21);
        pnlInput.add(lblInputTitle, gridBagConstraints);

        lblInputHint.setForeground(new java.awt.Color(102, 121, 132));
        lblInputHint.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 21, 10, 21);
        pnlInput.add(lblInputHint, gridBagConstraints);

        pnlForm.setBackground(new java.awt.Color(255, 255, 255));
        pnlForm.setLayout(new java.awt.GridBagLayout());

        lblNim.setText("NIM *");
        lblNim.setForeground(new java.awt.Color(22, 54, 69));
        lblNim.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 7, 3, 9);
        pnlForm.add(lblNim, gridBagConstraints);

        lblNama.setText("Nama Peserta *");
        lblNama.setForeground(new java.awt.Color(22, 54, 69));
        lblNama.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 9, 3, 7);
        pnlForm.add(lblNama, gridBagConstraints);

        txtNim.setForeground(new java.awt.Color(22, 54, 69));
        txtNim.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 7, 9, 9);
        pnlForm.add(txtNim, gridBagConstraints);

        txtNama.setForeground(new java.awt.Color(22, 54, 69));
        txtNama.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 9, 9, 7);
        pnlForm.add(txtNama, gridBagConstraints);

        lblKelamin.setText("Jenis Kelamin *");
        lblKelamin.setForeground(new java.awt.Color(22, 54, 69));
        lblKelamin.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 7, 3, 9);
        pnlForm.add(lblKelamin, gridBagConstraints);

        lblProdi.setText("Program Studi *");
        lblProdi.setForeground(new java.awt.Color(22, 54, 69));
        lblProdi.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 9, 3, 7);
        pnlForm.add(lblProdi, gridBagConstraints);

        pnlKelamin.setBackground(new java.awt.Color(255, 255, 255));
        pnlKelamin.setLayout(new java.awt.GridBagLayout());

        rbLaki.setBackground(new java.awt.Color(255, 255, 255));
        groupKelamin.add(rbLaki);
        rbLaki.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        rbLaki.setText("Laki-laki");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 12);
        pnlKelamin.add(rbLaki, gridBagConstraints);

        rbPerempuan.setBackground(new java.awt.Color(255, 255, 255));
        groupKelamin.add(rbPerempuan);
        rbPerempuan.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        rbPerempuan.setText("Perempuan");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        pnlKelamin.add(rbPerempuan, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 7, 9, 9);
        pnlForm.add(pnlKelamin, gridBagConstraints);

        txtProdi.setForeground(new java.awt.Color(22, 54, 69));
        txtProdi.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 9, 9, 7);
        pnlForm.add(txtProdi, gridBagConstraints);

        lblNoHp.setText("Nomor HP (opsional)");
        lblNoHp.setForeground(new java.awt.Color(22, 54, 69));
        lblNoHp.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 7, 3, 9);
        pnlForm.add(lblNoHp, gridBagConstraints);

        lblEmail.setText("Email (opsional)");
        lblEmail.setForeground(new java.awt.Color(22, 54, 69));
        lblEmail.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 9, 3, 7);
        pnlForm.add(lblEmail, gridBagConstraints);

        txtNoHp.setForeground(new java.awt.Color(22, 54, 69));
        txtNoHp.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 7, 9, 9);
        pnlForm.add(txtNoHp, gridBagConstraints);

        txtEmail.setForeground(new java.awt.Color(22, 54, 69));
        txtEmail.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 9, 9, 7);
        pnlForm.add(txtEmail, gridBagConstraints);

        lblJenis.setText("Jenis Peserta *");
        lblJenis.setForeground(new java.awt.Color(22, 54, 69));
        lblJenis.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 7, 3, 9);
        pnlForm.add(lblJenis, gridBagConstraints);

        lblKode.setText("Kode Akses Demo *");
        lblKode.setForeground(new java.awt.Color(22, 54, 69));
        lblKode.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 9, 3, 7);
        pnlForm.add(lblKode, gridBagConstraints);

        pnlJenis.setBackground(new java.awt.Color(255, 255, 255));
        pnlJenis.setLayout(new java.awt.GridBagLayout());

        rbMahasiswa.setBackground(new java.awt.Color(255, 255, 255));
        groupJenis.add(rbMahasiswa);
        rbMahasiswa.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        rbMahasiswa.setText("Mahasiswa (10%)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 7);
        pnlJenis.add(rbMahasiswa, gridBagConstraints);

        rbUmum.setBackground(new java.awt.Color(255, 255, 255));
        groupJenis.add(rbUmum);
        rbUmum.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        rbUmum.setText("Umum");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        pnlJenis.add(rbUmum, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 7, 9, 9);
        pnlForm.add(pnlJenis, gridBagConstraints);

        txtKodeAkses.setForeground(new java.awt.Color(22, 54, 69));
        txtKodeAkses.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 9, 9, 7);
        pnlForm.add(txtKodeAkses, gridBagConstraints);

        lblKursus.setText("Kursus *");
        lblKursus.setForeground(new java.awt.Color(22, 54, 69));
        lblKursus.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 7, 3, 9);
        pnlForm.add(lblKursus, gridBagConstraints);

        lblFasilitas.setText("Fasilitas (opsional)");
        lblFasilitas.setForeground(new java.awt.Color(22, 54, 69));
        lblFasilitas.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 9, 3, 7);
        pnlForm.add(lblFasilitas, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 7, 9, 9);
        pnlForm.add(cmbKursus, gridBagConstraints);

        pnlFasilitas.setBackground(new java.awt.Color(255, 255, 255));
        pnlFasilitas.setLayout(new java.awt.GridBagLayout());

        cbModul.setBackground(new java.awt.Color(255, 255, 255));
        cbModul.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cbModul.setText("Modul cetak (+Rp50.000)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        pnlFasilitas.add(cbModul, gridBagConstraints);

        cbSertifikat.setText("Sertifikat (+Rp75.000)");
        cbSertifikat.setBackground(new java.awt.Color(255, 255, 255));
        cbSertifikat.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        pnlFasilitas.add(cbSertifikat, gridBagConstraints);

        cbRekaman.setText("Akses rekaman (+Rp40.000)");
        cbRekaman.setBackground(new java.awt.Color(255, 255, 255));
        cbRekaman.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        pnlFasilitas.add(cbRekaman, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 9, 9, 7);
        pnlForm.add(pnlFasilitas, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 14, 5, 14);
        pnlInput.add(pnlForm, gridBagConstraints);

        lblKodeHint.setText("Kode demonstrasi: PBO2026");
        lblKodeHint.setForeground(new java.awt.Color(102, 121, 132));
        lblKodeHint.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 21, 8, 21);
        pnlInput.add(lblKodeHint, gridBagConstraints);

        pnlTombol.setBackground(new java.awt.Color(255, 255, 255));
        pnlTombol.setLayout(new java.awt.GridBagLayout());

        btnProses.setText("HITUNG BIAYA");
        btnProses.setBackground(new java.awt.Color(22, 54, 69));
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
        gridBagConstraints.weightx = 0.31;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        pnlTombol.add(btnProses, gridBagConstraints);

        btnSimpan.setText("SIMPAN PESERTA");
        btnSimpan.setBackground(new java.awt.Color(23, 116, 91));
        btnSimpan.setForeground(new java.awt.Color(255, 255, 255));
        btnSimpan.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.45;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        pnlTombol.add(btnSimpan, gridBagConstraints);

        btnReset.setText("RESET");
        btnReset.setBackground(new java.awt.Color(235, 241, 240));
        btnReset.setForeground(new java.awt.Color(22, 54, 69));
        btnReset.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.24;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        pnlTombol.add(btnReset, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 21, 6, 21);
        pnlInput.add(pnlTombol, gridBagConstraints);

        lblOffline.setForeground(new java.awt.Color(102, 121, 132));
        lblOffline.setFont(new java.awt.Font("SansSerif", 0, 10)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 21, 0, 21);
        pnlInput.add(lblOffline, gridBagConstraints);

        pnlFiller.setBackground(new java.awt.Color(255, 255, 255));
        pnlFiller.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlInput.add(pnlFiller, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.64;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(12, 14, 14, 7);
        pnlBody.add(pnlInput, gridBagConstraints);

        pnlHasil.setBackground(new java.awt.Color(255, 255, 255));
        pnlHasil.setLayout(new java.awt.GridBagLayout());

        lblHasilTitle.setText("RINGKASAN PENDAFTARAN");
        lblHasilTitle.setForeground(new java.awt.Color(23, 116, 91));
        lblHasilTitle.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(17, 19, 2, 19);
        pnlHasil.add(lblHasilTitle, gridBagConstraints);

        lblHasilHint.setText("Rincian biaya dan status penyimpanan");
        lblHasilHint.setForeground(new java.awt.Color(102, 121, 132));
        lblHasilHint.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 19, 12, 19);
        pnlHasil.add(lblHasilHint, gridBagConstraints);

        txtRingkasan.setEditable(false);
        txtRingkasan.setRows(16);
        txtRingkasan.setColumns(30);
        txtRingkasan.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        txtRingkasan.setBackground(new java.awt.Color(248, 251, 250));
        txtRingkasan.setForeground(new java.awt.Color(22, 54, 69));
        scrollRingkasan.setViewportView(txtRingkasan);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 19, 9, 19);
        pnlHasil.add(scrollRingkasan, gridBagConstraints);

        lblDbStatus.setForeground(new java.awt.Color(102, 121, 132));
        lblDbStatus.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 19, 4, 19);
        pnlHasil.add(lblDbStatus, gridBagConstraints);

        lblFormula.setForeground(new java.awt.Color(102, 121, 132));
        lblFormula.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 19, 15, 19);
        pnlHasil.add(lblFormula, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.36;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(12, 7, 14, 14);
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

    private void buatRingkasanPendaftaran() {
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
    }

    private void btnProsesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProsesActionPerformed
        if (!validasiInput()) return;
        buatRingkasanPendaftaran();
        JOptionPane.showMessageDialog(this, "Pendaftaran berhasil diproses.",
                "Berhasil", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnProsesActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        resetForm();
    }//GEN-LAST:event_btnResetActionPerformed


    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        if (!validasiPesertaDatabase()) return;
        buatRingkasanPendaftaran();
        try {
            Peserta p = new Peserta(
                    txtNim.getText().trim(), txtNama.getText().trim(),
                    rbLaki.isSelected() ? "L" : "P",
                    txtProdi.getText().trim(), txtNoHp.getText().trim(),
                    txtEmail.getText().trim());
            boolean tersimpan = new PesertaDAO().insert(p);
            if (tersimpan) {
                lblDbStatus.setText("TERSIMPAN  |  ID peserta: " + p.getIdPeserta()
                        + " | NIM: " + p.getNim());
                JOptionPane.showMessageDialog(this,
                        "Data peserta berhasil disimpan ke db_sikursus.peserta.\n"
                        + "ID: " + p.getIdPeserta() + " | NIM: " + p.getNim()
                        + "\nCatatan: data peserta tersimpan",
                        "Berhasil", JOptionPane.INFORMATION_MESSAGE);
            } else {
                lblDbStatus.setText("Data belum berhasil disimpan.");
                JOptionPane.showMessageDialog(this, "Data peserta tidak tersimpan.",
                        "Informasi", JOptionPane.WARNING_MESSAGE);
            }
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(),
                    "Validasi Peserta", JOptionPane.WARNING_MESSAGE);
        } catch (SQLException e) {
            lblDbStatus.setText("GAGAL MENYIMPAN - Periksa Laragon / data.");
            String info;
            if (e.getErrorCode() == 1062) {
                info = "NIM sudah terdaftar. Gunakan NIM yang berbeda.";
            } else if (e.getSQLState() != null && e.getSQLState().startsWith("08")) {
                info = "Gagal terhubung ke MySQL. Periksa Laragon dan port database.";
            } else {
                info = "Gagal menyimpan data: " + e.getMessage();
            }
            JOptionPane.showMessageDialog(this, info,
                    "Database Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

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
    private javax.swing.JButton btnSimpan;
    private javax.swing.JCheckBox cbModul;
    private javax.swing.JCheckBox cbRekaman;
    private javax.swing.JCheckBox cbSertifikat;
    private javax.swing.JComboBox cmbKursus;
    private javax.swing.ButtonGroup groupJenis;
    private javax.swing.ButtonGroup groupKelamin;
    private javax.swing.JLabel lblDbStatus;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblFasilitas;
    private javax.swing.JLabel lblFormula;
    private javax.swing.JLabel lblHasilHint;
    private javax.swing.JLabel lblHasilTitle;
    private javax.swing.JLabel lblHeader;
    private javax.swing.JLabel lblInputHint;
    private javax.swing.JLabel lblInputTitle;
    private javax.swing.JLabel lblJenis;
    private javax.swing.JLabel lblKelamin;
    private javax.swing.JLabel lblKode;
    private javax.swing.JLabel lblKodeHint;
    private javax.swing.JLabel lblKursus;
    private javax.swing.JLabel lblNama;
    private javax.swing.JLabel lblNim;
    private javax.swing.JLabel lblNoHp;
    private javax.swing.JLabel lblOffline;
    private javax.swing.JLabel lblProdi;
    private javax.swing.JLabel lblSubtitle;
    private javax.swing.JPanel pnlBody;
    private javax.swing.JPanel pnlFasilitas;
    private javax.swing.JPanel pnlFiller;
    private javax.swing.JPanel pnlForm;
    private javax.swing.JPanel pnlHasil;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JPanel pnlInput;
    private javax.swing.JPanel pnlJenis;
    private javax.swing.JPanel pnlKelamin;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlTombol;
    private javax.swing.JRadioButton rbLaki;
    private javax.swing.JRadioButton rbMahasiswa;
    private javax.swing.JRadioButton rbPerempuan;
    private javax.swing.JRadioButton rbUmum;
    private javax.swing.JScrollPane scrollRingkasan;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JPasswordField txtKodeAkses;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtNim;
    private javax.swing.JTextField txtNoHp;
    private javax.swing.JTextField txtProdi;
    private javax.swing.JTextArea txtRingkasan;
    // End of variables declaration//GEN-END:variables
}
