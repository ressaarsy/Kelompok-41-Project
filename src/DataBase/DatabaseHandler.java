package DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseHandler {

    public static void simpanDataPemain(String nama, int jumlahMenang) {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String updateQuery = "UPDATE user SET jumlah_menang = jumlah_menang + ? WHERE nama = ?";
            try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
                updateStatement.setInt(1, jumlahMenang);
                updateStatement.setString(2, nama);
                int updatedRows = updateStatement.executeUpdate();

                if (updatedRows == 0) {
                    String insertQuery = "INSERT INTO user (nama, jumlah_menang) VALUES (?, ?)";
                    try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
                        insertStatement.setString(1, nama);
                        insertStatement.setInt(2, jumlahMenang);
                        insertStatement.executeUpdate();

                        ResultSet generatedKeys = insertStatement.getGeneratedKeys();
                        if (generatedKeys.next()) {
                            int idUser = generatedKeys.getInt(1);

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

    public static ArrayList<String> showLeaderboard() {
        ArrayList<String> leaderboardData = new ArrayList<>();
        try (Connection connection = DatabaseConnector.getConnection()) {
            String query = "SELECT nama, jumlah_menang FROM user ORDER BY jumlah_menang DESC";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
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
        return leaderboardData;
    }
}
