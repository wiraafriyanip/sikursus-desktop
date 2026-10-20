package app;

import java.util.ArrayList;
import java.util.List;
import model.Admin;
import model.Instruktur;
import model.Orang;
import model.Peserta;

/** Demo milestone pertemuan 6: overriding, polymorphism, argumen, instanceof. */
public class DemoPolymorphism {

    public static void tampilkanInfo(Orang orang) {
        System.out.println(orang.getInfo());
    }

    public static void cetakSemua(List<Orang> daftar) {
        for (Orang o : daftar) {
            // Dynamic dispatch: tanpa if maupun instanceof.
            System.out.println(o.getInfo());
        }
    }

    public static void main(String[] args) {
        Peserta peserta = new Peserta(1, "Ahmad Fauzan", "081234567801", "252001", "Informatika");
        Instruktur instruktur = new Instruktur(2, "Dina Pratama", "081211110001", "Java Desktop");
        Admin admin = new Admin(3, "Maya Sari", "081288880001", "Operator Kursus");

        System.out.println("=== OVERRIDE LANGSUNG ===");
        System.out.println(peserta.getInfo());
        System.out.println(instruktur.getInfo());

        List<Orang> daftarOrang = new ArrayList<Orang>();
        daftarOrang.add(peserta);
        daftarOrang.add(instruktur);
        daftarOrang.add(admin);  // Tipe baru, loop tidak berubah.

        System.out.println("\n=== LIST<ORANG> DAN LOOP ===");
        cetakSemua(daftarOrang);

        System.out.println("\n=== POLYMORPHIC ARGUMENT ===");
        tampilkanInfo(peserta);
        tampilkanInfo(instruktur);

        System.out.println("\n=== INSTANCEOF KHUSUS NIM PESERTA ===");
        for (Orang o : daftarOrang) {
            if (o instanceof Peserta) {
                Peserta p = (Peserta) o;
                System.out.println("NIM khusus peserta: " + p.getNim());
            }
        }
        System.out.println("Total objek: " + daftarOrang.size());
    }
}
