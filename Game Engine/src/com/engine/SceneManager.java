package com.engine;

import java.io.*;

public class SceneManager {

    private static Scene currentScene;

    public static String directoryName = "C://Users//jackson.eggerd//Documents//Game Engine Saves//";

    public static Scene loadScene(String fileName){

        Handler.clear();

        Scene scene;

        try {
            // Reading the object from a file
            FileInputStream file = new FileInputStream(directoryName + fileName + ".ser");
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            scene = (Scene)in.readObject();
            currentScene = scene;

            in.close();
            file.close();

            System.out.println("Scene: " + fileName + ", has been deserialized");
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }

        return scene;
    }

    public static void setCurrentScene(Scene scene){
        System.out.println("Setting new current scene");

        currentScene = scene;

        Handler.addExistingObjects(scene.gameObjects);
    }

    public static Scene loadSceneWithPath(String filePath){

        Handler.clear();

        Scene scene;

        try {
            // Reading the object from a file
            FileInputStream file = new FileInputStream(filePath);
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            scene = (Scene)in.readObject();
            setCurrentScene(scene);

            in.close();
            file.close();

            System.out.println("Scene from path: " + filePath + ", has been deserialized");
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }

        return scene;
    }

    public static Scene getCurrentScene(){
        return currentScene;
    }
}
