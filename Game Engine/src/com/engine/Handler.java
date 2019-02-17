package com.engine;

import java.awt.*;
import java.util.ArrayList;

public class Handler {

    public static boolean inEditor;

    //Gets gameobjects from scene now, no use for this
    //static ArrayList<GameObject> gameObjects = new ArrayList<>();
    static ArrayList<Physics> physics = new ArrayList<>();
    static ArrayList<Component> components = new ArrayList<>();
    static ArrayList<Renderer> renderers = new ArrayList<>();
    static InterfaceRenderer interfaceRenderer;
    private static ArrayList<Object> toRemove = new ArrayList<>();
    private static ArrayList<Object> toAdd = new ArrayList<>();

    public static void start(){
        for(Component c : components){
            c.start();
        }
    }

    public static void update(){

        for(GameObject obj : SceneManager.getCurrentScene().gameObjects){
            if(!obj.destroyed && obj.active){
                obj.update();
            }
        }
        for(Component c : components){
            if(!c.destroyed && c.active){
                c.update();
            }
        }
    }

    public static void physicsUpdate(){

        for(Physics physics : physics){
            if(physics.destroyed || !physics.active){
                continue;
            }
            else{
                physics.update();
            }
        }
    }

    public static void render(Graphics2D g2d){
        for(Renderer r : renderers){
            if(!r.destroyed && r.active){
                r.render(g2d);
            }
        }
    }

    public static void renderInterface(Graphics2D g){
        interfaceRenderer.render(g);
    }

    public static void addObject(GameObject object){
        toAdd.add(object);
    }

    public static void addObjects(ArrayList<GameObject> gameObjects){
        toAdd.addAll(gameObjects);
    }

    public static void addExistingObjects(ArrayList<GameObject> gameObjects){
        for(GameObject g : gameObjects){
            toAdd.add(g);
            if(g.components.size() > 0){
                addComponents(g.components);
            }
            if(g.objectRenderer != null){
                addRenderer(g.objectRenderer);
            }
        }
    }

    public static void addExistingObject(GameObject g){
        toAdd.add(g);
        if(g.components.size() > 0){
            toAdd.addAll(g.components);
        }
        if(g.objectRenderer != null){
            toAdd.add(g.objectRenderer);
        }
    }

    public static void addComponent(Component c){
        toAdd.add(c);
    }

    public static void addComponents(ArrayList<Component> components){
        toAdd.addAll(components);
    }

    public static void addRenderer(Renderer r){
        toAdd.add(r);
    }

    public static void destroyGameObject(GameObject object){
        toRemove.add(object);
        object.destroyed = true;
        for(Component c : object.getComponents()){
            destroyComponent(c);
        }
    }

    public static void destroyPhysics(Physics physics){
        toRemove.add(physics);
        physics.destroyed = true;
    }

    public static void destroyComponent(Component c) {
        toRemove.add(c);
        c.destroyed = true;
        c.getGameObject().components.remove(c);
    }

    public static void destroyRenderer(Renderer r){
        toRemove.add(r);
        r.destroyed = true;
        if(r instanceof ObjectRenderer){
            ((ObjectRenderer) r).getGameObject().components.remove(r);
        }
    }

    public static void clear(){
        //I don't think you need this line below
        //toRemove.addAll(gameObjects);
        for(Component c : components){
            toRemove.add(c);
            c.destroyed = true;
        }
        for(Physics physics : physics){
            toRemove.add(physics);
            physics.destroyed = true;
        }
    }

    public static void ClearGarbage(){
        for(Object obj : toRemove){
            if(obj instanceof GameObject){
                SceneManager.getCurrentScene().gameObjects.remove(obj);
            }
            else if(obj instanceof Physics){
                physics.remove(obj);
            }
            else if(obj instanceof Component){
                components.remove(obj);
            }
            else if(obj instanceof Renderer){
                renderers.remove(obj);
            }
        }
        toRemove.clear();
    }

    public static void InstantiateNew(){
        for(Object obj : toAdd){
            if(obj instanceof GameObject){
                SceneManager.getCurrentScene().gameObjects.add((GameObject)obj);
            }
            else if(obj instanceof Physics){
                physics.add((Physics)obj);
            }
            else if(obj instanceof  Component){
                components.add((Component)obj);
            }
            else if(obj instanceof Renderer){
                renderers.add((Renderer)obj);
            }
        }
        toAdd.clear();
    }

}
