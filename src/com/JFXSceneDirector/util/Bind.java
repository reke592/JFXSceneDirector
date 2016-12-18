package com.JFXSceneDirector.util;

import javafx.beans.property.Property;
import javafx.scene.Node;
import java.lang.reflect.InvocationTargetException;

public class Bind {

//    /**
//     * This function enables bidirectional binding in one controller to multiple fxml source file
//     * */
//    @SuppressWarnings("unchecked")
//    public static Property<?> biDirectional(final Node n, final String propGetMethodName, final Property prop) {
//        Property<?> nodeProp = null;
//        if(n != null) {
//            try {
//                nodeProp = (Property<?>) n.getClass().getMethod(propGetMethodName).invoke(n);
//                nodeProp.bindBidirectional(prop);
//            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
//                e.printStackTrace();
//            }
//        }
//        return nodeProp;
//    }

    /**
     * This function enables bidirectional binding in one controller to multiple fxml source file
     * */
    @SuppressWarnings("unchecked")
    public static BindingFunction biDirectional = new BindingFunction() {
        @Override
        public Property<?> bind(Node node, String propGetMethodName, Property other) {
            if(node != null) {
                try {
                    Property<?> nodeProp = (Property<?>) node.getClass().getMethod(propGetMethodName).invoke(node);
                    nodeProp.bindBidirectional(other);
                    return nodeProp;
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    };


    /**
     * This function enables unidirectional binding in one controller to multiple fxml source file
     * */
    @SuppressWarnings("unchecked")
    public static BindingFunction uniDirectional = new BindingFunction() {
        @Override
        public Property<?> bind(Node node, String propGetMethodName, Property other) {
            if(node != null) {
                try {
                    Property<?> nodeProp = (Property<?>) node.getClass().getMethod(propGetMethodName).invoke(node);
                    nodeProp.bind(other);
                    return nodeProp;
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    };


}
