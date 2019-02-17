package com.engine;

import javax.swing.*;
import java.awt.*;

public class Inspector {

    public JFrame frame;

    public Inspector(int width, int height){
        frame = new JFrame("Runtime Inspector");
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.requestFocus();
    }

    public void update(){

    }


}
