package config;

import java.sql.Connection;
import java.sql.SQLException;

/** Run File (Shift+F6) setelah Laragon / MySQL menyala dan SQL diimpor. */
public class TestDatabase {
    public static void main(String[] args) {
        try (Connection conn = Database.getConnection()) {
            if (conn != null && conn.isValid(2)) {
                System.out.println("KONEKSI BERHASIL ke db_sikursus");
            } else {
                System.out.println("Koneksi terbentuk tetapi tidak valid.");
            }
        } catch (SQLException ex) {
            System.out.println("KONEKSI GAGAL: " + ex.getMessage());
        }
    }
}
