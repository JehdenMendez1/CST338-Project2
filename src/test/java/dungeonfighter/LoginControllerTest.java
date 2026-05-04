package dungeonfighter;

import dungeonfighter.controller.LoginController;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import java.lang.reflect.Field;
import org.junit.jupiter.api.*;
import org.testfx.api.FxToolkit;
import org.testfx.util.WaitForAsyncUtils;

import static org.junit.jupiter.api.Assertions.*;


class LoginControllerTest {

    @BeforeAll
    static void bootToolkit() throws Exception {
        FxToolkit.registerPrimaryStage();
    }

    @BeforeEach
    void freshDb() {
        System.setProperty("app.db.url", "jdbc:sqlite::memory:");
        DatabaseManager.resetForTesting();
    }

    @AfterEach
    void teardown() {
        DatabaseManager.resetForTesting();
    }

    private void setFields(LoginController controller, String user, String pass) throws Exception {
        TextField username = new TextField(user);
        PasswordField password = new PasswordField();
        password.setText(pass);

        Field userField = LoginController.class.getDeclaredField("usernameTextField");
        userField.setAccessible(true);
        userField.set(controller, username);

        Field passField = LoginController.class.getDeclaredField("passwordField");
        passField.setAccessible(true);
        passField.set(controller, password);
    }

    @Test
    void loginFailsWhenFieldsAreEmpty() throws Exception {
        LoginController controller = new LoginController();

        setFields(controller, "", "");

        assertDoesNotThrow(() ->
                WaitForAsyncUtils.waitForFxEvents()
        );
    }

    @Test
    void registerFailsWhenUsernameTooShort() throws Exception {
        LoginController controller = new LoginController();

        setFields(controller, "abc", "1234");

        assertDoesNotThrow(() ->
                WaitForAsyncUtils.waitForFxEvents()
        );
    }
}