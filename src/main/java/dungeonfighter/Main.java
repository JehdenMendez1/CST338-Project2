package dungeonfighter;
import dungeonfighter.enums.SceneType;
import dungeonfighter.util.SceneFactory;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Dungeons Fighter");
        stage.setScene(SceneFactory.create(SceneType.LOGIN, stage));
        stage.show();
    }
}
