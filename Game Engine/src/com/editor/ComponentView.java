package com.editor;

import com.engine.Component;

import javax.swing.*;
import java.lang.reflect.Field;

public class ComponentView extends JPanel {

    private Component component;

    ComponentView(Component component){
        this.component = component;
        PopulateView();
    }

    void setComponent(Component c){
        component = c;
    }

    Component getComponent(){
        return component;
    }

    void PopulateView(){
        for(Field f : component.getClass().getFields()){

        }
    }

}
