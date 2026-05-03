package dungeonfighter;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Explanation:
 *
 * @author Tharindu Amarasinghage
 * @since 4/29/26
 */
class DatabaseManagerTest {

    @BeforeEach
    void freshDb(){
        System.setProperty("app.db.url", "jdbc:sqlite::memory:");
        DatabaseManager.resetForTesting();
    }
    @AfterEach
    void tearDown() {
        DatabaseManager.resetForTesting();
    }

    @Test
    @DisplayName("getInstance returns the same object every call")
    void singletonIdentity(){
        DatabaseManager a = DatabaseManager.getInstance();
        DatabaseManager b = DatabaseManager.getInstance();
        assertSame(a,b);
    }

    @Test
    void registerUser_Success(){
        DatabaseManager db = DatabaseManager.getInstance();
        db.registerUser("testUser", "1234");
        assertTrue(db.userExists("testUser"));
    }

    @Test
    void passwordMatch(){
        DatabaseManager db = DatabaseManager.getInstance();
        db.registerUser("testUser", "1234");
        assertTrue(db.passwordMatch("testUser", "1234"));
    }

    @Test
    @DisplayName("highScoresTable adds a score to scores table")
    void highScoresTable_success() {
        DatabaseManager db = DatabaseManager.getInstance();

        db.highScoresTable("testUser", 250);

        List<String> scores = db.getTopTenScores();

        assertEquals(1, scores.size());
        assertTrue(scores.get(0).contains("testUser"));
        assertTrue(scores.get(0).contains("250"));
    }
}