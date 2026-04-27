package dungeonfighter.controller;

import dungeonfighter.DatabaseManager;
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
 * @since 4/22/26
 */
public class MainMenuController {
    private static final int SCENE_WIDTH = 1200;
    private static final int SCENE_HEIGHT = 1000;

    private final DatabaseManager db = DatabaseManager.getInstance();

    public Scene buildMainScene(Stage stage) {

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
