package com.engine;

public class PathfindingAgent extends Component implements java.io.Serializable {

    public float travelSpeed = 1;
    public int colliderWidth;
    public int colliderHeight;

    private Vector target;

    public PathfindingAgent(GameObject gameObject, int colliderWidth, int colliderHeight){
        super(gameObject);

        this.colliderWidth = colliderWidth;
        this.colliderHeight = colliderHeight;
    }

    public void moveToDestination(Vector target){
        float[] speed = Physics.getXYSpeedToTarget(travelSpeed, getGameObject().getPosition(), target);
        ((Physics)getGameObject().getComponent("Physics")).velocityX = (int)speed[0];
        ((Physics)getGameObject().getComponent("Physics")).velocityY = (int)speed[1];
    }
}
