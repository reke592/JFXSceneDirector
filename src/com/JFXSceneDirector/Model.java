package com.JFXSceneDirector;


import javafx.scene.Parent;

/**
 * Interface for scene model
 * */
public interface Model {
    Parent load();
    String getWinTitle();
}
