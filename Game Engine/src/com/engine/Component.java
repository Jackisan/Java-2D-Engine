package com.engine;

public abstract class Component implements java.io.Serializable {

    private GameObject gameObject;
    public String id;
    public boolean active = true;
    boolean destroyed;

    public Component(GameObject gameObject){
        this.gameObject = gameObject;
        start();
    }

    protected void start(){

    }
    protected void update(){

    }

    public GameObject getGameObject(){
        return gameObject;
    }
}
