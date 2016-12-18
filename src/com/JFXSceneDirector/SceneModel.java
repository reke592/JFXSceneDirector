package com.JFXSceneDirector;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * the model used to manipulate scenes in Director
 * @see com.JFXSceneDirector.Director com.JFXSceneDirector.Director
 * */
class SceneModel implements Model {
    private String win_title;
    private URL fxml_url;

    SceneModel(URL fxml_url, String win_title) {
        this.fxml_url = fxml_url;
        this.win_title = win_title;
    }

    @Override
    public String getWinTitle() {
        return this.win_title;
    }

    @Override
    public Parent load() {
        try {
            FXMLLoader loader = new FXMLLoader(fxml_url);
            return loader.load();
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
