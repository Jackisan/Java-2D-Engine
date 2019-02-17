package com.engine;

import java.awt.event.*;

public class Input implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {

    private static final int NUM_OF_KEYS = 700;
    private static boolean[] keys = new boolean[NUM_OF_KEYS];
    private static boolean[] keysLast = new boolean[NUM_OF_KEYS];

    private static final int NUM_OF_BUTTONS = 5;
    private static boolean[] buttons = new boolean[NUM_OF_BUTTONS];
    private static boolean[] buttonsLast = new boolean[NUM_OF_BUTTONS];

    private int mouseX = 0, mouseY = 0;
    private int scroll = 0;

    public void update(){

        for(int i = 0; i < NUM_OF_KEYS; i++){
            keysLast[i] = keys[i];
        }
        for(int i = 0; i < NUM_OF_BUTTONS; i++){
            buttonsLast[i] = buttons[i];
        }
    }

    public void keyTyped(KeyEvent e) {



    }

    public void keyPressed(KeyEvent e) {

        keys[e.getKeyCode()] = true;

    }

    public void keyReleased(KeyEvent e) {

        keys[e.getKeyCode()] = false;

    }

    public void mouseClicked(MouseEvent e) {



    }

    public void mousePressed(MouseEvent e) {

        buttons[e.getButton()] = true;

    }

    public void mouseReleased(MouseEvent e) {

        buttons[e.getButton()] = false;

    }

    public void mouseEntered(MouseEvent e) {



    }

    public void mouseExited(MouseEvent e) {



    }

    public void mouseDragged(MouseEvent e) {



    }

    public void mouseMoved(MouseEvent e) {

        mouseX = (int)(e.getX() / 1);
        mouseY = (int)(e.getY() / 1);

    }

    public void mouseWheelMoved(MouseWheelEvent e) {

        scroll = e.getWheelRotation();

    }

    public static boolean isKey(int keyCode){
        return keys[keyCode];
    }

    public static boolean isKeyUp(int keyCode){
        return !keys[keyCode] && keysLast[keyCode];
    }

    public static boolean isKeyDown(int keyCode){
        return keys[keyCode] && !keysLast[keyCode];
    }

    public static boolean isButton(int buttonCode){
        return buttons[buttonCode];
    }

    public static boolean isButtonUp(int buttonCode){
        return !buttons[buttonCode] && buttonsLast[buttonCode];
    }

    public static boolean isButtonDown(int buttonCode){
        return buttons[buttonCode] && !buttonsLast[buttonCode];
    }

}
