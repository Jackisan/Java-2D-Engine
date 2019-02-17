package com.engine;

import java.awt.*;

public class ObjectRenderer extends Renderer implements java.io.Serializable {

    private GameObject gameObject;

    //Use the renderMethod interface to assign the function of render() elsewhere in the code
    private RenderMethod renderMethod;

    //If an image is used, store the path here and call it from renderMethod.render()
    private String imagePath;

    public ObjectRenderer(GameObject gameObject, RenderMethod renderMethod){
        this.gameObject = gameObject;
        this.renderMethod = renderMethod;
    }
    public ObjectRenderer(GameObject gameObject){
        this.gameObject = gameObject;
    }

    public void render(Graphics2D g2D){

        if(renderMethod != null){
            renderMethod.render(g2D);
        }
        else{
            g2D.setColor(Color.pink);
            g2D.fillRect(gameObject.getPosition().x, gameObject.getPosition().y, 10,10);
        }


        //Below are examples of how to draw with outlines, fills, and images

        /*if(useFillColor){

            shape = new Rectangle(gameObject.getPosition().x, gameObject.getPosition().y, 10,10);

            g2D.setColor(color);
            g2D.fill(shape);
        }
        else if(useOutlineColor){

            shape = new Rectangle(gameObject.getPosition().x, gameObject.getPosition().y, 10,10);

            g2D.setColor(color);
            g2D.draw(shape);
        }
        else if(useImageDraw){
            try {
                g2D.drawImage(ImageIO.read(new File(imagePath)), width, height, null);
            } catch(IOException e){
                e.printStackTrace();
            }
        }*/
    }

    public GameObject getGameObject() {
        return gameObject;
    }

    public void setImagePath(String path){
        imagePath = path;
    }

}
