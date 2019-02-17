package com.engine;

import java.util.ArrayList;

public class Transform implements java.io.Serializable {

    private Vector position = new Vector(0,0);
    private ArrayList<Transform> children = new ArrayList<>();
    private Transform parent;

    Transform(){

    }

    void update(){
        if(parent != null) {
            position.set(parent.position.subtract(position));
        }
    }

    public void setParent(Transform parentTransform){
        parentTransform.addChild(this);
        parent = parentTransform;
    }

    public void removeParent(){
        parent = null;
    }

    public void addChild(Transform childTransform){
        children.add(childTransform);
    }

    public Vector getPosition(){
        return position;
    }
}
