package com.JFXSceneDirector.util;

import javafx.beans.property.Property;
import javafx.scene.Node;

/**
 * A model that handles the information between two properties that are bounded
 * */
public class BindingModel {
    private BindingFunction binding;
    private Property<?> prop;
    private Property other;
    private boolean bounded;


    /**
     * Create new BindingModel, this model provides an easy way to bind and unbind properties
     * @param node the target node that has the property to bind with
     * @param propGetMethodName the name of the getter function in node
     * @param other the target property to bind with node property
     * @param binding the binding function type provided by Bind class
     * @see com.JFXSceneDirector.util.Bind com.JFXSceneDirector.util.Bind
     * */
    BindingModel(Node node, String propGetMethodName, Property<?> other, BindingFunction binding) {
        this.binding = binding;
        this.other = other;
        this.prop = binding.bind(node, propGetMethodName, other);
        bounded = prop != null;
    }


    /**
     * remove bindings
     * */
    @SuppressWarnings("unchecked")
    public void unbind() {
        if(bounded) {
            if (this.binding.equals(Bind.biDirectional))
                this.prop.unbindBidirectional(this.other);
            else if (this.binding.equals(Bind.uniDirectional))
                this.prop.unbind();
        }
    }


    /**
     * binding state
     * @return boolean
     * */
    public boolean isBounded(){
        return bounded;
    }

}
