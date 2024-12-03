// Import paket-paket yang diperlukan
package src.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

// Kelas DatabaseHandler untuk mengelola operasi pada database
public class DatabaseHandler {
    // ArrayList untuk menyimpan data sementara
    public static ArrayList<String> data = new ArrayList<>();

    // Metode untuk menyimpan data pemain ke dalam database
    public static void simpanDataPemain(String nama, int jumlahMenang) {
        try (Connection connection = DatabaseConnector.getConnection()) {
            // Query untuk mengupdate jumlah kemenangan jika pemain sudah terdaftar
            String updateQuery = "UPDATE user SET jumlah_menang = jumlah_menang + ? WHERE nama = ?";
            try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
                updateStatement.setInt(1, jumlahMenang);
                updateStatement.setString(2, nama);
                int updatedRows = updateStatement.executeUpdate();

                // Jika tidak ada baris yang terupdate, artinya pemain belum terdaftar
                if (updatedRows == 0) {
                    // Query untuk menyimpan data pemain baru
                    String insertQuery = "INSERT INTO user (nama, jumlah_menang) VALUES (?, ?)";
                    try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
                        insertStatement.setString(1, nama);
                        insertStatement.setInt(2, jumlahMenang);
                        insertStatement.executeUpdate();

                        // Mendapatkan kunci yang dihasilkan dari operasi penyisipan
                        ResultSet generatedKeys = insertStatement.getGeneratedKeys();

                        // Jika kunci dihasilkan, artinya data pemain berhasil disimpan
                        if (generatedKeys.next()) {
                            int idUser = generatedKeys.getInt(1); 

                            // Query untuk menyimpan data skor pemain
                            String scoreInsertQuery = "INSERT INTO score (id_user, jumlah_menang, tgl_menang) VALUES (?, ?, NOW())";
                            try (PreparedStatement scoreInsertStatement = connection.prepareStatement(scoreInsertQuery)) {
                                scoreInsertStatement.setInt(1, idUser);
                                scoreInsertStatement.setInt(2, jumlahMenang);
                                scoreInsertStatement.executeUpdate();
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Metode untuk menampilkan data leaderboard
    public static ArrayList<String> showLeaderboard() {
        // ArrayList untuk menyimpan data leaderboard
        ArrayList<String> leaderboardData = new ArrayList<>();
        try (Connection connection = DatabaseConnector.getConnection()) {
            // Query untuk mendapatkan data leaderboard yang diurutkan berdasarkan jumlah kemenangan
            String query = "SELECT nama, jumlah_menang FROM user ORDER BY jumlah_menang DESC";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery()) {
                // Memproses hasil query dan menambahkannya ke dalam ArrayList
                while (resultSet.next()) {
                    String winnerName = resultSet.getString("nama");
                    int jumlahMenang = resultSet.getInt("jumlah_menang");
                    String leaderboardEntry = winnerName + ": " + jumlahMenang + " wins";
                    leaderboardData.add(leaderboardEntry);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Mengembalikan data leaderboard
        return leaderboardData;
    }
}
