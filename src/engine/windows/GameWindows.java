/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.windows;


import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tdh4vn
 */
public class GameWindows extends Frame implements Runnable {


    private static final int UPDATE_PER_SECOND = 60;
    List<GameObject> listGameObject;
    private Background gameBackground;
    private Image image;
    private Graphics second;

    public GameWindows() {
        super();
        listGameObject = new ArrayList<>();
        listGameObject.add(new Plane(
                new Position(250, 100),
                500,
                50,
                2,
                4
        ));

        Plane playerPlane = new Plane(
                new Position(300, 300),
                300,
                15,
                4,
                1
        );
        listGameObject.add(playerPlane);
        this.setSize(480, 800);
        gameBackground = new Background(this.getWidth(), this.getHeight());
        this.setTitle("Techdee");
        this.setFocusable(true);
        this.setVisible(true);
        this.setResizable(false);


        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                super.windowClosing(e);
                dispose();
            }
        });
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_SPACE){
                    listGameObject.add(playerPlane.shot());
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        this.addKeyListener(playerPlane.getKeyListener());
    }


    @Override
    public void update(Graphics g) {
        checkCollide();
        if (image == null) {
            image = createImage(this.getWidth(), this.getHeight());
            //Tạo một 1 graphics ẩn
            second = image.getGraphics();
            //Lấy graphics ẩn
        }
        paint(second);
        for (GameObject gameObject : listGameObject) {
            gameObject.update();
        }
        //Vẽ trên graphics ẩn
        g.drawImage(image, 0, 0, null);
    }

    @Override
    public void paint(Graphics g) {
        gameBackground.draw(g);
        for (GameObject gameObject : listGameObject) {
            gameObject.draw(g);
        }
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

    void checkCollide() {
        for (int i = 0; i < listGameObject.size(); i++) {
            for (int j = 0; j < listGameObject.size(); j++) {
                if (i != j) {
                    if (listGameObject.get(i).isCollide(listGameObject.get(j))) {
                        System.out.println("Collision detected");
                    }
                }
            }
        }
    }

    public void start() {
        Thread mainThread = new Thread(this);
        mainThread.start();
    }
}
