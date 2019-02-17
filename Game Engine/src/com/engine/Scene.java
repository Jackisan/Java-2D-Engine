package com.engine;

import java.io.*;
import java.util.ArrayList;

public class Scene implements java.io.Serializable {

    public ArrayList<GameObject> gameObjects = new ArrayList<>();
    public String savePath;

    public Scene(String savePath){
        this.savePath = savePath;
    }

    public void save(){
        try {
            //Saving of object in a file
            FileOutputStream file = new FileOutputStream(savePath);
            ObjectOutputStream out = new ObjectOutputStream(file);

            // Method for serialization of object
            out.writeObject(this);

            out.close();
            file.close();

            System.out.println("Scene at: " + savePath + ", has been serialized");

        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void saveTo(String absolutePath){
        try {
            //Saving of object in a file
            FileOutputStream file = new FileOutputStream(absolutePath);
            ObjectOutputStream out = new ObjectOutputStream(file);

            // Method for serialization of object
            out.writeObject(this);

            out.close();
            file.close();

            System.out.println("Scene at: " + savePath + ", has been serialized");

        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }

}
