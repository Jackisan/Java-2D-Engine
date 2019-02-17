package com.engine;

import java.awt.*;

public class Physics extends Component implements java.io.Serializable {

    public boolean kinematic;
    public boolean ignoreCollision;

    public int velocityX;
    public int velocityY;

    public int forceX;
    public int forceY;

    public int colliderWidth;
    public int colliderHeight;
    public boolean colliding;
    public Physics collidedPhys;

    public Physics(GameObject gameObject, int colliderWidth, int colliderHeight){
        super(gameObject);
        id = "Physics";
        this.colliderWidth = colliderWidth;
        this.colliderHeight = colliderHeight;
        Handler.addComponent(this);
    }

    public void start(){

    }

    public void update(){

        if(!ignoreCollision) {
            CheckCollider();
        }

        if(!kinematic && ForceNotZero()){
            CalcMove();
        }
        else if(kinematic){
            velocityY = forceY;
            velocityX = forceX;
            getGameObject().getPosition().x = (int)(getGameObject().getPosition().x + (velocityX * Time.deltaTime));
            getGameObject().getPosition().y = (int)(getGameObject().getPosition().y + (velocityY * Time.deltaTime));
        }
    }

    public Rectangle getCollider(){
        return new Rectangle(getGameObject().getPosition().x, getGameObject().getPosition().y, colliderWidth, colliderHeight);
    }

    private boolean CollidingWith(Physics phys){
        if(phys.getCollider().intersects(getCollider())){
            return true;
        }
        else{
            return false;
        }
    }

    private boolean ForceNotZero(){
        if(forceX > 0 || forceX < 0 || forceY > 0 || forceY < 0){
            return true;
        }
        else{
            return false;
        }
    }

    private void TriggerCollision(Physics phys){

        collidedPhys = phys;
        colliding = true;
        EnterCollision();
    }

    public static float[] getXYSpeedToTarget(float desiredSpeed, Vector position, Vector target){
        float[] speed = new float[2];
        //set slope[0] and slope[1] to the rise and run of the slope, scaled to travelSpeed
        speed[0] = (target.x - position.x) / desiredSpeed;
        speed[1] = (target.y - position.y) / desiredSpeed;
        float factor = desiredSpeed / (float)Math.sqrt(speed[0] * speed[0] + speed[1] * speed[1]);
        speed[0] *= factor;
        speed[1] *= factor;
        return speed;
    }

    private double DistanceBetween(Rectangle otherCollider){
        //region
        /*
        //top and left
        if((getCollider().y + colliderHeight) < otherCollider.y && (otherCollider.x + otherCollider.getHeight()) < getCollider().x){
            return Vector.Distance(new Vector(getCollider().x, getCollider().y + colliderHeight),new Vector(otherCollider.x + otherCollider.getWidth(), otherCollider.y));
        }
        //left and bottom
        else if((otherCollider.x + otherCollider.getHeight()) < getCollider().x && (otherCollider.y + otherCollider.getHeight()) < getCollider().y){
            return Vector.Distance(new Vector(getCollider().x, getCollider().y), new Vector(otherCollider.x + otherCollider.getWidth(), otherCollider.y + otherCollider.getHeight()));
        }
        //Bottom and right
        else if((otherCollider.y + otherCollider.getHeight()) < getCollider().y && (getCollider().x + colliderWidth) < otherCollider.x){
            return Vector.Distance(new Vector(getCollider().x + colliderWidth, getCollider().y),new Vector(otherCollider.x, otherCollider.y + otherCollider.getHeight()));
        }
        //Right and top
        else if((getCollider().x + colliderWidth) < otherCollider.x && (getCollider().y + colliderHeight) < otherCollider.y){
            return Vector.Distance(new Vector(getCollider().x + colliderWidth, getCollider().y + colliderHeight), new Vector(otherCollider.x, otherCollider.y));
        }
        //Left
        else if((otherCollider.x + otherCollider.getHeight()) < getCollider().x) {
            return getCollider().x - (otherCollider.x + otherCollider.getWidth());
        }
        //Right
        else if(getCollider().x + colliderWidth < otherCollider.x) {
            return otherCollider.x - (getCollider().x + colliderWidth);
        }
        //Bottom
        else if(otherCollider.y + otherCollider.getHeight() < getCollider().y) {
            return otherCollider.y - (otherCollider.y + otherCollider.getHeight());
        }
        //Top
        else if(getCollider().y + colliderHeight < otherCollider.y) {
            return otherCollider.y - (getCollider().y + colliderHeight);
        }
        // rectangles intersect
        else {
            return 0;
        }
        */
        //endregion
        if(getCollider().intersects(otherCollider)){
            return 0;
        }
        //Top Left
        if(otherCollider.x > getCollider().x && otherCollider.y < getCollider().y){

        }
        //Top
        else if(otherCollider.x == getCollider().x && otherCollider.y < getCollider().y){

        }
        //Bottom Left
        else if(otherCollider.x > getCollider().x && otherCollider.y > getCollider().y){

        }
        //Bottom
        else if(otherCollider.x == getCollider().x && otherCollider.y > getCollider().y){

        }
        //Top Right
        else if(otherCollider.x < getCollider().x && otherCollider.y < getCollider().y){

        }
        //Left
        else if(otherCollider.x > getCollider().x && otherCollider.y == getCollider().y){

        }
        //Right
        else if(otherCollider.x < getCollider().x && otherCollider.y == getCollider().y){

        }
        //Top Right
        else if(otherCollider.x < getCollider().x && otherCollider.y > getCollider().y){

        }
        return 0;
    }

    private void CheckCollider(){

        for(Physics phys : Handler.physics){
            if(phys == this || phys.destroyed || !phys.active|| (colliding && collidedPhys == phys && CollidingWith(phys))){
                continue;
            }
            else if(Vector.Distance(phys.getGameObject().getPosition(), getGameObject().getPosition()) > 15){
                continue;
            }
            else if(CollidingWith(phys)){
                colliding = true;
                collidedPhys = phys;

                EnterCollision();
            }
            else if(colliding && collidedPhys == phys && !CollidingWith(phys)){
                ExitCollision();
            }
        }
    }

    private void CalcMove(){
        boolean unrestricted = true;
        velocityY = 0;
        velocityX = 0;
        for(Physics phys : Handler.physics){
            if(phys == this || phys.destroyed || !phys.active){
                continue;
            }
            else if(Vector.Distance(getGameObject().getPosition(),phys.getGameObject().getPosition()) > 15){
                continue;
            }
            else if(new Rectangle(getGameObject().getPosition().x + forceX, getGameObject().getPosition().y + forceY, colliderWidth, colliderHeight).intersects(phys.getCollider())){
                unrestricted = false;
                boolean top = false;
                boolean bottom = false;
                boolean right = false;
                boolean left = false;
                if(!colliding) {
                    TriggerCollision(phys);
                }
                /*if(phys.getCollider().x > getCollider().x && forceX > 0){
                    left = true;
                    gameObject.getPosition().x += phys.gameObject.getPosition().x - (gameObject.getPosition().x + colliderWidth);
                }
                else if(phys.getCollider().x < getCollider().x && forceX < 0) {
                    right = true;
                    gameObject.getPosition().x += (phys.gameObject.getPosition().x + getCollider().getWidth()) - gameObject.getPosition().x;
                }
                if(phys.getCollider().y > getCollider().y && forceY > 0) {
                    bottom = true;
                    gameObject.getPosition().y += phys.gameObject.getPosition().y - (gameObject.getPosition().y + colliderHeight);
                }
                else if(phys.getCollider().y < getCollider().y && forceY < 0) {
                    top = true;
                    gameObject.getPosition().y += (phys.gameObject.getPosition().y + getCollider().getHeight()) - gameObject.getPosition().y;
                }*/

                /*if(top && gameObject.getPosition().y - (phys.gameObject.getPosition().y + phys.colliderHeight) >= 0){
                    velocityX = forceX;
                }
                else if(bottom && phys.gameObject.getPosition().y - (gameObject.getPosition().y + colliderHeight) >= 0){
                    velocityX = forceX;
                }
                else if(right && gameObject.getPosition().x - (phys.gameObject.getPosition().x + phys.colliderWidth) >= 0){
                    velocityY = forceY;
                }
                else if(left && phys.gameObject.getPosition().x - (gameObject.getPosition().x + colliderWidth) >= 0){
                    velocityY = forceY;
                } */

                getGameObject().getPosition().x += velocityX * Time.deltaTime;
                getGameObject().getPosition().y += velocityY * Time.deltaTime;
                break;
            }
        }
        if(unrestricted) {
            velocityY = forceY;
            velocityX = forceX;
            getGameObject().getPosition().x = (int) (getGameObject().getPosition().x + (velocityX * Time.deltaTime));
            getGameObject().getPosition().y = (int) (getGameObject().getPosition().y + (velocityY * Time.deltaTime));
        }

    }

    private void EnterCollision(){

        if(!CollidingWith(collidedPhys)){
            return;
        }
        /*try{
            Method m = gameObject.getClass().getMethod("OnCollisionEnter");
            m.invoke(gameObject);
        }catch(Exception e){

        } */
    }

    private void ExitCollision() {

        colliding = false;
        collidedPhys = null;

        /*try {
            Method m = gameObject.getClass().getMethod("OnCollisionExit");
            m.invoke(gameObject);
        } catch (Exception e) {

        }*/
    }
}
