package sample;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        new SceneController().init(primaryStage);
    }

    @Override
    public void stop() throws Exception {
        SceneController.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
