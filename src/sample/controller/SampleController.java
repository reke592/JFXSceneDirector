package sample.controller;

import com.JFXSceneDirector.SceneContext;
import javafx.fxml.FXML;
import sample.SceneController;
import sample.model.NewUser;

public class SampleController {

    @FXML public void next1() {
//        SceneController.show("sample2", new SceneContext(new String[] { "test1", "test2", "test3" }));
        SceneController.show("regForm1", new SceneContext(new NewUser()));
    }
}
