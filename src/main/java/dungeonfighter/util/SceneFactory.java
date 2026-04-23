package dungeonfighter.util;

import dungeonfighter.DatabaseManager;
import dungeonfighter.controller.LoginController;
import dungeonfighter.enums.SceneType;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
            case LOGIN -> new LoginController().buildLoginScene(stage);
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

    private static Scene buildDashboardScene(Stage stage) {
        DatabaseManager db = DatabaseManager.getInstance();
        /* TODO */
        return null;

    }
}
