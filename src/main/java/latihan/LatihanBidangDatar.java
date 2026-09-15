package latihan;

public class LatihanBidangDatar {
    
    public static void main(String[] args) {
        // --- VARIABLE DECLARATION ---
        // Pake tipe double in case ukurannya ada desimal (misal 15.5)
        double panjang = 15.0; 
        double lebar = 10.0;
        
        // --- CORE LOGIC ---
        // Rumus Luas = Panjang x Lebar
        double luas = panjang * lebar;
        
        // Rumus Keliling = 2 x (Panjang + Lebar)
        double keliling = 2 * (panjang + lebar);
        
        // --- OUTPUT ---
        System.out.println("=== PROGRAM HITUNG PERSEGI PANJANG ===");
        System.out.println("Panjang Bangun  : " + panjang + " cm");
        System.out.println("Lebar Bangun    : " + lebar + " cm");
        System.out.println("--------------------------------------");
        System.out.println("Luas Area       : " + luas + " cm persegi");
        System.out.println("Total Keliling  : " + keliling + " cm");
    }
}