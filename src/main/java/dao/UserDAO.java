package dao;

import config.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.User;
import util.PasswordUtil;

/** DAO untuk autentikasi akun dari tabel users. */
public class UserDAO {

    public User authenticate(String username, char[] password) throws SQLException {
        if (username == null || username.trim().isEmpty()
                || password == null || password.length == 0) {
            return null;
        }

        String sql = "SELECT id_user, username, password_hash, role, aktif "
                + "FROM users WHERE username = ? AND aktif = 1 LIMIT 1";
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username.trim());
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return null;
                String passwordHash = rs.getString("password_hash");
                if (!PasswordUtil.verify(password, passwordHash)) return null;
                return new User(
                        rs.getInt("id_user"),
                        rs.getString("username"),
                        rs.getString("role"),
                        rs.getBoolean("aktif"));
            }
        }
    }
}
