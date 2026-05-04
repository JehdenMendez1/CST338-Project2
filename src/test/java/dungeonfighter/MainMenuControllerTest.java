package dungeonfighter;

import dungeonfighter.enums.SceneType;
import dungeonfighter.util.SceneFactory;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxToolkit;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Explanation: Test for main menu controller
 *
 * @author Tharindu Amarasinghage
 * @since 5/3/26
 */
class MainMenuControllerTest {

    private Stage stage;

    @BeforeAll
    static void bootToolkit() throws Exception {
        FxToolkit.registerPrimaryStage();
    }

    @BeforeEach
    void setup() throws Exception {
        System.setProperty("aap.db.url", "jdbc.sqlite::memory:");
        DatabaseManager.resetForTesting();

        stage = FxToolkit.registerPrimaryStage();
    }

    @AfterEach
    void teardown() {
        DatabaseManager.resetForTesting();;
    }

    @Test
    void mainMenuSceneLoads() throws Exception {
        Scene scene = FxToolkit.setupScene(() ->
            SceneFactory.create(SceneType.MAIN, stage)
        );
        assertNotNull(scene);
        assertNotNull(scene.getRoot());
    }

}