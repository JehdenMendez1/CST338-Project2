package dungeonfighter.controller;

import dungeonfighter.DatabaseManager;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Explanation:
 *
 * @author Tharindu Amarasinghage
 * @since 4/26/26
 */
class LoginControllerTest {

    @BeforeEach
    void setUp() {






    }

    @AfterEach
    void tearDown() {
        // Delete testUser/test
        DatabaseManager testDB = DatabaseManager.getInstance();
        testDB.userRemove("testUser");
        testDB.userRemove("test");
    }

    @Test
    void testBuildLoginScene() {
    }

    @Test
    void testHandleRegister() {
        LoginController testLC = new LoginController();
        DatabaseManager testDB = DatabaseManager.getInstance();

        // Empty Inputs
        testLC.handleRegister("", "");
        assertFalse(testDB.userExists(""));

        testLC.handleRegister("test", "");
        assertFalse(testDB.userExists("test"));

        testLC.handleRegister("", "test");
        assertFalse(testDB.userExists(""));

        // Username Short
        testLC.handleRegister("test", "1234");
        assertFalse(testDB.userExists("test"));

        // Password Short
        testLC.handleRegister("testUser", "123");
        assertFalse(testDB.userExists("testUser"));

        // Register User
        testLC.handleRegister("testUser", "test");
        assertTrue(testDB.userExists("testUser"));

        // User Exists
        testLC.handleRegister("testUser", "test");
        assertTrue(testDB.userExists("testUser"));

    }

//    @Test
//    void testHandleLogin() {
//        LoginController testLC = new LoginController();
//        DatabaseManager testDB = DatabaseManager.getInstance();
//        Stage testStage =  new Stage();
//
//
//
//        assertFalse(testLC.handleLogin("", "", testStage));
//        assertFalse(testLC.handleLogin("testUse", "1234", testStage));
//        assertTrue(testLC.handleLogin("testUser", "1234", testStage));
//
//    }
}