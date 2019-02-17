package com.engine;

public abstract class InterfaceComponent {

    public String name;

    public Vector anchor = new Vector(0,0);
    public Vector offset;
    public Vector position;

    public InterfaceComponent(String name, Vector position){
        this.name = name;
        this.position = new Vector(position.x,position.y);
        offset = new Vector(this.position.x - anchor.x,this.position.y - anchor.y);
    }

}
