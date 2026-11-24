package dao;

import config.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/** Membaca daftar pendaftaran melalui relasi peserta, kelas, kursus, dan instruktur. */
public final class PendaftaranDAO {
    private static final String SQL =
            "SELECT p.id_pendaftaran, ps.nim, ps.nama AS nama_peserta, "
            + "kls.nama_kelas, p.tanggal_daftar, p.status AS status_pendaftaran "
            + "FROM pendaftaran p "
            + "INNER JOIN peserta ps ON p.id_peserta = ps.id_peserta "
            + "INNER JOIN kelas kls ON p.id_kelas = kls.id_kelas "
            + "INNER JOIN kursus krs ON kls.id_kursus = krs.id_kursus "
            + "INNER JOIN instruktur ins ON kls.id_instruktur = ins.id_instruktur "
            + "ORDER BY p.id_pendaftaran DESC";

    public List<Object[]> findAll() throws SQLException {
        List<Object[]> rows = new ArrayList<Object[]>();
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL);
             ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                rows.add(new Object[]{
                    Integer.valueOf(result.getInt("id_pendaftaran")),
                    result.getString("nim"),
                    result.getString("nama_peserta"),
                    result.getString("nama_kelas"),
                    result.getDate("tanggal_daftar"),
                    result.getString("status_pendaftaran")
                });
            }
        }
        return rows;
    }
}
