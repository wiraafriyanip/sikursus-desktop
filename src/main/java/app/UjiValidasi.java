package app;

import model.Instruktur;
import model.Kursus;
import model.Orang;
import model.Peserta;

/** Uji manual seluruh kondisi invalid dengan pesan yang bisa dijadikan evidence. */
public class UjiValidasi {
    private interface Skenario { void jalankan(); }

    private static void uji(String nama, Skenario uji) {
        try {
            uji.jalankan();
            System.out.println("TIDAK DITOLAK: " + nama);
        } catch (IllegalArgumentException ex) {
            System.out.println("DITOLAK (" + nama + "): " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        uji("ID negatif", () -> new Orang(-1, "Alya", "081234567890"));
        uji("Nama kosong", () -> new Orang(1, " ", "081234567890"));
        uji("No HP huruf", () -> new Orang(1, "Alya", "telepon"));
        uji("NIM kosong", () -> new Peserta(1, "Alya", "081234567890", "", "Informatika"));
        uji("Prodi kosong", () -> new Peserta(1, "Alya", "081234567890", "252001", ""));
        uji("Keahlian kosong", () -> new Instruktur(2, "Dina", "081234567890", ""));
        uji("Biaya negatif", () -> new Kursus("X", "Java", "Basic", -100000));
        System.out.println("\nVALID: " + new Peserta(1, "Alya", "081234567890", "252001", "Informatika").getInfo());
        System.out.println("VALID biaya: " + new Kursus("J", "Java", "Basic", 500000).getBiaya());
    }
}
