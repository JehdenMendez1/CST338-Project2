package dungeonfighter.controller;

import dungeonfighter.DatabaseManager;
import dungeonfighter.enums.SceneType;
import dungeonfighter.util.SceneFactory;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * Explanation:
 *
 * @author Tharindu Amarasinghage
 * @since 4/22/26
 */
public class LoginController {
    private static final int SCENE_WIDTH = 1200;
    private static final int SCENE_HEIGHT = 1000;
    private static final String USERNAME_PROMPT = "USERNAME";
    private static final String PASSWORD_PROMPT = "PASSWORD";

    private final DatabaseManager db = DatabaseManager.getInstance();

    public Scene buildLoginScene(Stage stage) {

        //DatabaseManager db = DatabaseManager.getInstance();
        LoginController loginController = new LoginController();

        Label titleLabel = new Label("Welcome to the Dungeons Fighter");
        titleLabel.setStyle("-fx-font-size: 40px; -fx-font-weight: bold; -fx-alignment: center; -fx-text-fill: white ");
        titleLabel.setAlignment(Pos.TOP_CENTER);

        // Username Text Filed
        TextField userName = new TextField();
        userName.setPromptText(USERNAME_PROMPT);
        userName.setPrefWidth(200);
        userName.setMaxWidth(200);
        userName.setPrefHeight(30);

        // Password Text Field
        TextField password = new PasswordField();
        password.setPromptText(PASSWORD_PROMPT);
        password.setPrefWidth(200);
        password.setMaxWidth(200);
        password.setPrefHeight(30);

        // Login Button
        Button loginButton = new Button("LOGIN");

        loginButton.setOnAction(e ->
                loginController.handleLogin(
                        userName.getText().trim(),
                        password.getText().trim(),
                        stage
                )
        );

        // Register Button
        Button registerButton = new Button("REGISTER");

        registerButton.setOnAction(e -> loginController.handleRegister(
                        userName.getText().trim(), password.getText().trim()
                )
        );

        // GIF Animation on the login page
        Image LoginGIF = new Image(Objects.requireNonNull(
                SceneFactory.class.getResource(
                        "/LoginPageGif.gif")).toExternalForm());
        ImageView gifView = new ImageView(LoginGIF);
        gifView.setPreserveRatio(true);

        // Login Page Arrangement
        VBox mainVBOX = new VBox();
        mainVBOX.setPadding(new Insets(100));
        mainVBOX.setSpacing(50);

        VBox vBoxLogin = new VBox();
        vBoxLogin.setPadding(new Insets(10));
        vBoxLogin.setSpacing(20);
        vBoxLogin.setAlignment(Pos.BOTTOM_CENTER);
        vBoxLogin.getChildren().addAll( userName, password, loginButton, registerButton, gifView);

        HBox gameTitle = new HBox();
        gameTitle.setAlignment(Pos.TOP_CENTER);
        gameTitle.getChildren().addAll(titleLabel);

        // Background image without the linter error in setStyle
        String loginImagePath = Objects.requireNonNull
                        (SceneFactory.class.getResource("/LoginPageBG.jpeg"))
                .toExternalForm();


        mainVBOX.setStyle("-fx-background-image: url('" + loginImagePath + "');" +
                "-fx-background-size: cover;" +
                "-fx-background-position: center;");

        mainVBOX.getChildren().addAll(gameTitle, vBoxLogin);
        mainVBOX.setAlignment(Pos.TOP_CENTER);
        mainVBOX.setPadding(new Insets(20));
        VBox.setMargin(gameTitle, new Insets( 100, 0, 0, 0));


        return new Scene(mainVBOX, SCENE_WIDTH, SCENE_HEIGHT);
    }
    public void handleRegister(String userName, String password){

        if(userName.isEmpty() || password.isEmpty()) {
            System.out.println("Username/Passoword cannot be empty");
            Alert emptyFields = new Alert(Alert.AlertType.ERROR);
            emptyFields.setTitle("ERROR!!!");
            emptyFields.setContentText("Username?Password cannot be empty.");
            emptyFields.showAndWait();
            return;
        }
        if(userName.length() < 5){
            System.out.println("Too Short");
            Alert usernameShort= new Alert(Alert.AlertType.ERROR);
            usernameShort.setTitle("ERROR!!!");
            usernameShort.setContentText("Minimum length for username is 5 characters.");
            usernameShort.showAndWait();
            return;
        }

        if(db.userExists(userName)){
            System.out.println("Username Taken");
            Alert usernameTaken = new Alert(Alert.AlertType.ERROR);
            usernameTaken.setTitle("ERROR!!!");
            usernameTaken.setContentText("Username not available. Use a different username.");
            usernameTaken.showAndWait();
            return;
        }

        if(password.length() < 4){
            System.out.println("Too short");
            Alert passwordShort = new Alert(Alert.AlertType.ERROR);
            passwordShort.setTitle("ERROR!!!");
            passwordShort.setContentText("Minimum length for password is 4 characters.");
            passwordShort.showAndWait();
            return;
        }

        db.registerUser(userName, password);
        System.out.println("Success");
        Alert regSuccess= new Alert(Alert.AlertType.CONFIRMATION);
        regSuccess.setTitle("SUCCESS!!!");
        regSuccess.setContentText("New user created. Please login to play.");
        regSuccess.showAndWait();
    }

    public void handleLogin(String userName, String password, Stage stage){

        if(userName.isEmpty() || password.isEmpty()) {
            System.out.println("Username/Passoword cannot be empty");
            Alert emptyUsernamePassword = new Alert(Alert.AlertType.ERROR);
            emptyUsernamePassword.setTitle("ERROR!!!");
            emptyUsernamePassword.setContentText("Username/Passoword cannot be empty!");
            emptyUsernamePassword.show();
            return;
        }

        if(!db.userExists(userName)){
            System.out.println("Wrong Username / Register for new user");
            Alert wrongUserName = new Alert(Alert.AlertType.ERROR);
            wrongUserName.setTitle("ERROR!!!");
            wrongUserName.setContentText("Wrong Username / Register for new user");
            wrongUserName.showAndWait();
            return;

        }

        if(db.passwordMatch(userName, password)){
                stage.setScene(SceneFactory.create(SceneType.MAIN, stage));
            }
        }

    }


