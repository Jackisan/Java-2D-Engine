package com.WarGame;

import com.engine.GameObject;
import com.engine.Renderer;
import java.awt.*;
import java.util.Random;

public class BiomeRenderer extends Renderer {

    Random rand = new Random();
    public GameObject gameObject;
    private Color c = Color.getHSBColor(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255));

    public BiomeRenderer(GameObject gameObject){
        this.gameObject = gameObject;
    }

    public void render(Graphics2D g) {
        g.setColor(c);
        g.fillRect(gameObject.getPosition().x,gameObject.getPosition().y, BiomeGenerator.biomeWidth,BiomeGenerator.biomeHeight);

    }
}
