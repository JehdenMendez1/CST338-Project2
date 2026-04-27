package dungeonfighter.controller;

import dungeonfighter.DatabaseManager;
import dungeonfighter.enums.SceneType;
import dungeonfighter.util.SceneFactory;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
            //TODO Add a popup notification
            return;
        }
        if(userName.length() < 5){
            System.out.println("Too Short");
            //TODO Add a popup notification
            return;
        }

        if(db.userExists(userName)){
            System.out.println("Username Taken");
            //TODO Add a popup notification
            return;
        }

        if(password.length() < 4){
            System.out.println("Too short");
            //TODO Add a popup notification
            return;
        }

        db.registerUser(userName, password);
        System.out.println("Success");
        //TODO Add a popup notification
    }

    public void handleLogin(String userName, String password, Stage stage){

        if(userName.isEmpty() || password.isEmpty()) {
            System.out.println("Username/Passoword cannot be empty");
            //TODO Add a popup notification
            return;
        }

        if(!db.userExists(userName)){
            System.out.println("Wrong Username / Register for new user");
            return;
            //TODO Add a popup notification
        }

        if(db.passwordMatch(userName, password)){
                stage.setScene(SceneFactory.create(SceneType.MAIN, stage));
            }
        }

        //TODO Add a popup notification
    }


