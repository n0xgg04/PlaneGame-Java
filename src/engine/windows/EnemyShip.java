package engine.windows;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class EnemyShip {
    private static final int UPDATE_PER_SECOND = 60;

    private Position position;

    BufferedImage image;

    public EnemyShip(Position position) {
        try {
            image = ImageIO.read(new File("Resources/ship.png"));
            this.position = position;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void draw(Graphics g){
        g.drawImage(image, position.x, position.y, null);
    }

    public void update() {
    }

    public Position getPosition() {
        return position;
    }
}
