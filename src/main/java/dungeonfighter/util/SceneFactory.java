package dungeonfighter.util;

import dungeonfighter.enums.SceneType;
import javafx.scene.Scene;
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
        /* TODO */
        return null;
    }

    private static Scene buildDashboardScene(Stage stage) {
        /* TODO */
        return null;
    }
}
