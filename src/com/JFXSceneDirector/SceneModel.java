package com.JFXSceneDirector;

import com.JFXSceneDirector.exceptions.FXMLFileNotFoundException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 * the model used to manipulate scenes in Director
 * @see com.JFXSceneDirector.Director com.JFXSceneDirector.Director
 * */
class SceneModel implements ISceneModel {
    private String win_title;
    private String fxml_source;
    private static ClassLoader classLoader;

    static void setClassLoader(ClassLoader source) {
        classLoader = source;
    }

    SceneModel(String fxml_source, String win_title) throws FXMLFileNotFoundException {
        if(classLoader.getResource(fxml_source) == null)
            throw new FXMLFileNotFoundException("The classloader cannot find the fxml resource " + fxml_source);

        this.fxml_source = fxml_source;
        this.win_title = win_title;
    }

    @Override
    public String getWinTitle() {
        return this.win_title;
    }

    @Override
    public Parent load() {
        try {
            FXMLLoader loader = new FXMLLoader(classLoader.getResource(this.fxml_source));
            return loader.load();
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
