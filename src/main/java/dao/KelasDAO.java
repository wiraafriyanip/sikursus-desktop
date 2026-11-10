package dao;

import config.Database;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Kelas;

/** Operasi database Master Kelas dengan relasi ke tabel kursus dan instruktur. */
public class KelasDAO {
    private static final String SELECT = "SELECT k.id_kelas, k.id_kursus, k.id_instruktur, "
            + "k.nama_kelas, k.tanggal_mulai, k.kapasitas, c.nama_kursus, i.nama AS nama_instruktur "
            + "FROM kelas k JOIN kursus c ON c.id_kursus=k.id_kursus "
            + "JOIN instruktur i ON i.id_instruktur=k.id_instruktur ";

    private Kelas map(ResultSet rs) throws SQLException {
        Kelas item = new Kelas(rs.getInt("id_kelas"), rs.getInt("id_kursus"),
                rs.getInt("id_instruktur"), rs.getString("nama_kelas"),
                rs.getDate("tanggal_mulai").toLocalDate(), rs.getInt("kapasitas"));
        item.setNamaKursus(rs.getString("nama_kursus"));
        item.setNamaInstruktur(rs.getString("nama_instruktur"));
        return item;
    }

    public boolean insert(Kelas item) throws SQLException {
        String sql = "INSERT INTO kelas (id_kursus,id_instruktur,nama_kelas,tanggal_mulai,kapasitas) "
                + "VALUES (?,?,?,?,?)";
        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            bind(ps, item);
            if (ps.executeUpdate() != 1) return false;
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) item.setIdKelas(keys.getInt(1));
            }
            return true;
        }
    }

    private void bind(PreparedStatement ps, Kelas item) throws SQLException {
        ps.setInt(1, item.getIdKursus());
        ps.setInt(2, item.getIdInstruktur());
        ps.setString(3, item.getNamaKelas());
        ps.setDate(4, Date.valueOf(item.getTanggalMulai()));
        ps.setInt(5, item.getKapasitas());
    }

    public List<Kelas> findAll() throws SQLException {
        List<Kelas> rows = new ArrayList<>();
        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(SELECT + "ORDER BY k.id_kelas DESC");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) rows.add(map(rs));
        }
        return rows;
    }

    public List<Kelas> search(String keyword) throws SQLException {
        String text = keyword == null ? "" : keyword.trim();
        if (text.isEmpty()) return findAll();
        List<Kelas> rows = new ArrayList<>();
        String sql = SELECT + "WHERE k.nama_kelas LIKE ? OR c.nama_kursus LIKE ? "
                + "OR i.nama LIKE ? ORDER BY k.nama_kelas";
        String like = "%" + text + "%";
        try (Connection c = Database.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, like); ps.setString(2, like); ps.setString(3, like);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) rows.add(map(rs));
            }
        }
        return rows;
    }

    public boolean update(Kelas item) throws SQLException {
        if (item == null || item.getIdKelas() <= 0) throw new IllegalArgumentException("Pilih kelas yang valid.");
        String sql = "UPDATE kelas SET id_kursus=?, id_instruktur=?, nama_kelas=?, tanggal_mulai=?, "
                + "kapasitas=? WHERE id_kelas=?";
        try (Connection c = Database.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            bind(ps, item);
            ps.setInt(6, item.getIdKelas());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean delete(int id) throws SQLException {
        if (id <= 0) throw new IllegalArgumentException("ID kelas tidak valid.");
        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement("DELETE FROM kelas WHERE id_kelas=?")) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}
