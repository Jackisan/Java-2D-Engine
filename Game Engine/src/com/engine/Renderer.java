package com.engine;

import java.awt.*;

public abstract class Renderer implements java.io.Serializable {

    boolean destroyed;
    public boolean active = true;

    public abstract void render(Graphics2D g2D);


}
