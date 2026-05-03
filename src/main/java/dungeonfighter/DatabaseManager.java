package dungeonfighter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Explanation:
 *
 * @author Tharindu Amarasinghage
 * @since 4/22/26
 */
public class DatabaseManager {


    private static final String DEFAULT_DB_URL = "jdbc:sqlite:app.db";
    private static DatabaseManager instance;

    private Connection connection;

    private static String currentUser;

    private DatabaseManager(){
        try{
            String DB_URL = System.getProperty("app.db.url", DEFAULT_DB_URL);
            connection = DriverManager.getConnection(DB_URL);
            System.out.println("Database Connected");
            createTables();
            scoreCardTables();
        } catch (SQLException e){
            System.err.println("Connection failed: " + e.getMessage());
        }
    }

    public static DatabaseManager getInstance(){
        if (instance == null){
            instance = new DatabaseManager();
        }
        return instance;
    }



    public void close(){
        try{
            if(connection != null && !connection.isClosed()){
                connection.close();
            }
        } catch (SQLException e){
            System.err.println("Error Closing DB: " + e.getMessage());
        }
    }

    static void resetForTesting(){
        if(instance != null) instance.close();
        instance = null;

    }

    public void createTables(){
        String sql = """
                CREATE TABLE IF NOT EXISTS users (
                id              INTEGER PRIMARY KEY AUTOINCREMENT,
                username        TEXT    NOT NULL UNIQUE,
                password        TEXT    NOT NULL,
                date_register   TEXT    DEFAULT (datetime('now'))
                )
                
                """;

        try (Statement stmt = connection.createStatement()){
            stmt.execute(sql);
        } catch (SQLException e){
            System.err.println("Database Initialization Failed: " + e.getMessage());
        }
    }

    public void registerUser(String userName, String password){
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
        try(PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setString(1, userName);
            pstmt.setString(2, password);
            pstmt.executeUpdate();
        } catch (SQLException e){
            System.err.println("registerUser Failed: " + e.getMessage());
        }

    }

    public void updateUserPassword(String username, String newPassword) {
        String sql = "UPDATE users SET password = ? WHERE username = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, newPassword);
            pstmt.setString(2, username);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("updateUserPassword failed: " + e.getMessage());
        }
    }

    public void deleteUser(String username) {
        String sql = "DELETE FROM users WHERE username = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("deleteUser failed: " + e.getMessage());
        }
    }

    public boolean userExists(String username){
        String sql = "SELECT 1 FROM users WHERE username = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery();
            return rs.next(); // true if found, false if not

        } catch (SQLException e) {
            System.err.println("userExists check failed: " + e.getMessage());
            return false;
        }
    }

    public boolean userRemove(String username){
        String sql = "DELETE FROM users WHERE username = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, username);

            int rowsDeleted = pstmt.executeUpdate();
            return rowsDeleted > 0; // true if user deleted

        } catch (SQLException e) {
            System.err.println("userRemove failed: " + e.getMessage());
            return false;
        }
    }

    public boolean passwordMatch(String username, String password){
        String sql = "SELECT 2 FROM users WHERE username = ? AND password = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();
            return rs.next();

        } catch (SQLException e){
            System.err.println("Login Failed: " + e.getMessage());
            return false;
        }
    }


    public void scoreCardTables(){
        String sql = """
                CREATE TABLE IF NOT EXISTS scores (
                id              INTEGER PRIMARY KEY AUTOINCREMENT,
                username        TEXT    NOT NULL,
                score       INTEGER    NOT NULL,
                datePlayed   TEXT    DEFAULT (datetime('now'))
                )
                
                """;

        try (Statement stmt = connection.createStatement()){
            stmt.execute(sql);
        } catch (SQLException e){
            System.err.println("Database Initialization Failed: " + e.getMessage());
        }
    }

    public void highScoresTable(String username, int score){

        String sql = "INSERT INTO scores (username, score) VALUES (?, ?)";
        try(PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setString(1, username);
            pstmt.setInt(2, score);
            pstmt.executeUpdate();
        } catch (SQLException e){
            System.err.println("highScoreTable Failed: " + e.getMessage());
        }

    }

    public void updateScore(int scoreId, int updatedScore) {
        String sql = "UPDATE scores SET score = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, updatedScore);
            pstmt.setInt(2, scoreId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("updateScore failed: " + e.getMessage());
        }
    }

    public void deleteScore(int scoreId) {
        String sql = "DELETE FROM scores WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, scoreId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("deleteScore failed: " + e.getMessage());
        }
    }

    public List<String[]> getTopTenScores() {

        List<String[]> topScores = new ArrayList<>();

        String sql = "SELECT username, score FROM scores ORDER BY score DESC LIMIT 10";

        try (PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String username = rs.getString("username");
                String score = Integer.toString(rs.getInt("score"));
                topScores.add(new String[]{username, score});
            }

        } catch (SQLException e) {
            System.err.println("Login Failed: " + e.getMessage());


        }
        return topScores;
    }


    public static void setCurrentUser(String user) {
        currentUser = user;
    }

    public static String getCurrentUser() {
        return currentUser;
    }

}
