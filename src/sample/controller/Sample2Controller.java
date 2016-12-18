package sample.controller;

import javafx.fxml.FXML;
import sample.SceneController;

public class Sample2Controller {
    @FXML public void test2() {
        for (String s : (String[]) SceneController.getContext()) {
            System.out.println(s);
        }
        SceneController.show("sample");
    }
}
