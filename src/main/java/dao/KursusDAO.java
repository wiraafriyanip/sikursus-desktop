package dao;

import config.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Kursus;

/** Operasi database Master Kursus. */
public class KursusDAO {
    private static final String COLUMNS = "id_kursus, kode_kursus, nama_kursus, level, biaya";

    private Kursus map(ResultSet rs) throws SQLException {
        return new Kursus(rs.getInt("id_kursus"), rs.getString("kode_kursus"),
                rs.getString("nama_kursus"), rs.getString("level"), rs.getDouble("biaya"));
    }

    public boolean insert(Kursus item) throws SQLException {
        String sql = "INSERT INTO kursus (kode_kursus, nama_kursus, level, biaya) VALUES (?,?,?,?)";
        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            bind(ps, item);
            if (ps.executeUpdate() != 1) return false;
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) item.setIdKursus(keys.getInt(1));
            }
            return true;
        }
    }

    private void bind(PreparedStatement ps, Kursus item) throws SQLException {
        ps.setString(1, item.getKode());
        ps.setString(2, item.getNama());
        ps.setString(3, item.getLevel());
        ps.setBigDecimal(4, java.math.BigDecimal.valueOf(item.getBiaya()));
    }

    public List<Kursus> findAll() throws SQLException {
        List<Kursus> rows = new ArrayList<>();
        String sql = "SELECT " + COLUMNS + " FROM kursus ORDER BY id_kursus DESC";
        try (Connection c = Database.getConnection(); PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) rows.add(map(rs));
        }
        return rows;
    }

    public List<Kursus> search(String keyword) throws SQLException {
        String text = keyword == null ? "" : keyword.trim();
        if (text.isEmpty()) return findAll();
        List<Kursus> rows = new ArrayList<>();
        String sql = "SELECT " + COLUMNS + " FROM kursus "
                + "WHERE kode_kursus LIKE ? OR nama_kursus LIKE ? OR level LIKE ? ORDER BY nama_kursus";
        String like = "%" + text + "%";
        try (Connection c = Database.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, like); ps.setString(2, like); ps.setString(3, like);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) rows.add(map(rs));
            }
        }
        return rows;
    }

    public boolean update(Kursus item) throws SQLException {
        if (item == null || item.getIdKursus() <= 0) throw new IllegalArgumentException("Pilih kursus yang valid.");
        String sql = "UPDATE kursus SET kode_kursus=?, nama_kursus=?, level=?, biaya=? WHERE id_kursus=?";
        try (Connection c = Database.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            bind(ps, item);
            ps.setInt(5, item.getIdKursus());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean delete(int id) throws SQLException {
        if (id <= 0) throw new IllegalArgumentException("ID kursus tidak valid.");
        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement("DELETE FROM kursus WHERE id_kursus=?")) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}
