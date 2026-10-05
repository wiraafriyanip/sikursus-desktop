package latihan;

import java.util.Scanner;

public class LatihanBiayaKursus {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        // 1. INPUT DINAMIS & VALIDASI
        System.out.println("=== SISTEM KASIR KURSUS IT ===");
        System.out.print("Masukkan Kode Kursus (Contoh: JAVA-BSC atau WEB-ADV): ");
        String kode = input.nextLine().toUpperCase();
        
        double biayakursus = 0;
        boolean inputValid = false;
        
        while (!inputValid) {
            try {
                System.out.print("Masukkan Biaya Registrasi (Angka saja): Rp");
                biayakursus = input.nextDouble();
                inputValid = true;
            } catch (Exception e) {
                System.out.println("[ERROR] Input harus berupa angka tanpa titik/koma! Coba lagi.");
                input.nextLine();
            }
        }
        
        // 2. OTOMATISASI DATA
        String namaKursus;
        double biayaTambahanFasilitas = 0;
        
        if (kode.startsWith("JAVA")) {
            namaKursus = "Java Desktop Fundamental";
            biayaTambahanFasilitas = 500000;
        } else if (kode.startsWith("WEB")) {
            namaKursus = "Advanced Web Development";
            biayaTambahanFasilitas = 750000;
        } else {
            namaKursus = "Kursus Umum (Standard Kelas)";
            biayaTambahanFasilitas = 250000;
        }

        // 3. PERHITUNGAN
        double totalSebelumDiskon = biayakursus + biayaTambahanFasilitas;
        double persenDiskon = hitungPersenDiskon(totalSebelumDiskon);
        double potongan = totalSebelumDiskon * persenDiskon;
        double totalAkhir = totalSebelumDiskon - potongan;
        String status = tentukanStatusHarga(totalAkhir);
        
        // 4. OUTPUT
        System.out.println("\n====================================");
        System.out.println("Kode             : " + kode);
        System.out.println("Kursus           : " + namaKursus);
        System.out.printf("Biaya Registrasi : Rp%,.0f%n", biayakursus);
        System.out.printf("Biaya Tambahan   : Rp%,.0f%n", biayaTambahanFasilitas);
        System.out.printf("Total Kotor      : Rp%,.0f%n", totalSebelumDiskon);
        System.out.printf("Diskon (%d%%)     : Rp%,.0f%n",
                (int)(persenDiskon * 100), potongan);
        System.out.println("------------------------------------");
        System.out.printf("Total Bayar      : Rp%,.0f%n", totalAkhir);
        System.out.println("Status           : " + status);
        System.out.println("====================================");
        
        input.close();
    }

    public static double hitungPersenDiskon(double totalKotor) {
        if (totalKotor >= 3000000) {
            return 0.15;
        } else if (totalKotor >= 1500000) {
            return 0.10;
        } else {
            return 0.05;
        }
    }

    public static String tentukanStatusHarga(double totalAkhir) {
        if (totalAkhir >= 2500000) {
            return "PREMIUM/MAHAL";
        } else if (totalAkhir >= 1500000) {
            return "STANDAR";
        } else {
            return "EKONOMIS/TERJANGKAU";
        }
    }
}