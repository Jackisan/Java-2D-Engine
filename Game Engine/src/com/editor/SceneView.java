package com.editor;

import com.engine.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class SceneView extends JPanel implements Runnable {

    private Thread thread;
    private boolean running = true;
    int frames;

    Vector userViewPosition = new Vector(0,0);
    int moveSpeed = 5;

    SceneView(){
        startThread();

        //Initialize Input
        Window.frame.addKeyListener(Editor.input);
        Window.frame.addMouseListener(Editor.input);
        Window.frame.addMouseMotionListener(Editor.input);
        Window.frame.addMouseWheelListener(Editor.input);
    }

    private synchronized void startThread(){
        thread = new Thread(this);
        thread.start();
    }

    private synchronized void stop(){
        try{
            thread.join();
            running = false;
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public Dimension getPreferredSize(){
        return new Dimension(600,600);
    }

    public void run(){
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        int updates = 0;
        long timer = System.currentTimeMillis();
        while(running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1) {
                update();
                updates++;
                delta--;
            }
            Time.deltaTime = (float)delta;
            repaint();
            if(SceneManager.getCurrentScene() != null) {
                Handler.ClearGarbage();
                Handler.InstantiateNew();
            }
            frames++;

            if(System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                //System.out.println("FPS: " + frames + " updates: " + updates);
                frames = 0;
                updates = 0;
            }
        }
        stop();
    }

    public void update(){

        //Do movement with arrows
        if(Input.isKey(KeyEvent.VK_UP)){
            userViewPosition.y -= moveSpeed;
        }
        else if(Input.isKey(KeyEvent.VK_DOWN)){
            userViewPosition.y += moveSpeed;
        }
        if(Input.isKey(KeyEvent.VK_LEFT)){
            userViewPosition.x -= moveSpeed;
        }
        else if(Input.isKey(KeyEvent.VK_RIGHT)){
            userViewPosition.x += moveSpeed;
        }

        //Do input update last
        Editor.input.update();
    }

    public void paint(Graphics g){

        Graphics2D g2d = (Graphics2D)g;

        g2d.setColor(Color.black);
        g2d.fillRect(0,0,1000,1000);

        //Transform the view based on the in game user position
        //Transformable graphics below
        g2d.translate(-userViewPosition.x + (getWidth()/2), -userViewPosition.y + (getHeight()/2));

        if(SceneManager.getCurrentScene() != null) {
            Handler.render(g2d);
        }

        //Transformable graphics end
        g2d.translate(-(-userViewPosition.x + (getWidth()/2)), -(-userViewPosition.y + (getHeight()/2)));

    }


}