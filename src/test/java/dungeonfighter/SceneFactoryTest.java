package dungeonfighter;

import dungeonfighter.enums.SceneType;
import dungeonfighter.util.SceneFactory;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxToolkit;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Explanation: Test for SceneFactory
 *
 * @author Tharindu Amarasinghage
 * @since 5/3/26
 */
class SceneFactoryTest {

        Stage stage;

        @BeforeAll
        static void bootToolkit() throws Exception {
            FxToolkit.registerPrimaryStage(); // starts JavaFX
        }

        @BeforeEach
        void setup() throws Exception {
            System.setProperty("app.db.url", "jdbc:sqlite::memory:");
            DatabaseManager.resetForTesting();

            stage = FxToolkit.registerPrimaryStage();
        }

        @Test
        void mainSceneLoads() throws Exception {
            Scene scene = FxToolkit.setupScene(() ->
                    SceneFactory.create(SceneType.MAIN, stage)
            );

            assertNotNull(scene);
            assertNotNull(scene.getRoot());
        }

}