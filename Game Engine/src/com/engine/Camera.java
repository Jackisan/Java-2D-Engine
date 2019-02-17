package com.engine;

public class Camera extends Component implements java.io.Serializable {

    public static Camera main;

    public Camera(GameObject gameObject){
        super(gameObject);
        id = "Camera";
        if(Camera.main == null){
            main = this;
        }
    }

    public void start(){

    }

    public void update(){

        if(getGameObject().tag.equals("Main Camera")){
            main = this;
        }
    }

    public Vector translatedVector(){
        return new Vector(-getGameObject().getPosition().x + Window.middle().x, -getGameObject().getPosition().y + Window.middle().y);
    }

}
