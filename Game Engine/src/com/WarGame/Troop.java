package com.WarGame;

import com.engine.Component;
import com.engine.GameObject;
import com.engine.PathfindingAgent;

public class Troop extends Component {

    public PathfindingAgent pathfindingAgent;
    public int health = 100;
    public int speed = 5;

    public Troop(GameObject gameObject) {
        super(gameObject);
    }


}
