package model;

import java.time.LocalDate;

/** Model kelas pelatihan yang menghubungkan kursus dan instruktur. */
public class Kelas {
    private int idKelas;
    private int idKursus;
    private int idInstruktur;
    private String namaKelas;
    private LocalDate tanggalMulai;
    private int kapasitas;
    private String namaKursus = "";
    private String namaInstruktur = "";

    public Kelas(int idKelas, int idKursus, int idInstruktur, String namaKelas,
            LocalDate tanggalMulai, int kapasitas) {
        setIdKelas(idKelas);
        setIdKursus(idKursus);
        setIdInstruktur(idInstruktur);
        setNamaKelas(namaKelas);
        setTanggalMulai(tanggalMulai);
        setKapasitas(kapasitas);
    }

    public int getIdKelas() { return idKelas; }
    public int getIdKursus() { return idKursus; }
    public int getIdInstruktur() { return idInstruktur; }
    public String getNamaKelas() { return namaKelas; }
    public LocalDate getTanggalMulai() { return tanggalMulai; }
    public int getKapasitas() { return kapasitas; }
    public String getNamaKursus() { return namaKursus; }
    public String getNamaInstruktur() { return namaInstruktur; }

    public void setIdKelas(int idKelas) {
        if (idKelas < 0) throw new IllegalArgumentException("ID kelas tidak valid");
        this.idKelas = idKelas;
    }
    public void setIdKursus(int idKursus) {
        if (idKursus <= 0) throw new IllegalArgumentException("Pilih kursus yang valid");
        this.idKursus = idKursus;
    }
    public void setIdInstruktur(int idInstruktur) {
        if (idInstruktur <= 0) throw new IllegalArgumentException("Pilih instruktur yang valid");
        this.idInstruktur = idInstruktur;
    }
    public void setNamaKelas(String namaKelas) {
        if (namaKelas == null || namaKelas.trim().isEmpty())
            throw new IllegalArgumentException("Nama kelas wajib diisi");
        if (namaKelas.trim().length() > 100)
            throw new IllegalArgumentException("Nama kelas maksimal 100 karakter");
        this.namaKelas = namaKelas.trim();
    }
    public void setTanggalMulai(LocalDate tanggalMulai) {
        if (tanggalMulai == null) throw new IllegalArgumentException("Tanggal mulai wajib diisi");
        this.tanggalMulai = tanggalMulai;
    }
    public void setKapasitas(int kapasitas) {
        if (kapasitas < 1 || kapasitas > 1000)
            throw new IllegalArgumentException("Kapasitas harus antara 1 sampai 1000 peserta");
        this.kapasitas = kapasitas;
    }
    public void setNamaKursus(String namaKursus) {
        this.namaKursus = namaKursus == null ? "" : namaKursus;
    }
    public void setNamaInstruktur(String namaInstruktur) {
        this.namaInstruktur = namaInstruktur == null ? "" : namaInstruktur;
    }
}
