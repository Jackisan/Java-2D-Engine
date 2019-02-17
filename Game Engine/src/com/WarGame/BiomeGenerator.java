package com.WarGame;

import com.engine.GameObject;
import com.engine.Vector;

public class BiomeGenerator {

    private static final int maxX = 1000;
    private static final int maxY = 1000;

    public static final int biomeWidth = 10;
    public static final int biomeHeight = 10;

    public BiomeGenerator(){

    }

    public void Generate(){
        for(int x = 0; x < maxX; x += biomeWidth){
            for(int y = 0; y < maxY; y += biomeHeight){

                GameObject biome = new GameObject("Biome", new Vector(x,y));
                //ColorRenderer colorRend = new ColorRenderer();
                //colorRend.shape = new Rectangle2D.Float(biome.getPosition().x, biome.getPosition().y, biomeWidth, biomeHeight);
                //biome.addRenderer(colorRend);

            }
        }
    }

}
