package dungeonfighter.controller;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @author jehdenmendez
 * @since 04/24/26
 */

public class DeckBuildScene {
    private static final int SCENE_WIDTH = 1200;
    private static final int SCENE_HEIGHT = 1000;

    public Scene buildDeckBuildScene(Stage stage) {

        Label titleLabel = new Label("Build Your Deck");
        titleLabel.setStyle("-fx-font-size: 40px; -fx-font-weight: bold; -fx-alignment: center; -fx-text-fill: white ");
        titleLabel.setAlignment(Pos.TOP_CENTER);

        Button cardButton1 = new Button("Card");
        Button cardButton2 = new Button("Card");
        Button cardButton3 = new Button("Card");
        Button cardButton4 = new Button("Card");
        Button cardButton5 = new Button("Card");
        Button cardButton6 = new Button("Card");
        Button cardButton7 = new Button("Card");
        Button cardButton8 = new Button("Card");

        HBox cardRow1 = new HBox(8);
        cardRow1.setAlignment(Pos.CENTER);
        cardRow1.getChildren().addAll(cardButton1, cardButton2, cardButton3, cardButton4);

        HBox cardRow2 = new HBox(8);
        cardRow2.setAlignment(Pos.CENTER);
        cardRow2.getChildren().addAll(cardButton5, cardButton6, cardButton7, cardButton8);

        VBox mainVbox = new VBox(titleLabel, cardRow1, cardRow2);
        mainVbox.setAlignment(Pos.CENTER);

        return new Scene(mainVbox, SCENE_WIDTH, SCENE_HEIGHT);
    }
}
