package com.engine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Engine extends JPanel implements Runnable {

    private Thread thread;
    private boolean running = false;
    private static int framesPerSecond;
    private static int curFrames;
    static Engine instance;
    static boolean start = true;
    static Input input = new Input();
    public static boolean debug = true;

    private Engine(){
        new Window(this);
    }

    public Dimension getPreferredSize(){
        return new Dimension(Window.width, Window.height);
    }

    synchronized void mainThreadStart(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    private void awake(){

        //Initialize things that need to be started first
        addKeyListener(input);
        addMouseListener(input);
        addMouseMotionListener(input);
        addMouseWheelListener(input);
        Interface.start();
    }

    private void start(){

        //Initialize things that need to be after awake
        Handler.InstantiateNew();
        Handler.ClearGarbage();
        Handler.start();
        start = false;
    }

    private synchronized void stop(){
        try{
            thread.join();
            running = false;
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void run(){

        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        int updates = 0;
        long timer = System.currentTimeMillis();
        awake();
        start();
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
            Handler.ClearGarbage();
            Handler.InstantiateNew();
            curFrames++;

            if(System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                //System.out.println("FPS: " + frames + " updates: " + updates);
                Engine.framesPerSecond = curFrames;
                curFrames = 0;
                updates = 0;
            }
        }
        stop();
    }

    private void update(){

        Handler.update();
        Handler.physicsUpdate();

        if(Input.isKeyUp(KeyEvent.VK_PERIOD)){
            debug = !debug;
        }

        //Do input update last
        input.update();

        //Window update is not ready to be used
        //Window.update();
    }

    public void paint(Graphics g){

        super.paint(g);

        Graphics2D g2D = (Graphics2D) g;

        //Graphics drawing goes below

        g2D.setColor(Color.black);
        //Draws a black background on the entire game (which will be overwritten by anything done in future rendering)
        g2D.fillRect(0,0, Window.width, Window.height);

        //Transformable graphics go below
        if(Camera.main != null) {
            g2D.translate(Camera.main.translatedVector().x, Camera.main.translatedVector().y);
        }
        else {
            g2D.drawString("No Main Camera!",Window.middle().x, Window.middle().y);
        }

        Handler.render(g2D);

        if(Camera.main != null) {
            g2D.translate(-Camera.main.translatedVector().x, -Camera.main.translatedVector().y);
        }
        //Transformable graphics end here

        //UI Below

        Handler.renderInterface(g2D);

        //Graphics end here

        //g.dispose();
        //bs.show();
    }

    public static int getFPS(){
        return framesPerSecond;
    }

    public static void main(String args[]){
        instance = new Engine();
    }
}