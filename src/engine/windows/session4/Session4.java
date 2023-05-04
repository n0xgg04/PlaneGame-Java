package engine.windows.session4;

import engine.windows.MovementVector;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Session4 extends Frame implements Runnable{

    BufferedImage bufferedImage;
    BufferedImage[] bufferedImageList = new BufferedImage[100];
    public Session4(){
        this.setTitle("Session4");
        this.setFocusable(true);
        this.setSize(1920, 1080);
        this.setVisible(true);
        this.setResizable(false);

        try {
            bufferedImage = ImageIO.read(new File("Resources/cat.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void run() {
        while (true) {
            repaint();
            try {
                Thread.sleep(1000 / 60);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void update(Graphics g) {
        g.drawImage(bufferedImage, 50, 100, null);
    }

    @Override
    public void paint(Graphics g) {

    }

    public void start() {
        Thread mainThread = new Thread(this);
        mainThread.start();
    }
}
