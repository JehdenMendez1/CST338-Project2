package dungeonfighter.UI;

import dungeonfighter.game.Card;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.util.Objects;

/**
 * Explanation: Provides reusable card design and
 * outputs based on the data on Card class
 *
 * @author Tharindu Amarasinghage
 * @since 4/29/26
 */

public class CardDesign extends VBox {

    private final Label nameLabel = new Label();
    private final ImageView imageView = new ImageView();
    private final ProgressBar healthBar = new ProgressBar();
    private final Label healthLabel = new Label();
    private final Label abilityLabel = new Label();
    private final Label abilityDescription = new Label();


    public CardDesign() {
        setSpacing(10);
        setAlignment(Pos.CENTER);
        setPrefSize(150, 220);

        setStyle("""
            -fx-background-color: #2b2b2b;
            -fx-border-color: gold;
            -fx-border-width: 2;
            -fx-padding: 10;
            -fx-background-radius: 10;
        """);

        imageView.setFitWidth(100);
        imageView.setFitHeight(100);

        healthBar.setPrefWidth(120);

        nameLabel.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");
        healthLabel.setStyle("-fx-text-fill: green; -fx-font-size: 10;");
        abilityLabel.setStyle("-fx-text-fill: darkgray; -fx-font-size: 10;");
        abilityDescription.setStyle("-fx-text-fill: lightgray; -fx-font-size: 10;");

        getChildren().addAll(
                nameLabel,
                imageView,
                healthBar,
                healthLabel,
                abilityLabel,
                abilityDescription);
    }

    public void setCardData(Card card) {
        nameLabel.setText(card.getName());
        abilityLabel.setText(card.getAbility());
        abilityDescription.setText(card.getAbilityDescription());
        healthBar.setProgress(card.getHealthProgress());
        healthLabel.setText(card.getHealth() + " / " + card.getMaxHealth());

        try {
            imageView.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(card.getImageAsset()))));
        } catch (Exception e) {
            System.out.println("Image not found: " + card.getImageAsset());
        }
    }
}