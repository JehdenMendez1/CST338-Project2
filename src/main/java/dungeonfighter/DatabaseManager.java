package dungeonfighter;

import java.sql.*;

/**
 * Explanation:
 *
 * @author Tharindu Amarasinghage
 * @since 4/22/26
 */
public class DatabaseManager {


    private static final String DB_URL = "jdbc:sqlite:app.db";
    private static DatabaseManager instance;

    private Connection connection;

    private DatabaseManager(){
        try{
            connection = DriverManager.getConnection(DB_URL);
            System.out.println("Database Connected");
            createTables();
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

    public boolean userExists(String username){
        String sql = "SELECT 1 FROM users WHERE userName = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery();
            return rs.next(); // true if found, false if not

        } catch (SQLException e) {
            System.err.println("userExists check failed: " + e.getMessage());
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

}
