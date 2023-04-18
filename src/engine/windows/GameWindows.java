/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.windows;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author Tdh4vn
 */
public class GameWindows extends Frame implements Runnable {


    private static int UPDATE_PER_SECOND = 60;
    private BufferedImage backgroundImage;
    private Plane plane1;
    private Plane plane2;

    int vectorLeft = 0;
    int vectorRight = 0;
    int vectorUp = 0;
    int vectorDown = 0;
    private Image image;
    private Graphics second;

    public GameWindows() {
        super();
        plane1 = new Plane(
                new Position(300, 300),
                300,
                15,
                4,
                1
        );

        plane2 = new Plane(
                new Position(250, 100),
                500,
                50,
                2,
                4
        );

        this.setTitle("Techdee");
        this.setFocusable(true);
        this.setSize(480, 800);
        this.setVisible(true);
        this.setResizable(false);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                super.windowClosing(e);
                dispose();
            }
        });

        try {
            backgroundImage = ImageIO.read(new File("Resources/Background.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_A:
                    case KeyEvent.VK_LEFT:
                        vectorLeft = 1;
                        break;
                    case KeyEvent.VK_W:
                    case KeyEvent.VK_UP:
                        vectorUp = 1;
                        break;
                    case KeyEvent.VK_S:
                    case KeyEvent.VK_DOWN:
                        vectorDown = 1;
                        break;
                    case KeyEvent.VK_D:
                    case KeyEvent.VK_RIGHT:
                        vectorRight = 1;
                        break;
                    case KeyEvent.VK_SPACE:
                        plane1.shot();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_A:
                    case KeyEvent.VK_LEFT:
                        vectorLeft = 0;
                        break;
                    case KeyEvent.VK_W:
                    case KeyEvent.VK_UP:
                        vectorUp = 0;
                        break;
                    case KeyEvent.VK_S:
                    case KeyEvent.VK_DOWN:
                        vectorDown = 0;
                        break;
                    case KeyEvent.VK_D:
                    case KeyEvent.VK_RIGHT:
                        vectorRight = 0;
                        break;
                }
            }
        });
    }


    @Override
    public void update(Graphics g) {
        if (image == null) {
            image = createImage(this.getWidth(), this.getHeight());
            //Tạo một 1 graphics ẩn
            second = image.getGraphics();
            //Lấy graphics ẩn
        }
        paint(second);
        plane1.update();
        //Vẽ trên graphics ẩn
        g.drawImage(image, 0, 0, null);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, null);

        //Draw theo phím

        plane1.setMovementVector(new MovementVector(vectorRight - vectorLeft, vectorDown - vectorUp));

        plane1.draw(g);
        plane2.draw(g);
    }


    @Override
    public void run() {
        while (true) {
            repaint();
            try {
                Thread.sleep(1000 / UPDATE_PER_SECOND);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void start() {
        Thread mainThread = new Thread(this);
        mainThread.start();
    }
}
