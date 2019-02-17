package com.engine;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class InterfaceRenderer extends Renderer {

    public ArrayList<Text> textList = new ArrayList<>();
    public ArrayList<JButton> buttons = new ArrayList<>();
    private JPanel container;

    public InterfaceRenderer(JPanel container){
        this.container = container;
        Handler.interfaceRenderer = this;
    }

    public void render(Graphics2D g) {

        //if in debug mode, draw frame counter
        if(Engine.debug) {
            g.setColor(Color.green);
            g.drawString("" + Engine.getFPS(), 10, 10);
        }

        for(Text t : textList){
            g.setColor(t.color);
            g.drawString(t.text,t.getPosition().x,t.getPosition().y);
        }
    }

    public void scale(){

    }

    public void addText(Text text){
        textList.add(text);
    }

    public void addButton(JButton button){
        buttons.add(button);
        container.add(button);
    }

    public Object getObject(String name){
        for(Text t : textList){
            if(t.name == name){
                return t;
            }
        }
        return null;
    }

}
