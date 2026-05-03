package dungeonfighter.controller;

import dungeonfighter.DatabaseManager;
import dungeonfighter.enums.SceneType;
import dungeonfighter.util.SceneFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;

/**
 * Explanation: Controller for the login Screen.
 * Handles user authenticating, registration
 * and user input validation.
 * Grand access to the main menu upon successful validation.
 *
 * @author Tharindu Amarasinghage
 * @since 4/22/26
 */
public class LoginController {
    private static final int SCENE_WIDTH = 1280;
    private static final int SCENE_HEIGHT = 800;

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private ImageView backgroundImage;

    private final DatabaseManager db = DatabaseManager.getInstance();

    public Scene buildLoginScene(Stage stage) {
        try {
            URL fxmlURL = getClass().getResource("/fxml/login.fxml");
            FXMLLoader loader = new FXMLLoader(fxmlURL);
            Parent root = loader.load();
            Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);

            LoginController controller = loader.getController();
            controller.backgroundImage.fitWidthProperty().bind(scene.widthProperty());
            controller.backgroundImage.fitHeightProperty().bind(scene.heightProperty());

            return scene;

        } catch (Exception e) {
            throw new RuntimeException("Failed to load login.fxml" + e);
        }
    }

    @FXML
    public void handleRegister() {

        String userName = usernameTextField.getText().trim();
        String password = passwordField.getText().trim();

        if (userName.isEmpty() || password.isEmpty()) {
            System.out.println("Username/Passoword cannot be empty");
            Alert emptyFields = new Alert(Alert.AlertType.ERROR);
            emptyFields.setTitle("ERROR!!!");
            emptyFields.setContentText("Username?Password cannot be empty.");
            emptyFields.showAndWait();
            return;
        }
        if (userName.length() < 5) {
            System.out.println("Too Short");
            Alert usernameShort = new Alert(Alert.AlertType.ERROR);
            usernameShort.setTitle("ERROR!!!");
            usernameShort.setContentText("Minimum length for username is 5 characters.");
            usernameShort.showAndWait();
            return;
        }

        if (db.userExists(userName)) {
            System.out.println("Username Taken");
            Alert usernameTaken = new Alert(Alert.AlertType.ERROR);
            usernameTaken.setTitle("ERROR!!!");
            usernameTaken.setContentText("Username not available. Use a different username.");
            usernameTaken.showAndWait();
            return;
        }

        if (password.length() < 4) {
            System.out.println("Too short");
            Alert passwordShort = new Alert(Alert.AlertType.ERROR);
            passwordShort.setTitle("ERROR!!!");
            passwordShort.setContentText("Minimum length for password is 4 characters.");
            passwordShort.showAndWait();
            return;
        }

        db.registerUser(userName, password);
        System.out.println("Success");
        Alert regSuccess = new Alert(Alert.AlertType.CONFIRMATION);
        regSuccess.setTitle("SUCCESS!!!");
        regSuccess.setContentText("New user created. Please login to play.");
        regSuccess.showAndWait();
    }

    @FXML
    public void handleLogin() {

        String userName = usernameTextField.getText().trim();
        String password = passwordField.getText().trim();

        if (userName.isEmpty() || password.isEmpty()) {
            System.out.println("Username/Passoword cannot be empty");
            Alert emptyUsernamePassword = new Alert(Alert.AlertType.ERROR);
            emptyUsernamePassword.setTitle("ERROR!!!");
            emptyUsernamePassword.setContentText("Username/Passoword cannot be empty!");
            emptyUsernamePassword.show();
            return;
        }

        if (!db.userExists(userName)) {
            System.out.println("Wrong Username / Register for new user");
            Alert wrongUserName = new Alert(Alert.AlertType.ERROR);
            wrongUserName.setTitle("ERROR!!!");
            wrongUserName.setContentText("Wrong Username / Register for new user");
            wrongUserName.showAndWait();
            return;
        }

        if (db.passwordMatch(userName, password)) {
            DatabaseManager.setCurrentUser(userName);
            Stage stage = (Stage) usernameTextField.getScene().getWindow();
            stage.setScene(SceneFactory.create(SceneType.MAIN, stage));

        }
    }
}


