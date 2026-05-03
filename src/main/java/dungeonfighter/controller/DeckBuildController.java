package dungeonfighter.controller;

import dungeonfighter.enums.SceneType;
import dungeonfighter.util.SceneFactory;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @author jehdenmendez
 * @since 04/24/26
 */

public class DeckBuildController {
    private static final int SCENE_WIDTH = 1280;
    private static final int SCENE_HEIGHT = 800;

    public Scene buildDeckBuildScene(Stage stage) {

        ImageView background = new ImageView(
                new Image(getClass().getResourceAsStream("/MainMenuBG.jpeg"))
        );
        background.setFitWidth(SCENE_WIDTH);
        background.setFitHeight(SCENE_HEIGHT);

        Label titleLabel = new Label("Build Your Deck");
        titleLabel.setStyle("-fx-font-size: 40px; -fx-font-weight: bold; -fx-alignment: center; -fx-text-fill: black; -fx-font-family: Courier New");
        titleLabel.setAlignment(Pos.TOP_CENTER);

        Button card1 = new Button("Card");
        Button card2 = new Button("Card");
        Button card3 = new Button("Card");
        Button card4 = new Button("Card");
        Button card5 = new Button("Card");
        Button card6 = new Button("Card");
        Button card7 = new Button("Card");
        Button card8 = new Button("Card");

        card1.setStyle("-fx-pref-height: 160; -fx-pref-width: 110");
        card2.setStyle("-fx-pref-height: 160; -fx-pref-width: 110");
        card3.setStyle("-fx-pref-height: 160; -fx-pref-width: 110");
        card4.setStyle("-fx-pref-height: 160; -fx-pref-width: 110");
        card5.setStyle("-fx-pref-height: 160; -fx-pref-width: 110");
        card6.setStyle("-fx-pref-height: 160; -fx-pref-width: 110");
        card7.setStyle("-fx-pref-height: 160; -fx-pref-width: 110");
        card8.setStyle("-fx-pref-height: 160; -fx-pref-width: 110");

        Button toPrevious = new Button("Return To Main Menu");
        toPrevious.setAlignment(Pos.BOTTOM_RIGHT);
        toPrevious.setStyle("-fx-pref-height: 35; -fx-pref-width: 180; -fx-font-family: Courier New");

        toPrevious.setOnAction(e->
                stage.setScene(SceneFactory.create(SceneType.MAIN, stage)));

        HBox cardRow1 = new HBox(50);
        cardRow1.setAlignment(Pos.CENTER);
        cardRow1.getChildren().addAll(card1, card2, card3, card4);

        HBox cardRow2 = new HBox(50);
        cardRow2.setAlignment(Pos.CENTER);
        cardRow2.getChildren().addAll(card5, card6, card7, card8);

        HBox toPrevAlign = new HBox();
        toPrevAlign.getChildren().addAll(toPrevious);

        VBox mainVbox = new VBox(45);
        mainVbox.setAlignment(Pos.TOP_CENTER);
        mainVbox.getChildren().addAll(titleLabel, cardRow1, cardRow2, toPrevious);

        StackPane root = new StackPane();
        root.getChildren().addAll(background, mainVbox);
        return new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
    }
}
