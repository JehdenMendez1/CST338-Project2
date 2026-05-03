package dungeonfighter.controller;

import dungeonfighter.DatabaseManager;
import dungeonfighter.enums.ArenaType;
import dungeonfighter.enums.SceneType;
import dungeonfighter.util.SceneFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import java.io.IOException;

/**
 * Explanation:
 *
 * @author Tharindu Amarasinghage
 * @since 4/22/26
 */
public class MainMenuController {
    private static final int SCENE_WIDTH = 1280;
    private static final int SCENE_HEIGHT = 800;
    private static final String USERNAME_PROMPT = "USERNAME";
    private static final String PASSWORD_PROMPT = "PASSWORD";
    private ArenaType selectedArena = ArenaType.FIRE;



    private final DatabaseManager db = DatabaseManager.getInstance();

    @FXML
    private Button buildDeckMainMenu;

    @FXML
    private Button myCardsMainMenu;

    @FXML
    private Button scoresMainMenu;

    @FXML
    private Button logoutMainMenu;

    @FXML
    private Button deleteUserMainMenu;

    @FXML
    private Button iceArena;

    @FXML
    private Button fireArena;

    @FXML
    private Button wildernessArena;

    @FXML
    private Button playMainMenu;

    @FXML
    private ListView<String> highScoreListName;

    @FXML
    private ListView<String> highScoreListScore;

    @FXML
    public void initialize() {
        loadHighScores();

        playMainMenu.setOnAction(e -> playMainMenu.getScene().getWindow().hide());

        buildDeckMainMenu.setOnAction(e -> {
            Stage stage = (Stage) buildDeckMainMenu.getScene().getWindow();
            stage.setScene(SceneFactory.create(SceneType.DECK, stage));
        });

        logoutMainMenu.setOnAction(e -> {
            Stage stage = (Stage) logoutMainMenu.getScene().getWindow();
            stage.setScene(SceneFactory.create(SceneType.LOGIN, stage));
        });

        fireArena.setOnAction(e -> selectedArena = ArenaType.FIRE);
        iceArena.setOnAction(e -> selectedArena = ArenaType.ICE);
        wildernessArena.setOnAction(e -> selectedArena = ArenaType.JUNGLE);

        playMainMenu.setOnAction(e -> {
            Stage stage = (Stage) playMainMenu.getScene().getWindow();
            stage.setScene(SceneFactory.create(SceneType.BATTLE, stage, selectedArena));
        });

        deleteUserMainMenu.setOnAction(e -> {
            String user = DatabaseManager.getCurrentUser();
            db.userRemove(user);
            Stage stage = (Stage) logoutMainMenu.getScene().getWindow();
            stage.setScene(SceneFactory.create(SceneType.LOGIN, stage));
        });
    }

    public static Scene buildMainScene(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneFactory.class.getResource("/fxml/mainmenu.fxml"));
            Parent root = loader.load();
            return new Scene(root, 1280, 800);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load mainmenu.fxml", e);
        }
    }

    private void loadHighScores() {
        highScoreListScore .getItems().clear();
        highScoreListName.getItems().clear();

        for(String[] row: db.getTopTenScores()){
            highScoreListName.getItems().add(row[0]);
            highScoreListScore.getItems().add(row[1]);

        }
    }

}
