package dungeonfighter.util;

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

    public static Scene create (SceneType type, Stage stage){
        return switch (type) {
            case MAIN -> buildMainScene(stage);
            case LOGIN -> buildLoginScene (stage);
            case DASHBOARD -> buildDashboardScene(stage);

        };
    }

    private static Scene buildMainScene(Stage stage) {
        /* TODO */
        return null;
    }

    private static Scene buildLoginScene(Stage stage) {

        Label titleLabel = new Label("Welcome to the Dungeons Fighter");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-alignment: center;");

        Button loginButton = new Button("LOGIN");

        loginButton.setOnAction(e ->
                stage.setScene(create(SceneType.MAIN, stage))
        );

        VBox layout = new VBox(16, titleLabel, loginButton);
        layout.setAlignment(Pos.CENTER);




        Button registerButton = new Button("REGISTER");
        /* TODO */
        return new Scene(layout, 800, 600);
    }

    private static Scene buildDashboardScene(Stage stage) {
        /* TODO */
        return null;

    }
}
