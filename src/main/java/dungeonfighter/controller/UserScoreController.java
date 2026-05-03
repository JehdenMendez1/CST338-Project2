package dungeonfighter.controller;

import dungeonfighter.DatabaseManager;
import dungeonfighter.enums.SceneType;
import dungeonfighter.util.SceneFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

/**
 * Explanation: Generate the scene to show the users past top 10 scores.
 *
 * @author Tharindu Amarasinghage
 * @since 5/3/26
 */
public class UserScoreController {
    private static final int SCENE_WIDTH = 1280;
    private static final int SCENE_HEIGHT = 800;

    private final DatabaseManager db = DatabaseManager.getInstance();
    private String user = DatabaseManager.getCurrentUser();


    @FXML
    private ListView<String> scoreList;

    @FXML
    private Button backUserScore;

    @FXML
    public void initialize() {
        List<Integer> scores = db.getUserScores(user);
        int num = 1;

        for(int score: scores){
            scoreList.getItems().add(num++ + ". \t " + score);
        }

        backUserScore.setOnAction(e -> {
            Stage stage = (Stage) backUserScore.getScene().getWindow();
            stage.setScene(SceneFactory.create(SceneType.MAIN, stage));
        });

        scoreList.setMouseTransparent(true);
        scoreList.setFocusTraversable(false);
    }

    public static Scene buildUserScoreScene(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneFactory.class.getResource("/fxml/userScore.fxml"));
            Parent root = loader.load();
            return new Scene(root, 1280, 800);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load userScore.fxml", e);
        }
    }
}
