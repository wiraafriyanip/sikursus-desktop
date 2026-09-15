package latihan;

public class LatihanBiayaKursus {

    public static void main(String[] args) {
        // --- data awal ---
        String kode = "JAVA";
        String nama = "Java Desktop Fundamental";
        
        // biaya registrasi diisi nilai ukt kamu
        double biayaRegistrasi = 2_120_000;
        
        // biaya tambahan tetap rp500.000
        double biayaTambahan = 500_000;

        // total kotor = biaya registrasi + biaya tambahan
        double totalKotor = biayaRegistrasi + biayaTambahan;

        // logic diskon bertingkat berdasarkan total kotor
        double rateDiskon;
        if (totalKotor >= 3_500_000) {
            rateDiskon = 0.15; // 15% jika total kotor >= 3,5 jt
        } else if (totalKotor >= 1_500_000) {
            rateDiskon = 0.10; // 10% jika total kotor 1,5 jt - 3 jt
        } else {
            rateDiskon = 0.05; // 5% jika total kotor < 1,5 jt
        }

        double diskon = totalKotor * rateDiskon;
        double totalBayar = totalKotor - diskon;

        // logic status berdasarkan total bayar
        String status;
        if (totalBayar > 2_500_000) {
            status = "MAHAL";
        } else if (totalBayar >= 1_500_000) {
            status = "STANDAR";
        } else {
            status = "TERJANGKAU";
        }

        // --- output ke console (sesuai format bapak di layar proyektor) ---
        System.out.println("--- SISTEM KASIR KURSUS IT ---");
        System.out.println("----------------------------------------");
        System.out.println("Kode             : " + kode);
        System.out.println("Kursus           : " + nama);
        System.out.printf("Biaya Registrasi : Rp%,.0f%n", biayaRegistrasi);
        System.out.printf("Biaya Tambahan   : Rp%,.0f%n", biayaTambahan);
        System.out.printf("Total Kotor      : Rp%,.0f%n", totalKotor);
        System.out.printf("Diskon (%.0f%%)     : Rp%,.0f%n", (rateDiskon * 100), diskon);
        System.out.println("----------------------------------------");
        System.out.printf("Total Bayar      : Rp%,.0f%n", totalBayar);
        System.out.println("Status           : " + status);
        System.out.println("----------------------------------------");
    }
}