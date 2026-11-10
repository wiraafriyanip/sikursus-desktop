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
import model.Instruktur;

/** Operasi database Master Instruktur. SQL dipisahkan dari JFrame. */
public class InstrukturDAO {
    private static final String COLUMNS = "id_instruktur, nama, keahlian, no_hp, email";

    private Instruktur map(ResultSet rs) throws SQLException {
        return new Instruktur(rs.getInt("id_instruktur"), rs.getString("nama"),
                rs.getString("keahlian"), rs.getString("no_hp"), rs.getString("email"));
    }

    private void nullable(PreparedStatement ps, int index, String value) throws SQLException {
        if (value == null || value.trim().isEmpty()) ps.setNull(index, Types.VARCHAR);
        else ps.setString(index, value.trim());
    }

    public boolean insert(Instruktur item) throws SQLException {
        String sql = "INSERT INTO instruktur (nama, keahlian, no_hp, email) VALUES (?,?,?,?)";
        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, item.getNama());
            ps.setString(2, item.getKeahlian());
            nullable(ps, 3, item.getNoHp());
            nullable(ps, 4, item.getEmail());
            if (ps.executeUpdate() != 1) return false;
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) item.setId(keys.getInt(1));
            }
            return true;
        }
    }

    public List<Instruktur> findAll() throws SQLException {
        List<Instruktur> rows = new ArrayList<>();
        String sql = "SELECT " + COLUMNS + " FROM instruktur ORDER BY id_instruktur DESC";
        try (Connection c = Database.getConnection(); PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) rows.add(map(rs));
        }
        return rows;
    }

    public List<Instruktur> search(String keyword) throws SQLException {
        String text = keyword == null ? "" : keyword.trim();
        if (text.isEmpty()) return findAll();
        List<Instruktur> rows = new ArrayList<>();
        String sql = "SELECT " + COLUMNS + " FROM instruktur "
                + "WHERE nama LIKE ? OR keahlian LIKE ? OR no_hp LIKE ? OR email LIKE ? "
                + "ORDER BY nama";
        String like = "%" + text + "%";
        try (Connection c = Database.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            for (int i = 1; i <= 4; i++) ps.setString(i, like);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) rows.add(map(rs));
            }
        }
        return rows;
    }

    public boolean update(Instruktur item) throws SQLException {
        if (item == null || item.getId() <= 0) throw new IllegalArgumentException("Pilih instruktur yang valid.");
        String sql = "UPDATE instruktur SET nama=?, keahlian=?, no_hp=?, email=? WHERE id_instruktur=?";
        try (Connection c = Database.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, item.getNama());
            ps.setString(2, item.getKeahlian());
            nullable(ps, 3, item.getNoHp());
            nullable(ps, 4, item.getEmail());
            ps.setInt(5, item.getId());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean delete(int id) throws SQLException {
        if (id <= 0) throw new IllegalArgumentException("ID instruktur tidak valid.");
        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement("DELETE FROM instruktur WHERE id_instruktur=?")) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}
