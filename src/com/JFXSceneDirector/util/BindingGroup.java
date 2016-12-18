package com.JFXSceneDirector.util;

import javafx.beans.property.Property;
import javafx.scene.Node;
import java.util.HashMap;
import java.util.Map;

/**
 * Create new binding group.<br><br>
 * The binding group provides the functionality to declare bindings between nodes in multiple fxml file over a single controller<br>
 * this automatically unbind and bind the properties when declared in <em>initialize()</em> function provided by Initializable inteface.
 * <br><br>
 *     i.e.<br>
 *     you create two fxml file for pagination (form1.fxml , form2.fxml)<br>
 *     and you declare the fxml controller of both files to point at your controller<br>
 *     by default, you cannot bind to unloaded fxml file nodes.
 *
 * @see javafx.fxml.Initializable
 * */
public class BindingGroup {
    private Map<Node, BindingModel> props;
    private String groupName;


    /**
     * create new BindingGroup instance
     * @param groupName String
     * */
    public BindingGroup(String groupName) {
        this.groupName = groupName;
    }


    /**
     * return the binding group name
     * */
    public final String getName() {
        return groupName;
    }


    /**
     * add new binding model
     * @param node the target node that has the property to bind with
     * @param propGetMethodName the name of the getter function in node
     * @param other the target property to bind with node property
     * @param binding the binding function type provided by Bind class
     * @see com.JFXSceneDirector.util.BindingModel com.JFXSceneDirector.util.BindingModel
     * */
    public BindingGroup add(Node node, String propGetMethodName, Property other, BindingFunction binding) {
        if(node != null) {
            if(props == null)
                props = new HashMap<>();

            props.put(node, new BindingModel(node, propGetMethodName, other, binding));
        }
        return this;
    }


    /**
     * remove all bindings
     * */
    public void unbindAll() {
        props.keySet().forEach(K -> props.get(K).unbind());
        props.clear();
    }


}
