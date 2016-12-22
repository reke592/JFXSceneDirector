package sample;

import com.JFXSceneDirector.Director;
import com.JFXSceneDirector.SceneContext;
import sample.model.NewUser;

import java.net.URL;

public class SceneController extends Director {

    // Director initializer
    {
        add("sample", "sample/fxml/sample.fxml", "Form #1");
        add("sample2", "sample/fxml/sample2.fxml", "Form #2");
        add("regForm1", "sample/fxml/page1.fxml", "Sample Registration form 1");
        add("regForm2", "sample/fxml/page2.fxml", "Sample Registration form 2");

        start("sample");

    }

}
