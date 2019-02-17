package com.scenes;

import com.WarGame.Player;
import com.engine.*;
import com.engine.Camera;
import com.engine.Physics;
import com.engine.GameObject;
import com.engine.Window;

import java.awt.*;
import java.util.Random;

public class DefaultScene extends Scene {

    private Random rand = new Random();

    public DefaultScene(String fileName){
        super(fileName);
    }

    public void start(){

        //BiomeGenerator g = new BiomeGenerator();
        //g.Generate();

        for(int i = 0; i < 100; i++) {
            GameObject block = new GameObject("Block", new Vector(rand.nextInt(Window.width), rand.nextInt(Window.height)));
            Physics blockPhys = new Physics(block, 10, 10);
            blockPhys.kinematic = true;
            blockPhys.ignoreCollision = true;
            block.addComponent(blockPhys);
            block.setObjectRenderer(new ObjectRenderer(block, g2D -> {
                g2D.setColor(Color.green);
                g2D.fillRect(block.getPosition().x, block.getPosition().y, 10, 10);
            }));
        }

        GameObject player = new GameObject("Player", new Vector(100,100));
        player.tag = "Main Camera";
        Camera c = new Camera(player);
        player.addComponent(c);
        player.addComponent(new Physics(player,Player.width,Player.height));
        player.addComponent(new Player(player));
        player.setObjectRenderer(new ObjectRenderer(player, g2D -> {
            g2D.setColor(Color.cyan);
            g2D.fillRect(player.getPosition().x, player.getPosition().y, Player.width, Player.height);
        }));

        GameObject child = new GameObject("Child", new Vector(60,60));
        child.getTransform().setParent(player.getTransform());
        child.setObjectRenderer(new ObjectRenderer(child, g2D -> {
            g2D.setColor(Color.LIGHT_GRAY);
            g2D.fillRect(child.getPosition().x, child.getPosition().y, 10, 10);
        }));
    }

}
