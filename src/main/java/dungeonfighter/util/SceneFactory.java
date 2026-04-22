package dungeonfighter.util;

import dungeonfighter.DatabaseManager;
import dungeonfighter.enums.SceneType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * Explanation:
 *
 * @author Tharindu Amarasinghage
 * @since 4/20/26
 */
public class SceneFactory {
    private static final int SCENE_WIDTH = 1200;
    private static final int SCENE_HEIGHT = 1000;
    private static final String USERNAME_PROMPT = "USERNAME";
    private static final String PASSWORD_PROMPT = "PASSWORD";


    public static Scene create (SceneType type, Stage stage){
        return switch (type) {
            case MAIN -> buildMainScene(stage);
            case LOGIN -> buildLoginScene (stage);
            case DASHBOARD -> buildDashboardScene(stage);

        };
    }

    private static Scene buildMainScene(Stage stage) {

        DatabaseManager db = DatabaseManager.getInstance();

        Label mainSceneLabel = new Label("MAIN MENU");
        mainSceneLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-alignment: center;");

        Button playMainMenu = new Button("PLAY");
        Button buildDeckMainMenu = new Button("BUILD DECK");
        Button myCardsMainMenu = new Button("MY CARDS");
        Button scoresMainMenu = new Button("SCORES");
        Button logoutMainMenu = new Button("LOGOUT");

        VBox layoutMainMenu = new VBox();
        layoutMainMenu.setSpacing(16);
        layoutMainMenu.getChildren().addAll(mainSceneLabel, playMainMenu,
                buildDeckMainMenu, myCardsMainMenu, scoresMainMenu, logoutMainMenu);

        layoutMainMenu.setAlignment(Pos.CENTER_LEFT);

        /* TODO */
        return new Scene(layoutMainMenu, SCENE_WIDTH, SCENE_HEIGHT);
    }

    private static Scene buildLoginScene(Stage stage) {

        DatabaseManager db = DatabaseManager.getInstance();

        Label titleLabel = new Label("Welcome to the Dungeons Fighter");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-alignment: center;");
        titleLabel.setAlignment(Pos.TOP_CENTER);

        // Username Text Filed
        TextField userName = new TextField();
        userName.setPromptText(USERNAME_PROMPT);
        userName.setPrefWidth(100);



        // Password Text Field
        TextField password = new PasswordField();
        password.setPromptText(PASSWORD_PROMPT);
        password.setPrefWidth(100);


        // Login Button
        Button loginButton = new Button("LOGIN");

        loginButton.setOnAction(e -> {
                    String usernameInput = userName.getText().trim();
                    String passwordInput = password.getText().trim();

                    stage.setScene(create(SceneType.MAIN, stage));
                }
        );

        // Register Button
        Button registerButton = new Button("REGISTER");

        registerButton.setOnAction(e -> {
                    String usernameInput = userName.getText().trim();
                    String passwordInput = password.getText().trim();
                    if(usernameInput.isEmpty() || passwordInput.isEmpty()) {
                        System.out.println("Username/Passoword cannot be empty");
                        //TODO Add a popup notification
                        return;
                    }
                    if(usernameInput.length() < 5){
                        System.out.println("Too Short");
                        //TODO Add a popup notification
                        return;
                    }

                    if(db.userExists(usernameInput)){
                        System.out.println("Username Taken");
                        //TODO Add a popup notification
                        return;
                    }

                    if(passwordInput.length() < 4){
                        System.out.println("Too short");
                        //TODO Add a popup notification
                        return;
                    }

                    db.registerUser(usernameInput, passwordInput);
                    System.out.println("Success");
                    //TODO Add a popup notification
                }
                );
        /* TODO */


        // Login Page Arrangement
        VBox vBoxLeft = new VBox();

        VBox vBoxRight = new VBox();
        vBoxRight.setPadding(new Insets(50));
        vBoxRight.setSpacing(20);
        vBoxRight.getChildren().addAll( userName, password, loginButton, registerButton);

        HBox layout = new HBox();
        layout.getChildren().addAll(vBoxLeft,vBoxRight);


        //layout.getChildren().addAll(titleLabel);
        layout.setPadding(new Insets(50));
        layout.setAlignment(Pos.CENTER);

        VBox mainVBOX = new VBox();
        mainVBOX.setAlignment(Pos.CENTER);
        mainVBOX.setPadding(new Insets(20));
        mainVBOX.getChildren().addAll(titleLabel, layout);





        return new Scene(mainVBOX, SCENE_WIDTH, SCENE_HEIGHT);
    }

    private static Scene buildDashboardScene(Stage stage) {
        DatabaseManager db = DatabaseManager.getInstance();
        /* TODO */
        return null;

    }
}
