package dungeonfighter;
import dungeonfighter.enums.SceneType;
import dungeonfighter.util.SceneFactory;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    private DatabaseManager db;

    @Override
    public void start(Stage stage) {

        stage.setTitle("Dungeons Fighter");
        stage.setScene(SceneFactory.create(SceneType.LOGIN, stage));

        //TODO - Change LOGIN to MAIN if you want to test without login //
        stage.show();
    }

    @Override
    public void stop(){
        DatabaseManager.getInstance().close();
    }
}
