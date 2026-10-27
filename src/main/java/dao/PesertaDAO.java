package dao;

import config.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.Peserta;

/** Data Access Object: INSERT aman memakai PreparedStatement. */
public class PesertaDAO {

    public boolean insert(Peserta p) throws SQLException {
        if (p == null) throw new IllegalArgumentException("Peserta tidak boleh null");
        final String sql = "INSERT INTO peserta "
                + "(nim, nama, jenis_kelamin, prodi, no_hp, email) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, p.getNim());
            ps.setString(2, p.getNama());
            ps.setString(3, p.getJenisKelamin());
            ps.setString(4, p.getProdi());
            ps.setString(5, p.getNoHp().isEmpty() ? null : p.getNoHp());
            ps.setString(6, p.getEmail().isEmpty() ? null : p.getEmail());

            if (ps.executeUpdate() != 1) return false;
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) p.setIdPeserta(keys.getInt(1));
            }
            return true;
        }
    }
}
