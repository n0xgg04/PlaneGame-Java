package engine.windows;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Plane extends GameObject {
    private static final int UPDATE_PER_SECOND = 60;

    private final int healthPoint;
    private final int damage;
    private final int speed; // speed * 100 pixel per seconds
    private final int type;
    private final List<GameObject> bullets = new ArrayList<>();
    private final List<GameObject> invalidBullets = new ArrayList<>();
    int goUp;
    int goDown;
    int goLeft;
    int goRight;
    private int currentHp;
    private KeyListener keyListener;
    private Animation explodeAnimation;
    private boolean exploding;

    public Plane(Position position, int healthPoint, int damage, int speed, int type) {
        super(position);
        //this => current object, using this.property will differentiate name from the arguments
        this.position = position;
        this.healthPoint = healthPoint;
        this.damage = damage;
        this.speed = speed;
        this.type = type;
        this.currentHp = healthPoint;
        try {
            switch (this.type) {
                case 1:
                    image = ImageIO.read(new File("Resources/PLANE1.png"));
                    break;
                case 2:
                    image = ImageIO.read(new File("Resources/PLANE2.png"));
                    break;
                case 3:
                    image = ImageIO.read(new File("Resources/PLANE3.png"));
                    break;
                case 4:
                    image = ImageIO.read(new File("Resources/PLANE4.png"));
                    break;
            }
        } catch (IOException ex) {
            System.out.println("Error while loading plane image");
        }

        keyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_A:
                    case KeyEvent.VK_LEFT:
                        goLeft = 1;
                        break;
                    case KeyEvent.VK_W:
                    case KeyEvent.VK_UP:
                        goUp = 1;
                        break;
                    case KeyEvent.VK_S:
                    case KeyEvent.VK_DOWN:
                        goDown = 1;
                        break;
                    case KeyEvent.VK_D:
                    case KeyEvent.VK_RIGHT:
                        goRight = 1;
                        break;
                    case KeyEvent.VK_B:
                        explode();
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_A:
                    case KeyEvent.VK_LEFT:
                        goLeft = 0;
                        break;
                    case KeyEvent.VK_W:
                    case KeyEvent.VK_UP:
                        goUp = 0;
                        break;
                    case KeyEvent.VK_S:
                    case KeyEvent.VK_DOWN:
                        goDown = 0;
                        break;
                    case KeyEvent.VK_D:
                    case KeyEvent.VK_RIGHT:
                        goRight = 0;
                        break;
                }
            }
        };
    }

    private void explode() {
        this.exploding = true;
        try {
            BufferedImage assets = ImageIO.read(new File("Resources/1945assets.png"));
            List<BufferedImage> explodingImages = new ArrayList<>();
            for (int i = 0; i < 7; i++) {
                explodingImages.add(assets.getSubimage(i * 66 + 4, 301, 65, 65));
            }
            explodeAnimation = new Animation(750, explodingImages, true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Bullet shot() {
        int anchorX;
        int anchorY;

        anchorX = position.x + image.getWidth() / 2;
        anchorY = position.y - 30;
        Bullet bullet = new Bullet(new Position(anchorX, anchorY), 600);
        return bullet;
    }

    public void draw(Graphics g) {
        g.drawImage(image, position.x, position.y, null);
        for (GameObject bullet : bullets) {
            bullet.draw(g);
        }

        drawHealthBar(g);
    }

    public void hitWithBullet() {
        //trừ máu
        //Nếu hết máu thì hiện ra màn hình Console chữ "GameOver"
    }

    private void drawHealthBar(Graphics g) {
        if (exploding) return;
        g.setColor(Color.BLACK);
        g.fillRect(position.x, position.y + image.getHeight(), image.getWidth(), 5);
        g.setColor(Color.GREEN);
        float healthPercentage = Math.max(0f, ((float) currentHp / healthPoint));
        g.fillRect(position.x, position.y + image.getHeight(), (int) (healthPercentage * image.getWidth()), 5);
    }

    public void update() {
        for (int i = 0; i < bullets.size(); i++) {
            GameObject b = bullets.get(i);
            b.update();
            if (b.getPosition().x < 0 || b.getPosition().y < 0) {
                invalidBullets.add(b);
            }
        }

        for (GameObject bullet : bullets) {
            bullet.update();
            if (bullet.getPosition().x < 0 || bullet.getPosition().y < 0) {
                invalidBullets.add(bullet);
            }
        }

        bullets.removeAll(invalidBullets);
        invalidBullets.clear();

        if (exploding) {
            this.image = explodeAnimation.getCurrentImage();
        }

        this.position.x += (goRight - goLeft) * speed;
        this.position.y += (goDown - goUp) * speed;
    }

    public void deductCurrentHp(int amount) {
        currentHp -= amount;
    }

    public KeyListener getKeyListener() {
        return keyListener;
    }
}