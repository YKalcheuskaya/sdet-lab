package db;

import java.sql.*;
import java.util.Properties;

public class DbUtil {

    public static Connection getConnection() throws SQLException {
        String url = Config.get("db.url");
        String user = Config.get("db.user");
        String pass = Config.get("db.password");
        String ssl = Config.get("db.sslmode"); // опционально (require/disable/verify-full и т.п.)

        if (url == null || user == null || pass == null) {
            throw new IllegalStateException("DB config is missing: db.url/db.user/db.password");
        }

        url = url.trim();
        user = user.trim();
        pass = pass.trim();

        // если нужно принудительно добавить sslmode в URL
        if (ssl != null && !ssl.isBlank() && !url.contains("sslmode=")) {
            url += (url.contains("?") ? "&" : "?") + "sslmode=" + ssl.trim();
        }

        // безопасный лог (без пароля)
        System.out.printf("DB connect -> url=%s, user=%s, passLen=%d%n",
                url, user, pass.length());

        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", pass);

        return DriverManager.getConnection(url, props);
    }

    public static String getUserEmailById(int userId) {
        String sql = "SELECT email FROM " + Config.get("db.schema") + ".users WHERE id = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? rs.getString("email") : null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("DB query failed: " + e.getMessage(), e);
        }
    }
}