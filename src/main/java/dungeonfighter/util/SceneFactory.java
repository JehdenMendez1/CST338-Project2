package dungeonfighter.util;

import dungeonfighter.DatabaseManager;
import dungeonfighter.controller.BattleController;
import dungeonfighter.controller.DeckBuildController;
import dungeonfighter.controller.LoginController;
import dungeonfighter.controller.MainMenuController;
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
            case MAIN -> new MainMenuController().buildMainScene(stage);
            case LOGIN -> new LoginController().buildLoginScene(stage);
            case DASHBOARD -> buildDashboardScene(stage);
            case DECK -> new DeckBuildController().buildDeckBuildScene(stage);
            case BATTLE -> new BattleController().buildBattleScene(stage);
        };
    }

    private static Scene buildDashboardScene(Stage stage) {
        DatabaseManager db = DatabaseManager.getInstance();
        /* TODO */
        return null;

    }
}
