package com.JFXSceneDirector;


import javafx.scene.Parent;

/**
 * Interface for scene model
 * */
public interface ISceneModel {
    Parent load();
    String getWinTitle();
}
