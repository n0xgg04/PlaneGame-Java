/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.windows;


import java.awt.*;
import java.awt.event.WindowAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tdh4vn
 */
public class GameWindows extends Frame implements Runnable {


    private static final int UPDATE_PER_SECOND = 60;
    List<GameObject> gameObjects;
    private Background gameBackground;
    private Image image;
    private Graphics second;

    public GameWindows() {
        super();
        gameObjects = new ArrayList<>();
        gameObjects.add(new Plane(
                new Position(250, 100),
                500,
                50,
                2,
                4
        ));

        gameObjects.add(new EnemyPlane(new Position(100, 100)));
        gameObjects.add(new EnemyShip(new Position(150, 200)));
        gameObjects.add(new EnemyShip(new Position(250, 200)));
        gameObjects.add(new EnemySubmarine(new Position(100, 400)));
        gameObjects.add(new EnemySubmarine(new Position(160, 400)));
        gameObjects.add(new EnemySubmarine(new Position(220, 400)));

        Plane playerPlane = new Plane(
                new Position(300, 300),
                300,
                15,
                4,
                1
        );
        gameObjects.add(playerPlane);
        this.setTitle("Techdee");
        this.setFocusable(true);
        this.setSize(480, 800);
        this.setVisible(true);
        this.setResizable(false);

        gameBackground = new Background(this.getWidth(), this.getHeight());

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                super.windowClosing(e);
                dispose();
            }
        });

        this.addKeyListener(playerPlane.getKeyListener());
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
        for (GameObject gameObject : gameObjects) {
            gameObject.update();
        }
        //Vẽ trên graphics ẩn
        g.drawImage(image, 0, 0, null);
    }

    @Override
    public void paint(Graphics g) {
        gameBackground.draw(g);

        for (GameObject gameObject : gameObjects) {
            gameObject.draw(g);
        }

        //Draw theo phím
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
