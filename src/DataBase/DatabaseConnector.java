package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DatabaseConnector {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/ulartangga";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    // Metode untuk mendapatkan koneksi
    public static Connection getConnection() throws SQLException {
        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            System.out.println("Koneksi ke database berhasil!");
            return connection;
        } catch (SQLException e) {
            System.err.println("Koneksi ke database gagal: " + e.getMessage());
            throw e;
        }
    }

    public static boolean checkConnection() {
        try (Connection connection = getConnection()) {
            JOptionPane.showMessageDialog(null, "Koneksi ke database berhasil!");
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Koneksi ke database gagal: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public static void main(String[] args) {
        checkConnection();
    }
}
