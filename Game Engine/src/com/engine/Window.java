package com.engine;

import javax.swing.*;
import java.awt.*;

public class Window {

    static JFrame frame;
    static JPanel container;

    public static int width = 1200;
    public static int height = 700;
    public static int gameHeight = 680;

    Window(Engine engine) {
        frame = new JFrame("Engine");
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(600, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        //Use below for fullscreen (might not work, don't remember)
        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //frame.setUndecorated(true);
        //frame.setLayout(null);

        container = new JPanel();
        frame.add(container);
        container.setBounds(frame.getBounds());
        container.setPreferredSize(new Dimension(width, height));
        //container.setLayout(null);
        container.add(engine);
        engine.setLayout(null);
        engine.setBounds(frame.getBounds());
        frame.setVisible(true);
        frame.requestFocus();
        engine.mainThreadStart();
    }

    static Vector middle(){
        return new Vector(width/2,gameHeight/2);
    }

    static void update(){
        boolean changed = false;
        if(width != frame.getWidth()){
            width = frame.getWidth();
            changed = true;
        }
        if(height != frame.getHeight()){
            height = frame.getHeight();
            changed = true;
        }
        if(changed){
            Interface.scale();
        }
    }
}
