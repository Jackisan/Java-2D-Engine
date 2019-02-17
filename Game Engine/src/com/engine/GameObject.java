package com.engine;

import java.util.ArrayList;

public class GameObject implements java.io.Serializable {

    private Transform transform = new Transform();
    ArrayList<Component> components = new ArrayList<>();
    ObjectRenderer objectRenderer;

    boolean destroyed;
    public boolean active = true;
    public String name;
    public String tag;

    public GameObject(String name, Vector position){
        this.name = name;
        transform.getPosition().set(position);
        Handler.addObject(this);
        SceneManager.getCurrentScene().gameObjects.add(this);
    }

    void update(){
        transform.update();
    }

    public Component getComponent(String id){
        for(Component c : components) {
            if(c.id.equals(id) && !c.destroyed) {
                return c;
            }
        }
        return null;
    }

    public ArrayList<Component> getComponents(){
        return components;
    }

    public ObjectRenderer getObjectRenderer(){
        return objectRenderer;
    }

    public void addComponent(Component c){
        components.add(c);
        Handler.addComponent(c);
        if(!Engine.start){
            c.start();
        }
    }

    public void setObjectRenderer(ObjectRenderer r){
        objectRenderer = r;
        Handler.addRenderer(r);
    }

    public Vector getPosition(){
        return transform.getPosition();
    }

    public Transform getTransform(){
        return transform;
    }
}
