// Import paket yang diperlukan
package src.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Kelas DatabaseConnector untuk mengelola koneksi ke database
public class DatabaseConnector {
    // URL JDBC untuk koneksi ke database MySQL
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/ulartangga";
    
    // Nama pengguna dan kata sandi untuk mengakses database
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    // Metode untuk mendapatkan objek koneksi ke database
    public static Connection getConnection() throws SQLException {
        try {
            // Membuat koneksi ke database menggunakan DriverManager
            Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            
            // Menampilkan pesan keberhasilan jika koneksi berhasil
            System.out.println("Koneksi ke database berhasil!");
            
            // Mengembalikan objek koneksi
            return connection;
        } catch (SQLException e) {
            // Menampilkan pesan kesalahan jika koneksi gagal dan melempar SQLException
            System.err.println("Koneksi ke database gagal: " + e.getMessage());
            throw e;
        }
    }

    // Metode utama untuk melakukan tes koneksi
    public static void main(String[] args) {
        try (Connection connection = getConnection()) {
            // Menampilkan pesan keberhasilan jika tes koneksi berhasil
            System.out.println("Tes koneksi berhasil!");
        } catch (SQLException e) {
            // Menampilkan stack trace jika terjadi kesalahan pada tes koneksi
            e.printStackTrace();
        }
    }
}
