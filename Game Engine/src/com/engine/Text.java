package com.engine;

import java.awt.*;

public class Text extends InterfaceComponent{

    public String text;
    public Color color = Color.green;

    public Text(String name, String text, Vector position){
        super(name, position);
        this.text = text;
    }

    public void setPosition(Vector position){
        this.position.x = position.x;
        this.position.y = position.y;
    }
    public Vector getPosition(){
        return position;
    }

}
