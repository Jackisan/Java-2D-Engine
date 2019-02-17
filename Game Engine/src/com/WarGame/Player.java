package com.WarGame;

import com.engine.*;
import com.engine.Physics;

import java.awt.event.KeyEvent;

public class Player extends Component {

    public static final int width = 15;
    public static final int height = 15;
    public int speed = 3;
    public Text playerInfo;

    public Player(GameObject gameObject){
        super(gameObject);
    }

    protected void start() {
        id = "Player";
        playerInfo = new Text("Player Position","(" + getGameObject().getPosition().x +", " + getGameObject().getPosition().y + ")", new Vector(Window.width - 100,12));
        Interface.interfaceRenderer.addText(playerInfo);
    }

    protected void update() {

        playerInfo.text = "(" + getGameObject().getPosition().x +", " + getGameObject().getPosition().y + ")";

        if(Input.isKey(KeyEvent.VK_W)){
            ((Physics)getGameObject().getComponent("Physics")).forceY = -speed;
        }
        else if(Input.isKey(KeyEvent.VK_S)){
            ((Physics)getGameObject().getComponent("Physics")).forceY = speed;
        }
        else{
            ((Physics)getGameObject().getComponent("Physics")).forceY = 0;
        }

        if(Input.isKey(KeyEvent.VK_A)){
            ((Physics)getGameObject().getComponent("Physics")).forceX = -speed;
        }
        else if(Input.isKey(KeyEvent.VK_D)){
            ((Physics)getGameObject().getComponent("Physics")).forceX = speed;
        }
        else{
            ((Physics)getGameObject().getComponent("Physics")).forceX = 0;
        }
    }
}
