package com.engine;

public class Vector implements java.io.Serializable {

    public int x;
    public int y;

    public Vector(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void set(int x, int y){
        this.x = x;
        this.y = y;
    }
    public void set(Vector v){
        this.x = v.x;
        this.y = v.y;
    }

    public static double Distance(Vector v, Vector v2){
        double dist = Math.sqrt(Math.pow((v.x - v2.x), 2) - Math.pow((v.y - v2.y), 2));
        return dist;
    }

    public Vector subtract(Vector v) {
        return new Vector(x - v.x, y - v.y);
    }
    public Vector add(Vector v){
        return new Vector(x + v.x, y + v.y);
    }
    public Vector multiply(int value){
        return new Vector(x * value, y * value);
    }

}
