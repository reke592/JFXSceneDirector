# JFXSceneDirector (beta)
A small library to transform a java class into scene controller.

JAR file: <a href="https://github.com/reke592/JFXSceneDirector/releases/download/v0.1b/jfxscenedirector.jar"> jfxscenedirector.jar (beta)</a><br>
see: <a href="http://reke.16mb.com/article/read/39">documentation</a>


# Usage
Create a java file that will serve as your scene controller.


```
import JFXSceneDirector.Director;

public class SceneController extends Director {

    // Initialize Director
    {
        start("sample");
        add("sample", "sample/fxml/sample.fxml", "Form #1");
        add("sample2", "sample/fxml/sample2.fxml", "Form #2");
    }


}
```

`protected void start(String key)` declares the default scene when you run your application.

`protected void add(String key, String fxml_location, String window_title)` to register your scenes

<i>Parameters:</i><br>
<b>key</b>           : the key you need to supply when using the Director.show() method<br>
<b>fxml_location</b> : a String value to be supplied in the ClassLoader.getResource() function<br>
<b>window_title</b>  : the value to be supplied in Stage.setTitle() function<br>


#Initialize the SceneController in your application start method.

```
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
```

#Use the SceneController to load your scenes.

```
import JFXSceneDirector.SceneContext;
import javafx.fxml.FXML;
import sample.SceneController;

public class SampleController {

    @FXML public void nextScene() {
        SceneController.show("sample2", new SceneContext(new String[] { "test1", "test2", "test3" }));
    }
}
```

```
public void show(String key);
public void show(String key, SceneContext context);
```
<i>Parameters:</i><br>
<b>key</b>     : the key you declare when you add the scene in SceneController<br>
<b>context</b> : the data you want to pass after switching to the target scene<br>


you can access the context by calling the method `SceneController.getContext();`

#Note:
The return value of SceneController.getContext() is an Object, so you need to cast it into your desired class.


#How about bindings?
you can implement the Initializable interface into your controller, and set all bindings in the `initialize()` method.


Example:

```
public class SampleRegController implements Initializable {
    @FXML private TextField tfLastName;
    @FXML private TextField tfFirstName;
    @FXML private TextField tfMiddleName;
    @FXML private DatePicker dpBirthDate;
    @FXML private TextField tfUsername;
    @FXML private PasswordField pfPassword;
    @FXML private PasswordField pfConfirm;

    private static NewUser data;

    @FXML public void showNext() {
        SceneController.show("regForm2");
    }

    @FXML public void showPrev() {
        SceneController.show("regForm1");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(data == null)
            data = (NewUser) SceneController.getContext();

        tfFirstName.textProperty().bindBidirectional(data.firstNameProperty);
        // more bindings between current loaded fxml file...
    }

}
```

#What if the fxml file is not yet loaded?
In some cases you want to create multiple fxml files for your business/application logic. This library provides a utility to bind properties between static context and unloaded fxml file nodes. This enable us to <strong>use a single controller into multiple fxml source files</strong>. It is all possible by automatically binding and unbinding those properties when calling the initialize() method.

Example:
```
public class SampleRegController implements Initializable {
    
    // ...
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(data == null)
            data = (NewUser) SceneController.getContext();

        SceneController.createBindingGroup(new BindingGroup("Registration")
            // page1.fxml
            .add(tfFirstName, "textProperty", data.fisrtNameProperty(), Bind.biDirectional)
            .add(tfMiddleName, "textProperty", data.middleNameProperty(), Bind.biDirectional)
            .add(tfLastName, "textProperty", data.lastNameProperty(), Bind.biDirectional)
            .add(dpBirthDate, "valueProperty", data.birthdateProperty(), Bind.biDirectional)

            // page2.fxml
            .add(tfUsername, "textProperty", data.userNameProperty(), Bind.biDirectional)
            .add(pfPassword, "textProperty", data.passwordProperty(), Bind.biDirectional)
            .add(pfConfirm, "textProperty", data.confirmPasswordProperty(), Bind.biDirectional)
        );
    }
}
```

`
public BindingGroup add(Node node, String propGetMethodName, Property other, BindingFunction binding)
`

<i>Parameters:</i><br>
<b>node</b>              : the target node that has the property to bind with<br>
<b>propGetMethodName</b> : the name of the getter function in node to get the property<br>
<b>other</b>             : the target property to bind with node property<br>
<b>binding</b>           : a static function provided by JFXSceneDirector.util.Bind class<br>
