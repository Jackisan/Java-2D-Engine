package com.engine;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class ColorRenderer extends Renderer {

    public GameObject gameObject;
    public Color color = Color.LIGHT_GRAY;
    public Shape shape = new Rectangle2D.Float(0,0,10,10);

    public ColorRenderer(GameObject gameObject){
        this.gameObject = gameObject;
    }

    public void render(Graphics2D g) {

        g.setColor(color);
        g.fillRect(gameObject.getPosition().x, gameObject.getPosition().y, 10,10);
    }
}
