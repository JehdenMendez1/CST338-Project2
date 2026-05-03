package dungeonfighter.util;

import dungeonfighter.DatabaseManager;
import dungeonfighter.controller.*;
import dungeonfighter.enums.ArenaType;
import dungeonfighter.enums.SceneType;
import javafx.scene.Scene;

import javafx.stage.Stage;


/**
 * Explanation: SceneFactory is responsible for creating
 * JavaFX scenes.
 * Battle scene use the arena type as a extra parameter to change between
 * Arenas
 * @author Tharindu Amarasinghage
 * @since 4/20/26
 */
public class SceneFactory {

    public static Scene create (SceneType type, Stage stage){
        return switch (type) {
            case MAIN -> new MainMenuController().buildMainScene(stage);
            case LOGIN -> new LoginController().buildLoginScene(stage);
            case DECK -> new DeckBuildController().buildDeckBuildScene(stage);
            case SCORE -> new UserScoreController().buildUserScoreScene(stage);
            case BATTLE -> throw new IllegalArgumentException("Battle needs ArenaType");
        };
    }

    public static Scene create (SceneType type, Stage stage, ArenaType arenaType){
        return switch(type){
            case BATTLE -> new BattleController().buildBattleScene(stage, arenaType);
            default -> create(type, stage);
        };
    }

}
