package model;

/** Model kursus pertemuan 4, diperbaiki sesuai latihan enkapsulasi pertemuan 6. */
public class Kursus {
    private String kode;
    private String nama;
    private String level;
    private double biaya;

    public Kursus() {
        // Constructor tanpa parameter dipertahankan untuk latihan pertemuan 6.
    }

    public Kursus(String kode, String nama, String level, double biaya) {
        setKode(kode);
        setNama(nama);
        setLevel(level);
        setBiaya(biaya);
    }

    public String getKode() { return kode; }
    public String getNama() { return nama; }
    public String getLevel() { return level; }
    public double getBiaya() { return biaya; }

    public void setKode(String kode) {
        if (kode == null || kode.trim().isEmpty())
            throw new IllegalArgumentException("Kode kursus wajib diisi");
        this.kode = kode.trim();
    }

    public void setNama(String nama) {
        if (nama == null || nama.trim().isEmpty())
            throw new IllegalArgumentException("Nama kursus wajib diisi");
        this.nama = nama.trim();
    }

    public void setLevel(String level) {
        if (level == null || level.trim().isEmpty())
            throw new IllegalArgumentException("Level kursus wajib diisi");
        this.level = level.trim();
    }

    public void setBiaya(double biaya) {
        if (Double.isNaN(biaya) || Double.isInfinite(biaya) || biaya < 0)
            throw new IllegalArgumentException("Biaya tidak boleh negatif atau tidak valid");
        this.biaya = biaya;
    }

    /** Method dari pertemuan 4 tetap tersedia untuk GUI dan DemoKursus lama. */
    public double hitungBiayaSetelahDiskon(double persenDiskon) {
        if (Double.isNaN(persenDiskon) || Double.isInfinite(persenDiskon)
                || persenDiskon < 0 || persenDiskon > 100)
            throw new IllegalArgumentException("Diskon harus 0 sampai 100 persen");
        return biaya - (biaya * persenDiskon / 100.0);
    }
}
