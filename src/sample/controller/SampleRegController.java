package sample.controller;

import com.JFXSceneDirector.util.Bind;
import com.JFXSceneDirector.util.BindingGroup;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.SceneController;
import sample.model.NewUser;

import java.net.URL;
import java.util.ResourceBundle;


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

    @FXML public void submit() {
        data = null;
    }

    @FXML public void cancel() {

    }

    @FXML public void showPrev() {
        SceneController.show("regForm1");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(data == null)
            data = (NewUser) SceneController.getContext();

        System.out.println(data);
        // add bindings to multiple fxml file
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
