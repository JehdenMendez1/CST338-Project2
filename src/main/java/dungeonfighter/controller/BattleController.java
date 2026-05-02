package dungeonfighter.controller;

import dungeonfighter.enums.ArenaType;
import dungeonfighter.enums.SceneType;
import dungeonfighter.util.SceneFactory;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Optional;

/**
 * @author jehdenmendez
 * @since 4/25/26
 */

public class BattleController {
    private static final int SCENE_WIDTH = 1280;
    private static final int SCENE_HEIGHT = 800;


    public Scene buildBattleScene(Stage stage, ArenaType arenaType) {


        Label titleLabel = new Label("Battle - " + arenaType.getDungeonName());
        titleLabel.setStyle(arenaType.getFontColor());
        //titleLabel.setStyle("-fx-font-size: 40px; -fx-font-weight: bold; -fx-alignment: center; -fx-text-fill: black ");
        titleLabel.setAlignment(Pos.TOP_CENTER);

        Button endBattle = new Button("End");
        endBattle.setAlignment(Pos.BOTTOM_RIGHT);

        endBattle.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm");
            alert.setContentText("Are you sure you want to end?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                stage.setScene(SceneFactory.create(SceneType.MAIN, stage));
            }
        });

        ProgressBar opponentHealth = new ProgressBar(1.0);
        Button button1 = new Button("Current Card");
        Button button2 = new Button("opp.card");
        Button button3 = new Button("opp.card");
        Button button4 = new Button("opp.card");

        ProgressBar playerHealth = new ProgressBar(1.0);
        Button button5 = new Button("Current Card");
        Button button6 = new Button("opp.card");
        Button button7 = new Button("opp.card");
        Button button8 = new Button("opp.card");

        HBox opponent = new HBox();
        opponent.getChildren().addAll(opponentHealth, button1, button2, button3, button4);
        opponent.setAlignment(Pos.CENTER);
        opponent.setSpacing(10);

        HBox player = new HBox();
        player.getChildren().addAll(playerHealth, button5, button6, button7, button8);
        player.setAlignment(Pos.CENTER);
        player.setSpacing(10);

        VBox mainVbox = new VBox();
        mainVbox.getChildren().addAll(titleLabel, opponent, player, endBattle);
        mainVbox.setAlignment(Pos.TOP_CENTER);

        mainVbox.setStyle("-fx-background-image: url('" + arenaType.getImageBGPath() + "');" +
                "-fx-background-size: cover;" +
                "-fx-background-position: center;");
        return new Scene(mainVbox, SCENE_WIDTH, SCENE_HEIGHT);
    }
}