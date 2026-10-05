package latihan;

import java.util.Scanner;

public class LatihanBidangDatar1 {
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        // Bikin variabel buat nentuin apakah loop bakal jalan terus atau berhenti
        String lanjut = "y"; 
        
        // Selama variabel 'lanjut' isinya huruf 'y', program bakal muter terus di dalam blok ini
        while (lanjut.equalsIgnoreCase("y")) {
            
            System.out.println("=== PROGRAM HITUNG PERSEGI PANJANG ===");
            
            System.out.print("Masukkan nilai panjang (cm): ");
            double panjang = input.nextDouble(); 
            
            System.out.print("Masukkan nilai lebar (cm)  : ");
            double lebar = input.nextDouble();   
            
            double luas = panjang * lebar;
            double keliling = 2 * (panjang + lebar);
            
            System.out.println("\n--- HASIL KALKULASI ---"); 
            System.out.println("Luas Area       : " + luas + " cm persegi");
            System.out.println("Total Keliling  : " + keliling + " cm");
            System.out.println("-----------------------");
            
            // Konfirmasi ke user mau ngulang apa nggak
            System.out.print("Mau hitung angka lain? (y/n): ");
            lanjut = input.next(); // Nangkep jawaban 'y' atau 'n'
            
            System.out.println(); // Kasih enter kosong biar rapi pas muter ke atas lagi
        }
        
        // Kalau user ngetik selain 'y', loop-nya jebol dan baris ini bakal dieksekusi
        System.out.println("Program selesai. Thank you!");
        input.close();
    }
}