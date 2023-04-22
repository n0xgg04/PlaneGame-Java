package engine.windows;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class EnemyPlane {
    private static final int UPDATE_PER_SECOND = 60;

    private Position position;

    BufferedImage image;

    public EnemyPlane(Position position) {
        try {
            image = ImageIO.read(new File("Resources/enemy1.png"));
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
