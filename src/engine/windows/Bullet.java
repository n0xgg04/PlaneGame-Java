package engine.windows;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Bullet {
    private static final int UPDATE_PER_SECOND = 60;

    private Position position;

    private int speed; //pixel per seconds

    BufferedImage image;
    public Bullet(Position anchorPosition, int speed) {
        //Anchor Position: center of the bullet
        this.position = anchorPosition;


        try {
            image = ImageIO.read(new File("Resources/BULLET.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.position.x -= image.getWidth()/2;
        this.position.y -= image.getHeight();

        this.speed = speed;
    }

    public void draw(Graphics g){
        g.drawImage(image, position.x, position.y, null);
    }

    public void update() {
        this.position.y -= speed / UPDATE_PER_SECOND;
    }

    public Position getPosition() {
        return position;
    }
}
