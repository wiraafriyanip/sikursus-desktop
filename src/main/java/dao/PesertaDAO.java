package dao;

import config.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import model.Peserta;

/**
 * DAO Master Peserta - pertemuan 9 (INSERT) dan pertemuan 10 (CRUD + pencarian).
 * Semua SQL tetap berada di DAO. Semua query menggunakan PreparedStatement.
 */
public class PesertaDAO {

    private static final String FIELDS =
            "id_peserta, nim, nama, jenis_kelamin, prodi, no_hp, email";

    /** Memetakan satu baris JDBC ke model yang tetap extends Orang (P5/P6). */
    private Peserta mapRow(ResultSet rs) throws SQLException {
        try {
            Peserta p = new Peserta(
                    rs.getString("nim"),
                    rs.getString("nama"),
                    rs.getString("jenis_kelamin"),
                    rs.getString("prodi"),
                    rs.getString("no_hp"),
                    rs.getString("email"));
            p.setIdPeserta(rs.getInt("id_peserta"));
            return p;
        } catch (IllegalArgumentException ex) {
            throw new SQLException("Data peserta ID " + rs.getInt("id_peserta")
                    + " tidak valid: " + ex.getMessage(), ex);
        }
    }

    private static void setNullable(PreparedStatement ps, int index,
                                    String value) throws SQLException {
        if (value == null || value.trim().isEmpty()) {
            ps.setNull(index, Types.VARCHAR);
        } else {
            ps.setString(index, value.trim());
        }
    }

    /** CREATE: mempertahankan kontrak method P9 dan mengisi generated key. */
    public boolean insert(Peserta p) throws SQLException {
        if (p == null) throw new IllegalArgumentException("Peserta tidak boleh null");
        String sql = "INSERT INTO peserta "
                + "(nim,nama,jenis_kelamin,prodi,no_hp,email) VALUES (?,?,?,?,?,?)";
        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, p.getNim());
            ps.setString(2, p.getNama());
            ps.setString(3, p.getJenisKelamin());
            ps.setString(4, p.getProdi());
            setNullable(ps, 5, p.getNoHp());
            setNullable(ps, 6, p.getEmail());
            if (ps.executeUpdate() != 1) return false;
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) p.setIdPeserta(keys.getInt(1));
            }
            return true;
        }
    }

    /** READ: data terbaru di urutan atas. */
    public List<Peserta> findAll() throws SQLException {
        List<Peserta> rows = new ArrayList<>();
        String sql = "SELECT " + FIELDS + " FROM peserta ORDER BY id_peserta DESC";
        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) rows.add(mapRow(rs));
        }
        return rows;
    }

    /** UPDATE: hanya baris dengan primary key id_peserta yang dipilih. */
    public boolean update(Peserta p) throws SQLException {
        if (p == null || p.getIdPeserta() <= 0) {
            throw new IllegalArgumentException("Pilih peserta yang valid sebelum mengubah.");
        }
        String sql = "UPDATE peserta SET nim=?, nama=?, jenis_kelamin=?, "
                + "prodi=?, no_hp=?, email=? WHERE id_peserta=?";
        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, p.getNim());
            ps.setString(2, p.getNama());
            ps.setString(3, p.getJenisKelamin());
            ps.setString(4, p.getProdi());
            setNullable(ps, 5, p.getNoHp());
            setNullable(ps, 6, p.getEmail());
            ps.setInt(7, p.getIdPeserta());
            return ps.executeUpdate() > 0;
        }
    }

    /** DELETE: pastikan konfirmasi sudah diperoleh dari view. */
    public boolean delete(int idPeserta) throws SQLException {
        if (idPeserta <= 0) throw new IllegalArgumentException("ID peserta tidak valid.");
        String sql = "DELETE FROM peserta WHERE id_peserta=?";
        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, idPeserta);
            return ps.executeUpdate() > 0;
        }
    }

    /** SEARCH: pencarian sebagian NIM ATAU nama menggunakan dua parameter LIKE. */
    public List<Peserta> search(String keyword) throws SQLException {
        String text = keyword == null ? "" : keyword.trim();
        if (text.isEmpty()) return findAll();
        List<Peserta> rows = new ArrayList<>();
        String sql = "SELECT " + FIELDS + " FROM peserta "
                + "WHERE nim LIKE ? OR nama LIKE ? ORDER BY nama";
        String pattern = "%" + text + "%";
        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, pattern);
            ps.setString(2, pattern);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) rows.add(mapRow(rs));
            }
        }
        return rows;
    }
}
