package com.engine;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ImageRenderer extends Renderer {

    public String imagePath;
    int width, height;

    //Example path: "C:\\Users\\jackson.eggerd\\Desktop\\Pumpkins.jpg"

    public ImageRenderer(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void render(Graphics2D g) {
        try {
            g.drawImage(ImageIO.read(new File(imagePath)), width, height, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
