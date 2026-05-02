package dungeonfighter.controller;

import dungeonfighter.DatabaseManager;
import dungeonfighter.enums.ArenaType;
import dungeonfighter.enums.SceneType;
import dungeonfighter.util.SceneFactory;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Objects;
import java.util.Optional;

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

    public Scene buildMainScene(Stage stage) {
        Label mainSceneLabel = new Label("MAIN MENU");
        mainSceneLabel.setStyle("-fx-font-size: 40px; -fx-font-weight: bold; -fx-alignment: center; -fx-text-fill: white");

        Button playMainMenu = new Button("PLAY");
        //TODO
        playMainMenu.setOnAction(e -> stage.setScene(SceneFactory.create(SceneType.BATTLE, stage, selectedArena)));
        //TODO enable arena type selection//

        Button buildDeckMainMenu = new Button("BUILD DECK");
        //TODO
        buildDeckMainMenu.setOnAction(e -> stage.setScene(SceneFactory.create(SceneType.DECK, stage)));

        Button myCardsMainMenu = new Button("MY CARDS");
        //TODO

        Button scoresMainMenu = new Button("SCORES");
        //TODO

        Button logoutMainMenu = new Button("LOGOUT");

        Button fireArena = new Button("FIRE");
        fireArena.setOnAction(e->
            selectedArena = ArenaType.FIRE);

        Button iceArena = new Button("ICE");
        iceArena.setOnAction(e->
                selectedArena = ArenaType.ICE);

        Button wildernessArena = new Button("WILDERNESS");
        wildernessArena.setOnAction(e->
                selectedArena = ArenaType.JUNGLE);

        logoutMainMenu.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm");
            alert.setContentText("Are you sure you want to end?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                stage.setScene(SceneFactory.create(SceneType.LOGIN, stage));
            }
        });


        HBox mainMenuTitle = new HBox(mainSceneLabel);
        mainMenuTitle.setAlignment(Pos.TOP_CENTER);

        VBox vBoxLeft = new VBox();
        vBoxLeft.getChildren().addAll(fireArena, iceArena,wildernessArena ,playMainMenu,
                buildDeckMainMenu,
                myCardsMainMenu,
                scoresMainMenu,
                logoutMainMenu);

        vBoxLeft.setSpacing(30);
        VBox.setMargin(vBoxLeft, new Insets(10, 0, 0,0));
        HBox.setMargin(vBoxLeft, new Insets(10, 0,0, 0));
        vBoxLeft.setAlignment(Pos.CENTER_LEFT);

        VBox vBoxRight = new VBox();
        vBoxRight.setAlignment(Pos.TOP_CENTER);
        Label highScore = new Label("High Score");
        highScore.setStyle("-fx-font-size: 40px; -fx-font-weight: bold;" +
        " -fx-alignment: center; -fx-text-fill: white");
        vBoxRight.getChildren().addAll(highScore);

        for(String scoreList : db.getTopTenScores()){
            Label scoreString = new Label(scoreList);
            scoreString.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;" +
                    " -fx-alignment: center; -fx-text-fill: white");
            vBoxRight.getChildren().addAll(scoreString);
        }

//        HBox score = new HBox();
//        score.setAlignment(Pos.CENTER);
//        score.getChildren().add(highScore);

        HBox secondContainer = new HBox();
        secondContainer.setSpacing(200);
        secondContainer.setAlignment(Pos.CENTER);
        secondContainer.getChildren().addAll(vBoxLeft, vBoxRight);

        // Main Container
        VBox mainVBox = new VBox();
        mainVBox.setAlignment(Pos.TOP_CENTER);
        VBox.setMargin(mainVBox, new Insets(100, 50, 50, 50));
        mainVBox.setSpacing(100);

        // Background image without the linter error in setStyle
        String loginImagePath = Objects.requireNonNull
                        (SceneFactory.class.getResource("/LoginPageBG.png"))
                .toExternalForm();

        mainVBox.setStyle("-fx-background-image: url('" + loginImagePath + "');" +
                "-fx-background-size: cover;" +
                "-fx-background-position: center;");

//        mainVBox.getChildren().addAll(mainMenuTitle, score, secondContainer);
        mainVBox.getChildren().addAll(mainMenuTitle, secondContainer);
        VBox.setMargin(mainMenuTitle, new Insets(80, 0, 0, 0));

        /* TODO */
        return new Scene(mainVBox, SCENE_WIDTH, SCENE_HEIGHT);
    }

}
