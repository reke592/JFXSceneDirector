package com.JFXSceneDirector.util;

import javafx.beans.property.Property;
import javafx.scene.Node;

@FunctionalInterface
public interface BindingFunction {
    Property<?> bind(Node node, String propGetMethodName, Property other);
}
