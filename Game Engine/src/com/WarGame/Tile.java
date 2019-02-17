package com.WarGame;

import com.engine.Component;
import com.engine.GameObject;

public class Tile extends Component {

    public TileRenderer tileRenderer;

    public Tile(GameObject gameObject){
        super(gameObject);
    }

    protected void start(){
        tileRenderer = new TileRenderer();
    }
}
