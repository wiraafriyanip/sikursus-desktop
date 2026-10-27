package model;

/**
 * Pertemuan 9: model Peserta untuk MySQL, tetap extends Orang (P6).
 * Constructor lama tetap ada agar praktikum 5/6 tidak rusak.
 */
public class Peserta extends Orang {
    private String nim;
    private String prodi;
    private String jenisKelamin = ""; // L atau P ketika didaftarkan melalui form P9
    private String email = "";

    // Constructor lama Praktikum 5-6: JANGAN DIHAPUS.
    public Peserta(int id, String nama, String noHp, String nim, String prodi) {
        super(id, nama, noHp);
        setNim(nim);
        setProdi(prodi);
    }

    // Constructor baru Praktikum 9: id dari MySQL AUTO_INCREMENT.
    public Peserta(String nim, String nama, String jenisKelamin,
                   String prodi, String noHp, String email) {
        super(0, nama, noHp);
        setNim(nim);
        setProdi(prodi);
        setJenisKelamin(jenisKelamin);
        setEmail(email);
    }

    // Field id milik parent Orang dipakai juga sebagai id_peserta MySQL.
    public int getIdPeserta() { return getId(); }
    public void setIdPeserta(int idPeserta) { setId(idPeserta); }

    public String getNim() { return nim; }
    public String getProdi() { return prodi; }
    public String getJenisKelamin() { return jenisKelamin; }
    public String getEmail() { return email; }

    public void setNim(String nim) {
        if (nim == null || nim.trim().isEmpty())
            throw new IllegalArgumentException("NIM wajib diisi");
        if (nim.trim().length() > 30)
            throw new IllegalArgumentException("NIM maksimal 30 karakter");
        this.nim = nim.trim();
    }

    public void setProdi(String prodi) {
        if (prodi == null || prodi.trim().isEmpty())
            throw new IllegalArgumentException("Prodi wajib diisi");
        if (prodi.trim().length() > 100)
            throw new IllegalArgumentException("Prodi maksimal 100 karakter");
        this.prodi = prodi.trim();
    }

    public void setJenisKelamin(String jenisKelamin) {
        if (!"L".equals(jenisKelamin) && !"P".equals(jenisKelamin))
            throw new IllegalArgumentException("Jenis kelamin harus L atau P");
        this.jenisKelamin = jenisKelamin;
    }

    public void setEmail(String email) {
        String nilai = email == null ? "" : email.trim();
        if (!nilai.isEmpty() && (!nilai.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+[.][A-Za-z]{2,}$")
                || nilai.length() > 120))
            throw new IllegalArgumentException("Format email tidak valid");
        this.email = nilai;
    }

    @Override
    public String getInfo() {
        return "PESERTA | " + super.getInfo()
                + " | NIM: " + getNim()
                + " | Prodi: " + getProdi();
    }
}
