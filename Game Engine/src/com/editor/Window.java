package com.editor;

import javax.swing.*;
import java.awt.*;

class Window {

    static JFrame frame;
    static JPanel container;

    static int width = 1200;
    static int height = 700;

    Window() {
        frame = new JFrame("Editor");
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(1200, 700));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setResizable(false);
    }

    static void showFrame(){
        frame.setVisible(true);
        frame.requestFocus();
    }

}
