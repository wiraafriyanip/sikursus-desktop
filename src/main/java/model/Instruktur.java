package model;

/** Model instruktur yang tetap mewarisi identitas dasar Orang (P5/P6). */
public class Instruktur extends Orang {
    private String keahlian;
    private String email = "";

    /** Constructor lama dipertahankan agar latihan/pertemuan sebelumnya tidak rusak. */
    public Instruktur(int id, String nama, String noHp, String keahlian) {
        super(id, nama, noHp);
        setKeahlian(keahlian);
    }

    /** Constructor untuk data Master Instruktur yang tersimpan di MySQL. */
    public Instruktur(int id, String nama, String keahlian, String noHp, String email) {
        this(id, nama, noHp, keahlian);
        setEmail(email);
    }

    public String getKeahlian() { return keahlian; }
    public String getEmail() { return email; }

    public void setKeahlian(String keahlian) {
        if (keahlian == null || keahlian.trim().isEmpty())
            throw new IllegalArgumentException("Keahlian wajib diisi");
        if (keahlian.trim().length() > 120)
            throw new IllegalArgumentException("Keahlian maksimal 120 karakter");
        this.keahlian = keahlian.trim();
    }

    public void setEmail(String email) {
        String value = email == null ? "" : email.trim();
        if (!value.isEmpty() && (!value.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+[.][A-Za-z]{2,}$")
                || value.length() > 120)) {
            throw new IllegalArgumentException("Format email tidak valid atau melebihi 120 karakter");
        }
        this.email = value;
    }

    @Override
    public String getInfo() {
        return "INSTRUKTUR | " + super.getInfo() + " | Keahlian: " + getKeahlian();
    }

    @Override
    public String toString() { return getNama() + " (ID " + getId() + ")"; }
}
