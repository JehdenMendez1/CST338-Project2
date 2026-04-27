package dungeonfighter.controller;

import dungeonfighter.DatabaseManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class LoginControllerTest {


    @AfterEach
    void tearDown() {
        DatabaseManager db = DatabaseManager.getInstance();
        db.userRemove("testUser");
        db.userRemove("test");
    }

    @Test
    void testHandleRegister() {

        LoginController controller = new LoginController();
        DatabaseManager db = DatabaseManager.getInstance();

        db.userRemove("testUser");
        db.userRemove("test");

        controller.handleRegister("", "");
        assertFalse(db.userExists(""));

        controller.handleRegister("test", "");
        assertFalse(db.userExists("test"));

        controller.handleRegister("test", "1234");
        assertFalse(db.userExists("test"));

        controller.handleRegister("testUser", "123");
        assertFalse(db.userExists("testUser"));

        controller.handleRegister("testUser", "1234");
        assertTrue(db.userExists("testUser"));
    }
}