package com.engine;

import javax.swing.*;

public class Interface {

    public static InterfaceRenderer interfaceRenderer;
    private static JPanel container = Window.container;

    static void start(){
        interfaceRenderer = new InterfaceRenderer(container);
    }

    static void scale(){
        interfaceRenderer.scale();
    }

    public static JPanel getContainer(){
        return container;
    }

}
