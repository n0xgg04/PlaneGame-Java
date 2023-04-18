package engine.windows;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Plane {
    private static int UPDATE_PER_SECOND = 60;

    private Position position;

    private int healthPoint;
    private int currentHp;
    private int damage;
    private int speed; // speed * 100 pixel per seconds
    private int type;
    private BufferedImage image;

    private List<Bullet> bullets = new ArrayList<>();
    private List<Bullet> invalidBullets = new ArrayList<>();

    private MovementVector vector;

    public Plane(Position position, int healthPoint, int damage, int speed, int type) {
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
        } catch (IOException ex){
            System.out.println("Error while loading plane image");
        }
    }

    public Position getPosition() {
        return position;
    }

    public BufferedImage getImage() {
        return image;
    }

    public int getSpeed() {
        return speed;
    }

    public int getTickSpeed(){
        //A method that return the amount of change in position between ticks.
        return speed * 100 / UPDATE_PER_SECOND;
    }

    public void shot(){
        int anchorX;
        int anchorY;

        anchorX = position.x + image.getWidth()/2;
        anchorY = position.y;

        Bullet bullet = new Bullet(new Position(anchorX, anchorY), 600);
        bullets.add(bullet);
    }

    public void draw(Graphics g) {
        g.drawImage(image, position.x, position.y, null);
        for(int i = 0; i < bullets.size(); i++){
            Bullet b = bullets.get(i);
            b.draw(g);
        }

        g.setColor(Color.BLACK);
        g.fillRect(position.x, position.y + image.getHeight(), image.getWidth(), 5);
        g.setColor(Color.GREEN);
        float healthPercentage = Math.max(0f, ((float) currentHp / healthPoint));
        g.fillRect(position.x, position.y + image.getHeight(), (int) (healthPercentage * image.getWidth()), 5);
    }

    public void update() {
        this.position.x += this.getTickSpeed() * vector.x;
        this.position.y += this.getTickSpeed() * vector.y;

        for(int i = 0; i < bullets.size(); i++){
            Bullet b = bullets.get(i);
            b.update();
            if(b.getPosition().x < 0 || b.getPosition().y < 0) {
                invalidBullets.add(b);
            }
        }

        bullets.removeAll(invalidBullets);
        invalidBullets.clear();
    }

    public void deductCurrentHp(int amount) {
        currentHp -= amount;
    }
    public void setMovementVector(MovementVector vector) {
        this.vector = vector;
    }
}